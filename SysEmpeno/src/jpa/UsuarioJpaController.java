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
import entities.Rol;
import entities.Mueve;
import java.util.ArrayList;
import java.util.List;
import entities.Prenda;
import entities.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author erick
 */
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) {
        if (usuario.getMueveList() == null) {
            usuario.setMueveList(new ArrayList<Mueve>());
        }
        if (usuario.getPrendaList() == null) {
            usuario.setPrendaList(new ArrayList<Prenda>());
        }
        if (usuario.getPrendaList1() == null) {
            usuario.setPrendaList1(new ArrayList<Prenda>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rol rolIdrol = usuario.getRolIdrol();
            if (rolIdrol != null) {
                rolIdrol = em.getReference(rolIdrol.getClass(), rolIdrol.getIdrol());
                usuario.setRolIdrol(rolIdrol);
            }
            List<Mueve> attachedMueveList = new ArrayList<Mueve>();
            for (Mueve mueveListMueveToAttach : usuario.getMueveList()) {
                mueveListMueveToAttach = em.getReference(mueveListMueveToAttach.getClass(), mueveListMueveToAttach.getIdmueve());
                attachedMueveList.add(mueveListMueveToAttach);
            }
            usuario.setMueveList(attachedMueveList);
            List<Prenda> attachedPrendaList = new ArrayList<Prenda>();
            for (Prenda prendaListPrendaToAttach : usuario.getPrendaList()) {
                prendaListPrendaToAttach = em.getReference(prendaListPrendaToAttach.getClass(), prendaListPrendaToAttach.getIdprenda());
                attachedPrendaList.add(prendaListPrendaToAttach);
            }
            usuario.setPrendaList(attachedPrendaList);
            List<Prenda> attachedPrendaList1 = new ArrayList<Prenda>();
            for (Prenda prendaList1PrendaToAttach : usuario.getPrendaList1()) {
                prendaList1PrendaToAttach = em.getReference(prendaList1PrendaToAttach.getClass(), prendaList1PrendaToAttach.getIdprenda());
                attachedPrendaList1.add(prendaList1PrendaToAttach);
            }
            usuario.setPrendaList1(attachedPrendaList1);
            em.persist(usuario);
            if (rolIdrol != null) {
                rolIdrol.getUsuarioList().add(usuario);
                rolIdrol = em.merge(rolIdrol);
            }
            for (Mueve mueveListMueve : usuario.getMueveList()) {
                Usuario oldUsuarioIdusuarioOfMueveListMueve = mueveListMueve.getUsuarioIdusuario();
                mueveListMueve.setUsuarioIdusuario(usuario);
                mueveListMueve = em.merge(mueveListMueve);
                if (oldUsuarioIdusuarioOfMueveListMueve != null) {
                    oldUsuarioIdusuarioOfMueveListMueve.getMueveList().remove(mueveListMueve);
                    oldUsuarioIdusuarioOfMueveListMueve = em.merge(oldUsuarioIdusuarioOfMueveListMueve);
                }
            }
            for (Prenda prendaListPrenda : usuario.getPrendaList()) {
                Usuario oldRevisaOfPrendaListPrenda = prendaListPrenda.getRevisa();
                prendaListPrenda.setRevisa(usuario);
                prendaListPrenda = em.merge(prendaListPrenda);
                if (oldRevisaOfPrendaListPrenda != null) {
                    oldRevisaOfPrendaListPrenda.getPrendaList().remove(prendaListPrenda);
                    oldRevisaOfPrendaListPrenda = em.merge(oldRevisaOfPrendaListPrenda);
                }
            }
            for (Prenda prendaList1Prenda : usuario.getPrendaList1()) {
                Usuario oldAutorizaOfPrendaList1Prenda = prendaList1Prenda.getAutoriza();
                prendaList1Prenda.setAutoriza(usuario);
                prendaList1Prenda = em.merge(prendaList1Prenda);
                if (oldAutorizaOfPrendaList1Prenda != null) {
                    oldAutorizaOfPrendaList1Prenda.getPrendaList1().remove(prendaList1Prenda);
                    oldAutorizaOfPrendaList1Prenda = em.merge(oldAutorizaOfPrendaList1Prenda);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getIdusuario());
            Rol rolIdrolOld = persistentUsuario.getRolIdrol();
            Rol rolIdrolNew = usuario.getRolIdrol();
            List<Mueve> mueveListOld = persistentUsuario.getMueveList();
            List<Mueve> mueveListNew = usuario.getMueveList();
            List<Prenda> prendaListOld = persistentUsuario.getPrendaList();
            List<Prenda> prendaListNew = usuario.getPrendaList();
            List<Prenda> prendaList1Old = persistentUsuario.getPrendaList1();
            List<Prenda> prendaList1New = usuario.getPrendaList1();
            List<String> illegalOrphanMessages = null;
            for (Mueve mueveListOldMueve : mueveListOld) {
                if (!mueveListNew.contains(mueveListOldMueve)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Mueve " + mueveListOldMueve + " since its usuarioIdusuario field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (rolIdrolNew != null) {
                rolIdrolNew = em.getReference(rolIdrolNew.getClass(), rolIdrolNew.getIdrol());
                usuario.setRolIdrol(rolIdrolNew);
            }
            List<Mueve> attachedMueveListNew = new ArrayList<Mueve>();
            for (Mueve mueveListNewMueveToAttach : mueveListNew) {
                mueveListNewMueveToAttach = em.getReference(mueveListNewMueveToAttach.getClass(), mueveListNewMueveToAttach.getIdmueve());
                attachedMueveListNew.add(mueveListNewMueveToAttach);
            }
            mueveListNew = attachedMueveListNew;
            usuario.setMueveList(mueveListNew);
            List<Prenda> attachedPrendaListNew = new ArrayList<Prenda>();
            for (Prenda prendaListNewPrendaToAttach : prendaListNew) {
                prendaListNewPrendaToAttach = em.getReference(prendaListNewPrendaToAttach.getClass(), prendaListNewPrendaToAttach.getIdprenda());
                attachedPrendaListNew.add(prendaListNewPrendaToAttach);
            }
            prendaListNew = attachedPrendaListNew;
            usuario.setPrendaList(prendaListNew);
            List<Prenda> attachedPrendaList1New = new ArrayList<Prenda>();
            for (Prenda prendaList1NewPrendaToAttach : prendaList1New) {
                prendaList1NewPrendaToAttach = em.getReference(prendaList1NewPrendaToAttach.getClass(), prendaList1NewPrendaToAttach.getIdprenda());
                attachedPrendaList1New.add(prendaList1NewPrendaToAttach);
            }
            prendaList1New = attachedPrendaList1New;
            usuario.setPrendaList1(prendaList1New);
            usuario = em.merge(usuario);
            if (rolIdrolOld != null && !rolIdrolOld.equals(rolIdrolNew)) {
                rolIdrolOld.getUsuarioList().remove(usuario);
                rolIdrolOld = em.merge(rolIdrolOld);
            }
            if (rolIdrolNew != null && !rolIdrolNew.equals(rolIdrolOld)) {
                rolIdrolNew.getUsuarioList().add(usuario);
                rolIdrolNew = em.merge(rolIdrolNew);
            }
            for (Mueve mueveListNewMueve : mueveListNew) {
                if (!mueveListOld.contains(mueveListNewMueve)) {
                    Usuario oldUsuarioIdusuarioOfMueveListNewMueve = mueveListNewMueve.getUsuarioIdusuario();
                    mueveListNewMueve.setUsuarioIdusuario(usuario);
                    mueveListNewMueve = em.merge(mueveListNewMueve);
                    if (oldUsuarioIdusuarioOfMueveListNewMueve != null && !oldUsuarioIdusuarioOfMueveListNewMueve.equals(usuario)) {
                        oldUsuarioIdusuarioOfMueveListNewMueve.getMueveList().remove(mueveListNewMueve);
                        oldUsuarioIdusuarioOfMueveListNewMueve = em.merge(oldUsuarioIdusuarioOfMueveListNewMueve);
                    }
                }
            }
            for (Prenda prendaListOldPrenda : prendaListOld) {
                if (!prendaListNew.contains(prendaListOldPrenda)) {
                    prendaListOldPrenda.setRevisa(null);
                    prendaListOldPrenda = em.merge(prendaListOldPrenda);
                }
            }
            for (Prenda prendaListNewPrenda : prendaListNew) {
                if (!prendaListOld.contains(prendaListNewPrenda)) {
                    Usuario oldRevisaOfPrendaListNewPrenda = prendaListNewPrenda.getRevisa();
                    prendaListNewPrenda.setRevisa(usuario);
                    prendaListNewPrenda = em.merge(prendaListNewPrenda);
                    if (oldRevisaOfPrendaListNewPrenda != null && !oldRevisaOfPrendaListNewPrenda.equals(usuario)) {
                        oldRevisaOfPrendaListNewPrenda.getPrendaList().remove(prendaListNewPrenda);
                        oldRevisaOfPrendaListNewPrenda = em.merge(oldRevisaOfPrendaListNewPrenda);
                    }
                }
            }
            for (Prenda prendaList1OldPrenda : prendaList1Old) {
                if (!prendaList1New.contains(prendaList1OldPrenda)) {
                    prendaList1OldPrenda.setAutoriza(null);
                    prendaList1OldPrenda = em.merge(prendaList1OldPrenda);
                }
            }
            for (Prenda prendaList1NewPrenda : prendaList1New) {
                if (!prendaList1Old.contains(prendaList1NewPrenda)) {
                    Usuario oldAutorizaOfPrendaList1NewPrenda = prendaList1NewPrenda.getAutoriza();
                    prendaList1NewPrenda.setAutoriza(usuario);
                    prendaList1NewPrenda = em.merge(prendaList1NewPrenda);
                    if (oldAutorizaOfPrendaList1NewPrenda != null && !oldAutorizaOfPrendaList1NewPrenda.equals(usuario)) {
                        oldAutorizaOfPrendaList1NewPrenda.getPrendaList1().remove(prendaList1NewPrenda);
                        oldAutorizaOfPrendaList1NewPrenda = em.merge(oldAutorizaOfPrendaList1NewPrenda);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usuario.getIdusuario();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
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
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getIdusuario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Mueve> mueveListOrphanCheck = usuario.getMueveList();
            for (Mueve mueveListOrphanCheckMueve : mueveListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Mueve " + mueveListOrphanCheckMueve + " in its mueveList field has a non-nullable usuarioIdusuario field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Rol rolIdrol = usuario.getRolIdrol();
            if (rolIdrol != null) {
                rolIdrol.getUsuarioList().remove(usuario);
                rolIdrol = em.merge(rolIdrol);
            }
            List<Prenda> prendaList = usuario.getPrendaList();
            for (Prenda prendaListPrenda : prendaList) {
                prendaListPrenda.setRevisa(null);
                prendaListPrenda = em.merge(prendaListPrenda);
            }
            List<Prenda> prendaList1 = usuario.getPrendaList1();
            for (Prenda prendaList1Prenda : prendaList1) {
                prendaList1Prenda.setAutoriza(null);
                prendaList1Prenda = em.merge(prendaList1Prenda);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
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

    public Usuario findUsuario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
