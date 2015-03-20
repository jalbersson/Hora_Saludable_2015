/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.modelo;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class auxiliar 
{
    private boolean estadoCerrarSesion;
    private boolean estadoNombresUsuario;
    
    public auxiliar()
    {
        estadoCerrarSesion=true;
        estadoNombresUsuario=false;
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
    
    public void iniciarSesion()
    {
        estadoCerrarSesion=false;
        estadoNombresUsuario=true;
    }
}





