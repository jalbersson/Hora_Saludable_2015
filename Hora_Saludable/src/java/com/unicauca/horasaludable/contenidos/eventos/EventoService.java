/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.contenidos.eventos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import com.unicauca.horasaludable.contenidos.eventos.Evento;
import java.util.Date;
 
@ManagedBean(name = "eventoService")
@ApplicationScoped
public class EventoService {
     
    private final static String[] colors;
     
    private final static String[] brands;
     
    static {
        colors = new String[10];
        colors[0] = "Black";
        colors[1] = "White";
        colors[2] = "Green";
        colors[3] = "Red";
        colors[4] = "Blue";
        colors[5] = "Orange";
        colors[6] = "Silver";
        colors[7] = "Yellow";
        colors[8] = "Brown";
        colors[9] = "Maroon";
         
        brands = new String[10];
        brands[0] = "BMW";
        brands[1] = "Mercedes";
        brands[2] = "Volvo";
        brands[3] = "Audi";
        brands[4] = "Renault";
        brands[5] = "Fiat";
        brands[6] = "Volkswagen";
        brands[7] = "Honda";
        brands[8] = "Jaguar";
        brands[9] = "Ford";
    }
     
    public List<Evento> createEventos(int size) {
        List<Evento> list = new ArrayList<Evento>();
        for(int i = 0 ; i < size ; i++) {
            list.add(new Evento(getNombreEvento(), getLugarEvento(), getFechaEvento(), getDescripcionEvento()));
        }
         
        return list;
    }
     
    private String getNombreEvento() {
        return "Competencia de Atletismo";
    }
     
    private String getLugarEvento() {
        return "CDU Tulcan";
    }
     
    private String getFechaEvento() {
        return "02-03-2015";
    }
     
    private String getDescripcionEvento() {
        return "Competencia de atletismo en todas las modalidades.....";
    }    
}
