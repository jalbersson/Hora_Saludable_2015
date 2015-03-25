/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.asistenciaManagedbeans;

import java.util.Date;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author seven
 */
public class DateBean {

    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void handleDateSelect(SelectEvent event) {
        date = (Date) event.getObject();
        //Add facesmessage
    }
}
