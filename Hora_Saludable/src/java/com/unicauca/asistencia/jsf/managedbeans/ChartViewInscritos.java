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

/**
 *
 * @author seven
 */
@ManagedBean
@NoneScoped
public class ChartViewInscritos {
    private BarChartModel barModel;

    @PostConstruct
    public void init() {
        createBarModel();
    }

    private void createBarModel() {
        barModel = initBarModel();

        barModel.setTitle("Asistencia Inscritos por Mes");
        barModel.setLegendPosition("ne");

        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Mes");
        xAxis.setTickAngle(-50);

        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Inscritos");
        yAxis.setMin(0);
        yAxis.setMax(75);        
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
        asistencia.setLabel("Número de Participantes");
        asistencia.set("ENERO", 47);
        asistencia.set("FEBRERO", 54);
        asistencia.set("MARZO", 63);
        asistencia.set("ABRIL", 56);
        asistencia.set("MAYO", 65);
        asistencia.set("JUNIO", 71);
        asistencia.set("JULIO", 44);
        asistencia.set("AGOSTO", 59);
        asistencia.set("SEPTIEMBRE", 56);
        asistencia.set("OCTUBRE", 60);
        asistencia.set("NOVIEMBRE", 48);
        asistencia.set("DICIEMBRE", 30);
        
        model.addSeries(asistencia);

        return model;
    }
}
