package operacija.PotvrdaRezervacije;

import java.util.ArrayList;
import java.util.List;
import model.PotvrdaRezervacije;
import model.StavkaRezervacije;
import operacija.ApstraktnaGenerickaOperacija;

public class VratiPotvrduRezervacijeSO extends ApstraktnaGenerickaOperacija {

    private List<PotvrdaRezervacije> lista = new ArrayList<>();

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        // Nema preduslova
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {

        // ✅ Učitaj potvrde sa JOIN-ovima da se popune recepcionar i igrač
        String uslovPotvrda
                = " JOIN recepcionar ON recepcionar.idRecepcionar = potvrdarezervacije.idRecepcionar "
                + " JOIN igrac ON igrac.idIgrac = potvrdarezervacije.idIgrac "
                + " JOIN kategorijaigraca ON igrac.idKategorijaIgraca = kategorijaigraca.idKategorijaIgraca ";

        lista = broker.getAll(new PotvrdaRezervacije(), uslovPotvrda);

        // ✅ Za svaku potvrdu pronađi pripadajuće stavke
        for (PotvrdaRezervacije potvrda : lista) {

            String upitStavka
                    = " JOIN potvrdarezervacije ON stavkarezervacije.idRezervacija = potvrdarezervacije.idRezervacije "
                    + " JOIN tipterena ON tipterena.idTipTerena = stavkarezervacije.idTipTerena "
                    + " JOIN recepcionar ON recepcionar.idRecepcionar = potvrdarezervacije.idRecepcionar "
                    + " JOIN igrac ON igrac.idIgrac = potvrdarezervacije.idIgrac "
                    + " JOIN kategorijaigraca ON igrac.idKategorijaIgraca = kategorijaigraca.idKategorijaIgraca "
                    + " WHERE stavkarezervacije.idRezervacija = " + potvrda.getIdRezervacije();

            List<StavkaRezervacije> stavke = broker.getAll(new StavkaRezervacije(), upitStavka);
            potvrda.setStavke(stavke);
        }
    }

    public List<PotvrdaRezervacije> getLista() {
        return lista;
    }
}
