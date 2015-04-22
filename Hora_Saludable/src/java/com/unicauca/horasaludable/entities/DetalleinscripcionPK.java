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
public class DetalleinscripcionPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "USUID")
    private long usuid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "INSID")
    private long insid;

    public DetalleinscripcionPK() {
    }

    public DetalleinscripcionPK(long usuid, long insid) {
        this.usuid = usuid;
        this.insid = insid;
    }

    public long getUsuid() {
        return usuid;
    }

    public void setUsuid(long usuid) {
        this.usuid = usuid;
    }

    public long getInsid() {
        return insid;
    }

    public void setInsid(long insid) {
        this.insid = insid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) usuid;
        hash += (int) insid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleinscripcionPK)) {
            return false;
        }
        DetalleinscripcionPK other = (DetalleinscripcionPK) object;
        if (this.usuid != other.usuid) {
            return false;
        }
        if (this.insid != other.insid) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.horasaludable.entities.DetalleinscripcionPK[ usuid=" + usuid + ", insid=" + insid + " ]";
    }
    
}
