/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.asistenciaManagedbeans;

import com.unicauca.horasaludable.asistenciaDomain.Opcion;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author seven
 */
@ManagedBean
@ViewScoped
public class CarouselView {
    private List<Opcion> opciones;
    private Opcion selectedOpcion;

    /**
     * Creates a new instance of RingView
     */
    @PostConstruct
    public void init() {
        opciones = new ArrayList<Opcion>();
        opciones.add(new Opcion("Lista de Inscritos", "lista-inscritos", "registro/inscritos"));
        opciones.add(new Opcion("Registro Asistencia", "registro-asistencia", "registro/registro-asistencia"));
        opciones.add(new Opcion("Reporte: Asistencia General", "reportes", "reporte/reporte-asistencia-inscritos"));
        opciones.add(new Opcion("Reporte Asistencia por Dependencia", "reportes", "reporte/reporte-asistencia-dependencias-universirarias"));
        opciones.add(new Opcion("Reporte: Asistencia de Usuarios", "reporte-usuarios", "reporte/reporte-asistencia-usuarios"));
//        opciones.add(new Opcion("Reporte: Asistencia de Usuario", "AGF_greene", "control-asistencia"));
    }

    public List<Opcion> getOpciones() {
        return opciones;
    }

    public void setOpciones(List<Opcion> opciones) {
        this.opciones = opciones;
    }

    public Opcion getSelectedOpcion() {
        return selectedOpcion;
    }

    public void setSelectedOpcion(Opcion selectedOpcion) {
        this.selectedOpcion = selectedOpcion;
    }
    
}
