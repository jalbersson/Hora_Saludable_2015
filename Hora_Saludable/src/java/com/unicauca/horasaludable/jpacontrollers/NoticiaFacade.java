/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.jpacontrollers;

import com.unicauca.horasaludable.entities.Noticia;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author JuanJose
 */
@Stateless
public class NoticiaFacade extends AbstractFacade<Noticia> {
    @PersistenceContext(unitName = "Hora_SaludablePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NoticiaFacade() {
        super(Noticia.class);
    }
        public boolean buscarPorIdNoticia(String idNoticia)
    {
     Query query = getEntityManager().createNamedQuery("Noticia.findByNotid");
        query.setParameter("notid", idNoticia);
        List<Noticia> resultList = query.getResultList();
        return !resultList.isEmpty();     
    }
    public List<Noticia> retornarBuscarPorIdNoticia(String idNoticia)
    {
        Query query = getEntityManager().createNamedQuery("Noticia.findByNotid");
        query.setParameter("notid", idNoticia);
        List<Noticia> resultList = query.getResultList();
        return resultList;
    }
    public boolean buscarPorTituloNoticia(String tituloNoticia)
    {
     Query query = getEntityManager().createNamedQuery("Noticia.findByNottitulo");
        query.setParameter("nottitulo", tituloNoticia);
        List<Noticia> resultList = query.getResultList();
        return !resultList.isEmpty();     
    }
    public List<Noticia> retornarBuscarPorTituloNoticia(String tituloNoticia)
    {
        Query query = getEntityManager().createNamedQuery("Noticia.findByNottitulo");
        query.setParameter("nottitulo", tituloNoticia);
        List<Noticia> resultList = query.getResultList();
        return resultList;
    }
    
    public boolean buscarPorFechaPublicacionNoticia(String fechaNoticia)
    {
     Query query = getEntityManager().createNamedQuery("Noticia.findByNotfechapublicacion");
        query.setParameter("notfechapublicacion", fechaNoticia);
        List<Noticia> resultList = query.getResultList();
        return !resultList.isEmpty();     
    }
    public List<Noticia> retornarBuscarPorFechaPublicacionNoticia(String fechaNoticia)
    {
        Query query = getEntityManager().createNamedQuery("Noticia.findByNotfechapublicacion");
        query.setParameter("notfechapublicacion", fechaNoticia);
        List<Noticia> resultList = query.getResultList();
        return resultList;
    }
    
    public boolean buscarPorFechaPEdicionNoticia(String fechaENoticia)
    {
     Query query = getEntityManager().createNamedQuery("Noticia.findByNotfechaedicion");
        query.setParameter("notfechaedicion", fechaENoticia);
        List<Noticia> resultList = query.getResultList();
        return !resultList.isEmpty();     
    }
    public List<Noticia> retornarBuscarPorFechaEdicionNoticia(String fechaENoticia)
    {
        Query query = getEntityManager().createNamedQuery("Noticia.findByNotfechaedicion");
        query.setParameter("notfechaedicion", fechaENoticia);
        List<Noticia> resultList = query.getResultList();
        return resultList;
    }
}
