/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.managedbeans.usuarios;

import com.unicauca.horasaludable.cifrado.Cifrar;
import com.unicauca.horasaludable.entities.Cargo;
import com.unicauca.horasaludable.entities.Unidadacademica;
import com.unicauca.horasaludable.entities.Usuario;
import com.unicauca.horasaludable.jpacontrollers.CargoFacade;
import com.unicauca.horasaludable.jpacontrollers.UnidadacademicaFacade;
import com.unicauca.horasaludable.jpacontrollers.UsuarioFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.ViewHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.validator.ValidatorException;
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
    private boolean funcionarioSeleccionado;

    
    private List<Unidadacademica> listaFacultades;
    private List<Unidadacademica> listaUnidadAcademica;    
    private List<Cargo> listaCargos;    
    private Usuario usuario;
    private Unidadacademica facultad;
    private Unidadacademica unidadAcademica;
    private Cargo cargo;
    private String contrasena;   
    private String repetircontrasena;
    private String numeroIdentificacion;
    private List<Usuario> listaFuncionarios;
    private Usuario funcionario;
    private String nombreOApellidos;  
    
    
    
    public RegistrarUsuarioController() 
    {
        this.cargarListaTipo();
        this.inicializarCamposUsuarioEspecificos(); 
        this.usuario=new Usuario();
        
    }
    
    @PostConstruct
    private void init()
    {
        this.facultad=new Unidadacademica();
        this.cargarListaFacultades();        
    }
    
    public String getNombreOApellidos() 
    {
        return nombreOApellidos;
    }

    public void setNombreOApellidos(String nombreOApellidos) 
    {
        this.nombreOApellidos = nombreOApellidos;
    }
    
    public boolean isFuncionarioSeleccionado()
    {
        return funcionarioSeleccionado;
    }

    public void setFuncionarioSeleccionado(boolean funcionarioSeleccionado)
    {
        this.funcionarioSeleccionado = funcionarioSeleccionado;
    }
    
    public Usuario getFuncionario()
    {
        return funcionario;
    }

    public void setFuncionario(Usuario funcionario) 
    {
        this.funcionario = funcionario;
    }
    
    public List<Usuario> getListaFuncionarios() 
    {
        return listaFuncionarios;
    }

    public void setListaFuncionarios(List<Usuario> listaFuncionarios) 
    {
        this.listaFuncionarios = listaFuncionarios;
    }
    
    public String getNumeroIdentificacion() 
    {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) 
    {
        this.numeroIdentificacion = numeroIdentificacion;
    }
     public String getContrasena() 
    {
        return contrasena;
    }

    public void setContrasena(String contrasena) 
    {
        this.contrasena = contrasena;
    }

    public String getRepetircontrasena()
    {
        return repetircontrasena;
    }

    public void setRepetircontrasena(String repetircontrasena)
    {
        this.repetircontrasena = repetircontrasena;
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
        this.camposRegistroEstudiante=true;
        this.camposRegistroFamiliar=false;
        this.camposRegistroFuncionario=false;
        this.camposRegistroFuncionarioDocente=false;
        this.camposRegistroFuncionarioAdministrativo=false;
        this.funcionarioSeleccionado=false;
    }
    private void cargarListaTipo()
    {
        listaTipo=new ArrayList();        
        listaTipo.add("Estudiante");
        listaTipo.add("Familiar");
        listaTipo.add("Funcionario");
    }
    
    private void cargarListaFuncionarios()
    {
        this.listaFuncionarios=usuarioEJB.buscarPorUsuariosConCargo();
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
       requestContext.update("seleccionarUsuarios");
       requestContext.update("tablasUsuarios");
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
        this.listaFuncionarios=null;
        this.funcionario=null;
        if(tipo.equals("Estudiante"))
        {
            this.facultad=new Unidadacademica();
            this.camposRegistroEstudiante=true;
            this.cargarListaFacultades();
        }
        if(tipo.equals("Funcionario"))
        {
            this.cargo=new Cargo();
            this.facultad=new Unidadacademica();        
            this.cargarListaFacultades();
            this.camposRegistroFuncionarioDocente=true;
            this.camposRegistroFuncionario=true;
            this.cargarListaCargos();
        }
        if(tipo.equals("Familiar"))
        {
            this.funcionario= new Usuario();
            this.funcionario.setUniid(new Unidadacademica());
            this.cargarListaFuncionarios();
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.update("funcionarios");
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
    public void  buscarPorNombreFuncionario()
    {
        
        this.listaFuncionarios=usuarioEJB.busacarPorNombreFuncionario(this.nombreOApellidos.toLowerCase());
        
    }
    
    public void validateContrasena(FacesContext arg0, UIComponent arg1, Object arg2)throws ValidatorException {
      
        this.contrasena=String.valueOf(arg2);
    }
    
   public void validateRepitaContrasena(FacesContext arg0, UIComponent arg1, Object arg2)throws ValidatorException 
   {
      String texto = String.valueOf(arg2);      
      if (!(texto.equals(this.contrasena))) {
         throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,"","Las Contraseñas no Coinciden."));
          
      }
      
   }   
   public void seleccionarFuncionario(Usuario usuario)
   {
       RequestContext requestContext = RequestContext.getCurrentInstance();
       if(this.usuarioEJB.buscarPorConyugeid(usuario.getUsuid()))
       {
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Info", "El Funcionario ya tiene un Familiar Asociado."));
           requestContext.execute("PF('FuncionarioYaTieneFamiliarAsociado').show()");
       }
       else
       {
                         
           requestContext.execute("PF('seleccionarFuncionario').hide()");
           this.funcionario=usuario;
           this.funcionarioSeleccionado=false;
           requestContext.update("form:nombreFuncionario"); 
           requestContext.update("form:dependenciaFuncionario");
       }
       

   }
   public void registrarUsuario()
   {
   
       if(this.funcionario!=null)
       {
           if(this.funcionario.getUsunombres()==null)
           {
               this.funcionarioSeleccionado=true;
           }
           else
           {
               this.usuario.setUsuidentificacion(Long.parseLong(this.numeroIdentificacion));
               this.usuario.setUsucontrasena(Cifrar.sha512(this.contrasena));
               this.usuario.setConyugeid(this.funcionario);
               this.usuarioEJB.create(this.usuario);            
               
               RequestContext requestContext = RequestContext.getCurrentInstance();
               FacesContext context = FacesContext.getCurrentInstance();
               Application application = context.getApplication();
               ViewHandler viewHandler = application.getViewHandler();
               UIViewRoot viewRoot = viewHandler.createView(context, context.getViewRoot().getViewId());
               context.setViewRoot(viewRoot);       
               context.renderResponse();
               this.usuario=new Usuario();
               this.cargarListaTipo();
               this.inicializarCamposUsuarioEspecificos();
               this.facultad=new Unidadacademica();
               this.cargarListaFacultades();
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Registro Exitoso."));
               requestContext.execute("PF('mensajeRegistroExitoso').show()");
           }               
       }
       else
       {           
           if(this.cargo!=null)
           {
               this.usuario.setCarid(this.cargo);
           }
           if(this.facultad!=null)
           {
               this.usuario.setUniid(this.facultad);
           } 
           if(this.unidadAcademica!=null)
           {
               this.usuario.setUniid(this.unidadAcademica);
           }
           
           this.usuario.setUsuidentificacion(Long.parseLong(this.numeroIdentificacion));
           this.usuario.setUsucontrasena(Cifrar.sha512(this.contrasena));           
           this.usuarioEJB.create(this.usuario);            
           
           
           RequestContext requestContext = RequestContext.getCurrentInstance(); 
           FacesContext context = FacesContext.getCurrentInstance();
           Application application = context.getApplication();
           ViewHandler viewHandler = application.getViewHandler();
           UIViewRoot viewRoot = viewHandler.createView(context, context.getViewRoot().getViewId());
           context.setViewRoot(viewRoot);       
           context.renderResponse();
           this.usuario=new Usuario();
           this.cargarListaTipo();
           this.inicializarCamposUsuarioEspecificos();
           this.facultad=new Unidadacademica();
           this.cargarListaFacultades();
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Registro Exitoso."));
           requestContext.execute("PF('mensajeRegistroExitoso').show()");
       }    
   }   
}


