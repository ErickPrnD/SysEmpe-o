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
import Entities.Categoria;
import Entities.Subcategoria;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author erick
 */
public class SubcategoriaJpaController implements Serializable {

    public SubcategoriaJpaController() {
        this.emf = Persistence.createEntityManagerFactory("SysEmpenoPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Subcategoria subcategoria) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Categoria categoriaIdcategoria = subcategoria.getCategoriaIdcategoria();
            if (categoriaIdcategoria != null) {
                categoriaIdcategoria = em.getReference(categoriaIdcategoria.getClass(), categoriaIdcategoria.getIdcategoria());
                subcategoria.setCategoriaIdcategoria(categoriaIdcategoria);
            }
            em.persist(subcategoria);
            if (categoriaIdcategoria != null) {
                categoriaIdcategoria.getSubcategoriaList().add(subcategoria);
                categoriaIdcategoria = em.merge(categoriaIdcategoria);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Subcategoria subcategoria) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Subcategoria persistentSubcategoria = em.find(Subcategoria.class, subcategoria.getIdsubcategoria());
            Categoria categoriaIdcategoriaOld = persistentSubcategoria.getCategoriaIdcategoria();
            Categoria categoriaIdcategoriaNew = subcategoria.getCategoriaIdcategoria();
            if (categoriaIdcategoriaNew != null) {
                categoriaIdcategoriaNew = em.getReference(categoriaIdcategoriaNew.getClass(), categoriaIdcategoriaNew.getIdcategoria());
                subcategoria.setCategoriaIdcategoria(categoriaIdcategoriaNew);
            }
            subcategoria = em.merge(subcategoria);
            if (categoriaIdcategoriaOld != null && !categoriaIdcategoriaOld.equals(categoriaIdcategoriaNew)) {
                categoriaIdcategoriaOld.getSubcategoriaList().remove(subcategoria);
                categoriaIdcategoriaOld = em.merge(categoriaIdcategoriaOld);
            }
            if (categoriaIdcategoriaNew != null && !categoriaIdcategoriaNew.equals(categoriaIdcategoriaOld)) {
                categoriaIdcategoriaNew.getSubcategoriaList().add(subcategoria);
                categoriaIdcategoriaNew = em.merge(categoriaIdcategoriaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = subcategoria.getIdsubcategoria();
                if (findSubcategoria(id) == null) {
                    throw new NonexistentEntityException("The subcategoria with id " + id + " no longer exists.");
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
            Subcategoria subcategoria;
            try {
                subcategoria = em.getReference(Subcategoria.class, id);
                subcategoria.getIdsubcategoria();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The subcategoria with id " + id + " no longer exists.", enfe);
            }
            Categoria categoriaIdcategoria = subcategoria.getCategoriaIdcategoria();
            if (categoriaIdcategoria != null) {
                categoriaIdcategoria.getSubcategoriaList().remove(subcategoria);
                categoriaIdcategoria = em.merge(categoriaIdcategoria);
            }
            em.remove(subcategoria);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Subcategoria> findSubcategoriaEntities() {
        return findSubcategoriaEntities(true, -1, -1);
    }

    public List<Subcategoria> findSubcategoriaEntities(int maxResults, int firstResult) {
        return findSubcategoriaEntities(false, maxResults, firstResult);
    }

    private List<Subcategoria> findSubcategoriaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Subcategoria.class));
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

    public Subcategoria findSubcategoria(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Subcategoria.class, id);
        } finally {
            em.close();
        }
    }

    public int getSubcategoriaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Subcategoria> rt = cq.from(Subcategoria.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
