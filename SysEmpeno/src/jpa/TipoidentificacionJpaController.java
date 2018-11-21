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
import entities.Cliente;
import entities.Tipoidentificacion;
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
public class TipoidentificacionJpaController implements Serializable {

    public TipoidentificacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tipoidentificacion tipoidentificacion) {
        if (tipoidentificacion.getClienteList() == null) {
            tipoidentificacion.setClienteList(new ArrayList<Cliente>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Cliente> attachedClienteList = new ArrayList<Cliente>();
            for (Cliente clienteListClienteToAttach : tipoidentificacion.getClienteList()) {
                clienteListClienteToAttach = em.getReference(clienteListClienteToAttach.getClass(), clienteListClienteToAttach.getIdcliente());
                attachedClienteList.add(clienteListClienteToAttach);
            }
            tipoidentificacion.setClienteList(attachedClienteList);
            em.persist(tipoidentificacion);
            for (Cliente clienteListCliente : tipoidentificacion.getClienteList()) {
                Tipoidentificacion oldTipoidentificacionIdtipoidentificacionOfClienteListCliente = clienteListCliente.getTipoidentificacionIdtipoidentificacion();
                clienteListCliente.setTipoidentificacionIdtipoidentificacion(tipoidentificacion);
                clienteListCliente = em.merge(clienteListCliente);
                if (oldTipoidentificacionIdtipoidentificacionOfClienteListCliente != null) {
                    oldTipoidentificacionIdtipoidentificacionOfClienteListCliente.getClienteList().remove(clienteListCliente);
                    oldTipoidentificacionIdtipoidentificacionOfClienteListCliente = em.merge(oldTipoidentificacionIdtipoidentificacionOfClienteListCliente);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tipoidentificacion tipoidentificacion) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tipoidentificacion persistentTipoidentificacion = em.find(Tipoidentificacion.class, tipoidentificacion.getIdtipoidentificacion());
            List<Cliente> clienteListOld = persistentTipoidentificacion.getClienteList();
            List<Cliente> clienteListNew = tipoidentificacion.getClienteList();
            List<String> illegalOrphanMessages = null;
            for (Cliente clienteListOldCliente : clienteListOld) {
                if (!clienteListNew.contains(clienteListOldCliente)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Cliente " + clienteListOldCliente + " since its tipoidentificacionIdtipoidentificacion field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Cliente> attachedClienteListNew = new ArrayList<Cliente>();
            for (Cliente clienteListNewClienteToAttach : clienteListNew) {
                clienteListNewClienteToAttach = em.getReference(clienteListNewClienteToAttach.getClass(), clienteListNewClienteToAttach.getIdcliente());
                attachedClienteListNew.add(clienteListNewClienteToAttach);
            }
            clienteListNew = attachedClienteListNew;
            tipoidentificacion.setClienteList(clienteListNew);
            tipoidentificacion = em.merge(tipoidentificacion);
            for (Cliente clienteListNewCliente : clienteListNew) {
                if (!clienteListOld.contains(clienteListNewCliente)) {
                    Tipoidentificacion oldTipoidentificacionIdtipoidentificacionOfClienteListNewCliente = clienteListNewCliente.getTipoidentificacionIdtipoidentificacion();
                    clienteListNewCliente.setTipoidentificacionIdtipoidentificacion(tipoidentificacion);
                    clienteListNewCliente = em.merge(clienteListNewCliente);
                    if (oldTipoidentificacionIdtipoidentificacionOfClienteListNewCliente != null && !oldTipoidentificacionIdtipoidentificacionOfClienteListNewCliente.equals(tipoidentificacion)) {
                        oldTipoidentificacionIdtipoidentificacionOfClienteListNewCliente.getClienteList().remove(clienteListNewCliente);
                        oldTipoidentificacionIdtipoidentificacionOfClienteListNewCliente = em.merge(oldTipoidentificacionIdtipoidentificacionOfClienteListNewCliente);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipoidentificacion.getIdtipoidentificacion();
                if (findTipoidentificacion(id) == null) {
                    throw new NonexistentEntityException("The tipoidentificacion with id " + id + " no longer exists.");
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
            Tipoidentificacion tipoidentificacion;
            try {
                tipoidentificacion = em.getReference(Tipoidentificacion.class, id);
                tipoidentificacion.getIdtipoidentificacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoidentificacion with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Cliente> clienteListOrphanCheck = tipoidentificacion.getClienteList();
            for (Cliente clienteListOrphanCheckCliente : clienteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tipoidentificacion (" + tipoidentificacion + ") cannot be destroyed since the Cliente " + clienteListOrphanCheckCliente + " in its clienteList field has a non-nullable tipoidentificacionIdtipoidentificacion field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tipoidentificacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tipoidentificacion> findTipoidentificacionEntities() {
        return findTipoidentificacionEntities(true, -1, -1);
    }

    public List<Tipoidentificacion> findTipoidentificacionEntities(int maxResults, int firstResult) {
        return findTipoidentificacionEntities(false, maxResults, firstResult);
    }

    private List<Tipoidentificacion> findTipoidentificacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tipoidentificacion.class));
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

    public Tipoidentificacion findTipoidentificacion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tipoidentificacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoidentificacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tipoidentificacion> rt = cq.from(Tipoidentificacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
