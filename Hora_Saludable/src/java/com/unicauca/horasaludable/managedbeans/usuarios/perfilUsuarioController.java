/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author geovanny
 */
@ManagedBean
@ViewScoped
public class perfilUsuarioController implements Serializable
{
    @EJB
    private UsuarioFacade usuarioEJB;
    private String rutaFoto;
    private Usuario usuario;
    private SimpleDateFormat sdf;
    private String sexo;
    private String tipo;
    private String rutaAbsolutaFotos;
    private UploadedFile foto;    
    private boolean estudiante;    
    private boolean funcionario;
    private boolean familiar;
    private boolean mostrarFoto;    
    private boolean mostrarTelefono;    
    private boolean mostrarContrasena;      
    private String telefono;
    private String contrasena;
    private String confirmarContrasena;   
    
    
    private ValidarEdicionUsuarios validarEdicionUsuario;
      
    
    public perfilUsuarioController() 
    {
        
        rutaFoto="img/fotosUploads";
        String OS = System.getProperty("os.name").toLowerCase();
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String realPath = (String) servletContext.getRealPath("/"); 
        if(OS.contains("nux"))
        {
           this.rutaAbsolutaFotos=realPath.replace("build/", "")+"resources/img/fotosUploads/"; 
        }
        else
        {
            this.rutaAbsolutaFotos=realPath.replace("build\\", "")+"resources\\img\\fotosUploads\\";
        }
               
        
        this.sdf=new SimpleDateFormat("yyyy-MM-dd");
        
        
        
        
        
    }
    
    @PostConstruct
    private void init()
    {        
        this.buscarUsuario();
        this.definirSexo();
        this.definirTipo();
        this.inicializarCampos();
        
    }
    
    private void buscarUsuario()
    {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest req = (HttpServletRequest) fc.getExternalContext().getRequest();       
        if (req.getUserPrincipal() != null)
        {
            this.usuario=this.usuarioEJB.retornarBuscarPorNombreUsuario(req.getUserPrincipal().getName()).get(0);
        }
    }
    
    public String getContrasena() 
    {
        return contrasena;
    }

    public void setContrasena(String contrasena) 
    {
        this.contrasena = contrasena;
    }

    public String getConfirmarContrasena() 
    {
        return confirmarContrasena;
    }

    public void setConfirmarContrasena(String confirmarContrasena) 
    {
        this.confirmarContrasena = confirmarContrasena;
    }
    
    public boolean isMostrarContrasena()
    {
        return mostrarContrasena;
    }

    public void setMostrarContrasena(boolean mostrarContrasena)
    {
        this.mostrarContrasena = mostrarContrasena;
    }    
    
    public String getTelefono() 
    {
        return telefono;
    }

    public void setTelefono(String telefono) 
    {
        this.telefono = telefono;
    }
    
    public boolean isMostrarTelefono()
    {
        return mostrarTelefono;
    }

    public void setMostrarTelefono(boolean mostrarTelefono) 
    {
        this.mostrarTelefono = mostrarTelefono;
    }    
    
    public UploadedFile getFoto() 
    {
        return foto;
    }

    public void setFoto(UploadedFile foto)
    {
        this.foto = foto;
    }
    
    public boolean isMostrarFoto() 
    {
        return mostrarFoto;
    }

    public void setMostrarFoto(boolean mostrarFoto) 
    {
        this.mostrarFoto = mostrarFoto;
    }    
    
    public boolean isEstudiante() 
    {
        return estudiante;
    }

    public void setEstudiante(boolean estudiante) 
    {
        this.estudiante = estudiante;
    }

    public boolean isFuncionario() 
    {
        return funcionario;
    }

    public void setFuncionario(boolean funcionario)
    {
        this.funcionario = funcionario;
    }

    public boolean isFamiliar() 
    {
        return familiar;
    }

    public void setFamiliar(boolean familiar) 
    {
        this.familiar = familiar;
    }
    
    public String getTipo() 
    {
        return tipo;
    }

    public void setTipo(String tipo) 
    {
        this.tipo = tipo;
    }
    
    public String getSexo()
    {
        return sexo;
    }

    public void setSexo(String sexo)
    {
        this.sexo = sexo;
    }
    
    public SimpleDateFormat getSdf() 
    {
        return sdf;
    }

    public void setSdf(SimpleDateFormat sdf) 
    {
        this.sdf = sdf;
    }
    
    public Usuario getUsuario()
    {
        return usuario;
    }

    public void setUsuario(Usuario usuario)
    {
        this.usuario = usuario;
    }
    
    public String getRutaFoto() 
    {
        return rutaFoto;
    }

    public void setRutaFoto(String rutaFoto) 
    {
        this.rutaFoto = rutaFoto;
    }
    
    private void definirSexo()
    {
        if(this.usuario.getUsugenero().equals("M"))
        {
            this.sexo="Masculino";
        }
        else
        {
            this.sexo="Femenino";
        }
    }
    
    private void definirTipo()
    {
        this.estudiante=false;
        this.familiar=false;
        this.funcionario=false;
        if(this.usuario.getUniid()!=null)
        {
            if(this.usuario.getCarid()!=null)
            {
                this.tipo="Funcionario";
                this.funcionario=true;
            }
            else
            {
                this.tipo="Estudiante";
                this.estudiante=true;
            }
        }
        else
        {
            if(this.usuario.getConyugeid()!=null)
            {
                this.tipo="Familiar";
                this.familiar=true;
            }
        }
    }
    
    private void inicializarCampos()
    {
        this.mostrarFoto=true;        
        this.mostrarTelefono=true;        
        this.mostrarContrasena=true;        
    }
    
    public void cargarFoto(FileUploadEvent event)
    {
        RequestContext requestContext = RequestContext.getCurrentInstance(); 
        this.foto=event.getFile();
        requestContext.update("formularioPerfilFotoUsuario");        
        
    }
    public void mostraSubirFoto()
    {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.mostrarFoto=false;        
        requestContext.update("formularioPerfilFotoUsuario");
        requestContext.update("formularioEditarFoto");
    }
    
    public void actualizarFoto() throws InterruptedException
    {       
        RequestContext requestContext = RequestContext.getCurrentInstance();        
        if(this.foto!=null)
        {
            this.mostrarFoto=true;                        
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
        requestContext.update("formularioPerfilFotoUsuario");
        requestContext.update("formularioEditarFoto");
    }    
    public void cancelarSubirFoto()
    {        
        RequestContext requestContext = RequestContext.getCurrentInstance(); 
        this.mostrarFoto=true;        
        this.foto=null;
        requestContext.update("formularioPerfilFotoUsuario");
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
    
    public void mostrarModificarTelefono()
    {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.mostrarTelefono=false;        
        if(this.usuario.getUsutelefono()!=null)
        {
            this.telefono=this.usuario.getUsutelefono()+"";
        }        
        requestContext.update("formularioPerfilDatosPersonales");
    }
    
    public void cancelarActualizarTelefono()
    {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.mostrarTelefono=true;        
        this.telefono="";
        requestContext.update("formularioPerfilDatosPersonales");
    }
    
    public void actualizarTelefono()
    {
        this.validarEdicionUsuario= new ValidarEdicionUsuarios();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        if(this.validarEdicionUsuario.validarTelefono(this.telefono))
        {
            this.mostrarTelefono=true;            
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
        requestContext.update("formularioPerfilDatosPersonales");        
    }
    
    public void mostrarModificarContrasena()
    {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.mostrarContrasena=false;             
        requestContext.update("formularioPerfilDatosCuenta");
    }
    public void cancelarActualizarContrasena()
    {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.mostrarContrasena=true;        
        this.contrasena="";
        this.confirmarContrasena="";
        requestContext.update("formularioPerfilDatosCuenta");
    }
    public void cambiarContrasena()
    {
        this.validarEdicionUsuario= new ValidarEdicionUsuarios();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        if(this.validarEdicionUsuario.validarContrasenaConConfirmacion(this.contrasena,this.confirmarContrasena))
        {
            this.usuario.setUsucontrasena(Cifrar.sha256(this.contrasena));
            this.usuarioEJB.edit(this.usuario);
            this.mostrarContrasena=true;            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info. Se cambio la contraseña correctamente.", ""));

        }
        requestContext.update("formularioPerfilDatosCuenta");
        
    }
    
}

