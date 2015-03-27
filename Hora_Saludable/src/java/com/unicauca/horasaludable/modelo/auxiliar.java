/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.modelo;

import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import org.primefaces.context.RequestContext;

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
    private ArrayList<Usuario> listaUsuarios;
    private Usuario usuarioSeleccionado;
    private boolean tablaEstudiantes;    
    private boolean tablaFamiliar;
    private boolean tablaEmpleado;


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
        this.tablaEmpleado=false;
        this.tablaEstudiantes=false;
        this.tablaFamiliar=false;
        this.cargarUsuarios();
    }
    public boolean isTablaEstudiantes() {
        return tablaEstudiantes;
    }

    public void setTablaEstudiantes(boolean tablaEstudiantes) {
        this.tablaEstudiantes = tablaEstudiantes;
    }

    public boolean isTablaFamiliar() {
        return tablaFamiliar;
    }

    public void setTablaFamiliar(boolean tablaFamiliar) {
        this.tablaFamiliar = tablaFamiliar;
    }

    public boolean isTablaEmpleado() {
        return tablaEmpleado;
    }

    public void setTablaEmpleado(boolean tablaEmpleado) {
        this.tablaEmpleado = tablaEmpleado;
    }
     public Usuario getUsuarioSeleccionado() {
        return usuarioSeleccionado;
    }

    public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
        this.usuarioSeleccionado = usuarioSeleccionado;
    }

    private void cargarUsuarios()
    {
        listaUsuarios=new ArrayList();
        Usuario usuario= new Usuario();
        usuario.setNombres("Pedro Jose");
        usuario.setApellidos("Narvaez");
        usuario.setNumeroIdentificacion(11110110);
        usuario.setCorreo("pedro@gmail.com");
        usuario.setTipo("Empleado");
        usuario.setDependencia("Fac. Ingenieria Electronica");
        listaUsuarios.add(usuario);
        /*-----------------*/
        usuario= new Usuario();
        usuario.setNombres("Benito Jose");
        usuario.setApellidos("Romero");
        usuario.setNumeroIdentificacion(11222110);
        usuario.setCorreo("Benito@gmail.com");
        usuario.setTipo("Empleado");
        usuario.setDependencia("Fac. Ingenieria Civil");
        listaUsuarios.add(usuario);
        /*-----------------*/
        usuario= new Usuario();
        usuario.setNombres("Kyra Benita");
        usuario.setApellidos("Andrade");
        usuario.setNumeroIdentificacion(122203310);
        usuario.setCorreo("Kyra@gmail.com");
        usuario.setTipo("Empleado");
        usuario.setDependencia("Fac. Contables");
        listaUsuarios.add(usuario);
        /*-----------------*/
        usuario= new Usuario();
        usuario.setNombres("Pepito");
        usuario.setApellidos("Molina");
        usuario.setNumeroIdentificacion(122205510);
        usuario.setCorreo("pepito@gmail.com");
        usuario.setTipo("Empleado");
        usuario.setDependencia("Division Financiera");
        listaUsuarios.add(usuario);
    }
    public void seleccionarEmpleado(Usuario usuario)
    {
       RequestContext requestContext = RequestContext.getCurrentInstance();              
       requestContext.execute("PF('seleccionarFamiliar').hide()");
       this.usuarioSeleccionado=usuario;
       requestContext.update("form:nombreEmpleado"); 
       requestContext.update("form:dependenciaEmpleado");
       
    }
     public ArrayList<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(ArrayList<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
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
    public void cambiarTipoUsuario(ValueChangeEvent e)
    {
        String tipo=e.getNewValue().toString();
        if(tipo.equals("Seleccione"))
        {
            this.tablaEmpleado=false;
            this.tablaEstudiantes=false;
            this.tablaFamiliar=false;
        }
        if(tipo.equals("Empleado"))
        {
            this.tablaEmpleado=true;
            this.tablaEstudiantes=false;
            this.tablaFamiliar=false;
        }
    }
}





