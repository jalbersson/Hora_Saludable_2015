/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.jpacontrollers;

import com.unicauca.horasaludable.entities.Medida;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author seven
 */
@Stateless
public class MedidaFacade extends AbstractFacade<Medida> {
    @PersistenceContext(unitName = "Hora_SaludablePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MedidaFacade() {
        super(Medida.class);
    }
    
}
