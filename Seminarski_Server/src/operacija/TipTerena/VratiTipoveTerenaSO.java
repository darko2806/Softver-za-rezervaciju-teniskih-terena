/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.TipTerena;

import java.util.ArrayList;
import java.util.List;
import model.TipTerena;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author dare2
 */
public class VratiTipoveTerenaSO extends ApstraktnaGenerickaOperacija {
    List<TipTerena> tipoviTerena = new ArrayList<>();
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {

    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        tipoviTerena = broker.getAll(new TipTerena(), null);
    }

    public List<TipTerena> getTipoviTerena() {
        return tipoviTerena;
    }
    
    
}
