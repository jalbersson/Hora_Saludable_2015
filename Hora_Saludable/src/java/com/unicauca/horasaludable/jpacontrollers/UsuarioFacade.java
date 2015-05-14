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

    public boolean buscarPorNombreUsuario(String nombreUsuario) {
        Query query = getEntityManager().createNamedQuery("Usuario.findByUsunombreusuario");
        query.setParameter("usunombreusuario", nombreUsuario);
        List<Usuario> resultList = query.getResultList();
        return !resultList.isEmpty();
    }

    public List<Usuario> retornarBuscarPorNombreUsuario(String nombreUsuario) {
        Query query = getEntityManager().createNamedQuery("Usuario.findByUsunombreusuario");
        query.setParameter("usunombreusuario", nombreUsuario);
        List<Usuario> resultList = query.getResultList();
        return resultList;
    }

    public boolean buscarPorNumeroIdentificacion(Long numeroIdentificacion) {
        Query query = getEntityManager().createNamedQuery("Usuario.findByUsuidentificacion");
        query.setParameter("usuidentificacion", numeroIdentificacion);
        List<Usuario> resultList = query.getResultList();
        return !resultList.isEmpty();

    }

    public List<Usuario> buscarPorUsuariosConCargo() {
        Query query = getEntityManager().createNamedQuery("Usuario.findByCargo");
        List<Usuario> resultList = query.getResultList();

        return resultList;

    }

    public boolean buscarPorConyugeid(Long conyugeid) {
        Query query = getEntityManager().createNamedQuery("Usuario.findByconyugeid");
        query.setParameter("conyugeid", conyugeid);
        List<Usuario> resultList = query.getResultList();
        return !resultList.isEmpty();

    }

    public List<Usuario> busacarPorNombreFuncionario(String nombre) {
        Query query = getEntityManager().createNamedQuery("Usuario.findByNameFuncionarios");
        query.setParameter("nombre", "%" + nombre + "%");
        List<Usuario> resultList = query.getResultList();
        return resultList;
    }

    public List<Usuario> busacarPorNombreEstudiante(String nombre) {
        Query query = getEntityManager().createNamedQuery("Usuario.findByNameEstudiante");
        query.setParameter("nombre", "%" + nombre + "%");
        List<Usuario> resultList = query.getResultList();
        return resultList;
    }

    public List<Usuario> busacarPorNombreFamiliar(String nombre) {
        Query query = getEntityManager().createNamedQuery("Usuario.findByNameFamiliar");
        query.setParameter("nombre", "%" + nombre + "%");
        List<Usuario> resultList = query.getResultList();
        return resultList;
    }

    public List<Usuario> buscarPorEstudiantes() {
        Query query = getEntityManager().createNamedQuery("Usuario.findByEstudents");
        List<Usuario> resultList = query.getResultList();
        return resultList;

    }

    public List<Usuario> buscarPorFamiliares() {
        Query query = getEntityManager().createNamedQuery("Usuario.findByFamiliars");
        List<Usuario> resultList = query.getResultList();
        return resultList;
    }

    public boolean buscarPorEmail(String usuemail) {
        Query query = getEntityManager().createNamedQuery("Usuario.findByUsuemail");
        query.setParameter("usuemail", usuemail);
        List<Usuario> resultList = query.getResultList();
        return !resultList.isEmpty();

    }

    public List<Usuario> buscarPorIdentificacionEstudiante(Long usuidentificacion) {
        Query query = getEntityManager().createNamedQuery("Usuario.findByIdentiEstudiante");
        query.setParameter("usuidentificacion", usuidentificacion);
        List<Usuario> resultList = query.getResultList();
        return resultList;

    }

    public List<Usuario> buscarPorIdentificacionFamiliares(Long usuidentificacion) {
        Query query = getEntityManager().createNamedQuery("Usuario.findByIdentiFamiliar");
        query.setParameter("usuidentificacion", usuidentificacion);
        List<Usuario> resultList = query.getResultList();
        return resultList;

    }

    public List<Usuario> buscarPorIdentificacionFuncionario(Long usuidentificacion) {
        Query query = getEntityManager().createNamedQuery("Usuario.findByIdentiFuncionarios");
        query.setParameter("usuidentificacion", usuidentificacion);
        List<Usuario> resultList = query.getResultList();
        return resultList;
    }

    public List<Usuario> buscarPorIdUsuario(Long usuid) {
        Query query = getEntityManager().createNamedQuery("Usuario.findByUsuid");
        query.setParameter("usuid", usuid);
        List<Usuario> resultList = query.getResultList();
        return resultList;
    }

    public Usuario buscarUsuarioPorEmail(String usuemail) {
        Query query = getEntityManager().createNamedQuery("Usuario.findByUsuemail");
        query.setParameter("usuemail", usuemail);
        List<Usuario> listado = query.getResultList();
        if (listado.isEmpty()) {
            return null;
        }
        return listado.get(0);
    }

    public List<Usuario> buscarTodos() {
        Query query = getEntityManager().createNamedQuery("Usuario.findAll");
        List<Usuario> resultList = query.getResultList();
        return resultList;
    }
}
