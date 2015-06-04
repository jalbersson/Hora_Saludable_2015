/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.jpacontrollers;

import com.unicauca.horasaludable.entities.Evento;
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
public class EventoFacade extends AbstractFacade<Evento> {
    @PersistenceContext(unitName = "Hora_SaludablePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EventoFacade() {
        super(Evento.class);
    }
    
    public List<Evento> buscarEventos() {
        Query query = getEntityManager().createNamedQuery("Evento.findUltimosEventos");
        List<Evento> resultList = query.getResultList();
        return resultList;
    }

    public List<Evento> ultimosEventos() {
        Query query = getEntityManager().createNamedQuery("Evento.findUltimosEventos").setMaxResults(4);
        List<Evento> resultList = query.getResultList();
        return resultList;
    }

    public Evento mostrarEvento(Long eveid) {
        Query query = getEntityManager().createNamedQuery("Evento.findByEveid");
        query.setParameter("eveid", eveid);
        List<Evento> listado = query.getResultList();
        if (listado.isEmpty()) {
            return null;
        }
        return listado.get(0);
    }
}
