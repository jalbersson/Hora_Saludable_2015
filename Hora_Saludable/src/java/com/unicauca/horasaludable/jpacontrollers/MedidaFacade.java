/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.jpacontrollers;

import com.unicauca.horasaludable.entities.Medida;
import com.unicauca.horasaludable.entities.Usuario;
import java.util.ArrayList;
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
public class MedidaFacade extends AbstractFacade<Medida> {
    @PersistenceContext(unitName = "Hora_SaludablePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    
    public MedidaFacade() 
    {
        super(Medida.class);
    }
    
    public List<Medida> buscarMedidaUsuario(Long usuidentificacion)
    {
        Query query = getEntityManager().createNamedQuery("Medida.findByMedidasPorUsuario");
        query.setParameter("usuidentificacion", usuidentificacion);
        List<Medida> resultList = query.getResultList();
        return resultList;
    }

    public List<Medida> buscarporApellido(String porApellido) 
    {
        Query query = getEntityManager().createNamedQuery("Medida.findByporApellido");
        query.setParameter("usuapellidos", porApellido);
        List<Medida> resultList2 = query.getResultList();
        return resultList2;
    }
 
}
