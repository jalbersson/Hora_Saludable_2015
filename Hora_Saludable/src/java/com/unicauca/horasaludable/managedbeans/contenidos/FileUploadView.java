/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.managedbeans.contenidos;
 
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
 
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author Yuri
 */
@ManagedBean
public class FileUploadView 
{
     public void handleFileUpload(FileUploadEvent event) {
        FacesMessage message = new FacesMessage("Se a cargado exitosamnete el archivo ", event.getFile().getFileName() + ".");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
}