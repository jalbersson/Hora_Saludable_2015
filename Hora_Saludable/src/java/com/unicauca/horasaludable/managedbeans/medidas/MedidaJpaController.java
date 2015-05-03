/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.horasaludable.managedbeans.medidas;

import com.unicauca.horasaludable.entities.Medida;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.unicauca.horasaludable.entities.Usuario;
import com.unicauca.horasaludable.managedbeans.medidas.exceptions.NonexistentEntityException;
import com.unicauca.horasaludable.managedbeans.medidas.exceptions.RollbackFailureException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Jhonny
 */
public class MedidaJpaController implements Serializable {

    public MedidaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Medida medida) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Usuario usuid = medida.getUsuid();
            if (usuid != null) {
                usuid = em.getReference(usuid.getClass(), usuid.getUsuid());
                medida.setUsuid(usuid);
            }
            em.persist(medida);
            if (usuid != null) {
                usuid.getMedidaList().add(medida);
                usuid = em.merge(usuid);
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

    public void edit(Medida medida) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Medida persistentMedida = em.find(Medida.class, medida.getMedid());
            Usuario usuidOld = persistentMedida.getUsuid();
            Usuario usuidNew = medida.getUsuid();
            if (usuidNew != null) {
                usuidNew = em.getReference(usuidNew.getClass(), usuidNew.getUsuid());
                medida.setUsuid(usuidNew);
            }
            medida = em.merge(medida);
            if (usuidOld != null && !usuidOld.equals(usuidNew)) {
                usuidOld.getMedidaList().remove(medida);
                usuidOld = em.merge(usuidOld);
            }
            if (usuidNew != null && !usuidNew.equals(usuidOld)) {
                usuidNew.getMedidaList().add(medida);
                usuidNew = em.merge(usuidNew);
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
                Long id = medida.getMedid();
                if (findMedida(id) == null) {
                    throw new NonexistentEntityException("The medida with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Medida medida;
            try {
                medida = em.getReference(Medida.class, id);
                medida.getMedid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The medida with id " + id + " no longer exists.", enfe);
            }
            Usuario usuid = medida.getUsuid();
            if (usuid != null) {
                usuid.getMedidaList().remove(medida);
                usuid = em.merge(usuid);
            }
            em.remove(medida);
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

    public List<Medida> findMedidaEntities() {
        return findMedidaEntities(true, -1, -1);
    }

    public List<Medida> findMedidaEntities(int maxResults, int firstResult) {
        return findMedidaEntities(false, maxResults, firstResult);
    }

    private List<Medida> findMedidaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Medida.class));
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

    public Medida findMedida(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Medida.class, id);
        } finally {
            em.close();
        }
    }

    public int getMedidaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Medida> rt = cq.from(Medida.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    
    
}
