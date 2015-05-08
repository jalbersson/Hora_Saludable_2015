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
 * @author geovanny
 */
@Embeddable
public class UsuariogrupoPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "GRUID")
    private String gruid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "USUID")
    private long usuid;

    public UsuariogrupoPK() {
    }

    public UsuariogrupoPK(String gruid, long usuid) {
        this.gruid = gruid;
        this.usuid = usuid;
    }

    public String getGruid() {
        return gruid;
    }

    public void setGruid(String gruid) {
        this.gruid = gruid;
    }

    public long getUsuid() {
        return usuid;
    }

    public void setUsuid(long usuid) {
        this.usuid = usuid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (gruid != null ? gruid.hashCode() : 0);
        hash += (int) usuid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuariogrupoPK)) {
            return false;
        }
        UsuariogrupoPK other = (UsuariogrupoPK) object;
        if ((this.gruid == null && other.gruid != null) || (this.gruid != null && !this.gruid.equals(other.gruid))) {
            return false;
        }
        if (this.usuid != other.usuid) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.UsuariogrupoPK[ gruid=" + gruid + ", usuid=" + usuid + " ]";
    }
    
}
