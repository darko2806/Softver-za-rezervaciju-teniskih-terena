/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import domen.ApstraktniDomenskiObjekat;
import java.sql.ResultSet;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dare2
 */
public class RecepcionarSmena implements ApstraktniDomenskiObjekat {

    private LocalDateTime datumRecepcionarSmena;
    private Recepcionar recepcionar;
    private RadnaSmena smena;

    public RecepcionarSmena() {
    }

    public RecepcionarSmena(LocalDateTime datumRecepcionarSmena, Recepcionar recepcionar, RadnaSmena smena) {
        this.datumRecepcionarSmena = datumRecepcionarSmena;
        this.recepcionar = recepcionar;
        this.smena = smena;
    }

    public LocalDateTime getDatumRecepcionarSmena() {
        return datumRecepcionarSmena;
    }

    public void setDatumRecepcionarSmena(LocalDateTime datumRecepcionarSmena) {
        this.datumRecepcionarSmena = datumRecepcionarSmena;
    }

    public Recepcionar getRecepcionar() {
        return recepcionar;
    }

    public void setRecepcionar(Recepcionar recepcionar) {
        this.recepcionar = recepcionar;
    }

    public RadnaSmena getSmena() {
        return smena;
    }

    public void setSmena(RadnaSmena smena) {
        this.smena = smena;
    }

    @Override
    public String vratiNazivTabele() {
        return "recepcionarsmena";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            LocalDateTime datum = rs.getTimestamp("recepcionarsmena.datumRecepcionarSmena").toLocalDateTime();

            int idRecepcionar = rs.getInt("recepcionar.idRecepcionar");
            String imePrezime = rs.getString("recepcionar.imePrezime");
            LocalDateTime datumRodjenja = null;
            if (rs.getTimestamp("recepcionar.datumRodjenja") != null) {
                datumRodjenja = rs.getTimestamp("recepcionar.datumRodjenja").toLocalDateTime();
            }
            String korisnickoIme = rs.getString("recepcionar.korisnickoIme");
            String sifra = rs.getString("recepcionar.sifra");
            Recepcionar recepcionar = new Recepcionar(idRecepcionar, imePrezime, datumRodjenja, korisnickoIme, sifra);

            int rbSmene = rs.getInt("radnasmena.idRadnaSmena");
            String nazivSmene = rs.getString("radnasmena.nazivSmene");
            Time tPocetak = rs.getTime("radnasmena.pocetakSmene");
            Time tKraj = rs.getTime("radnasmena.krajSmene");
            LocalTime pocetakSmene = tPocetak != null ? tPocetak.toLocalTime() : null;
            LocalTime krajSmene = tKraj != null ? tKraj.toLocalTime() : null;
            RadnaSmena RadnaSmena = new RadnaSmena(rbSmene, nazivSmene, pocetakSmene, krajSmene);

            RecepcionarSmena ps = new RecepcionarSmena(datum, recepcionar, RadnaSmena);
            lista.add(ps);
        }
        return lista;

    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "idRecepcionar, idRadnaSmena, datumRecepcionarSmena";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return recepcionar.getIdRecepcionar() + "," + smena.getIdRadnaSmena() +",'"+datumRecepcionarSmena+"'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "idRecepcionar=" + recepcionar.getIdRecepcionar() + " AND idRadnaSmena=" + smena.getIdRadnaSmena() + " AND datumRecepcionarSmena='" + datumRecepcionarSmena + "'";
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "datumRecepcionarSmena='" + datumRecepcionarSmena.toString() + "'";
    }
}