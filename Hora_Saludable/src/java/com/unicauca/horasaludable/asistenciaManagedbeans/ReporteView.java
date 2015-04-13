/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.asistenciaManagedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author seven
 */
@ManagedBean
@ViewScoped
public class ReporteView implements Serializable {
    
    private String tipo; // anual semestral mensual 'por rango de fechas'
    private int anio;
    private String mes;
    private String perido;
    private Date fechaIncio;
    private Date fechaFin;
    
    private boolean anioRow = false;
    private boolean periodoRow = false;
    private boolean mesRow = false;
    private boolean rangoRow = false;
    private boolean disabledButton = true;
    private boolean mostrarReporte = false;
    /**
     * Creates a new instance of AsistenciaView
     */
    public ReporteView() {
        fechaIncio = new Date();
        fechaFin = new Date();
    }
    public List<String> getTipos() {
        List<String> tipos = new ArrayList();
        tipos.add("anual");
        tipos.add("semestral");
        tipos.add("mensual");
        tipos.add("por rango de fechas");
        return tipos;
    }
    public int getAnio() {
        Date date = new Date();        
        int varAnio = 1900+date.getYear();
        return varAnio;
    }
    public List<String> getPeriodos() {
        List<String> periodos = new ArrayList();
        periodos.add("primero");
        periodos.add("segundo");
        return periodos;
    }
    public List<String> getMeses() {
        List<String> meses = new ArrayList();
        meses.add("enero");
        meses.add("febrero");
        meses.add("marzo");
        meses.add("abril");
        meses.add("mayo");
        meses.add("junio");
        meses.add("julio");
        meses.add("agosto");
        meses.add("septiembre");
        meses.add("octubre");
        meses.add("noviembre");
        meses.add("diciembre");
        return meses;
        
    }
    //
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPerido() {
        return perido;
    }

    public void setPerido(String perido) {
        this.perido = perido;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public Date getFechaIncio() {
        return fechaIncio;
    }

    public void setFechaIncio(Date fechaIncio) {
        this.fechaIncio = fechaIncio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
    
    //
    public void seleccionarTipo(ValueChangeEvent e) {
        tipo=e.getNewValue().toString();
        if(tipo.equals("seleccione")) {
            anioRow = false;
            periodoRow = false;
            mesRow = false;
            rangoRow = false;
            disabledButton = true;
            return;
        }
        disabledButton = false;
        if(tipo.equals("anual")) {
            anioRow = true;
            periodoRow = false;
            mesRow = false;
            rangoRow = false;
        }            
        if(tipo.equals("semestral")) {
            anioRow = true;
            periodoRow = true;
            mesRow = false;
            rangoRow = false;            
        }
        if(tipo.equals("mensual")) {
            anioRow = true;
            periodoRow = false;
            mesRow = true;
            rangoRow = false;
        }
        if(tipo.equals("por rango de fechas")) {            
            anioRow = false;
            periodoRow = false;
            mesRow = false;
            rangoRow = true;            
        }
    }
    public void generarReporte(ValueChangeEvent e) {        
        tipo=e.getNewValue().toString();
        if(tipo.equals("Si")) {
            mostrarReporte = true;
            
        }        
        if(tipo.equals("No")) {
            mostrarReporte = false;
            
        }        
    }
    //
    public boolean isAnioRow() {
        return anioRow;
    }

    public boolean isPeriodoRow() {
        return periodoRow;
    }

    public boolean isMesRow() {
        return mesRow;
    }

    public boolean isRangoRow() {
        return rangoRow;
    }

    public boolean isDisabledButton() {
        return disabledButton;
    }

    public boolean isMostrarReporte() {
        return mostrarReporte;
    }
    
}
