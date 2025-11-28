/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import domen.ApstraktniDomenskiObjekat;
import java.sql.ResultSet;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dare2
 */
public class RadnaSmena implements ApstraktniDomenskiObjekat {
    
    private int idRadnaSmena;
    private String nazivSmene;
    private LocalTime pocetakSmene;
    private LocalTime krajSmene;

    public RadnaSmena() {
    }

    public RadnaSmena(int idRadnaSmena, String nazivSmene, LocalTime pocetakSmene, LocalTime krajSmene) {
        this.idRadnaSmena = idRadnaSmena;
        this.nazivSmene = nazivSmene;
        this.pocetakSmene = pocetakSmene;
        this.krajSmene = krajSmene;
    }

    public int getIdRadnaSmena() {
        return idRadnaSmena;
    }

    public void setIdRadnaSmena(int idRadnaSmena) {
        this.idRadnaSmena = idRadnaSmena;
    }

    public String getNazivSmene() {
        return nazivSmene;
    }

    public void setNazivSmene(String nazivSmene) {
        this.nazivSmene = nazivSmene;
    }

    public LocalTime getPocetakSmene() {
        return pocetakSmene;
    }

    public void setPocetakSmene(LocalTime pocetakSmene) {
        this.pocetakSmene = pocetakSmene;
    }

    public LocalTime getKrajSmene() {
        return krajSmene;
    }

    public void setKrajSmene(LocalTime krajSmene) {
        this.krajSmene = krajSmene;
    }

    @Override
    public String toString() {
        return pocetakSmene + " - " + krajSmene;
    }
    
    
    
    
    @Override
    public String vratiNazivTabele() {
        return "radnasmena";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("radnasmena.idRadnaSmena");
            String naziv = rs.getString("radnasmena.nazivSmene");
            Time tPocetak = rs.getTime("radnasmena.pocetakSmene");
            Time tKraj = rs.getTime("radnasmena.krajSmene");
            LocalTime pocetak = (tPocetak != null) ? tPocetak.toLocalTime() : null;
            LocalTime kraj = (tKraj != null) ? tKraj.toLocalTime() : null;

            RadnaSmena s = new RadnaSmena(id, naziv, pocetak, kraj);
            lista.add(s);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "nazivSmene, pocetakSmene, krajSmene";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'"+nazivSmene+"','"+pocetakSmene.toString()+"','"+krajSmene.toString()+"'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "radnasmena.idRadnaSmena="+idRadnaSmena;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "nazivSmene='" + nazivSmene + 
               "', pocetakSmene='" + pocetakSmene.toString() + 
               "', krajSmene='" + krajSmene.toString() + "'";
    }
    
}
