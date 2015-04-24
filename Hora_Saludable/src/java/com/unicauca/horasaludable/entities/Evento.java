/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author seven
 */
@Entity
@Table(name = "EVENTO", catalog = "asae", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Evento.findAll", query = "SELECT e FROM Evento e"),
    @NamedQuery(name = "Evento.findByEveid", query = "SELECT e FROM Evento e WHERE e.eveid = :eveid"),
    @NamedQuery(name = "Evento.findByEvetitulo", query = "SELECT e FROM Evento e WHERE e.evetitulo = :evetitulo"),
    @NamedQuery(name = "Evento.findByEvefechapublicacion", query = "SELECT e FROM Evento e WHERE e.evefechapublicacion = :evefechapublicacion"),
    @NamedQuery(name = "Evento.findByEvefechaevento", query = "SELECT e FROM Evento e WHERE e.evefechaevento = :evefechaevento"),
    @NamedQuery(name = "Evento.findByEvelugar", query = "SELECT e FROM Evento e WHERE e.evelugar = :evelugar"),
    @NamedQuery(name = "Evento.findByEvecontenido", query = "SELECT e FROM Evento e WHERE e.evecontenido = :evecontenido")})
public class Evento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "EVEID")
    private Long eveid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 75)
    @Column(name = "EVETITULO")
    private String evetitulo;
    @Column(name = "EVEFECHAPUBLICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date evefechapublicacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "EVEFECHAEVENTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date evefechaevento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 75)
    @Column(name = "EVELUGAR")
    private String evelugar;
    @Size(max = 200)
    @Column(name = "EVECONTENIDO")
    private String evecontenido;

    public Evento() {
    }

    public Evento(Long eveid) {
        this.eveid = eveid;
    }

    public Evento(Long eveid, String evetitulo, Date evefechaevento, String evelugar) {
        this.eveid = eveid;
        this.evetitulo = evetitulo;
        this.evefechaevento = evefechaevento;
        this.evelugar = evelugar;
    }

    public Long getEveid() {
        return eveid;
    }

    public void setEveid(Long eveid) {
        this.eveid = eveid;
    }

    public String getEvetitulo() {
        return evetitulo;
    }

    public void setEvetitulo(String evetitulo) {
        this.evetitulo = evetitulo;
    }

    public Date getEvefechapublicacion() {
        return evefechapublicacion;
    }

    public void setEvefechapublicacion(Date evefechapublicacion) {
        this.evefechapublicacion = evefechapublicacion;
    }

    public Date getEvefechaevento() {
        return evefechaevento;
    }

    public void setEvefechaevento(Date evefechaevento) {
        this.evefechaevento = evefechaevento;
    }

    public String getEvelugar() {
        return evelugar;
    }

    public void setEvelugar(String evelugar) {
        this.evelugar = evelugar;
    }

    public String getEvecontenido() {
        return evecontenido;
    }

    public void setEvecontenido(String evecontenido) {
        this.evecontenido = evecontenido;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (eveid != null ? eveid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Evento)) {
            return false;
        }
        Evento other = (Evento) object;
        if ((this.eveid == null && other.eveid != null) || (this.eveid != null && !this.eveid.equals(other.eveid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.horasaludable.entities.Evento[ eveid=" + eveid + " ]";
    }
    
}
