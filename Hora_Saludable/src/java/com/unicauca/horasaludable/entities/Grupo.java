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
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "GRUPO", catalog = "asae", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Grupo.findAll", query = "SELECT g FROM Grupo g"),
    @NamedQuery(name = "Grupo.findByGruid", query = "SELECT g FROM Grupo g WHERE g.gruid = :gruid"),
    @NamedQuery(name = "Grupo.findByGrunombre", query = "SELECT g FROM Grupo g WHERE g.grunombre = :grunombre"),
    @NamedQuery(name = "Grupo.findByGrudescripcion", query = "SELECT g FROM Grupo g WHERE g.grudescripcion = :grudescripcion")})
public class Grupo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "GRUID")
    private Long gruid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 75)
    @Column(name = "GRUNOMBRE")
    private String grunombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "GRUDESCRIPCION")
    private String grudescripcion;
    @ManyToMany(mappedBy = "grupoList")
    private List<Usuario> usuarioList;

    public Grupo() {
    }

    public Grupo(Long gruid) {
        this.gruid = gruid;
    }

    public Grupo(Long gruid, String grunombre, String grudescripcion) {
        this.gruid = gruid;
        this.grunombre = grunombre;
        this.grudescripcion = grudescripcion;
    }

    public Long getGruid() {
        return gruid;
    }

    public void setGruid(Long gruid) {
        this.gruid = gruid;
    }

    public String getGrunombre() {
        return grunombre;
    }

    public void setGrunombre(String grunombre) {
        this.grunombre = grunombre;
    }

    public String getGrudescripcion() {
        return grudescripcion;
    }

    public void setGrudescripcion(String grudescripcion) {
        this.grudescripcion = grudescripcion;
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
        hash += (gruid != null ? gruid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Grupo)) {
            return false;
        }
        Grupo other = (Grupo) object;
        if ((this.gruid == null && other.gruid != null) || (this.gruid != null && !this.gruid.equals(other.gruid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.horasaludable.entities.Grupo[ gruid=" + gruid + " ]";
    }
    
}
