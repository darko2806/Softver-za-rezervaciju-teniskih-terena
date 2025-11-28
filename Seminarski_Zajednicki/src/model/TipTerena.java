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
public class TipTerena implements ApstraktniDomenskiObjekat {

    private int idTipTerena;
    private String opis;
    private double cenaPoSatu;

    public TipTerena() {
    }

    public TipTerena(int idTipTerena, String opis, double cenaPoSatu) {
        this.idTipTerena = idTipTerena;
        this.opis = opis;
        this.cenaPoSatu = cenaPoSatu;
    }

    public int getIdTipTerena() {
        return idTipTerena;
    }

    public void setIdTipTerena(int idTipTerena) {
        this.idTipTerena = idTipTerena;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public double getCenaPoSatu() {
        return cenaPoSatu;
    }

    public void setCenaPoSatu(double cenaPoSatu) {
        this.cenaPoSatu = cenaPoSatu;
    }

    @Override
    public String vratiNazivTabele() {
        return "tipterena";
    }


@Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while(rs.next()){
            int idTerena = rs.getInt("tipterena.idTipTerena");
            String opis = rs.getString("tipterena.opis");
            double cena = rs.getDouble("tipterena.cenaPoSatu");
            
            TipTerena tt = new TipTerena(idTerena, opis, cena);
            lista.add(tt);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
       return "opis, cenaPoSatu";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + opis + "', " + cenaPoSatu;
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "tipterena.idTipTerena" + idTipTerena;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        if(rs.next()){
            int idTerena = rs.getInt("tipterena.idTipTerena");
            String opis = rs.getString("tipterena.opis");
            double cena = rs.getDouble("tipterena.cenaPoSatu");
            return new TipTerena(idTerena, opis, cena);
        }
        return null;
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return  "opis='" + opis + "', cenaPoSatu=" + cenaPoSatu;
    }

    @Override
    public String toString() {
        return opis;
    }
    
}
