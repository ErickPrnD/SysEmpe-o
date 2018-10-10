/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import Entities.Cliente;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entities.Institucionemite;
import Entities.Municipio;
import Entities.Ocupacion;
import Entities.Pais;
import Entities.Tipoidentificacion;
import Entities.Venta;
import java.util.ArrayList;
import java.util.List;
import Entities.Contrato;
import Entities.Fotocliente;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author erick
 */
public class ClienteJpaController implements Serializable {

    public ClienteJpaController() {
        this.emf = Persistence.createEntityManagerFactory("SysEmpenoPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cliente cliente) {
        if (cliente.getVentaList() == null) {
            cliente.setVentaList(new ArrayList<Venta>());
        }
        if (cliente.getContratoList() == null) {
            cliente.setContratoList(new ArrayList<Contrato>());
        }
        if (cliente.getFotoclienteList() == null) {
            cliente.setFotoclienteList(new ArrayList<Fotocliente>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Institucionemite institucionemiteIdinstitucionemite = cliente.getInstitucionemiteIdinstitucionemite();
            if (institucionemiteIdinstitucionemite != null) {
                institucionemiteIdinstitucionemite = em.getReference(institucionemiteIdinstitucionemite.getClass(), institucionemiteIdinstitucionemite.getIdinstitucionemite());
                cliente.setInstitucionemiteIdinstitucionemite(institucionemiteIdinstitucionemite);
            }
            Municipio municipioIdmunicipio = cliente.getMunicipioIdmunicipio();
            if (municipioIdmunicipio != null) {
                municipioIdmunicipio = em.getReference(municipioIdmunicipio.getClass(), municipioIdmunicipio.getIdmunicipio());
                cliente.setMunicipioIdmunicipio(municipioIdmunicipio);
            }
            Ocupacion ocupacionIdocupacion = cliente.getOcupacionIdocupacion();
            if (ocupacionIdocupacion != null) {
                ocupacionIdocupacion = em.getReference(ocupacionIdocupacion.getClass(), ocupacionIdocupacion.getIdocupacion());
                cliente.setOcupacionIdocupacion(ocupacionIdocupacion);
            }
            Pais paisnacimiento = cliente.getPaisnacimiento();
            if (paisnacimiento != null) {
                paisnacimiento = em.getReference(paisnacimiento.getClass(), paisnacimiento.getIdpais());
                cliente.setPaisnacimiento(paisnacimiento);
            }
            Tipoidentificacion tipoidentificacionIdtipoidentificacion = cliente.getTipoidentificacionIdtipoidentificacion();
            if (tipoidentificacionIdtipoidentificacion != null) {
                tipoidentificacionIdtipoidentificacion = em.getReference(tipoidentificacionIdtipoidentificacion.getClass(), tipoidentificacionIdtipoidentificacion.getIdtipoidentificacion());
                cliente.setTipoidentificacionIdtipoidentificacion(tipoidentificacionIdtipoidentificacion);
            }
            List<Venta> attachedVentaList = new ArrayList<Venta>();
            for (Venta ventaListVentaToAttach : cliente.getVentaList()) {
                ventaListVentaToAttach = em.getReference(ventaListVentaToAttach.getClass(), ventaListVentaToAttach.getIdventa());
                attachedVentaList.add(ventaListVentaToAttach);
            }
            cliente.setVentaList(attachedVentaList);
            List<Contrato> attachedContratoList = new ArrayList<Contrato>();
            for (Contrato contratoListContratoToAttach : cliente.getContratoList()) {
                contratoListContratoToAttach = em.getReference(contratoListContratoToAttach.getClass(), contratoListContratoToAttach.getIdcontrato());
                attachedContratoList.add(contratoListContratoToAttach);
            }
            cliente.setContratoList(attachedContratoList);
            List<Fotocliente> attachedFotoclienteList = new ArrayList<Fotocliente>();
            for (Fotocliente fotoclienteListFotoclienteToAttach : cliente.getFotoclienteList()) {
                fotoclienteListFotoclienteToAttach = em.getReference(fotoclienteListFotoclienteToAttach.getClass(), fotoclienteListFotoclienteToAttach.getIdfoto());
                attachedFotoclienteList.add(fotoclienteListFotoclienteToAttach);
            }
            cliente.setFotoclienteList(attachedFotoclienteList);
            em.persist(cliente);
            if (institucionemiteIdinstitucionemite != null) {
                institucionemiteIdinstitucionemite.getClienteList().add(cliente);
                institucionemiteIdinstitucionemite = em.merge(institucionemiteIdinstitucionemite);
            }
            if (municipioIdmunicipio != null) {
                municipioIdmunicipio.getClienteList().add(cliente);
                municipioIdmunicipio = em.merge(municipioIdmunicipio);
            }
            if (ocupacionIdocupacion != null) {
                ocupacionIdocupacion.getClienteList().add(cliente);
                ocupacionIdocupacion = em.merge(ocupacionIdocupacion);
            }
            if (paisnacimiento != null) {
                paisnacimiento.getClienteList().add(cliente);
                paisnacimiento = em.merge(paisnacimiento);
            }
            if (tipoidentificacionIdtipoidentificacion != null) {
                tipoidentificacionIdtipoidentificacion.getClienteList().add(cliente);
                tipoidentificacionIdtipoidentificacion = em.merge(tipoidentificacionIdtipoidentificacion);
            }
            for (Venta ventaListVenta : cliente.getVentaList()) {
                Cliente oldClienteIdclienteOfVentaListVenta = ventaListVenta.getClienteIdcliente();
                ventaListVenta.setClienteIdcliente(cliente);
                ventaListVenta = em.merge(ventaListVenta);
                if (oldClienteIdclienteOfVentaListVenta != null) {
                    oldClienteIdclienteOfVentaListVenta.getVentaList().remove(ventaListVenta);
                    oldClienteIdclienteOfVentaListVenta = em.merge(oldClienteIdclienteOfVentaListVenta);
                }
            }
            for (Contrato contratoListContrato : cliente.getContratoList()) {
                Cliente oldClienteIdclienteOfContratoListContrato = contratoListContrato.getClienteIdcliente();
                contratoListContrato.setClienteIdcliente(cliente);
                contratoListContrato = em.merge(contratoListContrato);
                if (oldClienteIdclienteOfContratoListContrato != null) {
                    oldClienteIdclienteOfContratoListContrato.getContratoList().remove(contratoListContrato);
                    oldClienteIdclienteOfContratoListContrato = em.merge(oldClienteIdclienteOfContratoListContrato);
                }
            }
            for (Fotocliente fotoclienteListFotocliente : cliente.getFotoclienteList()) {
                Cliente oldClienteIdclienteOfFotoclienteListFotocliente = fotoclienteListFotocliente.getClienteIdcliente();
                fotoclienteListFotocliente.setClienteIdcliente(cliente);
                fotoclienteListFotocliente = em.merge(fotoclienteListFotocliente);
                if (oldClienteIdclienteOfFotoclienteListFotocliente != null) {
                    oldClienteIdclienteOfFotoclienteListFotocliente.getFotoclienteList().remove(fotoclienteListFotocliente);
                    oldClienteIdclienteOfFotoclienteListFotocliente = em.merge(oldClienteIdclienteOfFotoclienteListFotocliente);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cliente cliente) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente persistentCliente = em.find(Cliente.class, cliente.getIdcliente());
            Institucionemite institucionemiteIdinstitucionemiteOld = persistentCliente.getInstitucionemiteIdinstitucionemite();
            Institucionemite institucionemiteIdinstitucionemiteNew = cliente.getInstitucionemiteIdinstitucionemite();
            Municipio municipioIdmunicipioOld = persistentCliente.getMunicipioIdmunicipio();
            Municipio municipioIdmunicipioNew = cliente.getMunicipioIdmunicipio();
            Ocupacion ocupacionIdocupacionOld = persistentCliente.getOcupacionIdocupacion();
            Ocupacion ocupacionIdocupacionNew = cliente.getOcupacionIdocupacion();
            Pais paisnacimientoOld = persistentCliente.getPaisnacimiento();
            Pais paisnacimientoNew = cliente.getPaisnacimiento();
            Tipoidentificacion tipoidentificacionIdtipoidentificacionOld = persistentCliente.getTipoidentificacionIdtipoidentificacion();
            Tipoidentificacion tipoidentificacionIdtipoidentificacionNew = cliente.getTipoidentificacionIdtipoidentificacion();
            List<Venta> ventaListOld = persistentCliente.getVentaList();
            List<Venta> ventaListNew = cliente.getVentaList();
            List<Contrato> contratoListOld = persistentCliente.getContratoList();
            List<Contrato> contratoListNew = cliente.getContratoList();
            List<Fotocliente> fotoclienteListOld = persistentCliente.getFotoclienteList();
            List<Fotocliente> fotoclienteListNew = cliente.getFotoclienteList();
            List<String> illegalOrphanMessages = null;
            for (Venta ventaListOldVenta : ventaListOld) {
                if (!ventaListNew.contains(ventaListOldVenta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Venta " + ventaListOldVenta + " since its clienteIdcliente field is not nullable.");
                }
            }
            for (Contrato contratoListOldContrato : contratoListOld) {
                if (!contratoListNew.contains(contratoListOldContrato)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Contrato " + contratoListOldContrato + " since its clienteIdcliente field is not nullable.");
                }
            }
            for (Fotocliente fotoclienteListOldFotocliente : fotoclienteListOld) {
                if (!fotoclienteListNew.contains(fotoclienteListOldFotocliente)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Fotocliente " + fotoclienteListOldFotocliente + " since its clienteIdcliente field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (institucionemiteIdinstitucionemiteNew != null) {
                institucionemiteIdinstitucionemiteNew = em.getReference(institucionemiteIdinstitucionemiteNew.getClass(), institucionemiteIdinstitucionemiteNew.getIdinstitucionemite());
                cliente.setInstitucionemiteIdinstitucionemite(institucionemiteIdinstitucionemiteNew);
            }
            if (municipioIdmunicipioNew != null) {
                municipioIdmunicipioNew = em.getReference(municipioIdmunicipioNew.getClass(), municipioIdmunicipioNew.getIdmunicipio());
                cliente.setMunicipioIdmunicipio(municipioIdmunicipioNew);
            }
            if (ocupacionIdocupacionNew != null) {
                ocupacionIdocupacionNew = em.getReference(ocupacionIdocupacionNew.getClass(), ocupacionIdocupacionNew.getIdocupacion());
                cliente.setOcupacionIdocupacion(ocupacionIdocupacionNew);
            }
            if (paisnacimientoNew != null) {
                paisnacimientoNew = em.getReference(paisnacimientoNew.getClass(), paisnacimientoNew.getIdpais());
                cliente.setPaisnacimiento(paisnacimientoNew);
            }
            if (tipoidentificacionIdtipoidentificacionNew != null) {
                tipoidentificacionIdtipoidentificacionNew = em.getReference(tipoidentificacionIdtipoidentificacionNew.getClass(), tipoidentificacionIdtipoidentificacionNew.getIdtipoidentificacion());
                cliente.setTipoidentificacionIdtipoidentificacion(tipoidentificacionIdtipoidentificacionNew);
            }
            List<Venta> attachedVentaListNew = new ArrayList<Venta>();
            for (Venta ventaListNewVentaToAttach : ventaListNew) {
                ventaListNewVentaToAttach = em.getReference(ventaListNewVentaToAttach.getClass(), ventaListNewVentaToAttach.getIdventa());
                attachedVentaListNew.add(ventaListNewVentaToAttach);
            }
            ventaListNew = attachedVentaListNew;
            cliente.setVentaList(ventaListNew);
            List<Contrato> attachedContratoListNew = new ArrayList<Contrato>();
            for (Contrato contratoListNewContratoToAttach : contratoListNew) {
                contratoListNewContratoToAttach = em.getReference(contratoListNewContratoToAttach.getClass(), contratoListNewContratoToAttach.getIdcontrato());
                attachedContratoListNew.add(contratoListNewContratoToAttach);
            }
            contratoListNew = attachedContratoListNew;
            cliente.setContratoList(contratoListNew);
            List<Fotocliente> attachedFotoclienteListNew = new ArrayList<Fotocliente>();
            for (Fotocliente fotoclienteListNewFotoclienteToAttach : fotoclienteListNew) {
                fotoclienteListNewFotoclienteToAttach = em.getReference(fotoclienteListNewFotoclienteToAttach.getClass(), fotoclienteListNewFotoclienteToAttach.getIdfoto());
                attachedFotoclienteListNew.add(fotoclienteListNewFotoclienteToAttach);
            }
            fotoclienteListNew = attachedFotoclienteListNew;
            cliente.setFotoclienteList(fotoclienteListNew);
            cliente = em.merge(cliente);
            if (institucionemiteIdinstitucionemiteOld != null && !institucionemiteIdinstitucionemiteOld.equals(institucionemiteIdinstitucionemiteNew)) {
                institucionemiteIdinstitucionemiteOld.getClienteList().remove(cliente);
                institucionemiteIdinstitucionemiteOld = em.merge(institucionemiteIdinstitucionemiteOld);
            }
            if (institucionemiteIdinstitucionemiteNew != null && !institucionemiteIdinstitucionemiteNew.equals(institucionemiteIdinstitucionemiteOld)) {
                institucionemiteIdinstitucionemiteNew.getClienteList().add(cliente);
                institucionemiteIdinstitucionemiteNew = em.merge(institucionemiteIdinstitucionemiteNew);
            }
            if (municipioIdmunicipioOld != null && !municipioIdmunicipioOld.equals(municipioIdmunicipioNew)) {
                municipioIdmunicipioOld.getClienteList().remove(cliente);
                municipioIdmunicipioOld = em.merge(municipioIdmunicipioOld);
            }
            if (municipioIdmunicipioNew != null && !municipioIdmunicipioNew.equals(municipioIdmunicipioOld)) {
                municipioIdmunicipioNew.getClienteList().add(cliente);
                municipioIdmunicipioNew = em.merge(municipioIdmunicipioNew);
            }
            if (ocupacionIdocupacionOld != null && !ocupacionIdocupacionOld.equals(ocupacionIdocupacionNew)) {
                ocupacionIdocupacionOld.getClienteList().remove(cliente);
                ocupacionIdocupacionOld = em.merge(ocupacionIdocupacionOld);
            }
            if (ocupacionIdocupacionNew != null && !ocupacionIdocupacionNew.equals(ocupacionIdocupacionOld)) {
                ocupacionIdocupacionNew.getClienteList().add(cliente);
                ocupacionIdocupacionNew = em.merge(ocupacionIdocupacionNew);
            }
            if (paisnacimientoOld != null && !paisnacimientoOld.equals(paisnacimientoNew)) {
                paisnacimientoOld.getClienteList().remove(cliente);
                paisnacimientoOld = em.merge(paisnacimientoOld);
            }
            if (paisnacimientoNew != null && !paisnacimientoNew.equals(paisnacimientoOld)) {
                paisnacimientoNew.getClienteList().add(cliente);
                paisnacimientoNew = em.merge(paisnacimientoNew);
            }
            if (tipoidentificacionIdtipoidentificacionOld != null && !tipoidentificacionIdtipoidentificacionOld.equals(tipoidentificacionIdtipoidentificacionNew)) {
                tipoidentificacionIdtipoidentificacionOld.getClienteList().remove(cliente);
                tipoidentificacionIdtipoidentificacionOld = em.merge(tipoidentificacionIdtipoidentificacionOld);
            }
            if (tipoidentificacionIdtipoidentificacionNew != null && !tipoidentificacionIdtipoidentificacionNew.equals(tipoidentificacionIdtipoidentificacionOld)) {
                tipoidentificacionIdtipoidentificacionNew.getClienteList().add(cliente);
                tipoidentificacionIdtipoidentificacionNew = em.merge(tipoidentificacionIdtipoidentificacionNew);
            }
            for (Venta ventaListNewVenta : ventaListNew) {
                if (!ventaListOld.contains(ventaListNewVenta)) {
                    Cliente oldClienteIdclienteOfVentaListNewVenta = ventaListNewVenta.getClienteIdcliente();
                    ventaListNewVenta.setClienteIdcliente(cliente);
                    ventaListNewVenta = em.merge(ventaListNewVenta);
                    if (oldClienteIdclienteOfVentaListNewVenta != null && !oldClienteIdclienteOfVentaListNewVenta.equals(cliente)) {
                        oldClienteIdclienteOfVentaListNewVenta.getVentaList().remove(ventaListNewVenta);
                        oldClienteIdclienteOfVentaListNewVenta = em.merge(oldClienteIdclienteOfVentaListNewVenta);
                    }
                }
            }
            for (Contrato contratoListNewContrato : contratoListNew) {
                if (!contratoListOld.contains(contratoListNewContrato)) {
                    Cliente oldClienteIdclienteOfContratoListNewContrato = contratoListNewContrato.getClienteIdcliente();
                    contratoListNewContrato.setClienteIdcliente(cliente);
                    contratoListNewContrato = em.merge(contratoListNewContrato);
                    if (oldClienteIdclienteOfContratoListNewContrato != null && !oldClienteIdclienteOfContratoListNewContrato.equals(cliente)) {
                        oldClienteIdclienteOfContratoListNewContrato.getContratoList().remove(contratoListNewContrato);
                        oldClienteIdclienteOfContratoListNewContrato = em.merge(oldClienteIdclienteOfContratoListNewContrato);
                    }
                }
            }
            for (Fotocliente fotoclienteListNewFotocliente : fotoclienteListNew) {
                if (!fotoclienteListOld.contains(fotoclienteListNewFotocliente)) {
                    Cliente oldClienteIdclienteOfFotoclienteListNewFotocliente = fotoclienteListNewFotocliente.getClienteIdcliente();
                    fotoclienteListNewFotocliente.setClienteIdcliente(cliente);
                    fotoclienteListNewFotocliente = em.merge(fotoclienteListNewFotocliente);
                    if (oldClienteIdclienteOfFotoclienteListNewFotocliente != null && !oldClienteIdclienteOfFotoclienteListNewFotocliente.equals(cliente)) {
                        oldClienteIdclienteOfFotoclienteListNewFotocliente.getFotoclienteList().remove(fotoclienteListNewFotocliente);
                        oldClienteIdclienteOfFotoclienteListNewFotocliente = em.merge(oldClienteIdclienteOfFotoclienteListNewFotocliente);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cliente.getIdcliente();
                if (findCliente(id) == null) {
                    throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.");
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
            Cliente cliente;
            try {
                cliente = em.getReference(Cliente.class, id);
                cliente.getIdcliente();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Venta> ventaListOrphanCheck = cliente.getVentaList();
            for (Venta ventaListOrphanCheckVenta : ventaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cliente (" + cliente + ") cannot be destroyed since the Venta " + ventaListOrphanCheckVenta + " in its ventaList field has a non-nullable clienteIdcliente field.");
            }
            List<Contrato> contratoListOrphanCheck = cliente.getContratoList();
            for (Contrato contratoListOrphanCheckContrato : contratoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cliente (" + cliente + ") cannot be destroyed since the Contrato " + contratoListOrphanCheckContrato + " in its contratoList field has a non-nullable clienteIdcliente field.");
            }
            List<Fotocliente> fotoclienteListOrphanCheck = cliente.getFotoclienteList();
            for (Fotocliente fotoclienteListOrphanCheckFotocliente : fotoclienteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cliente (" + cliente + ") cannot be destroyed since the Fotocliente " + fotoclienteListOrphanCheckFotocliente + " in its fotoclienteList field has a non-nullable clienteIdcliente field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Institucionemite institucionemiteIdinstitucionemite = cliente.getInstitucionemiteIdinstitucionemite();
            if (institucionemiteIdinstitucionemite != null) {
                institucionemiteIdinstitucionemite.getClienteList().remove(cliente);
                institucionemiteIdinstitucionemite = em.merge(institucionemiteIdinstitucionemite);
            }
            Municipio municipioIdmunicipio = cliente.getMunicipioIdmunicipio();
            if (municipioIdmunicipio != null) {
                municipioIdmunicipio.getClienteList().remove(cliente);
                municipioIdmunicipio = em.merge(municipioIdmunicipio);
            }
            Ocupacion ocupacionIdocupacion = cliente.getOcupacionIdocupacion();
            if (ocupacionIdocupacion != null) {
                ocupacionIdocupacion.getClienteList().remove(cliente);
                ocupacionIdocupacion = em.merge(ocupacionIdocupacion);
            }
            Pais paisnacimiento = cliente.getPaisnacimiento();
            if (paisnacimiento != null) {
                paisnacimiento.getClienteList().remove(cliente);
                paisnacimiento = em.merge(paisnacimiento);
            }
            Tipoidentificacion tipoidentificacionIdtipoidentificacion = cliente.getTipoidentificacionIdtipoidentificacion();
            if (tipoidentificacionIdtipoidentificacion != null) {
                tipoidentificacionIdtipoidentificacion.getClienteList().remove(cliente);
                tipoidentificacionIdtipoidentificacion = em.merge(tipoidentificacionIdtipoidentificacion);
            }
            em.remove(cliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cliente> findClienteEntities() {
        return findClienteEntities(true, -1, -1);
    }

    public List<Cliente> findClienteEntities(int maxResults, int firstResult) {
        return findClienteEntities(false, maxResults, firstResult);
    }

    private List<Cliente> findClienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cliente.class));
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

    public Cliente findCliente(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cliente.class, id);
        } finally {
            em.close();
        }
    }

    public int getClienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cliente> rt = cq.from(Cliente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
