/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.managedbeans.contenidos;

import com.unicauca.horasaludable.entities.Noticia;
import com.unicauca.horasaludable.jpacontrollers.NoticiaFacade;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

/**
 *
 * @author Yamid
 */
@ManagedBean
@RequestScoped
public class listarNoticiasController {

    /**
     * Creates a new instance of listarNoticiasController
     */
    private List<Noticia> listaNoticias = new ArrayList();
    @EJB
    private NoticiaFacade ebjNoticiaFacade = new NoticiaFacade();
    Boolean visible = true;
    Noticia noticia;

    private String titulo;
    private Date fechapublicacion;
    private Date fechaedicion;
    private String contenido;
    private String imagen;
    private String path;

    public listarNoticiasController() {
        listaNoticias = new ArrayList<>();
        this.visible = true;
        this.titulo = "";
        this.fechapublicacion = new Date();
        this.fechaedicion = new Date();
        this.contenido = "o";
        //this.path = "..\\..\\img\\imgNoticias\\";
        this.path = "s";
        this.imagen = this.path + "";
    }

    public List<Noticia> obtenerNoticias() {
        try {
            this.listaNoticias = this.ebjNoticiaFacade.buscarNoticias();
            /**
             * *****************PROVISIONAL ARREGLAR ESTO****************
             */
            asignacionImagenesAleatorias();
            /**
             * **********************************************************
             */

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "msgs", "Error: No se puede conectar con la base de datos !!!"));
        }

        return listaNoticias;
    }

    public Date convertToJavaDate(java.util.Date date) {
        java.sql.Date sqlDate = null;

        try {
            sqlDate = new java.sql.Date(date.getTime());
        } catch (Exception e) {
        }
        return sqlDate;
    }

    public String aleatorioArchivos() {

        int tam = 7;
        Random rn = new Random();
        return "imagen (" + (1 + rn.nextInt(tam)) + ").jpg";
    }

    public void asignacionImagenesAleatorias() {
        int tam = this.listaNoticias.size();
        /*
         for (int i = 0; i < tam; i++) {
         this.listaNoticias.get(i).setNotimagen(aleatorioArchivos());
         }
         */
    }

    public void eliminarNoticia() {
        try {
            FacesContext fc = FacesContext.getCurrentInstance();
            Map<String, String> params = fc.getExternalContext().getRequestParameterMap();

            Long _id = Long.parseLong(params.get("id"));
            noticia = ebjNoticiaFacade.mostrarNoticia(_id);
            ebjNoticiaFacade.remove(noticia);

            try {
                eliminarImagen(noticia.getNotimagen());
            } catch (Exception e) {

            }

            FacesContext contex = FacesContext.getCurrentInstance();
            contex.getExternalContext().redirect("listarNoticias.xhtml");
        } catch (Exception e) {

        }

    }

    public void eliminarImagen(String nombre) {

        String pathElimninar;
        String OS = System.getProperty("os.name").toLowerCase();
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String realPath = (String) servletContext.getRealPath("/");
        if (OS.contains("nux") || OS.contains("debian")) {
            pathElimninar = realPath + "/resources/img/imagenesNoticias/";
        } else {
            pathElimninar = realPath + "\\resources\\img\\imagenesNoticias\\";

        }
        if (!nombre.equals("default.jpg")) {
            File f2 = new File(pathElimninar + nombre);
            f2.delete();
        }

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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
