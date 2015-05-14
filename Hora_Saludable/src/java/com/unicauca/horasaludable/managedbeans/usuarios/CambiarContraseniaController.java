/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.managedbeans.usuarios;

import com.unicauca.horasaludable.cifrado.Cifrar;
import com.unicauca.horasaludable.entities.Recuperarcontrasena;
import com.unicauca.horasaludable.entities.Usuario;
import com.unicauca.horasaludable.jpacontrollers.RecuperarcontrasenaFacade;
import com.unicauca.horasaludable.jpacontrollers.UsuarioFacade;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author GORDA
 */
@ManagedBean
@RequestScoped
public class CambiarContraseniaController {

    @EJB
    private UsuarioFacade ejbUsuario;
    @EJB
    private RecuperarcontrasenaFacade ejbRecuperarContrasena;
    
    private String password1;
    private String password2;
    private String id;
    private Usuario usuario;
    private Recuperarcontrasena recuperarContrasena;
    private Cifrar cifrado;
                
    public CambiarContraseniaController() {
    }
    
    @PostConstruct
    private void init(){
       try{
           String url = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
           System.out.println("URL "+url);
            if(id == null){
            //    FacesContext.getCurrentInstance().getExternalContext().redirect("/Hora_Saludable/");
            }
       }catch(Exception e){}
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
        this.id = null;
        this.id = id;
         try{
            if(id != null){
                recuperarContrasena = ejbRecuperarContrasena.buscarRecuperarContrasenaCifrado(id);
                if(recuperarContrasena == null){
                    FacesContext.getCurrentInstance().getExternalContext().redirect("/Hora_Saludable/");
                }
            }else{
                FacesContext.getCurrentInstance().getExternalContext().redirect("/Hora_Saludable/");
            }
        }catch(Exception e){
        }
    }
    
     public void validateContrasena(FacesContext arg0, UIComponent arg1, Object arg2)throws ValidatorException {
      
        this.password1=String.valueOf(arg2);
    }
    
   public void validateRepitaContrasena(FacesContext arg0, UIComponent arg1, Object arg2)throws ValidatorException 
   {
      String texto = String.valueOf(arg2);      
      if (!(texto.equals(this.password1))) {
         throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,"","Las Contraseñas no Coinciden."));
          
      }
      
   } 
    public void cambiarContrasenia(){
        if(!id.isEmpty()){
           try{
           recuperarContrasena = ejbRecuperarContrasena.buscarRecuperarContrasenaCifrado(id);
           if(recuperarContrasena != null){
                List<Usuario> usuarios = ejbUsuario.buscarPorIdUsuario((recuperarContrasena.getReid()));
                if(!usuarios.isEmpty()){
                     usuario = usuarios.get(0);
                     this.usuario.setUsucontrasena(cifrado.sha256(this.password2));
                     this.ejbUsuario.edit(this.usuario);  
                     ejbRecuperarContrasena.remove(recuperarContrasena);
                     FacesContext.getCurrentInstance().getExternalContext().redirect("/Hora_Saludable/faces/usuario/recuperarcontrasenia/confirmacionCambioContrasenia.xhtml");
                 }

           }
           
           }catch(Exception e){
           }
           
        }
    }
    
}
