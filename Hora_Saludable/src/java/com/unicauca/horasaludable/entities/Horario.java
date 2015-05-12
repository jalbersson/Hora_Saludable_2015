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
    @NamedQuery(name = "Horario.findByHorhora", query = "SELECT h FROM Horario h WHERE h.horhora = :horhora"),
    @NamedQuery(name = "Horario.findByHorlunes", query = "SELECT h FROM Horario h WHERE h.horlunes = :horlunes"),
    @NamedQuery(name = "Horario.findByHormartes", query = "SELECT h FROM Horario h WHERE h.hormartes = :hormartes"),
    @NamedQuery(name = "Horario.findByHormiercoles", query = "SELECT h FROM Horario h WHERE h.hormiercoles = :hormiercoles"),
    @NamedQuery(name = "Horario.findByHorjueves", query = "SELECT h FROM Horario h WHERE h.horjueves = :horjueves"),
    @NamedQuery(name = "Horario.findByHorviernes", query = "SELECT h FROM Horario h WHERE h.horviernes = :horviernes")})
public class Horario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "HORID")
    private Long horid;
    @Size(max = 10)
    @Column(name = "HORHORA")
    private String horhora;
    @Size(max = 30)
    @Column(name = "HORLUNES")
    private String horlunes;
    @Size(max = 30)
    @Column(name = "HORMARTES")
    private String hormartes;
    @Size(max = 30)
    @Column(name = "HORMIERCOLES")
    private String hormiercoles;
    @Size(max = 30)
    @Column(name = "HORJUEVES")
    private String horjueves;
    @Size(max = 30)
    @Column(name = "HORVIERNES")
    private String horviernes;

    public Horario() {
    }

    public Horario(Long horid) {
        this.horid = horid;
    }

    public Long getHorid() {
        return horid;
    }

    public void setHorid(Long horid) {
        this.horid = horid;
    }

    public String getHorhora() {
        return horhora;
    }

    public void setHorhora(String horhora) {
        this.horhora = horhora;
    }

    public String getHorlunes() {
        return horlunes;
    }

    public void setHorlunes(String horlunes) {
        this.horlunes = horlunes;
    }

    public String getHormartes() {
        return hormartes;
    }

    public void setHormartes(String hormartes) {
        this.hormartes = hormartes;
    }

    public String getHormiercoles() {
        return hormiercoles;
    }

    public void setHormiercoles(String hormiercoles) {
        this.hormiercoles = hormiercoles;
    }

    public String getHorjueves() {
        return horjueves;
    }

    public void setHorjueves(String horjueves) {
        this.horjueves = horjueves;
    }

    public String getHorviernes() {
        return horviernes;
    }

    public void setHorviernes(String horviernes) {
        this.horviernes = horviernes;
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
