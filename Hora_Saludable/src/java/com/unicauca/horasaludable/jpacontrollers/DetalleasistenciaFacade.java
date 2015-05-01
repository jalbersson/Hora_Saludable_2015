/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.jpacontrollers;

import com.unicauca.horasaludable.entities.Detalleasistencia;
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
public class DetalleasistenciaFacade extends AbstractFacade<Detalleasistencia> {
    @PersistenceContext(unitName = "Hora_SaludablePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetalleasistenciaFacade() {
        super(Detalleasistencia.class);
    }
    
    public List<Detalleasistencia> obtenerAsisUsuid(int usuid){
        try
        {
            String queryString = "SELECT t FROM Detalleasistencia t "+
            "where t.usuario.usuid="+usuid;
            Query query = getEntityManager().createQuery(queryString);  
            //System.out.println("ERR"+Long.valueOf(usuid.intValue()+""));
            //query.set("usuid", Long.valueOf(usuid.intValue()+""));
            return query.getResultList();       
        }finally 
        {
           // em.close();
        }
    }
}
