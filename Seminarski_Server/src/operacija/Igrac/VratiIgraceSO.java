package operacija.Igrac;

import java.util.List;
import model.Igrac;
import operacija.ApstraktnaGenerickaOperacija;

public class VratiIgraceSO extends ApstraktnaGenerickaOperacija {
    List<Igrac> igraci;
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        igraci = broker.getAll(new Igrac(), " JOIN kategorijaigraca ON igrac.idKategorijaIgraca = kategorijaigraca.idKategorijaIgraca");
    }

    public List<Igrac> getIgraci() {
        return igraci;
    }
}