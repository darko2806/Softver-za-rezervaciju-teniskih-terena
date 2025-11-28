/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import komunikacija.Odgovor;
import komunikacija.Operacija;
import komunikacija.Posiljalac;
import komunikacija.Primalac;
import komunikacija.Zahtev;
import model.Igrac;
import model.KategorijaIgraca;
import model.PotvrdaRezervacije;
import model.RadnaSmena;
import model.Recepcionar;
import model.RecepcionarSmena;
import model.TipTerena;

/**
 *
 * @author dare2
 */
public class ObradaKlijentskihZahteva extends Thread {

    Socket klijentskiSoket;
    Server server;
    Posiljalac posiljalac;
    Primalac primalac;
    private boolean radi = true;

    public ObradaKlijentskihZahteva(Socket klijentskiSoket, Server server) {
        this.klijentskiSoket = klijentskiSoket;
        this.server = server;
        posiljalac = new Posiljalac(klijentskiSoket);
        primalac = new Primalac(klijentskiSoket);
    }

    @Override
    public void run() {
        while (radi) {
            if (klijentskiSoket.isClosed()) {
                break;
            }
            Zahtev zahtev;
            zahtev = (Zahtev) primalac.primi();

            if (zahtev == null) {
                System.out.println("Klijent se iskljucio.");
                break;
            }
            switch (zahtev.getOperacija()) {
                case LOGIN:
                    try {
                        Recepcionar recepcionar = (Recepcionar) zahtev.getParametar();
                        recepcionar = controller.Controller.getInstance().login(recepcionar);
                        if (recepcionar != null && recepcionar.getIdRecepcionar() != 0) {
                            controller.Controller.getInstance().osveziTabelu(recepcionar);
                        }
                        obavestiKlijenta(Operacija.LOGIN, recepcionar);
                    } catch (Exception ex) {
                        Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case LOGOUT:
                    Recepcionar odjavljeni = (Recepcionar) zahtev.getParametar();
                    controller.Controller.getInstance().logout(odjavljeni);
                    controller.Controller.getInstance().getKlijenti().remove(this);
                    break;
                case KREIRAJ_IGRACA:
                    Object kreirajIgraca = zahtev.getParametar();
                    int idIgrac = -1;
                    try {
                        idIgrac = controller.Controller.getInstance().kreirajIgraca(kreirajIgraca);
                    } catch (Exception ex) {
                        Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    obavestiKlijenta(Operacija.KREIRAJ_IGRACA, idIgrac);
                    break;
                case KREIRAJ_POTVRDU_REZERVACIJE:
                    Object kreirajPR = zahtev.getParametar();
                    int idRezervacije = -1;
                    try {
                        idRezervacije = controller.Controller.getInstance().kreirajPotvrduRezervacije(kreirajPR);
                    } catch (Exception ex) {
                        Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    obavestiKlijenta(Operacija.KREIRAJ_POTVRDU_REZERVACIJE, idRezervacije);
                    break;
                case OBRISI_IGRACA:
                    Igrac brisanjeIgraca = (Igrac) zahtev.getParametar();
                    boolean uspeh = false;
                    try {
                        controller.Controller.getInstance().obrisiIgraca(brisanjeIgraca);
                        uspeh = true;
                    } catch (Exception ex) {

                    }
                    obavestiKlijenta(Operacija.OBRISI_IGRACA, uspeh);
                    break;
                case OBRISI_POTVRDU_REZERVACIJE:
                    PotvrdaRezervacije brisanjePotvrde = (PotvrdaRezervacije) zahtev.getParametar();
                    try {
                        controller.Controller.getInstance().obrisiPotvrdu(brisanjePotvrde);
                    } catch (Exception ex) {
                        Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case PRETRAZI_IGRACA:
                    Map<String, Object> mapaIgraca = (Map<String, Object>) zahtev.getParametar();
                    List<Igrac> pretrazeniIgraci = new ArrayList<>();
                    try {
                        pretrazeniIgraci = controller.Controller.getInstance().pretraziIgrac(mapaIgraca);
                    } catch (Exception ex) {
                        Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    obavestiKlijenta(Operacija.PRETRAZI_IGRACA, pretrazeniIgraci);
                    break;
                case PRETRAZI_POTVRDE_REZERVACIJE:
                    List<PotvrdaRezervacije> pretragaPotvrda = new ArrayList<>();
                    Map<String, Object> mapa = (Map<String, Object>) zahtev.getParametar();
                    try {
                        pretragaPotvrda = controller.Controller.getInstance().pretraziPotvrde(mapa);
                    } catch (Exception ex) {
                        Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    obavestiKlijenta(Operacija.PRETRAZI_POTVRDE_REZERVACIJE, pretragaPotvrda);
                    break;
                case UBACI_RECEPCIONARSMENU:
                    RecepcionarSmena ubaciRecSme = (RecepcionarSmena) zahtev.getParametar();
                    try {
                        controller.Controller.getInstance().ubaciSmenu(ubaciRecSme);
                    } catch (Exception ex) {
                        Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case VRATI_IGRACE:
                    List<Igrac> sviIgraci = new ArrayList<>();
                    try {
                        sviIgraci = controller.Controller.getInstance().vratiIgrace();
                    } catch (Exception ex) {
                        Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    obavestiKlijenta(Operacija.VRATI_IGRACE, sviIgraci);
                    break;
                case VRATI_KATEGORIJE_IGRACA:
                    List<KategorijaIgraca> sveKategorije = new ArrayList<>();
                    try {
                        sveKategorije = controller.Controller.getInstance().vratiKategorijeIgraca();
                    } catch (Exception ex) {
                        Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    obavestiKlijenta(Operacija.VRATI_KATEGORIJE_IGRACA, sveKategorije);
                    break;
                case VRATI_POTVRDE_REZERVACIJE:
                    List<PotvrdaRezervacije> potvrde = new ArrayList<>();
                    try {
                        potvrde = controller.Controller.getInstance().vratiPotvrde();
                    } catch (Exception ex) {
                        Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    obavestiKlijenta(Operacija.VRATI_POTVRDE_REZERVACIJE, potvrde);
                    break;
                case VRATI_RADNASMENA:
                    List<RadnaSmena> sveSmene = new ArrayList<>();
                    try {
                        sveSmene = controller.Controller.getInstance().vratiRadneSmene();
                    } catch (Exception ex) {
                        Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    obavestiKlijenta(Operacija.VRATI_RADNASMENA, sveSmene);
                    break;
                case VRATI_RECEPCIONARE:
                    List<Recepcionar> sviRecepcionari = new ArrayList<>();
                    try {
                        sviRecepcionari = controller.Controller.getInstance().vratiRecepcionare();
                    } catch (Exception ex) {
                        Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    obavestiKlijenta(Operacija.VRATI_RECEPCIONARE, sviRecepcionari);
                    break;
                case VRATI_RECEPCIONARSMENA:
                    List<RecepcionarSmena> sviRecSme = new ArrayList<>();
                    try {
                        sviRecSme = controller.Controller.getInstance().vratiRecSme();
                    } catch (Exception ex) {
                        Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    obavestiKlijenta(Operacija.VRATI_RECEPCIONARSMENA, sviRecSme);
                    break;
                case VRATI_TIPOVE_TERENA:
                    List<TipTerena> sviTipoviTerena = new ArrayList<>();
                    try {
                        sviTipoviTerena = controller.Controller.getInstance().vratiTipoveTerena();
                    } catch (Exception ex) {
                        Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    obavestiKlijenta(Operacija.VRATI_TIPOVE_TERENA, sviTipoviTerena);
                    break;
                case ZAPAMTI_IGRACA:
                    Igrac zapamtiIgraca = (Igrac) zahtev.getParametar();
                    try {
                        controller.Controller.getInstance().zapamtiIgrac(zapamtiIgraca);
                    } catch (Exception ex) {
                        Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case ZAPAMTI_POTVRDU_REZERVACIJE:
                    Map<String, Object> zapamtiPotvrdu = (Map<String, Object>) zahtev.getParametar();
                    PotvrdaRezervacije promenjeno = null;
                    try {
                        promenjeno = controller.Controller.getInstance().zapamtiPotvrdu(zapamtiPotvrdu);
                    } catch (Exception ex) {
                        Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    obavestiKlijenta(Operacija.ZAPAMTI_POTVRDU_REZERVACIJE, promenjeno);
                    break;
                
                default:
                    System.out.println("Ne postoji ta operacija!");

            }

        }
        zausaviNit();
    }

    public void obavestiKlijenta(Operacija operacija, Object odgovor) {
        Odgovor so = new Odgovor(odgovor, operacija);
        posiljalac.posalji(so);
    }

    private void zausaviNit() {
        radi = false;
        try {
            if (!klijentskiSoket.isClosed() && !klijentskiSoket.isClosed()) {
                klijentskiSoket.shutdownInput();
                klijentskiSoket.shutdownOutput();
                klijentskiSoket.close();

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
