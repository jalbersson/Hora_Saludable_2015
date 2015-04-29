
package com.unicauca.horasaludable.validadores;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


@FacesValidator(value="ValidarCamposSeleccionar")
public class ValidarCamposSeleccionar implements Validator
{
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException 
    {
        
        String texto = String.valueOf(value);
        
        if(texto.equals("0"))
        {
           FacesMessage msg= new FacesMessage(FacesMessage.SEVERITY_ERROR,"","Debe Seleccionar Una Opcion.");
           throw new ValidatorException(msg);  
        }       
        

    }
}
