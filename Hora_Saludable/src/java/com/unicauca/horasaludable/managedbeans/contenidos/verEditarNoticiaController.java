/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.managedbeans.contenidos;

import com.unicauca.horasaludable.entities.Evento;
import com.unicauca.horasaludable.entities.Noticia;
import com.unicauca.horasaludable.jpacontrollers.EventoFacade;
import com.unicauca.horasaludable.jpacontrollers.NoticiaFacade;
import java.io.Serializable;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.transaction.Transactional;

/**
 *
 * @author Yamid
 */
@ManagedBean
@ViewScoped
@Transactional
public class verEditarNoticiaController implements Serializable {

    @EJB
    NoticiaFacade ejbNoticia;

    Noticia noticia;
    long id;

    @PostConstruct
    public void cargarNoticia() {

        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();

        id = Long.parseLong(params.get("id"));
        noticia = ejbNoticia.mostrarNoticia(id);
    }

    public Noticia getNoticia() {
        return noticia;
    }

    public void setNoticia(Noticia noticia) {
        this.noticia = noticia;
    }

    public String actualizarNoticia() {

        ejbNoticia.edit(noticia);
        
        return "listarNoticias";
    }

    /*<p:commandButton value="Guardar" actionListener="#{eventoVerEditarController.actualizarEvento()}" update="message">
     <p:confirm header="Confirmacion" message="Evento editado con exito!!!" icon="ui-icon-alert" />                                        
     </p:commandButton>
     <p:confirmDialog global="true" showEffect="fade" hideEffect="explode">
     <p:button value="Aceptar" outcome="editarEvento" style="float:right; right: 25%"/>                                  
     </p:confirmDialog>*/
}
