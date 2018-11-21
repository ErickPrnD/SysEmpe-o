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
import entities.Categoria;
import entities.Contrato;
import entities.Usuario;
import entities.Articulo;
import entities.Prenda;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author erick
 */
public class PrendaJpaController implements Serializable {

    public PrendaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Prenda prenda) {
        if (prenda.getArticuloList() == null) {
            prenda.setArticuloList(new ArrayList<Articulo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Categoria categoriaIdcategoria = prenda.getCategoriaIdcategoria();
            if (categoriaIdcategoria != null) {
                categoriaIdcategoria = em.getReference(categoriaIdcategoria.getClass(), categoriaIdcategoria.getIdcategoria());
                prenda.setCategoriaIdcategoria(categoriaIdcategoria);
            }
            Contrato contratoidContrato = prenda.getContratoidContrato();
            if (contratoidContrato != null) {
                contratoidContrato = em.getReference(contratoidContrato.getClass(), contratoidContrato.getIdcontrato());
                prenda.setContratoidContrato(contratoidContrato);
            }
            Usuario revisa = prenda.getRevisa();
            if (revisa != null) {
                revisa = em.getReference(revisa.getClass(), revisa.getIdusuario());
                prenda.setRevisa(revisa);
            }
            Usuario autoriza = prenda.getAutoriza();
            if (autoriza != null) {
                autoriza = em.getReference(autoriza.getClass(), autoriza.getIdusuario());
                prenda.setAutoriza(autoriza);
            }
            List<Articulo> attachedArticuloList = new ArrayList<Articulo>();
            for (Articulo articuloListArticuloToAttach : prenda.getArticuloList()) {
                articuloListArticuloToAttach = em.getReference(articuloListArticuloToAttach.getClass(), articuloListArticuloToAttach.getIdarticulo());
                attachedArticuloList.add(articuloListArticuloToAttach);
            }
            prenda.setArticuloList(attachedArticuloList);
            em.persist(prenda);
            if (categoriaIdcategoria != null) {
                categoriaIdcategoria.getPrendaList().add(prenda);
                categoriaIdcategoria = em.merge(categoriaIdcategoria);
            }
            if (contratoidContrato != null) {
                contratoidContrato.getPrendaList().add(prenda);
                contratoidContrato = em.merge(contratoidContrato);
            }
            if (revisa != null) {
                revisa.getPrendaList().add(prenda);
                revisa = em.merge(revisa);
            }
            if (autoriza != null) {
                autoriza.getPrendaList().add(prenda);
                autoriza = em.merge(autoriza);
            }
            for (Articulo articuloListArticulo : prenda.getArticuloList()) {
                Prenda oldPrendaIdprendaOfArticuloListArticulo = articuloListArticulo.getPrendaIdprenda();
                articuloListArticulo.setPrendaIdprenda(prenda);
                articuloListArticulo = em.merge(articuloListArticulo);
                if (oldPrendaIdprendaOfArticuloListArticulo != null) {
                    oldPrendaIdprendaOfArticuloListArticulo.getArticuloList().remove(articuloListArticulo);
                    oldPrendaIdprendaOfArticuloListArticulo = em.merge(oldPrendaIdprendaOfArticuloListArticulo);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Prenda prenda) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Prenda persistentPrenda = em.find(Prenda.class, prenda.getIdprenda());
            Categoria categoriaIdcategoriaOld = persistentPrenda.getCategoriaIdcategoria();
            Categoria categoriaIdcategoriaNew = prenda.getCategoriaIdcategoria();
            Contrato contratoidContratoOld = persistentPrenda.getContratoidContrato();
            Contrato contratoidContratoNew = prenda.getContratoidContrato();
            Usuario revisaOld = persistentPrenda.getRevisa();
            Usuario revisaNew = prenda.getRevisa();
            Usuario autorizaOld = persistentPrenda.getAutoriza();
            Usuario autorizaNew = prenda.getAutoriza();
            List<Articulo> articuloListOld = persistentPrenda.getArticuloList();
            List<Articulo> articuloListNew = prenda.getArticuloList();
            List<String> illegalOrphanMessages = null;
            for (Articulo articuloListOldArticulo : articuloListOld) {
                if (!articuloListNew.contains(articuloListOldArticulo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Articulo " + articuloListOldArticulo + " since its prendaIdprenda field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (categoriaIdcategoriaNew != null) {
                categoriaIdcategoriaNew = em.getReference(categoriaIdcategoriaNew.getClass(), categoriaIdcategoriaNew.getIdcategoria());
                prenda.setCategoriaIdcategoria(categoriaIdcategoriaNew);
            }
            if (contratoidContratoNew != null) {
                contratoidContratoNew = em.getReference(contratoidContratoNew.getClass(), contratoidContratoNew.getIdcontrato());
                prenda.setContratoidContrato(contratoidContratoNew);
            }
            if (revisaNew != null) {
                revisaNew = em.getReference(revisaNew.getClass(), revisaNew.getIdusuario());
                prenda.setRevisa(revisaNew);
            }
            if (autorizaNew != null) {
                autorizaNew = em.getReference(autorizaNew.getClass(), autorizaNew.getIdusuario());
                prenda.setAutoriza(autorizaNew);
            }
            List<Articulo> attachedArticuloListNew = new ArrayList<Articulo>();
            for (Articulo articuloListNewArticuloToAttach : articuloListNew) {
                articuloListNewArticuloToAttach = em.getReference(articuloListNewArticuloToAttach.getClass(), articuloListNewArticuloToAttach.getIdarticulo());
                attachedArticuloListNew.add(articuloListNewArticuloToAttach);
            }
            articuloListNew = attachedArticuloListNew;
            prenda.setArticuloList(articuloListNew);
            prenda = em.merge(prenda);
            if (categoriaIdcategoriaOld != null && !categoriaIdcategoriaOld.equals(categoriaIdcategoriaNew)) {
                categoriaIdcategoriaOld.getPrendaList().remove(prenda);
                categoriaIdcategoriaOld = em.merge(categoriaIdcategoriaOld);
            }
            if (categoriaIdcategoriaNew != null && !categoriaIdcategoriaNew.equals(categoriaIdcategoriaOld)) {
                categoriaIdcategoriaNew.getPrendaList().add(prenda);
                categoriaIdcategoriaNew = em.merge(categoriaIdcategoriaNew);
            }
            if (contratoidContratoOld != null && !contratoidContratoOld.equals(contratoidContratoNew)) {
                contratoidContratoOld.getPrendaList().remove(prenda);
                contratoidContratoOld = em.merge(contratoidContratoOld);
            }
            if (contratoidContratoNew != null && !contratoidContratoNew.equals(contratoidContratoOld)) {
                contratoidContratoNew.getPrendaList().add(prenda);
                contratoidContratoNew = em.merge(contratoidContratoNew);
            }
            if (revisaOld != null && !revisaOld.equals(revisaNew)) {
                revisaOld.getPrendaList().remove(prenda);
                revisaOld = em.merge(revisaOld);
            }
            if (revisaNew != null && !revisaNew.equals(revisaOld)) {
                revisaNew.getPrendaList().add(prenda);
                revisaNew = em.merge(revisaNew);
            }
            if (autorizaOld != null && !autorizaOld.equals(autorizaNew)) {
                autorizaOld.getPrendaList().remove(prenda);
                autorizaOld = em.merge(autorizaOld);
            }
            if (autorizaNew != null && !autorizaNew.equals(autorizaOld)) {
                autorizaNew.getPrendaList().add(prenda);
                autorizaNew = em.merge(autorizaNew);
            }
            for (Articulo articuloListNewArticulo : articuloListNew) {
                if (!articuloListOld.contains(articuloListNewArticulo)) {
                    Prenda oldPrendaIdprendaOfArticuloListNewArticulo = articuloListNewArticulo.getPrendaIdprenda();
                    articuloListNewArticulo.setPrendaIdprenda(prenda);
                    articuloListNewArticulo = em.merge(articuloListNewArticulo);
                    if (oldPrendaIdprendaOfArticuloListNewArticulo != null && !oldPrendaIdprendaOfArticuloListNewArticulo.equals(prenda)) {
                        oldPrendaIdprendaOfArticuloListNewArticulo.getArticuloList().remove(articuloListNewArticulo);
                        oldPrendaIdprendaOfArticuloListNewArticulo = em.merge(oldPrendaIdprendaOfArticuloListNewArticulo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = prenda.getIdprenda();
                if (findPrenda(id) == null) {
                    throw new NonexistentEntityException("The prenda with id " + id + " no longer exists.");
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
            Prenda prenda;
            try {
                prenda = em.getReference(Prenda.class, id);
                prenda.getIdprenda();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The prenda with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Articulo> articuloListOrphanCheck = prenda.getArticuloList();
            for (Articulo articuloListOrphanCheckArticulo : articuloListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Prenda (" + prenda + ") cannot be destroyed since the Articulo " + articuloListOrphanCheckArticulo + " in its articuloList field has a non-nullable prendaIdprenda field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Categoria categoriaIdcategoria = prenda.getCategoriaIdcategoria();
            if (categoriaIdcategoria != null) {
                categoriaIdcategoria.getPrendaList().remove(prenda);
                categoriaIdcategoria = em.merge(categoriaIdcategoria);
            }
            Contrato contratoidContrato = prenda.getContratoidContrato();
            if (contratoidContrato != null) {
                contratoidContrato.getPrendaList().remove(prenda);
                contratoidContrato = em.merge(contratoidContrato);
            }
            Usuario revisa = prenda.getRevisa();
            if (revisa != null) {
                revisa.getPrendaList().remove(prenda);
                revisa = em.merge(revisa);
            }
            Usuario autoriza = prenda.getAutoriza();
            if (autoriza != null) {
                autoriza.getPrendaList().remove(prenda);
                autoriza = em.merge(autoriza);
            }
            em.remove(prenda);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Prenda> findPrendaEntities() {
        return findPrendaEntities(true, -1, -1);
    }

    public List<Prenda> findPrendaEntities(int maxResults, int firstResult) {
        return findPrendaEntities(false, maxResults, firstResult);
    }

    private List<Prenda> findPrendaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Prenda.class));
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

    public Prenda findPrenda(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Prenda.class, id);
        } finally {
            em.close();
        }
    }

    public int getPrendaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Prenda> rt = cq.from(Prenda.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
