/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.jpacontrollers;

import com.unicauca.horasaludable.entities.Cargo;
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
public class CargoFacade extends AbstractFacade<Cargo> {
    @PersistenceContext(unitName = "Hora_SaludablePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CargoFacade() {
        super(Cargo.class);
    }
    
    public List<Cargo> buscarPorId(Long carid)
    {
        Query query = getEntityManager().createNamedQuery("Cargo.findByCarid");
        query.setParameter("carid", carid);
        List<Cargo> resultList = query.getResultList();
        
        return resultList;
    }
    
}
