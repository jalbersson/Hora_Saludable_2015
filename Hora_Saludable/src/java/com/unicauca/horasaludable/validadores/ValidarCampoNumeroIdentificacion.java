package com.unicauca.horasaludable.validadores;

import com.unicauca.horasaludable.jpacontrollers.UsuarioFacade;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


@FacesValidator(value="ValidarCampoNumeroIdentificacion")
public class ValidarCampoNumeroIdentificacion implements Validator
{
    @EJB
    private UsuarioFacade usuarioEJB;

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException 
    {
        String texto = String.valueOf(value);        
        Long numeroIdentificacion=Long.MIN_VALUE;
        try
        {
            numeroIdentificacion= Long.parseLong(texto);
        }catch(Exception e)
        {
            
            FacesMessage msg= new FacesMessage(FacesMessage.SEVERITY_ERROR,"Campo Solo Puede Contener Numeros.","Campo Solo Puede Contener Numeros.");
            throw new ValidatorException(msg);
            
        }        
        if(texto.length()>15)
        {
            FacesMessage msg= new FacesMessage(FacesMessage.SEVERITY_ERROR,"Campo No Mas de 15 Numeros.","Campo No Mas de 15 Numeros.");
            throw new ValidatorException(msg); 
        }
        else
        {
            if(usuarioEJB.buscarPorNumeroIdentificacion(numeroIdentificacion))
            {
                FacesMessage msg= new FacesMessage(FacesMessage.SEVERITY_ERROR,"Número de Identificación ya se Encuentra Registrado.","Número de Identificación ya se Encuentra Registrado.");
                throw new ValidatorException(msg);  
            }  
        }        
        
    }
}