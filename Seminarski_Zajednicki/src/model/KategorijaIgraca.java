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
public class KategorijaIgraca implements ApstraktniDomenskiObjekat {

    private int idKategorijaIgraca;
    private String nazivKategorije;

    public KategorijaIgraca() {
    }

    public KategorijaIgraca(int idKategorijaIgraca, String nazivKategorije) {
        this.idKategorijaIgraca = idKategorijaIgraca;
        this.nazivKategorije = nazivKategorije;
    }

    @Override
    public String toString() {
        return nazivKategorije;
    }

    public int getIdKategorijaIgraca() {
        return idKategorijaIgraca;
    }

    public void setIdKategorijaIgraca(int idKategorijaIgraca) {
        this.idKategorijaIgraca = idKategorijaIgraca;
    }

    public String getNazivKategorije() {
        return nazivKategorije;
    }

    public void setNazivKategorije(String nazivKategorije) {
        this.nazivKategorije = nazivKategorije;
    }

    @Override
    public String vratiNazivTabele() {
        return "kategorijaigraca";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("idKategorijaIgraca");          
            String naziv = rs.getString("nazivKategorije");     
            KategorijaIgraca k = new KategorijaIgraca(id, naziv);
            lista.add(k);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "nazivKategorije";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + nazivKategorije + "'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "kategorijaIgraca.idKategorijaIgraca" + idKategorijaIgraca;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "nazivKategorije='" + nazivKategorije + "'";
    }

}
