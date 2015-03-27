/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.contenidos.eventos;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

 
@ManagedBean(name="dtPaginatorView")
@ViewScoped
public class PaginatorView implements Serializable {
     
    private List<Evento> eventos;
     
    @ManagedProperty("#{eventoService}")
    private EventoService service;
 
    @PostConstruct
    public void init() {
        eventos = service.createEventos(50);
    }
     
    public List<Evento> getEventos() {
        return eventos;
    }
 
    public void setService(EventoService service) {
        this.service = service;
    }
}


