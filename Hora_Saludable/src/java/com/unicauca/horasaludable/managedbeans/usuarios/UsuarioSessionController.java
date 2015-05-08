package com.unicauca.horasaludable.managedbeans.usuarios;

import java.io.Serializable;
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
    
    public String login() {

        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest req = (HttpServletRequest) fc.getExternalContext().getRequest();
        //only login if not already logged in...
        
        if (req.getUserPrincipal() == null) {
            try {
                req.login(this.nombreDeUsuario, this.contrasena);
                req.getServletContext().log("Autenticacion exitosa");
            } catch (ServletException e) 
            {
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "FAILED", "Authentication failed"));
                return "";
            }
        } else {
            req.getServletContext().log("El usuario ya estaba logueado:  ");
        }
        //read the user data from db and return to caller
        fc.addMessage(null, new FacesMessage("SUCCESS"));
        return "/index";
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
        return true;
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
