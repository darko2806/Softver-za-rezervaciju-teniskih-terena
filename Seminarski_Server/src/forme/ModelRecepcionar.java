/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Recepcionar;

/**
 *
 * @author dare2
 */
class ModelRecepcionar extends AbstractTableModel {
    List<Recepcionar> lista;
    String[] kolone = {"ime i prezime", "korisnickoIme", "sifra", "datum rodjenja"};
    
    public ModelRecepcionar(List<Recepcionar> lista){
        this.lista = lista;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Recepcionar r = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return r.getImePrezime();
            case 1:
                return r.getKorisnickoIme();
            case 2:
                return r.getSifra();
            case 3:
                return r.getDatumRodjenja();
            default:
                return null;
        }
    }
    
}
