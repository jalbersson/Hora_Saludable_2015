/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.contenidos.java;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import com.unicauca.horasaludable.contenidos.java.Horario;
import com.unicauca.horasaludable.contenidos.java.ServicioHorario;

/**
 *
 * @author Yuri
 */
@ManagedBean(name="dtEditHorario")
@ViewScoped
public class EditHorario implements Serializable
{
    private List<Horario> cars1;
    private List<Horario> cars2;
         
    @ManagedProperty("#{carService}")
    private ServicioHorario service;
     
    @PostConstruct
    public void init() {
        cars1 = service.createHorario(5);
        cars2 = service.createHorario(5);
    }
 
    public List<Horario> getCars1() {
        return cars1;
    }
 
    public List<Horario> getCars2() {
        return cars2;
    }
     
    public List<String> getLunes() {
        return service.getLunes();
    }
     
    public List<String> getMartes() {
        return service.getMartes();
    }
    
    public List<String> getMiercoles() {
        return service.getMiercoles();
    }
    
    public List<String> getJueves() {
        return service.getJueves();
    }
    
    public List<String> getViernes() {
        return service.getViernes();
    }
 
    public void setService(ServicioHorario service) {
        this.service = service;
    }
     
    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Horario editado", ((Horario) event.getObject()).getId());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edicion cancelada", ((Horario) event.getObject()).getId());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
         
        if(newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
}
