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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author seven
 */
@Entity
@Table(name = "HORARIO", catalog = "asae", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Horario.findAll", query = "SELECT h FROM Horario h"),
    @NamedQuery(name = "Horario.findByHordia", query = "SELECT h FROM Horario h WHERE h.horarioPK.hordia = :hordia"),
    @NamedQuery(name = "Horario.findByHorhora", query = "SELECT h FROM Horario h WHERE h.horarioPK.horhora = :horhora"),
    @NamedQuery(name = "Horario.findByHordescripcion", query = "SELECT h FROM Horario h WHERE h.hordescripcion = :hordescripcion")})
public class Horario implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected HorarioPK horarioPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "HORDESCRIPCION")
    private String hordescripcion;

    public Horario() {
    }

    public Horario(HorarioPK horarioPK) {
        this.horarioPK = horarioPK;
    }

    public Horario(HorarioPK horarioPK, String hordescripcion) {
        this.horarioPK = horarioPK;
        this.hordescripcion = hordescripcion;
    }

    public Horario(String hordia, int horhora) {
        this.horarioPK = new HorarioPK(hordia, horhora);
    }

    public HorarioPK getHorarioPK() {
        return horarioPK;
    }

    public void setHorarioPK(HorarioPK horarioPK) {
        this.horarioPK = horarioPK;
    }

    public String getHordescripcion() {
        return hordescripcion;
    }

    public void setHordescripcion(String hordescripcion) {
        this.hordescripcion = hordescripcion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (horarioPK != null ? horarioPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Horario)) {
            return false;
        }
        Horario other = (Horario) object;
        if ((this.horarioPK == null && other.horarioPK != null) || (this.horarioPK != null && !this.horarioPK.equals(other.horarioPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.horasaludable.entities.Horario[ horarioPK=" + horarioPK + " ]";
    }
    
}
