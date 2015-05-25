/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.jpacontrollers;

import com.unicauca.horasaludable.entities.Horario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Yuri
 */
@Stateless
public class HorarioFacade extends AbstractFacade<Horario> {
    @PersistenceContext(unitName = "Hora_SaludablePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HorarioFacade() {
        super(Horario.class);
    }
    
    public List<Horario> lista() {
        Query query = getEntityManager().createNamedQuery("Horario.findAll");
        List<Horario> resultList = query.getResultList();
        return resultList;
    }
    
    public Horario mostrarHorario(Long id) {
        Query query = getEntityManager().createNamedQuery("Horario.findByHorid");
        query.setParameter("horid", id);
        List<Horario> listado = query.getResultList();
        if (listado.isEmpty()) {
            return null;
        }
        return listado.get(0);
    }
    
}
