/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.managedbeans.asistencia;

import com.unicauca.horasaludable.entities.Asistencia;
import com.unicauca.horasaludable.entities.Detalleasistencia;
import com.unicauca.horasaludable.entities.DetalleasistenciaPK;
import com.unicauca.horasaludable.entities.Usuario;
import com.unicauca.horasaludable.jpacontrollers.AsistenciaFacade;
import com.unicauca.horasaludable.jpacontrollers.DetalleasistenciaFacade;
import com.unicauca.horasaludable.jpacontrollers.DetalleinscripcionFacade;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author seven
 */
@ManagedBean
@ViewScoped
public class AsistenciaView {

    @EJB
    private AsistenciaFacade ejbFacadeAsi;
    @EJB
    private DetalleinscripcionFacade ejbFacadeDetins;
    @EJB
    private DetalleasistenciaFacade ejbFacadeDetasi;

    private Asistencia asi;

    private List<Usuario> usuarios;

    private List<Usuario> selectedUsus;

    private List<Usuario> filteredUsus;

    private String accion = "Registrar Asistencia";

    /**
     * Creates a new instance of AsistenciaView
     */
    public AsistenciaView() {
        asi = new Asistencia();

        asi.setAsifecha(new Date());
        asi.setAsiobservaciones(new String());

        selectedUsus = new ArrayList();
    }

    @PostConstruct
    private void init() {
        AuxiliarInscripcion ai = new AuxiliarInscripcion();

        List<Asistencia> asis = ejbFacadeAsi.findAll();
        Date date = new Date();
        for (int i = 0; i < asis.size(); i++) {
            if (asis.get(i).getAsifecha().getYear() == date.getYear()
                    && asis.get(i).getAsifecha().getMonth() == date.getMonth()
                    && asis.get(i).getAsifecha().getDay() == date.getDay()) {
                asi = asis.get(i);
                accion = "Actualizar Asistencia";

                List<Detalleasistencia> detasis = asi.getDetalleasistenciaList();
                for (int j = 0; j < detasis.size(); j++) {
                    if (detasis.get(j).getDetasistio()) {
                        selectedUsus.add(detasis.get(j).getUsuario());
                    }
                }
                return;
            }
        }
        accion = "Registrar Asistencia";

        System.out.println(asi.getAsifecha().getYear() + "***" + date.getYear());
        System.out.println(asi.getAsifecha().getMonth() + "***" + date.getMonth());
        System.out.println(asi.getAsifecha().getDay() + "***" + date.getDay());
    }

    public void crearAsistencia() {
        FacesContext context = FacesContext.getCurrentInstance();

        if (accion.equals("Registrar Asistencia")) {
            ejbFacadeAsi.create(asi);
        }

        for (int i = 0; i < usuarios.size(); i++) {
            crearDetalleasistencia(usuarios.get(i));
        }
        context.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, "Asistencia Creada", ""));
    }

    public void crearDetalleasistencia(Usuario usu) {
        Detalleasistencia detasi = new Detalleasistencia();
        detasi.setDetalleasistenciaPK(new DetalleasistenciaPK());

        if (selectedUsus.contains(usu)) { // usuario selecionado para registrar asistencia
            detasi.setDetasistio(true); // true:asistio
        } else {
            detasi.setDetasistio(false); //false:no asistio
        }
        detasi.setAsistencia(asi);
        detasi.setUsuario(usu);
        detasi.getDetalleasistenciaPK().setAsiid(detasi.getAsistencia().getAsiid());
        detasi.getDetalleasistenciaPK().setUsuid(detasi.getUsuario().getUsuid());

        if (accion.equals("Registrar Asistencia")) {
            ejbFacadeDetasi.create(detasi);
        }
        if (accion.equals("Actualizar Asistencia")) {
            ejbFacadeDetasi.edit(detasi);
        }
    }

    public void buscarAsistencia() {
        List<Asistencia> asis = ejbFacadeAsi.findAll();

        for (int i = 0; i < asis.size(); i++) {
            if (asis.get(i).getAsifecha().getYear() == asi.getAsifecha().getYear()
                    && asis.get(i).getAsifecha().getMonth() == asi.getAsifecha().getMonth()
                    && asis.get(i).getAsifecha().getDay() == asi.getAsifecha().getDay()) {
                asi = asis.get(i);
                accion = "Actualizar Asistencia";

                List<Detalleasistencia> detasis = asi.getDetalleasistenciaList();
                for (int j = 0; j < detasis.size(); j++) {
                    if (detasis.get(j).getDetasistio()) {
                        selectedUsus.add(detasis.get(j).getUsuario());
                    }
                }
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, "Busqueda Completa++", ""));
                return;
            }
        }
        selectedUsus = new ArrayList();
        accion = "Registrar Asistencia";
    }

    public Asistencia getAsi() {
        return asi;
    }

    public void setAsi(Asistencia asi) {
        this.asi = asi;
    }

    public AsistenciaFacade getEjbFacadeAsi() {
        return ejbFacadeAsi;
    }

    public List<Usuario> getUsuarios() {
        if (usuarios == null) {
            AuxiliarInscripcion ai = new AuxiliarInscripcion();

            usuarios = ejbFacadeDetins.usariosActivos(true, String.valueOf(ai.getMM(null)), ai.getAnio());
        }
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Usuario> getSelectedUsus() {
        return selectedUsus;
    }

    public void setSelectedUsus(List<Usuario> selectedUsus) {
        this.selectedUsus = selectedUsus;
    }

    public List<Usuario> getFilteredUsus() {
        return filteredUsus;
    }

    public void setFilteredUsus(List<Usuario> filteredUsus) {
        this.filteredUsus = filteredUsus;
    }

    public String getAccion() {
        return accion;
    }
}
