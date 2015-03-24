/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.asistencia.jsf.managedbeans;

import com.unicauca.asistencia.domain.Inscrito;
import com.unicauca.asistencia.service.InscritoService;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author seven
 */
@ManagedBean(name="inscritoBean")
@ViewScoped
public class InscritoBean {
    
    private List<Inscrito> inscritos;
    private List<Inscrito> selectedIncritos;
    private Inscrito selectedInscrito;
    
    private InscritoService service;
    /**
     * Creates a new instance of InscritoBean
     */
    @PostConstruct
    public void init() {
        service = new InscritoService();
        inscritos = service.crearInscritos();
    }
//    public InscritoBean() {
//        inscritos = new ArrayList<Inscrito>();
//        inscritos = service.crearInscritos();
//    }

    public List<Inscrito> getInscritos() {
        return inscritos;
    }

    public void setInscritos(List<Inscrito> inscritos) {
        this.inscritos = inscritos;
    }   

    public List<Inscrito> getSelectedIncritos() {
        return selectedIncritos;
    }

    public void setSelectedIncritos(List<Inscrito> selectedIncritos) {
        this.selectedIncritos = selectedIncritos;
    }

    public Inscrito getSelectedInscrito() {
        return selectedInscrito;
    }

    public void setSelectedInscrito(Inscrito selectedIncrito) {
        this.selectedInscrito = selectedIncrito;
    }
    
}
