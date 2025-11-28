package operacija.PotvrdaRezervacije;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import model.Igrac;
import model.PotvrdaRezervacije;
import model.Recepcionar;
import model.StavkaRezervacije;
import model.TipTerena;
import operacija.ApstraktnaGenerickaOperacija;

public class PretraziPotvrduRezervacijeSO extends ApstraktnaGenerickaOperacija {

    List<PotvrdaRezervacije> lista = new ArrayList<>();
    String uslovPotvrdaRez
            = " JOIN recepcionar ON recepcionar.idRecepcionar = potvrdarezervacije.idRecepcionar "
            + "JOIN igrac ON igrac.idIgrac = potvrdarezervacije.idIgrac "
            + "JOIN kategorijaigraca ON igrac.idKategorijaIgraca = kategorijaigraca.idKategorijaIgraca";
    List<StavkaRezervacije> stavke = new ArrayList<>();
    String uslovStavka
            = " JOIN potvrdarezervacije ON stavkarezervacije.idRezervacija = potvrdarezervacije.idRezervacije "
            + "JOIN tipterena ON tipterena.idTipTerena = stavkarezervacije.idTipTerena "
            + uslovPotvrdaRez;

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        Map<String, Object> mapa = (Map<String, Object>) objekat;
        Recepcionar recepcionar = (Recepcionar) mapa.get("recepcionar");
        Igrac igrac = (Igrac) mapa.get("igrac");
        TipTerena tipTerena = (TipTerena) mapa.get("TipTerena");
        Boolean obradjen = (Boolean) mapa.get("Obradjen");
        String a = " WHERE 1=1 ";
        if (recepcionar != null) {
            a += " AND potvrdarezervacije.idRecepcionar = " + recepcionar.getIdRecepcionar();
        }
        if (igrac != null) {
            a += " AND potvrdarezervacije.idIgrac = " + igrac.getIdIgrac();
        }
        if (tipTerena != null) {
            a += " AND tipterena.opis LIKE '%" + tipTerena.getOpis() + "%'";
        }
        if (obradjen != null) {
            a += " AND potvrdarezervacije.obradjen = " + (obradjen ? "1" : "0");
        }

        uslovStavka += a;
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {

        List<StavkaRezervacije> listaStavka = broker.getAll(new StavkaRezervacije(), uslovStavka);
        for (StavkaRezervacije stavka : listaStavka) {

            if (vecPostojiUListi(lista, stavka.getPotvrdaRezervacije().getIdRezervacije(), stavka) == false) {
                stavka.getPotvrdaRezervacije().getStavke().add(stavka);
                lista.add(stavka.getPotvrdaRezervacije());
            }

        }

    }

    public static boolean vecPostojiUListi(List<PotvrdaRezervacije> lista, int id, StavkaRezervacije stavka) {
        for (PotvrdaRezervacije pr : lista) {
            if (pr.getIdRezervacije() == id) {
                pr.getStavke().add(stavka);
                return true;
            }
        }
        return false;
    }

    public List<PotvrdaRezervacije> getLista() {
        return lista;
    }
}
