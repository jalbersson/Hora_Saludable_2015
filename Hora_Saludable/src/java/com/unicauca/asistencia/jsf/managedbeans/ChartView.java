/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.asistencia.jsf.managedbeans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.NoneScoped;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.DateAxis;

/**
 *
 * @author seven
 */
@ManagedBean
@NoneScoped
public class ChartView {

    private BarChartModel barModel;

    @PostConstruct
    public void init() {
        createBarModel();
    }

    private void createBarModel() {
        barModel = initBarModel();

        barModel.setTitle("Asistencia Dependencias Universitarias");
        barModel.setLegendPosition("ne");

        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Dependencia Universitaria");
        xAxis.setTickAngle(-50);

        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Participantes");
        yAxis.setMin(0);
        yAxis.setMax(25);        
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }

    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();

        ChartSeries asistencia = new ChartSeries();
        asistencia.setLabel("Asitencia");
        asistencia.set("AREA COMERCIAL", 1);
        asistencia.set("ÁREA DE EDIFICIOS", 1);
        asistencia.set("ÁREA DE EQUIPOS", 1);
        asistencia.set("CECAV", 8);
        asistencia.set("CONTROL INTERNO", 2);
        asistencia.set("CONYUGE", 14);
        asistencia.set("CORRESPONDENCIA", 2);
        asistencia.set("DIV. BIBLIOTECA", 1);
        asistencia.set("DIV. COMUNICACIONES", 3);
        asistencia.set("DIV. DEP.Y REC.", 1);
        asistencia.set("DIV. TECNOLOGIAS", 2);
        asistencia.set("FAC. ARTES", 1);
        asistencia.set("FAC. DERECHO", 4);
        asistencia.set("FAC. EDUCACION", 21);
        asistencia.set("FAC. ELECTRONICA", 7);
        asistencia.set("FAC. HUMANIDADES", 3);
        asistencia.set("FAC. ING. CIVIL", 10);
        asistencia.set("FAC. MEDICINA", 2);
        asistencia.set("FINANCIERA", 1);
        asistencia.set("FONDUC", 1);
        asistencia.set("PENCIONADO", 1);
        asistencia.set("PLANEACION", 1);
        asistencia.set("REGIONALIZACION", 2);
        asistencia.set("SALUD INTEGRAL", 6);
        asistencia.set("SALUD OCUPACIONAL", 2);
        asistencia.set("T.EDITORIAL", 2);
        asistencia.set("UNIDAD DE SALUD", 2);
        asistencia.set("VIC. ACADEMICA", 1);
        asistencia.set("VIC. ADMINISTRATIVA", 2);
        asistencia.set("VIC. INVESTIGACIONES", 1);

        model.addSeries(asistencia);

        return model;
    }
    
}
