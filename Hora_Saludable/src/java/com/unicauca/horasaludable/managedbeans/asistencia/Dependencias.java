/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.asistenciaManagedbeans;

import com.unicauca.horasaludable.jpacontrollers.DetalleasistenciaFacade;
import com.unicauca.horasaludable.jpacontrollers.UnidadacademicaFacade;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

/**
 *
 * @author Sarita Sofia
 */
@ManagedBean
@ViewScoped
public class Dependencias implements Serializable {

    @EJB
    private DetalleasistenciaFacade ejbFacadeDetAsi;
    @EJB
    private UnidadacademicaFacade unidadEJB;
    String anioSeleccionado;
    String mesSeleccionado;
    private boolean mostrarPoraño;
    private boolean mostrarPorMes;
    private boolean mes1;
    private boolean verReporte;
    private boolean reporteMensual;
    private BarChartModel barModel;
    private String tipo;
    private boolean comboAnio;
    private boolean comboMes;

    public Dependencias() {
        this.mostrarPoraño = false;
        this.comboAnio = false;
        this.comboMes = false;
        this.mostrarPorMes=false;
    }

    public boolean isVerReporte() {
        return verReporte;
    }

    public void setVerReporte(boolean verReporte) {
        this.verReporte = verReporte;
    }
    

    public boolean isReporteMensual() {
        return reporteMensual;
    }

    public void setReporteMensual(boolean reporteMensual) {
        this.reporteMensual = reporteMensual;
    }
    

    public boolean isMes1() {
        return mes1;
    }

    public void setMes1(boolean mes1) {
        this.mes1 = mes1;
    }
    
    
    public boolean isMostrarPorMes() {
        return mostrarPorMes;
    }

    public void setMostrarPorMes(boolean mostrarPorMes) {
        this.mostrarPorMes = mostrarPorMes;
    }

    public String getMesSeleccionado() {
        return mesSeleccionado;
    }

    public void setMesSeleccionado(String mesSeleccionado) {
        this.mesSeleccionado = mesSeleccionado;
    }

    public boolean isComboMes() {
        return comboMes;
    }

    public void setComboMes(boolean comboMes) {
        this.comboMes = comboMes;
    }

    public boolean isMostrarPoraño() {
        return mostrarPoraño;
    }

    public void setMostrarPoraño(boolean mostrarPoraño) {
        this.mostrarPoraño = mostrarPoraño;
    }

    public boolean isComboAnio() {
        return comboAnio;
    }

    public void setComboAnio(boolean comboAnio) {
        this.comboAnio = comboAnio;
    }

      public String getAnioSeleccionado() {
        return anioSeleccionado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setAnioSeleccionado(String anioSeleccionado) {
        this.anioSeleccionado = anioSeleccionado;
    }
    
    public BarChartModel asistenciaPorMes() {
        BarChartModel barmodel = new BarChartModel();
        ChartSeries asistenciaMes = new ChartSeries();
        asistenciaMes.setLabel("Asistencia por Mes");
        int aniio2 = Integer.parseInt(this.anioSeleccionado);
        aniio2 = aniio2 - 1900;
        String mes = this.mesSeleccionado;
        int valorMes = 0;
        if (mes.equals("Enero")) {
            valorMes = 0;
        }
        if (mes.equals("Febrero")) {
            valorMes = 1;
        }
        if (mes.equals("Marzo")) {
            valorMes = 2;
        }
        if (mes.equals("Abril")) {
            valorMes = 3;
        }
        if (mes.equals("Mayo")) {
            valorMes = 4;
        }
        if (mes.equals("Junio")) {
            valorMes = 5;
        }
        if (mes.equals("Julio")) {
            valorMes = 6;
        }
        if (mes.equals("Agosto")) {
            valorMes = 7;
        }
        if (mes.equals("Septiembre")) {
            valorMes = 8;
        }
        if (mes.equals("Octubre")) {
            valorMes = 9;
        }
        if (mes.equals("Noviembre")) {
            valorMes = 10;
        }
        if (mes.equals("Diciembre")) {
            valorMes = 11;
        }
        asistenciaMes.setLabel("Asistencia para el mes de: " + mes);
        Date mesInicial = new Date(aniio2,valorMes, 1, 0, 0, 0);
        Date mesFinal = new Date(aniio2,valorMes, 31, 24, 59, 59);
        List<Object[]> asistenciaanio = ejbFacadeDetAsi.detalle_dependencia(mesInicial, mesFinal);
        List<Object[]> unidadesAcademicas = unidadEJB.retornarUnidadesAcademicas();
        
        for (Object[] asis : asistenciaanio) {
            for (Object[] uni : unidadesAcademicas) {
                if (((String) asis[0]).equals(((String) uni[0]))) {
                    uni[1] = asis[1];
                }
            }
        }
        for (Object[] uni2 : unidadesAcademicas) {
            asistenciaMes.set(uni2[0], ((Long) uni2[1]));
        }
        barmodel.addSeries(asistenciaMes);

        barmodel.setTitle("Asistencia Dependencias Universitarias");
        barmodel.setLegendPosition("ne");

        Axis xAxis = barmodel.getAxis(AxisType.X);
        xAxis.setLabel("Dependencia Universitaria");
        xAxis.setTickAngle(-70);

        Axis yAxis = barmodel.getAxis(AxisType.Y);
        yAxis.setLabel("Participantes");

        

        return barmodel;
    }

    public BarChartModel asistenciaPorAnio() {
        BarChartModel barmodel = new BarChartModel();
        ChartSeries asistencia = new ChartSeries();
        
        int aniio = Integer.parseInt(this.anioSeleccionado);
        aniio = aniio - 1900;
        
        int anio_ = aniio + 1900;
        asistencia.setLabel("Asistencia para el año: " + anio_);
        
        Date fecha1 = new Date(aniio, 0, 1, 0, 0, 0);
        Date fecha2 = new Date(aniio, 11, 31, 24, 59, 59);
        List<Object[]> asistenciaanio = ejbFacadeDetAsi.detalle_dependencia(fecha1, fecha2);
        List<Object[]> unidadesAcademicas = unidadEJB.retornarUnidadesAcademicas();

        for (Object[] asis : asistenciaanio) {
            for (Object[] uni : unidadesAcademicas) {
                if (((String) asis[0]).equals(((String) uni[0]))) {
                    uni[1] = asis[1];
                }
            }
        }
        for (Object[] uni2 : unidadesAcademicas) {
            asistencia.set(uni2[0], ((Long) uni2[1]));
        }
        barmodel.addSeries(asistencia);

        barmodel.setTitle("Asistencia Dependencias Universitarias");
        barmodel.setLegendPosition("ne");

        Axis xAxis = barmodel.getAxis(AxisType.X);
        xAxis.setLabel("Dependencia Universitaria");
        xAxis.setTickAngle(-70);

        Axis yAxis = barmodel.getAxis(AxisType.Y);
        yAxis.setLabel("Participantes");

        return barmodel;
    }

  

   
    public void cambiarAño(ValueChangeEvent e) {
        String tip = e.getNewValue().toString();
        this.anioSeleccionado = tip;

        if (this.tipo.equals("Anual")) {
            if (this.anioSeleccionado.equals("NingunAño")) {
                this.mostrarPoraño = false;

            } else {
                this.mostrarPoraño = true;
                this.barModel = this.asistenciaPorAnio();

            }

        } else {
            if (this.anioSeleccionado.equals("NingunAño")) {
                this.mostrarPoraño = false;

            } else {
                this.comboMes = true;
                //this.barModel=this.asistenciaPorMes();
            }
        }

    }
    
     public void cambiarPorMes(ValueChangeEvent e)
    {
        String tip=e.getNewValue().toString();
        this.mesSeleccionado=tip;
        if (this.tipo.equals("Mensual")) {
            if (this.mesSeleccionado.equals("NingunMes")) {
                this.mostrarPorMes = false;

            } else {
                this.mostrarPorMes = true;
                this.barModel = this.asistenciaPorMes();

            }

        } else {
            if (this.mesSeleccionado.equals("NingunMes")) {
                this.mostrarPorMes = false;

            } 
     }
    }

    public void cambiarTipo(ValueChangeEvent e) {
        this.tipo = e.getNewValue().toString();
        if (this.tipo.equals("Anual")) {
            this.comboAnio = true;

        }
        if (this.tipo.equals("Mensual")) {
            this.comboAnio = true;
        }
        if (this.tipo.equals("NingunReporte")) {
            this.comboAnio = false;
            this.comboMes = false;

        }
        this.mostrarPoraño = false;
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }

}
