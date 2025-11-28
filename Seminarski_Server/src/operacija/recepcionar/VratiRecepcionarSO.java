/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.recepcionar;

import java.util.List;
import model.Recepcionar;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author dare2
 */
public class VratiRecepcionarSO extends ApstraktnaGenerickaOperacija {
    List<Recepcionar> recepcionari;
    @Override
    protected void preduslovi(Object objekat) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        recepcionari = broker.getAll(new Recepcionar(), null);
    }

    public List<Recepcionar> getRecepcionari() {
        return recepcionari;
    }
    
}
