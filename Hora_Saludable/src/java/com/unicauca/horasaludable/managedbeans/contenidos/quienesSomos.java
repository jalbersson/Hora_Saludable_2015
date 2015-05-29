/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.managedbeans.contenidos;

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
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletContext;

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
        String path;
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        path = (String) servletContext.getRealPath("/"); // Sustituye "/" por el directorio ej: "/upload"
        String OS =System.getProperty("os.name").toLowerCase();
        if (OS.contains("nux") || OS.contains("nux")) 
        {
            path =path +"/resources/docs/mision.txt";
        }else{
            path =path +"\\resources\\docs\\mision.txt";
        }
        File fl = new File(path);
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
        String path;
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        path = (String) servletContext.getRealPath("/"); // Sustituye "/" por el directorio ej: "/upload"
        String OS =System.getProperty("os.name").toLowerCase();
        if (OS.contains("nux") || OS.contains("nux")) 
        {
            path =path +"/resources/docs/objetivo.txt";
        }else{
            path =path +"\\resources\\docs\\objetivo.txt";
        }
        File fl = new File(path);
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
        String path;
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        path = (String) servletContext.getRealPath("/"); // Sustituye "/" por el directorio ej: "/upload"
        String OS =System.getProperty("os.name").toLowerCase();
        if (OS.contains("nux") || OS.contains("nux")) 
        {
            path =path +"/resources/docs/vision.txt";
        }else{
            path =path +"\\resources\\docs\\vision.txt";
        }
        File fl = new File(path);
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
        String path;
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        path = (String) servletContext.getRealPath("/"); // Sustituye "/" por el directorio ej: "/upload"
        String OS =System.getProperty("os.name").toLowerCase();
        if (OS.contains("nux") || OS.contains("nux")) 
        {
            path =path +"/resources/docs/equipo.txt";
        }else{
            path =path +"\\resources\\docs\\equipo.txt";
        }
        File fl = new File(path);
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
        String path;
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        path = (String) servletContext.getRealPath("/"); // Sustituye "/" por el directorio ej: "/upload"
        String OS =System.getProperty("os.name").toLowerCase();
        if (OS.contains("nux") || OS.contains("nux")) 
        {
            path =path +"/resources/docs/mision.txt";
        }else{
            path =path +"\\resources\\docs\\mision.txt";
        }
        File fl = new File(path);
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
        
        return "quienesSomos";
    }
     
    public String crearVision() {
        String path;
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        path = (String) servletContext.getRealPath("/"); // Sustituye "/" por el directorio ej: "/upload"
        String OS =System.getProperty("os.name").toLowerCase();
        if (OS.contains("nux") || OS.contains("nux")) 
        {
            path =path +"/resources/docs/vision.txt";
        }else{
            path =path +"\\resources\\docs\\vision.txt";
        }
        File fl = new File(path);
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

        return "quienesSomos";
    }
    
    public String crearResponsables() {
        String path;
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        path = (String) servletContext.getRealPath("/"); // Sustituye "/" por el directorio ej: "/upload"
        String OS =System.getProperty("os.name").toLowerCase();
        if (OS.contains("nux") || OS.contains("nux")) 
        {
            path =path +"/resources/docs/equipo.txt";
        }else{
            path =path +"\\resources\\docs\\equipo.txt";
        }
        File fl = new File(path);
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

        return "quienesSomos";
    }
    
    
}
