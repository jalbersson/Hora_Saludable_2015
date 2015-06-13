package com.unicauca.horasaludable.validadores;


import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


@FacesValidator(value="ValidarCampoNoCaracterNumericoInicio")
public class ValidarCampoNoCaracterNumericoInicio implements Validator
{

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
            FacesMessage msg= new FacesMessage(FacesMessage.SEVERITY_ERROR,"","Campo nombre usuario no puede comenzar con un número.");
            throw new ValidatorException(msg); 
        }
        

    }
    
    
    
    
    
}