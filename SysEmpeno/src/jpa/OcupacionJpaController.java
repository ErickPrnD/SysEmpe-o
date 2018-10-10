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
import Entities.Ocupacion;
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
public class OcupacionJpaController implements Serializable {

    public OcupacionJpaController() {
        this.emf = Persistence.createEntityManagerFactory("SysEmpenoPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ocupacion ocupacion) {
        if (ocupacion.getClienteList() == null) {
            ocupacion.setClienteList(new ArrayList<Cliente>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Cliente> attachedClienteList = new ArrayList<Cliente>();
            for (Cliente clienteListClienteToAttach : ocupacion.getClienteList()) {
                clienteListClienteToAttach = em.getReference(clienteListClienteToAttach.getClass(), clienteListClienteToAttach.getIdcliente());
                attachedClienteList.add(clienteListClienteToAttach);
            }
            ocupacion.setClienteList(attachedClienteList);
            em.persist(ocupacion);
            for (Cliente clienteListCliente : ocupacion.getClienteList()) {
                Ocupacion oldOcupacionIdocupacionOfClienteListCliente = clienteListCliente.getOcupacionIdocupacion();
                clienteListCliente.setOcupacionIdocupacion(ocupacion);
                clienteListCliente = em.merge(clienteListCliente);
                if (oldOcupacionIdocupacionOfClienteListCliente != null) {
                    oldOcupacionIdocupacionOfClienteListCliente.getClienteList().remove(clienteListCliente);
                    oldOcupacionIdocupacionOfClienteListCliente = em.merge(oldOcupacionIdocupacionOfClienteListCliente);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Ocupacion ocupacion) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ocupacion persistentOcupacion = em.find(Ocupacion.class, ocupacion.getIdocupacion());
            List<Cliente> clienteListOld = persistentOcupacion.getClienteList();
            List<Cliente> clienteListNew = ocupacion.getClienteList();
            List<String> illegalOrphanMessages = null;
            for (Cliente clienteListOldCliente : clienteListOld) {
                if (!clienteListNew.contains(clienteListOldCliente)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Cliente " + clienteListOldCliente + " since its ocupacionIdocupacion field is not nullable.");
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
            ocupacion.setClienteList(clienteListNew);
            ocupacion = em.merge(ocupacion);
            for (Cliente clienteListNewCliente : clienteListNew) {
                if (!clienteListOld.contains(clienteListNewCliente)) {
                    Ocupacion oldOcupacionIdocupacionOfClienteListNewCliente = clienteListNewCliente.getOcupacionIdocupacion();
                    clienteListNewCliente.setOcupacionIdocupacion(ocupacion);
                    clienteListNewCliente = em.merge(clienteListNewCliente);
                    if (oldOcupacionIdocupacionOfClienteListNewCliente != null && !oldOcupacionIdocupacionOfClienteListNewCliente.equals(ocupacion)) {
                        oldOcupacionIdocupacionOfClienteListNewCliente.getClienteList().remove(clienteListNewCliente);
                        oldOcupacionIdocupacionOfClienteListNewCliente = em.merge(oldOcupacionIdocupacionOfClienteListNewCliente);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ocupacion.getIdocupacion();
                if (findOcupacion(id) == null) {
                    throw new NonexistentEntityException("The ocupacion with id " + id + " no longer exists.");
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
            Ocupacion ocupacion;
            try {
                ocupacion = em.getReference(Ocupacion.class, id);
                ocupacion.getIdocupacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ocupacion with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Cliente> clienteListOrphanCheck = ocupacion.getClienteList();
            for (Cliente clienteListOrphanCheckCliente : clienteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Ocupacion (" + ocupacion + ") cannot be destroyed since the Cliente " + clienteListOrphanCheckCliente + " in its clienteList field has a non-nullable ocupacionIdocupacion field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(ocupacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Ocupacion> findOcupacionEntities() {
        return findOcupacionEntities(true, -1, -1);
    }

    public List<Ocupacion> findOcupacionEntities(int maxResults, int firstResult) {
        return findOcupacionEntities(false, maxResults, firstResult);
    }

    private List<Ocupacion> findOcupacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ocupacion.class));
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

    public Ocupacion findOcupacion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ocupacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getOcupacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ocupacion> rt = cq.from(Ocupacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
