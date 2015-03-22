/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.asistenciapersonal;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

/**
 *
 * @author daniel
 */
@ManagedBean
@RequestScoped
public class Statistics implements Serializable {

    private static final long serialVersionUID = 6401166601481931346L;
     
    private CartesianChartModel viviendas;  
 
    public CartesianChartModel getViviendas() {
        return viviendas;
    }
 
    @PostConstruct
    public void init(){
        viviendas = new CartesianChartModel();
        
        /*
        final ChartSeries venta  = new ChartSeries("Venta");
        venta.set("2008", 800);
        venta.set("2009", 1300);
        venta.set("2010", 700);
        venta.set("2011", 500);
        */
         
        final ChartSeries alquiler  = new ChartSeries("Asistencia Anual");
        alquiler.set("Ene", 6);
        alquiler.set("Feb", 12);
        alquiler.set("Mar", 11);
        alquiler.set("Abr", 17);
        alquiler.set("May", 20);
        alquiler.set("Jun", 6);
        alquiler.set("Jul", 17);
        alquiler.set("Ag", 20);
        alquiler.set("Sep", 23);
        alquiler.set("Oct", 22);
        alquiler.set("Nov", 15);
        alquiler.set("Dic", 5);
        
         
        //viviendas.addSeries(venta);
        viviendas.addSeries(alquiler);
 
    }
    
    
    
}
