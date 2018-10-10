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
import Entities.Institucionemite;
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
public class InstitucionemiteJpaController implements Serializable {

    public InstitucionemiteJpaController() {
        this.emf = Persistence.createEntityManagerFactory("SysEmpenoPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Institucionemite institucionemite) {
        if (institucionemite.getClienteList() == null) {
            institucionemite.setClienteList(new ArrayList<Cliente>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Cliente> attachedClienteList = new ArrayList<Cliente>();
            for (Cliente clienteListClienteToAttach : institucionemite.getClienteList()) {
                clienteListClienteToAttach = em.getReference(clienteListClienteToAttach.getClass(), clienteListClienteToAttach.getIdcliente());
                attachedClienteList.add(clienteListClienteToAttach);
            }
            institucionemite.setClienteList(attachedClienteList);
            em.persist(institucionemite);
            for (Cliente clienteListCliente : institucionemite.getClienteList()) {
                Institucionemite oldInstitucionemiteIdinstitucionemiteOfClienteListCliente = clienteListCliente.getInstitucionemiteIdinstitucionemite();
                clienteListCliente.setInstitucionemiteIdinstitucionemite(institucionemite);
                clienteListCliente = em.merge(clienteListCliente);
                if (oldInstitucionemiteIdinstitucionemiteOfClienteListCliente != null) {
                    oldInstitucionemiteIdinstitucionemiteOfClienteListCliente.getClienteList().remove(clienteListCliente);
                    oldInstitucionemiteIdinstitucionemiteOfClienteListCliente = em.merge(oldInstitucionemiteIdinstitucionemiteOfClienteListCliente);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Institucionemite institucionemite) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Institucionemite persistentInstitucionemite = em.find(Institucionemite.class, institucionemite.getIdinstitucionemite());
            List<Cliente> clienteListOld = persistentInstitucionemite.getClienteList();
            List<Cliente> clienteListNew = institucionemite.getClienteList();
            List<String> illegalOrphanMessages = null;
            for (Cliente clienteListOldCliente : clienteListOld) {
                if (!clienteListNew.contains(clienteListOldCliente)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Cliente " + clienteListOldCliente + " since its institucionemiteIdinstitucionemite field is not nullable.");
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
            institucionemite.setClienteList(clienteListNew);
            institucionemite = em.merge(institucionemite);
            for (Cliente clienteListNewCliente : clienteListNew) {
                if (!clienteListOld.contains(clienteListNewCliente)) {
                    Institucionemite oldInstitucionemiteIdinstitucionemiteOfClienteListNewCliente = clienteListNewCliente.getInstitucionemiteIdinstitucionemite();
                    clienteListNewCliente.setInstitucionemiteIdinstitucionemite(institucionemite);
                    clienteListNewCliente = em.merge(clienteListNewCliente);
                    if (oldInstitucionemiteIdinstitucionemiteOfClienteListNewCliente != null && !oldInstitucionemiteIdinstitucionemiteOfClienteListNewCliente.equals(institucionemite)) {
                        oldInstitucionemiteIdinstitucionemiteOfClienteListNewCliente.getClienteList().remove(clienteListNewCliente);
                        oldInstitucionemiteIdinstitucionemiteOfClienteListNewCliente = em.merge(oldInstitucionemiteIdinstitucionemiteOfClienteListNewCliente);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = institucionemite.getIdinstitucionemite();
                if (findInstitucionemite(id) == null) {
                    throw new NonexistentEntityException("The institucionemite with id " + id + " no longer exists.");
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
            Institucionemite institucionemite;
            try {
                institucionemite = em.getReference(Institucionemite.class, id);
                institucionemite.getIdinstitucionemite();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The institucionemite with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Cliente> clienteListOrphanCheck = institucionemite.getClienteList();
            for (Cliente clienteListOrphanCheckCliente : clienteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Institucionemite (" + institucionemite + ") cannot be destroyed since the Cliente " + clienteListOrphanCheckCliente + " in its clienteList field has a non-nullable institucionemiteIdinstitucionemite field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(institucionemite);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Institucionemite> findInstitucionemiteEntities() {
        return findInstitucionemiteEntities(true, -1, -1);
    }

    public List<Institucionemite> findInstitucionemiteEntities(int maxResults, int firstResult) {
        return findInstitucionemiteEntities(false, maxResults, firstResult);
    }

    private List<Institucionemite> findInstitucionemiteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Institucionemite.class));
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

    public Institucionemite findInstitucionemite(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Institucionemite.class, id);
        } finally {
            em.close();
        }
    }

    public int getInstitucionemiteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Institucionemite> rt = cq.from(Institucionemite.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
