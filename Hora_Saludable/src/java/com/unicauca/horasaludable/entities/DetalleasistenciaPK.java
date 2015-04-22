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

/**
 *
 * @author seven
 */
@Embeddable
public class DetalleasistenciaPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "ASIID")
    private long asiid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "USUID")
    private long usuid;

    public DetalleasistenciaPK() {
    }

    public DetalleasistenciaPK(long asiid, long usuid) {
        this.asiid = asiid;
        this.usuid = usuid;
    }

    public long getAsiid() {
        return asiid;
    }

    public void setAsiid(long asiid) {
        this.asiid = asiid;
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
        hash += (int) asiid;
        hash += (int) usuid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleasistenciaPK)) {
            return false;
        }
        DetalleasistenciaPK other = (DetalleasistenciaPK) object;
        if (this.asiid != other.asiid) {
            return false;
        }
        if (this.usuid != other.usuid) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.horasaludable.entities.DetalleasistenciaPK[ asiid=" + asiid + ", usuid=" + usuid + " ]";
    }
    
}
