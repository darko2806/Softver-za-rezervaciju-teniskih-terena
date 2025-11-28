/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import domen.ApstraktniDomenskiObjekat;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dare2
 */
public class Igrac implements ApstraktniDomenskiObjekat {

    private int idIgrac;
    private String imePrezime;
    private KategorijaIgraca kategorijaIgraca;

    public Igrac() {
    }

    public Igrac(int idKupca, String imePrezime, KategorijaIgraca kategorijaIgraca) {
        this.idIgrac = idKupca;
        this.imePrezime = imePrezime;
        this.kategorijaIgraca = kategorijaIgraca;
    }

    public int getIdIgrac() {
        return idIgrac;
    }

    public void setIdIgrac(int idIgrac) {
        this.idIgrac = idIgrac;
    }

    public String getImePrezime() {
        return imePrezime;
    }

    public void setImePrezime(String imePrezime) {
        this.imePrezime = imePrezime;
    }

    public KategorijaIgraca getKategorijaIgraca() {
        return kategorijaIgraca;
    }

    public void setKategorijaIgraca(KategorijaIgraca kategorijaIgraca) {
        this.kategorijaIgraca = kategorijaIgraca;
    }

    @Override
    public String toString() {
        return imePrezime;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.idIgrac;
        return hash;
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
        final Igrac other = (Igrac) obj;
        return this.idIgrac == other.idIgrac;
    }

    @Override
    public String vratiNazivTabele() {
        return "igrac";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("igrac.idIgrac");
            String ime = rs.getString("igrac.imePrezime");

            int idKategorijaIgraca = rs.getInt("kategorijaIgraca.idKategorijaIgraca");
            String nazivKategorije = rs.getString("kategorijaIgraca.nazivKategorije");
            KategorijaIgraca kategorijaIgraca = new KategorijaIgraca(idKategorijaIgraca, nazivKategorije);

            Igrac k = new Igrac(id, ime, kategorijaIgraca);
            lista.add(k);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "imePrezime, idKategorijaIgraca";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + imePrezime + "', " + kategorijaIgraca.getIdKategorijaIgraca();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "igrac.idIgrac=" + idIgrac;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "imePrezime='" + imePrezime + "', idKategorijaIgraca=" + kategorijaIgraca.getIdKategorijaIgraca();
    }

}
