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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "UNIDADACADEMICA", catalog = "asae", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Unidadacademica.findAll", query = "SELECT u FROM Unidadacademica u"),
    @NamedQuery(name = "Unidadacademica.findByUniid", query = "SELECT u FROM Unidadacademica u WHERE u.uniid = :uniid"),
    @NamedQuery(name = "Unidadacademica.findByUninombre", query = "SELECT u FROM Unidadacademica u WHERE u.uninombre = :uninombre")})
public class Unidadacademica implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "UNIID")
    private Long uniid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 75)
    @Column(name = "UNINOMBRE")
    private String uninombre;
    @OneToMany(mappedBy = "uniid")
    private List<Usuario> usuarioList;
    @JoinColumn(name = "TIPID", referencedColumnName = "TIPID")
    @ManyToOne(optional = false)
    private Tipounidadacademica tipid;

    public Unidadacademica() {
    }

    public Unidadacademica(Long uniid) {
        this.uniid = uniid;
    }

    public Unidadacademica(Long uniid, String uninombre) {
        this.uniid = uniid;
        this.uninombre = uninombre;
    }

    public Long getUniid() {
        return uniid;
    }

    public void setUniid(Long uniid) {
        this.uniid = uniid;
    }

    public String getUninombre() {
        return uninombre;
    }

    public void setUninombre(String uninombre) {
        this.uninombre = uninombre;
    }

    @XmlTransient
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    public Tipounidadacademica getTipid() {
        return tipid;
    }

    public void setTipid(Tipounidadacademica tipid) {
        this.tipid = tipid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (uniid != null ? uniid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Unidadacademica)) {
            return false;
        }
        Unidadacademica other = (Unidadacademica) object;
        if ((this.uniid == null && other.uniid != null) || (this.uniid != null && !this.uniid.equals(other.uniid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.horasaludable.entities.Unidadacademica[ uniid=" + uniid + " ]";
    }
    
}
