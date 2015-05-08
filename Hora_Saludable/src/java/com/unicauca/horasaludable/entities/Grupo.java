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
 * @author geovanny
 */
@Entity
@Table(name = "GRUPO", catalog = "asae", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Grupo.findAll", query = "SELECT g FROM Grupo g"),
    @NamedQuery(name = "Grupo.findByGruid", query = "SELECT g FROM Grupo g WHERE g.gruid = :gruid"),
    @NamedQuery(name = "Grupo.findByGrudescripcion", query = "SELECT g FROM Grupo g WHERE g.grudescripcion = :grudescripcion")})
public class Grupo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "GRUID")
    private String gruid;
    @Size(max = 255)
    @Column(name = "GRUDESCRIPCION")
    private String grudescripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "grupo")
    private List<Usuariogrupo> usuariogrupoList;

    public Grupo() {
    }

    public Grupo(String gruid) {
        this.gruid = gruid;
    }

    public String getGruid() {
        return gruid;
    }

    public void setGruid(String gruid) {
        this.gruid = gruid;
    }

    public String getGrudescripcion() {
        return grudescripcion;
    }

    public void setGrudescripcion(String grudescripcion) {
        this.grudescripcion = grudescripcion;
    }

    @XmlTransient
    public List<Usuariogrupo> getUsuariogrupoList() {
        return usuariogrupoList;
    }

    public void setUsuariogrupoList(List<Usuariogrupo> usuariogrupoList) {
        this.usuariogrupoList = usuariogrupoList;
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
        return "modelo.Grupo[ gruid=" + gruid + " ]";
    }
    
}
