/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package konfiguracija;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dare2
 */
public class Konfiguracija {
    private static Konfiguracija instance;
    private Properties konfiguracija;

    private Konfiguracija() {
        konfiguracija = new Properties();
        String naziv = "C:\\Users\\dare2\\Documents\\NetBeansProjects\\Seminarski_Server\\config\\config.properties";
        File fajl = new File(naziv);
        if (!fajl.exists()) {
            fajl.getParentFile().mkdirs();
            konfiguracija.setProperty("password", "");
            konfiguracija.setProperty("port", 0+"");
            konfiguracija.setProperty("url", "jdbc\\:mysql\\://localhost\\:3306/");
            konfiguracija.setProperty("username", "root");
            try {
                konfiguracija.store(new FileOutputStream(fajl), null);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Konfiguracija.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Konfiguracija.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                konfiguracija.load(new FileInputStream(fajl));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Konfiguracija.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Konfiguracija.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static Konfiguracija getInstance() {
        if (instance == null) {
            instance = new Konfiguracija();
        }
        return instance;
    }

    public void setProperty(String key, String value) {
        konfiguracija.setProperty(key, value);
    }

    public String getProperty(String key) {
        return konfiguracija.getProperty(key);
    }

    public void sacuvajIzmene() {
        try {
            konfiguracija.store(new FileOutputStream("C:\\Users\\dare2\\Documents\\NetBeansProjects\\Seminarski_Server\\config\\config.properties"), null);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Konfiguracija.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Konfiguracija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
