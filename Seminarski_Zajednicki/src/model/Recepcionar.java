/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import domen.ApstraktniDomenskiObjekat;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author dare2
 */
public class Recepcionar implements ApstraktniDomenskiObjekat {
    
    private int idRecepcionar;
    private String imePrezime;
    private LocalDateTime datumRodjenja;
    private String korisnickoIme;
    private String sifra;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    public Recepcionar() {
    }

    public Recepcionar(int idRecepcionar, String imePrezime, LocalDateTime datumRodjenja, String korisnickoIme, String sifra) {
        this.idRecepcionar = idRecepcionar;
        this.imePrezime = imePrezime;
        this.datumRodjenja = datumRodjenja;
        this.korisnickoIme = korisnickoIme;
        this.sifra = sifra;
    }

    public int getIdRecepcionar() {
        return idRecepcionar;
    }

    public void setIdRecepcionar(int idRecepcionar) {
        this.idRecepcionar = idRecepcionar;
    }

    public String getImePrezime() {
        return imePrezime;
    }

    public void setImePrezime(String imePrezime) {
        this.imePrezime = imePrezime;
    }

    public LocalDateTime getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(LocalDateTime datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    @Override
    public String toString() {
        return imePrezime;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.korisnickoIme);
        hash = 97 * hash + Objects.hashCode(this.sifra);
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
        final Recepcionar other = (Recepcionar) obj;
        if (!Objects.equals(this.korisnickoIme, other.korisnickoIme)) {
            return false;
        }
        return Objects.equals(this.sifra, other.sifra);
    }
    
    
    @Override
    public String vratiNazivTabele() {
        return "recepcionar";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("recepcionar.idRecepcionar");
            String ime = rs.getString("recepcionar.imePrezime");
            LocalDateTime datum = rs.getTimestamp("recepcionar.datumRodjenja").toLocalDateTime();
            String korisnickoIme = rs.getString("recepcionar.korisnickoIme");
            String sifra = rs.getString("recepcionar.sifra");
            Recepcionar r = new Recepcionar(id, ime, datum, korisnickoIme, sifra);
            lista.add(r);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "imePrezime, datumRodjenja, korisnickoIme, sifra";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'"+imePrezime+"','"+datumRodjenja.format(formatter)+"','"+korisnickoIme+"','"+sifra+"'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "recepcionar.idRecepcionar="+idRecepcionar;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "imePrezime='" + imePrezime + 
               "', datumRodjenja='" + datumRodjenja.format(formatter) + 
               "', korisnickoIme='" + korisnickoIme + 
               "', sifra='" + sifra + "'";
    }
    
}
