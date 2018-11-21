/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entities.Articulo;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entities.Prenda;
import entities.Venta;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author erick
 */
public class ArticuloJpaController implements Serializable {

    public ArticuloJpaController() {
        this.emf = Persistence.createEntityManagerFactory("SysEmpenoPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Articulo articulo) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Prenda prendaIdprenda = articulo.getPrendaIdprenda();
            if (prendaIdprenda != null) {
                prendaIdprenda = em.getReference(prendaIdprenda.getClass(), prendaIdprenda.getIdprenda());
                articulo.setPrendaIdprenda(prendaIdprenda);
            }
            Venta ventaIdventa = articulo.getVentaIdventa();
            if (ventaIdventa != null) {
                ventaIdventa = em.getReference(ventaIdventa.getClass(), ventaIdventa.getIdventa());
                articulo.setVentaIdventa(ventaIdventa);
            }
            em.persist(articulo);
            if (prendaIdprenda != null) {
                prendaIdprenda.getArticuloList().add(articulo);
                prendaIdprenda = em.merge(prendaIdprenda);
            }
            if (ventaIdventa != null) {
                ventaIdventa.getArticuloList().add(articulo);
                ventaIdventa = em.merge(ventaIdventa);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Articulo articulo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Articulo persistentArticulo = em.find(Articulo.class, articulo.getIdarticulo());
            Prenda prendaIdprendaOld = persistentArticulo.getPrendaIdprenda();
            Prenda prendaIdprendaNew = articulo.getPrendaIdprenda();
            Venta ventaIdventaOld = persistentArticulo.getVentaIdventa();
            Venta ventaIdventaNew = articulo.getVentaIdventa();
            if (prendaIdprendaNew != null) {
                prendaIdprendaNew = em.getReference(prendaIdprendaNew.getClass(), prendaIdprendaNew.getIdprenda());
                articulo.setPrendaIdprenda(prendaIdprendaNew);
            }
            if (ventaIdventaNew != null) {
                ventaIdventaNew = em.getReference(ventaIdventaNew.getClass(), ventaIdventaNew.getIdventa());
                articulo.setVentaIdventa(ventaIdventaNew);
            }
            articulo = em.merge(articulo);
            if (prendaIdprendaOld != null && !prendaIdprendaOld.equals(prendaIdprendaNew)) {
                prendaIdprendaOld.getArticuloList().remove(articulo);
                prendaIdprendaOld = em.merge(prendaIdprendaOld);
            }
            if (prendaIdprendaNew != null && !prendaIdprendaNew.equals(prendaIdprendaOld)) {
                prendaIdprendaNew.getArticuloList().add(articulo);
                prendaIdprendaNew = em.merge(prendaIdprendaNew);
            }
            if (ventaIdventaOld != null && !ventaIdventaOld.equals(ventaIdventaNew)) {
                ventaIdventaOld.getArticuloList().remove(articulo);
                ventaIdventaOld = em.merge(ventaIdventaOld);
            }
            if (ventaIdventaNew != null && !ventaIdventaNew.equals(ventaIdventaOld)) {
                ventaIdventaNew.getArticuloList().add(articulo);
                ventaIdventaNew = em.merge(ventaIdventaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = articulo.getIdarticulo();
                if (findArticulo(id) == null) {
                    throw new NonexistentEntityException("The articulo with id " + id + " no longer exists.");
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
            Articulo articulo;
            try {
                articulo = em.getReference(Articulo.class, id);
                articulo.getIdarticulo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The articulo with id " + id + " no longer exists.", enfe);
            }
            Prenda prendaIdprenda = articulo.getPrendaIdprenda();
            if (prendaIdprenda != null) {
                prendaIdprenda.getArticuloList().remove(articulo);
                prendaIdprenda = em.merge(prendaIdprenda);
            }
            Venta ventaIdventa = articulo.getVentaIdventa();
            if (ventaIdventa != null) {
                ventaIdventa.getArticuloList().remove(articulo);
                ventaIdventa = em.merge(ventaIdventa);
            }
            em.remove(articulo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Articulo> findArticuloEntities() {
        return findArticuloEntities(true, -1, -1);
    }

    public List<Articulo> findArticuloEntities(int maxResults, int firstResult) {
        return findArticuloEntities(false, maxResults, firstResult);
    }

    private List<Articulo> findArticuloEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Articulo.class));
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

    public Articulo findArticulo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Articulo.class, id);
        } finally {
            em.close();
        }
    }

    public int getArticuloCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Articulo> rt = cq.from(Articulo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
