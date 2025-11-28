package model;

import domen.ApstraktniDomenskiObjekat;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dare2
 */
public class StavkaRezervacije implements ApstraktniDomenskiObjekat {

    private int rb;
    private int brojSati;
    private double cenaPoSatu;
    private double cenaRez;
    private TipTerena tipTerena;
    private PotvrdaRezervacije potvrdaRezervacije;

    public StavkaRezervacije() {
    }

    public StavkaRezervacije(int rb, int brojSati, double cenaPoSatu, double cenaRez, TipTerena tipTerena, PotvrdaRezervacije potvrdaRezervacije) {
        this.rb = rb;
        this.brojSati = brojSati;
        this.cenaPoSatu = cenaPoSatu;
        this.cenaRez = cenaRez;
        this.tipTerena = tipTerena;
        this.potvrdaRezervacije = potvrdaRezervacije;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public int getBrojSati() {
        return brojSati;
    }

    public void setBrojSati(int brojSati) {
        this.brojSati = brojSati;
    }

    public double getCenaPoSatu() {
        return cenaPoSatu;
    }

    public void setCenaPoSatu(double cenaPoSatu) {
        this.cenaPoSatu = cenaPoSatu;
    }

    public double getCenaRez() {
        return cenaRez;
    }

    public void setCenaRez(double cenaRez) {
        this.cenaRez = cenaRez;
    }

    public TipTerena getTipTerena() {
        return tipTerena;
    }

    public void setTipTerena(TipTerena tipTerena) {
        this.tipTerena = tipTerena;
    }

    public PotvrdaRezervacije getPotvrdaRezervacije() {
        return potvrdaRezervacije;
    }

    public void setPotvrdaRezervacije(PotvrdaRezervacije potvrdaRezervacije) {
        this.potvrdaRezervacije = potvrdaRezervacije;
    }

    @Override
    public String vratiNazivTabele() {
        return "stavkarezervacije";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {

            // ---- STAVKA ----
            int rb = rs.getInt("stavkarezervacije.rb");
            int brojSati = rs.getInt("stavkarezervacije.brojSati");
            double cenaPoSatu = rs.getDouble("stavkarezervacije.cenaPoSatu");
            double cenaRez = rs.getDouble("stavkarezervacije.cenaRez");

            // ---- TIP TERENA ----
            int idTerena = rs.getInt("tipterena.idTipTerena");
            String opis = rs.getString("tipterena.opis");
            double cenaT = rs.getDouble("tipterena.cenaPoSatu");
            TipTerena tt = new TipTerena(idTerena, opis, cenaT);

            // ---- RECEPCIONAR ----
            int idRecepcionar = rs.getInt("recepcionar.idRecepcionar");
            String imePrezimeR = rs.getString("recepcionar.imePrezime");
            LocalDateTime datumR = rs.getTimestamp("recepcionar.datumRodjenja").toLocalDateTime();
            String korisnickoIme = rs.getString("recepcionar.korisnickoIme");
            String sifra = rs.getString("recepcionar.sifra");
            Recepcionar recepcionar = new Recepcionar(idRecepcionar, imePrezimeR, datumR, korisnickoIme, sifra);

            // ---- IGRAČ ----
            int idIgrac = rs.getInt("igrac.idIgrac");
            String imePrezimeI = rs.getString("igrac.imePrezime");
            int idKat = rs.getInt("kategorijaigraca.idKategorijaIgraca");
            String nazivKat = rs.getString("kategorijaigraca.nazivKategorije");
            KategorijaIgraca kat = new KategorijaIgraca(idKat, nazivKat);
            Igrac igrac = new Igrac(idIgrac, imePrezimeI, kat);

            // ---- POTVRDA REZERVACIJE ----
            int idRez = rs.getInt("potvrdarezervacije.idRezervacije");
            double ukupnaCena = rs.getDouble("potvrdarezervacije.cenaUkupno");
            boolean obradjen = rs.getBoolean("potvrdarezervacije.obradjen");

            PotvrdaRezervacije potvrda = new PotvrdaRezervacije(idRez, ukupnaCena, obradjen, recepcionar, igrac);

            // ---- SPOJI SVE ----
            StavkaRezervacije stavka = new StavkaRezervacije(rb, brojSati, cenaPoSatu, cenaRez, tt, potvrda);
            lista.add(stavka);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "brojSati, cenaRez, cenaPoSatu, idTipTerena, idRezervacija";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return brojSati + ", " + cenaRez + ", " + cenaPoSatu + ", "
                + tipTerena.getIdTipTerena() + ", " + potvrdaRezervacije.getIdRezervacije();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "rb=" + rb + " AND idRezervacija=" + potvrdaRezervacije.getIdRezervacije();
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        
        int rb = rs.getInt("rb");
        int brojSati = rs.getInt("brojSati");
        double cenaPoSatu = rs.getDouble("cenaPoSatu");
        double cenaRez = rs.getDouble("cenaRez");
        int idTerena = rs.getInt("idTipTerena");
        TipTerena tt = new TipTerena();
        tt.setIdTipTerena(idTerena);
        int idRez = rs.getInt("idRezervacija");
        PotvrdaRezervacije pr = new PotvrdaRezervacije();
        pr.setIdRezervacije(idRez);
        return new StavkaRezervacije(rb, brojSati, cenaPoSatu, cenaRez, tt, pr);
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "brojSati=" + brojSati + ", cenaRez=" + cenaRez
                + ", cenaPoSatu=" + cenaPoSatu + ", idTipTerena=" + tipTerena.getIdTipTerena();
    }
}
