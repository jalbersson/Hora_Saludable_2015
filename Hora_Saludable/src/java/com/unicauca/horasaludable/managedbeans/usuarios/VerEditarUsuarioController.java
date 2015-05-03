package com.unicauca.horasaludable.managedbeans.usuarios;

import com.unicauca.horasaludable.cifrado.Cifrar;
import com.unicauca.horasaludable.entities.Usuario;
import com.unicauca.horasaludable.jpacontrollers.UsuarioFacade;
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
    private Usuario usuario;
    private boolean camposFuncionario;
    private boolean camposEstudiante;
    private boolean camposFamiliar;
    private boolean camposubidaFoto;
    private boolean campoFoto;
    private boolean campoNombre;    
    private boolean campoModificarNombre;
    private boolean campoIdentificacion;
    private boolean campoApellidos;    
    private boolean campoModificarApellidos;
    private boolean campoModificarIdentificacion;
    private boolean campoFechaNacimiento;   
    private boolean campoModificarFechaNacimiento;
    private boolean campoCorreo;    
    private boolean campoModificarCorreo;
    private boolean campotelefono;   
    private boolean campoModificarTefelfono;
    private boolean campoNombreUsuario;    
    private boolean campoModificarNombreUsuario;
    private boolean campoContrasena;
    private boolean campoModificarContrasena;
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
    
    private ValidarEdicionUsuarios validarEdicionUsuario;
    
       
    public VerEditarUsuarioController() 
    {
        this.rutaFoto="img/fotosUploads";
        this.rutaAbsolutaFotos="/home/geovanny/Documentos/Asae/Hora_Saludable_2015/Hora_Saludable/web/resources/img/fotosUploads/";
        this.sdf=new SimpleDateFormat("yyyy-MM-dd");
    }
    
    public boolean isCampoContrasena() 
    {
        return campoContrasena;
    }

    public void setCampoContrasena(boolean campoContrasena) 
    {
        this.campoContrasena = campoContrasena;
    }

    public boolean isCampoModificarContrasena() 
    {
        return campoModificarContrasena;
    }

    public void setCampoModificarContrasena(boolean campoModificarContrasena)
    {
        this.campoModificarContrasena = campoModificarContrasena;
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

    public boolean isCampoModificarNombreUsuario() 
    {
        return campoModificarNombreUsuario;
    }

    public void setCampoModificarNombreUsuario(boolean campoModificarNombreUsuario) 
    {
        this.campoModificarNombreUsuario = campoModificarNombreUsuario;
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

    public boolean isCampoModificarTefelfono() 
    {
        return campoModificarTefelfono;
    }

    public void setCampoModificarTefelfono(boolean campoModificarTefelfono) 
    {
        this.campoModificarTefelfono = campoModificarTefelfono;
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

    public boolean isCampoModificarCorreo() 
    {
        return campoModificarCorreo;
    }

    public void setCampoModificarCorreo(boolean campoModificarCorreo)
    {
        this.campoModificarCorreo = campoModificarCorreo;
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

    public boolean isCampoModificarFechaNacimiento() 
    {
        return campoModificarFechaNacimiento;
    }

    public void setCampoModificarFechaNacimiento(boolean campoModificarFechaNacimiento) 
    {
        this.campoModificarFechaNacimiento = campoModificarFechaNacimiento;
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

    public boolean isCampoModificarApellidos() 
    {
        return campoModificarApellidos;
    }

    public void setCampoModificarApellidos(boolean campoModificarApellidos) 
    {
        this.campoModificarApellidos = campoModificarApellidos;
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

    public boolean isCampoModificarIdentificacion() 
    {
        return campoModificarIdentificacion;
    }

    public void setCampoModificarIdentificacion(boolean campoModificarIdentificacion)
    {
        this.campoModificarIdentificacion = campoModificarIdentificacion;
    }
    
    public boolean isCampoNombre() 
    {
        return campoNombre;
    }

    public void setCampoNombre(boolean campoNombre)
    {
        this.campoNombre = campoNombre;
    }

    public boolean isCampoModificarNombre()
    {
        return campoModificarNombre;
    }

    public void setCampoModificarNombre(boolean campoModificarNombre) 
    {
        this.campoModificarNombre = campoModificarNombre;
    }
    
    public boolean isCamposubidaFoto()
    {
        return camposubidaFoto;
    }
    
    public boolean isCampoFoto() 
    {
        return campoFoto;
    }

    public void setCampoFoto(boolean campoFoto) 
    {
        this.campoFoto = campoFoto;
    }
    
    public void setCamposubidaFoto(boolean camposubidaFoto)
    {
        this.camposubidaFoto = camposubidaFoto;
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
    
    public void funcionarioSeleccionado(Usuario funcionario)
    {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.usuario=funcionario; 
        this.camposFuncionario=true;
        this.campoFoto=true;
        this.campoNombre=true;
        this.campoIdentificacion=true;
        this.campoApellidos=true;
        this.campoFechaNacimiento=true;
        this.campoCorreo=true;
        this.campotelefono=true;
        this.campoNombreUsuario=true;
        this.campoContrasena=true;
        
        this.camposEstudiante=false;
        this.camposFamiliar=false;        
        this.camposubidaFoto=false;
        this.campoModificarNombre=false;
        this.campoModificarIdentificacion=false;
        this.campoModificarApellidos=false;
        this.campoModificarFechaNacimiento=false;
        this.campoModificarCorreo=false;
        this.campoModificarTefelfono=false;
        this.campoModificarNombreUsuario=false;
        this.campoModificarContrasena=false;
        requestContext.update("formularioFoto");
        requestContext.update("formularioEditarFoto");
        requestContext.update("formularioDatosPersonales");
        requestContext.update("formularioDatosAcademia");
        requestContext.execute("PF('verEditarUsuario').show()");               
        
    }
    
    public void estudianteSeleccionado(Usuario estudiante)
    {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.usuario=estudiante; 
        this.camposEstudiante=true;
        this.campoFoto=true;
        this.campoNombre=true;
        this.campoIdentificacion=true;
        this.campoApellidos=true;
        this.campoFechaNacimiento=true;
        this.campoCorreo=true;
        this.campotelefono=true;
        this.campoNombreUsuario=true;
        this.campoContrasena=true;
        
        this.camposFuncionario=false;
        this.camposFamiliar=false;        
        this.camposubidaFoto=false;
        this.campoModificarNombre=false;
        this.campoModificarIdentificacion=false;
        this.campoModificarApellidos=false;
        this.campoModificarFechaNacimiento=false;
        this.campoModificarCorreo=false;
        this.campoModificarTefelfono=false;
        this.campoModificarNombreUsuario=false;
        this.campoModificarContrasena=false;
        requestContext.update("formularioFoto");
        requestContext.update("formularioEditarFoto");
        requestContext.update("formularioDatosPersonales");
        requestContext.update("formularioDatosAcademia");
        requestContext.execute("PF('verEditarUsuario').show()");               
        
    }
    
    public void familiarSeleccionado(Usuario familiar)
    {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.usuario=familiar; 
        this.camposFamiliar=true;
        this.campoFoto=true;
        this.campoNombre=true;
        this.campoIdentificacion=true;
        this.campoApellidos=true;
        this.campoFechaNacimiento=true;
        this.campoCorreo=true;
        this.campotelefono=true;
        this.campoNombreUsuario=true;
        this.campoContrasena=true;
        
        this.camposFuncionario=false;
        this.camposEstudiante=false;        
        this.camposubidaFoto=false;
        this.campoModificarNombre=false;
        this.campoModificarIdentificacion=false;
        this.campoModificarApellidos=false;
        this.campoModificarFechaNacimiento=false;
        this.campoModificarCorreo=false;
        this.campoModificarTefelfono=false;
        this.campoModificarNombreUsuario=false;
        this.campoModificarContrasena=false;
        requestContext.update("formularioFoto");
        requestContext.update("formularioEditarFoto");
        requestContext.update("formularioDatosPersonales");
        requestContext.update("formularioDatosAcademia");
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
            this.camposubidaFoto=false;            
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
        this.camposubidaFoto=false;
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
        this.camposubidaFoto=true;
        requestContext.update("formularioFoto");
        requestContext.update("formularioEditarFoto"); 
    }
    
    public void mostrarModifiarNombre()
    {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.campoNombre=false;
        this.campoModificarNombre=true;
        this.nombres=this.usuario.getUsunombres();
        requestContext.update("formularioDatosPersonales");
    }
    
    public void cancelarActualizarNombre()
    {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.campoNombre=true;
        this.campoModificarNombre=false;
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
            this.campoModificarNombre=false;
            this.usuario.setUsunombres(nombres);
            this.usuarioEJB.edit(this.usuario);            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info. Campo Nombre Actualizado.", ""));
        }
        requestContext.update("formularioDatosPersonales");        
    }
    
    public void mostrarModificarIdentificacion()
    {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.campoIdentificacion=false;
        this.campoModificarIdentificacion=true;
        this.identificacion=this.usuario.getUsuidentificacion()+"";
        requestContext.update("formularioDatosPersonales");
        
    }  
    
    public void cancelarActualizarIdentificacion()
    {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.campoIdentificacion=true;
        this.campoModificarIdentificacion=false;
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
            this.campoModificarIdentificacion=false;
            this.usuario.setUsuidentificacion(Long.parseLong(this.identificacion));
            this.usuarioEJB.edit(this.usuario);
            this.identificacion="";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info. Campo número de identificación Actualizado.", ""));
        }        
        requestContext.update("formularioDatosPersonales");
    }
    
    public void mostrarModificarApellidos()
    {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.campoApellidos=false;
        this.campoModificarApellidos=true;
        this.apellidos=this.usuario.getUsuapellidos();
        requestContext.update("formularioDatosPersonales");
    }
    
    public void cancelarActualizarApellidos()
    {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.campoApellidos=true;
        this.campoModificarApellidos=false;
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
            this.campoModificarApellidos=false;
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
        this.campoModificarFechaNacimiento=true;
        this.fechaNacimiento= new Date(this.usuario.getUsufechanacimiento().getYear(),this.usuario.getUsufechanacimiento().getMonth(),this.usuario.getUsufechanacimiento().getDate());
        requestContext.update("formularioDatosPersonales");        
    }
    
    public void cancelarActualizarFechaNacimiento()
    {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.campoFechaNacimiento=true;
        this.campoModificarFechaNacimiento=false;
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
            this.campoModificarFechaNacimiento=false;
            this.usuario.setUsufechanacimiento(this.fechaNacimiento);
            this.usuarioEJB.edit(this.usuario);            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info. Campo fecha nacimiento actualizado.", ""));
        }
        requestContext.update("formularioDatosPersonales");
    }
    
    public void mostrarModificarCorreo()
    {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.campoCorreo=false;
        this.campoModificarCorreo=true;
        this.correo=this.usuario.getUsuemail();
        requestContext.update("formularioDatosPersonales");
    }
    
    public void cancelarActualizarCorreo()
    {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.campoCorreo=true;
        this.campoModificarCorreo=false;
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
            this.campoModificarCorreo=false;
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
        this.campoModificarTefelfono=true;
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
        this.campoModificarTefelfono=false;
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
            this.campoModificarTefelfono=false;
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
        this.campoModificarNombreUsuario=true;
        this.nombreUsuario=this.usuario.getUsunombreusuario();
        requestContext.update("formularioDatosPersonales");
    }
    
    public void cancelarActualizarNombreUsuario()
    {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.campoNombreUsuario=true;
        this.campoModificarNombreUsuario=false;
        this.nombreUsuario="";
        requestContext.update("formularioDatosPersonales");
    }
    
    public void actualizarNombreUsuario()
    {
        this.validarEdicionUsuario= new ValidarEdicionUsuarios();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        if(this.validarEdicionUsuario.validarNombreUsuario(this.nombreUsuario,this.usuarioEJB))
        {
            this.campoNombreUsuario=true;
            this.campoModificarNombreUsuario=false;
            this.usuario.setUsunombreusuario(this.nombreUsuario);
            this.usuarioEJB.edit(this.usuario);            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info. Campo nombre de usuario actualizado.", ""));
        }
        requestContext.update("formularioDatosPersonales");        
    }
    
    public void mostrarModificarContrasena()
    {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.campoContrasena=false;
        this.campoModificarContrasena=true;        
        requestContext.update("formularioDatosPersonales");
    }
    
    public void cancelarActualizarContrasena()
    {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.campoContrasena=true;
        this.campoModificarContrasena=false;
        this.contrasena="";
        requestContext.update("formularioDatosPersonales");
    }
    
    public void actualizarContrasena()
    {
        this.validarEdicionUsuario= new ValidarEdicionUsuarios();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        if(this.validarEdicionUsuario.validarContrasena(this.contrasena))
        {
            this.campoContrasena=true;
            this.campoModificarContrasena=false;
            this.usuario.setUsucontrasena(Cifrar.sha512(this.contrasena));
            this.usuarioEJB.edit(this.usuario);            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info. Campo contraseña actualizado.", ""));
        }
        requestContext.update("formularioDatosPersonales");        
    }
}
