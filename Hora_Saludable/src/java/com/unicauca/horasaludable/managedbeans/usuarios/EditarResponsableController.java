/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.managedbeans.usuarios;

import com.unicauca.horasaludable.entities.Responsable;
import com.unicauca.horasaludable.jpacontrollers.ResponsableFacade;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author aranda
 */
@ManagedBean
@SessionScoped
public class EditarResponsableController 
{
   @EJB
   private ResponsableFacade responsableEJB;
   private Responsable responsable;
   private String nombreResponsable;
   
   
   public EditarResponsableController() 
   {

   }  
   public Responsable getResponsable() {
       return responsable;
   }

   public void setResponsable(Responsable responsable) {
       this.responsable = responsable;
   }
   
   public String getNombreResponsable() {
       return nombreResponsable;
   }

   public void setNombreResponsable(String nombreResponsable) {
       this.nombreResponsable = nombreResponsable;
   }
   
   public void goEdit()
   {
       List<Responsable> listaResponsable = responsableEJB.findAll();
       responsable= new Responsable();
       if(listaResponsable != null && listaResponsable.size()>0)
       {
           responsable = listaResponsable.get(0);
           nombreResponsable = responsable.getRespnombre();
       } 
       try {
           FacesContext.getCurrentInstance().getExternalContext().redirect("/Hora_Saludable/faces/administrador/usuarios/editarResponsable.xhtml");
       } catch (IOException ex) {
           
       }
   }
   
   public void guardar()
   {
       responsable.setRespnombre(nombreResponsable);
       responsableEJB.edit(responsable);
       try {
           FacesContext.getCurrentInstance().getExternalContext().redirect("/Hora_Saludable/faces/administrador//contenidos/principal.xhtml");
       } catch (IOException ex) {
           
       }
   }
}
