/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.managedbeans.medidas;

import com.unicauca.horasaludable.entities.Medida;
import com.unicauca.horasaludable.entities.Usuario;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Jhonny Taborda
 */
@ManagedBean(name = "consultaUsuarioMedidadController")
@RequestScoped
public class ConsultarUsuarioMedidaController {

    @EJB
    private com.unicauca.horasaludable.jpacontrollers.MedidaFacade ejbMedida;
    @EJB
    private com.unicauca.horasaludable.jpacontrollers.UsuarioFacade ejbUsuario;

    /**
     * Creates a new instance of ConsultarUsuarioMedidaController
     */
    private Usuario usuario;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    private List<Medida> listadoMedidas = null;

    public List<Medida> getListadoMedidas() {
        return ejbMedida.findAll();
    }

    public void setListadoMedidas(List<Medida> listadoMedidas) {
        this.listadoMedidas = listadoMedidas;
    }

    public String buscarMedias() {
        if (usuario == null) {
            listadoMedidas = null;
        } else {
            listadoMedidas = ejbMedida.findUsuario(usuario.getUsuidentificacion());
        }
        return "/meidas/BuscarEstudianteLibrosAjax";
    }

    public ConsultarUsuarioMedidaController() {
    }

}
