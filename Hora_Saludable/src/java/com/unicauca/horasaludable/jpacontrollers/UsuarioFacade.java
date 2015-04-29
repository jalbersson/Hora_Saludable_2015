/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.jpacontrollers;

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
public class UsuarioFacade extends AbstractFacade<Usuario> {
    @PersistenceContext(unitName = "Hora_SaludablePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    public boolean buscarPorNombreUsuario(String nombreUsuario)
    {
        Query query = getEntityManager().createNamedQuery("Usuario.findByUsunombreusuario");
        query.setParameter("usunombreusuario", nombreUsuario);
        List<Usuario> resultList = query.getResultList();
        return !resultList.isEmpty();        
    }
    
    public boolean buscarPorNumeroIdentificacion(Long numeroIdentificacion)
    {
        Query query = getEntityManager().createNamedQuery("Usuario.findByUsuidentificacion");
        query.setParameter("usuidentificacion", numeroIdentificacion);
        List<Usuario> resultList = query.getResultList();
        return !resultList.isEmpty();
        
    }
    public List<Usuario> buscarPorUsuariosConCargo()
    {
        Query query = getEntityManager().createNamedQuery("Usuario.findByCargo");        
        List<Usuario> resultList = query.getResultList();
        
        return resultList;
        
    }
    public boolean buscarPorConyugeid(Long conyugeid)
    {
        Query query = getEntityManager().createNamedQuery("Usuario.findByconyugeid");
        query.setParameter("conyugeid", conyugeid);
        List<Usuario> resultList = query.getResultList();
        return !resultList.isEmpty();
        
    }
    public List<Usuario> busacarPorNombreOApellidos(String nombre)
    {
        Query query = getEntityManager().createNamedQuery("Usuario.findByName");
        query.setParameter("nombre", "%"+nombre+"%");
        List<Usuario> resultList = query.getResultList();
        return resultList;
    }
         
    
    
    
}
