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
public class ObrisiPotvrduRezervacijeSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object objekat) throws Exception {

    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        PotvrdaRezervacije brisanje = (PotvrdaRezervacije) objekat;
        broker.delete(brisanje);
    }
    
}
