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
import java.util.Map;
import java.util.Objects;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Angela
 */
@ManagedBean
@SessionScoped
public class eventoController {

     @EJB
    EventoFacade ejbEvento;
    
    Evento evento;
    Evento detallesEvento;
    Evento editarEvento;
    private List<Evento> eventos = new ArrayList();
    private List<Evento> ultimos = new ArrayList();
    Long idE=null;
    java.util.Date evefevento;
    
    public eventoController() {
       evento = new Evento() ;
    }
    
    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public java.util.Date getEvefevento() {
        return evefevento;
    }

    public void setEvefevento(java.util.Date evefevento) {
        this.evefevento = evefevento;
    }
    
    public List<Evento> getEventos() {
        this.eventos = this.ejbEvento.buscarEventos();
        return eventos;
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }
    
    public List<Evento> getUltimos() {
        try
        {
            this.ultimos = this.ejbEvento.ultimosEventos();
        }
        catch(Exception e)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "msgs", "Error: No se puede conectar con la base de datos !!!"));
        }
        return ultimos;
    }

    public void setUltimos(List<Evento> ultimos) {
        this.ultimos = ultimos;
    }
    
    
    public Evento getDetallesEvento() {
        return detallesEvento;
    }

    public void setDetallesEvento(Evento detallesEvento) {
        this.detallesEvento = detallesEvento;
    }
    
    public Long getIdE() {
        return idE;
    }

    public void setIdE(Long idE) {
        this.idE = idE;
    }
    
    public Evento getEditarEvento() {
        return editarEvento;
    }

    public void setEditarEvento(Evento editarEvento) {
        this.editarEvento = editarEvento;
    }
    
    public Date convertToJavaDate(java.util.Date date)
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
            this.evento.setEvefechapublicacion(convertToJavaDate(fechaPublicado));
            this.evento.setEvefechaevento(convertToJavaDate(evefevento));
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
    
    public String detalleEvento(Long id)
    {
        idE=id;
        return "detalleEvento";
    }
    
      
    public Evento eventoDetallado()
    {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
	idE = Long.parseLong(params.get("eventoId"));
        detallesEvento = new Evento();
        for(int i=0;i<eventos.size();i++)
        {
            if(eventos.get(i).getEveid().equals(idE))
            {
                detallesEvento=eventos.get(i);
            }
        }
            return detallesEvento;
    }
    
    public String eliminarEvento(Evento e)
    {
        try
        {
            this.ejbEvento.remove(e);
        }
        catch(Exception ex)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Error: El evento no fue agregado!"));
            return "editarEvento";
        }

        return "principal";  
    }
    
    public String mostrarEventoEditar(Evento e)
    {
        editarEvento = new Evento();
        editarEvento = e;
        return "editarEventoEspecifico";
    }
    
    public String editarEvento()
    {
        try
        {
            this.editarEvento.setEvetitulo("Editado");
            this.editarEvento.setEvelugar("Editado");
            java.util.Date  fechaPublicado = new java.util.Date();
            this.editarEvento.setEvefechapublicacion(convertToJavaDate(fechaPublicado));
            this.editarEvento.setEvefechaevento(convertToJavaDate(evefevento));
            this.editarEvento.setEvecontenido("url_contenido");
            this.editarEvento.setEveimagen("url_imagen");
            this.ejbEvento.edit(this.editarEvento);
            
        }
        catch(Exception e)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Error: El evento no fue agregado!"));
            return "quienesSomos";
        }

        return "principal";
    }
    
    
}
