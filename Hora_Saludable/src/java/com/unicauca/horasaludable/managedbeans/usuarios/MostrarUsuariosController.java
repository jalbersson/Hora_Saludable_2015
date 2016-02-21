/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.managedbeans.usuarios;

import com.unicauca.horasaludable.entities.Usuario;
import com.unicauca.horasaludable.jpacontrollers.UsuarioFacade;
import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
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
 * @author geovanny
 */
@ManagedBean
@SessionScoped
public class MostrarUsuariosController implements Serializable {

    @EJB
    private UsuarioFacade usuarioEJB;
    
    private List<String> listaTiposdeUsuario;
    private List<Usuario> listaUsuarios; 
    private boolean habilitarEstudiantes;    
    private boolean habilitarFuncionarios;
    private boolean habilitarFamiliares;
    private boolean habilitarTablaUsuarios;
    
    private String nombreUsuario;
    
    
    public MostrarUsuariosController() 
    {
        
    }    
    @PostConstruct
    private void init()
    {
        this.cargarListaTiposUsuarios();
        this.InicializarValores();
    }   
    /**
     * Recupera la foto de la bd y la devuelve como un StreamedContent
     * @return flujo de la imagen
     */
    public StreamedContent getImagenFlujo() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            return new DefaultStreamedContent();
        } else {
            String id = context.getExternalContext().getRequestParameterMap().get("id");
            Usuario usu = usuarioEJB.buscarPorIdUsuario(Long.valueOf(id)).get(0);
            if(usu.getUsuFotoBD()==null){
                 return Utilidades.getImagenPorDefecto("foto");
            }else{
                return new DefaultStreamedContent(new ByteArrayInputStream(usu.getUsuFotoBD()));
            }
        }
    }    
    public boolean isHabilitarTablaUsuarios()
    {
        return habilitarTablaUsuarios;
    }

    public void setHabilitarTablaUsuarios(boolean habilitarTablaUsuarios)
    {
        this.habilitarTablaUsuarios = habilitarTablaUsuarios;
    }
    
    public String getNombreUsuario() 
    {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) 
    {
        this.nombreUsuario = nombreUsuario;
    }
    
    public List<String> getListaTiposdeUsuario() 
    {
        return listaTiposdeUsuario;
    }

    public void setListaTiposdeUsuario(List<String> listaTiposdeUsuario) 
    {
        this.listaTiposdeUsuario = listaTiposdeUsuario;
    }
    
    public List<Usuario> getListaUsuarios() 
    {
        return listaUsuarios;
    }   
    
    public boolean isHabilitarEstudiantes()
    {
        return this.habilitarEstudiantes;
    }

    public void setHabilitarEstudiantes(boolean haibilitarEstudiantes) 
    {
        this.habilitarEstudiantes = haibilitarEstudiantes;
    }

    public boolean isHabilitarFuncionarios()
    {
        return this.habilitarFuncionarios;
    }

    public void setHabilitarFuncionarios(boolean habilitarFuncionarios) 
    {
        this.habilitarFuncionarios = habilitarFuncionarios;
    }

    public boolean isHabilitarFamiliares() 
    {
        return this.habilitarFamiliares;
    }

    public void setHabilitarFamiliares(boolean habilitarFamiliares) 
    {
        this.habilitarFamiliares = habilitarFamiliares;
    }
    
    private void cargarListaTiposUsuarios()
    {
        this.listaTiposdeUsuario=new ArrayList();
        this.listaTiposdeUsuario.add("Estudiantes");
        this.listaTiposdeUsuario.add("Funcionarios");
        this.listaTiposdeUsuario.add("Familiares");
    }
    
    private void InicializarValores()
    {
        this.habilitarEstudiantes=false;
        this.habilitarFamiliares=false;
        this.habilitarFuncionarios=false;
        this.habilitarTablaUsuarios=false;
    }
    
    public void cambiarTipoUsuario(ValueChangeEvent e)
    {
        String tipo=e.getNewValue().toString();
        this.habilitarEstudiantes=false;
        this.habilitarFuncionarios=false;
        this.habilitarFamiliares=false;
        this.habilitarTablaUsuarios=false;
        this.nombreUsuario=null;
        if(tipo.equals("Funcionarios"))
        {
            this.habilitarFuncionarios=true;
            this.habilitarTablaUsuarios=true;
            this.listaUsuarios=this.usuarioEJB.buscarPorUsuariosConCargo();
        }
        if(tipo.equals("Familiares"))
        {
            this.habilitarFamiliares=true;
            this.habilitarTablaUsuarios=true;
            this.listaUsuarios=this.usuarioEJB.buscarPorFamiliares();
        }
        if(tipo.equals("Estudiantes"))
        {
            this.habilitarEstudiantes=true;
            this.habilitarTablaUsuarios=true;
            this.listaUsuarios=this.usuarioEJB.buscarPorEstudiantes();
        }
    }    
       
    public void buscarPorNombreUsuario()
    {
        if(this.habilitarEstudiantes==true)
        {
            this.listaUsuarios=usuarioEJB.busacarPorNombreEstudiante(this.nombreUsuario);
        }
        else
        {
            if(this.habilitarFamiliares==true)
            {
                this.listaUsuarios=usuarioEJB.busacarPorNombreFamiliar(this.nombreUsuario);
 
            }
            else
            {
                this.listaUsuarios=usuarioEJB.busacarPorNombreFuncionario(this.nombreUsuario);
            }
        }
    }  
    
    
}
