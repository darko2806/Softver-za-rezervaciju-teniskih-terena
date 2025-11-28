/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.db.impl;

import domen.ApstraktniDomenskiObjekat;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import repository.db.DbConnectionFactory;
import repository.db.DbRepository;

/**
 *
 * @author dare2
 */
public class DbRepositoryGeneric implements DbRepository<ApstraktniDomenskiObjekat> {

    @Override
    public int add(ApstraktniDomenskiObjekat param) throws Exception {
        String upit = "INSERT INTO " + param.vratiNazivTabele() + "(" + param.vratiKoloneZaUbacivanje() + ") VALUES(" + param.vratiVrednostiZaUbacivanje() + ")";
        PreparedStatement st = DbConnectionFactory.getInstance().getConnection().prepareStatement(upit, Statement.RETURN_GENERATED_KEYS);
        st.executeUpdate();
        DbConnectionFactory.getInstance().getConnection().commit();
        ResultSet rs = st.getGeneratedKeys();
        int id = -1;
        if (rs.next()) {
            id = rs.getInt(1);
        }
        st.close();
        rs.close();
        return id;
    }

    @Override
    public void edit(ApstraktniDomenskiObjekat param) throws Exception {
        String upit = "UPDATE " + param.vratiNazivTabele()
                + " SET " + param.vratiVrednostiZaIzmenu()
                + " WHERE " + param.vratiPrimarniKljuc();

        Statement st = DbConnectionFactory.getInstance().getConnection().createStatement();
        st.executeUpdate(upit);
        st.close();
    } 

    @Override
    public void delete(ApstraktniDomenskiObjekat param) throws Exception {

        String upit = "DELETE FROM " + param.vratiNazivTabele() + " WHERE " + param.vratiPrimarniKljuc();
        Statement st = DbConnectionFactory.getInstance().getConnection().createStatement();
        int a = st.executeUpdate(upit);

        st.close();
    }

    @Override
    public List<ApstraktniDomenskiObjekat> getAll(ApstraktniDomenskiObjekat param, String uslov) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        String upit = "SELECT * FROM " + param.vratiNazivTabele();
        if (uslov != null) {
            upit += uslov;
        }
        Statement st = DbConnectionFactory.getInstance().getConnection().createStatement();
        ResultSet rs = st.executeQuery(upit);
        lista = param.vratiListu(rs);

        rs.close();
        st.close();
        return lista;
    }
}
