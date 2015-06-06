/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.managedbeans.asistencia;

import com.unicauca.horasaludable.jpacontrollers.DetalleasistenciaFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

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

    }

    @PostConstruct
    public void init() {
        fechaIncio = new Date();
        fechaFin = new Date();

        Date date = new Date();
        anio = 1900 + date.getYear();
    }

    public List<String> getTipos() {
        List<String> tipos = new ArrayList();
        tipos.add("Anual");
        tipos.add("Semestral");
        tipos.add("Mensual");
//        tipos.add("Por rango de fechas");
        return tipos;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }
        
    public List<String> getPeriodos() {
        List<String> periodos = new ArrayList();
        periodos.add("Primero");
        periodos.add("Segundo");
        return periodos;
    }

    public List<String> getMeses() {
        List<String> meses = new ArrayList();
        meses.add("Enero");
        meses.add("Febrero");
        meses.add("Marzo");
        meses.add("Abril");
        meses.add("Mayo");
        meses.add("Junio");
        meses.add("Julio");
        meses.add("Agosto");
        meses.add("Septiembre");
        meses.add("Octubre");
        meses.add("Noviembre");
        meses.add("Diciembre");
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
        tipo = e.getNewValue().toString();
        if (tipo.equals("Seleccione")) {
            anioRow = false;
            periodoRow = false;
            mesRow = false;
            rangoRow = false;
            disabledButton = true;
            return;
        }
        disabledButton = false;
        if (tipo.equals("Anual")) {
            anioRow = true;
            periodoRow = false;
            mesRow = false;
            rangoRow = false;
        }
        if (tipo.equals("Semestral")) {
            anioRow = true;
            periodoRow = true;
            mesRow = false;
            rangoRow = false;
        }
        if (tipo.equals("Mensual")) {
            anioRow = true;
            periodoRow = false;
            mesRow = true;
            rangoRow = false;
        }
        if (tipo.equals("Por rango de fechas")) {
            anioRow = false;
            periodoRow = false;
            mesRow = false;
            rangoRow = true;
        }
    }

    public void generarReporte(ValueChangeEvent e) {
        String generar = e.getNewValue().toString();
        if (generar.equals("Si")) {
            AuxiliarReporte.tipo = tipo;
            AuxiliarReporte.anio = anio;
            AuxiliarReporte.perido = perido;
            AuxiliarReporte.mes = mes;
            AuxiliarReporte.fechaIncio = fechaIncio;
            AuxiliarReporte.fechaFin = fechaFin;
            mostrarReporte = true;

        }
        if (generar.equals("No")) {
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
