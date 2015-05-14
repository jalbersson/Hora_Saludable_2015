/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.managedbeans.asistencia;

import static com.unicauca.horasaludable.managedbeans.asistencia.AuxiliarReporte.tipo;
import java.io.Serializable;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author seven
 */
@ManagedBean
@ViewScoped
public class BtnGenerarBean implements Serializable {

    public boolean mostrarReporte = false;

    /**
     * Creates a new instance of BtnGenerarBean
     */
    public BtnGenerarBean() {
    }

    public void btnGenerarReporte(ActionEvent event) {

        mostrarReporte = true;

        AuxiliarReporte.tipo = (String) event.getComponent().getAttributes().get("tipo");
        AuxiliarReporte.anio = (Integer) event.getComponent().getAttributes().get("anio");
        AuxiliarReporte.perido = (String) event.getComponent().getAttributes().get("perido");
        AuxiliarReporte.mes = (String) event.getComponent().getAttributes().get("mes");
        AuxiliarReporte.fechaIncio = (Date) event.getComponent().getAttributes().get("fechaIncio");
        AuxiliarReporte.fechaFin = (Date) event.getComponent().getAttributes().get("fechaFin");
        
//        System.out.println("***************************"+AuxiliarReporte.tipo);
//        System.out.println("***************************"+AuxiliarReporte.anio);
//        System.out.println("***************************"+AuxiliarReporte.perido);
//        System.out.println("***************************"+AuxiliarReporte.mes);
//        System.out.println("***************************"+AuxiliarReporte.fechaIncio);
//        System.out.println("***************************"+AuxiliarReporte.fechaFin);
        
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        try {
            context.redirect("ReporteAsistenciaGeneral.xhtml");
        } catch (Exception ex) {
            
        }
    }

    public boolean isMostrarReporte() {
        return mostrarReporte;
    }

    public void setMostrarReporte(boolean mostrarReporte) {
        this.mostrarReporte = mostrarReporte;
    }

}
