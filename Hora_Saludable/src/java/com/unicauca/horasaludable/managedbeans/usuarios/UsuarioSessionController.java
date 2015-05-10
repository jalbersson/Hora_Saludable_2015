package com.unicauca.horasaludable.managedbeans.usuarios;

import com.unicauca.horasaludable.jpacontrollers.UsuariogrupoFacade;
import java.io.IOException;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.ViewHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.context.RequestContext;

/**
 *
 * @author geovanny
 */
@ManagedBean
@SessionScoped
public class UsuarioSessionController implements Serializable
{
    @EJB
    private UsuariogrupoFacade usuarioGrupoEJB;
    String nombreDeUsuario;    
    String contrasena;
    
    
    public UsuarioSessionController()
    {
    }
    
    public String getNombreDeUsuario()
    {
        return nombreDeUsuario;
    }

    public void setNombreDeUsuario(String nombreDeUsuario) 
    {
        this.nombreDeUsuario = nombreDeUsuario;
    }

    public String getContrasena() 
    {
        return contrasena;
    }

    public void setContrasena(String contrasena)
    {
        this.contrasena = contrasena;
    }
       
    public void login()throws IOException, ServletException 
    {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest req = (HttpServletRequest) fc.getExternalContext().getRequest();        
        if (req.getUserPrincipal() == null) {
            try 
            {
                req.login(this.nombreDeUsuario, this.contrasena);
                req.getServletContext().log("Autenticacion exitosa");
                if(this.usuarioGrupoEJB.buscarPorNombreUsuario(req.getUserPrincipal().getName()).get(0).getUsuariogrupoPK().getGruid().equals("user"))
                {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("/Hora_Saludable");
                }
                else
                {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("/Hora_Saludable/faces/administrador/contenidos/principal.xhtml");
                }
            } 
            catch (ServletException e) 
            {
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nombre de usuario o contraseña incorrectos", "Nombre de usuario o contraseña incorrectos"));
                requestContext.update("formularioInicioSession");                
            }
        } 
        else 
        {
            req.getServletContext().log("El usuario ya estaba logueado:  ");
            requestContext.update("formularioInicioSession");
        }
    }
    
    public void logout() throws IOException, ServletException 
    {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest req = (HttpServletRequest) fc.getExternalContext().getRequest();
        try {
            req.logout();            
            req.getSession().invalidate();
            fc.getExternalContext().invalidateSession();
            FacesContext.getCurrentInstance().getExternalContext().redirect("/Hora_Saludable/");

        } catch (ServletException e) {            
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "FAILED", "Logout failed on backend"));            
        }
        
    }
    
    public void ventanaInicioSession()
    {
       RequestContext requestContext = RequestContext.getCurrentInstance();          
       FacesContext context = FacesContext.getCurrentInstance();
       Application application = context.getApplication();
       ViewHandler viewHandler = application.getViewHandler();
       UIViewRoot viewRoot = viewHandler.createView(context, context.getViewRoot().getViewId());
       context.setViewRoot(viewRoot);       
       context.renderResponse();
       this.contrasena=null;
       this.nombreDeUsuario=null;
       requestContext.update("formularioInicioSession");       
       requestContext.execute("PF('IniciarSesion').show()");
    }
    public boolean esusuarioSinSession()
    {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest req = (HttpServletRequest) fc.getExternalContext().getRequest();       
        if (req.getUserPrincipal() == null) 
        {
            return true;
        }
        return false;
    }
    
    public boolean esusuarioConSession()
    {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest req = (HttpServletRequest) fc.getExternalContext().getRequest();       
        if (req.getUserPrincipal() == null) 
        {
            return false;
            
        }
        else
        {
            if(this.usuarioGrupoEJB.buscarPorNombreUsuario(req.getUserPrincipal().getName()).get(0).getUsuariogrupoPK().getGruid().equals("user"))
            {
                return true;
            }
            return false;
        }
        
    }
    public boolean esAdministrador()
    {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest req = (HttpServletRequest) fc.getExternalContext().getRequest();       
        if (req.getUserPrincipal() == null) 
        {
            return false;
            
        }
        else
        {
            if(this.usuarioGrupoEJB.buscarPorNombreUsuario(req.getUserPrincipal().getName()).get(0).getUsuariogrupoPK().getGruid().equals("admin"))
            {
                return true;
            }
            return false;
        }
        
    }
    public String nombreUsuario()
    {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest req = (HttpServletRequest) fc.getExternalContext().getRequest();       
        if (req.getUserPrincipal() == null) 
        {
            return "";
        }
        else
        {
            return req.getUserPrincipal().getName();
        }
    }
}
