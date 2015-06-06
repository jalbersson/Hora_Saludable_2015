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
     
     public List<Medida> buscarporUsuidH(int usuid) {
        try {
            String queryString = "SELECT *, DATE_FORMAT(MEDFECHA,'%d/%m/%Y %H:%i:%s') AS fec FROM MEDIDA"
                    + " WHERE usuid=" + usuid+"  ORDER BY fec DESC ";
            Query query = getEntityManager().createNativeQuery(queryString,Medida.class);
            //System.out.println("ERR"+Long.valueOf(usuid.intValue()+""));
            //query.set("usuid", Long.valueOf(usuid.intValue()+""));
            List<Medida> res=  query.getResultList();
           
            if(res.size()>3)return res.subList(0, 3);
            else return res;
        } finally {
            // em.close();
        }
     }
    

    public List<Medida> buscarporApellido(String porApellido) 
    {
        Query query = getEntityManager().createNamedQuery("Medida.findByporApellido");
        query.setParameter("usuapellidos", porApellido);
        List<Medida> resultList2 = query.getResultList();
        return resultList2;
    }
    public List<Medida> buscarPorUsuID(int id)
    {
        try {
            String queryString = "SELECT t FROM Medida t "
                    + "where t.usuid=" + id;
            Query query = getEntityManager().createQuery(queryString);
            //System.out.println("ERR"+Long.valueOf(usuid.intValue()+""));
            //query.set("usuid", Long.valueOf(usuid.intValue()+""));
            return query.getResultList();
        } finally {
            // em.close();
        }
    }
}
