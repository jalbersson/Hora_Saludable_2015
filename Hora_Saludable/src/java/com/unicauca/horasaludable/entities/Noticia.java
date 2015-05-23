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
 * @author JuanJose
 */
@Entity
@Table(name = "NOTICIA", catalog = "asae", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Noticia.findAll", query = "SELECT n FROM Noticia n"),
    @NamedQuery(name = "Noticia.findByNotid", query = "SELECT n FROM Noticia n WHERE n.notid = :notid"),
    @NamedQuery(name = "Noticia.findByNottitulo", query = "SELECT n FROM Noticia n WHERE n.nottitulo = :nottitulo"),
    @NamedQuery(name = "Noticia.findByNotfechapublicacion", query = "SELECT n FROM Noticia n WHERE n.notfechapublicacion = :notfechapublicacion"),
    @NamedQuery(name = "Noticia.findByNotfechaedicion", query = "SELECT n FROM Noticia n WHERE n.notfechaedicion = :notfechaedicion"),
    @NamedQuery(name = "Noticia.findByNotvisible", query = "SELECT n FROM Noticia n WHERE n.notvisible = :notvisible"),
    @NamedQuery(name = "Noticia.findByNotcontenido", query = "SELECT n FROM Noticia n WHERE n.notcontenido = :notcontenido"),
    @NamedQuery(name = "Noticia.findUltimosContenido", query = "SELECT n FROM Noticia n ORDER BY n.notid DESC"),
    @NamedQuery(name = "Noticia.findByNotimagen", query = "SELECT n FROM Noticia n WHERE n.notimagen = :notimagen")})
public class Noticia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "NOTID")
    private Long notid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "NOTTITULO")
    private String nottitulo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NOTFECHAPUBLICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date notfechapublicacion;
    @Column(name = "NOTFECHAEDICION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date notfechaedicion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NOTVISIBLE")
    private boolean notvisible;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10000)
    @Column(name = "NOTCONTENIDO")
    private String notcontenido;
    @Size(max = 350)
    @Column(name = "NOTIMAGEN")
    private String notimagen;

    public Noticia() {
    }

    public Noticia(Long notid) {
        this.notid = notid;
    }

    public Noticia(Long notid, String nottitulo, Date notfechapublicacion, boolean notvisible, String notcontenido) {
        this.notid = notid;
        this.nottitulo = nottitulo;
        this.notfechapublicacion = notfechapublicacion;
        this.notvisible = notvisible;
        this.notcontenido = notcontenido;
    }

    public Long getNotid() {
        return notid;
    }

    public void setNotid(Long notid) {
        this.notid = notid;
    }

    public String getNottitulo() {
        return nottitulo;
    }

    public void setNottitulo(String nottitulo) {
        this.nottitulo = nottitulo;
    }

    public Date getNotfechapublicacion() {
        return notfechapublicacion;
    }

    public void setNotfechapublicacion(Date notfechapublicacion) {
        this.notfechapublicacion = notfechapublicacion;
    }

    public Date getNotfechaedicion() {
        return notfechaedicion;
    }

    public void setNotfechaedicion(Date notfechaedicion) {
        this.notfechaedicion = notfechaedicion;
    }

    public boolean getNotvisible() {
        return notvisible;
    }

    public void setNotvisible(boolean notvisible) {
        this.notvisible = notvisible;
    }

    public String getNotcontenido() {
        return notcontenido;
    }

    public void setNotcontenido(String notcontenido) {
        this.notcontenido = notcontenido;
    }

    public String getNotimagen() {
        return notimagen;
    }

    public void setNotimagen(String notimagen) {
        this.notimagen = notimagen;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (notid != null ? notid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Noticia)) {
            return false;
        }
        Noticia other = (Noticia) object;
        if ((this.notid == null && other.notid != null) || (this.notid != null && !this.notid.equals(other.notid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.horasaludable.entities.Noticia[ notid=" + notid + " ]";
    }
    
}
