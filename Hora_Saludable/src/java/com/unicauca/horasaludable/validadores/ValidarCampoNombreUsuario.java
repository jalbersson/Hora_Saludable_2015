package com.unicauca.horasaludable.validadores;

import com.unicauca.horasaludable.jpacontrollers.UsuarioFacade;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


@FacesValidator(value="ValidarCampoNombreUsuario")
public class ValidarCampoNombreUsuario implements Validator
{
    @EJB
    private UsuarioFacade usuarioEJB;

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException 
    {
        String texto = String.valueOf(value);
        String caracter=texto.charAt(0)+"";
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
            FacesMessage msg= new FacesMessage(FacesMessage.SEVERITY_ERROR,"Campo nombre de usuario no puede comenzar con un número.","Campo nombre de usuario no puede comenzar con un número.");
            throw new ValidatorException(msg); 
        }
        else
        {
            if(texto.length()>20)
            {
                FacesMessage msg= new FacesMessage(FacesMessage.SEVERITY_ERROR,"nombre de usuario no mas de 20 caracteres.","nombre de usuario no mas de 20 caracteres.");
                throw new ValidatorException(msg); 
            }
            else
            {
                if(texto.length()<3)
                {
                    FacesMessage msg= new FacesMessage(FacesMessage.SEVERITY_ERROR,"nombre de usuario no menos de 3 caracteres.","nombre de usuario no menos de 3 caracteres.");
                    throw new ValidatorException(msg);  
                }
                else
                {
                    Pattern patron = Pattern.compile("[^A-Za-z_.ñÑ0-9]");
                    Matcher encaja = patron.matcher(texto);        
                    if(encaja.find())
                    {
                        FacesMessage msg= new FacesMessage(FacesMessage.SEVERITY_ERROR,"Solo se permiten caracteres alfanúmericos, guiones bajos y puntos.","Solo se permiten caracteres alfanúmericos, guiones bajos y puntos.");
                        throw new ValidatorException(msg);
                    }
                    else
                    {
                        if(usuarioEJB.buscarPorNombreUsuario(texto))
                        {
                            FacesMessage msg= new FacesMessage(FacesMessage.SEVERITY_ERROR,"Nombre de usuario ya esta en uso.","Nombre de usuario ya esta en uso.");
                            throw new ValidatorException(msg);  
                        }  
                    }
                    
                }
                
            }
             
            
        }        
    }
}