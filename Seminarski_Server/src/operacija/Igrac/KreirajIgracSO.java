/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.Igrac;

import model.Igrac;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author dare2
 */
public class KreirajIgracSO extends ApstraktnaGenerickaOperacija {
    int id = -1;
    
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        if(objekat instanceof Igrac){
            id = broker.add((Igrac)objekat);
        }
    }

    public int getId() {
        return id;
    }
    
}
