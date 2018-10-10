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
import Entities.Cliente;
import Entities.Fotocliente;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author erick
 */
public class FotoclienteJpaController implements Serializable {

    public FotoclienteJpaController() {
        this.emf = Persistence.createEntityManagerFactory("SysEmpenoPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Fotocliente fotocliente) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente clienteIdcliente = fotocliente.getClienteIdcliente();
            if (clienteIdcliente != null) {
                clienteIdcliente = em.getReference(clienteIdcliente.getClass(), clienteIdcliente.getIdcliente());
                fotocliente.setClienteIdcliente(clienteIdcliente);
            }
            em.persist(fotocliente);
            if (clienteIdcliente != null) {
                clienteIdcliente.getFotoclienteList().add(fotocliente);
                clienteIdcliente = em.merge(clienteIdcliente);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Fotocliente fotocliente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Fotocliente persistentFotocliente = em.find(Fotocliente.class, fotocliente.getIdfoto());
            Cliente clienteIdclienteOld = persistentFotocliente.getClienteIdcliente();
            Cliente clienteIdclienteNew = fotocliente.getClienteIdcliente();
            if (clienteIdclienteNew != null) {
                clienteIdclienteNew = em.getReference(clienteIdclienteNew.getClass(), clienteIdclienteNew.getIdcliente());
                fotocliente.setClienteIdcliente(clienteIdclienteNew);
            }
            fotocliente = em.merge(fotocliente);
            if (clienteIdclienteOld != null && !clienteIdclienteOld.equals(clienteIdclienteNew)) {
                clienteIdclienteOld.getFotoclienteList().remove(fotocliente);
                clienteIdclienteOld = em.merge(clienteIdclienteOld);
            }
            if (clienteIdclienteNew != null && !clienteIdclienteNew.equals(clienteIdclienteOld)) {
                clienteIdclienteNew.getFotoclienteList().add(fotocliente);
                clienteIdclienteNew = em.merge(clienteIdclienteNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = fotocliente.getIdfoto();
                if (findFotocliente(id) == null) {
                    throw new NonexistentEntityException("The fotocliente with id " + id + " no longer exists.");
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
            Fotocliente fotocliente;
            try {
                fotocliente = em.getReference(Fotocliente.class, id);
                fotocliente.getIdfoto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The fotocliente with id " + id + " no longer exists.", enfe);
            }
            Cliente clienteIdcliente = fotocliente.getClienteIdcliente();
            if (clienteIdcliente != null) {
                clienteIdcliente.getFotoclienteList().remove(fotocliente);
                clienteIdcliente = em.merge(clienteIdcliente);
            }
            em.remove(fotocliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Fotocliente> findFotoclienteEntities() {
        return findFotoclienteEntities(true, -1, -1);
    }

    public List<Fotocliente> findFotoclienteEntities(int maxResults, int firstResult) {
        return findFotoclienteEntities(false, maxResults, firstResult);
    }

    private List<Fotocliente> findFotoclienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Fotocliente.class));
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

    public Fotocliente findFotocliente(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Fotocliente.class, id);
        } finally {
            em.close();
        }
    }

    public int getFotoclienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Fotocliente> rt = cq.from(Fotocliente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
