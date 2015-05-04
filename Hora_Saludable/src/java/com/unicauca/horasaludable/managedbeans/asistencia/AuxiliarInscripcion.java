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
        me_eses.add("enero");
        me_eses.add("febrero");
        me_eses.add("marzo");
        me_eses.add("abril");
        me_eses.add("mayo");
        me_eses.add("junio");
        me_eses.add("julio");
        me_eses.add("agosto");
        me_eses.add("septiembre");
        me_eses.add("octubre");
        me_eses.add("noviembre");
        me_eses.add("diciembre");        
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
