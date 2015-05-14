/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.managedbeans.asistencia;

import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

/**
 *
 * @author seven
 */
@ManagedBean
@ViewScoped
public class AsiAuxBean {

    /**
     * Creates a new instance of AsiAuxBean
     */
    public AsiAuxBean() {
    }
    public void generarReporte(ActionEvent event) //guardar informacion del trabajo de grado que se esta tratando
    {
        //Agregamos los datos del trabajo de grado para no enviar por url.                          
        AuxiliarReporte.tipo = (String) event.getComponent().getAttributes().get("tipo");
        AuxiliarReporte.anio = (int) event.getComponent().getAttributes().get("anio");
        AuxiliarReporte.perido = (String) event.getComponent().getAttributes().get("perido");;
        AuxiliarReporte.mes = (String) event.getComponent().getAttributes().get("mes");;
        AuxiliarReporte.fechaIncio = (Date) event.getComponent().getAttributes().get("fechaIncio");;
        AuxiliarReporte.fechaFin = (Date) event.getComponent().getAttributes().get("fechaFin");
    }
}
