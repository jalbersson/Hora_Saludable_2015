/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.managedbeans.contenidos;


import com.unicauca.horasaludable.entities.Evento;
import com.unicauca.horasaludable.jpacontrollers.EventoFacade;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Angela
 */
@ManagedBean
@RequestScoped
public class eventoController {

     @EJB
    EventoFacade ejbEvento;
    
    Evento evento;
    private List<Evento> eventos;
    String evefpublicacion;
    String eveTitulo="Guerrero";

    public String getEveTitulo() {
        return eveTitulo;
    }

    public void setEveTitulo(String eveTitulo) {
        this.eveTitulo = eveTitulo;
    }
    java.util.Date evefevento;
    
    public eventoController() {
       evento = new Evento() ;
       System.out.println("Hola mundo");
    }
    
    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public String getEvefpublicacion() {
        return evefpublicacion;
    }

    public void setEvefpublicacion(String evefpublicacion) {
        this.evefpublicacion = evefpublicacion;
    }

    public java.util.Date getEvefevento() {
        return evefevento;
    }

    public void setEvefevento(java.util.Date evefevento) {
        this.evefevento = evefevento;
    }
    
    public List<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }
    
    
    /*public String agregarEvento(){
        return "agregarEvento";
    }*/
    
public static Date convertStringToDate(java.util.Date date)
    {
      java.sql.Date sqlDate = null;
        
      try
      {
        sqlDate = new java.sql.Date(date.getTime()); 
      }
      catch(Exception e)
      {   
      }    
      return sqlDate;
    }
    
    
    
    public String guardarEvento(){

        try
        {
            java.util.Date  fechaPublicado = new java.util.Date();
            this.evento.setEvefechapublicacion(convertStringToDate(fechaPublicado));
            this.evento.setEvefechaevento(convertStringToDate(evefevento));
            this.evento.setEvecontenido("url_contenido");
            this.evento.setEveimagen("url_imagen");
            this.ejbEvento.create(this.evento);
            
        }
        catch(Exception e)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Error: El evento no fue agregado!"));
            return "editarEvento";
        }

        return "principal";
    }
    
    public String cargarEventos()
    {
        
        
       
        
        return "eliminarEvento";
        
        /*try
        {
           eventos =this.ejbEvento.buscarEventos(); 
           
        }
        catch(Exception e)
        {
            return "editarEvento";
        }
        return "eliminarEvento";*/
    }
    
}
