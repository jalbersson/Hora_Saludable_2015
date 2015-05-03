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
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Jhonny Taborda
 */
@ManagedBean(name = "consultaUsuarioMedidadController")
@ViewScoped
public class ConsultarUsuarioMedidaController implements Serializable {

    @EJB
    private MedidaFacade ejbMedida;
    private List<Medida> listaMedidas;
    private Long identificacionUsuario;   
    private String porApellido;

    public String getPorApellido() {
        return porApellido;
    }

    public void setPorApellido(String porApellido) {
        this.porApellido = porApellido;
    }

    
    public ConsultarUsuarioMedidaController() 
    {
    }
    
    public List<Medida> getListaMedidas() 
    {
        return listaMedidas;
    }

    public void setListaMedidas(List<Medida> listaMedidas) 
    {
        this.listaMedidas = listaMedidas;
    }
    
    public Long getIdentificacionUsuario() 
    {
        return identificacionUsuario;
    }

    public void setIdentificacionUsuario(Long identificacionUsuario) 
    {
        this.identificacionUsuario = identificacionUsuario;
    }
    
    public void buscarMedidasUsuario()
    {
        this.listaMedidas= this.ejbMedida.buscarMedidaUsuario(this.identificacionUsuario);
    }
 public void buscarporApellido()
    {
        this.listaMedidas= this.ejbMedida.buscarporApellido(this.porApellido);
    }
 
}
