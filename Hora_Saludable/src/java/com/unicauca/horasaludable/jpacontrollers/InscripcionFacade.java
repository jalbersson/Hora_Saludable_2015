/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.jpacontrollers;

import com.unicauca.horasaludable.entities.Inscripcion;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author seven
 */
@Stateless
public class InscripcionFacade extends AbstractFacade<Inscripcion> {

    @PersistenceContext(unitName = "Hora_SaludablePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InscripcionFacade() {
        super(Inscripcion.class);
    }

    public Inscripcion existeInscripcion(String mes, int anio) {
        try {
            String queryString = "SELECT i "
                    + "FROM Inscripcion i "
                    + "WHERE i.insmes = :mes AND i.insanio = :anio";

            TypedQuery<Inscripcion> query = getEntityManager().createQuery(queryString, Inscripcion.class);
            query.setParameter("mes", mes);
            query.setParameter("anio", anio);

            if (query.getSingleResult() != null) {
                return query.getSingleResult();
            }

            return null;
        } finally {
            // em.close();
//            return null;
        }
    }

}
