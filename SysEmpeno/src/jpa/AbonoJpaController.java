/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entities.Abono;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entities.Concepto;
import entities.Contrato;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author erick
 */
public class AbonoJpaController implements Serializable {

    public AbonoJpaController() {
        this.emf = Persistence.createEntityManagerFactory("SysEmpenoPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Abono abono) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Concepto conceptoIdconcepto = abono.getConceptoIdconcepto();
            if (conceptoIdconcepto != null) {
                conceptoIdconcepto = em.getReference(conceptoIdconcepto.getClass(), conceptoIdconcepto.getIdconcepto());
                abono.setConceptoIdconcepto(conceptoIdconcepto);
            }
            Contrato contratoIdcontrato = abono.getContratoIdcontrato();
            if (contratoIdcontrato != null) {
                contratoIdcontrato = em.getReference(contratoIdcontrato.getClass(), contratoIdcontrato.getIdcontrato());
                abono.setContratoIdcontrato(contratoIdcontrato);
            }
            em.persist(abono);
            if (conceptoIdconcepto != null) {
                conceptoIdconcepto.getAbonoList().add(abono);
                conceptoIdconcepto = em.merge(conceptoIdconcepto);
            }
            if (contratoIdcontrato != null) {
                contratoIdcontrato.getAbonoList().add(abono);
                contratoIdcontrato = em.merge(contratoIdcontrato);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Abono abono) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Abono persistentAbono = em.find(Abono.class, abono.getIdAbono());
            Concepto conceptoIdconceptoOld = persistentAbono.getConceptoIdconcepto();
            Concepto conceptoIdconceptoNew = abono.getConceptoIdconcepto();
            Contrato contratoIdcontratoOld = persistentAbono.getContratoIdcontrato();
            Contrato contratoIdcontratoNew = abono.getContratoIdcontrato();
            if (conceptoIdconceptoNew != null) {
                conceptoIdconceptoNew = em.getReference(conceptoIdconceptoNew.getClass(), conceptoIdconceptoNew.getIdconcepto());
                abono.setConceptoIdconcepto(conceptoIdconceptoNew);
            }
            if (contratoIdcontratoNew != null) {
                contratoIdcontratoNew = em.getReference(contratoIdcontratoNew.getClass(), contratoIdcontratoNew.getIdcontrato());
                abono.setContratoIdcontrato(contratoIdcontratoNew);
            }
            abono = em.merge(abono);
            if (conceptoIdconceptoOld != null && !conceptoIdconceptoOld.equals(conceptoIdconceptoNew)) {
                conceptoIdconceptoOld.getAbonoList().remove(abono);
                conceptoIdconceptoOld = em.merge(conceptoIdconceptoOld);
            }
            if (conceptoIdconceptoNew != null && !conceptoIdconceptoNew.equals(conceptoIdconceptoOld)) {
                conceptoIdconceptoNew.getAbonoList().add(abono);
                conceptoIdconceptoNew = em.merge(conceptoIdconceptoNew);
            }
            if (contratoIdcontratoOld != null && !contratoIdcontratoOld.equals(contratoIdcontratoNew)) {
                contratoIdcontratoOld.getAbonoList().remove(abono);
                contratoIdcontratoOld = em.merge(contratoIdcontratoOld);
            }
            if (contratoIdcontratoNew != null && !contratoIdcontratoNew.equals(contratoIdcontratoOld)) {
                contratoIdcontratoNew.getAbonoList().add(abono);
                contratoIdcontratoNew = em.merge(contratoIdcontratoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = abono.getIdAbono();
                if (findAbono(id) == null) {
                    throw new NonexistentEntityException("The abono with id " + id + " no longer exists.");
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
            Abono abono;
            try {
                abono = em.getReference(Abono.class, id);
                abono.getIdAbono();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The abono with id " + id + " no longer exists.", enfe);
            }
            Concepto conceptoIdconcepto = abono.getConceptoIdconcepto();
            if (conceptoIdconcepto != null) {
                conceptoIdconcepto.getAbonoList().remove(abono);
                conceptoIdconcepto = em.merge(conceptoIdconcepto);
            }
            Contrato contratoIdcontrato = abono.getContratoIdcontrato();
            if (contratoIdcontrato != null) {
                contratoIdcontrato.getAbonoList().remove(abono);
                contratoIdcontrato = em.merge(contratoIdcontrato);
            }
            em.remove(abono);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Abono> findAbonoEntities() {
        return findAbonoEntities(true, -1, -1);
    }

    public List<Abono> findAbonoEntities(int maxResults, int firstResult) {
        return findAbonoEntities(false, maxResults, firstResult);
    }

    private List<Abono> findAbonoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Abono.class));
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

    public Abono findAbono(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Abono.class, id);
        } finally {
            em.close();
        }
    }

    public int getAbonoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Abono> rt = cq.from(Abono.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
