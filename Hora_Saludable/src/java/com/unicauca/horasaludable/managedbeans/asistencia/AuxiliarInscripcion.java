/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.managedbeans.asistencia;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author seven
 */
public class AuxiliarInscripcion {
    
//    private List<String> meses;
    /**
     * Creates a new instance of AuxiliarAsistencia
     */
    public AuxiliarInscripcion() {
//        meses = meses();
    }
    private List<String> meses() {
        List<String> me_eses = new ArrayList();
        me_eses.add("Enero");
        me_eses.add("Febrero");
        me_eses.add("Marzo");
        me_eses.add("Abril");
        me_eses.add("Mayo");
        me_eses.add("Junio");
        me_eses.add("Julio");
        me_eses.add("Agosto");
        me_eses.add("Septiembre");
        me_eses.add("Octubre");
        me_eses.add("Noviembre");
        me_eses.add("Diciembre");        
        return me_eses;
    }
    public int getDia() {
        return 29;
    }
    public List<String> getMeses() {
        return meses();
    }
    public int getMM(String mes) {
        List<String> meses = meses();
        for(int i=0; i<meses.size(); i++) {
            if(meses.get(i).equals(mes)) {
                return i+1;
            }
        }
        Date date = new Date();
        return date.getMonth() + 1;
    }
    public String getMes(int mm) {
        List<String> meses = meses();
        if(mm > 0 && mm <= 12) {
            return meses.get(mm-1);
        }
        Date date = new Date();
        String mes = meses.get(date.getMonth());
        return mes;
    }
    public int getAnio() {
        Date date = new Date();
        int anio = 1900 + date.getYear();
        return anio;
    }
}
