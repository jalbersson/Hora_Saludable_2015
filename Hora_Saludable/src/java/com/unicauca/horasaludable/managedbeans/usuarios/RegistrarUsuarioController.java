/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.managedbeans.usuarios;

import com.unicauca.horasaludable.entities.Cargo;
import com.unicauca.horasaludable.entities.Unidadacademica;
import com.unicauca.horasaludable.entities.Usuario;
import com.unicauca.horasaludable.jpacontrollers.CargoFacade;
import com.unicauca.horasaludable.jpacontrollers.UnidadacademicaFacade;
import com.unicauca.horasaludable.jpacontrollers.UsuarioFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.Application;
import javax.faces.application.ViewHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import org.primefaces.context.RequestContext;

/**
 *
 * @author geovanny
 */
@ManagedBean
@ViewScoped
public class RegistrarUsuarioController implements Serializable {

   @EJB
    private UnidadacademicaFacade unidadAcademicaEJB;
   @EJB
    private UsuarioFacade usuarioEJB;
   @EJB
    private CargoFacade cargoEJB;
    private List<String> listaTipo;
    private boolean camposRegistroEstudiante;
    private boolean camposRegistroFuncionario;    
    private boolean camposRegistroFamiliar;
    private boolean camposRegistroFuncionarioDocente;    
    private boolean camposRegistroFuncionarioAdministrativo;
    private List<Unidadacademica> listaFacultades;
    private List<Unidadacademica> listaUnidadAcademica;    
    private List<Cargo> listaCargos;    
    private Usuario usuario;
    private Unidadacademica facultad;
    private Unidadacademica unidadAcademica;
    private Cargo cargo;
    
    
    
    public RegistrarUsuarioController() 
    {
        this.cargarListaTipo();
        this.inicializarCamposUsuarioEspecificos(); 
        this.usuario=new Usuario();
    }
    
    public Cargo getCargo() 
    {
        return cargo;
    }

    public void setCargo(Cargo cargo) 
    {
        this.cargo = cargo;
    }
    
    public List<Unidadacademica> getListaUnidadAcademica() 
    {
        return listaUnidadAcademica;
    }

    public void setListaUnidadAcademica(List<Unidadacademica> listaUnidadAcademica) 
    {
        this.listaUnidadAcademica = listaUnidadAcademica;
    }
    
    public Unidadacademica getUnidadAcademica() 
    {
        return unidadAcademica;
    }

    public void setUnidadAcademica(Unidadacademica unidadAcademica) 
    {
        this.unidadAcademica = unidadAcademica;
    }
    
    public boolean isCamposRegistroFuncionarioDocente() 
    {
        return camposRegistroFuncionarioDocente;
    }

    public void setCamposRegistroFuncionarioDocente(boolean camposRegistroFuncionarioDocente) 
    {
        this.camposRegistroFuncionarioDocente = camposRegistroFuncionarioDocente;
    }

    public boolean isCamposRegistroFuncionarioAdministrativo() 
    {
        return camposRegistroFuncionarioAdministrativo;
    }

    public void setCamposRegistroFuncionarioAdministrativo(boolean camposRegistroFuncionarioAdministrativo) 
    {
        this.camposRegistroFuncionarioAdministrativo = camposRegistroFuncionarioAdministrativo;
    }
    
    public List<Cargo> getListaCargos() 
    {
        return listaCargos;
    }

    public void setListaCargos(List<Cargo> listaCargos) 
    {
        this.listaCargos = listaCargos;
    }
    
    public Unidadacademica getFacultad() 
    {
        return facultad;
    }

    public void setFacultad(Unidadacademica unidadacademica) 
    {
        this.facultad = unidadacademica;
    }
    
    public Usuario getUsuario()
    {
        return usuario;
    }

    public void setUsuario(Usuario usuario) 
    {
        this.usuario = usuario;
    }
    
    public List<Unidadacademica> getListaFacultades() 
    {
        return listaFacultades;
    }

    public void setListaFacultades(List<Unidadacademica> listaUnidadAcademica) 
    {
        this.listaFacultades = listaUnidadAcademica;
    }
    
    public boolean isCamposRegistroFuncionario() 
    {
        return camposRegistroFuncionario;
    }

    public void setCamposRegistroFuncionario(boolean camposRegistroFuncionario) 
    {
        this.camposRegistroFuncionario = camposRegistroFuncionario;
    }

    public boolean isCamposRegistroFamiliar() 
    {
        return camposRegistroFamiliar;
    }

    public void setCamposRegistroFamiliar(boolean camposRegistroFamiliar) 
    {
        this.camposRegistroFamiliar = camposRegistroFamiliar;
    }
    
    public boolean isCamposRegistroEstudiante() 
    {
        return camposRegistroEstudiante;
    }

    public void setCamposRegistroEstudiante(boolean camposRegistroEstudiante)
    {
        this.camposRegistroEstudiante = camposRegistroEstudiante;
    }
    
    public List<String> getListaTipo() 
    {
        return listaTipo;
    }

    public void setListaTipo(List<String> listaTipo) 
    {
        this.listaTipo = listaTipo;
    }
    
    private void cargarListaFacultades() 
    {
        this.listaFacultades= unidadAcademicaEJB.findBYFacultades();
    }
    
    private void cargarListaCargos() 
    {
        this.listaCargos= cargoEJB.findAll();
    }
    
    private void cargarListaUnidadesAcademicas()
    {
        this.listaUnidadAcademica=unidadAcademicaEJB.findAll();
    }
    
    private void inicializarCamposUsuarioEspecificos()
    {
        this.camposRegistroEstudiante=false;
        this.camposRegistroFamiliar=false;
        this.camposRegistroFuncionario=false;
        this.camposRegistroFuncionarioDocente=false;
        this.camposRegistroFuncionarioAdministrativo=false;
    }
    private void cargarListaTipo()
    {
        listaTipo=new ArrayList();        
        listaTipo.add("Estudiante");
        listaTipo.add("Familiar");
        listaTipo.add("Funcionario");
    }    
    public void abrirVentanaRegistrarUsuario()
    {
       RequestContext requestContext = RequestContext.getCurrentInstance();          
       FacesContext context = FacesContext.getCurrentInstance();
       Application application = context.getApplication();
       ViewHandler viewHandler = application.getViewHandler();
       UIViewRoot viewRoot = viewHandler.createView(context, context.getViewRoot().getViewId());
       context.setViewRoot(viewRoot);       
       context.renderResponse();          
       requestContext.update("form:panel");       
       requestContext.execute("PF('RegistrarUsuario').show()");
    }
    public void cambiarTipoUsuario(ValueChangeEvent e)
    {         
        String tipo=e.getNewValue().toString();
        this.camposRegistroEstudiante=false;
        this.camposRegistroFamiliar=false;
        this.camposRegistroFuncionario=false;
        this.camposRegistroFuncionarioDocente=false;
        this.camposRegistroFuncionarioAdministrativo=false;
        this.listaFacultades=null;
        this.facultad=null;
        this.cargo=null;
        this.unidadAcademica=null;        
        if(tipo.equals("Estudiante"))
        {
            this.facultad=new Unidadacademica();
            this.camposRegistroEstudiante=true;
            this.cargarListaFacultades();
        }
        if(tipo.equals("Funcionario"))
        {
            this.cargo=new Cargo();
            this.camposRegistroFuncionario=true;
            this.cargarListaCargos();
        }
        if(tipo.equals("Familiar"))
        {
            this.camposRegistroFamiliar=true;
        }
    }
    public void cambiarCargoFuncionario(ValueChangeEvent e)
    {         
        String tipo=e.getNewValue().toString();
        this.camposRegistroFuncionarioDocente=false;
        this.camposRegistroFuncionarioAdministrativo=false;
        this.listaFacultades=null;
        this.listaUnidadAcademica=null;
        this.facultad=null;
        this.unidadAcademica=null;
        if(tipo.equals("1"))
        {   this.facultad=new Unidadacademica();        
            this.cargarListaFacultades();
            this.camposRegistroFuncionarioDocente=true;            
        }
        if(tipo.equals("2"))
        {
            this.unidadAcademica= new Unidadacademica();
            this.cargarListaUnidadesAcademicas();
            this.camposRegistroFuncionarioAdministrativo=true;
        }       
    }
    public List<Unidadacademica> listarFacultades()
    {
        return unidadAcademicaEJB.findBYFacultades();
    }
    public void registrarUsuario()
    {
        if(this.facultad!=null)
        {
            this.usuario.setUniid(this.facultad);
        }
        if(this.unidadAcademica!=null)
        {
            this.usuario.setUniid(this.unidadAcademica);
        }
        if(this.cargo!=null)
        {
            this.usuario.setCarid(this.cargo);
        }
        this.usuarioEJB.create(this.usuario);
    }
    
}
