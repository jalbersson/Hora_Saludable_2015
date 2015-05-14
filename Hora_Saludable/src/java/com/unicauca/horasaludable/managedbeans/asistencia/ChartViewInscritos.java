/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.managedbeans.asistencia;

import com.unicauca.horasaludable.jpacontrollers.DetalleasistenciaFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.NoneScoped;
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
public class ChartViewInscritos implements Serializable {

    private BarChartModel barModel;
    @EJB
    DetalleasistenciaFacade ejbFacadeDetAsi;

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
        yAxis.setLabel("Participantes");
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
        String[] meses = {
            "Enero",
            "Febrero",
            "Marzo",
            "Abril",
            "Mayo",
            "Junio",
            "Julio",
            "Agosto",
            "Septiembre",
            "Octubre",
            "Noviembre",
            "Diciembre"};

        if (AuxiliarReporte.tipo.equals("Seleccione")) {

        }
        if (AuxiliarReporte.tipo.equals("Anual")) {
            long[] asistentes = new long[12];
            for (int i = 0; i < 12; i++) {
                asistentes[i] = ejbFacadeDetAsi.asistenciaPorMes(i + 1, AuxiliarReporte.anio);
            }
            for (int i = 0; i < 12; i++) {
                asistencia.set(meses[i], asistentes[i]);
            }
        }
        if (AuxiliarReporte.tipo.equals("Semestral")) {

            if (AuxiliarReporte.perido.equals("Primero")) {
                long[] asistentes = new long[12];
                for (int i = 0; i < 6; i++) {
                    asistentes[i] = ejbFacadeDetAsi.asistenciaPorMes(i + 1, AuxiliarReporte.anio);
                }
                for (int i = 0; i < 6; i++) {
                    asistencia.set(meses[i], asistentes[i]);
                }
            }
            if (AuxiliarReporte.perido.equals("Segundo")) {

                long[] asistentes = new long[12];
                for (int i = 6; i < 12; i++) {
                    asistentes[i] = ejbFacadeDetAsi.asistenciaPorMes(i + 1, AuxiliarReporte.anio);
                }
                for (int i = 6; i < 12; i++) {
                    asistencia.set(meses[i], asistentes[i]);
                }
            }
        }
        if (AuxiliarReporte.tipo.equals("Mensual")) {
            int mes = 0;

            for (int i = 0; i < meses.length; i++) {
                if (AuxiliarReporte.mes.equals(meses[i])) {
                    mes = i + 1;
                    break;
                }
            }
            long numAsistentes = ejbFacadeDetAsi.asistenciaPorMes(mes, AuxiliarReporte.anio);

            asistencia.set(meses[mes - 1], numAsistentes);
        }
        if (AuxiliarReporte.tipo.equals("Por rango de fechas")) {
            int mesIncio = AuxiliarReporte.fechaIncio.getMonth();
            int mesFin = AuxiliarReporte.fechaFin.getMonth();
            
            int anioIncio = AuxiliarReporte.fechaIncio.getYear();
            int anioFin = AuxiliarReporte.fechaFin.getYear();

            if (anioIncio == anioFin) {
                if (mesIncio == mesFin) {
                    long numAsistentes = ejbFacadeDetAsi.asistenciaPorMes(mesIncio + 1, AuxiliarReporte.anio);
                    asistencia.set(meses[mesIncio], numAsistentes);
                }
                if (mesIncio > mesFin) {
                    for (int i = mesIncio+1; i <= mesFin+1; i++) {
                        long numAsistentes = ejbFacadeDetAsi.asistenciaPorMes(i, AuxiliarReporte.anio);
                        asistencia.set(meses[i], numAsistentes);
                    }
                }
                if (mesIncio < mesFin) {
                    for (int i = mesFin+1; i <= mesIncio+1; i++) {
                        long numAsistentes = ejbFacadeDetAsi.asistenciaPorMes(i, AuxiliarReporte.anio);
                        asistencia.set(meses[i], numAsistentes);
                    }
                }
            }
        }

        model.addSeries(asistencia);

        return model;
    }
}
