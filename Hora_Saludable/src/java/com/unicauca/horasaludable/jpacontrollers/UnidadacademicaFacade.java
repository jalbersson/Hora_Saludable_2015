/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.jpacontrollers;

import com.unicauca.horasaludable.entities.Unidadacademica;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author seven
 */
@Stateless
public class UnidadacademicaFacade extends AbstractFacade<Unidadacademica> {
    @PersistenceContext(unitName = "Hora_SaludablePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UnidadacademicaFacade() {
        super(Unidadacademica.class);
    }
    
    public List<Unidadacademica> findBYFacultades()
    {
        Query query = getEntityManager().createNamedQuery("Unidadacademica.findByTipo");
        query.setParameter("tiponombre", "Facultad");
        List<Unidadacademica> resultList = query.getResultList();
        
        return resultList;
    }
    public List<Unidadacademica> findBYDivisiones()
    {
        Query query = getEntityManager().createNamedQuery("Unidadacademica.findByTipo");
        query.setParameter("tiponombre", "Division");
        List<Unidadacademica> resultList = query.getResultList();
        
        return resultList;
    }
}
