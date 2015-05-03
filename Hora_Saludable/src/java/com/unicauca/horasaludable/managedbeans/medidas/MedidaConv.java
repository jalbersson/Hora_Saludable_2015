/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.managedbeans.medidas;


import com.unicauca.horasaludable.entities.Medida;
import java.math.BigDecimal;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author pcblanco
 */
@FacesConverter("medidaConv")
public class MedidaConv implements Converter {
 
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value != null && value.trim().length() > 0) {
            try {
                HistorialMedController hismed = (HistorialMedController) fc.getApplication().getELResolver().getValue(fc.getELContext(), null, "historialMedController");
                        /*fc.getExternalContext().getApplicationMap().get("formatoA");*/
                return hismed.buscarmed(new Long(value));
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid theme."));
            }
        }
        else {
            return null;
        }
    }
 
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
      if(object != null && object != "") {
            return String.valueOf(((Medida) object).getMedid().toString());
        }
        else {
            return null;
        }
    }   
}   