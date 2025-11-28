package operacija.RecepcionarSmena;

import java.util.List;
import model.RecepcionarSmena;
import operacija.ApstraktnaGenerickaOperacija;

public class VratiRecepcionarSmenaSO extends ApstraktnaGenerickaOperacija {
    List<RecepcionarSmena> lista;

    @Override
    protected void preduslovi(Object objekat) throws Exception {

    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        lista = broker.getAll(new RecepcionarSmena(),
                " JOIN recepcionar ON recepcionarsmena.idRecepcionar = recepcionar.idRecepcionar "
              + "JOIN radnasmena ON recepcionarsmena.idRadnaSmena = radnasmena.idRadnaSmena");
    }

    public List<RecepcionarSmena> getLista() {
        return lista;
    }
}
