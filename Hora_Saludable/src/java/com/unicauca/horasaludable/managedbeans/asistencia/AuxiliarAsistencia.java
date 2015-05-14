
package com.unicauca.horasaludable.managedbeans.asistencia;

import javax.faces.event.ValueChangeEvent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class AuxiliarAsistencia {
    
    private boolean reporteAnual;
    private boolean año;
    private boolean reporteMensual;
    private boolean mes;
    private boolean verReporte;
    private boolean tablaAnual;
    private boolean tablaMensual;
    
    public AuxiliarAsistencia()
    {
        this.reporteAnual = false;
        this.reporteMensual = false;
        this.año = false;
        this.mes = false;
        this.verReporte = false;
        this.tablaAnual = false;
        this.tablaMensual = false;
    }

    public boolean isReporteAnual() {
        return reporteAnual;
    }

    public void setReporteAnual(boolean reporteAnual) {
        this.reporteAnual = reporteAnual;
    }

    public boolean isAño() {
        return año;
    }

    public void setAño(boolean año) {
        this.año = año;
    }

    public boolean isReporteMensual() {
        return reporteMensual;
    }

    public void setReporteMensual(boolean reporteMensual) {
        this.reporteMensual = reporteMensual;
    }

    public boolean isMes() {
        return mes;
    }

    public void setMes(boolean mes) {
        this.mes = mes;
    }

    public boolean isVerReporte() {
        return verReporte;
    }

    public void setVerReporte(boolean verReporte) {
        this.verReporte = verReporte;
    }

    public boolean isTablaAnual()
    {
        if(verReporte==true && reporteAnual==true && año==true && reporteMensual==false)
            return true;
        else
            return false;
    }

    public void setTablaAnual(boolean tablaAnual) {
        this.tablaAnual = tablaAnual;
    }

    public boolean isTablaMensual()
    {
        if(verReporte==true && reporteMensual==true && año==true && mes==true)
            return true;
        else
            return false;
    }

    public void setTablaMensual(boolean tablaMensual) {
        this.tablaMensual = tablaMensual;
    }
    
    public void cambiarTipo(ValueChangeEvent e)
    {         
        String tipo=e.getNewValue().toString();        
        if(tipo.equals("Anual"))
        {
            this.reporteAnual = true;
            this.reporteMensual = false;
        }
        if(tipo.equals("Mensual"))
        {
            this.reporteAnual = true;
            this.reporteMensual = true;
        }
        if(tipo.equals("NingunReporte"))
        {
            this.reporteAnual = false;
            this.reporteMensual = false;
        }
        this.verReporte = false;
    }
    
    public void cambiarAño(ValueChangeEvent e)
    {
        String tipo=e.getNewValue().toString();
        
        if(!tipo.equals("NingunAño"))
        {
            año = true;
            if(reporteMensual == false || mes == true)
                verReporte = true;
            else
                verReporte = false;
        }
        else
        {
            verReporte = false;
            año = false;
        }
    }
    
    public void cambiarMes(ValueChangeEvent e)
    {
        String tipo=e.getNewValue().toString();
        if(!tipo.equals("NingunMes"))
        {
            mes = true;
            if(reporteMensual == true && año == true)
                verReporte = true;
            else
                verReporte = false;
        }
        else
        {
            verReporte = false;
            mes = false;
        }
    }
}
