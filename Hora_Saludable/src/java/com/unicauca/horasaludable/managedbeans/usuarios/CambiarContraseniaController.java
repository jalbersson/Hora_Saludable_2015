/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.managedbeans.usuarios;

import com.unicauca.horasaludable.entities.Usuario;
import com.unicauca.horasaludable.jpacontrollers.UsuarioFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author GORDA
 */
@ManagedBean
@RequestScoped
public class CambiarContraseniaController {

    @EJB
    private UsuarioFacade ejbUsuario;
    private String password1;
    private String password2;
    private String id;
    private Usuario usuario;
            
    public CambiarContraseniaController() {
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    
    public void cambiarContrasenia(){
        if(id != null){
           try{
           List<Usuario> usuarios = ejbUsuario.buscarPorIdUsuario(Long.parseLong(id));
           if(!usuarios.isEmpty()){
                usuario = usuarios.get(0);
                //RequestContext requestContext = RequestContext.getCurrentInstance();
                this.usuario.setUsucontrasena(this.password2);
                this.ejbUsuario.edit(this.usuario);            
                //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info. Campo apellidos actualizado.", ""));
                //requestContext.update("formularioDatosPersonales");
                //requestContext.update("tablasUsuarios");   
                FacesContext.getCurrentInstance().getExternalContext().redirect("/Hora_Saludable/faces/usuario/recuperarcontrasenia/confirmacionCambioContrasenia.xhtml");
            }
           }catch(Exception e){
           }
        }
    }
    
}
