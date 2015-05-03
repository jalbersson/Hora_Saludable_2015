/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.managedbeans.medidas;

import com.unicauca.horasaludable.entities.Medida;
import com.unicauca.horasaludable.jpacontrollers.MedidaFacade;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Jhonny Taborda
 */
@ManagedBean
@ViewScoped
public class ConsultarPorApellidos implements Serializable
{

    /**
     * Creates a new instance of ConsultarPorApellidos
     */
    
    private MedidaFacade ejbMedida;
    private List<Medida> listaMedidas;

    public List<Medida> getListaMedidas() {
        return listaMedidas;
    }

    public void setListaMedidas(List<Medida> listaMedidas) {
        this.listaMedidas = listaMedidas;
    }

    public String getPorApellido() {
        return porApellido;
    }

    public void setPorApellido(String porApellido) {
        this.porApellido = porApellido;
    }
    private String porApellido;
    
       public void buscarporApellido()
    {
        this.listaMedidas= this.ejbMedida.buscarporApellido(this.porApellido);
    }
       
       
    public ConsultarPorApellidos() 
    {
        
    }
    
    
    
}
