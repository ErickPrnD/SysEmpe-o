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
import Entities.Estado;
import java.util.ArrayList;
import java.util.List;
import Entities.Cliente;
import Entities.Pais;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author erick
 */
public class PaisJpaController implements Serializable {

    public PaisJpaController() {
        this.emf = Persistence.createEntityManagerFactory("SysEmpenoPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pais pais) {
        if (pais.getEstadoList() == null) {
            pais.setEstadoList(new ArrayList<Estado>());
        }
        if (pais.getClienteList() == null) {
            pais.setClienteList(new ArrayList<Cliente>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Estado> attachedEstadoList = new ArrayList<Estado>();
            for (Estado estadoListEstadoToAttach : pais.getEstadoList()) {
                estadoListEstadoToAttach = em.getReference(estadoListEstadoToAttach.getClass(), estadoListEstadoToAttach.getIdestado());
                attachedEstadoList.add(estadoListEstadoToAttach);
            }
            pais.setEstadoList(attachedEstadoList);
            List<Cliente> attachedClienteList = new ArrayList<Cliente>();
            for (Cliente clienteListClienteToAttach : pais.getClienteList()) {
                clienteListClienteToAttach = em.getReference(clienteListClienteToAttach.getClass(), clienteListClienteToAttach.getIdcliente());
                attachedClienteList.add(clienteListClienteToAttach);
            }
            pais.setClienteList(attachedClienteList);
            em.persist(pais);
            for (Estado estadoListEstado : pais.getEstadoList()) {
                Pais oldPaisIdpaisOfEstadoListEstado = estadoListEstado.getPaisIdpais();
                estadoListEstado.setPaisIdpais(pais);
                estadoListEstado = em.merge(estadoListEstado);
                if (oldPaisIdpaisOfEstadoListEstado != null) {
                    oldPaisIdpaisOfEstadoListEstado.getEstadoList().remove(estadoListEstado);
                    oldPaisIdpaisOfEstadoListEstado = em.merge(oldPaisIdpaisOfEstadoListEstado);
                }
            }
            for (Cliente clienteListCliente : pais.getClienteList()) {
                Pais oldPaisnacimientoOfClienteListCliente = clienteListCliente.getPaisnacimiento();
                clienteListCliente.setPaisnacimiento(pais);
                clienteListCliente = em.merge(clienteListCliente);
                if (oldPaisnacimientoOfClienteListCliente != null) {
                    oldPaisnacimientoOfClienteListCliente.getClienteList().remove(clienteListCliente);
                    oldPaisnacimientoOfClienteListCliente = em.merge(oldPaisnacimientoOfClienteListCliente);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pais pais) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pais persistentPais = em.find(Pais.class, pais.getIdpais());
            List<Estado> estadoListOld = persistentPais.getEstadoList();
            List<Estado> estadoListNew = pais.getEstadoList();
            List<Cliente> clienteListOld = persistentPais.getClienteList();
            List<Cliente> clienteListNew = pais.getClienteList();
            List<String> illegalOrphanMessages = null;
            for (Estado estadoListOldEstado : estadoListOld) {
                if (!estadoListNew.contains(estadoListOldEstado)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Estado " + estadoListOldEstado + " since its paisIdpais field is not nullable.");
                }
            }
            for (Cliente clienteListOldCliente : clienteListOld) {
                if (!clienteListNew.contains(clienteListOldCliente)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Cliente " + clienteListOldCliente + " since its paisnacimiento field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Estado> attachedEstadoListNew = new ArrayList<Estado>();
            for (Estado estadoListNewEstadoToAttach : estadoListNew) {
                estadoListNewEstadoToAttach = em.getReference(estadoListNewEstadoToAttach.getClass(), estadoListNewEstadoToAttach.getIdestado());
                attachedEstadoListNew.add(estadoListNewEstadoToAttach);
            }
            estadoListNew = attachedEstadoListNew;
            pais.setEstadoList(estadoListNew);
            List<Cliente> attachedClienteListNew = new ArrayList<Cliente>();
            for (Cliente clienteListNewClienteToAttach : clienteListNew) {
                clienteListNewClienteToAttach = em.getReference(clienteListNewClienteToAttach.getClass(), clienteListNewClienteToAttach.getIdcliente());
                attachedClienteListNew.add(clienteListNewClienteToAttach);
            }
            clienteListNew = attachedClienteListNew;
            pais.setClienteList(clienteListNew);
            pais = em.merge(pais);
            for (Estado estadoListNewEstado : estadoListNew) {
                if (!estadoListOld.contains(estadoListNewEstado)) {
                    Pais oldPaisIdpaisOfEstadoListNewEstado = estadoListNewEstado.getPaisIdpais();
                    estadoListNewEstado.setPaisIdpais(pais);
                    estadoListNewEstado = em.merge(estadoListNewEstado);
                    if (oldPaisIdpaisOfEstadoListNewEstado != null && !oldPaisIdpaisOfEstadoListNewEstado.equals(pais)) {
                        oldPaisIdpaisOfEstadoListNewEstado.getEstadoList().remove(estadoListNewEstado);
                        oldPaisIdpaisOfEstadoListNewEstado = em.merge(oldPaisIdpaisOfEstadoListNewEstado);
                    }
                }
            }
            for (Cliente clienteListNewCliente : clienteListNew) {
                if (!clienteListOld.contains(clienteListNewCliente)) {
                    Pais oldPaisnacimientoOfClienteListNewCliente = clienteListNewCliente.getPaisnacimiento();
                    clienteListNewCliente.setPaisnacimiento(pais);
                    clienteListNewCliente = em.merge(clienteListNewCliente);
                    if (oldPaisnacimientoOfClienteListNewCliente != null && !oldPaisnacimientoOfClienteListNewCliente.equals(pais)) {
                        oldPaisnacimientoOfClienteListNewCliente.getClienteList().remove(clienteListNewCliente);
                        oldPaisnacimientoOfClienteListNewCliente = em.merge(oldPaisnacimientoOfClienteListNewCliente);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pais.getIdpais();
                if (findPais(id) == null) {
                    throw new NonexistentEntityException("The pais with id " + id + " no longer exists.");
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
            Pais pais;
            try {
                pais = em.getReference(Pais.class, id);
                pais.getIdpais();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pais with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Estado> estadoListOrphanCheck = pais.getEstadoList();
            for (Estado estadoListOrphanCheckEstado : estadoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Pais (" + pais + ") cannot be destroyed since the Estado " + estadoListOrphanCheckEstado + " in its estadoList field has a non-nullable paisIdpais field.");
            }
            List<Cliente> clienteListOrphanCheck = pais.getClienteList();
            for (Cliente clienteListOrphanCheckCliente : clienteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Pais (" + pais + ") cannot be destroyed since the Cliente " + clienteListOrphanCheckCliente + " in its clienteList field has a non-nullable paisnacimiento field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(pais);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pais> findPaisEntities() {
        return findPaisEntities(true, -1, -1);
    }

    public List<Pais> findPaisEntities(int maxResults, int firstResult) {
        return findPaisEntities(false, maxResults, firstResult);
    }

    private List<Pais> findPaisEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pais.class));
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

    public Pais findPais(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pais.class, id);
        } finally {
            em.close();
        }
    }

    public int getPaisCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pais> rt = cq.from(Pais.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
