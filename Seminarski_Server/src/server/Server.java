/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import komunikacija.Operacija;

/**
 *
 * @author dare2
 */
public class Server extends Thread {
    ServerSocket serverskiSoket;
    Socket s;
    boolean kraj = false;
    int port;

    public Server(int port) {
        this.port=port;
    }

    @Override
    public void run() {
        try {
            serverskiSoket = new ServerSocket(9000);
            System.out.println("Server je pokrenut na port: " + serverskiSoket.getLocalPort());
            while (!kraj) {
                s = serverskiSoket.accept();
                System.out.println("Klijent se povezao na server.");

                ObradaKlijentskihZahteva okz = new ObradaKlijentskihZahteva(s,this);
                controller.Controller.getInstance().getKlijenti().add(okz);
                okz.start();
            }
        } catch (SocketException se) {
            System.out.println("Server je iskljucen!");
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            zaustaviServer();
        }

    }


    public void zaustaviServer() {
        kraj = true;
        if (serverskiSoket != null && !serverskiSoket.isClosed()) {
            try {
                serverskiSoket.close();
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void ugasiSveKlijente() {
        controller.Controller.getInstance().osveziRecepcionare();
        List<ObradaKlijentskihZahteva> sviKlijenti = controller.Controller.getInstance().getKlijenti();
        for (ObradaKlijentskihZahteva okz : new ArrayList<>(sviKlijenti)) {
            okz.obavestiKlijenta(Operacija.GASENJE_SERVERA, null);
            
            controller.Controller.getInstance().getKlijenti().remove(okz);
        }
    }
}
