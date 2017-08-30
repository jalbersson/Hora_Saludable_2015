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
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Wilson Carvajal
 */
@ManagedBean
@SessionScoped
public class ResponsableController implements Serializable 
{
    @EJB
    private ResponsableFacade responsableEJB;
    private String nombreResponsable;

    public String getNombreResponsable() {
        return nombreResponsable;
    }

    public void setNombreResponsable(String nombreResponsable) {
        this.nombreResponsable = nombreResponsable;
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
        nombreResponsable="";
        if(listaResponsable != null && listaResponsable.size()>0)
        {
            Responsable responsable = listaResponsable.get(0);
            nombreResponsable = responsable.getRespnombre();
        }
        
    }
}
