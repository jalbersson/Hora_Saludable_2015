/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.managedbeans.medidas;

import com.unicauca.horasaludable.entities.Usuario;
import com.unicauca.horasaludable.jpacontrollers.UsuarioFacade;
import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.faces.event.ValueChangeEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import com.unicauca.horasaludable.utilidades.Utilidades;

/**
 *
 * @author Jhonny Taborda
 */
@ManagedBean
@SessionScoped
public class ListasUsuariosMedidasController implements Serializable {

    @EJB
    private UsuarioFacade usuarioEJB;

    private String identificacion;

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }
    private String nombreUsuario;
    private boolean habilitarEstudiantes;
    private boolean habilitarFuncionarios;
    private boolean habilitarFamiliares;
    private boolean habilitarTablaUsuarios;

    private List<Usuario> listaUsuarios;

    public ListasUsuariosMedidasController() {
        this.habilitarEstudiantes = false;
        this.habilitarFamiliares = false;
        this.habilitarFuncionarios = false;
        this.habilitarTablaUsuarios = false;
    }

   /**
     * Recupera la foto de la bd y la devuelve como un StreamedContent
     *
     * @return flujo de la imagen
     */
    public StreamedContent getImagenFlujo() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            return new DefaultStreamedContent();
        } else {
            String id = context.getExternalContext().getRequestParameterMap().get("id");
            Usuario usu = usuarioEJB.buscarPorIdUsuario(Long.valueOf(id)).get(0);
            if (usu.getUsuFotoBD() == null) {
                return Utilidades.getImagenPorDefecto("foto");
            } else {
                return new DefaultStreamedContent(new ByteArrayInputStream(usu.getUsuFotoBD()));
            }
        }
    }
    
    
    public boolean isHabilitarTablaUsuarios() {
        return habilitarTablaUsuarios;
    }

    public void setHabilitarTablaUsuarios(boolean habilitarTablaUsuarios) {
        this.habilitarTablaUsuarios = habilitarTablaUsuarios;
    }

    public boolean isHabilitarEstudiantes() {
        return habilitarEstudiantes;
    }

    public void setHabilitarEstudiantes(boolean habilitarEstudiantes) {
        this.habilitarEstudiantes = habilitarEstudiantes;
    }

    public boolean isHabilitarFuncionarios() {
        return habilitarFuncionarios;
    }

    public void setHabilitarFuncionarios(boolean habilitarFuncionarios) {
        this.habilitarFuncionarios = habilitarFuncionarios;
    }

    public boolean isHabilitarFamiliares() {
        return habilitarFamiliares;
    }

    public void setHabilitarFamiliares(boolean habilitarFamiliares) {
        this.habilitarFamiliares = habilitarFamiliares;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public void cambiarTipoUsuario(ValueChangeEvent e) {
        String tipo = e.getNewValue().toString();
        this.habilitarEstudiantes = false;
        this.habilitarFuncionarios = false;
        this.habilitarFamiliares = false;
        this.habilitarTablaUsuarios = false;
        this.nombreUsuario = null;
        if (tipo.equals("Funcionarios")) {
            this.habilitarFuncionarios = true;
            this.habilitarTablaUsuarios = true;
            this.listaUsuarios = this.usuarioEJB.buscarPorUsuariosConCargo();
        }
        if (tipo.equals("Familiares")) {
            this.habilitarTablaUsuarios = true;
            this.habilitarFamiliares = true;
            this.listaUsuarios = this.usuarioEJB.buscarPorFamiliares();
        }
        if (tipo.equals("Estudiantes")) {
            this.habilitarTablaUsuarios = true;
            this.habilitarEstudiantes = true;
            this.listaUsuarios = this.usuarioEJB.buscarPorEstudiantes();
        }
    }

    public void buscarporIdentificacion() {
        if (this.habilitarEstudiantes == true) {
            this.listaUsuarios = usuarioEJB.buscarPorIdentificacionEstudiante(this.identificacion);
        } else {
            if (this.habilitarFamiliares == true) {
                this.listaUsuarios = usuarioEJB.buscarPorIdentificacionFamiliares(this.identificacion);

            } else {
                this.listaUsuarios = usuarioEJB.buscarPorIdentificacionFuncionario(this.identificacion);
            }
        }
    }

    public void buscarPorNombreUsuario() {
        if (this.habilitarEstudiantes == true) {
            this.listaUsuarios = usuarioEJB.busacarPorNombreEstudiante(this.nombreUsuario);
        } else {
            if (this.habilitarFamiliares == true) {
                this.listaUsuarios = usuarioEJB.busacarPorNombreFamiliar(this.nombreUsuario);

            } else {
                this.listaUsuarios = usuarioEJB.busacarPorNombreFuncionario(this.nombreUsuario);
            }
        }
    }

}
