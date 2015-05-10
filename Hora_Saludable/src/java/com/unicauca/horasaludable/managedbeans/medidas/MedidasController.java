/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.managedbeans.medidas;

import static com.sun.javafx.logging.PulseLogger.addMessage;
import com.unicauca.horasaludable.entities.Medida;
import java.io.IOException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.context.RequestContext;

/**
 *
 * @author pcblanco
 */
@ManagedBean
@ViewScoped
public class MedidasController {

      
    @EJB
    private com.unicauca.horasaludable.jpacontrollers.MedidaFacade ejbMedida;
    private Medida medicionactual;  
    private ServicioCalculoMedidas servmed;
        
    public MedidasController() {
      
    }
    
    @PostConstruct
    public void init()
        {
      int idusu = 20141224 ; //para probar
      int idmed = 1 ; //para probar 
      
      medicionactual =  ejbMedida.buscarporMedId(idmed).get(0);
      servmed = new ServicioCalculoMedidas(medicionactual, 1, 155, 55);
        }

    public Medida getMedicionactual() {
        return medicionactual;
    }

    public void setMedicionactual(Medida medicionactual) {
        this.medicionactual = medicionactual;
    }
    
    public ServicioCalculoMedidas getServmed() {
        return servmed;
    }

    public void setServmed(ServicioCalculoMedidas servmed) {
        this.servmed = servmed;
    }

   public void redireccionar(Medida test) throws IOException 
   {
     
       this.medicionactual=test;
       
         RequestContext requestContext = RequestContext.getCurrentInstance();
 
         requestContext.update("formularioFoto");
        requestContext.update("formularioEditarFoto");
        requestContext.update("formularioDatosPersonales");
        requestContext.update("formularioDatosAcademia");
        requestContext.execute("PF('imprimir').show()");  
       //FacesContext.getCurrentInstance().getExternalContext().redirect("/Hora_Saludable/faces/administrador/medidas/VistaImprimirMedida.xhtml");
   }
    
      public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
        
    public void guardarmed()
     {
       FacesContext context = FacesContext.getCurrentInstance();      
       try
           {
             ejbMedida.edit(medicionactual);
             context.addMessage("msggestion", new FacesMessage(FacesMessage.SEVERITY_INFO, "Completado", "Operacion realizada con exito"));
              ExternalContext extcontext = context.getExternalContext();
              extcontext.getFlash().setKeepMessages(true); 
              extcontext.redirect("GestionTest.xhtml");
           }
        catch (Exception e) {
                context.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Ocurrio algun error al intentar  efectuar la operacion"));
            }
    }       
    
}
