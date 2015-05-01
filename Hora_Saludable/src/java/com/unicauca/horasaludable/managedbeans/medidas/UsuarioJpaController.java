/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.managedbeans.medidas;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.unicauca.horasaludable.entities.Cargo;
import com.unicauca.horasaludable.entities.Usuario;
import com.unicauca.horasaludable.entities.Unidadacademica;
import com.unicauca.horasaludable.entities.Grupo;
import java.util.ArrayList;
import java.util.List;
import com.unicauca.horasaludable.entities.Detalleasistencia;
import com.unicauca.horasaludable.entities.Detalleinscripcion;
import com.unicauca.horasaludable.entities.Medida;
import com.unicauca.horasaludable.managedbeans.medidas.exceptions.IllegalOrphanException;
import com.unicauca.horasaludable.managedbeans.medidas.exceptions.NonexistentEntityException;
import com.unicauca.horasaludable.managedbeans.medidas.exceptions.RollbackFailureException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Jhonny Taborda
 */
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) throws RollbackFailureException, Exception {
        if (usuario.getGrupoList() == null) {
            usuario.setGrupoList(new ArrayList<Grupo>());
        }
        if (usuario.getDetalleasistenciaList() == null) {
            usuario.setDetalleasistenciaList(new ArrayList<Detalleasistencia>());
        }
        if (usuario.getUsuarioList() == null) {
            usuario.setUsuarioList(new ArrayList<Usuario>());
        }
        if (usuario.getDetalleinscripcionList() == null) {
            usuario.setDetalleinscripcionList(new ArrayList<Detalleinscripcion>());
        }
        if (usuario.getMedidaList() == null) {
            usuario.setMedidaList(new ArrayList<Medida>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Cargo carid = usuario.getCarid();
            if (carid != null) {
                carid = em.getReference(carid.getClass(), carid.getCarid());
                usuario.setCarid(carid);
            }
            Usuario conyugeid = usuario.getConyugeid();
            if (conyugeid != null) {
                conyugeid = em.getReference(conyugeid.getClass(), conyugeid.getUsuid());
                usuario.setConyugeid(conyugeid);
            }
            Unidadacademica uniid = usuario.getUniid();
            if (uniid != null) {
                uniid = em.getReference(uniid.getClass(), uniid.getUniid());
                usuario.setUniid(uniid);
            }
            List<Grupo> attachedGrupoList = new ArrayList<Grupo>();
            for (Grupo grupoListGrupoToAttach : usuario.getGrupoList()) {
                grupoListGrupoToAttach = em.getReference(grupoListGrupoToAttach.getClass(), grupoListGrupoToAttach.getGruid());
                attachedGrupoList.add(grupoListGrupoToAttach);
            }
            usuario.setGrupoList(attachedGrupoList);
            List<Detalleasistencia> attachedDetalleasistenciaList = new ArrayList<Detalleasistencia>();
            for (Detalleasistencia detalleasistenciaListDetalleasistenciaToAttach : usuario.getDetalleasistenciaList()) {
                detalleasistenciaListDetalleasistenciaToAttach = em.getReference(detalleasistenciaListDetalleasistenciaToAttach.getClass(), detalleasistenciaListDetalleasistenciaToAttach.getDetalleasistenciaPK());
                attachedDetalleasistenciaList.add(detalleasistenciaListDetalleasistenciaToAttach);
            }
            usuario.setDetalleasistenciaList(attachedDetalleasistenciaList);
            List<Usuario> attachedUsuarioList = new ArrayList<Usuario>();
            for (Usuario usuarioListUsuarioToAttach : usuario.getUsuarioList()) {
                usuarioListUsuarioToAttach = em.getReference(usuarioListUsuarioToAttach.getClass(), usuarioListUsuarioToAttach.getUsuid());
                attachedUsuarioList.add(usuarioListUsuarioToAttach);
            }
            usuario.setUsuarioList(attachedUsuarioList);
            List<Detalleinscripcion> attachedDetalleinscripcionList = new ArrayList<Detalleinscripcion>();
            for (Detalleinscripcion detalleinscripcionListDetalleinscripcionToAttach : usuario.getDetalleinscripcionList()) {
                detalleinscripcionListDetalleinscripcionToAttach = em.getReference(detalleinscripcionListDetalleinscripcionToAttach.getClass(), detalleinscripcionListDetalleinscripcionToAttach.getDetalleinscripcionPK());
                attachedDetalleinscripcionList.add(detalleinscripcionListDetalleinscripcionToAttach);
            }
            usuario.setDetalleinscripcionList(attachedDetalleinscripcionList);
            List<Medida> attachedMedidaList = new ArrayList<Medida>();
            for (Medida medidaListMedidaToAttach : usuario.getMedidaList()) {
                medidaListMedidaToAttach = em.getReference(medidaListMedidaToAttach.getClass(), medidaListMedidaToAttach.getMedid());
                attachedMedidaList.add(medidaListMedidaToAttach);
            }
            usuario.setMedidaList(attachedMedidaList);
            em.persist(usuario);
            if (carid != null) {
                carid.getUsuarioList().add(usuario);
                carid = em.merge(carid);
            }
            if (conyugeid != null) {
                conyugeid.getUsuarioList().add(usuario);
                conyugeid = em.merge(conyugeid);
            }
            if (uniid != null) {
                uniid.getUsuarioList().add(usuario);
                uniid = em.merge(uniid);
            }
            for (Grupo grupoListGrupo : usuario.getGrupoList()) {
                grupoListGrupo.getUsuarioList().add(usuario);
                grupoListGrupo = em.merge(grupoListGrupo);
            }
            for (Detalleasistencia detalleasistenciaListDetalleasistencia : usuario.getDetalleasistenciaList()) {
                Usuario oldUsuarioOfDetalleasistenciaListDetalleasistencia = detalleasistenciaListDetalleasistencia.getUsuario();
                detalleasistenciaListDetalleasistencia.setUsuario(usuario);
                detalleasistenciaListDetalleasistencia = em.merge(detalleasistenciaListDetalleasistencia);
                if (oldUsuarioOfDetalleasistenciaListDetalleasistencia != null) {
                    oldUsuarioOfDetalleasistenciaListDetalleasistencia.getDetalleasistenciaList().remove(detalleasistenciaListDetalleasistencia);
                    oldUsuarioOfDetalleasistenciaListDetalleasistencia = em.merge(oldUsuarioOfDetalleasistenciaListDetalleasistencia);
                }
            }
            for (Usuario usuarioListUsuario : usuario.getUsuarioList()) {
                Usuario oldConyugeidOfUsuarioListUsuario = usuarioListUsuario.getConyugeid();
                usuarioListUsuario.setConyugeid(usuario);
                usuarioListUsuario = em.merge(usuarioListUsuario);
                if (oldConyugeidOfUsuarioListUsuario != null) {
                    oldConyugeidOfUsuarioListUsuario.getUsuarioList().remove(usuarioListUsuario);
                    oldConyugeidOfUsuarioListUsuario = em.merge(oldConyugeidOfUsuarioListUsuario);
                }
            }
            for (Detalleinscripcion detalleinscripcionListDetalleinscripcion : usuario.getDetalleinscripcionList()) {
                Usuario oldUsuarioOfDetalleinscripcionListDetalleinscripcion = detalleinscripcionListDetalleinscripcion.getUsuario();
                detalleinscripcionListDetalleinscripcion.setUsuario(usuario);
                detalleinscripcionListDetalleinscripcion = em.merge(detalleinscripcionListDetalleinscripcion);
                if (oldUsuarioOfDetalleinscripcionListDetalleinscripcion != null) {
                    oldUsuarioOfDetalleinscripcionListDetalleinscripcion.getDetalleinscripcionList().remove(detalleinscripcionListDetalleinscripcion);
                    oldUsuarioOfDetalleinscripcionListDetalleinscripcion = em.merge(oldUsuarioOfDetalleinscripcionListDetalleinscripcion);
                }
            }
            for (Medida medidaListMedida : usuario.getMedidaList()) {
                Usuario oldUsuidOfMedidaListMedida = medidaListMedida.getUsuid();
                medidaListMedida.setUsuid(usuario);
                medidaListMedida = em.merge(medidaListMedida);
                if (oldUsuidOfMedidaListMedida != null) {
                    oldUsuidOfMedidaListMedida.getMedidaList().remove(medidaListMedida);
                    oldUsuidOfMedidaListMedida = em.merge(oldUsuidOfMedidaListMedida);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getUsuid());
            Cargo caridOld = persistentUsuario.getCarid();
            Cargo caridNew = usuario.getCarid();
            Usuario conyugeidOld = persistentUsuario.getConyugeid();
            Usuario conyugeidNew = usuario.getConyugeid();
            Unidadacademica uniidOld = persistentUsuario.getUniid();
            Unidadacademica uniidNew = usuario.getUniid();
            List<Grupo> grupoListOld = persistentUsuario.getGrupoList();
            List<Grupo> grupoListNew = usuario.getGrupoList();
            List<Detalleasistencia> detalleasistenciaListOld = persistentUsuario.getDetalleasistenciaList();
            List<Detalleasistencia> detalleasistenciaListNew = usuario.getDetalleasistenciaList();
            List<Usuario> usuarioListOld = persistentUsuario.getUsuarioList();
            List<Usuario> usuarioListNew = usuario.getUsuarioList();
            List<Detalleinscripcion> detalleinscripcionListOld = persistentUsuario.getDetalleinscripcionList();
            List<Detalleinscripcion> detalleinscripcionListNew = usuario.getDetalleinscripcionList();
            List<Medida> medidaListOld = persistentUsuario.getMedidaList();
            List<Medida> medidaListNew = usuario.getMedidaList();
            List<String> illegalOrphanMessages = null;
            for (Detalleasistencia detalleasistenciaListOldDetalleasistencia : detalleasistenciaListOld) {
                if (!detalleasistenciaListNew.contains(detalleasistenciaListOldDetalleasistencia)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detalleasistencia " + detalleasistenciaListOldDetalleasistencia + " since its usuario field is not nullable.");
                }
            }
            for (Detalleinscripcion detalleinscripcionListOldDetalleinscripcion : detalleinscripcionListOld) {
                if (!detalleinscripcionListNew.contains(detalleinscripcionListOldDetalleinscripcion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detalleinscripcion " + detalleinscripcionListOldDetalleinscripcion + " since its usuario field is not nullable.");
                }
            }
            for (Medida medidaListOldMedida : medidaListOld) {
                if (!medidaListNew.contains(medidaListOldMedida)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Medida " + medidaListOldMedida + " since its usuid field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (caridNew != null) {
                caridNew = em.getReference(caridNew.getClass(), caridNew.getCarid());
                usuario.setCarid(caridNew);
            }
            if (conyugeidNew != null) {
                conyugeidNew = em.getReference(conyugeidNew.getClass(), conyugeidNew.getUsuid());
                usuario.setConyugeid(conyugeidNew);
            }
            if (uniidNew != null) {
                uniidNew = em.getReference(uniidNew.getClass(), uniidNew.getUniid());
                usuario.setUniid(uniidNew);
            }
            List<Grupo> attachedGrupoListNew = new ArrayList<Grupo>();
            for (Grupo grupoListNewGrupoToAttach : grupoListNew) {
                grupoListNewGrupoToAttach = em.getReference(grupoListNewGrupoToAttach.getClass(), grupoListNewGrupoToAttach.getGruid());
                attachedGrupoListNew.add(grupoListNewGrupoToAttach);
            }
            grupoListNew = attachedGrupoListNew;
            usuario.setGrupoList(grupoListNew);
            List<Detalleasistencia> attachedDetalleasistenciaListNew = new ArrayList<Detalleasistencia>();
            for (Detalleasistencia detalleasistenciaListNewDetalleasistenciaToAttach : detalleasistenciaListNew) {
                detalleasistenciaListNewDetalleasistenciaToAttach = em.getReference(detalleasistenciaListNewDetalleasistenciaToAttach.getClass(), detalleasistenciaListNewDetalleasistenciaToAttach.getDetalleasistenciaPK());
                attachedDetalleasistenciaListNew.add(detalleasistenciaListNewDetalleasistenciaToAttach);
            }
            detalleasistenciaListNew = attachedDetalleasistenciaListNew;
            usuario.setDetalleasistenciaList(detalleasistenciaListNew);
            List<Usuario> attachedUsuarioListNew = new ArrayList<Usuario>();
            for (Usuario usuarioListNewUsuarioToAttach : usuarioListNew) {
                usuarioListNewUsuarioToAttach = em.getReference(usuarioListNewUsuarioToAttach.getClass(), usuarioListNewUsuarioToAttach.getUsuid());
                attachedUsuarioListNew.add(usuarioListNewUsuarioToAttach);
            }
            usuarioListNew = attachedUsuarioListNew;
            usuario.setUsuarioList(usuarioListNew);
            List<Detalleinscripcion> attachedDetalleinscripcionListNew = new ArrayList<Detalleinscripcion>();
            for (Detalleinscripcion detalleinscripcionListNewDetalleinscripcionToAttach : detalleinscripcionListNew) {
                detalleinscripcionListNewDetalleinscripcionToAttach = em.getReference(detalleinscripcionListNewDetalleinscripcionToAttach.getClass(), detalleinscripcionListNewDetalleinscripcionToAttach.getDetalleinscripcionPK());
                attachedDetalleinscripcionListNew.add(detalleinscripcionListNewDetalleinscripcionToAttach);
            }
            detalleinscripcionListNew = attachedDetalleinscripcionListNew;
            usuario.setDetalleinscripcionList(detalleinscripcionListNew);
            List<Medida> attachedMedidaListNew = new ArrayList<Medida>();
            for (Medida medidaListNewMedidaToAttach : medidaListNew) {
                medidaListNewMedidaToAttach = em.getReference(medidaListNewMedidaToAttach.getClass(), medidaListNewMedidaToAttach.getMedid());
                attachedMedidaListNew.add(medidaListNewMedidaToAttach);
            }
            medidaListNew = attachedMedidaListNew;
            usuario.setMedidaList(medidaListNew);
            usuario = em.merge(usuario);
            if (caridOld != null && !caridOld.equals(caridNew)) {
                caridOld.getUsuarioList().remove(usuario);
                caridOld = em.merge(caridOld);
            }
            if (caridNew != null && !caridNew.equals(caridOld)) {
                caridNew.getUsuarioList().add(usuario);
                caridNew = em.merge(caridNew);
            }
            if (conyugeidOld != null && !conyugeidOld.equals(conyugeidNew)) {
                conyugeidOld.getUsuarioList().remove(usuario);
                conyugeidOld = em.merge(conyugeidOld);
            }
            if (conyugeidNew != null && !conyugeidNew.equals(conyugeidOld)) {
                conyugeidNew.getUsuarioList().add(usuario);
                conyugeidNew = em.merge(conyugeidNew);
            }
            if (uniidOld != null && !uniidOld.equals(uniidNew)) {
                uniidOld.getUsuarioList().remove(usuario);
                uniidOld = em.merge(uniidOld);
            }
            if (uniidNew != null && !uniidNew.equals(uniidOld)) {
                uniidNew.getUsuarioList().add(usuario);
                uniidNew = em.merge(uniidNew);
            }
            for (Grupo grupoListOldGrupo : grupoListOld) {
                if (!grupoListNew.contains(grupoListOldGrupo)) {
                    grupoListOldGrupo.getUsuarioList().remove(usuario);
                    grupoListOldGrupo = em.merge(grupoListOldGrupo);
                }
            }
            for (Grupo grupoListNewGrupo : grupoListNew) {
                if (!grupoListOld.contains(grupoListNewGrupo)) {
                    grupoListNewGrupo.getUsuarioList().add(usuario);
                    grupoListNewGrupo = em.merge(grupoListNewGrupo);
                }
            }
            for (Detalleasistencia detalleasistenciaListNewDetalleasistencia : detalleasistenciaListNew) {
                if (!detalleasistenciaListOld.contains(detalleasistenciaListNewDetalleasistencia)) {
                    Usuario oldUsuarioOfDetalleasistenciaListNewDetalleasistencia = detalleasistenciaListNewDetalleasistencia.getUsuario();
                    detalleasistenciaListNewDetalleasistencia.setUsuario(usuario);
                    detalleasistenciaListNewDetalleasistencia = em.merge(detalleasistenciaListNewDetalleasistencia);
                    if (oldUsuarioOfDetalleasistenciaListNewDetalleasistencia != null && !oldUsuarioOfDetalleasistenciaListNewDetalleasistencia.equals(usuario)) {
                        oldUsuarioOfDetalleasistenciaListNewDetalleasistencia.getDetalleasistenciaList().remove(detalleasistenciaListNewDetalleasistencia);
                        oldUsuarioOfDetalleasistenciaListNewDetalleasistencia = em.merge(oldUsuarioOfDetalleasistenciaListNewDetalleasistencia);
                    }
                }
            }
            for (Usuario usuarioListOldUsuario : usuarioListOld) {
                if (!usuarioListNew.contains(usuarioListOldUsuario)) {
                    usuarioListOldUsuario.setConyugeid(null);
                    usuarioListOldUsuario = em.merge(usuarioListOldUsuario);
                }
            }
            for (Usuario usuarioListNewUsuario : usuarioListNew) {
                if (!usuarioListOld.contains(usuarioListNewUsuario)) {
                    Usuario oldConyugeidOfUsuarioListNewUsuario = usuarioListNewUsuario.getConyugeid();
                    usuarioListNewUsuario.setConyugeid(usuario);
                    usuarioListNewUsuario = em.merge(usuarioListNewUsuario);
                    if (oldConyugeidOfUsuarioListNewUsuario != null && !oldConyugeidOfUsuarioListNewUsuario.equals(usuario)) {
                        oldConyugeidOfUsuarioListNewUsuario.getUsuarioList().remove(usuarioListNewUsuario);
                        oldConyugeidOfUsuarioListNewUsuario = em.merge(oldConyugeidOfUsuarioListNewUsuario);
                    }
                }
            }
            for (Detalleinscripcion detalleinscripcionListNewDetalleinscripcion : detalleinscripcionListNew) {
                if (!detalleinscripcionListOld.contains(detalleinscripcionListNewDetalleinscripcion)) {
                    Usuario oldUsuarioOfDetalleinscripcionListNewDetalleinscripcion = detalleinscripcionListNewDetalleinscripcion.getUsuario();
                    detalleinscripcionListNewDetalleinscripcion.setUsuario(usuario);
                    detalleinscripcionListNewDetalleinscripcion = em.merge(detalleinscripcionListNewDetalleinscripcion);
                    if (oldUsuarioOfDetalleinscripcionListNewDetalleinscripcion != null && !oldUsuarioOfDetalleinscripcionListNewDetalleinscripcion.equals(usuario)) {
                        oldUsuarioOfDetalleinscripcionListNewDetalleinscripcion.getDetalleinscripcionList().remove(detalleinscripcionListNewDetalleinscripcion);
                        oldUsuarioOfDetalleinscripcionListNewDetalleinscripcion = em.merge(oldUsuarioOfDetalleinscripcionListNewDetalleinscripcion);
                    }
                }
            }
            for (Medida medidaListNewMedida : medidaListNew) {
                if (!medidaListOld.contains(medidaListNewMedida)) {
                    Usuario oldUsuidOfMedidaListNewMedida = medidaListNewMedida.getUsuid();
                    medidaListNewMedida.setUsuid(usuario);
                    medidaListNewMedida = em.merge(medidaListNewMedida);
                    if (oldUsuidOfMedidaListNewMedida != null && !oldUsuidOfMedidaListNewMedida.equals(usuario)) {
                        oldUsuidOfMedidaListNewMedida.getMedidaList().remove(medidaListNewMedida);
                        oldUsuidOfMedidaListNewMedida = em.merge(oldUsuidOfMedidaListNewMedida);
                    }
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = usuario.getUsuid();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getUsuid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Detalleasistencia> detalleasistenciaListOrphanCheck = usuario.getDetalleasistenciaList();
            for (Detalleasistencia detalleasistenciaListOrphanCheckDetalleasistencia : detalleasistenciaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Detalleasistencia " + detalleasistenciaListOrphanCheckDetalleasistencia + " in its detalleasistenciaList field has a non-nullable usuario field.");
            }
            List<Detalleinscripcion> detalleinscripcionListOrphanCheck = usuario.getDetalleinscripcionList();
            for (Detalleinscripcion detalleinscripcionListOrphanCheckDetalleinscripcion : detalleinscripcionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Detalleinscripcion " + detalleinscripcionListOrphanCheckDetalleinscripcion + " in its detalleinscripcionList field has a non-nullable usuario field.");
            }
            List<Medida> medidaListOrphanCheck = usuario.getMedidaList();
            for (Medida medidaListOrphanCheckMedida : medidaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Medida " + medidaListOrphanCheckMedida + " in its medidaList field has a non-nullable usuid field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Cargo carid = usuario.getCarid();
            if (carid != null) {
                carid.getUsuarioList().remove(usuario);
                carid = em.merge(carid);
            }
            Usuario conyugeid = usuario.getConyugeid();
            if (conyugeid != null) {
                conyugeid.getUsuarioList().remove(usuario);
                conyugeid = em.merge(conyugeid);
            }
            Unidadacademica uniid = usuario.getUniid();
            if (uniid != null) {
                uniid.getUsuarioList().remove(usuario);
                uniid = em.merge(uniid);
            }
            List<Grupo> grupoList = usuario.getGrupoList();
            for (Grupo grupoListGrupo : grupoList) {
                grupoListGrupo.getUsuarioList().remove(usuario);
                grupoListGrupo = em.merge(grupoListGrupo);
            }
            List<Usuario> usuarioList = usuario.getUsuarioList();
            for (Usuario usuarioListUsuario : usuarioList) {
                usuarioListUsuario.setConyugeid(null);
                usuarioListUsuario = em.merge(usuarioListUsuario);
            }
            em.remove(usuario);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Usuario findUsuario(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
