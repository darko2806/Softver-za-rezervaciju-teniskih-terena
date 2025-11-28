/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package komunikacija;

import java.io.Serializable;

/**
 *
 * @author dare2
 */
public enum Operacija implements Serializable {
    LOGIN, GASENJE_SERVERA, LOGOUT, VRATI_KATEGORIJE_IGRACA, VRATI_IGRACE, KREIRAJ_IGRACA, 
    OBRISI_IGRACA, PRETRAZI_IGRACA, ZAPAMTI_IGRACA, VRATI_POTVRDE_REZERVACIJE, 
    PRETRAZI_POTVRDE_REZERVACIJE, VRATI_RECEPCIONARE, VRATI_TIPOVE_TERENA, KREIRAJ_POTVRDU_REZERVACIJE, OBRISI_POTVRDU_REZERVACIJE, 
    ZAPAMTI_POTVRDU_REZERVACIJE, VRATI_RECEPCIONARSMENA, VRATI_RADNASMENA, UBACI_RECEPCIONARSMENU;
}
