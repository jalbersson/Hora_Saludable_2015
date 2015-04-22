/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author seven
 */
@Embeddable
public class HorarioPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "HORDIA")
    private String hordia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "HORHORA")
    private int horhora;

    public HorarioPK() {
    }

    public HorarioPK(String hordia, int horhora) {
        this.hordia = hordia;
        this.horhora = horhora;
    }

    public String getHordia() {
        return hordia;
    }

    public void setHordia(String hordia) {
        this.hordia = hordia;
    }

    public int getHorhora() {
        return horhora;
    }

    public void setHorhora(int horhora) {
        this.horhora = horhora;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hordia != null ? hordia.hashCode() : 0);
        hash += (int) horhora;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HorarioPK)) {
            return false;
        }
        HorarioPK other = (HorarioPK) object;
        if ((this.hordia == null && other.hordia != null) || (this.hordia != null && !this.hordia.equals(other.hordia))) {
            return false;
        }
        if (this.horhora != other.horhora) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.horasaludable.entities.HorarioPK[ hordia=" + hordia + ", horhora=" + horhora + " ]";
    }
    
}
