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

    public List<Usuario> buscarporUsuid(int usuid) {
        Query query = getEntityManager().createNamedQuery("Usuario.findByUsuid");
        query.setParameter("usuid", usuid);
        List<Usuario> resultList = query.getResultList();
        return resultList;
    }

    public List<Usuario> retornarBuscarPorNombreUsuario(String nombreUsuario) {
        Query query = getEntityManager().createNamedQuery("Usuario.findByUsunombreusuario");
        query.setParameter("usunombreusuario", nombreUsuario);
        List<Usuario> resultList = query.getResultList();
        return resultList;
    }

    public List<Asistencia> findByYearMonth(Integer anio, Integer mes)
    {
        Query query = getEntityManager().createNamedQuery("Asistencia.findByYearMonth");
        query.setParameter("year", anio);
        query.setParameter("month", mes);
        List<Asistencia> resultList = query.getResultList();
        return resultList;
    }
    
    public List<Asistencia> findByYearMonthDay(Integer anio, Integer mes, Integer dia)
    {
        Query query = getEntityManager().createNamedQuery("Asistencia.findByYearMonthDay");
        query.setParameter("year", anio);
        query.setParameter("month", mes);
        query.setParameter("day", dia);
        List<Asistencia> resultList = query.getResultList();
        return resultList;
    }
}
