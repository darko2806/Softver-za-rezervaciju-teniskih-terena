/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.RadnaSmena;

import java.util.List;
import model.RadnaSmena;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author dare2
 */
public class VratiRadnuSmenuSO extends ApstraktnaGenerickaOperacija {
    List<RadnaSmena> lista;
    @Override
    protected void preduslovi(Object objekat) throws Exception {

    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        lista = broker.getAll(new RadnaSmena(), null);
    }

    public List<RadnaSmena> getLista() {
        return lista;
    }
    
    
}
