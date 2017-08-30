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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Wilson
 */

@Entity
@Table(name = "RESPONSABLE", catalog = "asae", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Responsable.findAll", query = "SELECT r FROM Responsable r"),
    @NamedQuery(name = "Responsable.findByRespid", query = "SELECT r FROM Responsable r WHERE r.respnombre = :respnombre")})
public class Responsable implements Serializable, Comparable<Responsable>{
    
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "RESPID")
    private Long respid;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "RESPNOMBRE")
    private String respnombre;
    
    public Responsable() {
    }

    public Responsable(Long respid) {
        this.respid = respid;
    }

    
    public Responsable(Long respid, String respnombre ) {
        this.respid = respid;
        this.respnombre = respnombre;
    }

    @Override
    public int compareTo(Responsable o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Long getRespid() {
        return respid;
    }

    public void setRespid(Long respid) {
        this.respid = respid;
    }
    
    public String getRespnombre() {
        return respnombre;
    }

    public void setRespnombre(String respnombre) {
        this.respnombre = respnombre;
    }
}
