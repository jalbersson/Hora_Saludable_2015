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
    
    public List<Noticia> buscarNoticias() {
        Query query = getEntityManager().createNamedQuery("Noticia.findUltimosContenido");
        List<Noticia> resultList = query.getResultList();
        return resultList;
    }

    public List<Noticia> ultimasNoticias() {
        Query query = getEntityManager().createNamedQuery("Noticia.findUltimosContenido").setMaxResults(3);
        List<Noticia> resultList = query.getResultList();
        return resultList;
    }
    
    public List<Noticia> noticiaID(Long id){
        Noticia result = new Noticia();
    //Query query = getEntityManager().createNamedQuery("Noticia.findByNotid");
        //
        Query query = getEntityManager().createNamedQuery("Noticia.findByNotid").setMaxResults(3);
        query.setParameter("notid", id);
        List<Noticia> resultList = query.getResultList();
        return resultList;
    }
    
    public Noticia mostrarNoticia(Long id) {
        Query query = getEntityManager().createNamedQuery("Noticia.findByNotid");
        query.setParameter("notid", id);
        List<Noticia> listado = query.getResultList();
        if (listado.isEmpty()) {
            return null;
        }
        return listado.get(0);
    }
}
