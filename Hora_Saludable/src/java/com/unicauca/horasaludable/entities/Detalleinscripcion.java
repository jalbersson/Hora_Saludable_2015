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
@Table(name = "DETALLEINSCRIPCION", catalog = "asae", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detalleinscripcion.findAll", query = "SELECT d FROM Detalleinscripcion d"),
    @NamedQuery(name = "Detalleinscripcion.findByUsuid", query = "SELECT d FROM Detalleinscripcion d WHERE d.detalleinscripcionPK.usuid = :usuid"),
    @NamedQuery(name = "Detalleinscripcion.findByInsid", query = "SELECT d FROM Detalleinscripcion d WHERE d.detalleinscripcionPK.insid = :insid"),
    @NamedQuery(name = "Detalleinscripcion.findByDetactivo", query = "SELECT d FROM Detalleinscripcion d WHERE d.detactivo = :detactivo")})
public class Detalleinscripcion implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetalleinscripcionPK detalleinscripcionPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DETACTIVO")
    private boolean detactivo;
    @JoinColumn(name = "USUID", referencedColumnName = "USUID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario;
    @JoinColumn(name = "INSID", referencedColumnName = "INSID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Inscripcion inscripcion;

    public Detalleinscripcion() {
    }

    public Detalleinscripcion(DetalleinscripcionPK detalleinscripcionPK) {
        this.detalleinscripcionPK = detalleinscripcionPK;
    }

    public Detalleinscripcion(DetalleinscripcionPK detalleinscripcionPK, boolean detactivo) {
        this.detalleinscripcionPK = detalleinscripcionPK;
        this.detactivo = detactivo;
    }

    public Detalleinscripcion(long usuid, long insid) {
        this.detalleinscripcionPK = new DetalleinscripcionPK(usuid, insid);
    }

    public DetalleinscripcionPK getDetalleinscripcionPK() {
        return detalleinscripcionPK;
    }

    public void setDetalleinscripcionPK(DetalleinscripcionPK detalleinscripcionPK) {
        this.detalleinscripcionPK = detalleinscripcionPK;
    }

    public boolean getDetactivo() {
        return detactivo;
    }

    public void setDetactivo(boolean detactivo) {
        this.detactivo = detactivo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Inscripcion getInscripcion() {
        return inscripcion;
    }

    public void setInscripcion(Inscripcion inscripcion) {
        this.inscripcion = inscripcion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detalleinscripcionPK != null ? detalleinscripcionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detalleinscripcion)) {
            return false;
        }
        Detalleinscripcion other = (Detalleinscripcion) object;
        if ((this.detalleinscripcionPK == null && other.detalleinscripcionPK != null) || (this.detalleinscripcionPK != null && !this.detalleinscripcionPK.equals(other.detalleinscripcionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.horasaludable.entities.Detalleinscripcion[ detalleinscripcionPK=" + detalleinscripcionPK + " ]";
    }
    
}
