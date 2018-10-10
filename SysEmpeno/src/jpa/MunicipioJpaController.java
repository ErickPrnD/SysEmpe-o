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
import Entities.Cliente;
import java.util.ArrayList;
import java.util.List;
import Entities.Empresa;
import Entities.Municipio;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author erick
 */
public class MunicipioJpaController implements Serializable {

    public MunicipioJpaController() {
        this.emf = Persistence.createEntityManagerFactory("SysEmpenoPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Municipio municipio) {
        if (municipio.getClienteList() == null) {
            municipio.setClienteList(new ArrayList<Cliente>());
        }
        if (municipio.getEmpresaList() == null) {
            municipio.setEmpresaList(new ArrayList<Empresa>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estado estadoIdestado = municipio.getEstadoIdestado();
            if (estadoIdestado != null) {
                estadoIdestado = em.getReference(estadoIdestado.getClass(), estadoIdestado.getIdestado());
                municipio.setEstadoIdestado(estadoIdestado);
            }
            List<Cliente> attachedClienteList = new ArrayList<Cliente>();
            for (Cliente clienteListClienteToAttach : municipio.getClienteList()) {
                clienteListClienteToAttach = em.getReference(clienteListClienteToAttach.getClass(), clienteListClienteToAttach.getIdcliente());
                attachedClienteList.add(clienteListClienteToAttach);
            }
            municipio.setClienteList(attachedClienteList);
            List<Empresa> attachedEmpresaList = new ArrayList<Empresa>();
            for (Empresa empresaListEmpresaToAttach : municipio.getEmpresaList()) {
                empresaListEmpresaToAttach = em.getReference(empresaListEmpresaToAttach.getClass(), empresaListEmpresaToAttach.getIdempresa());
                attachedEmpresaList.add(empresaListEmpresaToAttach);
            }
            municipio.setEmpresaList(attachedEmpresaList);
            em.persist(municipio);
            if (estadoIdestado != null) {
                estadoIdestado.getMunicipioList().add(municipio);
                estadoIdestado = em.merge(estadoIdestado);
            }
            for (Cliente clienteListCliente : municipio.getClienteList()) {
                Municipio oldMunicipioIdmunicipioOfClienteListCliente = clienteListCliente.getMunicipioIdmunicipio();
                clienteListCliente.setMunicipioIdmunicipio(municipio);
                clienteListCliente = em.merge(clienteListCliente);
                if (oldMunicipioIdmunicipioOfClienteListCliente != null) {
                    oldMunicipioIdmunicipioOfClienteListCliente.getClienteList().remove(clienteListCliente);
                    oldMunicipioIdmunicipioOfClienteListCliente = em.merge(oldMunicipioIdmunicipioOfClienteListCliente);
                }
            }
            for (Empresa empresaListEmpresa : municipio.getEmpresaList()) {
                Municipio oldMunicipioidMunicipioOfEmpresaListEmpresa = empresaListEmpresa.getMunicipioidMunicipio();
                empresaListEmpresa.setMunicipioidMunicipio(municipio);
                empresaListEmpresa = em.merge(empresaListEmpresa);
                if (oldMunicipioidMunicipioOfEmpresaListEmpresa != null) {
                    oldMunicipioidMunicipioOfEmpresaListEmpresa.getEmpresaList().remove(empresaListEmpresa);
                    oldMunicipioidMunicipioOfEmpresaListEmpresa = em.merge(oldMunicipioidMunicipioOfEmpresaListEmpresa);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Municipio municipio) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Municipio persistentMunicipio = em.find(Municipio.class, municipio.getIdmunicipio());
            Estado estadoIdestadoOld = persistentMunicipio.getEstadoIdestado();
            Estado estadoIdestadoNew = municipio.getEstadoIdestado();
            List<Cliente> clienteListOld = persistentMunicipio.getClienteList();
            List<Cliente> clienteListNew = municipio.getClienteList();
            List<Empresa> empresaListOld = persistentMunicipio.getEmpresaList();
            List<Empresa> empresaListNew = municipio.getEmpresaList();
            List<String> illegalOrphanMessages = null;
            for (Cliente clienteListOldCliente : clienteListOld) {
                if (!clienteListNew.contains(clienteListOldCliente)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Cliente " + clienteListOldCliente + " since its municipioIdmunicipio field is not nullable.");
                }
            }
            for (Empresa empresaListOldEmpresa : empresaListOld) {
                if (!empresaListNew.contains(empresaListOldEmpresa)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Empresa " + empresaListOldEmpresa + " since its municipioidMunicipio field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (estadoIdestadoNew != null) {
                estadoIdestadoNew = em.getReference(estadoIdestadoNew.getClass(), estadoIdestadoNew.getIdestado());
                municipio.setEstadoIdestado(estadoIdestadoNew);
            }
            List<Cliente> attachedClienteListNew = new ArrayList<Cliente>();
            for (Cliente clienteListNewClienteToAttach : clienteListNew) {
                clienteListNewClienteToAttach = em.getReference(clienteListNewClienteToAttach.getClass(), clienteListNewClienteToAttach.getIdcliente());
                attachedClienteListNew.add(clienteListNewClienteToAttach);
            }
            clienteListNew = attachedClienteListNew;
            municipio.setClienteList(clienteListNew);
            List<Empresa> attachedEmpresaListNew = new ArrayList<Empresa>();
            for (Empresa empresaListNewEmpresaToAttach : empresaListNew) {
                empresaListNewEmpresaToAttach = em.getReference(empresaListNewEmpresaToAttach.getClass(), empresaListNewEmpresaToAttach.getIdempresa());
                attachedEmpresaListNew.add(empresaListNewEmpresaToAttach);
            }
            empresaListNew = attachedEmpresaListNew;
            municipio.setEmpresaList(empresaListNew);
            municipio = em.merge(municipio);
            if (estadoIdestadoOld != null && !estadoIdestadoOld.equals(estadoIdestadoNew)) {
                estadoIdestadoOld.getMunicipioList().remove(municipio);
                estadoIdestadoOld = em.merge(estadoIdestadoOld);
            }
            if (estadoIdestadoNew != null && !estadoIdestadoNew.equals(estadoIdestadoOld)) {
                estadoIdestadoNew.getMunicipioList().add(municipio);
                estadoIdestadoNew = em.merge(estadoIdestadoNew);
            }
            for (Cliente clienteListNewCliente : clienteListNew) {
                if (!clienteListOld.contains(clienteListNewCliente)) {
                    Municipio oldMunicipioIdmunicipioOfClienteListNewCliente = clienteListNewCliente.getMunicipioIdmunicipio();
                    clienteListNewCliente.setMunicipioIdmunicipio(municipio);
                    clienteListNewCliente = em.merge(clienteListNewCliente);
                    if (oldMunicipioIdmunicipioOfClienteListNewCliente != null && !oldMunicipioIdmunicipioOfClienteListNewCliente.equals(municipio)) {
                        oldMunicipioIdmunicipioOfClienteListNewCliente.getClienteList().remove(clienteListNewCliente);
                        oldMunicipioIdmunicipioOfClienteListNewCliente = em.merge(oldMunicipioIdmunicipioOfClienteListNewCliente);
                    }
                }
            }
            for (Empresa empresaListNewEmpresa : empresaListNew) {
                if (!empresaListOld.contains(empresaListNewEmpresa)) {
                    Municipio oldMunicipioidMunicipioOfEmpresaListNewEmpresa = empresaListNewEmpresa.getMunicipioidMunicipio();
                    empresaListNewEmpresa.setMunicipioidMunicipio(municipio);
                    empresaListNewEmpresa = em.merge(empresaListNewEmpresa);
                    if (oldMunicipioidMunicipioOfEmpresaListNewEmpresa != null && !oldMunicipioidMunicipioOfEmpresaListNewEmpresa.equals(municipio)) {
                        oldMunicipioidMunicipioOfEmpresaListNewEmpresa.getEmpresaList().remove(empresaListNewEmpresa);
                        oldMunicipioidMunicipioOfEmpresaListNewEmpresa = em.merge(oldMunicipioidMunicipioOfEmpresaListNewEmpresa);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = municipio.getIdmunicipio();
                if (findMunicipio(id) == null) {
                    throw new NonexistentEntityException("The municipio with id " + id + " no longer exists.");
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
            Municipio municipio;
            try {
                municipio = em.getReference(Municipio.class, id);
                municipio.getIdmunicipio();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The municipio with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Cliente> clienteListOrphanCheck = municipio.getClienteList();
            for (Cliente clienteListOrphanCheckCliente : clienteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Municipio (" + municipio + ") cannot be destroyed since the Cliente " + clienteListOrphanCheckCliente + " in its clienteList field has a non-nullable municipioIdmunicipio field.");
            }
            List<Empresa> empresaListOrphanCheck = municipio.getEmpresaList();
            for (Empresa empresaListOrphanCheckEmpresa : empresaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Municipio (" + municipio + ") cannot be destroyed since the Empresa " + empresaListOrphanCheckEmpresa + " in its empresaList field has a non-nullable municipioidMunicipio field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Estado estadoIdestado = municipio.getEstadoIdestado();
            if (estadoIdestado != null) {
                estadoIdestado.getMunicipioList().remove(municipio);
                estadoIdestado = em.merge(estadoIdestado);
            }
            em.remove(municipio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Municipio> findMunicipioEntities() {
        return findMunicipioEntities(true, -1, -1);
    }

    public List<Municipio> findMunicipioEntities(int maxResults, int firstResult) {
        return findMunicipioEntities(false, maxResults, firstResult);
    }

    private List<Municipio> findMunicipioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Municipio.class));
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

    public Municipio findMunicipio(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Municipio.class, id);
        } finally {
            em.close();
        }
    }

    public int getMunicipioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Municipio> rt = cq.from(Municipio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
