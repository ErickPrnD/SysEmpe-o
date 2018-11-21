/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entities.Contrato;
import entities.Fotoprenda;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author erick
 */
public class FotoprendaJpaController implements Serializable {

    public FotoprendaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Fotoprenda fotoprenda) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Contrato contratoIdcontrato = fotoprenda.getContratoIdcontrato();
            if (contratoIdcontrato != null) {
                contratoIdcontrato = em.getReference(contratoIdcontrato.getClass(), contratoIdcontrato.getIdcontrato());
                fotoprenda.setContratoIdcontrato(contratoIdcontrato);
            }
            em.persist(fotoprenda);
            if (contratoIdcontrato != null) {
                contratoIdcontrato.getFotoprendaList().add(fotoprenda);
                contratoIdcontrato = em.merge(contratoIdcontrato);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Fotoprenda fotoprenda) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Fotoprenda persistentFotoprenda = em.find(Fotoprenda.class, fotoprenda.getIdfoto());
            Contrato contratoIdcontratoOld = persistentFotoprenda.getContratoIdcontrato();
            Contrato contratoIdcontratoNew = fotoprenda.getContratoIdcontrato();
            if (contratoIdcontratoNew != null) {
                contratoIdcontratoNew = em.getReference(contratoIdcontratoNew.getClass(), contratoIdcontratoNew.getIdcontrato());
                fotoprenda.setContratoIdcontrato(contratoIdcontratoNew);
            }
            fotoprenda = em.merge(fotoprenda);
            if (contratoIdcontratoOld != null && !contratoIdcontratoOld.equals(contratoIdcontratoNew)) {
                contratoIdcontratoOld.getFotoprendaList().remove(fotoprenda);
                contratoIdcontratoOld = em.merge(contratoIdcontratoOld);
            }
            if (contratoIdcontratoNew != null && !contratoIdcontratoNew.equals(contratoIdcontratoOld)) {
                contratoIdcontratoNew.getFotoprendaList().add(fotoprenda);
                contratoIdcontratoNew = em.merge(contratoIdcontratoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = fotoprenda.getIdfoto();
                if (findFotoprenda(id) == null) {
                    throw new NonexistentEntityException("The fotoprenda with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Fotoprenda fotoprenda;
            try {
                fotoprenda = em.getReference(Fotoprenda.class, id);
                fotoprenda.getIdfoto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The fotoprenda with id " + id + " no longer exists.", enfe);
            }
            Contrato contratoIdcontrato = fotoprenda.getContratoIdcontrato();
            if (contratoIdcontrato != null) {
                contratoIdcontrato.getFotoprendaList().remove(fotoprenda);
                contratoIdcontrato = em.merge(contratoIdcontrato);
            }
            em.remove(fotoprenda);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Fotoprenda> findFotoprendaEntities() {
        return findFotoprendaEntities(true, -1, -1);
    }

    public List<Fotoprenda> findFotoprendaEntities(int maxResults, int firstResult) {
        return findFotoprendaEntities(false, maxResults, firstResult);
    }

    private List<Fotoprenda> findFotoprendaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Fotoprenda.class));
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

    public Fotoprenda findFotoprenda(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Fotoprenda.class, id);
        } finally {
            em.close();
        }
    }

    public int getFotoprendaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Fotoprenda> rt = cq.from(Fotoprenda.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
