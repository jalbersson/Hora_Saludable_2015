
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
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Campo número de identificación obligatorio.", "Campo número de identificación obligatorio."));
            return false;
        }
        else
        {
            try {
                Long numero = Long.parseLong(numeroIdentificacion);
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Campo número de identificación solo puede contener números.", "Campo número de identificación solo puede contener números."));
                return false;

            }
            if (numeroIdentificacion.length() > 15) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Campo número de identificación no mas de 15 números.", "Campo número de identificación no mas de 15 números."));
                return false;
            } else {
                Long numeroide = Long.parseLong(numeroIdentificacion);
                if (usuariosEJB.buscarPorNumeroIdentificacion(numeroide)) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Número de identificación ya se encuentra registrado.", "Número de identificación ya se encuentra registrado."));
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
            Pattern patron = Pattern.compile("[^A-Za-z ñÑáéíóú]");
            Matcher encaja = patron.matcher(nombre);
            if (encaja.find()) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Campo nombre solo letras y espacios.", "Campo nombre solo letras y espacios"));
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
            Pattern patron = Pattern.compile("[^A-Za-z ñÑáéíóú]");
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
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Campo fecha de nacimiento es obligatorio.", "Campo fecha de nacimiento es obligatorio."));
           return false;
       }
       else
       {
           Date fechaActual=new Date();
           if(fecha.compareTo(fechaActual)>0)
           {
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Campo fecha de nacimiento no puede ser mayor a la fecha actual.", "Campo fecha de nacimiento no puede ser mayor a la fecha actual."));
               return false;
           }
           else 
           {
               return true;
           }
       }      
        
    }
    
    public boolean validarCorreo(String correo,UsuarioFacade usuariosEJB)
    {  
        if(correo.isEmpty())
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Campo correo electrónico obligatorio.", "Campo correo electrónico obligatorio."));
            return false;
        }
        else
        {                    
            Pattern patron = Pattern.compile("([_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,}))");
            Matcher encaja = patron.matcher(correo);        
            if(!encaja.find())
            {
                FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Formato de correo electrónico inválido.","Formato de correo electrónico inválido."));
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
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Campo teléfono solo puede contener números.", "Campo teléfono solo puede contener números."));
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
                        Pattern patron = Pattern.compile("[^A-Za-z_.ñÑ0-9]");
                        Matcher encaja = patron.matcher(nombreUsuario);        
                        if(encaja.find())
                        {
                            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Solo se permiten caracteres alfanúmericos, guiones bajos y puntos.","Solo se permiten caracteres alfanúmericos, guiones bajos y puntos."));
                            return false;
                        }
                        else
                        {
                            if(usuariosEJB.buscarPorNombreUsuario(nombreUsuario))
                            {
                                FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Nombre de usuario ya esta en uso.","Nombre de usuario ya esta en uso."));
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
    
    public boolean validarContrasenaConConfirmacion(String contrasena,String confirmarContrasena)
    {  
        if(confirmarContrasena.isEmpty() && contrasena.isEmpty())
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Campo contraseña obligatorio.", "Campo contraseña obligatorio."));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Campo confirmar contraseña obligatorio.", "Campo confirmar contraseña obligatorio."));
            return false;
        }
        if(contrasena.isEmpty())
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Campo contraseña obligatorio.", "Campo contraseña obligatorio."));
            return false;
        }
        else
        {
            if(confirmarContrasena.isEmpty())
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Campo confirmar contraseña obligatorio.", "Campo confirmar contraseña obligatorio."));
                return false;
            }
            else 
            {
                if (contrasena.length() < 6) 
                {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Campo contraseña minimo 6 caracteres.", "Campo contraseña minimo 6 caracteres."));
                    return false;
                } else 
                {
                    if (contrasena.length() > 20) 
                    {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Campo contraseña maximo 20 caracteres.", "Campo contraseña maximo 20 caracteres."));
                        return false;
                    }
                    else
                    {
                        if(!contrasena.equals(confirmarContrasena))
                        {
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Las contraseñas no coinciden.", "Las contraseñas no coinciden."));
                            return false;
                        }
                        return true;
                    }
                    
                }
            }
            
        }                    
    }
    
}
