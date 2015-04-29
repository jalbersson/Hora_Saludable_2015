package com.unicauca.horasaludable.validadores;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


@FacesValidator(value="ValidarCampoMaximo20Caracteres")
public class ValidarCampoMaximo20Caracteres implements Validator
{
   

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException 
    {
        String texto = String.valueOf(value);
        
        if(texto.length()>20)
        {
             FacesMessage msg= new FacesMessage(FacesMessage.SEVERITY_ERROR,"","Campo No Mas de 20 Caracteres.");
             throw new ValidatorException(msg);  
        }           
        
    }
}