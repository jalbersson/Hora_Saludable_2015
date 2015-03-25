/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.noticias;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import com.unicauca.noticias.Noticias;
import com.unicauca.noticias.ServicioNoticias;
import java.util.ArrayList;
 

/**
 *
 * @author Yamid
 */
@ManagedBean
@ViewScoped
public class DataListView implements Serializable {
    private List<Noticias> lst;
    private Noticias selectedNoticia;
    
    @ManagedProperty("#{servicioNoticias}")
    private ServicioNoticias service;
     
    @PostConstruct
    public void init() {
        lst = new ArrayList<Noticias>();
        lst = service.createNoticias(5);
    }

    public List<Noticias> getLst() {
        return lst;
    }

    public void setLst(List<Noticias> lst) {
        this.lst = lst;
    }

    public Noticias getSelectedNoticia() {
        return selectedNoticia;
    }

    public void setSelectedNoticia(Noticias selectedNoticia) {
        this.selectedNoticia = selectedNoticia;
    }

    public ServicioNoticias getService() {
        return service;
    }

    public void setService(ServicioNoticias service) {
        this.service = service;
    }
 
   

     public void setSelectedNoticias(Noticias selectedNoticia) {
        this.selectedNoticia = selectedNoticia;
    }

}