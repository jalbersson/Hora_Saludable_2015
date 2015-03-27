/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.contenidos.java;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import com.unicauca.horasaludable.contenidos.java.Horario;
import java.util.Arrays;

/**
 *
 * @author Yuri
 */
@ManagedBean(name = "carService")
@ApplicationScoped
public class ServicioHorario 
{
    private final static String[] lunes;
    private final static String[] martes;
    private final static String[] miercoles;
    private final static String[] jueves;
    private final static String[] viernes;
    private final static String[] hora;
     
    static {
        hora = new String[5];
        hora[0] = "7:00 - 9:00";
        hora[1] = "9:00 - 11:00";
        hora[2] = "11:00 - 1:00";
        hora[3] = "2:00 - 4:00";
        hora[4] = "4:00 - 6:00";
        
        lunes = new String[5];
        lunes[0] = "cerrardo";
        lunes[1] = "abierto";
        lunes[2] = "aerobicos";
        lunes[3] = "cerrado";
        lunes[4] = "abierto";
        
         
        martes = new String[5];
        martes[0] = "cerrardo";
        martes[1] = "cerrardo";
        martes[2] = "abierto";
        martes[3] = "abierto";
        martes[4] = "cerrardo";
        
        
        miercoles = new String[5];
        miercoles[0] = "aerobicos";
        miercoles[1] = "aerobicos";
        miercoles[2] = "cerrardo";
        miercoles[3] = "cerrardo";
        miercoles[4] = "aerobicos";
        
        jueves = new String[5];
        jueves[0] = "abierto";
        jueves[1] = "abierto";
        jueves[2] = "cerrardo";
        jueves[3] = "abierto";
        jueves[4] = "abierto";
        
        viernes = new String[5];
        viernes[0] = "abierto";
        viernes[1] = "abierto";
        viernes[2] = "cerrardo";
        viernes[3] = "abierto";
        viernes[4] = "abierto";
    }
     
    public List<Horario> createHorario(int size) {
        List<Horario> list = new ArrayList<Horario>();
        for(int i = 0 ; i < size ; i++) {
            list.add(new Horario(hora[i], getRandomLunes(), getRandomMartes(), getRandomMiercoles(), getRandomJueves(), getRandomViernes(),getRandomId()));
        }
         
        return list;
    }
     
    private String getRandomId() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
     
    private String getRandomLunes() {
        return lunes[(int) (Math.random() * 10)];
    }
     
    private String getRandomMartes() {
        return martes[(int) (Math.random() * 10)];
    }
    
    private String getRandomMiercoles() {
        return miercoles[(int) (Math.random() * 10)];
    }
     
     private String getRandomJueves() {
        return jueves[(int) (Math.random() * 10)];
    }
     
     private String getRandomViernes() {
        return viernes[(int) (Math.random() * 10)];
    }
     
    public int getRandomPrice() {
        return (int) (Math.random() * 100000);
    }
     
    public boolean getRandomSoldState() {
        return (Math.random() > 0.5) ? true: false;
    }
 
    public List<String> getLunes() {
        return Arrays.asList(lunes);
    }
    
    public List<String> getMartes() {
        return Arrays.asList(martes);
    }
    
    public List<String> getMiercoles() {
        return Arrays.asList(miercoles);
    }
    
    public List<String> getJueves() {
        return Arrays.asList(jueves);
    }
    
    public List<String> getViernes() {
        return Arrays.asList(viernes);
    }
}


