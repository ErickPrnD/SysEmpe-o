/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import Entities.Empresa;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entities.Municipio;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author erick
 */
public class EmpresaJpaController implements Serializable {

    public EmpresaJpaController() {
        this.emf = Persistence.createEntityManagerFactory("SysEmpenoPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Empresa empresa) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Municipio municipioidMunicipio = empresa.getMunicipioidMunicipio();
            if (municipioidMunicipio != null) {
                municipioidMunicipio = em.getReference(municipioidMunicipio.getClass(), municipioidMunicipio.getIdmunicipio());
                empresa.setMunicipioidMunicipio(municipioidMunicipio);
            }
            em.persist(empresa);
            if (municipioidMunicipio != null) {
                municipioidMunicipio.getEmpresaList().add(empresa);
                municipioidMunicipio = em.merge(municipioidMunicipio);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Empresa empresa) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empresa persistentEmpresa = em.find(Empresa.class, empresa.getIdempresa());
            Municipio municipioidMunicipioOld = persistentEmpresa.getMunicipioidMunicipio();
            Municipio municipioidMunicipioNew = empresa.getMunicipioidMunicipio();
            if (municipioidMunicipioNew != null) {
                municipioidMunicipioNew = em.getReference(municipioidMunicipioNew.getClass(), municipioidMunicipioNew.getIdmunicipio());
                empresa.setMunicipioidMunicipio(municipioidMunicipioNew);
            }
            empresa = em.merge(empresa);
            if (municipioidMunicipioOld != null && !municipioidMunicipioOld.equals(municipioidMunicipioNew)) {
                municipioidMunicipioOld.getEmpresaList().remove(empresa);
                municipioidMunicipioOld = em.merge(municipioidMunicipioOld);
            }
            if (municipioidMunicipioNew != null && !municipioidMunicipioNew.equals(municipioidMunicipioOld)) {
                municipioidMunicipioNew.getEmpresaList().add(empresa);
                municipioidMunicipioNew = em.merge(municipioidMunicipioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = empresa.getIdempresa();
                if (findEmpresa(id) == null) {
                    throw new NonexistentEntityException("The empresa with id " + id + " no longer exists.");
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
            Empresa empresa;
            try {
                empresa = em.getReference(Empresa.class, id);
                empresa.getIdempresa();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The empresa with id " + id + " no longer exists.", enfe);
            }
            Municipio municipioidMunicipio = empresa.getMunicipioidMunicipio();
            if (municipioidMunicipio != null) {
                municipioidMunicipio.getEmpresaList().remove(empresa);
                municipioidMunicipio = em.merge(municipioidMunicipio);
            }
            em.remove(empresa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Empresa> findEmpresaEntities() {
        return findEmpresaEntities(true, -1, -1);
    }

    public List<Empresa> findEmpresaEntities(int maxResults, int firstResult) {
        return findEmpresaEntities(false, maxResults, firstResult);
    }

    private List<Empresa> findEmpresaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Empresa.class));
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

    public Empresa findEmpresa(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Empresa.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmpresaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Empresa> rt = cq.from(Empresa.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
