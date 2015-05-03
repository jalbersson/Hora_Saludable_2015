/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.managedbeans.usuarios;

import com.unicauca.horasaludable.entities.Usuario;
import com.unicauca.horasaludable.jpacontrollers.UsuarioFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;
/**
 *
 * @author geovanny
 */
@ManagedBean
@ViewScoped
public class MostrarUsuariosController implements Serializable {

    @EJB
    private UsuarioFacade usuarioEJB;
    
    private List<String> listaTiposdeUsuario;
    private List<Usuario> listaestudiantes;    
    private List<Usuario> listaFuncionarios;
    private List<Usuario> listaFamiliares;
    
    private boolean habilitarEstudiantes;    
    private boolean habilitarFuncionarios;
    private boolean habilitarFamiliares;
    private String nombreFuncionario;
    private String nombreEstudiante;
    private String nombreFamiliar;
    
    public MostrarUsuariosController() 
    {
        
    }    
    @PostConstruct
    private void init()
    {
        this.cargarListaTiposUsuarios();
        this.InicializarValores();
    }
    
    public String getNombreFamiliar() 
    {
        return nombreFamiliar;
    }

    public void setNombreFamiliar(String nombreFamiliar) 
    {
        this.nombreFamiliar = nombreFamiliar;
    }
    
    public String getNombreEstudiante() 
    {
        return nombreEstudiante;
    }

    public void setNombreEstudiante(String nombreEstudiante) 
    {
        this.nombreEstudiante = nombreEstudiante;
    }
    
    public String getNombreFuncionario() 
    {
        return nombreFuncionario;
    }

    public void setNombreFuncionario(String nombreFuncionario) 
    {
        this.nombreFuncionario = nombreFuncionario;
    }
    
    public List<String> getListaTiposdeUsuario() 
    {
        return listaTiposdeUsuario;
    }

    public void setListaTiposdeUsuario(List<String> listaTiposdeUsuario) 
    {
        this.listaTiposdeUsuario = listaTiposdeUsuario;
    }
    
    public List<Usuario> getListaestudiantes() 
    {
        return listaestudiantes;
    }

    public void setListaestudiantes(List<Usuario> listaestudiantes)
    {
        this.listaestudiantes = listaestudiantes;
    }

    public List<Usuario> getListaFuncionarios()
    {
        return listaFuncionarios;
    }

    public void setListaFuncionarios(List<Usuario> listaFuncionarios)
    {
        this.listaFuncionarios = listaFuncionarios;
    }

    public List<Usuario> getListaFamiliares() 
    {
        return listaFamiliares;
    }

    public void setListaFamiliares(List<Usuario> listaFamiliares) 
    {
        this.listaFamiliares = listaFamiliares;
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
    }
    
    public void cambiarTipoUsuario(ValueChangeEvent e)
    {
        String tipo=e.getNewValue().toString();
        this.habilitarEstudiantes=false;
        this.habilitarFuncionarios=false;
        this.habilitarFamiliares=false;
        this.listaFamiliares=null;
        this.listaFuncionarios=null;
        this.listaestudiantes=null;
        this.nombreEstudiante=null;
        this.nombreFuncionario=null;
        this.nombreFamiliar=null;
        if(tipo.equals("Funcionarios"))
        {
            this.habilitarFuncionarios=true;
            this.listaFuncionarios=this.usuarioEJB.buscarPorUsuariosConCargo();
        }
        if(tipo.equals("Familiares"))
        {
            this.habilitarFamiliares=true;
            this.listaFamiliares=this.usuarioEJB.buscarPorFamiliares();
        }
        if(tipo.equals("Estudiantes"))
        {
            this.habilitarEstudiantes=true;
            this.listaestudiantes=this.usuarioEJB.buscarPorEstudiantes();
        }
    }    
       
    public void buscarPorNombreFuncionario()
    {
        this.listaFuncionarios=usuarioEJB.busacarPorNombreFuncionario(this.nombreFuncionario);
    }
    
    public void buscarPorNombreEstudiante()
    {
        this.listaestudiantes=usuarioEJB.busacarPorNombreEstudiante(this.nombreEstudiante);
    }
    
    public void buscarPorNombreFamiliar()
    {
        this.listaFamiliares=usuarioEJB.busacarPorNombreFamiliar(this.nombreFamiliar);
    }
    
}
