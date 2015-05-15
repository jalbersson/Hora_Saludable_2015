/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.managedbeans.contenidos;

import com.unicauca.horasaludable.entities.Evento;
import com.unicauca.horasaludable.jpacontrollers.EventoFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Yuri
 */
@ManagedBean
@ViewScoped
public class eventoVerEditarController implements Serializable {

    @EJB
    EventoFacade ejbEvento;

    Evento evento;
    long id;
    
    @PostConstruct
    public void cargarEvento() {
        id = 6;
        evento = ejbEvento.mostrarEvento(id);
    }

    public eventoVerEditarController() {
        
    }
    
    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }
    
    

    public String actualizarEvento() {
        
        ejbEvento.edit(evento);
        System.out.println(evento.getEvelugar());
        return "editarEvento";
    }

    
    /*<p:commandButton value="Guardar" actionListener="#{eventoVerEditarController.actualizarEvento()}" update="message">
                                        <p:confirm header="Confirmacion" message="Evento editado con exito!!!" icon="ui-icon-alert" />                                        
                                    </p:commandButton>
                                    <p:confirmDialog global="true" showEffect="fade" hideEffect="explode">
                                        <p:button value="Aceptar" outcome="editarEvento" style="float:right; right: 25%"/>                                  
                                    </p:confirmDialog>*/
}
