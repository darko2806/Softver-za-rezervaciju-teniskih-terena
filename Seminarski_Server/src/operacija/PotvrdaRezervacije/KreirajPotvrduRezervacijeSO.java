/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.PotvrdaRezervacije;

import model.PotvrdaRezervacije;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author dare2
 */
public class KreirajPotvrduRezervacijeSO extends ApstraktnaGenerickaOperacija {

    int id = -1;

    @Override
    protected void preduslovi(Object objekat) throws Exception {

    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        if (objekat instanceof PotvrdaRezervacije) {
            
            id = broker.add((PotvrdaRezervacije) objekat);
        }

    }

    public int getId() {
        return id;
    }

}
