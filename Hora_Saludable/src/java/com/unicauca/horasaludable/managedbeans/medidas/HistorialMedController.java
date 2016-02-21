/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.managedbeans.medidas;

import com.unicauca.horasaludable.entities.Medida;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author pcblanco
 */
@ManagedBean
@ViewScoped
public class HistorialMedController {

      @EJB
    private com.unicauca.horasaludable.jpacontrollers.MedidaFacade ejbMedida;
 
    private Medida medhist1; 
    private Medida medhist2;  
    private List<Medida> medidas;
 
    public HistorialMedController() {
      
    }
    
    @PostConstruct
    public void init()
        {
      int idusu = 20141105 ; //para probar
      int idmed = 5 ; //para probar
      FacesContext context = FacesContext.getCurrentInstance();
      MostrarUsuarioTestController s = (MostrarUsuarioTestController) context.getApplication().evaluateExpressionGet(context, "#{mostrarUsuarioTestController}", MostrarUsuarioTestController.class);
      medidas = ejbMedida.buscarporUsuid(s.getUsuario().getUsuid().intValue());
      
      if(medidas.size()>0)medhist1 = medidas.get(0);
      if(medidas.size()>1)medhist2 = medidas.get(1);
        }

    public List<Medida> getMedidas() {
           return medidas;
    }

    public void setMedidas(List<Medida> medidas) {
        this.medidas = medidas;
    }

    public Medida getMedhist1() {
        return medhist1;
    }

    public void setMedhist1(Medida medhist1) {
        this.medhist1 = medhist1;
    }

    public Medida getMedhist2() {
        return medhist2;
    }

    public void setMedhist2(Medida medhist2) {
        this.medhist2 = medhist2;
    }
    
    public Medida buscarmed(Long idmed)
      {
        for(Medida m : medidas)
            {
             if(m.getMedid().equals(idmed))return m;
            }
        return new Medida();
      }      
    
}
