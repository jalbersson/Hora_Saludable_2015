/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author geovanny
 */
@Entity
@Table(name = "RECUPERARCONTRASENA", catalog = "asae", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Recuperarcontrasena.findAll", query = "SELECT r FROM Recuperarcontrasena r"),
    @NamedQuery(name = "Recuperarcontrasena.findByReid", query = "SELECT r FROM Recuperarcontrasena r WHERE r.reid = :reid"),
    @NamedQuery(name = "Recuperarcontrasena.findByReidcifrado", query = "SELECT r FROM Recuperarcontrasena r WHERE r.reidcifrado = :reidcifrado")})
public class Recuperarcontrasena implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "REID")
    private Long reid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "REIDCIFRADO")
    private String reidcifrado;

    public Recuperarcontrasena() {
    }

    public Recuperarcontrasena(Long reid) {
        this.reid = reid;
    }

    public Recuperarcontrasena(Long reid, String reidcifrado) {
        this.reid = reid;
        this.reidcifrado = reidcifrado;
    }

    public Long getReid() {
        return reid;
    }

    public void setReid(Long reid) {
        this.reid = reid;
    }

    public String getReidcifrado() {
        return reidcifrado;
    }

    public void setReidcifrado(String reidcifrado) {
        this.reidcifrado = reidcifrado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reid != null ? reid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Recuperarcontrasena)) {
            return false;
        }
        Recuperarcontrasena other = (Recuperarcontrasena) object;
        if ((this.reid == null && other.reid != null) || (this.reid != null && !this.reid.equals(other.reid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Recuperarcontrasena[ reid=" + reid + " ]";
    }
    
}
