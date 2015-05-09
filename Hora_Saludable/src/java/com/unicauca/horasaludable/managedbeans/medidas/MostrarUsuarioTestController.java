/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.managedbeans.medidas;


import com.unicauca.horasaludable.entities.Cargo;
import com.unicauca.horasaludable.entities.Unidadacademica;
import com.unicauca.horasaludable.entities.Usuario;
import com.unicauca.horasaludable.jpacontrollers.CargoFacade;
import com.unicauca.horasaludable.jpacontrollers.UnidadacademicaFacade;
import com.unicauca.horasaludable.jpacontrollers.UsuarioFacade;
import com.unicauca.horasaludable.jpacontrollers.UsuariogrupoFacade;
import com.unicauca.horasaludable.managedbeans.usuarios.MostrarUsuariosController;
import com.unicauca.horasaludable.validadores.ValidarEdicionUsuarios;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Jhonny Taborda
 */
@ManagedBean
@SessionScoped
public class MostrarUsuarioTestController {

    @EJB
    private UsuarioFacade usuarioEJB;
    @EJB
    private CargoFacade cargoEJB;
    @EJB
    private UnidadacademicaFacade unidadAcademicaEJB;
    @EJB
    private UsuariogrupoFacade usuarioGrupoEJB;
    private MostrarUsuariosController mostraUsuariosController;
    private Usuario usuario;
   
    private String usuarioSeleccionado;
    private String rutaFoto;
    private String rutaAbsolutaFotos;
    private UploadedFile foto;
    private String identificacion;
    private String nombres;
    private String apellidos;
    private Date fechaNacimiento;
    private SimpleDateFormat sdf;
    private String correo;
    private String telefono;
    private String nombreUsuario;
    private String contrasena;
    private String tipoUsuario;
    private List<Cargo> listaCargo;    
    private List<Unidadacademica> listaUnidadAcademica;
    private Long idCargo;
    private Long idUnidadAcademica;
    private Usuario funcionarioFamiliar;
    private List<Usuario> listaFuncionarios;
    private String nombreOApellidos;   
     private boolean campoFoto;

    public boolean isCampoFoto() {
        return campoFoto;
    }

    public void setCampoFoto(boolean campoFoto) {
        this.campoFoto = campoFoto;
    }
    
    private ValidarEdicionUsuarios validarEdicionUsuario;
    
       
    public MostrarUsuarioTestController() 
    {
        this.rutaFoto="img/fotosUploads";
        this.rutaAbsolutaFotos="/home/geovanny/Documentos/Asae/Hora_Saludable_2015/Hora_Saludable/web/resources/img/fotosUploads/";
        this.sdf=new SimpleDateFormat("yyyy-MM-dd");
    }
    
    public String getNombreOApellidos()
    {
        return nombreOApellidos;
    }

    public void setNombreOApellidos(String nombreOApellidos) 
    {
        this.nombreOApellidos = nombreOApellidos;
    }
    
    public List<Usuario> getListaFuncionarios() 
    {
        return listaFuncionarios;
    }

    public void setListaFuncionarios(List<Usuario> listaFuncionarios) 
    {
        this.listaFuncionarios = listaFuncionarios;
    }
    
    public Usuario getFuncionarioFamiliar() 
    {
        return funcionarioFamiliar;
    }

    public void setFuncionarioFamiliar(Usuario funcionarioFamiliar) 
    {
        this.funcionarioFamiliar = funcionarioFamiliar;
    }
    
    
    public Long getIdCargo() 
    {
        return idCargo;
    }

    public void setIdCargo(Long idCargo) 
    {
        this.idCargo = idCargo;
    }

    public Long getIdUnidadAcademica() 
    {
        return idUnidadAcademica;
    }

    public void setIdUnidadAcademica(Long idUnidadAcademica) 
    {
        this.idUnidadAcademica = idUnidadAcademica;
    }
    
    public String getTipoUsuario()
    {
        return tipoUsuario;
    }

    public List<Cargo> getListaCargo()
    {
        return listaCargo;
    }

    public void setListaCargo(List<Cargo> listaCargo)
    {
        this.listaCargo = listaCargo;
    }

    public List<Unidadacademica> getListaUnidadAcademica()
    {
        return listaUnidadAcademica;
    }

    public void setUnidadAcademica(List<Unidadacademica> listaUnidadAcademica)
    {
        this.listaUnidadAcademica = listaUnidadAcademica;
    }
    
    public void setTipoUsuario(String tipoUsuario) 
    {
        this.tipoUsuario = tipoUsuario;
    }
    
  

    public String getContrasena()
    {
        return contrasena;
    }

    public void setContrasena(String contrasena) 
    {
        this.contrasena = contrasena;
    }
    
    

    public String getNombreUsuario() 
    {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) 
    {
        this.nombreUsuario = nombreUsuario;
    }
    

    public String getTelefono()
    {
        return telefono;
    }

    public void setTelefono(String telefono) 
    {
        this.telefono = telefono;
    }
   
    public String getCorreo() 
    {
        return correo;
    }

    public void setCorreo(String correo)
    {
        this.correo = correo;
    }
    
    public SimpleDateFormat getSdf()
    {
        return sdf;
    }

    public void setSdf(SimpleDateFormat sdf) 
    {
        this.sdf = sdf;
    }
    
    public Date getFechaNacimiento() 
    {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) 
    {
        this.fechaNacimiento = fechaNacimiento;
    }
    
    public String getApellidos() 
    {
        return apellidos;
    }

    public void setApellidos(String apellidos) 
    {
        this.apellidos = apellidos;
    }
    
    
    public String getNombres()
    {
        return nombres;
    }

    public void setNombres(String nombres) 
    {
        this.nombres = nombres;
    }
    
    public String getIdentificacion() 
    {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) 
    {
        this.identificacion = identificacion;
    }
    
    public UploadedFile getFoto()
    {
        return foto;
    }

    public void setFoto(UploadedFile foto) 
    {
        this.foto = foto;
    }
    
    public String getRutaAbsolutaFotos() 
    {
        return rutaAbsolutaFotos;
    }

    public void setRutaAbsolutaFotos(String rutaAbsolutaFotos) 
    {
        this.rutaAbsolutaFotos = rutaAbsolutaFotos;
    }
    
    public String getRutaFoto() 
    {
        return rutaFoto;
    }

    public void setRutaFoto(String rutaFoto) 
    {
        this.rutaFoto = rutaFoto;
    }
    
    public Usuario getUsuario() 
    {
        return usuario;
    }

    public void setUsuario(Usuario usuario) 
    {
        this.usuario = usuario;
    }
    
    
    public void estudianteSeleccionado(Usuario estudiante)
    {
       
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.usuario=estudiante; 
       
        this.campoFoto=true;
     
                         
        
    }
   
    
    
    
}
