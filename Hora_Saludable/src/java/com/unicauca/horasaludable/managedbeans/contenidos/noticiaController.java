/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.managedbeans.contenidos;

import com.unicauca.horasaludable.entities.Noticia;
import com.unicauca.horasaludable.jpacontrollers.NoticiaFacade;
import com.unicauca.horasaludable.utilidades.RedimensionadorImagenes;
import com.unicauca.horasaludable.utilidades.Utilidades;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Yamid
 */
@ManagedBean
@SessionScoped
public class noticiaController {

    @EJB
    private NoticiaFacade ebjNoticiaFacade = new NoticiaFacade();
    Boolean visible = true;
    Noticia noticia;

    private String titulo;
    private Date fechapublicacion;
    private Date fechaedicion;
    private String contenido;
    private UploadedFile file;

    public noticiaController() {
        this.visible = true;
        this.fechapublicacion = new Date();
        this.fechaedicion = new Date();
    }

    public void guardarNoticia() {
        this.noticia = new Noticia();
        Date fecha = new java.util.Date();
        this.noticia.setNottitulo(this.titulo);
        this.noticia.setNotfechapublicacion(convertStringToDate(fecha));
        this.noticia.setNotfechaedicion(convertStringToDate(fecha));
        this.noticia.setNotvisible(this.visible);
        this.noticia.setNotcontenido(this.contenido);
        try {
            //Grabar la imagen
            InputStream fi;
            if (file == null) {
                //Poner la imagen de noticia por defecto. Se deben convertirla
                StreamedContent img = Utilidades.getImagenPorDefecto("noticia");
                this.noticia.setNotImagen(Utilidades.StreameadContentToByte(img));
            } else {
                //Graba la imagen del file pero primero lo redimensiona a 300 px
                fi = file.getInputstream();
                byte[] buffer = RedimensionadorImagenes.redimensionar(fi, 300);
                this.noticia.setNotImagen(buffer);
            }

            this.ebjNoticiaFacade.create(this.noticia);
        } catch (Exception e) {
            Logger.getLogger("Error al crear nueva noticias");
        }
        //return "listarNoticias"; //No funcionó

        //Redireccionar a Listar noticias
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext extContext = context.getExternalContext();
        extContext.getFlash().setKeepMessages(true);
        try {
            extContext.redirect("listarNoticias.xhtml");
        } catch (IOException ex) {
            Logger.getLogger("Error al redireccioar noticias");
        }
    }

    public static Date convertStringToDate(java.util.Date date) {
        java.sql.Date sqlDate = null;

        try {
            sqlDate = new java.sql.Date(date.getTime());
        } catch (Exception e) {
        }
        return sqlDate;
    }

    public NoticiaFacade getEbjNoticiaFacade() {
        return ebjNoticiaFacade;
    }

    public void setEbjNoticiaFacade(NoticiaFacade ebjNoticiaFacade) {
        this.ebjNoticiaFacade = ebjNoticiaFacade;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Noticia getNoticia() {
        return noticia;
    }

    public void setNoticia(Noticia noticia) {
        this.noticia = noticia;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getFechapublicacion() {
        return fechapublicacion;
    }

    public void setFechapublicacion(Date fechapublicacion) {
        this.fechapublicacion = fechapublicacion;
    }

    public Date getFechaedicion() {
        return fechaedicion;
    }

    public void setFechaedicion(Date fechaedicion) {
        this.fechaedicion = fechaedicion;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public UploadedFile getFile() {

        return file;
    }

    public void setFile(UploadedFile file) {

        this.file = file;
    }

}
