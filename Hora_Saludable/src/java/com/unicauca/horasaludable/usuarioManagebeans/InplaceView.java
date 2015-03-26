/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicacuca.horasaludable.usuarioManagebeans;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
 
@ManagedBean
public class InplaceView implements Serializable {
     
    private String telefono = "3104049431";
    private String correo="pepito@unicauca.edu.co";
    private String facultad="Facultad de Salud";
    private String fechanac="13/05/1991";
    private String identificacion="Identificacion";
    private String nombre="Nombre";

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }

    public String getFechanac() {
        return fechanac;
    }

    public void setFechanac(String fechanac) {
        this.fechanac = fechanac;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
   
}