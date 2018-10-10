/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import Entities.Estado;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entities.Pais;
import Entities.Municipio;
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
public class EstadoJpaController implements Serializable {

    public EstadoJpaController() {
        this.emf = Persistence.createEntityManagerFactory("SysEmpenoPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Estado estado) {
        if (estado.getMunicipioList() == null) {
            estado.setMunicipioList(new ArrayList<Municipio>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pais paisIdpais = estado.getPaisIdpais();
            if (paisIdpais != null) {
                paisIdpais = em.getReference(paisIdpais.getClass(), paisIdpais.getIdpais());
                estado.setPaisIdpais(paisIdpais);
            }
            List<Municipio> attachedMunicipioList = new ArrayList<Municipio>();
            for (Municipio municipioListMunicipioToAttach : estado.getMunicipioList()) {
                municipioListMunicipioToAttach = em.getReference(municipioListMunicipioToAttach.getClass(), municipioListMunicipioToAttach.getIdmunicipio());
                attachedMunicipioList.add(municipioListMunicipioToAttach);
            }
            estado.setMunicipioList(attachedMunicipioList);
            em.persist(estado);
            if (paisIdpais != null) {
                paisIdpais.getEstadoList().add(estado);
                paisIdpais = em.merge(paisIdpais);
            }
            for (Municipio municipioListMunicipio : estado.getMunicipioList()) {
                Estado oldEstadoIdestadoOfMunicipioListMunicipio = municipioListMunicipio.getEstadoIdestado();
                municipioListMunicipio.setEstadoIdestado(estado);
                municipioListMunicipio = em.merge(municipioListMunicipio);
                if (oldEstadoIdestadoOfMunicipioListMunicipio != null) {
                    oldEstadoIdestadoOfMunicipioListMunicipio.getMunicipioList().remove(municipioListMunicipio);
                    oldEstadoIdestadoOfMunicipioListMunicipio = em.merge(oldEstadoIdestadoOfMunicipioListMunicipio);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Estado estado) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estado persistentEstado = em.find(Estado.class, estado.getIdestado());
            Pais paisIdpaisOld = persistentEstado.getPaisIdpais();
            Pais paisIdpaisNew = estado.getPaisIdpais();
            List<Municipio> municipioListOld = persistentEstado.getMunicipioList();
            List<Municipio> municipioListNew = estado.getMunicipioList();
            List<String> illegalOrphanMessages = null;
            for (Municipio municipioListOldMunicipio : municipioListOld) {
                if (!municipioListNew.contains(municipioListOldMunicipio)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Municipio " + municipioListOldMunicipio + " since its estadoIdestado field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (paisIdpaisNew != null) {
                paisIdpaisNew = em.getReference(paisIdpaisNew.getClass(), paisIdpaisNew.getIdpais());
                estado.setPaisIdpais(paisIdpaisNew);
            }
            List<Municipio> attachedMunicipioListNew = new ArrayList<Municipio>();
            for (Municipio municipioListNewMunicipioToAttach : municipioListNew) {
                municipioListNewMunicipioToAttach = em.getReference(municipioListNewMunicipioToAttach.getClass(), municipioListNewMunicipioToAttach.getIdmunicipio());
                attachedMunicipioListNew.add(municipioListNewMunicipioToAttach);
            }
            municipioListNew = attachedMunicipioListNew;
            estado.setMunicipioList(municipioListNew);
            estado = em.merge(estado);
            if (paisIdpaisOld != null && !paisIdpaisOld.equals(paisIdpaisNew)) {
                paisIdpaisOld.getEstadoList().remove(estado);
                paisIdpaisOld = em.merge(paisIdpaisOld);
            }
            if (paisIdpaisNew != null && !paisIdpaisNew.equals(paisIdpaisOld)) {
                paisIdpaisNew.getEstadoList().add(estado);
                paisIdpaisNew = em.merge(paisIdpaisNew);
            }
            for (Municipio municipioListNewMunicipio : municipioListNew) {
                if (!municipioListOld.contains(municipioListNewMunicipio)) {
                    Estado oldEstadoIdestadoOfMunicipioListNewMunicipio = municipioListNewMunicipio.getEstadoIdestado();
                    municipioListNewMunicipio.setEstadoIdestado(estado);
                    municipioListNewMunicipio = em.merge(municipioListNewMunicipio);
                    if (oldEstadoIdestadoOfMunicipioListNewMunicipio != null && !oldEstadoIdestadoOfMunicipioListNewMunicipio.equals(estado)) {
                        oldEstadoIdestadoOfMunicipioListNewMunicipio.getMunicipioList().remove(municipioListNewMunicipio);
                        oldEstadoIdestadoOfMunicipioListNewMunicipio = em.merge(oldEstadoIdestadoOfMunicipioListNewMunicipio);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = estado.getIdestado();
                if (findEstado(id) == null) {
                    throw new NonexistentEntityException("The estado with id " + id + " no longer exists.");
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
            Estado estado;
            try {
                estado = em.getReference(Estado.class, id);
                estado.getIdestado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estado with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Municipio> municipioListOrphanCheck = estado.getMunicipioList();
            for (Municipio municipioListOrphanCheckMunicipio : municipioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Estado (" + estado + ") cannot be destroyed since the Municipio " + municipioListOrphanCheckMunicipio + " in its municipioList field has a non-nullable estadoIdestado field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Pais paisIdpais = estado.getPaisIdpais();
            if (paisIdpais != null) {
                paisIdpais.getEstadoList().remove(estado);
                paisIdpais = em.merge(paisIdpais);
            }
            em.remove(estado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Estado> findEstadoEntities() {
        return findEstadoEntities(true, -1, -1);
    }

    public List<Estado> findEstadoEntities(int maxResults, int firstResult) {
        return findEstadoEntities(false, maxResults, firstResult);
    }

    private List<Estado> findEstadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Estado.class));
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

    public Estado findEstado(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Estado.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstadoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Estado> rt = cq.from(Estado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
