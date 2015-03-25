/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.asistenciaDomain;

/**
 *
 * @author seven
 */
public class Opcion {
    private String nombre;
    private String urlImagen;
    private String facelet;
    
    public Opcion() {}
    public Opcion(String nombre, String urlImagen,String facelet) {
        this.nombre = nombre;
        this.urlImagen = urlImagen;
        this.facelet = facelet;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public String getFacelet() {
        return facelet;
    }

    public void setFacelet(String facelet) {
        this.facelet = facelet;
    }
    
}
