/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.managedbeans.asistencia;

import com.unicauca.horasaludable.jpacontrollers.DetalleasistenciaFacade;
import com.unicauca.horasaludable.jpacontrollers.UnidadacademicaFacade;
import com.unicauca.horasaludable.entities.Unidadacademica;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
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
public class ReporteDependencias implements Serializable {

    @EJB
    private DetalleasistenciaFacade ejbFacadeDetAsi;
    @EJB
    private UnidadacademicaFacade unidadEJB;
    
    String anioSeleccionado;
    String mesSeleccionado;
    String semestreSeleccionado;
    
    private boolean mostrarGrafica;
    
    private boolean comboAnio;
    private boolean comboMes;
    private boolean comboPeriodo;
    
    private BarChartModel barModel;
    private String tipo;
    private List<String> anios;
    

    public ReporteDependencias() {
        this.mostrarGrafica = false;
        
        this.comboAnio = false;
        this.comboMes = false;
        this.comboPeriodo = false;
        
        anioSeleccionado = "NingunAño";
        mesSeleccionado = "NingunMes";
        semestreSeleccionado = "NingunSemestre";
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

    public boolean isComboAnio() {
        return comboAnio;
    }

    public void setComboAnio(boolean comboAnio) {
        this.comboAnio = comboAnio;
    }

      public String getAnioSeleccionado() {
        return anioSeleccionado;
    }

    public List<String> getAnios() {
        generarAnios();
        return anios;
    }

    public void setAnios(List<String> anios) {
        this.anios = anios;
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

    public String getSemestreSeleccionado() {
        return semestreSeleccionado;
    }

    public void setSemestreSeleccionado(String semestreSeleccionado) {
        this.semestreSeleccionado = semestreSeleccionado;
    }

    public boolean isMostrarGrafica() {
        return mostrarGrafica;
    }

    public void setMostrarGrafica(boolean mostrarGrafica) {
        this.mostrarGrafica = mostrarGrafica;
    }

    public boolean isComboPeriodo() {
        return comboPeriodo;
    }

    public void setComboPeriodo(boolean comboPeriodo) {
        this.comboPeriodo = comboPeriodo;
    }
    
    public BarChartModel asistenciaPorMes() {
        BarChartModel barmodel = new BarChartModel();
        ChartSeries asistencia = new ChartSeries();
        asistencia.setLabel("Participantes");
        int anio = Integer.parseInt(this.anioSeleccionado);
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
        
        int mes = 0;
        for (int i = 0; i < meses.length; i++) {
            if (this.mesSeleccionado.equals(meses[i])) {
                mes = i + 1;
                break;
            }
        }
        
        List<Unidadacademica> unidadesAcademicas = unidadEJB.findAll();
        
        for (Unidadacademica uni : unidadesAcademicas) {
            long numAsistentes = ejbFacadeDetAsi.asistenciaDependenciaPorMes(mes, anio, uni.getUniid());
            asistencia.set(abreviar(uni.getUninombre()), numAsistentes);
        }
        
        barmodel.addSeries(asistencia);    
        barmodel.setTitle("REPORTE DE ASISTENCIA - MES: "+this.mesSeleccionado.toUpperCase()+" - AÑO: "+this.anioSeleccionado);
        barmodel.setLegendPosition("ne");
        barmodel.setShowPointLabels(true);
        barmodel.setSeriesColors("4D94FF, 1975FF");
        
        Axis xAxis = barmodel.getAxis(AxisType.X);
        xAxis.setLabel("Programa Académico o Dependencia");
        xAxis.setTickAngle(-90);
        
        Axis yAxis = barmodel.getAxis(AxisType.Y);
        yAxis.setLabel("Número de participantes");
        yAxis.setMin(0);
        yAxis.setMax(63);

        return barmodel;
    }

    public BarChartModel asistenciaPorAnio() {
        BarChartModel barmodel = new BarChartModel();
        ChartSeries asistencia = new ChartSeries();
        asistencia.setLabel("Participantes");
        
        int aniio = Integer.parseInt(this.anioSeleccionado);
        
        List<Unidadacademica> unidadesAcademicas = unidadEJB.findAll();
        
        for (Unidadacademica uni : unidadesAcademicas) {
            long asistenciaanio = ejbFacadeDetAsi.asistenciaDependenciaPorAño(aniio, uni.getUniid());
            asistencia.set(abreviar(uni.getUninombre()), (Long) asistenciaanio);
        }
                
        barmodel.addSeries(asistencia);    
        barmodel.setTitle("REPORTE DE ASISTENCIA - AÑO: "+this.anioSeleccionado);
        barmodel.setLegendPosition("ne");
        barmodel.setShowPointLabels(true);
        barmodel.setSeriesColors("4D94FF, 1975FF");
        
        Axis xAxis = barmodel.getAxis(AxisType.X);
        xAxis.setLabel("Programa Académico o Dependencia");
        xAxis.setTickAngle(-90);
        
        Axis yAxis = barmodel.getAxis(AxisType.Y);
        yAxis.setLabel("Número de participantes");
        yAxis.setMin(0);
        yAxis.setMax(63);
        
        return barmodel;
    }
    
    public BarChartModel asistenciaPorSemestre() {
        BarChartModel barmodel = new BarChartModel();
        ChartSeries asistencia = new ChartSeries();
        asistencia.setLabel("Participantes");
        int anio = Integer.parseInt(this.anioSeleccionado);
        
        List<Unidadacademica> unidadesAcademicas = unidadEJB.findAll();
        
        if (this.semestreSeleccionado.equals("Primero")) {
            String fechaInicial = anio+"-01-01 00:00:00.000000";
            String fechaFinal = anio+"-06-30 00:00:00.000000";
            long numAsistentes;
            for (Unidadacademica uni : unidadesAcademicas) {
                numAsistentes = ejbFacadeDetAsi.asistenciaDependenciaPorSemestre(fechaInicial,fechaFinal,uni.getUniid());
                asistencia.set(abreviar(uni.getUninombre()), numAsistentes);
            }
        }
        if (this.semestreSeleccionado.equals("Segundo")) {
            String fechaInicial = anio+"-07-01 00:00:00.000000";
            String fechaFinal = anio+"-12-31 00:00:00.000000";
            long numAsistentes;
            for (Unidadacademica uni : unidadesAcademicas) {
                numAsistentes = ejbFacadeDetAsi.asistenciaDependenciaPorSemestre(fechaInicial,fechaFinal,uni.getUniid());
                asistencia.set(abreviar(uni.getUninombre()), numAsistentes);
            }
        }
                
        barmodel.addSeries(asistencia);    
        barmodel.setTitle("ASISTENCIA AL PROGRAMA HORA SALUDABLE - AÑO: "+this.anioSeleccionado+" - PERIODO: "+this.semestreSeleccionado.toUpperCase());
        barmodel.setLegendPosition("ne");
        barmodel.setShowPointLabels(true);
        barmodel.setSeriesColors("4D94FF, 1975FF");
        
        Axis xAxis = barmodel.getAxis(AxisType.X);
        xAxis.setLabel("Programa Académico o Dependencia");
        xAxis.setTickAngle(-90);
        
        Axis yAxis = barmodel.getAxis(AxisType.Y);
        yAxis.setLabel("Número de participantes");
        yAxis.setMin(0);
        yAxis.setMax(63);

        return barmodel;
    }
    
    public String abreviar(String cadena)
    {
        String abreviacion = "";
        
        switch(cadena)
        {
            //Facultades
            case "Facultad de Ingeniería Electrónica y Telecomunicaciones":
                abreviacion = "FAC. ING. ELECTRONICA"; break;
            case "Facultad de Ingeniería Civil":
                abreviacion = "FAC. ING. CIVIL"; break;
            case "Facultad de Derecho, Ciencias Políticas y Sociales":
                abreviacion = "FAC. DERECHO"; break;
            case "Facultad de Ciencias Naturales, Exactas y de la Educación":
                abreviacion = "FAC. EDUCACIÓN"; break;
            case "Facultad de Ciencias Contables, Económicas y Administrativas":
                abreviacion = "FAC. CONTABLES"; break;
            case "Facultad de Ciencias de la Salud":
                abreviacion = "FAC. SALUD"; break;
            case "Facultad de Ciencias Agrarias":
                abreviacion = "FAC. AGRARIA"; break;
            case "Facultad de Artes":
                abreviacion = "FAC. ARTES"; break;
            case "Facultad de Ciencias Humanas y Sociales":
                abreviacion = "FAC. HUMANIDADES"; break;
            
            //Areas
            case "Área de Adquisiciones e Inventarios":
                abreviacion = "AREA ADQUI. E INV."; break;
            case "Área de Planta Física":
                abreviacion = "AREA PLANTA FISICA"; break;    
            case "Área de Egresados":
                abreviacion = "AREA EGRESADOS"; break;
            case "Área de Mantenimiento":
                abreviacion = "AREA MANTENIMIENTO"; break;    
            case "Área de Desarrollo Editorial":
                abreviacion = "AREA EDITORIAL"; break;    
            case "Área de Salud Ocupacional":
                abreviacion = "AREA SALUD OCUPAC."; break;    
            case "Área de Transporte y Aseo":
                abreviacion = "AREA TRASN Y ASEO"; break;    
                
            //Divisiones
            case "División Administrativa y de Servicios":
                abreviacion = "DIV. ADMINISTRATIVA"; break;  
            case "División de Admisiones, Registro y Control Académico":
                abreviacion = "DARCA"; break;     
            case "División de Comunicaciones y de Prensa":
                abreviacion = "DIV. COMUNICACIONES"; break;     
            case "División de Cultura y Patrimonio":
                abreviacion = "DIV. CULTURA"; break;     
            case "División de Gestión de la Investigación":
                abreviacion = "DIV. INVESTIGACIÓN"; break;     
            case "División de Gestión de Medios y Recursos Bibliográficos":
                abreviacion = "DIV. REC. BIBLIOGRAFICOS"; break;   
            case "División de Gestión del Talento Humano":
                abreviacion = "DIV. TALENTO HUMANO"; break;     
            case "División de Gestión Financiera":
                abreviacion = "DIV. FINANCIERA"; break;     
            case "División de Recreación y Deporte":
                abreviacion = "DIV. RECRE. Y DEPOR."; break;     
            case "División de Salud Integral":
                abreviacion = "DIV. SALUD INTEG."; break;     
            case "División de Tecnologías de la Información y las Comunicaciones":
                abreviacion = "DIV. TIC'S"; break;    
            //Oficinas
            case "Oficina de Control Interno":
                abreviacion = "OFIC. CONTROL INT."; break;
            case "Oficina de Planeación y Desarrollo Institucional":
                abreviacion = "OFIC. PLANEACIÓN"; break;
            case "Oficina de Relaciones Interinstitucionales e Internacionales":
                abreviacion = "OFIC. RELACIONES"; break;
            case "Programa de Formación en Idiomas (PFI)":
                abreviacion = "PFI"; break;
            //Vicerectorias
            case "Vicerrectoría Administrativa":
                abreviacion = "VIC. ADMINISTRATIVA"; break;
            case "Vicerrectoría de Investigaciones":
                abreviacion = "VIC. INVESTIGACIONES"; break;
            case "Vicerrectoría de Cultura y Bienestar":
                abreviacion = "VIC. CULTURA Y BIEN."; break;
            //Centros
            case "Centro de Educación Continua":
                abreviacion = "C. EDUCACIÓN CONTINUA"; break;
            case "Centro de Regionalización":
                abreviacion = "C. REGIONALIZACIÓN"; break;
            default:
                abreviacion = cadena.toUpperCase(); break;
        }
        
        return abreviacion;
    }
    
    public void cambiarAño(ValueChangeEvent e) {
        String tip = e.getNewValue().toString();
        this.anioSeleccionado = tip;
        this.mostrarGrafica = false;
        if (this.tipo.equals("Anual")) {
            if (this.anioSeleccionado.equals("NingunAño")) {
                this.mostrarGrafica = false;

            } else {
                this.mostrarGrafica = true;
                this.barModel = this.asistenciaPorAnio();

            }

        } else {
            if (this.anioSeleccionado.equals("NingunAño")) {
                this.mostrarGrafica = false;

            }  else {
                if(this.tipo.equals("Semestral"))
                    this.comboPeriodo = true;
                else
                    this.comboMes = true;
            }
        }
        this.mesSeleccionado = "NingunMes";
        this.semestreSeleccionado = "NingunSemestre";
    }
    
    public void cambiarPorMes(ValueChangeEvent e) {
        String tip = e.getNewValue().toString();
        this.mesSeleccionado = tip;
        this.mostrarGrafica = false;
        if (this.tipo.equals("Mensual")) {
            if (this.mesSeleccionado.equals("NingunMes")) {
                this.mostrarGrafica = false;

            } else {
                this.mostrarGrafica = true;
                this.barModel = this.asistenciaPorMes();

            }

        } else {
            if (this.mesSeleccionado.equals("NingunMes")) {
                this.mostrarGrafica = false;

            }
        }
    }

    public void cambiarTipo(ValueChangeEvent e) {        
        this.tipo = e.getNewValue().toString();
        if (this.tipo.equals("Anual")) {
            this.comboAnio = true;
            this.comboMes = false;
            this.comboPeriodo = false;
        }
        if (this.tipo.equals("Semestral")) {
            this.comboAnio = true;
            this.comboMes = false;
            this.comboPeriodo = false;
        }
        if (this.tipo.equals("Mensual")) {
            this.comboAnio = true;
            this.comboMes = false;
            this.comboPeriodo = false;
        }        
        if (this.tipo.equals("NingunReporte")) {
            this.comboAnio = false;
            this.comboMes = false;
            this.comboPeriodo = false;
        }
        this.mostrarGrafica = false;
        
        this.anioSeleccionado = "NingunAño";
        this.mesSeleccionado = "NingunMes";
        this.semestreSeleccionado = "NingunSemestre";
    }
    
    public void cambiarPorSemestre(ValueChangeEvent e) {
        String tip = e.getNewValue().toString();
        this.semestreSeleccionado = tip;
        this.mostrarGrafica = false;
        if (this.tipo.equals("Semestral")) {
            if (this.semestreSeleccionado.equals("NingunSemestre")) {
                this.mostrarGrafica = false;
            } else {
                this.mostrarGrafica = true;
                this.barModel = asistenciaPorSemestre();
            }
        } else {
            if (this.semestreSeleccionado.equals("NingunSemestre")) {
                this.mostrarGrafica = false;
            }
        }
    }
    
    public BarChartModel getBarModel() {
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }
    
    private void generarAnios()
    {
        anios = new ArrayList();
        Date date = new Date();
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);        
        while(year> 2014)
        {
            String anio = year + "";
            anios.add(anio);
            year --;
        }
        String anio = year + "";
        anios.add(anio);
    }
}