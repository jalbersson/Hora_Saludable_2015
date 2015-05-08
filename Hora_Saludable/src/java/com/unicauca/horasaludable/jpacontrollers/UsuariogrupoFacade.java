/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.jpacontrollers;

import com.unicauca.horasaludable.entities.Usuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.unicauca.horasaludable.entities.Usuariogrupo;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author geovanny
 */
@Stateless
public class UsuariogrupoFacade extends AbstractFacade<Usuariogrupo> {
    @PersistenceContext(unitName = "Hora_SaludablePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuariogrupoFacade() {
        super(Usuariogrupo.class);
    }
    public int actualizarNombreUsuario(String gruid,Long usuid,String usunombreusuario)
    {
        Query query = getEntityManager().createQuery(
        "UPDATE Usuariogrupo u SET u.usunombreusuario = "+"'"+usunombreusuario+"'"+
        " WHERE u.usuariogrupoPK.usuid = :usuid AND u.usuariogrupoPK.gruid = :gruid");
        query.setParameter("usuid", usuid);
        query.setParameter("gruid", gruid);
        return query.executeUpdate();
          
    }
}
