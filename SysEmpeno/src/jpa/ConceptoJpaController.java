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
import entities.Abono;
import entities.Concepto;
import java.util.ArrayList;
import java.util.List;
import entities.Movimiento;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author erick
 */
public class ConceptoJpaController implements Serializable {

    public ConceptoJpaController() {
        this.emf = Persistence.createEntityManagerFactory("SysEmpenoPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Concepto concepto) {
        if (concepto.getAbonoList() == null) {
            concepto.setAbonoList(new ArrayList<Abono>());
        }
        if (concepto.getMovimientoList() == null) {
            concepto.setMovimientoList(new ArrayList<Movimiento>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Abono> attachedAbonoList = new ArrayList<Abono>();
            for (Abono abonoListAbonoToAttach : concepto.getAbonoList()) {
                abonoListAbonoToAttach = em.getReference(abonoListAbonoToAttach.getClass(), abonoListAbonoToAttach.getIdAbono());
                attachedAbonoList.add(abonoListAbonoToAttach);
            }
            concepto.setAbonoList(attachedAbonoList);
            List<Movimiento> attachedMovimientoList = new ArrayList<Movimiento>();
            for (Movimiento movimientoListMovimientoToAttach : concepto.getMovimientoList()) {
                movimientoListMovimientoToAttach = em.getReference(movimientoListMovimientoToAttach.getClass(), movimientoListMovimientoToAttach.getIdmovimiento());
                attachedMovimientoList.add(movimientoListMovimientoToAttach);
            }
            concepto.setMovimientoList(attachedMovimientoList);
            em.persist(concepto);
            for (Abono abonoListAbono : concepto.getAbonoList()) {
                Concepto oldConceptoIdconceptoOfAbonoListAbono = abonoListAbono.getConceptoIdconcepto();
                abonoListAbono.setConceptoIdconcepto(concepto);
                abonoListAbono = em.merge(abonoListAbono);
                if (oldConceptoIdconceptoOfAbonoListAbono != null) {
                    oldConceptoIdconceptoOfAbonoListAbono.getAbonoList().remove(abonoListAbono);
                    oldConceptoIdconceptoOfAbonoListAbono = em.merge(oldConceptoIdconceptoOfAbonoListAbono);
                }
            }
            for (Movimiento movimientoListMovimiento : concepto.getMovimientoList()) {
                Concepto oldConceptoIdconceptoOfMovimientoListMovimiento = movimientoListMovimiento.getConceptoIdconcepto();
                movimientoListMovimiento.setConceptoIdconcepto(concepto);
                movimientoListMovimiento = em.merge(movimientoListMovimiento);
                if (oldConceptoIdconceptoOfMovimientoListMovimiento != null) {
                    oldConceptoIdconceptoOfMovimientoListMovimiento.getMovimientoList().remove(movimientoListMovimiento);
                    oldConceptoIdconceptoOfMovimientoListMovimiento = em.merge(oldConceptoIdconceptoOfMovimientoListMovimiento);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Concepto concepto) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Concepto persistentConcepto = em.find(Concepto.class, concepto.getIdconcepto());
            List<Abono> abonoListOld = persistentConcepto.getAbonoList();
            List<Abono> abonoListNew = concepto.getAbonoList();
            List<Movimiento> movimientoListOld = persistentConcepto.getMovimientoList();
            List<Movimiento> movimientoListNew = concepto.getMovimientoList();
            List<String> illegalOrphanMessages = null;
            for (Abono abonoListOldAbono : abonoListOld) {
                if (!abonoListNew.contains(abonoListOldAbono)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Abono " + abonoListOldAbono + " since its conceptoIdconcepto field is not nullable.");
                }
            }
            for (Movimiento movimientoListOldMovimiento : movimientoListOld) {
                if (!movimientoListNew.contains(movimientoListOldMovimiento)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Movimiento " + movimientoListOldMovimiento + " since its conceptoIdconcepto field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Abono> attachedAbonoListNew = new ArrayList<Abono>();
            for (Abono abonoListNewAbonoToAttach : abonoListNew) {
                abonoListNewAbonoToAttach = em.getReference(abonoListNewAbonoToAttach.getClass(), abonoListNewAbonoToAttach.getIdAbono());
                attachedAbonoListNew.add(abonoListNewAbonoToAttach);
            }
            abonoListNew = attachedAbonoListNew;
            concepto.setAbonoList(abonoListNew);
            List<Movimiento> attachedMovimientoListNew = new ArrayList<Movimiento>();
            for (Movimiento movimientoListNewMovimientoToAttach : movimientoListNew) {
                movimientoListNewMovimientoToAttach = em.getReference(movimientoListNewMovimientoToAttach.getClass(), movimientoListNewMovimientoToAttach.getIdmovimiento());
                attachedMovimientoListNew.add(movimientoListNewMovimientoToAttach);
            }
            movimientoListNew = attachedMovimientoListNew;
            concepto.setMovimientoList(movimientoListNew);
            concepto = em.merge(concepto);
            for (Abono abonoListNewAbono : abonoListNew) {
                if (!abonoListOld.contains(abonoListNewAbono)) {
                    Concepto oldConceptoIdconceptoOfAbonoListNewAbono = abonoListNewAbono.getConceptoIdconcepto();
                    abonoListNewAbono.setConceptoIdconcepto(concepto);
                    abonoListNewAbono = em.merge(abonoListNewAbono);
                    if (oldConceptoIdconceptoOfAbonoListNewAbono != null && !oldConceptoIdconceptoOfAbonoListNewAbono.equals(concepto)) {
                        oldConceptoIdconceptoOfAbonoListNewAbono.getAbonoList().remove(abonoListNewAbono);
                        oldConceptoIdconceptoOfAbonoListNewAbono = em.merge(oldConceptoIdconceptoOfAbonoListNewAbono);
                    }
                }
            }
            for (Movimiento movimientoListNewMovimiento : movimientoListNew) {
                if (!movimientoListOld.contains(movimientoListNewMovimiento)) {
                    Concepto oldConceptoIdconceptoOfMovimientoListNewMovimiento = movimientoListNewMovimiento.getConceptoIdconcepto();
                    movimientoListNewMovimiento.setConceptoIdconcepto(concepto);
                    movimientoListNewMovimiento = em.merge(movimientoListNewMovimiento);
                    if (oldConceptoIdconceptoOfMovimientoListNewMovimiento != null && !oldConceptoIdconceptoOfMovimientoListNewMovimiento.equals(concepto)) {
                        oldConceptoIdconceptoOfMovimientoListNewMovimiento.getMovimientoList().remove(movimientoListNewMovimiento);
                        oldConceptoIdconceptoOfMovimientoListNewMovimiento = em.merge(oldConceptoIdconceptoOfMovimientoListNewMovimiento);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = concepto.getIdconcepto();
                if (findConcepto(id) == null) {
                    throw new NonexistentEntityException("The concepto with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Concepto concepto;
            try {
                concepto = em.getReference(Concepto.class, id);
                concepto.getIdconcepto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The concepto with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Abono> abonoListOrphanCheck = concepto.getAbonoList();
            for (Abono abonoListOrphanCheckAbono : abonoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Concepto (" + concepto + ") cannot be destroyed since the Abono " + abonoListOrphanCheckAbono + " in its abonoList field has a non-nullable conceptoIdconcepto field.");
            }
            List<Movimiento> movimientoListOrphanCheck = concepto.getMovimientoList();
            for (Movimiento movimientoListOrphanCheckMovimiento : movimientoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Concepto (" + concepto + ") cannot be destroyed since the Movimiento " + movimientoListOrphanCheckMovimiento + " in its movimientoList field has a non-nullable conceptoIdconcepto field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(concepto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Concepto> findConceptoEntities() {
        return findConceptoEntities(true, -1, -1);
    }

    public List<Concepto> findConceptoEntities(int maxResults, int firstResult) {
        return findConceptoEntities(false, maxResults, firstResult);
    }

    private List<Concepto> findConceptoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Concepto.class));
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

    public Concepto findConcepto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Concepto.class, id);
        } finally {
            em.close();
        }
    }

    public int getConceptoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Concepto> rt = cq.from(Concepto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
