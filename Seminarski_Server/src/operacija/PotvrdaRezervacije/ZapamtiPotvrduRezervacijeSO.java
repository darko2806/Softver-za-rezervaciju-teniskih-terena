/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.PotvrdaRezervacije;

import java.util.List;
import java.util.Map;
import model.PotvrdaRezervacije;
import model.StavkaRezervacije;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author dare2
 */
public class ZapamtiPotvrduRezervacijeSO extends ApstraktnaGenerickaOperacija {
    PotvrdaRezervacije pr;
    @Override
    protected void preduslovi(Object objekat) throws Exception {

    }
    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        Map<String, Object> mapa = (Map<String, Object>) objekat;
         pr = (PotvrdaRezervacije) mapa.get("potvrdarezervacije");

        List<StavkaRezervacije> stavke = (List<StavkaRezervacije>) mapa.get("stavke");
        List<StavkaRezervacije> zaBrisanje = (List<StavkaRezervacije>) mapa.get("zaBrisanje");
        

        broker.edit(pr);

        if (zaBrisanje != null) {
            for (StavkaRezervacije sr : zaBrisanje) {
                broker.delete(sr);
            }
        }

        if (stavke != null && stavke.size()!=0) {
            for (StavkaRezervacije sr : stavke) {
                if (sr.getRb()== -1) {
                    int id = broker.add(sr);
                    sr.setRb(id);
                } else {
                    broker.edit(sr);
                }
            }
        }
        pr.setStavke(stavke);
    }

    public PotvrdaRezervacije getPr() {
        return pr;
    }
    
}
