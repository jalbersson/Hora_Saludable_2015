/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.jpacontrollers;

import com.unicauca.horasaludable.entities.Noticia;
import java.util.ArrayList;
import java.util.Date;
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
        Query query = getEntityManager().createNamedQuery("Noticia.findAll");
        List<Noticia> resultList = query.getResultList();
        return resultList;
    }

    public List<Noticia> ultimasNoticias() {
        Query query = getEntityManager().createNamedQuery("Noticia.findUltimosContenido").setMaxResults(4);
        List<Noticia> resultList = query.getResultList();
        return resultList;
    }

}
