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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "MEDIDA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Medida.findAll", query = "SELECT m FROM Medida m"),
    @NamedQuery(name = "Medida.findByMedid", query = "SELECT m FROM Medida m WHERE m.medid = :medid"),
    @NamedQuery(name = "Medida.findByMedfecha", query = "SELECT m FROM Medida m WHERE m.medfecha = :medfecha"),
    @NamedQuery(name = "Medida.findByMedobservaciones", query = "SELECT m FROM Medida m WHERE m.medobservaciones = :medobservaciones"),
    @NamedQuery(name = "Medida.findByMedpeso", query = "SELECT m FROM Medida m WHERE m.medpeso = :medpeso"),
    @NamedQuery(name = "Medida.findByMedtriceps", query = "SELECT m FROM Medida m WHERE m.medtriceps = :medtriceps"),
    @NamedQuery(name = "Medida.findByMedsubescapular", query = "SELECT m FROM Medida m WHERE m.medsubescapular = :medsubescapular"),
    @NamedQuery(name = "Medida.findByMedsuprailiaco", query = "SELECT m FROM Medida m WHERE m.medsuprailiaco = :medsuprailiaco"),
    @NamedQuery(name = "Medida.findByMedabdominal", query = "SELECT m FROM Medida m WHERE m.medabdominal = :medabdominal"),
    @NamedQuery(name = "Medida.findByMedmuslo", query = "SELECT m FROM Medida m WHERE m.medmuslo = :medmuslo"),
    @NamedQuery(name = "Medida.findByMedpantorilla", query = "SELECT m FROM Medida m WHERE m.medpantorilla = :medpantorilla"),
    @NamedQuery(name = "Medida.findByMedperimetromuneca", query = "SELECT m FROM Medida m WHERE m.medperimetromuneca = :medperimetromuneca"),
    @NamedQuery(name = "Medida.findByMedperimetrocabeza", query = "SELECT m FROM Medida m WHERE m.medperimetrocabeza = :medperimetrocabeza"),
    @NamedQuery(name = "Medida.findByMeddiametrobiacromial", query = "SELECT m FROM Medida m WHERE m.meddiametrobiacromial = :meddiametrobiacromial"),
    @NamedQuery(name = "Medida.findByMeddiametrobiltiocristal", query = "SELECT m FROM Medida m WHERE m.meddiametrobiltiocristal = :meddiametrobiltiocristal"),
    @NamedQuery(name = "Medida.findByMeddiametrohumero", query = "SELECT m FROM Medida m WHERE m.meddiametrohumero = :meddiametrohumero"),
    @NamedQuery(name = "Medida.findByMeddiametrofemur", query = "SELECT m FROM Medida m WHERE m.meddiametrofemur = :meddiametrofemur"),
    @NamedQuery(name = "Medida.findByMedperimetrobrazo", query = "SELECT m FROM Medida m WHERE m.medperimetrobrazo = :medperimetrobrazo"),
    @NamedQuery(name = "Medida.findByMeddiametroantebrazo", query = "SELECT m FROM Medida m WHERE m.meddiametroantebrazo = :meddiametroantebrazo"),
    @NamedQuery(name = "Medida.findByMedperimetropantorrilla", query = "SELECT m FROM Medida m WHERE m.medperimetropantorrilla = :medperimetropantorrilla"),
    @NamedQuery(name = "Medida.findByMedperimetrocajatoraxica", query = "SELECT m FROM Medida m WHERE m.medperimetrocajatoraxica = :medperimetrocajatoraxica"),
    @NamedQuery(name = "Medida.findByMedperimetromuslo", query = "SELECT m FROM Medida m WHERE m.medperimetromuslo = :medperimetromuslo")})
public class Medida implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MEDID")
    private Long medid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MEDFECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date medfecha;
    @Size(max = 400)
    @Column(name = "MEDOBSERVACIONES")
    private String medobservaciones;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MEDPESO")
    private float medpeso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MEDTRICEPS")
    private float medtriceps;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MEDSUBESCAPULAR")
    private float medsubescapular;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MEDSUPRAILIACO")
    private float medsuprailiaco;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MEDABDOMINAL")
    private float medabdominal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MEDMUSLO")
    private float medmuslo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MEDPANTORILLA")
    private float medpantorilla;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MEDPERIMETROMUNECA")
    private float medperimetromuneca;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MEDPERIMETROCABEZA")
    private float medperimetrocabeza;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MEDDIAMETROBIACROMIAL")
    private float meddiametrobiacromial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MEDDIAMETROBILTIOCRISTAL")
    private float meddiametrobiltiocristal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MEDDIAMETROHUMERO")
    private float meddiametrohumero;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MEDDIAMETROFEMUR")
    private float meddiametrofemur;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MEDPERIMETROBRAZO")
    private float medperimetrobrazo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MEDDIAMETROANTEBRAZO")
    private float meddiametroantebrazo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MEDPERIMETROPANTORRILLA")
    private float medperimetropantorrilla;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MEDPERIMETROCAJATORAXICA")
    private float medperimetrocajatoraxica;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MEDPERIMETROMUSLO")
    private float medperimetromuslo;
    @JoinColumn(name = "USUID", referencedColumnName = "USUID")
    @ManyToOne(optional = false)
    private Usuario usuid;

    public Medida() {
    }

    public Medida(Long medid) {
        this.medid = medid;
    }

    public Medida(Long medid, Date medfecha, float medpeso, float medtriceps, float medsubescapular, float medsuprailiaco, float medabdominal, float medmuslo, float medpantorilla, float medperimetromuneca, float medperimetrocabeza, float meddiametrobiacromial, float meddiametrobiltiocristal, float meddiametrohumero, float meddiametrofemur, float medperimetrobrazo, float meddiametroantebrazo, float medperimetropantorrilla, float medperimetrocajatoraxica, float medperimetromuslo) {
        this.medid = medid;
        this.medfecha = medfecha;
        this.medpeso = medpeso;
        this.medtriceps = medtriceps;
        this.medsubescapular = medsubescapular;
        this.medsuprailiaco = medsuprailiaco;
        this.medabdominal = medabdominal;
        this.medmuslo = medmuslo;
        this.medpantorilla = medpantorilla;
        this.medperimetromuneca = medperimetromuneca;
        this.medperimetrocabeza = medperimetrocabeza;
        this.meddiametrobiacromial = meddiametrobiacromial;
        this.meddiametrobiltiocristal = meddiametrobiltiocristal;
        this.meddiametrohumero = meddiametrohumero;
        this.meddiametrofemur = meddiametrofemur;
        this.medperimetrobrazo = medperimetrobrazo;
        this.meddiametroantebrazo = meddiametroantebrazo;
        this.medperimetropantorrilla = medperimetropantorrilla;
        this.medperimetrocajatoraxica = medperimetrocajatoraxica;
        this.medperimetromuslo = medperimetromuslo;
    }

    public Long getMedid() {
        return medid;
    }

    public void setMedid(Long medid) {
        this.medid = medid;
    }

    public Date getMedfecha() {
        return medfecha;
    }

    public void setMedfecha(Date medfecha) {
        this.medfecha = medfecha;
    }

    public String getMedobservaciones() {
        return medobservaciones;
    }

    public void setMedobservaciones(String medobservaciones) {
        this.medobservaciones = medobservaciones;
    }

    public float getMedpeso() {
        return medpeso;
    }

    public void setMedpeso(float medpeso) {
        this.medpeso = medpeso;
    }

    public float getMedtriceps() {
        return medtriceps;
    }

    public void setMedtriceps(float medtriceps) {
        this.medtriceps = medtriceps;
    }

    public float getMedsubescapular() {
        return medsubescapular;
    }

    public void setMedsubescapular(float medsubescapular) {
        this.medsubescapular = medsubescapular;
    }

    public float getMedsuprailiaco() {
        return medsuprailiaco;
    }

    public void setMedsuprailiaco(float medsuprailiaco) {
        this.medsuprailiaco = medsuprailiaco;
    }

    public float getMedabdominal() {
        return medabdominal;
    }

    public void setMedabdominal(float medabdominal) {
        this.medabdominal = medabdominal;
    }

    public float getMedmuslo() {
        return medmuslo;
    }

    public void setMedmuslo(float medmuslo) {
        this.medmuslo = medmuslo;
    }

    public float getMedpantorilla() {
        return medpantorilla;
    }

    public void setMedpantorilla(float medpantorilla) {
        this.medpantorilla = medpantorilla;
    }

    public float getMedperimetromuneca() {
        return medperimetromuneca;
    }

    public void setMedperimetromuneca(float medperimetromuneca) {
        this.medperimetromuneca = medperimetromuneca;
    }

    public float getMedperimetrocabeza() {
        return medperimetrocabeza;
    }

    public void setMedperimetrocabeza(float medperimetrocabeza) {
        this.medperimetrocabeza = medperimetrocabeza;
    }

    public float getMeddiametrobiacromial() {
        return meddiametrobiacromial;
    }

    public void setMeddiametrobiacromial(float meddiametrobiacromial) {
        this.meddiametrobiacromial = meddiametrobiacromial;
    }

    public float getMeddiametrobiltiocristal() {
        return meddiametrobiltiocristal;
    }

    public void setMeddiametrobiltiocristal(float meddiametrobiltiocristal) {
        this.meddiametrobiltiocristal = meddiametrobiltiocristal;
    }

    public float getMeddiametrohumero() {
        return meddiametrohumero;
    }

    public void setMeddiametrohumero(float meddiametrohumero) {
        this.meddiametrohumero = meddiametrohumero;
    }

    public float getMeddiametrofemur() {
        return meddiametrofemur;
    }

    public void setMeddiametrofemur(float meddiametrofemur) {
        this.meddiametrofemur = meddiametrofemur;
    }

    public float getMedperimetrobrazo() {
        return medperimetrobrazo;
    }

    public void setMedperimetrobrazo(float medperimetrobrazo) {
        this.medperimetrobrazo = medperimetrobrazo;
    }

    public float getMeddiametroantebrazo() {
        return meddiametroantebrazo;
    }

    public void setMeddiametroantebrazo(float meddiametroantebrazo) {
        this.meddiametroantebrazo = meddiametroantebrazo;
    }

    public float getMedperimetropantorrilla() {
        return medperimetropantorrilla;
    }

    public void setMedperimetropantorrilla(float medperimetropantorrilla) {
        this.medperimetropantorrilla = medperimetropantorrilla;
    }

    public float getMedperimetrocajatoraxica() {
        return medperimetrocajatoraxica;
    }

    public void setMedperimetrocajatoraxica(float medperimetrocajatoraxica) {
        this.medperimetrocajatoraxica = medperimetrocajatoraxica;
    }

    public float getMedperimetromuslo() {
        return medperimetromuslo;
    }

    public void setMedperimetromuslo(float medperimetromuslo) {
        this.medperimetromuslo = medperimetromuslo;
    }

    public Usuario getUsuid() {
        return usuid;
    }

    public void setUsuid(Usuario usuid) {
        this.usuid = usuid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (medid != null ? medid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Medida)) {
            return false;
        }
        Medida other = (Medida) object;
        if ((this.medid == null && other.medid != null) || (this.medid != null && !this.medid.equals(other.medid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.horasaludable.entities.Medida[ medid=" + medid + " ]";
    }
    
}
