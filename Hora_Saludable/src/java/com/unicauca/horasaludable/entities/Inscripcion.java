/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.entities;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author seven
 */
@Entity
@Table(name = "INSCRIPCION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Inscripcion.findAll", query = "SELECT i FROM Inscripcion i"),
    @NamedQuery(name = "Inscripcion.findByInsid", query = "SELECT i FROM Inscripcion i WHERE i.insid = :insid"),
    @NamedQuery(name = "Inscripcion.findByInsmes", query = "SELECT i FROM Inscripcion i WHERE i.insmes = :insmes"),
    @NamedQuery(name = "Inscripcion.findByInsanio", query = "SELECT i FROM Inscripcion i WHERE i.insanio = :insanio")})
public class Inscripcion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "INSID")
    private Long insid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "INSMES")
    private String insmes;
    @Basic(optional = false)
    @NotNull
    @Column(name = "INSANIO")
    private int insanio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "inscripcion")
    private List<Detalleinscripcion> detalleinscripcionList;

    public Inscripcion() {
    }

    public Inscripcion(Long insid) {
        this.insid = insid;
    }

    public Inscripcion(Long insid, String insmes, int insanio) {
        this.insid = insid;
        this.insmes = insmes;
        this.insanio = insanio;
    }

    public Long getInsid() {
        return insid;
    }

    public void setInsid(Long insid) {
        this.insid = insid;
    }

    public String getInsmes() {
        return insmes;
    }

    public void setInsmes(String insmes) {
        this.insmes = insmes;
    }

    public int getInsanio() {
        return insanio;
    }

    public void setInsanio(int insanio) {
        this.insanio = insanio;
    }

    @XmlTransient
    public List<Detalleinscripcion> getDetalleinscripcionList() {
        return detalleinscripcionList;
    }

    public void setDetalleinscripcionList(List<Detalleinscripcion> detalleinscripcionList) {
        this.detalleinscripcionList = detalleinscripcionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (insid != null ? insid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Inscripcion)) {
            return false;
        }
        Inscripcion other = (Inscripcion) object;
        if ((this.insid == null && other.insid != null) || (this.insid != null && !this.insid.equals(other.insid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.horasaludable.entities.Inscripcion[ insid=" + insid + " ]";
    }
    
}
