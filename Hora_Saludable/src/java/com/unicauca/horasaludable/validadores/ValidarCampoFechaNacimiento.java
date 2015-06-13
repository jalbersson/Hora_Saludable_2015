/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.validadores;

import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author geovanny
 */
@FacesValidator(value="ValidarCampoFechaNacimiento")
public class ValidarCampoFechaNacimiento implements Validator
{

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
         Date fecha= (Date)value;
         if(!validarFechaNacimiento(fecha))
         {
            FacesMessage msg= new FacesMessage(FacesMessage.SEVERITY_ERROR,"Campo fecha de nacimiento no puede ser mayor a la fecha actual.","Campo fecha de nacimiento no puede ser mayor a la fecha actual.");
            throw new ValidatorException(msg);
         }
    }
    
    public boolean validarFechaNacimiento(Date fecha)
    {
       
           Date fechaActual=new Date();
           if(fecha.compareTo(fechaActual)>0)
           {
               return false;
           }
           else 
           {
               return true;
           }
           
        
    }
    
}
