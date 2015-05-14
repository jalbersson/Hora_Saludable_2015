/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.managedbeans.asistencia;

import com.unicauca.horasaludable.jpacontrollers.DetalleasistenciaFacade;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
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
public class AsistenciaGeneralBean {

    private String[] meses = {
        "Enero",
        "Febrero",
        "Marzo",
        "Abril",
        "Mayo",
        "Junio",
        "Julio",
        "Agosto",
        "Septiembre",
        "Otubre",
        "Noviembre",
        "Diciembre"};
    /**
     * Creates a new instance of AsistenciaGeneralBean
     */
    @EJB
    DetalleasistenciaFacade ejbFacadeDetAsi;

    private BarChartModel barModel;
    private ReporteView tipoReporte;

    public AsistenciaGeneralBean() {
    }
    
    @PostConstruct
    public void init() {
        generarReporte();
    }

    public long getAsistentes() {
        return ejbFacadeDetAsi.asistenciaPorMes(1, 2015);
    }

    public BarChartModel getBarModel() {
        return this.barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }

    public BarChartModel generarReporte() {
        FacesContext context = FacesContext.getCurrentInstance();
        ReporteView reporte = (ReporteView) context.getApplication().evaluateExpressionGet(context, "#{ReporteView}", ReporteView.class);

        if (reporte.getTipo().equals("seleccione")) {

        }
        if (reporte.getTipo().equals("anual")) {

        }
        if (reporte.getTipo().equals("semestral")) {

        }
        if (reporte.getTipo().equals("mensual")) {

        }
        if (reporte.getTipo().equals("por rango de fechas")) {

        }
        barModel = reporteAsiGeneralAnual(reporte.getAnio());

        barModel.setTitle("Asistencia al programa Hora Saludable por mes");
        barModel.setLegendPosition("ne");

        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Mes");
        xAxis.setTickAngle(-50);

        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Asistentes");
        yAxis.setMin(0);
        yAxis.setMax(25);
        return barModel;
    }

    public BarChartModel reporteAsiGeneralAnual(int anio) {
        long[] asistentes = new long[12];

        for (int i = 0; i < 12; i++) {
            asistentes[i] = ejbFacadeDetAsi.asistenciaPorMes(i + 1, anio);
        }

        BarChartModel model = new BarChartModel();
        ChartSeries asistencia = new ChartSeries();
        asistencia.setLabel("Asistentes");

//        for (int i = 0; i < 12; i++) {
//            asistencia.set(meses[i], asistentes[i]);
//        }
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

    public void reporteAsiGeneralSemestralI() {
    }

    public void reporteAsiGeneralSemestralII() {
    }

    public void reporteAsiGeneralMensual() {
    }
}
