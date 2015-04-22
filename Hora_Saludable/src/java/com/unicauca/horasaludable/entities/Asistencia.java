/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author seven
 */
@Entity
@Table(name = "ASISTENCIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Asistencia.findAll", query = "SELECT a FROM Asistencia a"),
    @NamedQuery(name = "Asistencia.findByAsiid", query = "SELECT a FROM Asistencia a WHERE a.asiid = :asiid"),
    @NamedQuery(name = "Asistencia.findByAsifecha", query = "SELECT a FROM Asistencia a WHERE a.asifecha = :asifecha"),
    @NamedQuery(name = "Asistencia.findByAsiobservaciones", query = "SELECT a FROM Asistencia a WHERE a.asiobservaciones = :asiobservaciones")})
public class Asistencia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ASIID")
    private Long asiid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ASIFECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date asifecha;
    @Size(max = 400)
    @Column(name = "ASIOBSERVACIONES")
    private String asiobservaciones;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "asistencia")
    private List<Detalleasistencia> detalleasistenciaList;

    public Asistencia() {
    }

    public Asistencia(Long asiid) {
        this.asiid = asiid;
    }

    public Asistencia(Long asiid, Date asifecha) {
        this.asiid = asiid;
        this.asifecha = asifecha;
    }

    public Long getAsiid() {
        return asiid;
    }

    public void setAsiid(Long asiid) {
        this.asiid = asiid;
    }

    public Date getAsifecha() {
        return asifecha;
    }

    public void setAsifecha(Date asifecha) {
        this.asifecha = asifecha;
    }

    public String getAsiobservaciones() {
        return asiobservaciones;
    }

    public void setAsiobservaciones(String asiobservaciones) {
        this.asiobservaciones = asiobservaciones;
    }

    @XmlTransient
    public List<Detalleasistencia> getDetalleasistenciaList() {
        return detalleasistenciaList;
    }

    public void setDetalleasistenciaList(List<Detalleasistencia> detalleasistenciaList) {
        this.detalleasistenciaList = detalleasistenciaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (asiid != null ? asiid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asistencia)) {
            return false;
        }
        Asistencia other = (Asistencia) object;
        if ((this.asiid == null && other.asiid != null) || (this.asiid != null && !this.asiid.equals(other.asiid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.horasaludable.entities.Asistencia[ asiid=" + asiid + " ]";
    }
    
}
