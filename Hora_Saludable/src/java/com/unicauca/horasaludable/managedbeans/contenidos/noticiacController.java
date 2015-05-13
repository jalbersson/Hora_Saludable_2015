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
    private List<Noticia> noticias = new ArrayList();
    private List<Noticia> ultimos = new ArrayList();
    String notfpublicacion;
    String notTitulo;
    java.util.Date notfnoticia;
    
    public noticiacController() {
        noticia = new Noticia();
       System.out.println("Hola mundo");
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
        try
        {
            this.ultimos = this.ejbNoticia.ultimasNoticias();
        }
        catch(Exception e)
        {
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
    
    public Date convertToJavaDate(java.util.Date date)
    {
      java.sql.Date sqlDate = null;
        
      try
      {
        sqlDate = new java.sql.Date(date.getTime()); 
      }
      catch(Exception e)
      {   
      }    
      return sqlDate;
    }
    
    
    
    public String detalleNoticia(Long id)
    {
        return "visualizarNoticia";
    }
    
}
