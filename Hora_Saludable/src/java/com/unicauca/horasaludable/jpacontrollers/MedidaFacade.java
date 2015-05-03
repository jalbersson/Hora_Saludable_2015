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
    
      public List<Medida> findUsuario(Long codigo)
    {
        Query query = getEntityManager().createNamedQuery("Usuario.findByUsuidentificacion");
        query.setParameter("codigo", codigo);
        List<Usuario> resultList = query.getResultList();
        List<Medida> listado = new ArrayList();
        for(Usuario el: resultList)
        {
            listado.add((Medida) el.getMedidaList());
        }
        return listado;
    }
      
     public List<Medida> buscarporMedId(int mediId) {
        try {
            String queryString = "SELECT t FROM Medida t "
                    + "where t.medid=" + mediId;
            Query query = getEntityManager().createQuery(queryString);
            //System.out.println("ERR"+Long.valueOf(usuid.intValue()+""));
            //query.set("usuid", Long.valueOf(usuid.intValue()+""));
            return query.getResultList();
        } finally {
            // em.close();
        }
     }
     
     public List<Medida> buscarporUsuid(int usuid) {
        try {
            String queryString = "SELECT *, DATE_FORMAT(MEDFECHA,'%d/%m/%Y %H:%i:%s') AS fec FROM MEDIDA"
                    + " WHERE usuid=" + usuid+"  ORDER BY fec DESC";
            Query query = getEntityManager().createNativeQuery(queryString,Medida.class);
            //System.out.println("ERR"+Long.valueOf(usuid.intValue()+""));
            //query.set("usuid", Long.valueOf(usuid.intValue()+""));
            return query.getResultList();
        } finally {
            // em.close();
        }
     }
    
}
