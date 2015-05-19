/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.managedbeans.contenidos;

/**
 *
 * @author Yuri
 */
public class Horario {
    
    private int idH;
    private String nombreH;
    private String rutaH;

    //constructor
    
    //get y set
    public int getIdH() {
        return idH;
    }

    public void setIdH(int idH) {
        this.idH = idH;
    }

    public String getNombreH() {
        return nombreH;
    }

    public void setNombreH(String nombreH) {
        this.nombreH = nombreH;
    }

    public String getRutaH() {
        return rutaH;
    }

    public void setRutaH(String rutaH) {
        this.rutaH = rutaH;
    }  
    
    
}
