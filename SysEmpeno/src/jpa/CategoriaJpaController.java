/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import Entities.Categoria;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entities.Subcategoria;
import java.util.ArrayList;
import java.util.List;
import Entities.Prenda;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author erick
 */
public class CategoriaJpaController implements Serializable {

    public CategoriaJpaController() {
        this.emf = Persistence.createEntityManagerFactory("SysEmpenoPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Categoria categoria) {
        if (categoria.getSubcategoriaList() == null) {
            categoria.setSubcategoriaList(new ArrayList<Subcategoria>());
        }
        if (categoria.getPrendaList() == null) {
            categoria.setPrendaList(new ArrayList<Prenda>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Subcategoria> attachedSubcategoriaList = new ArrayList<Subcategoria>();
            for (Subcategoria subcategoriaListSubcategoriaToAttach : categoria.getSubcategoriaList()) {
                subcategoriaListSubcategoriaToAttach = em.getReference(subcategoriaListSubcategoriaToAttach.getClass(), subcategoriaListSubcategoriaToAttach.getIdsubcategoria());
                attachedSubcategoriaList.add(subcategoriaListSubcategoriaToAttach);
            }
            categoria.setSubcategoriaList(attachedSubcategoriaList);
            List<Prenda> attachedPrendaList = new ArrayList<Prenda>();
            for (Prenda prendaListPrendaToAttach : categoria.getPrendaList()) {
                prendaListPrendaToAttach = em.getReference(prendaListPrendaToAttach.getClass(), prendaListPrendaToAttach.getIdprenda());
                attachedPrendaList.add(prendaListPrendaToAttach);
            }
            categoria.setPrendaList(attachedPrendaList);
            em.persist(categoria);
            for (Subcategoria subcategoriaListSubcategoria : categoria.getSubcategoriaList()) {
                Categoria oldCategoriaIdcategoriaOfSubcategoriaListSubcategoria = subcategoriaListSubcategoria.getCategoriaIdcategoria();
                subcategoriaListSubcategoria.setCategoriaIdcategoria(categoria);
                subcategoriaListSubcategoria = em.merge(subcategoriaListSubcategoria);
                if (oldCategoriaIdcategoriaOfSubcategoriaListSubcategoria != null) {
                    oldCategoriaIdcategoriaOfSubcategoriaListSubcategoria.getSubcategoriaList().remove(subcategoriaListSubcategoria);
                    oldCategoriaIdcategoriaOfSubcategoriaListSubcategoria = em.merge(oldCategoriaIdcategoriaOfSubcategoriaListSubcategoria);
                }
            }
            for (Prenda prendaListPrenda : categoria.getPrendaList()) {
                Categoria oldCategoriaIdcategoriaOfPrendaListPrenda = prendaListPrenda.getCategoriaIdcategoria();
                prendaListPrenda.setCategoriaIdcategoria(categoria);
                prendaListPrenda = em.merge(prendaListPrenda);
                if (oldCategoriaIdcategoriaOfPrendaListPrenda != null) {
                    oldCategoriaIdcategoriaOfPrendaListPrenda.getPrendaList().remove(prendaListPrenda);
                    oldCategoriaIdcategoriaOfPrendaListPrenda = em.merge(oldCategoriaIdcategoriaOfPrendaListPrenda);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Categoria categoria) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Categoria persistentCategoria = em.find(Categoria.class, categoria.getIdcategoria());
            List<Subcategoria> subcategoriaListOld = persistentCategoria.getSubcategoriaList();
            List<Subcategoria> subcategoriaListNew = categoria.getSubcategoriaList();
            List<Prenda> prendaListOld = persistentCategoria.getPrendaList();
            List<Prenda> prendaListNew = categoria.getPrendaList();
            List<String> illegalOrphanMessages = null;
            for (Subcategoria subcategoriaListOldSubcategoria : subcategoriaListOld) {
                if (!subcategoriaListNew.contains(subcategoriaListOldSubcategoria)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Subcategoria " + subcategoriaListOldSubcategoria + " since its categoriaIdcategoria field is not nullable.");
                }
            }
            for (Prenda prendaListOldPrenda : prendaListOld) {
                if (!prendaListNew.contains(prendaListOldPrenda)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Prenda " + prendaListOldPrenda + " since its categoriaIdcategoria field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Subcategoria> attachedSubcategoriaListNew = new ArrayList<Subcategoria>();
            for (Subcategoria subcategoriaListNewSubcategoriaToAttach : subcategoriaListNew) {
                subcategoriaListNewSubcategoriaToAttach = em.getReference(subcategoriaListNewSubcategoriaToAttach.getClass(), subcategoriaListNewSubcategoriaToAttach.getIdsubcategoria());
                attachedSubcategoriaListNew.add(subcategoriaListNewSubcategoriaToAttach);
            }
            subcategoriaListNew = attachedSubcategoriaListNew;
            categoria.setSubcategoriaList(subcategoriaListNew);
            List<Prenda> attachedPrendaListNew = new ArrayList<Prenda>();
            for (Prenda prendaListNewPrendaToAttach : prendaListNew) {
                prendaListNewPrendaToAttach = em.getReference(prendaListNewPrendaToAttach.getClass(), prendaListNewPrendaToAttach.getIdprenda());
                attachedPrendaListNew.add(prendaListNewPrendaToAttach);
            }
            prendaListNew = attachedPrendaListNew;
            categoria.setPrendaList(prendaListNew);
            categoria = em.merge(categoria);
            for (Subcategoria subcategoriaListNewSubcategoria : subcategoriaListNew) {
                if (!subcategoriaListOld.contains(subcategoriaListNewSubcategoria)) {
                    Categoria oldCategoriaIdcategoriaOfSubcategoriaListNewSubcategoria = subcategoriaListNewSubcategoria.getCategoriaIdcategoria();
                    subcategoriaListNewSubcategoria.setCategoriaIdcategoria(categoria);
                    subcategoriaListNewSubcategoria = em.merge(subcategoriaListNewSubcategoria);
                    if (oldCategoriaIdcategoriaOfSubcategoriaListNewSubcategoria != null && !oldCategoriaIdcategoriaOfSubcategoriaListNewSubcategoria.equals(categoria)) {
                        oldCategoriaIdcategoriaOfSubcategoriaListNewSubcategoria.getSubcategoriaList().remove(subcategoriaListNewSubcategoria);
                        oldCategoriaIdcategoriaOfSubcategoriaListNewSubcategoria = em.merge(oldCategoriaIdcategoriaOfSubcategoriaListNewSubcategoria);
                    }
                }
            }
            for (Prenda prendaListNewPrenda : prendaListNew) {
                if (!prendaListOld.contains(prendaListNewPrenda)) {
                    Categoria oldCategoriaIdcategoriaOfPrendaListNewPrenda = prendaListNewPrenda.getCategoriaIdcategoria();
                    prendaListNewPrenda.setCategoriaIdcategoria(categoria);
                    prendaListNewPrenda = em.merge(prendaListNewPrenda);
                    if (oldCategoriaIdcategoriaOfPrendaListNewPrenda != null && !oldCategoriaIdcategoriaOfPrendaListNewPrenda.equals(categoria)) {
                        oldCategoriaIdcategoriaOfPrendaListNewPrenda.getPrendaList().remove(prendaListNewPrenda);
                        oldCategoriaIdcategoriaOfPrendaListNewPrenda = em.merge(oldCategoriaIdcategoriaOfPrendaListNewPrenda);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = categoria.getIdcategoria();
                if (findCategoria(id) == null) {
                    throw new NonexistentEntityException("The categoria with id " + id + " no longer exists.");
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
            Categoria categoria;
            try {
                categoria = em.getReference(Categoria.class, id);
                categoria.getIdcategoria();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The categoria with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Subcategoria> subcategoriaListOrphanCheck = categoria.getSubcategoriaList();
            for (Subcategoria subcategoriaListOrphanCheckSubcategoria : subcategoriaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Categoria (" + categoria + ") cannot be destroyed since the Subcategoria " + subcategoriaListOrphanCheckSubcategoria + " in its subcategoriaList field has a non-nullable categoriaIdcategoria field.");
            }
            List<Prenda> prendaListOrphanCheck = categoria.getPrendaList();
            for (Prenda prendaListOrphanCheckPrenda : prendaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Categoria (" + categoria + ") cannot be destroyed since the Prenda " + prendaListOrphanCheckPrenda + " in its prendaList field has a non-nullable categoriaIdcategoria field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(categoria);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Categoria> findCategoriaEntities() {
        return findCategoriaEntities(true, -1, -1);
    }

    public List<Categoria> findCategoriaEntities(int maxResults, int firstResult) {
        return findCategoriaEntities(false, maxResults, firstResult);
    }

    private List<Categoria> findCategoriaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Categoria.class));
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

    public Categoria findCategoria(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Categoria.class, id);
        } finally {
            em.close();
        }
    }

    public int getCategoriaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Categoria> rt = cq.from(Categoria.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
