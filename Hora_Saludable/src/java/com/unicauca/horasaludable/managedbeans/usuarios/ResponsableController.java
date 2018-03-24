/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.managedbeans.usuarios;

import com.unicauca.horasaludable.entities.Responsable;
import com.unicauca.horasaludable.jpacontrollers.ResponsableFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Wilson Carvajal
 */
@ManagedBean
@RequestScoped
public class ResponsableController implements Serializable 
{
    @EJB
    private ResponsableFacade responsableEJB;
    private Responsable responsable;

    public Responsable getResponsable() {
        return responsable;
    }

    public void setResponsable(Responsable responsable) {
        this.responsable = responsable;
    }
    
    public ResponsableController() 
    {
        
    }    
    @PostConstruct
    private void init()
    {
        this.inicializarNombreResponsable();
    } 
    
    private void inicializarNombreResponsable()
    {
        List<Responsable> listaResponsable = responsableEJB.findAll();
        responsable= new Responsable();
        if(listaResponsable != null && listaResponsable.size()>0)
        {
            responsable = listaResponsable.get(0);
        }
        
    }
}
