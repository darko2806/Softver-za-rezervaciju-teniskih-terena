/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import forme.ServerskaForma;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.Igrac;
import model.KategorijaIgraca;
import model.PotvrdaRezervacije;
import model.RadnaSmena;
import model.Recepcionar;
import model.RecepcionarSmena;
import model.TipTerena;
import operacija.Igrac.KreirajIgracSO;
import operacija.Igrac.ObrisiIgracSO;
import operacija.Igrac.PretraziIgracSO;
import operacija.Igrac.VratiIgraceSO;
import operacija.Igrac.ZapamtiIgracSO;
import operacija.KategorijaIgraca.VratiKategorijaIgracaSO;
import operacija.PotvrdaRezervacije.KreirajPotvrduRezervacijeSO;
import operacija.PotvrdaRezervacije.ObrisiPotvrduRezervacijeSO;
import operacija.PotvrdaRezervacije.PretraziPotvrduRezervacijeSO;
import operacija.PotvrdaRezervacije.VratiPotvrduRezervacijeSO;
import operacija.PotvrdaRezervacije.ZapamtiPotvrduRezervacijeSO;
import operacija.RadnaSmena.VratiRadnuSmenuSO;
import operacija.RecepcionarSmena.UbaciRecepcionarSmenaSO;
import operacija.RecepcionarSmena.VratiRecepcionarSmenaSO;
import operacija.TipTerena.VratiTipoveTerenaSO;
import operacija.recepcionar.PrijaviRecepcionarSO;
import operacija.recepcionar.VratiRecepcionarSO;
import server.ObradaKlijentskihZahteva;

/**
 *
 * @author dare2
 */
public class Controller {
    private static Controller instance;
    private List<ObradaKlijentskihZahteva> klijenti = new ArrayList<>();
    private List<Recepcionar> ulogovaniRecepcionari = new ArrayList<>();
    private ServerskaForma sf;
    
    private Controller() {
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public List<ObradaKlijentskihZahteva> getKlijenti() {
        return klijenti;
    }

    public List<Recepcionar> getLogovaniRecepcionari() {
        return ulogovaniRecepcionari;
    }
    
    public void osveziRecepcionare() {
        ulogovaniRecepcionari = new ArrayList<>();
        sf.setujModel(ulogovaniRecepcionari);
    }
    
    public Recepcionar login(Recepcionar recepcionar) throws Exception {
        if (ulogovaniRecepcionari.contains(recepcionar)) {
            return recepcionar;
        }
        PrijaviRecepcionarSO loginSO = new PrijaviRecepcionarSO();
        loginSO.izvrsi(recepcionar, null);
        return loginSO.getRecepcionar();
    }
    
    public void osveziTabelu(Recepcionar recepcionar) {
        ulogovaniRecepcionari.add(recepcionar);
        sf.setujModel(ulogovaniRecepcionari);
    }
    
     public void setSf(ServerskaForma sf) {
        this.sf = sf;
    }
     
    public void logout(Recepcionar odjavljeni) {
        ulogovaniRecepcionari.remove(odjavljeni);
        sf.setujModel(ulogovaniRecepcionari);
    }
    
    public List<KategorijaIgraca> vratiKategorijeIgraca() throws Exception {
        VratiKategorijaIgracaSO operacija = new VratiKategorijaIgracaSO();
        operacija.izvrsi(null, null);
        return operacija.getKategorije();
    }
    
    public List<Igrac> vratiIgrace() throws Exception {
        VratiIgraceSO operacija = new VratiIgraceSO();
        operacija.izvrsi(null, null);
        return operacija.getIgraci();
    }
    
    public int kreirajIgraca(Object kreirajIgraca) throws Exception {
        KreirajIgracSO operacija = new KreirajIgracSO();
        operacija.izvrsi(kreirajIgraca, null);
        return operacija.getId();
    }
    
    public void obrisiIgraca(Igrac brisanjeIgraca) throws Exception {
        ObrisiIgracSO operacija = new ObrisiIgracSO();
        operacija.izvrsi(brisanjeIgraca, null);
    }
    
    public void zapamtiIgrac(Igrac zapamtiIgrac) throws Exception {
        ZapamtiIgracSO operacija = new ZapamtiIgracSO();
        operacija.izvrsi(zapamtiIgrac, null);
    }
    public List<Igrac> pretraziIgrac(Map<String, Object> mapaIgraca) throws Exception {
        PretraziIgracSO operacija = new PretraziIgracSO();
        operacija.izvrsi(mapaIgraca, null);
        return operacija.getLista();
    }
    
    public List<Recepcionar> vratiRecepcionare() throws Exception {
        VratiRecepcionarSO operacija = new VratiRecepcionarSO();
        operacija.izvrsi(null, null);
        return operacija.getRecepcionari();
    }
    
    public List<TipTerena> vratiTipoveTerena() throws Exception {
        VratiTipoveTerenaSO operacija = new VratiTipoveTerenaSO();
        operacija.izvrsi(null, null);
        return operacija.getTipoviTerena();
    }
    
    public List<RecepcionarSmena> vratiRecSme() throws Exception {
        VratiRecepcionarSmenaSO operacija = new VratiRecepcionarSmenaSO();
        operacija.izvrsi(null, null);
        return operacija.getLista();
    }
    
    public List<RadnaSmena> vratiRadneSmene() throws Exception {
        VratiRadnuSmenuSO operacija = new VratiRadnuSmenuSO();
        operacija.izvrsi(null, null);
        return operacija.getLista();
    }
    public void ubaciSmenu(RecepcionarSmena ubaciRecSme) throws Exception {
        UbaciRecepcionarSmenaSO operacija = new UbaciRecepcionarSmenaSO();
        operacija.izvrsi(ubaciRecSme, null);
    }
    public List<PotvrdaRezervacije> vratiPotvrde() throws Exception{
        VratiPotvrduRezervacijeSO operacija = new VratiPotvrduRezervacijeSO();
        operacija.izvrsi(null, null);
        return operacija.getLista();
    }
    public List<PotvrdaRezervacije> pretraziPotvrde(Map<String, Object> mapa) throws Exception {
        PretraziPotvrduRezervacijeSO operacija = new PretraziPotvrduRezervacijeSO();
        operacija.izvrsi(mapa, null);
        return operacija.getLista();
    }
    public int kreirajPotvrduRezervacije(Object kreirajPotvrdu) throws Exception {
        KreirajPotvrduRezervacijeSO operacija = new KreirajPotvrduRezervacijeSO();
        operacija.izvrsi(kreirajPotvrdu, null);
        return operacija.getId();
    }
    public void obrisiPotvrdu(PotvrdaRezervacije brisanjePotvrde) throws Exception {
        ObrisiPotvrduRezervacijeSO operacija = new ObrisiPotvrduRezervacijeSO();
        operacija.izvrsi(brisanjePotvrde, null);
    }
    public PotvrdaRezervacije zapamtiPotvrdu(Map<String, Object> zapamtiPotvrdu) throws Exception {
        ZapamtiPotvrduRezervacijeSO operacija = new ZapamtiPotvrduRezervacijeSO();
        operacija.izvrsi(zapamtiPotvrdu, null);
        return operacija.getPr();
    }
    
}
