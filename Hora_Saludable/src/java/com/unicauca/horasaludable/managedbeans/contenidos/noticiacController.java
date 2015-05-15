/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.managedbeans.contenidos;

import com.unicauca.horasaludable.entities.Noticia;
import com.unicauca.horasaludable.jpacontrollers.NoticiaFacade;
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

/**
 *
 * @author Leidi
 */
@ManagedBean
@RequestScoped
public class noticiacController {

    @EJB
    NoticiaFacade ejbNoticia;

    Noticia noticia;
    Noticia detallesNoticia;
    private List<Noticia> noticias = new ArrayList();
    private List<Noticia> ultimos = new ArrayList();
    String notfpublicacion;
    String notTitulo;
    java.util.Date notfnoticia;
    Long idN = null;

    public noticiacController() {
        noticia = new Noticia();

    }

    public Noticia getNoticia() {
        return noticia;
    }

    public void setNoticia(Noticia noticia) {
        this.noticia = noticia;
    }

    public List<Noticia> getNoticias() {
        this.noticias = this.ejbNoticia.buscarNoticias();
        return noticias;
    }

    public void setNoticias(List<Noticia> noticias) {
        this.noticias = noticias;
    }

    public List<Noticia> getUltimos() {
        try {
            this.ultimos = this.ejbNoticia.ultimasNoticias();
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

        return ultimos;
    }

    public void setUltimos(List<Noticia> ultimos) {
        this.ultimos = ultimos;
    }

    public String getNotfpublicacion() {
        return notfpublicacion;
    }

    public void setNotfpublicacion(String notfpublicacion) {
        this.notfpublicacion = notfpublicacion;
    }

    public String getNotTitulo() {
        return notTitulo;
    }

    public void setNotTitulo(String notTitulo) {
        this.notTitulo = notTitulo;
    }

    public Date getNotfnoticia() {
        return notfnoticia;
    }

    public void setNotfnoticia(Date notfnoticia) {
        this.notfnoticia = notfnoticia;
    }

    public Long getIdN() {
        return idN;
    }

    public void setIdN(Long idN) {
        this.idN = idN;
    }

    public Noticia getDetallesNoticia() {
        return detallesNoticia;
    }

    public void setDetallesNoticia(Noticia detallesNoticia) {
        this.detallesNoticia = detallesNoticia;
    }

    public Date convertToJavaDate(java.util.Date date) {
        java.sql.Date sqlDate = null;

        try {
            sqlDate = new java.sql.Date(date.getTime());
        } catch (Exception e) {
        }
        return sqlDate;
    }

    public String detalleNoticia(Long id) {
        idN = id;
        return "detalleNoticia?noticiaId=" + idN;
    }

    public String aleatorioArchivos() {

        int tam = 7;
        Random rn = new Random();
        return "imagen (" +(1+ rn.nextInt(tam)) + ").jpg";
    }

    public void asignacionImagenesAleatorias() {
        int tam = this.ultimos.size();
        for (int i = 0; i < tam; i++) {
            this.ultimos.get(i).setNotimagen(aleatorioArchivos());
        }
    }

    public String country;

    public String outcome() {

        FacesContext fc = FacesContext.getCurrentInstance();
        this.country = getCountryParam(fc);

        return "result";
    }

    //get value from "f:param"
    public String getCountryParam(FacesContext fc) {

        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        return params.get("country");

    }

    public Noticia noticiaDetallada() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        //eveTitulo=params.get("eventoId");
        idN = Long.parseLong(params.get("noticiaId"));
        detallesNoticia = new Noticia();
        /*
        
         //long numero= (long)idE;
         for (int i = 0; i < ultimos.size(); i++) {
         if (ultimos.get(i).getNotid().equals(idN)) {
         detallesNoticia = ultimos.get(i);
         }
         }*/
        //detallesEvento=ultimos.get(numero);

        this.ultimos = this.ejbNoticia.noticiaID(idN);
        detallesNoticia = ultimos.get(0);
        return detallesNoticia;
    }

}
