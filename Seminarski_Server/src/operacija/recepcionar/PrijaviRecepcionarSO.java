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
public class PrijaviRecepcionarSO extends ApstraktnaGenerickaOperacija {
    Recepcionar r;
    @Override
    protected void preduslovi(Object objekat) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        List<Recepcionar> lista = broker.getAll((Recepcionar) objekat, null);
        for (Recepcionar recepcionar : lista) {
            if (recepcionar.equals((Recepcionar) objekat)) {
                r = recepcionar;
                return;
            }
        }
        r = null;
    }

    public Recepcionar getRecepcionar() {
        return r;
    }
    
    
}
