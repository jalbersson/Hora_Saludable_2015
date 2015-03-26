/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.asistenciaManagedbeans;

import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author seven
 */
@ManagedBean
@ViewScoped
public class AsistenciaBean {
    private Date fecha;
    /**
     * Creates a new instance of AsistenciaBean
     */
    public AsistenciaBean() {
    }

    public Date getFecha() {
        return new Date();
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
}
