package com.unicauca.horasaludable.managedbeans.usuarios;

import com.unicauca.horasaludable.cifrado.Cifrar;
import com.unicauca.horasaludable.entities.Cargo;
import com.unicauca.horasaludable.entities.Unidadacademica;
import com.unicauca.horasaludable.entities.Usuario;
import com.unicauca.horasaludable.jpacontrollers.CargoFacade;
import com.unicauca.horasaludable.jpacontrollers.UnidadacademicaFacade;
import com.unicauca.horasaludable.jpacontrollers.UsuarioFacade;
import com.unicauca.horasaludable.jpacontrollers.UsuariogrupoFacade;
import com.unicauca.horasaludable.validadores.ValidarEdicionUsuarios;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author geovanny
 */
@ManagedBean
@ViewScoped
public class VerEditarUsuarioController implements Serializable 
{
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
    private boolean camposFuncionario;
    private boolean camposEstudiante;
    private boolean camposFamiliar;    
    private boolean campoFoto;
    private boolean campoFechaNacimiento;
    private boolean campoIdentificacion;
    private boolean campoNombre;
    private boolean campoApellidos;
    private boolean campoCorreo;
    private boolean campotelefono;
    private boolean campoNombreUsuario;
    private boolean campoContrasena;    
    private boolean modificarDatosAcademicos;    
    private boolean aceptarCancelarModificarDatosAcademicos;
    private boolean modificarDatosFuncionario;
    private boolean modificarDatosEstudiante;
    private boolean modificarDatosFamiliar;    
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
    
    
    private ValidarEdicionUsuarios validarEdicionUsuario;
    
       
    public VerEditarUsuarioController() 
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
    
    public boolean isModificarDatosFuncionario()
    {
        return modificarDatosFuncionario;
    }

    public void setModificarDatosFuncionario(boolean modificarDatosFuncionario)
    {
        this.modificarDatosFuncionario = modificarDatosFuncionario;
    }

    public boolean isModificarDatosEstudiante()
    {
        return modificarDatosEstudiante;
    }

    public void setModificarDatosEstudiante(boolean modificarDatosEstudiante)
    {
        this.modificarDatosEstudiante = modificarDatosEstudiante;
    }

    public boolean isModificarDatosFamiliar()
    {
        return modificarDatosFamiliar;
    }

    public void setModificarDatosFamiliar(boolean modificarDatosFamiliar)
    {
        this.modificarDatosFamiliar = modificarDatosFamiliar;
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
    
    public boolean isModificarDatosAcademicos()
    {
        return modificarDatosAcademicos;
    }

    public void setModificarDatosAcademicos(boolean modificarDatosAcademicos) 
    {
        this.modificarDatosAcademicos = modificarDatosAcademicos;
    }

    public boolean isAceptarCancelarModificarDatosAcademicos() 
    {
        return aceptarCancelarModificarDatosAcademicos;
    }

    public void setAceptarCancelarModificarDatosAcademicos(boolean aceptarCancelarModificarDatosAcademicos) 
    {
        this.aceptarCancelarModificarDatosAcademicos = aceptarCancelarModificarDatosAcademicos;
    }
    
    public boolean isCampoContrasena() 
    {
        return campoContrasena;
    }

    public void setCampoContrasena(boolean campoContrasena) 
    {
        this.campoContrasena = campoContrasena;
    }
    
    public String getContrasena()
    {
        return contrasena;
    }

    public void setContrasena(String contrasena) 
    {
        this.contrasena = contrasena;
    }
    
    public boolean isCampoNombreUsuario() 
    {
        return campoNombreUsuario;
    }

    public void setCampoNombreUsuario(boolean campoNombreUsuario)
    {
        this.campoNombreUsuario = campoNombreUsuario;
    }   

    public String getNombreUsuario() 
    {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) 
    {
        this.nombreUsuario = nombreUsuario;
    }
    
    public boolean isCampotelefono() 
    {
        return campotelefono;
    }

    public void setCampotelefono(boolean campotelefono) 
    {
        this.campotelefono = campotelefono;
    }    

    public String getTelefono()
    {
        return telefono;
    }

    public void setTelefono(String telefono) 
    {
        this.telefono = telefono;
    }
    
    public boolean isCampoCorreo() 
    {
        return campoCorreo;
    }

    public void setCampoCorreo(boolean campoCorreo) 
    {
        this.campoCorreo = campoCorreo;
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
    
    public boolean isCampoFechaNacimiento()
    {
        return campoFechaNacimiento;
    }

    public void setCampoFechaNacimiento(boolean campoFechaNacimiento)
    {
        this.campoFechaNacimiento = campoFechaNacimiento;
    }    
    
    public String getApellidos() 
    {
        return apellidos;
    }

    public void setApellidos(String apellidos) 
    {
        this.apellidos = apellidos;
    }
    
    public boolean isCampoApellidos() 
    {
        return campoApellidos;
    }

    public void setCampoApellidos(boolean campoApellidos) 
    {
        this.campoApellidos = campoApellidos;
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
    
     public boolean isCampoIdentificacion()
    {
        return campoIdentificacion;
    }

    public void setCampoIdentificacion(boolean campoIdentificacion) 
    {
        this.campoIdentificacion = campoIdentificacion;
    }
    
    public boolean isCampoNombre() 
    {
        return campoNombre;
    }

    public void setCampoNombre(boolean campoNombre)
    {
        this.campoNombre = campoNombre;
    }
    
    public boolean isCampoFoto() 
    {
        return campoFoto;
    }

    public void setCampoFoto(boolean campoFoto) 
    {
        this.campoFoto = campoFoto;
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
    
    public boolean isCamposFuncionario() 
    {
        return camposFuncionario;
    }

    public void setCamposFuncionario(boolean camposFuncionario) 
    {
        this.camposFuncionario = camposFuncionario;
    }

    public boolean isCamposEstudiante() 
    {
        return camposEstudiante;
    }

    public void setCamposEstudiante(boolean camposEstudiante) 
    {
        this.camposEstudiante = camposEstudiante;
    }

    public boolean isCamposFamiliar() 
    {
        return camposFamiliar;
    }

    public void setCamposFamiliar(boolean camposFamiliar) 
    {
        this.camposFamiliar = camposFamiliar;
    }
    
    public Usuario getUsuario() 
    {
        return usuario;
    }

    public void setUsuario(Usuario usuario) 
    {
        this.usuario = usuario;
    }
    
    public void usuarioSeleccionado(Usuario usuario,MostrarUsuariosController mgb)
    {
        this.mostraUsuariosController=mgb;
        RequestContext requestContext = RequestContext.getCurrentInstance();
        
        this.camposEstudiante=this.mostraUsuariosController.isHabilitarEstudiantes();
        this.camposFamiliar=this.mostraUsuariosController.isHabilitarFamiliares();
        this.camposFuncionario=this.mostraUsuariosController.isHabilitarFuncionarios();
        
        this.usuario=usuario;       
        this.campoFoto=true;
        this.campoNombre=true;
        this.campoIdentificacion=true;
        this.campoApellidos=true;
        this.campoFechaNacimiento=true;
        this.campoCorreo=true;
        this.campotelefono=true;
        this.campoNombreUsuario=true;
        this.campoContrasena=true;
        this.modificarDatosAcademicos=true;  
        
        this.aceptarCancelarModificarDatosAcademicos=false;
        requestContext.update("formularioFoto");
        requestContext.update("formularioTituloVerEditar");
        requestContext.update("formularioEditarFoto");
        requestContext.update("formularioDatosPersonales");
        requestContext.update("formularioDatosCuenta");
        requestContext.update("formularioOtrosDatos");
        requestContext.execute("PF('verEditarUsuario').show()");               
        
    } 
    public void cargarFoto(FileUploadEvent event)
    {
        RequestContext requestContext = RequestContext.getCurrentInstance(); 
        this.foto=event.getFile();
        requestContext.update("formularioFoto");
        requestContext.update("formularioEditarFoto");
        
    }
    public void actualizarFoto() throws InterruptedException
    {       
        RequestContext requestContext = RequestContext.getCurrentInstance();        
        if(this.foto!=null)
        {
            this.campoFoto=true;                       
            int i = this.foto.getFileName().lastIndexOf('.');            
            String extension = this.foto.getFileName().substring(i+1);
            String nombre;
            if(!this.usuario.getUsufoto().equals("vacio.jpg"))
            {
                int indiceImagenAnterior=this.usuario.getUsufoto().lastIndexOf(".");
                String imagenAnerior=this.usuario.getUsufoto().substring(0, indiceImagenAnterior);
                int indiceImagenFiltrada=imagenAnerior.lastIndexOf("_");
                String numerostring=imagenAnerior.substring(indiceImagenFiltrada+1);
                int numero=Integer.parseInt(numerostring);
                numero=numero+1;
                nombre=this.usuario.getUsuidentificacion()+"_"+numero+"."+extension;                
            }
            else
            {
                nombre=this.usuario.getUsuidentificacion()+"_1."+extension;
            }
            
            
            try 
            {
                this.usuario.setUsufoto(nombre);
                this.usuarioEJB.edit(usuario);
                this.GuardarFoto(nombre, this.foto.getInputstream());
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Foto Actualizada.", "Foto Actualizada."));
            } catch (IOException e) 
            {
                e.printStackTrace();
            }
            Thread.sleep(2000);
            this.foto=null;            
            
        }
        else
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se ha cargado una foto.", "No se ha cargado una foto"));
        }
        requestContext.update("formularioFoto");
        requestContext.update("formularioEditarFoto"); 
    }    
    public void cancelarSubirFoto()
    {        
        RequestContext requestContext = RequestContext.getCurrentInstance(); 
        this.campoFoto=true;        
        this.foto=null;
        requestContext.update("formularioFoto");
        requestContext.update("formularioEditarFoto"); 
        
    }
    private void GuardarFoto(String filename, InputStream in)
    {
       try 
       { 
            OutputStream out = new FileOutputStream(new File(this.rutaAbsolutaFotos + filename));              
            int read = 0;
            byte[] bytes = new byte[1024];              
            while ((read = in.read(bytes)) != -1) 
            {
                out.write(bytes, 0, read);
            }              
            in.close();
            out.flush();
            out.close();
       } catch (IOException e)
       {
            System.out.println(e.getMessage());
       } 
    }   
    
    public void mostraSubirFoto()
    {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.campoFoto=false;        
        requestContext.update("formularioFoto");
        requestContext.update("formularioEditarFoto"); 
    }
    
    public void mostrarModifiarNombre()
    {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.campoNombre=false;        
        this.nombres=this.usuario.getUsunombres();
        requestContext.update("formularioDatosPersonales");
    }
    
    public void cancelarActualizarNombre()
    {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.campoNombre=true;        
        this.nombres="";
        requestContext.update("formularioDatosPersonales");
    }
    
    public void actualizarNombre()
    {
        this.validarEdicionUsuario= new ValidarEdicionUsuarios();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        if(this.validarEdicionUsuario.validarNombres(this.nombres))
        {
            this.campoNombre=true;            
            this.usuario.setUsunombres(nombres);
            this.usuarioEJB.edit(this.usuario);            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info. Campo Nombre Actualizado.", ""));
        }
        requestContext.update("formularioDatosPersonales");
        requestContext.update("tablasUsuarios");
    }
    
    public void mostrarModificarIdentificacion()
    {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.campoIdentificacion=false;        
        this.identificacion=this.usuario.getUsuidentificacion()+"";
        requestContext.update("formularioDatosPersonales");        
    }  
    
    public void cancelarActualizarIdentificacion()
    {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.campoIdentificacion=true;        
        this.identificacion="";
        requestContext.update("formularioDatosPersonales");        
    }    
    public void actualizarIdentificacion()
    {
        this.validarEdicionUsuario= new ValidarEdicionUsuarios();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        if(this.validarEdicionUsuario.validarNumeroIdentificacion(this.identificacion,this.usuarioEJB))
        {            
            this.campoIdentificacion=true;            
            this.usuario.setUsuidentificacion(Long.parseLong(this.identificacion));
            this.usuarioEJB.edit(this.usuario);
            this.identificacion="";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info. Campo número de identificación Actualizado.", ""));
        }        
        requestContext.update("formularioDatosPersonales");
        requestContext.update("tablasUsuarios");
    }
    
    public void mostrarModificarApellidos()
    {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.campoApellidos=false;        
        this.apellidos=this.usuario.getUsuapellidos();
        requestContext.update("formularioDatosPersonales");
    }
    
    public void cancelarActualizarApellidos()
    {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.campoApellidos=true;        
        this.apellidos="";
        requestContext.update("formularioDatosPersonales");
    }
    
    public void actualizarApellidos()
    {
        this.validarEdicionUsuario= new ValidarEdicionUsuarios();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        if(this.validarEdicionUsuario.validarApellidos(this.apellidos))
        {
            this.campoApellidos=true;            
            this.usuario.setUsuapellidos(this.apellidos);
            this.usuarioEJB.edit(this.usuario);            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info. Campo apellidos actualizado.", ""));
        }
        requestContext.update("formularioDatosPersonales");
        requestContext.update("tablasUsuarios");        
    }
    
    public void mostrarModificarFechaNacimiento()
    {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.campoFechaNacimiento=false;        
        this.fechaNacimiento= new Date(this.usuario.getUsufechanacimiento().getYear(),this.usuario.getUsufechanacimiento().getMonth(),this.usuario.getUsufechanacimiento().getDate());
        requestContext.update("formularioDatosPersonales");        
    }
    
    public void cancelarActualizarFechaNacimiento()
    {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.campoFechaNacimiento=true;        
        this.fechaNacimiento= new Date();
        requestContext.update("formularioDatosPersonales");
    }
    
    public void actualizarFechaNacimiento()
    {
        this.validarEdicionUsuario= new ValidarEdicionUsuarios();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        if(this.validarEdicionUsuario.validarFechaNacimiento(this.fechaNacimiento))
        {
            this.campoFechaNacimiento=true;            
            this.usuario.setUsufechanacimiento(this.fechaNacimiento);
            this.usuarioEJB.edit(this.usuario);            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info. Campo fecha nacimiento actualizado.", ""));
        }
        requestContext.update("formularioDatosPersonales");
        requestContext.update("tablasUsuarios");
    }
    
    public void mostrarModificarCorreo()
    {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.campoCorreo=false;        
        this.correo=this.usuario.getUsuemail();
        requestContext.update("formularioDatosPersonales");
    }
    
    public void cancelarActualizarCorreo()
    {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.campoCorreo=true;        
        this.correo="";
        requestContext.update("formularioDatosPersonales");
    }
    
    public void actualizarCorreo()
    {
        this.validarEdicionUsuario= new ValidarEdicionUsuarios();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        if(this.validarEdicionUsuario.validarCorreo(this.correo,this.usuarioEJB))
        {
            this.campoCorreo=true;            
            this.usuario.setUsuemail(this.correo);
            this.usuarioEJB.edit(this.usuario);            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info. Campo correo electrónico actualizado.", ""));
        }
        requestContext.update("formularioDatosPersonales");        
    }
    
    public void mostrarModificarTelefono()
    {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.campotelefono=false;        
        if(this.usuario.getUsutelefono()!=null)
        {
            this.telefono=this.usuario.getUsutelefono()+"";
        }        
        requestContext.update("formularioDatosPersonales");
    }
    
    public void cancelarActualizarTelefono()
    {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.campotelefono=true;        
        this.telefono="";
        requestContext.update("formularioDatosPersonales");
    }
    
    public void actualizarTelefono()
    {
        this.validarEdicionUsuario= new ValidarEdicionUsuarios();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        if(this.validarEdicionUsuario.validarTelefono(this.telefono))
        {
            this.campotelefono=true;            
            if(!this.telefono.isEmpty())
            {
                BigInteger bi= new BigInteger(this.telefono);
                this.usuario.setUsutelefono(bi);
            }
            else
            {
                this.usuario.setUsutelefono(null);
            }
            this.usuarioEJB.edit(this.usuario);            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info. Campo teléfono actualizado.", ""));
        }
        requestContext.update("formularioDatosPersonales");        
    }
    
    public void mostrarModificarNombreUsuario()
    {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.campoNombreUsuario=false;        
        this.nombreUsuario=this.usuario.getUsunombreusuario();
        requestContext.update("formularioDatosCuenta");
    }
    
    public void cancelarActualizarNombreUsuario()
    {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.campoNombreUsuario=true;        
        this.nombreUsuario="";
        requestContext.update("formularioDatosCuenta");
    }
    
    public void actualizarNombreUsuario()
    {
        this.validarEdicionUsuario= new ValidarEdicionUsuarios();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        if(this.validarEdicionUsuario.validarNombreUsuario(this.nombreUsuario,this.usuarioEJB))
        {
            this.campoNombreUsuario=true;            
            this.usuario.setUsunombreusuario(this.nombreUsuario);
            this.usuarioEJB.edit(this.usuario);
            this.usuarioGrupoEJB.actualizarNombreUsuario("user",this.usuario.getUsuid(), this.usuario.getUsunombreusuario());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info. Campo nombre de usuario actualizado.", ""));
        }
        requestContext.update("formularioDatosCuenta");        
    }
    
    public void mostrarModificarContrasena()
    {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.campoContrasena=false;                
        requestContext.update("formularioDatosCuenta");
    }
    
    public void cancelarActualizarContrasena()
    {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.campoContrasena=true;        
        this.contrasena="";
        requestContext.update("formularioDatosCuenta");
    }
    
    public void actualizarContrasena()
    {
        this.validarEdicionUsuario= new ValidarEdicionUsuarios();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        if(this.validarEdicionUsuario.validarContrasena(this.contrasena))
        {
            this.campoContrasena=true;            
            this.usuario.setUsucontrasena(Cifrar.sha256(this.contrasena));
            this.usuarioEJB.edit(this.usuario);            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info. Campo contraseña actualizado.", ""));
        }
        requestContext.update("formularioDatosCuenta");        
    }
    public void modificarDatosAcademicos()
    {
        this.modificarDatosFamiliar=false;
        this.modificarDatosEstudiante=false;
        this.modificarDatosFuncionario=false;
        if(this.camposFuncionario==true)
        {
            this.modificarDatosFuncionario=true;
            this.usuarioSeleccionado="Funcionario";
            this.tipoUsuario="Funcionario";
            this.camposFuncionario=false;
            this.listaCargo=cargoEJB.findAll();
            if(this.usuario.getCarid().getCarnombre().equals("Docente"))
            {
                this.listaUnidadAcademica=unidadAcademicaEJB.findBYFacultades();
            }
            else
            {
                this.listaUnidadAcademica=unidadAcademicaEJB.findAll();
            }
            this.idCargo=usuario.getCarid().getCarid();
            this.idUnidadAcademica=usuario.getUniid().getUniid();
        }
        if(this.camposFamiliar==true)
        {
            this.modificarDatosFamiliar=true;
            this.usuarioSeleccionado="Familiar";
            this.tipoUsuario="Familiar";
            this.camposFamiliar=false;
            this.funcionarioFamiliar=this.usuario.getConyugeid();            
            this.listaFuncionarios=this.usuarioEJB.buscarPorUsuariosConCargo();            
        }
        if(this.camposEstudiante==true)
        {
            this.modificarDatosEstudiante=true;
            this.usuarioSeleccionado="Estudiante";
            this.tipoUsuario="Estudiante";
            this.camposEstudiante=false;
            this.idUnidadAcademica=this.usuario.getUniid().getUniid();
            this.listaUnidadAcademica=unidadAcademicaEJB.findBYFacultades();
        }
        this.aceptarCancelarModificarDatosAcademicos=true;
        this.modificarDatosAcademicos=false;
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formularioOtrosDatos");
        requestContext.update("ventanaSeleccionarFuncionario"); 
    }
    public void cancelarModificarDatosAcademicos()
    {
        if(this.usuarioSeleccionado.equals("Funcionario"))
        {            
            this.camposFuncionario=true;            
        }
        if(this.usuarioSeleccionado.equals("Familiar"))        {
            
            this.camposFamiliar=true;
        }
        if(this.usuarioSeleccionado.equals("Estudiante"))
        {
            this.camposEstudiante=true;
        }
        this.aceptarCancelarModificarDatosAcademicos=false;
        this.modificarDatosAcademicos=true;
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formularioOtrosDatos"); 
        requestContext.update("tablasUsuarios");
        requestContext.update("formularioTituloVerEditar");
    }
    
    public void seleccionarFuncionario(Usuario funcionarioEditar)
   {
       RequestContext requestContext = RequestContext.getCurrentInstance();
       if(this.usuarioEJB.buscarPorConyugeid(funcionarioEditar.getUsuid()))
       {
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Info", "El Funcionario ya tiene un Familiar Asociado."));
           requestContext.execute("PF('editarFuncionarioYaTieneFamiliarAsociado').show()");
       }
       else
       {
           if(this.usuario.getUsuid().equals(funcionarioEditar.getUsuid()))
           {
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Info", "No puede ser familiar de el mismo."));
               requestContext.execute("PF('editarFuncionarioYaTieneFamiliarAsociado').show()");
           }
           else
           {
              requestContext.execute("PF('seleccionarEditarFuncionario').hide()");
              this.funcionarioFamiliar=funcionarioEditar;        
              requestContext.update("formularioOtrosDatos"); 
           }
            
       }       

   }
    public void  buscarPorNombreFuncionario()
    {
        
        this.listaFuncionarios=usuarioEJB.busacarPorNombreFuncionario(this.nombreOApellidos.toLowerCase());
        
    }
    
    public void cambiarCargo()
    {        
        if(this.idCargo.toString().equals("1"))
        {
            this.listaUnidadAcademica=this.unidadAcademicaEJB.findBYFacultades();
        }
        else
        {
            this.listaUnidadAcademica=this.unidadAcademicaEJB.findAll();
        }        
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formularioOtrosDatos");
    }
    
    public void aceptarModificarDatosAcademicos()
    {
        int bandera=1;
        if(this.tipoUsuario.equals("Funcionario"))
        {
            this.usuario.setConyugeid(null);
            Cargo carid= cargoEJB.buscarPorId(this.idCargo).get(0);
            Unidadacademica uniid= unidadAcademicaEJB.buscarPorId(this.idUnidadAcademica).get(0);
            this.usuario.setCarid(carid);
            this.usuario.setUniid(uniid);
            this.usuarioEJB.edit(this.usuario);
            this.camposFuncionario=true;
            this.camposEstudiante=false;
            this.camposFamiliar=false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info. Campos otros datos actualizados.", ""));
        }
        if(this.tipoUsuario.equals("Familiar"))
        {
            if(this.usuarioEJB.buscarPorConyugeid(this.usuario.getUsuid()))
            {
                bandera=0;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se puede cambiar el funcionario tiene familiar asociado.", "No se puede cambiar el funcionario tiene familiar asociado."));
            }
            else 
            {
                if (this.funcionarioFamiliar != null) 
                {
                    this.usuario.setCarid(null);
                    this.usuario.setUniid(null);
                    this.usuario.setConyugeid(this.funcionarioFamiliar);
                    this.usuarioEJB.edit(this.usuario);
                    this.camposFuncionario = false;
                    this.camposEstudiante = false;
                    this.camposFamiliar = true;
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info. Campos otros datos actualizados.", "Info. Campos Datos Academicos actualizados."));
                } 
                else 
                {
                    bandera = 0;
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe Selecccionar un Funcionario.", "Debe Selecccionar un Funcionario."));
                }
            }
        }
        if(this.tipoUsuario.equals("Estudiante"))
        {
            if(this.usuarioEJB.buscarPorConyugeid(this.usuario.getUsuid()))
            {
                bandera=0;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se puede cambiar el funcionario tiene familiar asociado.", "No se puede cambiar el funcionario tiene familiar asociado."));
            }
            else 
            {
                this.usuario.setCarid(null);
                this.usuario.setConyugeid(null);
                Unidadacademica uniid= unidadAcademicaEJB.buscarPorId(this.idUnidadAcademica).get(0);
                this.usuario.setUniid(uniid);
                this.usuarioEJB.edit(this.usuario);
                this.camposFuncionario=false;
                this.camposEstudiante=true;
                this.camposFamiliar=false;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info. Campos otros datos actualizados.", ""));
            }
        }
        if (bandera == 1) 
        {           
            this.mostraUsuariosController.getListaUsuarios().remove(this.usuario);
            if (this.tipoUsuario.equals("Familiar")) 
            {
                if(this.mostraUsuariosController.isHabilitarFamiliares()==true)
                {
                    this.mostraUsuariosController.getListaUsuarios().add(this.usuario);

                }
            }
            if (this.tipoUsuario.equals("Estudiante")) 
            {
                if(this.mostraUsuariosController.isHabilitarEstudiantes()==true)
                {
                    this.mostraUsuariosController.getListaUsuarios().add(this.usuario);

                }
            }
            if (this.tipoUsuario.equals("Funcionario")) 
            {
                if(this.mostraUsuariosController.isHabilitarFuncionarios()==true)
                {
                    this.mostraUsuariosController.getListaUsuarios().add(this.usuario);

                }
            }
            
            
            this.aceptarCancelarModificarDatosAcademicos = false;
            this.modificarDatosAcademicos = true;
        }      
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formularioOtrosDatos"); 
        requestContext.update("tablasUsuarios"); 
        requestContext.update("formularioTituloVerEditar");      
    }
    
    public void cambiarTipo()
    {        
        if(this.tipoUsuario.equals("Funcionario"))
        {
            this.modificarDatosEstudiante=false;
            this.modificarDatosFamiliar=false;
            this.modificarDatosFuncionario=true;
            this.listaCargo=cargoEJB.findAll();
            this.listaUnidadAcademica=unidadAcademicaEJB.findBYFacultades();
            this.idCargo=Long.parseLong("1");            
        }
        else
        {
            if(this.tipoUsuario.equals("Estudiante"))
            {
                this.modificarDatosEstudiante=true;
                this.modificarDatosFamiliar=false;
                this.modificarDatosFuncionario=false;
                this.listaUnidadAcademica=unidadAcademicaEJB.findBYFacultades();                
            }
            else
            {
                this.modificarDatosEstudiante=false;
                this.modificarDatosFamiliar=true;
                this.modificarDatosFuncionario=false;
                this.funcionarioFamiliar=null;
                this.listaFuncionarios=usuarioEJB.buscarPorUsuariosConCargo();
            }
        }        
        RequestContext requestContext = RequestContext.getCurrentInstance();        
        requestContext.update("formularioOtrosDatos");
        requestContext.update("ventanaSeleccionarFuncionario"); 
    }
    
}
