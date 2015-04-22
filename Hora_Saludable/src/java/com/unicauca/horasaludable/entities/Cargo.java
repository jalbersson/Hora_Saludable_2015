/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
@Table(name = "CARGO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cargo.findAll", query = "SELECT c FROM Cargo c"),
    @NamedQuery(name = "Cargo.findByCarid", query = "SELECT c FROM Cargo c WHERE c.carid = :carid"),
    @NamedQuery(name = "Cargo.findByCarnombre", query = "SELECT c FROM Cargo c WHERE c.carnombre = :carnombre")})
public class Cargo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CARID")
    private Long carid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 75)
    @Column(name = "CARNOMBRE")
    private String carnombre;
    @OneToMany(mappedBy = "carid")
    private List<Usuario> usuarioList;

    public Cargo() {
    }

    public Cargo(Long carid) {
        this.carid = carid;
    }

    public Cargo(Long carid, String carnombre) {
        this.carid = carid;
        this.carnombre = carnombre;
    }

    public Long getCarid() {
        return carid;
    }

    public void setCarid(Long carid) {
        this.carid = carid;
    }

    public String getCarnombre() {
        return carnombre;
    }

    public void setCarnombre(String carnombre) {
        this.carnombre = carnombre;
    }

    @XmlTransient
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (carid != null ? carid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cargo)) {
            return false;
        }
        Cargo other = (Cargo) object;
        if ((this.carid == null && other.carid != null) || (this.carid != null && !this.carid.equals(other.carid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.horasaludable.entities.Cargo[ carid=" + carid + " ]";
    }
    
}
