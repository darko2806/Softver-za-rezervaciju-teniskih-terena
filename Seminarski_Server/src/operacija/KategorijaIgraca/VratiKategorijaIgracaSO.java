/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.KategorijaIgraca;

import java.util.List;
import model.KategorijaIgraca;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author dare2
 */
public class VratiKategorijaIgracaSO extends ApstraktnaGenerickaOperacija {
    
    List<KategorijaIgraca> kategorije;
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        kategorije = broker.getAll(new KategorijaIgraca(), null);
    }

    public List<KategorijaIgraca> getKategorije() {
        return kategorije;
    }
    
}
