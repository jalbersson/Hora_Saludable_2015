/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.managedbeans.contenidos;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

/**
 *
 * @author Yuri
 */
@ManagedBean
public class ImagesView {

    private List<String> images;

    String dir;
    File f;

    @PostConstruct
    public void init() {

        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String realPath = (String) servletContext.getRealPath("/"); // Sustituye "/" por el directorio ej: "/upload"
        dir = realPath + "\\resources\\img\\imagenSalud";

        f = new File(dir);
        images = new ArrayList<>();

        if (f.exists()) {
            File[] ficheros = f.listFiles();
            for (int i = 0; i < ficheros.length; i++) {
                images.add(ficheros[i].getName());                
            }
        }

        /*images = new ArrayList<String>();
        for (int i = 1; i <= 5; i++) {
            images.add("salud" + i + ".jpg");
        }*/
    }

    public List<String> getImages() {
        return images;
    }
}
