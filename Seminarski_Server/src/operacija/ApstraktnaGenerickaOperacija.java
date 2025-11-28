/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija;

import java.sql.SQLException;
import repository.db.DbRepository;
import repository.db.impl.DbRepositoryGeneric;

/**
 *
 * @author dare2
 */
public abstract class ApstraktnaGenerickaOperacija {
    protected final repository.Repository broker;

    public ApstraktnaGenerickaOperacija() {
        this.broker = new DbRepositoryGeneric();
    }

    public final void izvrsi(Object objekat, String kljuc) throws Exception {
        try {
            preduslovi(objekat);
            zapocniTransakciju();
            izvrsiOperaciju(objekat, kljuc);
            potvrdiTransakciju();
        } catch (SQLException e) {
            ponistiTransakciju();
            throw e;
        } catch (Exception e) {
            ponistiTransakciju();
            e.printStackTrace();
        } finally {
//            try {
//                ugasiKonekciju();
//            } catch (Exception e) {
//                System.out.println("Greška prilikom zatvaranja veze: " + e.getMessage());
//                e.printStackTrace();
//            }
        }
    }
    
    
    protected abstract void preduslovi(Object objekat) throws Exception;

    private void zapocniTransakciju() throws Exception {
        ((DbRepository) broker).connect();
    }

    protected abstract void izvrsiOperaciju(Object objekat, String kljuc) throws Exception;

    private void potvrdiTransakciju() throws Exception {
        ((DbRepository) broker).commit();
    }

    private void ponistiTransakciju() throws Exception {
        ((DbRepository) broker).rollback();
    }

    private void ugasiKonekciju() throws Exception {
        ((DbRepository) broker).disconnect();
    }
}
