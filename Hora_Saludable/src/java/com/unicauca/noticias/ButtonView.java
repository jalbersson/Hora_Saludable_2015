/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.noticias;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.NoneScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
/**
 *
 * @author Yamid
 */
@ManagedBean
@NoneScoped
public class ButtonView {

    /**
     * Creates a new instance of ButtonView
     */
    public ButtonView() {
    }
        public void buttonAction(ActionEvent actionEvent) {
        addMessage("Noticia Nueva ha sido Guardada!!");
    }
     
    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}