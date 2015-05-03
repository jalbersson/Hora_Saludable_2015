/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.jpacontrollers;

import com.unicauca.horasaludable.entities.Asistencia;
import com.unicauca.horasaludable.entities.Usuario;
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
public class AsistenciaFacade extends AbstractFacade<Asistencia> {
    @PersistenceContext(unitName = "Hora_SaludablePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AsistenciaFacade() {
        super(Asistencia.class);
    }
    
    public List<Usuario> buscarporUsuid(int usuid)
    {      
       Query query = getEntityManager().createNamedQuery("Usuario.findByUsuid");
       query.setParameter("usuid", usuid);
       List<Usuario> resultList=query.getResultList();
       return resultList;
    }
    
}
