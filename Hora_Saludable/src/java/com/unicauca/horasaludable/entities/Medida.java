/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.entities;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
@Table(name = "MEDIDA", catalog = "asae", schema = "")
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
    @NamedQuery(name = "Medida.findByMedperimetromuslo", query = "SELECT m FROM Medida m WHERE m.medperimetromuslo = :medperimetromuslo"),
    @NamedQuery(name = "Medida.findByMedpulso0", query = "SELECT m FROM Medida m WHERE m.medpulso0 = :medpulso0"),
    @NamedQuery(name = "Medida.findByMedpulso1", query = "SELECT m FROM Medida m WHERE m.medpulso1 = :medpulso1"),
    @NamedQuery(name = "Medida.findByMedpulso2", query = "SELECT m FROM Medida m WHERE m.medpulso2 = :medpulso2"),
    @NamedQuery(name = "Medida.findByMedflexibilidad", query = "SELECT m FROM Medida m WHERE m.medflexibilidad = :medflexibilidad"),
    @NamedQuery(name = "Medida.findByMedembergadura", query = "SELECT m FROM Medida m WHERE m.medembergadura = :medembergadura"),
    @NamedQuery(name = "Medida.findByMedsaltomaximo", query = "SELECT m FROM Medida m WHERE m.medsaltomaximo = :medsaltomaximo"),
    @NamedQuery(name = "Medida.findByMedsaltoreal", query = "SELECT m FROM Medida m WHERE m.medsaltoreal = :medsaltoreal"),
    @NamedQuery(name = "Medida.findByMedidasPorUsuario", query = "SELECT m FROM Medida m WHERE m.usuid.usuidentificacion = :usuidentificacion"),
    @NamedQuery(name = "Medida.findByporApellido", query = "SELECT m FROM Medida m WHERE m.usuid.usuapellidos = :usuapellidos")})
public class Medida implements Serializable {

    @Column(name = "MEDFCARDIACA1")
    private Integer medfcardiaca1;
    @Column(name = "MEDFCARDIACA2")
    private Integer medfcardiaca2;
    @Column(name = "MEDFCARDIACA3")
    private Integer medfcardiaca3;
    @Column(name = "MEDFCARDIACA4")
    private Integer medfcardiaca4;
    @Column(name = "MEDFCARDIACA5")
    private Integer medfcardiaca5;
    @Column(name = "MEDFCARDIACA6")
    private Integer medfcardiaca6;
    @Column(name = "MEDFCARDIACA7")
    private Integer medfcardiaca7;
    @Column(name = "MEDFCARDIACA8")
    private Integer medfcardiaca8;
    @Column(name = "MEDFCARDIACA9")
    private Integer medfcardiaca9;
    @Column(name = "MEDFCARDIACA10")
    private Integer medfcardiaca10;
    @Column(name = "MEDFCARDIACA11")
    private Integer medfcardiaca11;
    @Column(name = "MEDFCARDIACA12")
    private Integer medfcardiaca12;
    @Column(name = "MEDFCARDIACA13")
    private Integer medfcardiaca13;
    @Column(name = "MEDFCARDIACA14")
    private Integer medfcardiaca14;
    @Column(name = "MEDFCARDIACA15")
    private Integer medfcardiaca15;
    @Column(name = "MEDFCARDIACA16")
    private Integer medfcardiaca16;
    //Atributos Test de Frecuencia Cardiaca
    @Column(name = "MEDFCARDIACAMAXIMA")
    private Integer medfcardiacamaxima;
    @Column(name = "MEDFCARDIACAMAXIMALEGER")
    private Integer medfcardiacamaximaleger;
    @Column(name = "MEDFCARDIACAREPOSO")
    private Integer medfcardiacareposo;
    //Fin 

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
    @Column(name = "MEDTALLA")
    private float medtalla;
    @Size(max = 30)
    @Column(name = "MEDDEPORTE")
    private String meddeporte;
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
    @Basic(optional = false)
    @NotNull
    @Column(name = "MEDPULSO0")
    private float medpulso0;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MEDPULSO1")
    private float medpulso1;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MEDPULSO2")
    private float medpulso2;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MEDFLEXIBILIDAD")
    private float medflexibilidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MEDEMBERGADURA")
    private float medembergadura;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MEDSALTOMAXIMO")
    private float medsaltomaximo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MEDSALTOREAL")
    private float medsaltoreal;
    @JoinColumn(name = "USUID", referencedColumnName = "USUID")
    @ManyToOne(optional = false)
    private Usuario usuid;

    public Medida() {
    }

    public Medida(Long medid) {
        this.medid = medid;
    }

    public Medida(Long medid, Date medfecha, float medpeso, float medtalla, float medtriceps, float medsubescapular, float medsuprailiaco, float medabdominal, float medmuslo, float medpantorilla, float medperimetromuneca, float medperimetrocabeza, float meddiametrobiacromial, float meddiametrobiltiocristal, float meddiametrohumero, float meddiametrofemur, float medperimetrobrazo, float meddiametroantebrazo, float medperimetropantorrilla, float medperimetrocajatoraxica, float medperimetromuslo, float medpulso0, float medpulso1, float medpulso2, float medflexibilidad, float medembergadura, float medsaltomaximo, float medsaltoreal) {
        this.medid = medid;
        this.medfecha = medfecha;
        this.medpeso = medpeso;
        this.medtalla = medtalla;
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
        this.medpulso0 = medpulso0;
        this.medpulso1 = medpulso1;
        this.medpulso2 = medpulso2;
        this.medflexibilidad = medflexibilidad;
        this.medembergadura = medembergadura;
        this.medsaltomaximo = medsaltomaximo;
        this.medsaltoreal = medsaltoreal;

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

    public float getMedtalla() {
        return medtalla;
    }

    public void setMedtalla(float medtalla) {
        this.medtalla = medtalla;
    }

    public String getMeddeporte() {
        return meddeporte;
    }

    public void setMeddeporte(String meddeporte) {
        this.meddeporte = meddeporte;
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

    public float getMedpulso0() {
        return medpulso0;
    }

    public void setMedpulso0(float medpulso0) {
        this.medpulso0 = medpulso0;
    }

    public float getMedpulso1() {
        return medpulso1;
    }

    public void setMedpulso1(float medpulso1) {
        this.medpulso1 = medpulso1;
    }

    public float getMedpulso2() {
        return medpulso2;
    }

    public void setMedpulso2(float medpulso2) {
        this.medpulso2 = medpulso2;
    }

    public float getMedflexibilidad() {
        return medflexibilidad;
    }

    public void setMedflexibilidad(float medflexibilidad) {
        this.medflexibilidad = medflexibilidad;
    }

    public float getMedembergadura() {
        return medembergadura;
    }

    public void setMedembergadura(float medembergadura) {
        this.medembergadura = medembergadura;
    }

    public float getMedsaltomaximo() {
        return medsaltomaximo;
    }

    public void setMedsaltomaximo(float medsaltomaximo) {
        this.medsaltomaximo = medsaltomaximo;
    }

    public float getMedsaltoreal() {
        return medsaltomaximo - medembergadura;
    }

    public void setMedsaltoreal(float medsaltoreal) {
        this.medsaltoreal = medsaltoreal;
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

    public double sumatoriadepliegues() {
        return Redondear(medtriceps + medsubescapular + medsuprailiaco + medmuslo + medpantorilla + medabdominal);
    }

    public double porcentajeGrasa() {
        double r;
        if (usuid.getUsugenero().equals('M')) {
            r = sumatoriadepliegues() * 0.097 + 3.64;
        } else {
            r = sumatoriadepliegues() * 0.1429 + 4.56;
        }
        return Redondear(r);
    }

    public double pesoGraso() {
        return Redondear((medpeso * porcentajeGrasa()) / 100);
    }

    public double pesolibregrasa() {
        return Redondear(medpeso - pesoGraso());
    }

    public double masamuscular() {

        try {
            double ZMUS = medperimetrobrazo + meddiametroantebrazo + medperimetromuslo + medperimetropantorrilla + medperimetrocajatoraxica;
            double MMUS = (ZMUS * (170.18 / medtalla) - 207.21) / 13.74;
            double r = Redondear((MMUS * 5.4 + 24.5) / Math.pow(170.18 / medtalla, 3));
            return r;
        } catch (Exception e) {
            return 0;
        }

    }

    public double pesoideal() {
        return (0.75 * (medtalla - 150) + 50);
    }

    public double indicemasacorporal() {
        return Redondear(medpeso / 2.89);
    }

    public double complexion() {
        try {
            return Redondear(medtalla / medperimetromuneca);
        } catch (Exception e) {
            return 0;
        }
    }

    public double tasametabolicabasal() {
        double r;
        if (usuid.getUsugenero().equals('M')) {
            r = 66 + (13.8 * medpeso) + (5 * medtalla) - (6.8 * getEdadusuid());
        } else {
            r = 655 + (9.6 * medpeso) + (1.8 * medtalla) - (4.7 * getEdadusuid());
        }
        return Redondear(r);
    }

    public double excesodepeso() {
        return Redondear(medpeso - pesooptimo());
    }

    public double pesooptimo() {
        return Redondear(pesolibregrasa() / 0.9);
    }

    public double masaesqcuerpo() {

        try {
            double SOSEACUERPO = meddiametrobiacromial + meddiametrobiltiocristal + (2 * meddiametrohumero) + (2 * meddiametrofemur);
            double ZOSEACUERPO = ((SOSEACUERPO * (170.18 / medtalla)) - 98.88) / 5.33;
            double r = Redondear(((ZOSEACUERPO * 1.34) + 6.7) / Math.pow(170.18 / medtalla, 3));
            return r;
        } catch (Exception e) {
            return 0;
        }

    }

    public double masatotalosea() {
        try {
            double MOSEACABEZA = (medperimetrocabeza - 1.2) / 0.18;
            double r = Redondear(masaesqcuerpo() + MOSEACABEZA);
            return r;
        } catch (Exception e) {
            return 0;
        }

    }

    public double porcentajegrasaideal() {
        if (meddeporte == null) {
            meddeporte = "";
        }
        switch (meddeporte) {
            case "Atletismo":
                if (usuid.getUsugenero().equals('M')) {
                    return 7.04;
                } else {
                    return 14.61;
                }
            case "Baloncesto":
                return 8.40;
            case "Ciclismo":
                return 6.48;
            case "Gimnasia":
                return 6.97;

            case "Tiro":
                return 10.92;

            case "Boxeo":
                return 8.09;

            case "Karate Do":
                return 7.93;

            case "Tenis de Mesa":
                return 8.82;
            case "Voleibol":
                return 8.23;
            case "Futbol":
                return 8.83;

            case "Microfutbol":
                return 9.33;

            case "Tenis de Campo":
                return 9.16;

            case "Natacion":
                return 8.98;

            case "Lucha Olimpica":
                return 9.23;

            case "Levantamiento de Pesas":
                return 7.78;

            case "Judo":
                return 7.86;

            case "Taekondo":
                return 8.28;

            case "Esgrima":
                return 9.55;

            default:
                return 0;
        }
    }

    public String getEstadoPorcentajeGrasaIdeal() {
        double x = porcentajeGrasa();
        if (usuid.getUsugenero().equals('M')) {

            if (x < 12) {
                return "EXCELENTE";
            }
            if (x < 16) {
                return "BUENO";
            }
            if (x < 20) {
                return "ACEPTABLE";
            }
            if (x < 23) {
                return "MALO";
            } else {
                return "PESIMO";
            }
        } else {
            if (x < 18) {
                return "EXCELENTE";
            }
            if (x < 20) {
                return "BUENO";
            }
            if (x < 25) {
                return "ACEPTABLE";
            }
            if (x < 29) {
                return "MALO";
            } else {
                return "PESIMO";
            }
        }
    }

    public double frecuenciaMaxima() {
        return 220 - getEdadusuid();
    }

    public double rehabilitacion() {
        return Redondear((frecuenciaMaxima() - medpulso0) * 0.5 + medpulso0);
    }

    public double quemadegrasa() {
        return Redondear((frecuenciaMaxima() - medpulso0) * 0.6 + medpulso0);
    }

    public double desarrolloresistencia() {
        return Redondear((frecuenciaMaxima() - medpulso0) * 0.7 + medpulso0);
    }

    public double desarrollopotenciaaerobica() {
        return Redondear((frecuenciaMaxima() - medpulso0) * 0.8 + medpulso0);
    }

    public double aumentometanaerobico() {
        return Redondear((frecuenciaMaxima() - medpulso0) * 0.9 + medpulso0);
    }

    public double Redondear(double numero) {
        return Math.rint(numero * 100) / 100;
    }

    public String obtenerFecha() {
        DateFormat df = DateFormat.getDateInstance(DateFormat.FULL);
        return df.format(medfecha);
    }

    public String edad() {
        DateFormat df = DateFormat.getDateInstance(DateFormat.FULL);
        return df.format(medfecha);
    }

    public int getEdadusuid() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
        int x = Integer.parseInt(dateFormat.format(new Date())) - Integer.parseInt(dateFormat.format(usuid.getUsufechanacimiento()));
        return x;
    }

    public double getTestRufier() {
        return (medpulso0 + medpulso1 + medpulso2 - 200) / 10;
    }

    public String getEstadoIMC() {
        double imc = indicemasacorporal();
        if (imc >= 30) {
            return "OBESIDAD";
        }
        if (imc >= 25) {
            return "SOBREPESO";
        }
        if (imc >= 18.5) {
            return "NORMAL";
        }
        if (imc >= 17) {
            return "BAJO PESO";
        } else {
            return "DESNUTRICION";
        }
    }

    public String getEstadoComplexion() {
        double c = complexion();
        if (usuid.getUsugenero().equals('M')) {
            if (c > 10.4) {
                return "PEQUEÑA";
            }
            if (c >= 9.6) {
                return "MEDIANA";
            } else {
                return "GRANDE";
            }
        } else {
            if (c > 11) {
                return "PEQUEÑA";
            }
            if (c >= 10.1) {
                return "MEDIANA";
            } else {
                return "GRANDE";
            }
        }
    }

    public String getEstadoRufier() {
        double rufier = getTestRufier();
        if (rufier >= 0 && rufier <= 1) {
            return "Excelente";
        }
        if (rufier > 1 && rufier < 6) {
            return "Muy Bueno";
        }
        if (rufier >= 6 && rufier < 11) {
            return "Regular";
        }
        if (rufier >= 11 && rufier < 16) {
            return "Malo";
        } else {
            return "Muy Malo, requiere evaluacion medica";
        }
    }

    public String getEstadotestSargent() {
        if (usuid.getUsugenero().equals('M')) {
            if (getMedsaltoreal() > 70) {
                return "Excelente";
            }
            if (getMedsaltoreal() > 61 && getMedsaltoreal() <= 70) {
                return "Muy bueno";
            }
            if (getMedsaltoreal() > 51 && getMedsaltoreal() <= 60) {
                return "Arriba del promedio";
            }
            if (getMedsaltoreal() > 41 && getMedsaltoreal() <= 50) {
                return "En el promedio";
            }
            if (getMedsaltoreal() > 31 && getMedsaltoreal() <= 40) {
                return "Por debajo del promedio";
            }
            if (getMedsaltoreal() > 21 && getMedsaltoreal() <= 30) {
                return "Pobre";
            } else {
                return "Muy pobre";
            }
        } else {
            if (getMedsaltoreal() > 60) {
                return "Excelente";
            }
            if (getMedsaltoreal() > 51 && getMedsaltoreal() <= 60) {
                return "Muy bueno";
            }
            if (getMedsaltoreal() > 41 && getMedsaltoreal() <= 50) {
                return "Arriba del pomedio";
            }
            if (getMedsaltoreal() > 31 && getMedsaltoreal() <= 40) {
                return "En el promedio";
            }
            if (getMedsaltoreal() > 21 && getMedsaltoreal() < 31) {
                return "Abajo del promedio";
            }
            if (getMedsaltoreal() > 11 && getMedsaltoreal() < 21) {
                return "Pobre";
            } else {
                return "Muy pobre";
            }
        }
    }

    public String getEstadoTestWells() {
        if (usuid.getUsugenero().equals('M')) {
            if (getEdadusuid() > 15 && getEdadusuid() <= 19) {
                if (medflexibilidad > 38) {
                    return "Excelente";
                }
                if (medflexibilidad >= 34 && medflexibilidad <= 38) {
                    return "Encima del promedio";
                }
                if (medflexibilidad >= 29 && medflexibilidad < 34) {
                    return "En el promedio";
                }
                if (medflexibilidad >= 24 && medflexibilidad < 29) {
                    return "Por debajo del promedio";
                } else {
                    return "Pobre";
                }
            }
            if (getEdadusuid() > 19 && getEdadusuid() <= 29) {
                if (medflexibilidad > 39) {
                    return "Excelente";
                }
                if (medflexibilidad >= 34 && medflexibilidad <= 39) {
                    return "Encima del promedio";
                }
                if (medflexibilidad >= 30 && medflexibilidad < 34) {
                    return "En el promedio";
                }
                if (medflexibilidad >= 25 && medflexibilidad < 30) {
                    return "Por debajo del promedio";
                } else {
                    return "Pobre";
                }
            }
            if (getEdadusuid() > 29 && getEdadusuid() <= 39) {
                if (medflexibilidad > 37) {
                    return "Excelente";
                }
                if (medflexibilidad >= 33 && medflexibilidad <= 37) {
                    return "Encima del promedio";
                }
                if (medflexibilidad >= 28 && medflexibilidad < 33) {
                    return "En el promedio";
                }
                if (medflexibilidad >= 23 && medflexibilidad < 28) {
                    return "Por debajo del promedio";
                } else {
                    return "Pobre";
                }
            }
            if (getEdadusuid() > 39 && getEdadusuid() <= 49) {
                if (medflexibilidad > 34) {
                    return "Excelente";
                }
                if (medflexibilidad >= 29 && medflexibilidad <= 34) {
                    return "Encima del promedio";
                }
                if (medflexibilidad >= 24 && medflexibilidad < 29) {
                    return "En el promedio";
                }
                if (medflexibilidad >= 18 && medflexibilidad < 24) {
                    return "Por debajo del promedio";
                } else {
                    return "Pobre";
                }
            }
            if (getEdadusuid() > 49 && getEdadusuid() <= 59) {
                if (medflexibilidad > 34) {
                    return "Excelente";
                }
                if (medflexibilidad >= 28 && medflexibilidad <= 34) {
                    return "Encima del promedio";
                }
                if (medflexibilidad >= 24 && medflexibilidad < 28) {
                    return "En el promedio";
                }
                if (medflexibilidad >= 15 && medflexibilidad < 24) {
                    return "Por debajo del promedio";
                } else {
                    return "Pobre";
                }
            } else {
                if (medflexibilidad > 32) {
                    return "Excelente";
                }
                if (medflexibilidad >= 25 && medflexibilidad <= 32) {
                    return "Encima del promedio";
                }
                if (medflexibilidad >= 20 && medflexibilidad < 25) {
                    return "En el promedio";
                }
                if (medflexibilidad >= 15 && medflexibilidad < 20) {
                    return "Por debajo del promedio";
                } else {
                    return "Pobre";
                }
            }
        } else {
            if (getEdadusuid() > 15 && getEdadusuid() <= 19) {
                if (medflexibilidad > 42) {
                    return "Excelente";
                }
                if (medflexibilidad >= 38 && medflexibilidad <= 42) {
                    return "Encima del promedio";
                }
                if (medflexibilidad >= 34 && medflexibilidad < 38) {
                    return "En el promedio";
                }
                if (medflexibilidad >= 29 && medflexibilidad < 34) {
                    return "Por debajo del promedio";
                } else {
                    return "Pobre";
                }
            }
            if (getEdadusuid() > 19 && getEdadusuid() <= 29) {
                if (medflexibilidad > 40) {
                    return "Excelente";
                }
                if (medflexibilidad >= 37 && medflexibilidad <= 40) {
                    return "Encima del promedio";
                }
                if (medflexibilidad >= 33 && medflexibilidad < 37) {
                    return "En el promedio";
                }
                if (medflexibilidad >= 28 && medflexibilidad < 33) {
                    return "Por debajo del promedio";
                } else {
                    return "Pobre";
                }
            }
            if (getEdadusuid() > 29 && getEdadusuid() <= 39) {
                if (medflexibilidad > 40) {
                    return "Excelente";
                }
                if (medflexibilidad >= 36 && medflexibilidad <= 40) {
                    return "Encima del promedio";
                }
                if (medflexibilidad >= 32 && medflexibilidad < 36) {
                    return "En el promedio";
                }
                if (medflexibilidad >= 27 && medflexibilidad < 32) {
                    return "Por debajo del promedio";
                } else {
                    return "Pobre";
                }
            }
            if (getEdadusuid() > 39 && getEdadusuid() <= 49) {
                if (medflexibilidad > 37) {
                    return "Excelente";
                }
                if (medflexibilidad >= 34 && medflexibilidad <= 37) {
                    return "Encima del promedio";
                }
                if (medflexibilidad >= 30 && medflexibilidad < 34) {
                    return "En el promedio";
                }
                if (medflexibilidad >= 25 && medflexibilidad < 30) {
                    return "Por debajo del promedio";
                } else {
                    return "Pobre";
                }
            }
            if (getEdadusuid() > 49 && getEdadusuid() <= 59) {
                if (medflexibilidad > 38) {
                    return "Excelente";
                }
                if (medflexibilidad >= 33 && medflexibilidad <= 38) {
                    return "Encima del promedio";
                }
                if (medflexibilidad >= 30 && medflexibilidad < 33) {
                    return "En el promedio";
                }
                if (medflexibilidad >= 23 && medflexibilidad < 30) {
                    return "Por debajo del promedio";
                } else {
                    return "Pobre";
                }
            } else {
                if (medflexibilidad > 34) {
                    return "Excelente";
                }
                if (medflexibilidad >= 31 && medflexibilidad <= 34) {
                    return "Encima del promedio";
                }
                if (medflexibilidad >= 27 && medflexibilidad < 31) {
                    return "En el promedio";
                }
                if (medflexibilidad >= 23 && medflexibilidad < 27) {
                    return "Por debajo del promedio";
                } else {
                    return "Pobre";
                }
            }
        }
    }

    public Integer getMedfcardiaca1() {
        return medfcardiaca1;
    }

    public void setMedfcardiaca1(Integer medfcardiaca1) {
        this.medfcardiaca1 = medfcardiaca1;
    }

    public Integer getMedfcardiaca2() {
        return medfcardiaca2;
    }

    public void setMedfcardiaca2(Integer medfcardiaca2) {
        this.medfcardiaca2 = medfcardiaca2;
    }

    public Integer getMedfcardiaca3() {
        return medfcardiaca3;
    }

    public void setMedfcardiaca3(Integer medfcardiaca3) {
        this.medfcardiaca3 = medfcardiaca3;
    }

    public Integer getMedfcardiaca4() {
        return medfcardiaca4;
    }

    public void setMedfcardiaca4(Integer medfcardiaca4) {
        this.medfcardiaca4 = medfcardiaca4;
    }

    public Integer getMedfcardiaca5() {
        return medfcardiaca5;
    }

    public void setMedfcardiaca5(Integer medfcardiaca5) {
        this.medfcardiaca5 = medfcardiaca5;
    }

    public Integer getMedfcardiaca6() {
        return medfcardiaca6;
    }

    public void setMedfcardiaca6(Integer medfcardiaca6) {
        this.medfcardiaca6 = medfcardiaca6;
    }

    public Integer getMedfcardiaca7() {
        return medfcardiaca7;
    }

    public void setMedfcardiaca7(Integer medfcardiaca7) {
        this.medfcardiaca7 = medfcardiaca7;
    }

    public Integer getMedfcardiaca8() {
        return medfcardiaca8;
    }

    public void setMedfcardiaca8(Integer medfcardiaca8) {
        this.medfcardiaca8 = medfcardiaca8;
    }

    public Integer getMedfcardiaca9() {
        return medfcardiaca9;
    }

    public void setMedfcardiaca9(Integer medfcardiaca9) {
        this.medfcardiaca9 = medfcardiaca9;
    }

    public Integer getMedfcardiaca10() {
        return medfcardiaca10;
    }

    public void setMedfcardiaca10(Integer medfcardiaca10) {
        this.medfcardiaca10 = medfcardiaca10;
    }

    public Integer getMedfcardiaca11() {
        return medfcardiaca11;
    }

    public void setMedfcardiaca11(Integer medfcardiaca11) {
        this.medfcardiaca11 = medfcardiaca11;
    }

    public Integer getMedfcardiaca12() {
        return medfcardiaca12;
    }

    public void setMedfcardiaca12(Integer medfcardiaca12) {
        this.medfcardiaca12 = medfcardiaca12;
    }

    public Integer getMedfcardiaca13() {
        return medfcardiaca13;
    }

    public void setMedfcardiaca13(Integer medfcardiaca13) {
        this.medfcardiaca13 = medfcardiaca13;
    }

    public Integer getMedfcardiaca14() {
        return medfcardiaca14;
    }

    public void setMedfcardiaca14(Integer medfcardiaca14) {
        this.medfcardiaca14 = medfcardiaca14;
    }

    public Integer getMedfcardiaca15() {
        return medfcardiaca15;
    }

    public void setMedfcardiaca15(Integer medfcardiaca15) {
        this.medfcardiaca15 = medfcardiaca15;
    }

    public Integer getMedfcardiaca16() {
        return medfcardiaca16;
    }

    public void setMedfcardiaca16(Integer medfcardiaca16) {
        this.medfcardiaca16 = medfcardiaca16;
    }

    //Set y Get para Test de Frecuencia Cardiaca

    public Integer getMedfcardiacaMaxima() {
        return medfcardiacamaxima;
    }

    public void setMedfcardiacaMaxima(Integer medfcardiacamaxima) {
        this.medfcardiacamaxima = medfcardiacamaxima;
    }

    public Integer getMedfcardiacaMaximaLeger() {
        return medfcardiacamaximaleger;
    }

    public void setMedfcardiacaMaximaLeger(Integer medfcardiacamaximaleger) {
        this.medfcardiacamaximaleger = medfcardiacamaximaleger;
    }    
    
    public Integer getMedfcardiacaReposo() {
        return medfcardiacareposo;
    }

    public void setMedfcardiacaReposo(Integer medfcardiacareposo) {
        this.medfcardiacareposo = medfcardiacareposo;
    }

    //Metodos para el Test de Frecuencia Cardiaca
    private double frecuenciaDeReserva() {
        return medfcardiacamaxima - medfcardiacareposo;
    }

    private double frecuenciaDeReservaLeger() {
        return medfcardiacamaximaleger - medfcardiacareposo;
    }

    public double frecuenciaDeRehabilitacion() {
        if (medfcardiacareposo == null || medfcardiacamaxima == null) {
            return 0;
        } else {
            return Redondear(frecuenciaDeReserva() * 0.5 + medfcardiacareposo);
        }
    }
    public double frecuenciaDeRehabilitacionLeger() {
        if (medfcardiacareposo == null || medfcardiacamaximaleger == null) {
            return 0;
        } else {
            return Redondear(frecuenciaDeReservaLeger() * 0.5 + medfcardiacareposo);
        }
    }

    public double frecuenciaQuemaGrasa() {
        if (medfcardiacareposo == null || medfcardiacamaxima == null) {
            return 0;
        } else {
            return Redondear(frecuenciaDeReserva() * 0.6 + medfcardiacareposo);
        }
    }
    public double frecuenciaQuemaGrasaLeger() {
        if (medfcardiacareposo == null || medfcardiacamaximaleger == null) {
            return 0;
        } else {
            return Redondear(frecuenciaDeReservaLeger()* 0.6 + medfcardiacareposo);
        }
    }
    public double desarrolloDeResistencia() {
        if (medfcardiacareposo == null || medfcardiacamaxima == null) {
            return 0;
        } else {
            return Redondear(frecuenciaDeReserva() * 0.7 + medfcardiacareposo);
        }
    }

    public double desarrolloDeResistenciaLeger() {
        if (medfcardiacareposo == null || medfcardiacamaximaleger == null) {
            return 0;
        } else {
            return Redondear(frecuenciaDeReservaLeger() * 0.7 + medfcardiacareposo);
        }
    }

    public double desarrolloPotenciaAerobica() {
        if (medfcardiacareposo == null || medfcardiacamaxima == null) {
            return 0;
        } else {
            return Redondear(frecuenciaDeReserva() * 0.8 + medfcardiacareposo);
        }
    }
    public double desarrolloPotenciaAerobicaLeger() {
        if (medfcardiacareposo == null || medfcardiacamaximaleger == null) {
            return 0;
        } else {
            return Redondear(frecuenciaDeReservaLeger() * 0.8 + medfcardiacareposo);
        }
    }
    public double aumentoMetabolismoAnaerobicoLeger() {
        if (medfcardiacareposo == null || medfcardiacamaximaleger == null) {
            return 0;
        } else {
            return Redondear(frecuenciaDeReservaLeger() * 0.9 + medfcardiacareposo);
        }
    }
    public double aumentoMetabolismoAnaerobico() {
        if (medfcardiacareposo == null || medfcardiacamaximaleger == null) {
            return 0;
        } else {
            return Redondear(frecuenciaDeReserva() * 0.9 + medfcardiacareposo);
        }
    }    
}
