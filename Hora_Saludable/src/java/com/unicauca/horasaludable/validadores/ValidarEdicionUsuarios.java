
package com.unicauca.horasaludable.validadores;

import com.unicauca.horasaludable.jpacontrollers.UsuarioFacade;
import java.io.Serializable;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class ValidarEdicionUsuarios implements Serializable
{
    
        
    public  ValidarEdicionUsuarios()
    {
        
    }
    public boolean validarNumeroIdentificacion(String numeroIdentificacion,UsuarioFacade usuariosEJB)
    {  
        if(numeroIdentificacion.isEmpty())
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Campo Número de Identificación Obligatorio.", "Campo Número de Identificación Obligatorio."));
            return false;
        }
        else
        {
            try {
                Long numero = Long.parseLong(numeroIdentificacion);
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Campo numero de Identificacion Solo Puede Contener Numeros.", "Campo Solo Puede Contener Numeros."));
                return false;

            }
            if (numeroIdentificacion.length() > 15) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Campo numero de Identificacion No Mas de 15 Numeros.", "Campo No Mas de 15 Numeros."));
                return false;
            } else {
                Long numeroide = Long.parseLong(numeroIdentificacion);
                if (usuariosEJB.buscarPorNumeroIdentificacion(numeroide)) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Número de Identificación ya se Encuentra Registrado.", "Número de Identificación ya se Encuentra Registrado."));
                    return false;
                }
            }
            return true;
        }        
    }
    
    public boolean validarNombres(String nombre)
    {
       if(nombre.isEmpty())
       {
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Campo Nombre Obligatorio.", "Campo Nombre Obligatorio."));
           return false;
       }
       else 
       {
            Pattern patron = Pattern.compile("[^A-Za-z ñÑ]");
            Matcher encaja = patron.matcher(nombre);
            if (encaja.find()) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Campo Nombre Solo Letras y Espacios.", "Campo Nombre Solo Letras y Espacios."));
                return false;
            }
            return true;
       }
        
    }
    
    public boolean validarApellidos(String apellido)
    {
       if(apellido.isEmpty())
       {
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Campo apellidos obligatorio.", "Campo apellidos obligatorio."));
           return false;
       }
       else 
       {
            Pattern patron = Pattern.compile("[^A-Za-z ñÑ]");
            Matcher encaja = patron.matcher(apellido);
            if (encaja.find()) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Campo apellidos solo letras y espacios.", "Campo apellido solo letras y espacios."));
                return false;
            }
            return true;
       }
        
    }
    
    public boolean validarFechaNacimiento(Date fecha)
    {
       if(fecha==null)
       {
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Campo fecha naciemiento obligatorio.", "Campo fecha naciemiento obligatorio."));
           return false;
       }
       else 
       {
            return true;
       }
        
    }
    
    public boolean validarCorreo(String correo,UsuarioFacade usuariosEJB)
    {  
        if(correo.isEmpty())
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Campo correo electrónico Obligatorio.", "Campo correo electrónico Obligatorio."));
            return false;
        }
        else
        {                    
            Pattern patron = Pattern.compile("([_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,}))");
            Matcher encaja = patron.matcher(correo);        
            if(!encaja.find())
            {
                FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Formato de correo electrónico Invalido.","Formato de correo electrónico Invalido."));
                return false;
            }
            else
            {
              if(usuariosEJB.buscarPorEmail(correo))
              {
                  FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Correo electrónico ya se encuentra registrado.","Correo electrónico ya se encuentra registrado."));
                  return false;
              }
              return true;
            }
            
            
        }        
    }
    
    public boolean validarTelefono(String telefono)
    {  
        if(!telefono.isEmpty())
        {
            
            try
            {
                long campo= Long.parseLong(telefono);

            }catch(Exception e)
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Campo teléfono solo puede contener numeros.", "Campo teléfono solo puede contener números."));
                return false;
            }  
            
        }
        return true;             
    }
    
    public boolean validarNombreUsuario(String nombreUsuario,UsuarioFacade usuariosEJB)
    {  
        if(nombreUsuario.isEmpty())
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Campo nombre de usuario obligatorio.", "Campo nombre de usuario obligatorio."));
            return false;
        }
        else
        {                    
            String caracter=nombreUsuario.charAt(0)+"";
            int validacion=0;
            try
            {
               int numero= Integer.parseInt(caracter);


            }catch(Exception e)
            {
               validacion=1;
            }

            if(validacion==0)
            {
                FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Campo nombre usuario no puede comenzar con un número.","Campo nombre usuario no puede comenzar con un número.")); 
                return false;
            }
            else
            {
                if(nombreUsuario.length()>20)
                {
                    FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Nombre de usuario no mas de 20 caracteres.","Nombre de usuario no mas de 20 caracteres.")); 
                    return false;
                }
                else
                {
                    if(nombreUsuario.length()<3)
                    {
                        FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Nombre de usuario no menos de 3 caracteres.","Nombre de usuario no menos de 3 caracteres.")); 
                        return false;
                    }
                    else
                    {
                        Pattern patron = Pattern.compile("[^A-Za-z_.ñÑ]");
                        Matcher encaja = patron.matcher(nombreUsuario);        
                        if(encaja.find())
                        {
                            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Solo se permiten caracteres alfanumericos, guiones bajos y puntos.","Solo se permiten caracteres alfanumericos, guiones bajos y puntos."));
                            return false;
                        }
                        else
                        {
                            if(usuariosEJB.buscarPorNombreUsuario(nombreUsuario))
                            {
                                FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Nombre de usuario ya esta uso.","Nombre de usuario ya esta uso."));
                                return false;  
                            } 
                            return true;
                        }
                    }
                }
            }
        }        
    }
    public boolean validarContrasena(String contrasena)
    {  
        if(contrasena.isEmpty())
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Campo contraseña obligatorio.", "Campo contraseña obligatorio."));
            return false;
        }
        else
        {
            if(contrasena.length()<6)
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Campo contraseña minimo 6 caracteres.", "Campo contraseña minimo 6 caracteres."));
                return false;
            }
            else
            {
                if(contrasena.length()>20)
                {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Campo contraseña maximo 20 caracteres.", "Campo contraseña maximo 20 caracteres."));
                    return false;
                }                
                return true;
            }
        }                    
    }
    
}