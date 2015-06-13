package com.unicauca.horasaludable.validadores;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


@FacesValidator(value="ValidarCampoMinimo3Caracteres")
public class ValidarCampoMinimo3Caracteres implements Validator
{
   

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException 
    {
        String texto = String.valueOf(value);
        
        if(texto.length()<3)
        {
             FacesMessage msg= new FacesMessage(FacesMessage.SEVERITY_ERROR,"Nombre de usuario no menos de 3 caracteres.","Nombre de usuario no menos de 3 caracteres.");
             throw new ValidatorException(msg);  
        }           
        
    }
}
