/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.managedbeans.contenidos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

/**
 *
 * @author Yuri
 */
@ManagedBean
@ViewScoped
public class verEditarHorario implements Serializable {

    private Horario verH;
    private int id;

    @PostConstruct
    public void init() {
        verH = new Horario();
        
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();

        id = Integer.parseInt((params.get("id")));
        
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
                    
                    if(hor.getIdH() == id)
                        this.verH = hor;
                }
                fr.close();
            } catch (IOException ex) {
                Logger.getLogger(horarioController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(horarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    } 
    
    public Horario getVerH() {
        return verH;
    }

    public void setVerH(Horario verH) {
        this.verH = verH;
    }

}
