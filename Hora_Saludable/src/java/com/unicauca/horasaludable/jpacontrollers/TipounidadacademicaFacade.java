/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.jpacontrollers;

import com.unicauca.horasaludable.entities.Tipounidadacademica;
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
public class TipounidadacademicaFacade extends AbstractFacade<Tipounidadacademica> {

    @PersistenceContext(unitName = "Hora_SaludablePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipounidadacademicaFacade() {
        super(Tipounidadacademica.class);
    }

    public List<Unidadacademica> findBYFacultades() {
        Query query = getEntityManager().createNamedQuery("Unidadacademica.findByTipo");
        query.setParameter("tiponombre", "Facultad");
        List<Unidadacademica> resultList = query.getResultList();

        return resultList;
    }

    public List<Unidadacademica> findBYDivisiones() {
        Query query = getEntityManager().createNamedQuery("Unidadacademica.findByTipo");
        query.setParameter("tiponombre", "Division");
        List<Unidadacademica> resultList = query.getResultList();

        return resultList;
    }

    public List<Unidadacademica> buscarPorId(Long uniid) {
        Query query = getEntityManager().createNamedQuery("Unidadacademica.findByUniid");
        query.setParameter("uniid", uniid);
        List<Unidadacademica> resultList = query.getResultList();

        return resultList;
    }

    public List<Object[]> retornarUnidadesAcademicas() {
        Query query = getEntityManager().createNamedQuery("Unidadacademica.retornarUnidadesAcademicas");
        List<Object[]> resultList = query.getResultList();

        return resultList;
    }

}
