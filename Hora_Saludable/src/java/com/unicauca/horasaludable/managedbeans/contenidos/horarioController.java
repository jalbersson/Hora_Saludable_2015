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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

/**
 *
 * @author Yuri
 */
@ManagedBean
@ViewScoped
public class horarioController implements Serializable {

    private ArrayList<Horario> h;

    //metodos   
    @PostConstruct
    public void init() {
        h = new ArrayList<>();
        
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String realPath = (String) servletContext.getRealPath("/"); // Sustituye "/" por el directorio ej: "/upload"
        String url = realPath + "\\resources\\docs\\horarios.txt";

        File fl = new File(url);
        FileReader fr;
        try {
            fr = new FileReader(fl);
            BufferedReader br = new BufferedReader(fr);
            String linea;
            Horario hor;
            String[] array;
            try {
                while ((linea = br.readLine()) != null) {
                    hor = new Horario();
                    array = linea.split(",");
                    
                    hor.setIdH(Integer.parseInt(array[0]));
                    hor.setNombreH(array[1]);
                    hor.setRutaH(array[2]); 
                    
                    h.add(hor);
                }
                fr.close();
            } catch (IOException ex) {
                Logger.getLogger(horarioController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(horarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Horario> getH() {
        return h;
    }

    public void setH(ArrayList<Horario> h) {
        this.h = h;
    }

}
