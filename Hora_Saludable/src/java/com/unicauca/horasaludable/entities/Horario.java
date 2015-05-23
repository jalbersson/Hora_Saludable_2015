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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JuanJose
 */
@Entity
@Table(name = "HORARIO", catalog = "asae", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Horario.findAll", query = "SELECT h FROM Horario h"),
    @NamedQuery(name = "Horario.findByHorid", query = "SELECT h FROM Horario h WHERE h.horid = :horid"),
    @NamedQuery(name = "Horario.findByHornombre", query = "SELECT h FROM Horario h WHERE h.hornombre = :hornombre"),
    @NamedQuery(name = "Horario.findByHorcontenido", query = "SELECT h FROM Horario h WHERE h.horcontenido = :horcontenido")})
public class Horario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "HORID")
    private Integer horid;
    @Size(max = 100)
    @Column(name = "HORNOMBRE")
    private String hornombre;
    @Size(max = 10000)
    @Column(name = "HORCONTENIDO")
    private String horcontenido;

    public Horario() {
    }

    public Horario(Integer horid) {
        this.horid = horid;
    }

    public Integer getHorid() {
        return horid;
    }

    public void setHorid(Integer horid) {
        this.horid = horid;
    }

    public String getHornombre() {
        return hornombre;
    }

    public void setHornombre(String hornombre) {
        this.hornombre = hornombre;
    }

    public String getHorcontenido() {
        return horcontenido;
    }

    public void setHorcontenido(String horcontenido) {
        this.horcontenido = horcontenido;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (horid != null ? horid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Horario)) {
            return false;
        }
        Horario other = (Horario) object;
        if ((this.horid == null && other.horid != null) || (this.horid != null && !this.horid.equals(other.horid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.horasaludable.entities.Horario[ horid=" + horid + " ]";
    }
    
}
