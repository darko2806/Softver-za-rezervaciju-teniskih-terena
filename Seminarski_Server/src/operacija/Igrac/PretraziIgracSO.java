package operacija.Igrac;

import java.util.List;
import java.util.Map;
import model.Igrac;
import operacija.ApstraktnaGenerickaOperacija;

public class PretraziIgracSO extends ApstraktnaGenerickaOperacija {
    List<Igrac> lista;
    String uslov;
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        Map<String, Object> mapa = (Map<String, Object>) objekat;
        String imePrezime = (String) mapa.get("ime prezime");
        String kategorijaIgraca = (String) mapa.get("kategorija");
        
        String a = " JOIN kategorijaigraca ON igrac.idKategorijaIgraca = kategorijaigraca.idKategorijaIgraca WHERE 1=1";
        
        if (imePrezime != null && !imePrezime.isEmpty()) {
            a += " AND igrac.imePrezime LIKE '%" + imePrezime + "%'";
        }
        if (kategorijaIgraca != null && !kategorijaIgraca.isEmpty()) {
            a += " AND kategorijaigraca.nazivKategorije LIKE '%" + kategorijaIgraca + "%'";
        }

        uslov = a;
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        lista = broker.getAll(new Igrac(), uslov);
    }

    public List<Igrac> getLista() {
        return lista;
    }
}