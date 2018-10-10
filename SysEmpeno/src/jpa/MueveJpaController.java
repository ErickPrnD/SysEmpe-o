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
import Entities.Usuario;
import Entities.Movimiento;
import Entities.Mueve;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author erick
 */
public class MueveJpaController implements Serializable {

    public MueveJpaController() {
        this.emf = Persistence.createEntityManagerFactory("SysEmpenoPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Mueve mueve) {
        if (mueve.getMovimientoList() == null) {
            mueve.setMovimientoList(new ArrayList<Movimiento>());
        }
        if (mueve.getMovimientoList1() == null) {
            mueve.setMovimientoList1(new ArrayList<Movimiento>());
        }
        if (mueve.getMovimientoList2() == null) {
            mueve.setMovimientoList2(new ArrayList<Movimiento>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuarioIdusuario = mueve.getUsuarioIdusuario();
            if (usuarioIdusuario != null) {
                usuarioIdusuario = em.getReference(usuarioIdusuario.getClass(), usuarioIdusuario.getIdusuario());
                mueve.setUsuarioIdusuario(usuarioIdusuario);
            }
            List<Movimiento> attachedMovimientoList = new ArrayList<Movimiento>();
            for (Movimiento movimientoListMovimientoToAttach : mueve.getMovimientoList()) {
                movimientoListMovimientoToAttach = em.getReference(movimientoListMovimientoToAttach.getClass(), movimientoListMovimientoToAttach.getIdmovimiento());
                attachedMovimientoList.add(movimientoListMovimientoToAttach);
            }
            mueve.setMovimientoList(attachedMovimientoList);
            List<Movimiento> attachedMovimientoList1 = new ArrayList<Movimiento>();
            for (Movimiento movimientoList1MovimientoToAttach : mueve.getMovimientoList1()) {
                movimientoList1MovimientoToAttach = em.getReference(movimientoList1MovimientoToAttach.getClass(), movimientoList1MovimientoToAttach.getIdmovimiento());
                attachedMovimientoList1.add(movimientoList1MovimientoToAttach);
            }
            mueve.setMovimientoList1(attachedMovimientoList1);
            List<Movimiento> attachedMovimientoList2 = new ArrayList<Movimiento>();
            for (Movimiento movimientoList2MovimientoToAttach : mueve.getMovimientoList2()) {
                movimientoList2MovimientoToAttach = em.getReference(movimientoList2MovimientoToAttach.getClass(), movimientoList2MovimientoToAttach.getIdmovimiento());
                attachedMovimientoList2.add(movimientoList2MovimientoToAttach);
            }
            mueve.setMovimientoList2(attachedMovimientoList2);
            em.persist(mueve);
            if (usuarioIdusuario != null) {
                usuarioIdusuario.getMueveList().add(mueve);
                usuarioIdusuario = em.merge(usuarioIdusuario);
            }
            for (Movimiento movimientoListMovimiento : mueve.getMovimientoList()) {
                Mueve oldCapturaOfMovimientoListMovimiento = movimientoListMovimiento.getCaptura();
                movimientoListMovimiento.setCaptura(mueve);
                movimientoListMovimiento = em.merge(movimientoListMovimiento);
                if (oldCapturaOfMovimientoListMovimiento != null) {
                    oldCapturaOfMovimientoListMovimiento.getMovimientoList().remove(movimientoListMovimiento);
                    oldCapturaOfMovimientoListMovimiento = em.merge(oldCapturaOfMovimientoListMovimiento);
                }
            }
            for (Movimiento movimientoList1Movimiento : mueve.getMovimientoList1()) {
                Mueve oldModificacionOfMovimientoList1Movimiento = movimientoList1Movimiento.getModificacion();
                movimientoList1Movimiento.setModificacion(mueve);
                movimientoList1Movimiento = em.merge(movimientoList1Movimiento);
                if (oldModificacionOfMovimientoList1Movimiento != null) {
                    oldModificacionOfMovimientoList1Movimiento.getMovimientoList1().remove(movimientoList1Movimiento);
                    oldModificacionOfMovimientoList1Movimiento = em.merge(oldModificacionOfMovimientoList1Movimiento);
                }
            }
            for (Movimiento movimientoList2Movimiento : mueve.getMovimientoList2()) {
                Mueve oldCancelacionOfMovimientoList2Movimiento = movimientoList2Movimiento.getCancelacion();
                movimientoList2Movimiento.setCancelacion(mueve);
                movimientoList2Movimiento = em.merge(movimientoList2Movimiento);
                if (oldCancelacionOfMovimientoList2Movimiento != null) {
                    oldCancelacionOfMovimientoList2Movimiento.getMovimientoList2().remove(movimientoList2Movimiento);
                    oldCancelacionOfMovimientoList2Movimiento = em.merge(oldCancelacionOfMovimientoList2Movimiento);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Mueve mueve) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Mueve persistentMueve = em.find(Mueve.class, mueve.getIdmueve());
            Usuario usuarioIdusuarioOld = persistentMueve.getUsuarioIdusuario();
            Usuario usuarioIdusuarioNew = mueve.getUsuarioIdusuario();
            List<Movimiento> movimientoListOld = persistentMueve.getMovimientoList();
            List<Movimiento> movimientoListNew = mueve.getMovimientoList();
            List<Movimiento> movimientoList1Old = persistentMueve.getMovimientoList1();
            List<Movimiento> movimientoList1New = mueve.getMovimientoList1();
            List<Movimiento> movimientoList2Old = persistentMueve.getMovimientoList2();
            List<Movimiento> movimientoList2New = mueve.getMovimientoList2();
            List<String> illegalOrphanMessages = null;
            for (Movimiento movimientoListOldMovimiento : movimientoListOld) {
                if (!movimientoListNew.contains(movimientoListOldMovimiento)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Movimiento " + movimientoListOldMovimiento + " since its captura field is not nullable.");
                }
            }
            for (Movimiento movimientoList1OldMovimiento : movimientoList1Old) {
                if (!movimientoList1New.contains(movimientoList1OldMovimiento)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Movimiento " + movimientoList1OldMovimiento + " since its modificacion field is not nullable.");
                }
            }
            for (Movimiento movimientoList2OldMovimiento : movimientoList2Old) {
                if (!movimientoList2New.contains(movimientoList2OldMovimiento)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Movimiento " + movimientoList2OldMovimiento + " since its cancelacion field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (usuarioIdusuarioNew != null) {
                usuarioIdusuarioNew = em.getReference(usuarioIdusuarioNew.getClass(), usuarioIdusuarioNew.getIdusuario());
                mueve.setUsuarioIdusuario(usuarioIdusuarioNew);
            }
            List<Movimiento> attachedMovimientoListNew = new ArrayList<Movimiento>();
            for (Movimiento movimientoListNewMovimientoToAttach : movimientoListNew) {
                movimientoListNewMovimientoToAttach = em.getReference(movimientoListNewMovimientoToAttach.getClass(), movimientoListNewMovimientoToAttach.getIdmovimiento());
                attachedMovimientoListNew.add(movimientoListNewMovimientoToAttach);
            }
            movimientoListNew = attachedMovimientoListNew;
            mueve.setMovimientoList(movimientoListNew);
            List<Movimiento> attachedMovimientoList1New = new ArrayList<Movimiento>();
            for (Movimiento movimientoList1NewMovimientoToAttach : movimientoList1New) {
                movimientoList1NewMovimientoToAttach = em.getReference(movimientoList1NewMovimientoToAttach.getClass(), movimientoList1NewMovimientoToAttach.getIdmovimiento());
                attachedMovimientoList1New.add(movimientoList1NewMovimientoToAttach);
            }
            movimientoList1New = attachedMovimientoList1New;
            mueve.setMovimientoList1(movimientoList1New);
            List<Movimiento> attachedMovimientoList2New = new ArrayList<Movimiento>();
            for (Movimiento movimientoList2NewMovimientoToAttach : movimientoList2New) {
                movimientoList2NewMovimientoToAttach = em.getReference(movimientoList2NewMovimientoToAttach.getClass(), movimientoList2NewMovimientoToAttach.getIdmovimiento());
                attachedMovimientoList2New.add(movimientoList2NewMovimientoToAttach);
            }
            movimientoList2New = attachedMovimientoList2New;
            mueve.setMovimientoList2(movimientoList2New);
            mueve = em.merge(mueve);
            if (usuarioIdusuarioOld != null && !usuarioIdusuarioOld.equals(usuarioIdusuarioNew)) {
                usuarioIdusuarioOld.getMueveList().remove(mueve);
                usuarioIdusuarioOld = em.merge(usuarioIdusuarioOld);
            }
            if (usuarioIdusuarioNew != null && !usuarioIdusuarioNew.equals(usuarioIdusuarioOld)) {
                usuarioIdusuarioNew.getMueveList().add(mueve);
                usuarioIdusuarioNew = em.merge(usuarioIdusuarioNew);
            }
            for (Movimiento movimientoListNewMovimiento : movimientoListNew) {
                if (!movimientoListOld.contains(movimientoListNewMovimiento)) {
                    Mueve oldCapturaOfMovimientoListNewMovimiento = movimientoListNewMovimiento.getCaptura();
                    movimientoListNewMovimiento.setCaptura(mueve);
                    movimientoListNewMovimiento = em.merge(movimientoListNewMovimiento);
                    if (oldCapturaOfMovimientoListNewMovimiento != null && !oldCapturaOfMovimientoListNewMovimiento.equals(mueve)) {
                        oldCapturaOfMovimientoListNewMovimiento.getMovimientoList().remove(movimientoListNewMovimiento);
                        oldCapturaOfMovimientoListNewMovimiento = em.merge(oldCapturaOfMovimientoListNewMovimiento);
                    }
                }
            }
            for (Movimiento movimientoList1NewMovimiento : movimientoList1New) {
                if (!movimientoList1Old.contains(movimientoList1NewMovimiento)) {
                    Mueve oldModificacionOfMovimientoList1NewMovimiento = movimientoList1NewMovimiento.getModificacion();
                    movimientoList1NewMovimiento.setModificacion(mueve);
                    movimientoList1NewMovimiento = em.merge(movimientoList1NewMovimiento);
                    if (oldModificacionOfMovimientoList1NewMovimiento != null && !oldModificacionOfMovimientoList1NewMovimiento.equals(mueve)) {
                        oldModificacionOfMovimientoList1NewMovimiento.getMovimientoList1().remove(movimientoList1NewMovimiento);
                        oldModificacionOfMovimientoList1NewMovimiento = em.merge(oldModificacionOfMovimientoList1NewMovimiento);
                    }
                }
            }
            for (Movimiento movimientoList2NewMovimiento : movimientoList2New) {
                if (!movimientoList2Old.contains(movimientoList2NewMovimiento)) {
                    Mueve oldCancelacionOfMovimientoList2NewMovimiento = movimientoList2NewMovimiento.getCancelacion();
                    movimientoList2NewMovimiento.setCancelacion(mueve);
                    movimientoList2NewMovimiento = em.merge(movimientoList2NewMovimiento);
                    if (oldCancelacionOfMovimientoList2NewMovimiento != null && !oldCancelacionOfMovimientoList2NewMovimiento.equals(mueve)) {
                        oldCancelacionOfMovimientoList2NewMovimiento.getMovimientoList2().remove(movimientoList2NewMovimiento);
                        oldCancelacionOfMovimientoList2NewMovimiento = em.merge(oldCancelacionOfMovimientoList2NewMovimiento);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = mueve.getIdmueve();
                if (findMueve(id) == null) {
                    throw new NonexistentEntityException("The mueve with id " + id + " no longer exists.");
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
            Mueve mueve;
            try {
                mueve = em.getReference(Mueve.class, id);
                mueve.getIdmueve();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The mueve with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Movimiento> movimientoListOrphanCheck = mueve.getMovimientoList();
            for (Movimiento movimientoListOrphanCheckMovimiento : movimientoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Mueve (" + mueve + ") cannot be destroyed since the Movimiento " + movimientoListOrphanCheckMovimiento + " in its movimientoList field has a non-nullable captura field.");
            }
            List<Movimiento> movimientoList1OrphanCheck = mueve.getMovimientoList1();
            for (Movimiento movimientoList1OrphanCheckMovimiento : movimientoList1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Mueve (" + mueve + ") cannot be destroyed since the Movimiento " + movimientoList1OrphanCheckMovimiento + " in its movimientoList1 field has a non-nullable modificacion field.");
            }
            List<Movimiento> movimientoList2OrphanCheck = mueve.getMovimientoList2();
            for (Movimiento movimientoList2OrphanCheckMovimiento : movimientoList2OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Mueve (" + mueve + ") cannot be destroyed since the Movimiento " + movimientoList2OrphanCheckMovimiento + " in its movimientoList2 field has a non-nullable cancelacion field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Usuario usuarioIdusuario = mueve.getUsuarioIdusuario();
            if (usuarioIdusuario != null) {
                usuarioIdusuario.getMueveList().remove(mueve);
                usuarioIdusuario = em.merge(usuarioIdusuario);
            }
            em.remove(mueve);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Mueve> findMueveEntities() {
        return findMueveEntities(true, -1, -1);
    }

    public List<Mueve> findMueveEntities(int maxResults, int firstResult) {
        return findMueveEntities(false, maxResults, firstResult);
    }

    private List<Mueve> findMueveEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Mueve.class));
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

    public Mueve findMueve(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Mueve.class, id);
        } finally {
            em.close();
        }
    }

    public int getMueveCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Mueve> rt = cq.from(Mueve.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
