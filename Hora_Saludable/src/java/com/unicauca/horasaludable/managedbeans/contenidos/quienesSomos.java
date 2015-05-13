/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.managedbeans.contenidos;

import static com.sun.javafx.logging.PulseLogger.addMessage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.Buffer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Leidi
 */
@ManagedBean
@RequestScoped
public class quienesSomos {

    String mision = "";
    String vision = "";
    String objetivo = "";
    String equipo = "";

    /**
     * Creates a new instance of quienesSomos
     */
    public quienesSomos() {
    }

    public String getMision() {
       mision= devMision();
        return mision;
    }

    public void setMision(String mision) {
        
        this.mision = mision;
    }

    public String getVision() {
       vision=devVision();
        return vision;
    }

    public void setVision(String vision) {
        this.vision = vision;
    }

    public String getObjetivo() {
        objetivo=devObjetivo();
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        
        this.objetivo = objetivo;
    }

    public String getEquipo() {
        equipo=devEquipo();
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public String devMision() {
        File fl = new File("C:\\Users\\Leidi\\Documents\\NetBeansProjects\\Hora_Saludable_2015\\Hora_Saludable\\web\\resources\\docs\\mision.txt");
        FileReader fr;
        this.mision="";
        try {
            fr = new FileReader(fl);
            BufferedReader br = new BufferedReader(fr);
            String linea;
            try {
                while ((linea = br.readLine()) != null) {
                    this.mision = this.mision + linea;

                }
                fr.close();
            } catch (IOException ex) {
                Logger.getLogger(quienesSomos.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(quienesSomos.class.getName()).log(Level.SEVERE, null, ex);
        }

        return mision;
    }

    public String devObjetivo() {
        File fl = new File("C:\\Users\\Leidi\\Documents\\NetBeansProjects\\Hora_Saludable_2015\\Hora_Saludable\\web\\resources\\docs\\objetivo.txt");
        FileReader fr;
        this.objetivo="";
        try {
            fr = new FileReader(fl);
            BufferedReader br = new BufferedReader(fr);
            String linea;
            try {
                while ((linea = br.readLine()) != null) {
                    this.objetivo = this.objetivo + linea;

                }
                fr.close();
            } catch (IOException ex) {
                Logger.getLogger(quienesSomos.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(quienesSomos.class.getName()).log(Level.SEVERE, null, ex);
        }

        return objetivo;
    }

    public String devVision() {
        File fl = new File("C:\\Users\\Leidi\\Documents\\NetBeansProjects\\Hora_Saludable_2015\\Hora_Saludable\\web\\resources\\docs\\vision.txt");
        FileReader fr;
        this.vision="";
        try {
            fr = new FileReader(fl);
            BufferedReader br = new BufferedReader(fr);
            String linea;
            try {
                while ((linea = br.readLine()) != null) {
                    this.vision = this.vision + linea;

                }
                fr.close();
            } catch (IOException ex) {
                Logger.getLogger(quienesSomos.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(quienesSomos.class.getName()).log(Level.SEVERE, null, ex);
        }

        return vision;
    }
    
    public String devEquipo() {
        File fl = new File("C:\\Users\\Leidi\\Documents\\NetBeansProjects\\Hora_Saludable_2015\\Hora_Saludable\\web\\resources\\docs\\equipo.txt");
        FileReader fr;
        try {
            fr = new FileReader(fl);
            this.equipo="";
            BufferedReader br = new BufferedReader(fr);
            String linea;
            try {
                while ((linea = br.readLine()) != null) {
                    this.equipo = this.equipo + linea;

                }
                fr.close();
            } catch (IOException ex) {
                Logger.getLogger(quienesSomos.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(quienesSomos.class.getName()).log(Level.SEVERE, null, ex);
        }

        return equipo;
    }
    
    public String crearMision() {
        File fl = new File("C:\\Users\\Leidi\\Documents\\NetBeansProjects\\Hora_Saludable_2015\\Hora_Saludable\\web\\resources\\docs\\mision.txt");
        FileWriter fw;
        try {
            fw = new FileWriter(fl);
            BufferedWriter br = new BufferedWriter(fw);
            fw.write(this.mision);
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(quienesSomos.class.getName()).log(Level.SEVERE, null, ex);
            return "principal";
        }

        return "quienesSomosPrincipal";
    }
     
    public String crearVision() {
        File fl = new File("C:\\Users\\Leidi\\Documents\\NetBeansProjects\\Hora_Saludable_2015\\Hora_Saludable\\web\\resources\\docs\\vision.txt");
        FileWriter fw;
        try {
            fw = new FileWriter(fl);
            BufferedWriter br = new BufferedWriter(fw);
            fw.write(this.vision);
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(quienesSomos.class.getName()).log(Level.SEVERE, null, ex);
            return "principal";
        }

        return "quienesSomosPrincipal";
    }
    
    public String crearResponsables() {
        File fl = new File("C:\\Users\\Leidi\\Documents\\NetBeansProjects\\Hora_Saludable_2015\\Hora_Saludable\\web\\resources\\docs\\equipo.txt");
        FileWriter fw;
        try {
            fw = new FileWriter(fl);
            BufferedWriter br = new BufferedWriter(fw);
            fw.write(this.equipo);
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(quienesSomos.class.getName()).log(Level.SEVERE, null, ex);
            return "principal";
        }

        return "quienesSomosPrincipal";
    }
    
    
}
