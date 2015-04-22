/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author seven
 */
@Entity
@Table(name = "DETALLEASISTENCIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detalleasistencia.findAll", query = "SELECT d FROM Detalleasistencia d"),
    @NamedQuery(name = "Detalleasistencia.findByAsiid", query = "SELECT d FROM Detalleasistencia d WHERE d.detalleasistenciaPK.asiid = :asiid"),
    @NamedQuery(name = "Detalleasistencia.findByUsuid", query = "SELECT d FROM Detalleasistencia d WHERE d.detalleasistenciaPK.usuid = :usuid"),
    @NamedQuery(name = "Detalleasistencia.findByDetasistio", query = "SELECT d FROM Detalleasistencia d WHERE d.detasistio = :detasistio")})
public class Detalleasistencia implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetalleasistenciaPK detalleasistenciaPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DETASISTIO")
    private boolean detasistio;
    @JoinColumn(name = "USUID", referencedColumnName = "USUID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario;
    @JoinColumn(name = "ASIID", referencedColumnName = "ASIID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Asistencia asistencia;

    public Detalleasistencia() {
    }

    public Detalleasistencia(DetalleasistenciaPK detalleasistenciaPK) {
        this.detalleasistenciaPK = detalleasistenciaPK;
    }

    public Detalleasistencia(DetalleasistenciaPK detalleasistenciaPK, boolean detasistio) {
        this.detalleasistenciaPK = detalleasistenciaPK;
        this.detasistio = detasistio;
    }

    public Detalleasistencia(long asiid, long usuid) {
        this.detalleasistenciaPK = new DetalleasistenciaPK(asiid, usuid);
    }

    public DetalleasistenciaPK getDetalleasistenciaPK() {
        return detalleasistenciaPK;
    }

    public void setDetalleasistenciaPK(DetalleasistenciaPK detalleasistenciaPK) {
        this.detalleasistenciaPK = detalleasistenciaPK;
    }

    public boolean getDetasistio() {
        return detasistio;
    }

    public void setDetasistio(boolean detasistio) {
        this.detasistio = detasistio;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Asistencia getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(Asistencia asistencia) {
        this.asistencia = asistencia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detalleasistenciaPK != null ? detalleasistenciaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detalleasistencia)) {
            return false;
        }
        Detalleasistencia other = (Detalleasistencia) object;
        if ((this.detalleasistenciaPK == null && other.detalleasistenciaPK != null) || (this.detalleasistenciaPK != null && !this.detalleasistenciaPK.equals(other.detalleasistenciaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.horasaludable.entities.Detalleasistencia[ detalleasistenciaPK=" + detalleasistenciaPK + " ]";
    }
    
}
