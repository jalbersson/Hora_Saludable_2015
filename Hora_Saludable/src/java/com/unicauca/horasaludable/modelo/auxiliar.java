/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.modelo;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

@ManagedBean
@SessionScoped
public class auxiliar 
{
    private boolean estadoCerrarSesion;
    private boolean estadoNombresUsuario;
    
    private boolean cargo;
    private boolean dependencia;
    private boolean empleado;
    private boolean nombreEmpleado;
    private boolean dependenciaEmpleado;
    private boolean facultad;
    
    public auxiliar()
    {
        estadoCerrarSesion=true;
        estadoNombresUsuario=false;
        
        this.dependencia=false;
        this.empleado=false;
        this.nombreEmpleado=false;
        this.dependenciaEmpleado=false;
        this.facultad=false;
        this.cargo=false;
    }

    public boolean isEstadoCerrarSesion() 
    {
        return estadoCerrarSesion;
    }

    public void setEstadoCerrarSesion(boolean estadoCerrarSesion) 
    {
        this.estadoCerrarSesion = estadoCerrarSesion;
    }

    public boolean isEstadoNombresUsuario() 
    {
        return estadoNombresUsuario;
    }

    public void setEstadoNombresUsuario(boolean estadoNombresUsuario) 
    {
        this.estadoNombresUsuario = estadoNombresUsuario;
    }
    

    public boolean isCargo() {
        return cargo;
    }

    public void setCargo(boolean cargo) {
        this.cargo = cargo;
    }

    public boolean isDependencia() {
        return dependencia;
    }

    public void setDependencia(boolean dependencia) {
        this.dependencia = dependencia;
    }

    public boolean isEmpleado() {
        return empleado;
    }

    public void setEmpleado(boolean empleado) {
        this.empleado = empleado;
    }

    public boolean isNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(boolean nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public boolean isDependenciaEmpleado() {
        return dependenciaEmpleado;
    }

    public void setDependenciaEmpleado(boolean dependenciaEmpleado) {
        this.dependenciaEmpleado = dependenciaEmpleado;
    }

    public boolean isFacultad() {
        return facultad;
    }

    public void setFacultad(boolean facultad) {
        this.facultad = facultad;
    }
    
    public void iniciarSesion()
    {
        estadoCerrarSesion=false;
        estadoNombresUsuario=true;
    }
    public void cambiarTipo(ValueChangeEvent e)
    {         
        String tipo=e.getNewValue().toString();        
        if(tipo.equals("Estudiante"))
        {
            this.dependencia=false;
            this.empleado=false;
            this.nombreEmpleado=false;
            this.dependenciaEmpleado=false;
            this.facultad=true;
            this.cargo=false;
        }
        if(tipo.equals("Empleado"))
        {
            this.dependencia=true;
            this.empleado=false;
            this.nombreEmpleado=false;
            this.dependenciaEmpleado=false;
            this.facultad=false;
            this.cargo=true;
        }        
        if(tipo.equals("Familiar"))
        {
            this.dependencia=false;
            this.empleado=true;
            this.nombreEmpleado=true;
            this.dependenciaEmpleado=true;
            this.facultad=false;
            this.cargo=false;
        }
        if(tipo.equals("Seleccione"))
        {
            this.dependencia=false;
            this.empleado=false;
            this.nombreEmpleado=false;
            this.dependenciaEmpleado=false;
            this.facultad=false;
            this.cargo=false;
        }
        
    }   
}





