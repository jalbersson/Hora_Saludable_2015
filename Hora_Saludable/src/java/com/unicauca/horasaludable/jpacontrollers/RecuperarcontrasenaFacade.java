/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.jpacontrollers;

import com.unicauca.horasaludable.entities.Recuperarcontrasena;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


/**
 *
 * @author geovanny
 */
@Stateless
public class RecuperarcontrasenaFacade extends AbstractFacade<Recuperarcontrasena> {
    @PersistenceContext(unitName = "Hora_SaludablePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RecuperarcontrasenaFacade() {
        super(Recuperarcontrasena.class);
    }
    
    public Recuperarcontrasena buscarRecuperarContrasenaCifrado(String reidcifrado){
        Query query = getEntityManager().createNamedQuery("Recuperarcontrasena.findByReidcifrado");
        query.setParameter("reidcifrado", reidcifrado);
        List<Recuperarcontrasena> listado = query.getResultList();
        if(listado.isEmpty()){
            return null;
        }
        return listado.get(0);
    } 
}
