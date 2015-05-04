/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.jpacontrollers;

import com.unicauca.horasaludable.entities.Detalleinscripcion;
import com.unicauca.horasaludable.entities.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author seven
 */
@Stateless
public class DetalleinscripcionFacade extends AbstractFacade<Detalleinscripcion> {

    @PersistenceContext(unitName = "Hora_SaludablePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetalleinscripcionFacade() {
        super(Detalleinscripcion.class);
    }

    public List<Usuario> usariosActivos(boolean activo, String mes, int anio) {
        try {
            String queryString = "SELECT u "
                    + "FROM Usuario u JOIN u.detalleinscripcionList d JOIN d.inscripcion i "
                    + "WHERE d.detactivo = :activo AND i.insmes = :mes AND i.insanio = :anio";

            TypedQuery<Usuario> query = getEntityManager().createQuery(queryString, Usuario.class);
            query.setParameter("activo", activo);
            query.setParameter("mes", mes);
            query.setParameter("anio", anio);
            return query.getResultList();
        } finally {
            // em.close();
            //return null;
        }
    }

}
