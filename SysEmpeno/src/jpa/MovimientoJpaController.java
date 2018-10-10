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
import Entities.Concepto;
import Entities.Movimiento;
import Entities.Mueve;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author erick
 */
public class MovimientoJpaController implements Serializable {

    public MovimientoJpaController() {
        this.emf = Persistence.createEntityManagerFactory("SysEmpenoPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Movimiento movimiento) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Concepto conceptoIdconcepto = movimiento.getConceptoIdconcepto();
            if (conceptoIdconcepto != null) {
                conceptoIdconcepto = em.getReference(conceptoIdconcepto.getClass(), conceptoIdconcepto.getIdconcepto());
                movimiento.setConceptoIdconcepto(conceptoIdconcepto);
            }
            Mueve captura = movimiento.getCaptura();
            if (captura != null) {
                captura = em.getReference(captura.getClass(), captura.getIdmueve());
                movimiento.setCaptura(captura);
            }
            Mueve modificacion = movimiento.getModificacion();
            if (modificacion != null) {
                modificacion = em.getReference(modificacion.getClass(), modificacion.getIdmueve());
                movimiento.setModificacion(modificacion);
            }
            Mueve cancelacion = movimiento.getCancelacion();
            if (cancelacion != null) {
                cancelacion = em.getReference(cancelacion.getClass(), cancelacion.getIdmueve());
                movimiento.setCancelacion(cancelacion);
            }
            em.persist(movimiento);
            if (conceptoIdconcepto != null) {
                conceptoIdconcepto.getMovimientoList().add(movimiento);
                conceptoIdconcepto = em.merge(conceptoIdconcepto);
            }
            if (captura != null) {
                captura.getMovimientoList().add(movimiento);
                captura = em.merge(captura);
            }
            if (modificacion != null) {
                modificacion.getMovimientoList().add(movimiento);
                modificacion = em.merge(modificacion);
            }
            if (cancelacion != null) {
                cancelacion.getMovimientoList().add(movimiento);
                cancelacion = em.merge(cancelacion);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Movimiento movimiento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Movimiento persistentMovimiento = em.find(Movimiento.class, movimiento.getIdmovimiento());
            Concepto conceptoIdconceptoOld = persistentMovimiento.getConceptoIdconcepto();
            Concepto conceptoIdconceptoNew = movimiento.getConceptoIdconcepto();
            Mueve capturaOld = persistentMovimiento.getCaptura();
            Mueve capturaNew = movimiento.getCaptura();
            Mueve modificacionOld = persistentMovimiento.getModificacion();
            Mueve modificacionNew = movimiento.getModificacion();
            Mueve cancelacionOld = persistentMovimiento.getCancelacion();
            Mueve cancelacionNew = movimiento.getCancelacion();
            if (conceptoIdconceptoNew != null) {
                conceptoIdconceptoNew = em.getReference(conceptoIdconceptoNew.getClass(), conceptoIdconceptoNew.getIdconcepto());
                movimiento.setConceptoIdconcepto(conceptoIdconceptoNew);
            }
            if (capturaNew != null) {
                capturaNew = em.getReference(capturaNew.getClass(), capturaNew.getIdmueve());
                movimiento.setCaptura(capturaNew);
            }
            if (modificacionNew != null) {
                modificacionNew = em.getReference(modificacionNew.getClass(), modificacionNew.getIdmueve());
                movimiento.setModificacion(modificacionNew);
            }
            if (cancelacionNew != null) {
                cancelacionNew = em.getReference(cancelacionNew.getClass(), cancelacionNew.getIdmueve());
                movimiento.setCancelacion(cancelacionNew);
            }
            movimiento = em.merge(movimiento);
            if (conceptoIdconceptoOld != null && !conceptoIdconceptoOld.equals(conceptoIdconceptoNew)) {
                conceptoIdconceptoOld.getMovimientoList().remove(movimiento);
                conceptoIdconceptoOld = em.merge(conceptoIdconceptoOld);
            }
            if (conceptoIdconceptoNew != null && !conceptoIdconceptoNew.equals(conceptoIdconceptoOld)) {
                conceptoIdconceptoNew.getMovimientoList().add(movimiento);
                conceptoIdconceptoNew = em.merge(conceptoIdconceptoNew);
            }
            if (capturaOld != null && !capturaOld.equals(capturaNew)) {
                capturaOld.getMovimientoList().remove(movimiento);
                capturaOld = em.merge(capturaOld);
            }
            if (capturaNew != null && !capturaNew.equals(capturaOld)) {
                capturaNew.getMovimientoList().add(movimiento);
                capturaNew = em.merge(capturaNew);
            }
            if (modificacionOld != null && !modificacionOld.equals(modificacionNew)) {
                modificacionOld.getMovimientoList().remove(movimiento);
                modificacionOld = em.merge(modificacionOld);
            }
            if (modificacionNew != null && !modificacionNew.equals(modificacionOld)) {
                modificacionNew.getMovimientoList().add(movimiento);
                modificacionNew = em.merge(modificacionNew);
            }
            if (cancelacionOld != null && !cancelacionOld.equals(cancelacionNew)) {
                cancelacionOld.getMovimientoList().remove(movimiento);
                cancelacionOld = em.merge(cancelacionOld);
            }
            if (cancelacionNew != null && !cancelacionNew.equals(cancelacionOld)) {
                cancelacionNew.getMovimientoList().add(movimiento);
                cancelacionNew = em.merge(cancelacionNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = movimiento.getIdmovimiento();
                if (findMovimiento(id) == null) {
                    throw new NonexistentEntityException("The movimiento with id " + id + " no longer exists.");
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
            Movimiento movimiento;
            try {
                movimiento = em.getReference(Movimiento.class, id);
                movimiento.getIdmovimiento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The movimiento with id " + id + " no longer exists.", enfe);
            }
            Concepto conceptoIdconcepto = movimiento.getConceptoIdconcepto();
            if (conceptoIdconcepto != null) {
                conceptoIdconcepto.getMovimientoList().remove(movimiento);
                conceptoIdconcepto = em.merge(conceptoIdconcepto);
            }
            Mueve captura = movimiento.getCaptura();
            if (captura != null) {
                captura.getMovimientoList().remove(movimiento);
                captura = em.merge(captura);
            }
            Mueve modificacion = movimiento.getModificacion();
            if (modificacion != null) {
                modificacion.getMovimientoList().remove(movimiento);
                modificacion = em.merge(modificacion);
            }
            Mueve cancelacion = movimiento.getCancelacion();
            if (cancelacion != null) {
                cancelacion.getMovimientoList().remove(movimiento);
                cancelacion = em.merge(cancelacion);
            }
            em.remove(movimiento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Movimiento> findMovimientoEntities() {
        return findMovimientoEntities(true, -1, -1);
    }

    public List<Movimiento> findMovimientoEntities(int maxResults, int firstResult) {
        return findMovimientoEntities(false, maxResults, firstResult);
    }

    private List<Movimiento> findMovimientoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Movimiento.class));
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

    public Movimiento findMovimiento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Movimiento.class, id);
        } finally {
            em.close();
        }
    }

    public int getMovimientoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Movimiento> rt = cq.from(Movimiento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
