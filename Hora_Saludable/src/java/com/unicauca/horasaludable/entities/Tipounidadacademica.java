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
@Table(name = "TIPOUNIDADACADEMICA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipounidadacademica.findAll", query = "SELECT t FROM Tipounidadacademica t"),
    @NamedQuery(name = "Tipounidadacademica.findByTipid", query = "SELECT t FROM Tipounidadacademica t WHERE t.tipid = :tipid"),
    @NamedQuery(name = "Tipounidadacademica.findByTipnombre", query = "SELECT t FROM Tipounidadacademica t WHERE t.tipnombre = :tipnombre")})
public class Tipounidadacademica implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "TIPID")
    private Long tipid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 75)
    @Column(name = "TIPNOMBRE")
    private String tipnombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipid")
    private List<Unidadacademica> unidadacademicaList;

    public Tipounidadacademica() {
    }

    public Tipounidadacademica(Long tipid) {
        this.tipid = tipid;
    }

    public Tipounidadacademica(Long tipid, String tipnombre) {
        this.tipid = tipid;
        this.tipnombre = tipnombre;
    }

    public Long getTipid() {
        return tipid;
    }

    public void setTipid(Long tipid) {
        this.tipid = tipid;
    }

    public String getTipnombre() {
        return tipnombre;
    }

    public void setTipnombre(String tipnombre) {
        this.tipnombre = tipnombre;
    }

    @XmlTransient
    public List<Unidadacademica> getUnidadacademicaList() {
        return unidadacademicaList;
    }

    public void setUnidadacademicaList(List<Unidadacademica> unidadacademicaList) {
        this.unidadacademicaList = unidadacademicaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipid != null ? tipid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipounidadacademica)) {
            return false;
        }
        Tipounidadacademica other = (Tipounidadacademica) object;
        if ((this.tipid == null && other.tipid != null) || (this.tipid != null && !this.tipid.equals(other.tipid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.horasaludable.entities.Tipounidadacademica[ tipid=" + tipid + " ]";
    }
    
}
