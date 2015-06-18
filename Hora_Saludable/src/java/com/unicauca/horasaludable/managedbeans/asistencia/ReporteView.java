package com.unicauca.horasaludable.managedbeans.asistencia;

import com.unicauca.horasaludable.jpacontrollers.DetalleasistenciaFacade;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

@ManagedBean
@ViewScoped
public class ReporteView implements Serializable {

    @EJB
    private DetalleasistenciaFacade ejbFacadeDetAsi;
    
    String anioSeleccionado;
    String mesSeleccionado;
    String semestreSeleccionado;
    String graficaSeleccionada;
    
    private boolean mostrarGrafica;
    
    private boolean comboAnio;
    private boolean comboMes;
    private boolean comboSemestre;
    private boolean comboGrafica;
    
    private BarChartModel barModel;
    
    private String tipo;
    
    public ReporteView()
    {
        this.mostrarGrafica = false;
        
        this.comboAnio = false;
        this.comboMes = false;
        this.comboSemestre = false;
        this.comboGrafica = false;
        
        this.anioSeleccionado = "NingunAño";
        this.mesSeleccionado = "NingunMes";
        this.semestreSeleccionado = "NingunSemestre";
        this.graficaSeleccionada = "NingunaGrafica";
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

    public boolean isComboSemestre() {
        return comboSemestre;
    }

    public void setComboSemestre(boolean comboSemestre) {
        this.comboSemestre = comboSemestre;
    }

    public DetalleasistenciaFacade getEjbFacadeDetAsi() {
        return ejbFacadeDetAsi;
    }

    public void setEjbFacadeDetAsi(DetalleasistenciaFacade ejbFacadeDetAsi) {
        this.ejbFacadeDetAsi = ejbFacadeDetAsi;
    }

    public String getGraficaSeleccionada() {
        return graficaSeleccionada;
    }

    public void setGraficaSeleccionada(String graficaSeleccionada) {
        this.graficaSeleccionada = graficaSeleccionada;
    }
    
    public boolean isComboGrafica() {
        return comboGrafica;
    }

    public void setComboGrafica(boolean comboGrafica) {
        this.comboGrafica = comboGrafica;
    }

    public boolean isMostrarGrafica() {
        return mostrarGrafica;
    }

    public void setMostrarGrafica(boolean mostrarGrafica) {
        this.mostrarGrafica = mostrarGrafica;
    }

    public void cambiarAño(ValueChangeEvent e) {
        String tip = e.getNewValue().toString();
        this.anioSeleccionado = tip;
        this.mostrarGrafica = false;
        this.comboGrafica = false;
        if (this.tipo.equals("Anual")) {
            if (this.anioSeleccionado.equals("NingunAño")) {
                this.mostrarGrafica = false;
            } else {
                this.comboGrafica = true;
            }
        } else {
            if (this.anioSeleccionado.equals("NingunAño")) {
                this.mostrarGrafica = false;
            } else {
                if(this.tipo.equals("Semestral"))
                    this.comboSemestre = true;
                else
                    this.comboMes = true;
            }
        }
        this.mesSeleccionado = "NingunMes";
        this.semestreSeleccionado = "NingunSemestre";
        this.graficaSeleccionada = "NingunaGrafica";
    }
    
     public void cambiarPorMes(ValueChangeEvent e)
    {
        String tip=e.getNewValue().toString();
        this.mesSeleccionado=tip;
        this.mostrarGrafica = false;
        this.comboGrafica = false;
        if (this.tipo.equals("Mensual")) {
            if (this.mesSeleccionado.equals("NingunMes")) {
                this.mostrarGrafica = false;

            } else {
                this.comboGrafica = true;
            }

        } else {
            if (this.mesSeleccionado.equals("NingunMes")) {
                this.mostrarGrafica = false;
            } 
        }
        this.graficaSeleccionada = "NingunaGrafica";
    }
     
    public void cambiarPorSemestre(ValueChangeEvent e) {
        String tip = e.getNewValue().toString();
        this.semestreSeleccionado = tip;
        this.mostrarGrafica = false;
        this.comboGrafica = false;
        if (this.tipo.equals("Semestral")) {
            if (this.semestreSeleccionado.equals("NingunSemestre")) {
                this.mostrarGrafica = false;
            } else {
                this.comboGrafica = true;
            }
        } else {
            if (this.semestreSeleccionado.equals("NingunSemestre")) {
                this.mostrarGrafica = false;
            }
        }
        this.graficaSeleccionada = "NingunaGrafica";
    }

    public void cambiarTipo(ValueChangeEvent e) {
        this.tipo = e.getNewValue().toString();
        if (this.tipo.equals("Anual")) {
            this.comboAnio = true;
            this.comboMes = false;
            this.comboSemestre = false;
            this.comboGrafica = false;
        }
        if (this.tipo.equals("Semestral")) {
            this.comboAnio = true;
            this.comboSemestre = false;
            this.comboMes = false;
            this.comboGrafica = false;
        }
        if (this.tipo.equals("Mensual")) {
            this.comboAnio = true;
            this.comboMes = false;
            this.comboSemestre = false;
            this.comboGrafica = false;
        }        
        if (this.tipo.equals("NingunReporte")) {
            this.comboAnio = false;
            this.comboMes = false;
            this.comboSemestre = false;
            this.comboGrafica = false;
        }
        
        this.anioSeleccionado = "NingunAño";
        this.mesSeleccionado = "NingunMes";
        this.semestreSeleccionado = "NingunSemestre";
        this.graficaSeleccionada = "NingunaGrafica";
        this.mostrarGrafica = false;
    }
    
    public void cambiarGrafica(ValueChangeEvent e)
    {
        String tip = e.getNewValue().toString();
        this.graficaSeleccionada = tip;
        
        if(this.graficaSeleccionada.equals("Grafica1") || this.graficaSeleccionada.equals("Grafica2"))
        {
            this.mostrarGrafica = true;
            
            if (this.graficaSeleccionada.equals("Grafica1"))
            {
                if(comboAnio && !comboMes && !comboSemestre)
                    barModel = asistenciaPorAnioGrafica1();
                else if(comboAnio && comboMes && !comboSemestre)
                    barModel = asistenciaPorMesGrafica1();
                else if(comboAnio && !comboMes && comboSemestre)
                    barModel = asistenciaPorSemestreGrafica1();
            }
            else if (this.graficaSeleccionada.equals("Grafica2"))
            {                
                if(comboAnio && !comboMes && !comboSemestre)
                    barModel = asistenciaPorAnioGrafica2();
                else if(comboAnio && comboMes && !comboSemestre)
                    barModel = asistenciaPorMesGrafica2();
                else if(comboAnio && !comboMes && comboSemestre)
                    barModel = asistenciaPorSemestreGrafica2();
            }
        }        
        else{
            this.mostrarGrafica = false;
        }
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }
    
    public BarChartModel asistenciaPorAnioGrafica1() {
        BarChartModel barmodel = new BarChartModel();

        ChartSeries asistencia = new ChartSeries();
        asistencia.setLabel("Asistencias");
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

        long[] asistentes = new long[12];
        for (int i = 0; i < 12; i++) {
            asistentes[i] = ejbFacadeDetAsi.asistenciaPorMesGrafica1(i + 1, Integer.parseInt(this.anioSeleccionado));
        }
        for (int i = 0; i < 12; i++) {
            asistencia.set(meses[i].toUpperCase(), asistentes[i]);
        }
        
        barmodel.addSeries(asistencia);    
        barmodel.setTitle("ASISTENCIA AL PROGRAMA HORA SALUDABLE POR MES - AÑO: "+this.anioSeleccionado);
        barmodel.setLegendPosition("ne");
        barmodel.setShowPointLabels(true);
        barmodel.setSeriesColors("4D94FF, 1975FF");
        
        Axis xAxis = barmodel.getAxis(AxisType.X);
        xAxis.setLabel("Mes");
        xAxis.setTickAngle(-90);
        
        Axis yAxis = barmodel.getAxis(AxisType.Y);
        yAxis.setLabel("Número de asistencias");
        yAxis.setMin(0);
        yAxis.setMax(588);

        return barmodel;
    }
    
    public BarChartModel asistenciaPorMesGrafica1() {      
        
        BarChartModel barmodel = new BarChartModel();

        ChartSeries asistencia = new ChartSeries();
        asistencia.setLabel("Asistencias");
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
        long numAsistentes = ejbFacadeDetAsi.asistenciaPorMesGrafica1(mes, Integer.parseInt(this.anioSeleccionado));
        for (int i = 0; i < 12; i++) {
            if(i == mes - 1) {
                if((i-1) >= 0)
                    asistencia.set(meses[i-1].toUpperCase(), 0);
                asistencia.set(meses[i].toUpperCase(), numAsistentes);
                if((i+1) <= 11)
                    asistencia.set(meses[i+1].toUpperCase(), 0);
            }
        }
        
        barmodel.addSeries(asistencia);    
        barmodel.setTitle("ASISTENCIA AL PROGRAMA HORA SALUDABLE - MES: "+this.mesSeleccionado.toUpperCase()+" - AÑO: "+this.anioSeleccionado);
        barmodel.setLegendPosition("ne");
        barmodel.setShowPointLabels(true);
        barmodel.setSeriesColors("4D94FF, 1975FF");
        
        Axis xAxis = barmodel.getAxis(AxisType.X);
        xAxis.setLabel("Mes");
        
        Axis yAxis = barmodel.getAxis(AxisType.Y);
        yAxis.setLabel("Número de asistencias");
        yAxis.setMin(0);
        yAxis.setMax(588);

        return barmodel;
    }
    
    public BarChartModel asistenciaPorSemestreGrafica1()
    {
        BarChartModel barmodel = new BarChartModel();

        ChartSeries asistencia = new ChartSeries();
        asistencia.setLabel("Asistencias");
        
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
        
        if (this.semestreSeleccionado.equals("Primero")) {
            long[] asistentes = new long[12];
            for (int i = 0; i < 6; i++) {
                asistentes[i] = ejbFacadeDetAsi.asistenciaPorMesGrafica1(i + 1, Integer.parseInt(this.anioSeleccionado));
            }
            for (int i = 0; i < 6; i++) {
                asistencia.set(meses[i].toUpperCase(), asistentes[i]);
            }
        }
        if (this.semestreSeleccionado.equals("Segundo")) {

            long[] asistentes = new long[12];
            for (int i = 6; i < 12; i++) {
                asistentes[i] = ejbFacadeDetAsi.asistenciaPorMesGrafica1(i + 1, Integer.parseInt(this.anioSeleccionado));
            }
            for (int i = 6; i < 12; i++) {
                asistencia.set(meses[i].toUpperCase(), asistentes[i]);
            }
        }
        
        barmodel.addSeries(asistencia);    
        barmodel.setTitle("ASISTENCIA AL PROGRAMA HORA SALUDABLE - AÑO: "+this.anioSeleccionado+" - PERIODO: "+this.semestreSeleccionado.toUpperCase());
        barmodel.setLegendPosition("ne");
        barmodel.setShowPointLabels(true);
        barmodel.setSeriesColors("4D94FF, 1975FF");
        
        Axis xAxis = barmodel.getAxis(AxisType.X);
        xAxis.setLabel("Mes");
        
        Axis yAxis = barmodel.getAxis(AxisType.Y);
        yAxis.setLabel("Número de asistencias");
        yAxis.setMin(0);
        yAxis.setMax(588);

        return barmodel;
    }
    
    //GRAFICA 2
    
    public BarChartModel asistenciaPorAnioGrafica2() {
        BarChartModel barmodel = new BarChartModel();

        ChartSeries asistencia = new ChartSeries();
        asistencia.setLabel("Participantes");
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

        long[] asistentes = new long[12];
        for (int i = 0; i < 12; i++) {
            asistentes[i] = ejbFacadeDetAsi.asistenciaPorMesGrafica2(i + 1, Integer.parseInt(this.anioSeleccionado));
        }
        for (int i = 0; i < 12; i++) {
            asistencia.set(meses[i].toUpperCase(), asistentes[i]);
        }
        
        barmodel.addSeries(asistencia);    
        barmodel.setTitle("ASISTENCIA AL PROGRAMA HORA SALUDABLE POR MES - AÑO: "+this.anioSeleccionado);
        barmodel.setLegendPosition("ne");
        barmodel.setShowPointLabels(true);
        barmodel.setSeriesColors("4D94FF, 1975FF");
        
        Axis xAxis = barmodel.getAxis(AxisType.X);
        xAxis.setLabel("Mes");
        xAxis.setTickAngle(-90);
        
        Axis yAxis = barmodel.getAxis(AxisType.Y);
        yAxis.setLabel("Número de Participantes");
        yAxis.setMin(0);
        yAxis.setMax(120);

        return barmodel;
    }
    
    public BarChartModel asistenciaPorMesGrafica2() {      
        
        BarChartModel barmodel = new BarChartModel();

        ChartSeries asistencia = new ChartSeries();
        asistencia.setLabel("Participantes");
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
        long numAsistentes = ejbFacadeDetAsi.asistenciaPorMesGrafica2(mes, Integer.parseInt(this.anioSeleccionado));
        for (int i = 0; i < 12; i++) {
            if(i == mes - 1) {
                if((i-1) >= 0)
                    asistencia.set(meses[i-1].toUpperCase(), 0);
                asistencia.set(meses[i].toUpperCase(), numAsistentes);
                if((i+1) <= 11)
                    asistencia.set(meses[i+1].toUpperCase(), 0);
            }
        }
        
        barmodel.addSeries(asistencia);    
        barmodel.setTitle("ASISTENCIA AL PROGRAMA HORA SALUDABLE - MES: "+this.mesSeleccionado.toUpperCase()+" - AÑO: "+this.anioSeleccionado);
        barmodel.setLegendPosition("ne");
        barmodel.setShowPointLabels(true);
        barmodel.setSeriesColors("4D94FF, 1975FF");
        
        Axis xAxis = barmodel.getAxis(AxisType.X);
        xAxis.setLabel("Mes");
        
        Axis yAxis = barmodel.getAxis(AxisType.Y);
        yAxis.setLabel("Número de Participantes");
        yAxis.setMin(0);
        yAxis.setMax(120);

        return barmodel;
    }
    
    public BarChartModel asistenciaPorSemestreGrafica2()
    {
        BarChartModel barmodel = new BarChartModel();

        ChartSeries asistencia = new ChartSeries();
        asistencia.setLabel("Participantes");
        
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
        
        if (this.semestreSeleccionado.equals("Primero")) {
            long[] asistentes = new long[12];
            for (int i = 0; i < 6; i++) {
                asistentes[i] = ejbFacadeDetAsi.asistenciaPorMesGrafica2(i + 1, Integer.parseInt(this.anioSeleccionado));
            }
            for (int i = 0; i < 6; i++) {
                asistencia.set(meses[i].toUpperCase(), asistentes[i]);
            }
        }
        if (this.semestreSeleccionado.equals("Segundo")) {

            long[] asistentes = new long[12];
            for (int i = 6; i < 12; i++) {
                asistentes[i] = ejbFacadeDetAsi.asistenciaPorMesGrafica2(i + 1, Integer.parseInt(this.anioSeleccionado));
            }
            for (int i = 6; i < 12; i++) {
                asistencia.set(meses[i].toUpperCase(), asistentes[i]);
            }
        }
        
        barmodel.addSeries(asistencia);    
        barmodel.setTitle("ASISTENCIA AL PROGRAMA HORA SALUDABLE - AÑO: "+this.anioSeleccionado+" - PERIODO: "+this.semestreSeleccionado.toUpperCase());
        barmodel.setLegendPosition("ne");
        barmodel.setShowPointLabels(true);
        barmodel.setSeriesColors("4D94FF, 1975FF");
        
        Axis xAxis = barmodel.getAxis(AxisType.X);
        xAxis.setLabel("Mes");
        
        Axis yAxis = barmodel.getAxis(AxisType.Y);
        yAxis.setLabel("Número de participantes");
        yAxis.setMin(0);
        yAxis.setMax(120);

        return barmodel;
    }  
}
