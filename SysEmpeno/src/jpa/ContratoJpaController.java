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
import entities.Contrato;
import java.util.ArrayList;
import java.util.List;
import entities.Abono;
import entities.Fotoprenda;
import entities.Prenda;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;
import jpa.exceptions.PreexistingEntityException;

/**
 *
 * @author erick
 */
public class ContratoJpaController implements Serializable {

    public ContratoJpaController() {
        this.emf = Persistence.createEntityManagerFactory("SysEmpenoPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Contrato contrato) throws PreexistingEntityException, Exception {
        if (contrato.getContratoList() == null) {
            contrato.setContratoList(new ArrayList<Contrato>());
        }
        if (contrato.getContratoList1() == null) {
            contrato.setContratoList1(new ArrayList<Contrato>());
        }
        if (contrato.getContratoList2() == null) {
            contrato.setContratoList2(new ArrayList<Contrato>());
        }
        if (contrato.getContratoList3() == null) {
            contrato.setContratoList3(new ArrayList<Contrato>());
        }
        if (contrato.getAbonoList() == null) {
            contrato.setAbonoList(new ArrayList<Abono>());
        }
        if (contrato.getFotoprendaList() == null) {
            contrato.setFotoprendaList(new ArrayList<Fotoprenda>());
        }
        if (contrato.getPrendaList() == null) {
            contrato.setPrendaList(new ArrayList<Prenda>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente clienteIdcliente = contrato.getClienteIdcliente();
            if (clienteIdcliente != null) {
                clienteIdcliente = em.getReference(clienteIdcliente.getClass(), clienteIdcliente.getIdcliente());
                contrato.setClienteIdcliente(clienteIdcliente);
            }
            Contrato refrendoAnterior = contrato.getRefrendoAnterior();
            if (refrendoAnterior != null) {
                refrendoAnterior = em.getReference(refrendoAnterior.getClass(), refrendoAnterior.getIdcontrato());
                contrato.setRefrendoAnterior(refrendoAnterior);
            }
            Contrato refrendoPosterior = contrato.getRefrendoPosterior();
            if (refrendoPosterior != null) {
                refrendoPosterior = em.getReference(refrendoPosterior.getClass(), refrendoPosterior.getIdcontrato());
                contrato.setRefrendoPosterior(refrendoPosterior);
            }
            Contrato reempeñoAnterior = contrato.getReempeñoAnterior();
            if (reempeñoAnterior != null) {
                reempeñoAnterior = em.getReference(reempeñoAnterior.getClass(), reempeñoAnterior.getIdcontrato());
                contrato.setReempeñoAnterior(reempeñoAnterior);
            }
            Contrato reempeñoPosterior = contrato.getReempeñoPosterior();
            if (reempeñoPosterior != null) {
                reempeñoPosterior = em.getReference(reempeñoPosterior.getClass(), reempeñoPosterior.getIdcontrato());
                contrato.setReempeñoPosterior(reempeñoPosterior);
            }
            List<Contrato> attachedContratoList = new ArrayList<Contrato>();
            for (Contrato contratoListContratoToAttach : contrato.getContratoList()) {
                contratoListContratoToAttach = em.getReference(contratoListContratoToAttach.getClass(), contratoListContratoToAttach.getIdcontrato());
                attachedContratoList.add(contratoListContratoToAttach);
            }
            contrato.setContratoList(attachedContratoList);
            List<Contrato> attachedContratoList1 = new ArrayList<Contrato>();
            for (Contrato contratoList1ContratoToAttach : contrato.getContratoList1()) {
                contratoList1ContratoToAttach = em.getReference(contratoList1ContratoToAttach.getClass(), contratoList1ContratoToAttach.getIdcontrato());
                attachedContratoList1.add(contratoList1ContratoToAttach);
            }
            contrato.setContratoList1(attachedContratoList1);
            List<Contrato> attachedContratoList2 = new ArrayList<Contrato>();
            for (Contrato contratoList2ContratoToAttach : contrato.getContratoList2()) {
                contratoList2ContratoToAttach = em.getReference(contratoList2ContratoToAttach.getClass(), contratoList2ContratoToAttach.getIdcontrato());
                attachedContratoList2.add(contratoList2ContratoToAttach);
            }
            contrato.setContratoList2(attachedContratoList2);
            List<Contrato> attachedContratoList3 = new ArrayList<Contrato>();
            for (Contrato contratoList3ContratoToAttach : contrato.getContratoList3()) {
                contratoList3ContratoToAttach = em.getReference(contratoList3ContratoToAttach.getClass(), contratoList3ContratoToAttach.getIdcontrato());
                attachedContratoList3.add(contratoList3ContratoToAttach);
            }
            contrato.setContratoList3(attachedContratoList3);
            List<Abono> attachedAbonoList = new ArrayList<Abono>();
            for (Abono abonoListAbonoToAttach : contrato.getAbonoList()) {
                abonoListAbonoToAttach = em.getReference(abonoListAbonoToAttach.getClass(), abonoListAbonoToAttach.getIdAbono());
                attachedAbonoList.add(abonoListAbonoToAttach);
            }
            contrato.setAbonoList(attachedAbonoList);
            List<Fotoprenda> attachedFotoprendaList = new ArrayList<Fotoprenda>();
            for (Fotoprenda fotoprendaListFotoprendaToAttach : contrato.getFotoprendaList()) {
                fotoprendaListFotoprendaToAttach = em.getReference(fotoprendaListFotoprendaToAttach.getClass(), fotoprendaListFotoprendaToAttach.getIdfoto());
                attachedFotoprendaList.add(fotoprendaListFotoprendaToAttach);
            }
            contrato.setFotoprendaList(attachedFotoprendaList);
            List<Prenda> attachedPrendaList = new ArrayList<Prenda>();
            for (Prenda prendaListPrendaToAttach : contrato.getPrendaList()) {
                prendaListPrendaToAttach = em.getReference(prendaListPrendaToAttach.getClass(), prendaListPrendaToAttach.getIdprenda());
                attachedPrendaList.add(prendaListPrendaToAttach);
            }
            contrato.setPrendaList(attachedPrendaList);
            em.persist(contrato);
            if (clienteIdcliente != null) {
                clienteIdcliente.getContratoList().add(contrato);
                clienteIdcliente = em.merge(clienteIdcliente);
            }
            if (refrendoAnterior != null) {
                refrendoAnterior.getContratoList().add(contrato);
                refrendoAnterior = em.merge(refrendoAnterior);
            }
            if (refrendoPosterior != null) {
                refrendoPosterior.getContratoList().add(contrato);
                refrendoPosterior = em.merge(refrendoPosterior);
            }
            if (reempeñoAnterior != null) {
                reempeñoAnterior.getContratoList().add(contrato);
                reempeñoAnterior = em.merge(reempeñoAnterior);
            }
            if (reempeñoPosterior != null) {
                reempeñoPosterior.getContratoList().add(contrato);
                reempeñoPosterior = em.merge(reempeñoPosterior);
            }
            for (Contrato contratoListContrato : contrato.getContratoList()) {
                Contrato oldRefrendoAnteriorOfContratoListContrato = contratoListContrato.getRefrendoAnterior();
                contratoListContrato.setRefrendoAnterior(contrato);
                contratoListContrato = em.merge(contratoListContrato);
                if (oldRefrendoAnteriorOfContratoListContrato != null) {
                    oldRefrendoAnteriorOfContratoListContrato.getContratoList().remove(contratoListContrato);
                    oldRefrendoAnteriorOfContratoListContrato = em.merge(oldRefrendoAnteriorOfContratoListContrato);
                }
            }
            for (Contrato contratoList1Contrato : contrato.getContratoList1()) {
                Contrato oldRefrendoPosteriorOfContratoList1Contrato = contratoList1Contrato.getRefrendoPosterior();
                contratoList1Contrato.setRefrendoPosterior(contrato);
                contratoList1Contrato = em.merge(contratoList1Contrato);
                if (oldRefrendoPosteriorOfContratoList1Contrato != null) {
                    oldRefrendoPosteriorOfContratoList1Contrato.getContratoList1().remove(contratoList1Contrato);
                    oldRefrendoPosteriorOfContratoList1Contrato = em.merge(oldRefrendoPosteriorOfContratoList1Contrato);
                }
            }
            for (Contrato contratoList2Contrato : contrato.getContratoList2()) {
                Contrato oldReempeñoAnteriorOfContratoList2Contrato = contratoList2Contrato.getReempeñoAnterior();
                contratoList2Contrato.setReempeñoAnterior(contrato);
                contratoList2Contrato = em.merge(contratoList2Contrato);
                if (oldReempeñoAnteriorOfContratoList2Contrato != null) {
                    oldReempeñoAnteriorOfContratoList2Contrato.getContratoList2().remove(contratoList2Contrato);
                    oldReempeñoAnteriorOfContratoList2Contrato = em.merge(oldReempeñoAnteriorOfContratoList2Contrato);
                }
            }
            for (Contrato contratoList3Contrato : contrato.getContratoList3()) {
                Contrato oldReempeñoPosteriorOfContratoList3Contrato = contratoList3Contrato.getReempeñoPosterior();
                contratoList3Contrato.setReempeñoPosterior(contrato);
                contratoList3Contrato = em.merge(contratoList3Contrato);
                if (oldReempeñoPosteriorOfContratoList3Contrato != null) {
                    oldReempeñoPosteriorOfContratoList3Contrato.getContratoList3().remove(contratoList3Contrato);
                    oldReempeñoPosteriorOfContratoList3Contrato = em.merge(oldReempeñoPosteriorOfContratoList3Contrato);
                }
            }
            for (Abono abonoListAbono : contrato.getAbonoList()) {
                Contrato oldContratoIdcontratoOfAbonoListAbono = abonoListAbono.getContratoIdcontrato();
                abonoListAbono.setContratoIdcontrato(contrato);
                abonoListAbono = em.merge(abonoListAbono);
                if (oldContratoIdcontratoOfAbonoListAbono != null) {
                    oldContratoIdcontratoOfAbonoListAbono.getAbonoList().remove(abonoListAbono);
                    oldContratoIdcontratoOfAbonoListAbono = em.merge(oldContratoIdcontratoOfAbonoListAbono);
                }
            }
            for (Fotoprenda fotoprendaListFotoprenda : contrato.getFotoprendaList()) {
                Contrato oldContratoIdcontratoOfFotoprendaListFotoprenda = fotoprendaListFotoprenda.getContratoIdcontrato();
                fotoprendaListFotoprenda.setContratoIdcontrato(contrato);
                fotoprendaListFotoprenda = em.merge(fotoprendaListFotoprenda);
                if (oldContratoIdcontratoOfFotoprendaListFotoprenda != null) {
                    oldContratoIdcontratoOfFotoprendaListFotoprenda.getFotoprendaList().remove(fotoprendaListFotoprenda);
                    oldContratoIdcontratoOfFotoprendaListFotoprenda = em.merge(oldContratoIdcontratoOfFotoprendaListFotoprenda);
                }
            }
            for (Prenda prendaListPrenda : contrato.getPrendaList()) {
                Contrato oldContratoidContratoOfPrendaListPrenda = prendaListPrenda.getContratoidContrato();
                prendaListPrenda.setContratoidContrato(contrato);
                prendaListPrenda = em.merge(prendaListPrenda);
                if (oldContratoidContratoOfPrendaListPrenda != null) {
                    oldContratoidContratoOfPrendaListPrenda.getPrendaList().remove(prendaListPrenda);
                    oldContratoidContratoOfPrendaListPrenda = em.merge(oldContratoidContratoOfPrendaListPrenda);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findContrato(contrato.getIdcontrato()) != null) {
                throw new PreexistingEntityException("Contrato " + contrato + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Contrato contrato) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Contrato persistentContrato = em.find(Contrato.class, contrato.getIdcontrato());
            Cliente clienteIdclienteOld = persistentContrato.getClienteIdcliente();
            Cliente clienteIdclienteNew = contrato.getClienteIdcliente();
            Contrato refrendoAnteriorOld = persistentContrato.getRefrendoAnterior();
            Contrato refrendoAnteriorNew = contrato.getRefrendoAnterior();
            Contrato refrendoPosteriorOld = persistentContrato.getRefrendoPosterior();
            Contrato refrendoPosteriorNew = contrato.getRefrendoPosterior();
            Contrato reempeñoAnteriorOld = persistentContrato.getReempeñoAnterior();
            Contrato reempeñoAnteriorNew = contrato.getReempeñoAnterior();
            Contrato reempeñoPosteriorOld = persistentContrato.getReempeñoPosterior();
            Contrato reempeñoPosteriorNew = contrato.getReempeñoPosterior();
            List<Contrato> contratoListOld = persistentContrato.getContratoList();
            List<Contrato> contratoListNew = contrato.getContratoList();
            List<Contrato> contratoList1Old = persistentContrato.getContratoList1();
            List<Contrato> contratoList1New = contrato.getContratoList1();
            List<Contrato> contratoList2Old = persistentContrato.getContratoList2();
            List<Contrato> contratoList2New = contrato.getContratoList2();
            List<Contrato> contratoList3Old = persistentContrato.getContratoList3();
            List<Contrato> contratoList3New = contrato.getContratoList3();
            List<Abono> abonoListOld = persistentContrato.getAbonoList();
            List<Abono> abonoListNew = contrato.getAbonoList();
            List<Fotoprenda> fotoprendaListOld = persistentContrato.getFotoprendaList();
            List<Fotoprenda> fotoprendaListNew = contrato.getFotoprendaList();
            List<Prenda> prendaListOld = persistentContrato.getPrendaList();
            List<Prenda> prendaListNew = contrato.getPrendaList();
            List<String> illegalOrphanMessages = null;
            for (Abono abonoListOldAbono : abonoListOld) {
                if (!abonoListNew.contains(abonoListOldAbono)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Abono " + abonoListOldAbono + " since its contratoIdcontrato field is not nullable.");
                }
            }
            for (Fotoprenda fotoprendaListOldFotoprenda : fotoprendaListOld) {
                if (!fotoprendaListNew.contains(fotoprendaListOldFotoprenda)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Fotoprenda " + fotoprendaListOldFotoprenda + " since its contratoIdcontrato field is not nullable.");
                }
            }
            for (Prenda prendaListOldPrenda : prendaListOld) {
                if (!prendaListNew.contains(prendaListOldPrenda)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Prenda " + prendaListOldPrenda + " since its contratoidContrato field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (clienteIdclienteNew != null) {
                clienteIdclienteNew = em.getReference(clienteIdclienteNew.getClass(), clienteIdclienteNew.getIdcliente());
                contrato.setClienteIdcliente(clienteIdclienteNew);
            }
            if (refrendoAnteriorNew != null) {
                refrendoAnteriorNew = em.getReference(refrendoAnteriorNew.getClass(), refrendoAnteriorNew.getIdcontrato());
                contrato.setRefrendoAnterior(refrendoAnteriorNew);
            }
            if (refrendoPosteriorNew != null) {
                refrendoPosteriorNew = em.getReference(refrendoPosteriorNew.getClass(), refrendoPosteriorNew.getIdcontrato());
                contrato.setRefrendoPosterior(refrendoPosteriorNew);
            }
            if (reempeñoAnteriorNew != null) {
                reempeñoAnteriorNew = em.getReference(reempeñoAnteriorNew.getClass(), reempeñoAnteriorNew.getIdcontrato());
                contrato.setReempeñoAnterior(reempeñoAnteriorNew);
            }
            if (reempeñoPosteriorNew != null) {
                reempeñoPosteriorNew = em.getReference(reempeñoPosteriorNew.getClass(), reempeñoPosteriorNew.getIdcontrato());
                contrato.setReempeñoPosterior(reempeñoPosteriorNew);
            }
            List<Contrato> attachedContratoListNew = new ArrayList<Contrato>();
            for (Contrato contratoListNewContratoToAttach : contratoListNew) {
                contratoListNewContratoToAttach = em.getReference(contratoListNewContratoToAttach.getClass(), contratoListNewContratoToAttach.getIdcontrato());
                attachedContratoListNew.add(contratoListNewContratoToAttach);
            }
            contratoListNew = attachedContratoListNew;
            contrato.setContratoList(contratoListNew);
            List<Contrato> attachedContratoList1New = new ArrayList<Contrato>();
            for (Contrato contratoList1NewContratoToAttach : contratoList1New) {
                contratoList1NewContratoToAttach = em.getReference(contratoList1NewContratoToAttach.getClass(), contratoList1NewContratoToAttach.getIdcontrato());
                attachedContratoList1New.add(contratoList1NewContratoToAttach);
            }
            contratoList1New = attachedContratoList1New;
            contrato.setContratoList1(contratoList1New);
            List<Contrato> attachedContratoList2New = new ArrayList<Contrato>();
            for (Contrato contratoList2NewContratoToAttach : contratoList2New) {
                contratoList2NewContratoToAttach = em.getReference(contratoList2NewContratoToAttach.getClass(), contratoList2NewContratoToAttach.getIdcontrato());
                attachedContratoList2New.add(contratoList2NewContratoToAttach);
            }
            contratoList2New = attachedContratoList2New;
            contrato.setContratoList2(contratoList2New);
            List<Contrato> attachedContratoList3New = new ArrayList<Contrato>();
            for (Contrato contratoList3NewContratoToAttach : contratoList3New) {
                contratoList3NewContratoToAttach = em.getReference(contratoList3NewContratoToAttach.getClass(), contratoList3NewContratoToAttach.getIdcontrato());
                attachedContratoList3New.add(contratoList3NewContratoToAttach);
            }
            contratoList3New = attachedContratoList3New;
            contrato.setContratoList3(contratoList3New);
            List<Abono> attachedAbonoListNew = new ArrayList<Abono>();
            for (Abono abonoListNewAbonoToAttach : abonoListNew) {
                abonoListNewAbonoToAttach = em.getReference(abonoListNewAbonoToAttach.getClass(), abonoListNewAbonoToAttach.getIdAbono());
                attachedAbonoListNew.add(abonoListNewAbonoToAttach);
            }
            abonoListNew = attachedAbonoListNew;
            contrato.setAbonoList(abonoListNew);
            List<Fotoprenda> attachedFotoprendaListNew = new ArrayList<Fotoprenda>();
            for (Fotoprenda fotoprendaListNewFotoprendaToAttach : fotoprendaListNew) {
                fotoprendaListNewFotoprendaToAttach = em.getReference(fotoprendaListNewFotoprendaToAttach.getClass(), fotoprendaListNewFotoprendaToAttach.getIdfoto());
                attachedFotoprendaListNew.add(fotoprendaListNewFotoprendaToAttach);
            }
            fotoprendaListNew = attachedFotoprendaListNew;
            contrato.setFotoprendaList(fotoprendaListNew);
            List<Prenda> attachedPrendaListNew = new ArrayList<Prenda>();
            for (Prenda prendaListNewPrendaToAttach : prendaListNew) {
                prendaListNewPrendaToAttach = em.getReference(prendaListNewPrendaToAttach.getClass(), prendaListNewPrendaToAttach.getIdprenda());
                attachedPrendaListNew.add(prendaListNewPrendaToAttach);
            }
            prendaListNew = attachedPrendaListNew;
            contrato.setPrendaList(prendaListNew);
            contrato = em.merge(contrato);
            if (clienteIdclienteOld != null && !clienteIdclienteOld.equals(clienteIdclienteNew)) {
                clienteIdclienteOld.getContratoList().remove(contrato);
                clienteIdclienteOld = em.merge(clienteIdclienteOld);
            }
            if (clienteIdclienteNew != null && !clienteIdclienteNew.equals(clienteIdclienteOld)) {
                clienteIdclienteNew.getContratoList().add(contrato);
                clienteIdclienteNew = em.merge(clienteIdclienteNew);
            }
            if (refrendoAnteriorOld != null && !refrendoAnteriorOld.equals(refrendoAnteriorNew)) {
                refrendoAnteriorOld.getContratoList().remove(contrato);
                refrendoAnteriorOld = em.merge(refrendoAnteriorOld);
            }
            if (refrendoAnteriorNew != null && !refrendoAnteriorNew.equals(refrendoAnteriorOld)) {
                refrendoAnteriorNew.getContratoList().add(contrato);
                refrendoAnteriorNew = em.merge(refrendoAnteriorNew);
            }
            if (refrendoPosteriorOld != null && !refrendoPosteriorOld.equals(refrendoPosteriorNew)) {
                refrendoPosteriorOld.getContratoList().remove(contrato);
                refrendoPosteriorOld = em.merge(refrendoPosteriorOld);
            }
            if (refrendoPosteriorNew != null && !refrendoPosteriorNew.equals(refrendoPosteriorOld)) {
                refrendoPosteriorNew.getContratoList().add(contrato);
                refrendoPosteriorNew = em.merge(refrendoPosteriorNew);
            }
            if (reempeñoAnteriorOld != null && !reempeñoAnteriorOld.equals(reempeñoAnteriorNew)) {
                reempeñoAnteriorOld.getContratoList().remove(contrato);
                reempeñoAnteriorOld = em.merge(reempeñoAnteriorOld);
            }
            if (reempeñoAnteriorNew != null && !reempeñoAnteriorNew.equals(reempeñoAnteriorOld)) {
                reempeñoAnteriorNew.getContratoList().add(contrato);
                reempeñoAnteriorNew = em.merge(reempeñoAnteriorNew);
            }
            if (reempeñoPosteriorOld != null && !reempeñoPosteriorOld.equals(reempeñoPosteriorNew)) {
                reempeñoPosteriorOld.getContratoList().remove(contrato);
                reempeñoPosteriorOld = em.merge(reempeñoPosteriorOld);
            }
            if (reempeñoPosteriorNew != null && !reempeñoPosteriorNew.equals(reempeñoPosteriorOld)) {
                reempeñoPosteriorNew.getContratoList().add(contrato);
                reempeñoPosteriorNew = em.merge(reempeñoPosteriorNew);
            }
            for (Contrato contratoListOldContrato : contratoListOld) {
                if (!contratoListNew.contains(contratoListOldContrato)) {
                    contratoListOldContrato.setRefrendoAnterior(null);
                    contratoListOldContrato = em.merge(contratoListOldContrato);
                }
            }
            for (Contrato contratoListNewContrato : contratoListNew) {
                if (!contratoListOld.contains(contratoListNewContrato)) {
                    Contrato oldRefrendoAnteriorOfContratoListNewContrato = contratoListNewContrato.getRefrendoAnterior();
                    contratoListNewContrato.setRefrendoAnterior(contrato);
                    contratoListNewContrato = em.merge(contratoListNewContrato);
                    if (oldRefrendoAnteriorOfContratoListNewContrato != null && !oldRefrendoAnteriorOfContratoListNewContrato.equals(contrato)) {
                        oldRefrendoAnteriorOfContratoListNewContrato.getContratoList().remove(contratoListNewContrato);
                        oldRefrendoAnteriorOfContratoListNewContrato = em.merge(oldRefrendoAnteriorOfContratoListNewContrato);
                    }
                }
            }
            for (Contrato contratoList1OldContrato : contratoList1Old) {
                if (!contratoList1New.contains(contratoList1OldContrato)) {
                    contratoList1OldContrato.setRefrendoPosterior(null);
                    contratoList1OldContrato = em.merge(contratoList1OldContrato);
                }
            }
            for (Contrato contratoList1NewContrato : contratoList1New) {
                if (!contratoList1Old.contains(contratoList1NewContrato)) {
                    Contrato oldRefrendoPosteriorOfContratoList1NewContrato = contratoList1NewContrato.getRefrendoPosterior();
                    contratoList1NewContrato.setRefrendoPosterior(contrato);
                    contratoList1NewContrato = em.merge(contratoList1NewContrato);
                    if (oldRefrendoPosteriorOfContratoList1NewContrato != null && !oldRefrendoPosteriorOfContratoList1NewContrato.equals(contrato)) {
                        oldRefrendoPosteriorOfContratoList1NewContrato.getContratoList1().remove(contratoList1NewContrato);
                        oldRefrendoPosteriorOfContratoList1NewContrato = em.merge(oldRefrendoPosteriorOfContratoList1NewContrato);
                    }
                }
            }
            for (Contrato contratoList2OldContrato : contratoList2Old) {
                if (!contratoList2New.contains(contratoList2OldContrato)) {
                    contratoList2OldContrato.setReempeñoAnterior(null);
                    contratoList2OldContrato = em.merge(contratoList2OldContrato);
                }
            }
            for (Contrato contratoList2NewContrato : contratoList2New) {
                if (!contratoList2Old.contains(contratoList2NewContrato)) {
                    Contrato oldReempeñoAnteriorOfContratoList2NewContrato = contratoList2NewContrato.getReempeñoAnterior();
                    contratoList2NewContrato.setReempeñoAnterior(contrato);
                    contratoList2NewContrato = em.merge(contratoList2NewContrato);
                    if (oldReempeñoAnteriorOfContratoList2NewContrato != null && !oldReempeñoAnteriorOfContratoList2NewContrato.equals(contrato)) {
                        oldReempeñoAnteriorOfContratoList2NewContrato.getContratoList2().remove(contratoList2NewContrato);
                        oldReempeñoAnteriorOfContratoList2NewContrato = em.merge(oldReempeñoAnteriorOfContratoList2NewContrato);
                    }
                }
            }
            for (Contrato contratoList3OldContrato : contratoList3Old) {
                if (!contratoList3New.contains(contratoList3OldContrato)) {
                    contratoList3OldContrato.setReempeñoPosterior(null);
                    contratoList3OldContrato = em.merge(contratoList3OldContrato);
                }
            }
            for (Contrato contratoList3NewContrato : contratoList3New) {
                if (!contratoList3Old.contains(contratoList3NewContrato)) {
                    Contrato oldReempeñoPosteriorOfContratoList3NewContrato = contratoList3NewContrato.getReempeñoPosterior();
                    contratoList3NewContrato.setReempeñoPosterior(contrato);
                    contratoList3NewContrato = em.merge(contratoList3NewContrato);
                    if (oldReempeñoPosteriorOfContratoList3NewContrato != null && !oldReempeñoPosteriorOfContratoList3NewContrato.equals(contrato)) {
                        oldReempeñoPosteriorOfContratoList3NewContrato.getContratoList3().remove(contratoList3NewContrato);
                        oldReempeñoPosteriorOfContratoList3NewContrato = em.merge(oldReempeñoPosteriorOfContratoList3NewContrato);
                    }
                }
            }
            for (Abono abonoListNewAbono : abonoListNew) {
                if (!abonoListOld.contains(abonoListNewAbono)) {
                    Contrato oldContratoIdcontratoOfAbonoListNewAbono = abonoListNewAbono.getContratoIdcontrato();
                    abonoListNewAbono.setContratoIdcontrato(contrato);
                    abonoListNewAbono = em.merge(abonoListNewAbono);
                    if (oldContratoIdcontratoOfAbonoListNewAbono != null && !oldContratoIdcontratoOfAbonoListNewAbono.equals(contrato)) {
                        oldContratoIdcontratoOfAbonoListNewAbono.getAbonoList().remove(abonoListNewAbono);
                        oldContratoIdcontratoOfAbonoListNewAbono = em.merge(oldContratoIdcontratoOfAbonoListNewAbono);
                    }
                }
            }
            for (Fotoprenda fotoprendaListNewFotoprenda : fotoprendaListNew) {
                if (!fotoprendaListOld.contains(fotoprendaListNewFotoprenda)) {
                    Contrato oldContratoIdcontratoOfFotoprendaListNewFotoprenda = fotoprendaListNewFotoprenda.getContratoIdcontrato();
                    fotoprendaListNewFotoprenda.setContratoIdcontrato(contrato);
                    fotoprendaListNewFotoprenda = em.merge(fotoprendaListNewFotoprenda);
                    if (oldContratoIdcontratoOfFotoprendaListNewFotoprenda != null && !oldContratoIdcontratoOfFotoprendaListNewFotoprenda.equals(contrato)) {
                        oldContratoIdcontratoOfFotoprendaListNewFotoprenda.getFotoprendaList().remove(fotoprendaListNewFotoprenda);
                        oldContratoIdcontratoOfFotoprendaListNewFotoprenda = em.merge(oldContratoIdcontratoOfFotoprendaListNewFotoprenda);
                    }
                }
            }
            for (Prenda prendaListNewPrenda : prendaListNew) {
                if (!prendaListOld.contains(prendaListNewPrenda)) {
                    Contrato oldContratoidContratoOfPrendaListNewPrenda = prendaListNewPrenda.getContratoidContrato();
                    prendaListNewPrenda.setContratoidContrato(contrato);
                    prendaListNewPrenda = em.merge(prendaListNewPrenda);
                    if (oldContratoidContratoOfPrendaListNewPrenda != null && !oldContratoidContratoOfPrendaListNewPrenda.equals(contrato)) {
                        oldContratoidContratoOfPrendaListNewPrenda.getPrendaList().remove(prendaListNewPrenda);
                        oldContratoidContratoOfPrendaListNewPrenda = em.merge(oldContratoidContratoOfPrendaListNewPrenda);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = contrato.getIdcontrato();
                if (findContrato(id) == null) {
                    throw new NonexistentEntityException("The contrato with id " + id + " no longer exists.");
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
            Contrato contrato;
            try {
                contrato = em.getReference(Contrato.class, id);
                contrato.getIdcontrato();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The contrato with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Abono> abonoListOrphanCheck = contrato.getAbonoList();
            for (Abono abonoListOrphanCheckAbono : abonoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Contrato (" + contrato + ") cannot be destroyed since the Abono " + abonoListOrphanCheckAbono + " in its abonoList field has a non-nullable contratoIdcontrato field.");
            }
            List<Fotoprenda> fotoprendaListOrphanCheck = contrato.getFotoprendaList();
            for (Fotoprenda fotoprendaListOrphanCheckFotoprenda : fotoprendaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Contrato (" + contrato + ") cannot be destroyed since the Fotoprenda " + fotoprendaListOrphanCheckFotoprenda + " in its fotoprendaList field has a non-nullable contratoIdcontrato field.");
            }
            List<Prenda> prendaListOrphanCheck = contrato.getPrendaList();
            for (Prenda prendaListOrphanCheckPrenda : prendaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Contrato (" + contrato + ") cannot be destroyed since the Prenda " + prendaListOrphanCheckPrenda + " in its prendaList field has a non-nullable contratoidContrato field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Cliente clienteIdcliente = contrato.getClienteIdcliente();
            if (clienteIdcliente != null) {
                clienteIdcliente.getContratoList().remove(contrato);
                clienteIdcliente = em.merge(clienteIdcliente);
            }
            Contrato refrendoAnterior = contrato.getRefrendoAnterior();
            if (refrendoAnterior != null) {
                refrendoAnterior.getContratoList().remove(contrato);
                refrendoAnterior = em.merge(refrendoAnterior);
            }
            Contrato refrendoPosterior = contrato.getRefrendoPosterior();
            if (refrendoPosterior != null) {
                refrendoPosterior.getContratoList().remove(contrato);
                refrendoPosterior = em.merge(refrendoPosterior);
            }
            Contrato reempeñoAnterior = contrato.getReempeñoAnterior();
            if (reempeñoAnterior != null) {
                reempeñoAnterior.getContratoList().remove(contrato);
                reempeñoAnterior = em.merge(reempeñoAnterior);
            }
            Contrato reempeñoPosterior = contrato.getReempeñoPosterior();
            if (reempeñoPosterior != null) {
                reempeñoPosterior.getContratoList().remove(contrato);
                reempeñoPosterior = em.merge(reempeñoPosterior);
            }
            List<Contrato> contratoList = contrato.getContratoList();
            for (Contrato contratoListContrato : contratoList) {
                contratoListContrato.setRefrendoAnterior(null);
                contratoListContrato = em.merge(contratoListContrato);
            }
            List<Contrato> contratoList1 = contrato.getContratoList1();
            for (Contrato contratoList1Contrato : contratoList1) {
                contratoList1Contrato.setRefrendoPosterior(null);
                contratoList1Contrato = em.merge(contratoList1Contrato);
            }
            List<Contrato> contratoList2 = contrato.getContratoList2();
            for (Contrato contratoList2Contrato : contratoList2) {
                contratoList2Contrato.setReempeñoAnterior(null);
                contratoList2Contrato = em.merge(contratoList2Contrato);
            }
            List<Contrato> contratoList3 = contrato.getContratoList3();
            for (Contrato contratoList3Contrato : contratoList3) {
                contratoList3Contrato.setReempeñoPosterior(null);
                contratoList3Contrato = em.merge(contratoList3Contrato);
            }
            em.remove(contrato);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Contrato> findContratoEntities() {
        return findContratoEntities(true, -1, -1);
    }

    public List<Contrato> findContratoEntities(int maxResults, int firstResult) {
        return findContratoEntities(false, maxResults, firstResult);
    }

    private List<Contrato> findContratoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Contrato.class));
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

    public Contrato findContrato(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Contrato.class, id);
        } finally {
            em.close();
        }
    }

    public int getContratoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Contrato> rt = cq.from(Contrato.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
