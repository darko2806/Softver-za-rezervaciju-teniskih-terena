package model;

import domen.ApstraktniDomenskiObjekat;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PotvrdaRezervacije implements ApstraktniDomenskiObjekat {

    private int idRezervacije;
    private double cenaUkupno;
    private boolean obradjen;
    private Recepcionar recepcionar;
    private Igrac igrac;
    private List<StavkaRezervacije> stavke;

    public PotvrdaRezervacije() {
        stavke = new ArrayList<>();
    }

    public PotvrdaRezervacije(int idRezervacije, double cenaUkupno, boolean obradjen, Recepcionar recepcionar, Igrac igrac) {
        this.idRezervacije = idRezervacije;
        this.cenaUkupno = cenaUkupno;
        this.obradjen = obradjen;
        this.recepcionar = recepcionar;
        this.igrac = igrac;
        this.stavke = new ArrayList<>();
    }

    public int getIdRezervacije() {
        return idRezervacije;
    }

    public void setIdRezervacije(int idRezervacije) {
        this.idRezervacije = idRezervacije;
    }

    public double getCenaUkupno() {
        return cenaUkupno;
    }

    public void setCenaUkupno(double cenaUkupno) {
        this.cenaUkupno = cenaUkupno;
    }

    public boolean isObradjen() {
        return obradjen;
    }

    public void setObradjen(boolean obradjen) {
        this.obradjen = obradjen;
    }

    public Recepcionar getRecepcionar() {
        return recepcionar;
    }

    public void setRecepcionar(Recepcionar recepcionar) {
        this.recepcionar = recepcionar;
    }

    public Igrac getIgrac() {
        return igrac;
    }

    public void setIgrac(Igrac igrac) {
        this.igrac = igrac;
    }

    public List<StavkaRezervacije> getStavke() {
        return stavke;
    }

    public void setStavke(List<StavkaRezervacije> stavke) {
        this.stavke = stavke;
    }

    @Override
    public String toString() {
        return "Potvrda rezervacije #" + idRezervacije + " - " + cenaUkupno + " RSD";
    }

    @Override
    public String vratiNazivTabele() {
        return "potvrdarezervacije";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            int idR = rs.getInt("potvrdarezervacije.idRezervacije");
            double cenaUku = rs.getDouble("potvrdarezervacije.cenaUkupno");
            boolean obradjen = rs.getBoolean("potvrdarezervacije.obradjen");

            // ---- Recepcionar ----
            int idRecepcionar = rs.getInt("recepcionar.idRecepcionar");
            String imePrezimeRecepcionara = rs.getString("recepcionar.imePrezime");
            String korisnickoIme = rs.getString("recepcionar.korisnickoIme");
            String sifra = rs.getString("recepcionar.sifra");
            Recepcionar r = new Recepcionar();
            r.setIdRecepcionar(idRecepcionar);
            r.setImePrezime(imePrezimeRecepcionara);
            r.setKorisnickoIme(korisnickoIme);
            r.setSifra(sifra);

            // ---- Igrač ----
            int idIgrac = rs.getInt("igrac.idIgrac");
            String imePrezimeIgraca = rs.getString("igrac.imePrezime");

            int idKategorija = rs.getInt("kategorijaigraca.idKategorijaIgraca");
            String nazivKategorije = rs.getString("kategorijaigraca.nazivKategorije");
            KategorijaIgraca kategorija = new KategorijaIgraca(idKategorija, nazivKategorije);

            Igrac i = new Igrac(idIgrac, imePrezimeIgraca, kategorija);

            // ---- Potvrda ----
            PotvrdaRezervacije potvrda = new PotvrdaRezervacije(idR, cenaUku, obradjen, r, i);
            lista.add(potvrda);
        }

        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "cenaUkupno, obradjen, idRecepcionar, idIgrac";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        int idR = (recepcionar != null) ? recepcionar.getIdRecepcionar() : 0;
        int idI = (igrac != null) ? igrac.getIdIgrac() : 0;
        return String.format("%.2f, %d, %d, %d", cenaUkupno, (obradjen ? 1 : 0), idR, idI);
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "idRezervacije = " + idRezervacije;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        PotvrdaRezervacije potvrda = new PotvrdaRezervacije();
        potvrda.setIdRezervacije(rs.getInt("idRezervacije"));
        potvrda.setCenaUkupno(rs.getDouble("cenaUkupno"));
        potvrda.setObradjen(rs.getBoolean("obradjen"));
        return potvrda;
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        int idR = (recepcionar != null) ? recepcionar.getIdRecepcionar() : 0;
        int idI = (igrac != null) ? igrac.getIdIgrac() : 0;
        return "cenaUkupno=" + cenaUkupno + ", obradjen=" + (obradjen ? 1 : 0)
                + ", idRecepcionar=" + idR
                + ", idIgrac=" + idI;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(getIdRezervacije());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PotvrdaRezervacije other = (PotvrdaRezervacije) obj;
        return this.idRezervacije == other.idRezervacije;
    }

}
