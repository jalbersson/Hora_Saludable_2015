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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "USUARIOGRUPO", catalog = "asae", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuariogrupo.findAll", query = "SELECT u FROM Usuariogrupo u"),
    @NamedQuery(name = "Usuariogrupo.findByGruid", query = "SELECT u FROM Usuariogrupo u WHERE u.usuariogrupoPK.gruid = :gruid"),
    @NamedQuery(name = "Usuariogrupo.findByUsuid", query = "SELECT u FROM Usuariogrupo u WHERE u.usuariogrupoPK.usuid = :usuid"),
    @NamedQuery(name = "Usuariogrupo.findByUsunombreusuario", query = "SELECT u FROM Usuariogrupo u WHERE u.usunombreusuario = :usunombreusuario")})
public class Usuariogrupo implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UsuariogrupoPK usuariogrupoPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 75)
    @Column(name = "USUNOMBREUSUARIO")
    private String usunombreusuario;
    @JoinColumn(name = "GRUID", referencedColumnName = "GRUID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Grupo grupo;
    @JoinColumn(name = "USUID", referencedColumnName = "USUID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario;

    public Usuariogrupo() {
    }

    public Usuariogrupo(UsuariogrupoPK usuariogrupoPK) {
        this.usuariogrupoPK = usuariogrupoPK;
    }

    public Usuariogrupo(UsuariogrupoPK usuariogrupoPK, String usunombreusuario) {
        this.usuariogrupoPK = usuariogrupoPK;
        this.usunombreusuario = usunombreusuario;
    }

    public Usuariogrupo(String gruid, long usuid) {
        this.usuariogrupoPK = new UsuariogrupoPK(gruid, usuid);
    }

    public UsuariogrupoPK getUsuariogrupoPK() {
        return usuariogrupoPK;
    }

    public void setUsuariogrupoPK(UsuariogrupoPK usuariogrupoPK) {
        this.usuariogrupoPK = usuariogrupoPK;
    }

    public String getUsunombreusuario() {
        return usunombreusuario;
    }

    public void setUsunombreusuario(String usunombreusuario) {
        this.usunombreusuario = usunombreusuario;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuariogrupoPK != null ? usuariogrupoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuariogrupo)) {
            return false;
        }
        Usuariogrupo other = (Usuariogrupo) object;
        if ((this.usuariogrupoPK == null && other.usuariogrupoPK != null) || (this.usuariogrupoPK != null && !this.usuariogrupoPK.equals(other.usuariogrupoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Usuariogrupo[ usuariogrupoPK=" + usuariogrupoPK + " ]";
    }
    
}
