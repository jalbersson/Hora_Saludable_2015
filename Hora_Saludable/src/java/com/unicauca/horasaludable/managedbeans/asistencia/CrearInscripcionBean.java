/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.managedbeans.asistencia;

import com.unicauca.horasaludable.entities.Detalleinscripcion;
import com.unicauca.horasaludable.entities.DetalleinscripcionPK;
import com.unicauca.horasaludable.entities.Inscripcion;
import com.unicauca.horasaludable.entities.Usuario;
import com.unicauca.horasaludable.jpacontrollers.DetalleinscripcionFacade;
import com.unicauca.horasaludable.jpacontrollers.InscripcionFacade;
import com.unicauca.horasaludable.jpacontrollers.UsuarioFacade;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author seven
 */
@ManagedBean
@ViewScoped
public class CrearInscripcionBean {

    @EJB
    private InscripcionFacade ejbFacadeIns;
    @EJB
    private UsuarioFacade ejbFacadeUsu;
    @EJB
    private DetalleinscripcionFacade ejbFacadeDetins;

    private Inscripcion ins;

    private List<Usuario> usuarios;

    private List<Usuario> selectedUsus;
    
    private List<Usuario> filteredUsus;

    private String accion = "Inscribir"; // "crear" "actualizar"

    /**
     * Creates a new instance of CrearInscripcionBean
     */
    public CrearInscripcionBean() {
        ins = new Inscripcion();
        // mes y año actual
        AuxiliarInscripcion ai = new AuxiliarInscripcion();
        ins.setInsmes(ai.getMes(0)); // con 0 se obtiene el mes actual
        ins.setInsanio(ai.getAnio());

        selectedUsus = new ArrayList();
    }

    @PostConstruct
    private void init() {
        AuxiliarInscripcion ai = new AuxiliarInscripcion();
        int mes = ai.getMM(ins.getInsmes());
        selectedUsus = ejbFacadeDetins.usariosActivos(true, String.valueOf(mes), ins.getInsanio());
        if (selectedUsus.isEmpty()) {
            accion = "Inscribir";
        } else {
            accion = "Actualizar";

        }
    }

    public void crearIncripcion() {
        FacesContext context = FacesContext.getCurrentInstance();

        if (!selectedUsus.isEmpty()) {
            AuxiliarInscripcion ai = new AuxiliarInscripcion();
            int mes = ai.getMM(ins.getInsmes());
            ins.setInsmes(String.valueOf(mes));

            if (accion.equals("Inscribir")) {
                ejbFacadeIns.create(ins);
            } else {
                ins = ejbFacadeIns.existeInscripcion(String.valueOf(mes), ins.getInsanio());
            }            

            for (int i = 0; i < usuarios.size(); i++) {
                crearDetalleinscripcion(usuarios.get(i));
            }

            if (ejbFacadeIns.find(ins.getInsid()) != null) {
                accion = "Actualizar";
                context.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, "Inscripcion Creada", ""));
            } else {
                accion = "Inscribir";
                context.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, "No ha inscrito usuarios", ""));
            }
        } else {
            context.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, "No ha inscrito usuarios", ""));
        }

    }

    public void buscarInscritos() {
        AuxiliarInscripcion ai = new AuxiliarInscripcion();
        int mes = ai.getMM(ins.getInsmes());
        selectedUsus = ejbFacadeDetins.usariosActivos(true, String.valueOf(mes), ins.getInsanio());
        usuarios = ejbFacadeUsu.findAll();
        if (selectedUsus.isEmpty()) {
            accion = "Inscribir";
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, "No Hay Inscritos" + accion, ""));
        } else {
            accion = "Actualizar";
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, "Busqueda Completa" + accion, ""));
        }

    }

    public void crearDetalleinscripcion(Usuario usu) {
        Detalleinscripcion detins = new Detalleinscripcion();
        detins.setDetalleinscripcionPK(new DetalleinscripcionPK());
        if (selectedUsus.contains(usu)) { // usuario selecionado para inscribirlo
            detins.setDetactivo(true); // true:activo
        } else {
            detins.setDetactivo(false); //false:inactivo
        }
        detins.setInscripcion(ins);
        detins.setUsuario(usu);
        detins.getDetalleinscripcionPK().setInsid(detins.getInscripcion().getInsid());
        detins.getDetalleinscripcionPK().setUsuid(detins.getUsuario().getUsuid());

        if (accion.equals("Inscribir")) {
            ejbFacadeDetins.create(detins);
        }
        if (accion.equals("Actualizar")) {
            ejbFacadeDetins.edit(detins);
        }
    }

    public Inscripcion getIns() {
        return ins;
    }

    public void setIns(Inscripcion ins) {
        this.ins = ins;
    }

    public InscripcionFacade getEjbFacadeIns() {
        return ejbFacadeIns;
    }

    public List<String> getMeses() {
        AuxiliarInscripcion ai = new AuxiliarInscripcion();
        return ai.getMeses();
    }

    public int getAnio() {
        AuxiliarInscripcion ai = new AuxiliarInscripcion();
        return ai.getAnio();
    }

    public List<Usuario> getUsuarios() {
        if (usuarios == null) {

            usuarios = ejbFacadeUsu.findAll();

            AuxiliarInscripcion ai = new AuxiliarInscripcion();
            int mes = ai.getMM(ins.getInsmes());
            selectedUsus = ejbFacadeDetins.usariosActivos(true, String.valueOf(mes), ins.getInsanio());

            if (selectedUsus.isEmpty()) {
                accion = "Inscribir";
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, "No Hay Inscritos", ""));
            } else {
                accion = "Actualizar";
            }

            return usuarios;
        }
        return usuarios;
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

    public void setAccion(String accion) {
        this.accion = accion;
    }

}
