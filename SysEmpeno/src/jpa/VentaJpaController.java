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
import Entities.Articulo;
import Entities.Venta;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author erick
 */
public class VentaJpaController implements Serializable {

    public VentaJpaController() {
        this.emf = Persistence.createEntityManagerFactory("SysEmpenoPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Venta venta) {
        if (venta.getArticuloList() == null) {
            venta.setArticuloList(new ArrayList<Articulo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente clienteIdcliente = venta.getClienteIdcliente();
            if (clienteIdcliente != null) {
                clienteIdcliente = em.getReference(clienteIdcliente.getClass(), clienteIdcliente.getIdcliente());
                venta.setClienteIdcliente(clienteIdcliente);
            }
            List<Articulo> attachedArticuloList = new ArrayList<Articulo>();
            for (Articulo articuloListArticuloToAttach : venta.getArticuloList()) {
                articuloListArticuloToAttach = em.getReference(articuloListArticuloToAttach.getClass(), articuloListArticuloToAttach.getIdarticulo());
                attachedArticuloList.add(articuloListArticuloToAttach);
            }
            venta.setArticuloList(attachedArticuloList);
            em.persist(venta);
            if (clienteIdcliente != null) {
                clienteIdcliente.getVentaList().add(venta);
                clienteIdcliente = em.merge(clienteIdcliente);
            }
            for (Articulo articuloListArticulo : venta.getArticuloList()) {
                Venta oldVentaIdventaOfArticuloListArticulo = articuloListArticulo.getVentaIdventa();
                articuloListArticulo.setVentaIdventa(venta);
                articuloListArticulo = em.merge(articuloListArticulo);
                if (oldVentaIdventaOfArticuloListArticulo != null) {
                    oldVentaIdventaOfArticuloListArticulo.getArticuloList().remove(articuloListArticulo);
                    oldVentaIdventaOfArticuloListArticulo = em.merge(oldVentaIdventaOfArticuloListArticulo);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Venta venta) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Venta persistentVenta = em.find(Venta.class, venta.getIdventa());
            Cliente clienteIdclienteOld = persistentVenta.getClienteIdcliente();
            Cliente clienteIdclienteNew = venta.getClienteIdcliente();
            List<Articulo> articuloListOld = persistentVenta.getArticuloList();
            List<Articulo> articuloListNew = venta.getArticuloList();
            if (clienteIdclienteNew != null) {
                clienteIdclienteNew = em.getReference(clienteIdclienteNew.getClass(), clienteIdclienteNew.getIdcliente());
                venta.setClienteIdcliente(clienteIdclienteNew);
            }
            List<Articulo> attachedArticuloListNew = new ArrayList<Articulo>();
            for (Articulo articuloListNewArticuloToAttach : articuloListNew) {
                articuloListNewArticuloToAttach = em.getReference(articuloListNewArticuloToAttach.getClass(), articuloListNewArticuloToAttach.getIdarticulo());
                attachedArticuloListNew.add(articuloListNewArticuloToAttach);
            }
            articuloListNew = attachedArticuloListNew;
            venta.setArticuloList(articuloListNew);
            venta = em.merge(venta);
            if (clienteIdclienteOld != null && !clienteIdclienteOld.equals(clienteIdclienteNew)) {
                clienteIdclienteOld.getVentaList().remove(venta);
                clienteIdclienteOld = em.merge(clienteIdclienteOld);
            }
            if (clienteIdclienteNew != null && !clienteIdclienteNew.equals(clienteIdclienteOld)) {
                clienteIdclienteNew.getVentaList().add(venta);
                clienteIdclienteNew = em.merge(clienteIdclienteNew);
            }
            for (Articulo articuloListOldArticulo : articuloListOld) {
                if (!articuloListNew.contains(articuloListOldArticulo)) {
                    articuloListOldArticulo.setVentaIdventa(null);
                    articuloListOldArticulo = em.merge(articuloListOldArticulo);
                }
            }
            for (Articulo articuloListNewArticulo : articuloListNew) {
                if (!articuloListOld.contains(articuloListNewArticulo)) {
                    Venta oldVentaIdventaOfArticuloListNewArticulo = articuloListNewArticulo.getVentaIdventa();
                    articuloListNewArticulo.setVentaIdventa(venta);
                    articuloListNewArticulo = em.merge(articuloListNewArticulo);
                    if (oldVentaIdventaOfArticuloListNewArticulo != null && !oldVentaIdventaOfArticuloListNewArticulo.equals(venta)) {
                        oldVentaIdventaOfArticuloListNewArticulo.getArticuloList().remove(articuloListNewArticulo);
                        oldVentaIdventaOfArticuloListNewArticulo = em.merge(oldVentaIdventaOfArticuloListNewArticulo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = venta.getIdventa();
                if (findVenta(id) == null) {
                    throw new NonexistentEntityException("The venta with id " + id + " no longer exists.");
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
            Venta venta;
            try {
                venta = em.getReference(Venta.class, id);
                venta.getIdventa();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The venta with id " + id + " no longer exists.", enfe);
            }
            Cliente clienteIdcliente = venta.getClienteIdcliente();
            if (clienteIdcliente != null) {
                clienteIdcliente.getVentaList().remove(venta);
                clienteIdcliente = em.merge(clienteIdcliente);
            }
            List<Articulo> articuloList = venta.getArticuloList();
            for (Articulo articuloListArticulo : articuloList) {
                articuloListArticulo.setVentaIdventa(null);
                articuloListArticulo = em.merge(articuloListArticulo);
            }
            em.remove(venta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Venta> findVentaEntities() {
        return findVentaEntities(true, -1, -1);
    }

    public List<Venta> findVentaEntities(int maxResults, int firstResult) {
        return findVentaEntities(false, maxResults, firstResult);
    }

    private List<Venta> findVentaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Venta.class));
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

    public Venta findVenta(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Venta.class, id);
        } finally {
            em.close();
        }
    }

    public int getVentaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Venta> rt = cq.from(Venta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
