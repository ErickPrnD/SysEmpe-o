/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author erick
 */
@Entity
@Table(name = "contrato")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contrato.findAll", query = "SELECT c FROM Contrato c")
    , @NamedQuery(name = "Contrato.findByIdcontrato", query = "SELECT c FROM Contrato c WHERE c.idcontrato = :idcontrato")
    , @NamedQuery(name = "Contrato.findByCotitular", query = "SELECT c FROM Contrato c WHERE c.cotitular = :cotitular")
    , @NamedQuery(name = "Contrato.findByBeneficiario", query = "SELECT c FROM Contrato c WHERE c.beneficiario = :beneficiario")
    , @NamedQuery(name = "Contrato.findByObservacionesEmpe\u00f1o", query = "SELECT c FROM Contrato c WHERE c.observacionesEmpe\u00f1o = :observacionesEmpe\u00f1o")
    , @NamedQuery(name = "Contrato.findByFecha", query = "SELECT c FROM Contrato c WHERE c.fecha = :fecha")
    , @NamedQuery(name = "Contrato.findByObservacionesCliente", query = "SELECT c FROM Contrato c WHERE c.observacionesCliente = :observacionesCliente")
    , @NamedQuery(name = "Contrato.findByNumBolsa", query = "SELECT c FROM Contrato c WHERE c.numBolsa = :numBolsa")
    , @NamedQuery(name = "Contrato.findByFechaLimite", query = "SELECT c FROM Contrato c WHERE c.fechaLimite = :fechaLimite")
    , @NamedQuery(name = "Contrato.findByFechaEspera", query = "SELECT c FROM Contrato c WHERE c.fechaEspera = :fechaEspera")
    , @NamedQuery(name = "Contrato.findByFechaComercializacion", query = "SELECT c FROM Contrato c WHERE c.fechaComercializacion = :fechaComercializacion")
    , @NamedQuery(name = "Contrato.findByDesempe\u00f1a", query = "SELECT c FROM Contrato c WHERE c.desempe\u00f1a = :desempe\u00f1a")
    , @NamedQuery(name = "Contrato.findByAbono", query = "SELECT c FROM Contrato c WHERE c.abono = :abono")
    , @NamedQuery(name = "Contrato.findByIdentificacionCotitular", query = "SELECT c FROM Contrato c WHERE c.identificacionCotitular = :identificacionCotitular")
    , @NamedQuery(name = "Contrato.findByNombreDesempe\u00f1o", query = "SELECT c FROM Contrato c WHERE c.nombreDesempe\u00f1o = :nombreDesempe\u00f1o")
    , @NamedQuery(name = "Contrato.findByNumRefrendos", query = "SELECT c FROM Contrato c WHERE c.numRefrendos = :numRefrendos")
    , @NamedQuery(name = "Contrato.findByDiasRefrendo", query = "SELECT c FROM Contrato c WHERE c.diasRefrendo = :diasRefrendo")
    , @NamedQuery(name = "Contrato.findByDiasDesempe\u00f1o", query = "SELECT c FROM Contrato c WHERE c.diasDesempe\u00f1o = :diasDesempe\u00f1o")
    , @NamedQuery(name = "Contrato.findByAcumulado", query = "SELECT c FROM Contrato c WHERE c.acumulado = :acumulado")
    , @NamedQuery(name = "Contrato.findByIdentificacionDesempe\u00f1o", query = "SELECT c FROM Contrato c WHERE c.identificacionDesempe\u00f1o = :identificacionDesempe\u00f1o")
    , @NamedQuery(name = "Contrato.findByNumIdentificacionDesempe\u00f1o", query = "SELECT c FROM Contrato c WHERE c.numIdentificacionDesempe\u00f1o = :numIdentificacionDesempe\u00f1o")
    , @NamedQuery(name = "Contrato.findByComerciado", query = "SELECT c FROM Contrato c WHERE c.comerciado = :comerciado")})
public class Contrato implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idcontrato")
    private Integer idcontrato;
    @Column(name = "cotitular")
    private String cotitular;
    @Column(name = "beneficiario")
    private String beneficiario;
    @Column(name = "observacionesEmpe\u00f1o")
    private String observacionesEmpeño;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "observacionesCliente")
    private String observacionesCliente;
    @Basic(optional = false)
    @Column(name = "numBolsa")
    private int numBolsa;
    @Basic(optional = false)
    @Column(name = "fechaLimite")
    @Temporal(TemporalType.DATE)
    private Date fechaLimite;
    @Basic(optional = false)
    @Column(name = "fechaEspera")
    @Temporal(TemporalType.DATE)
    private Date fechaEspera;
    @Basic(optional = false)
    @Column(name = "fechaComercializacion")
    @Temporal(TemporalType.DATE)
    private Date fechaComercializacion;
    @Column(name = "desempe\u00f1a")
    private String desempeña;
    @Basic(optional = false)
    @Column(name = "abono")
    private double abono;
    @Column(name = "identificacionCotitular")
    private String identificacionCotitular;
    @Column(name = "nombreDesempe\u00f1o")
    private String nombreDesempeño;
    @Basic(optional = false)
    @Column(name = "numRefrendos")
    private int numRefrendos;
    @Basic(optional = false)
    @Column(name = "diasRefrendo")
    private int diasRefrendo;
    @Basic(optional = false)
    @Column(name = "diasDesempe\u00f1o")
    private int diasDesempeño;
    @Basic(optional = false)
    @Column(name = "acumulado")
    private double acumulado;
    @Column(name = "identificacionDesempe\u00f1o")
    private String identificacionDesempeño;
    @Column(name = "numIdentificacionDesempe\u00f1o")
    private String numIdentificacionDesempeño;
    @Column(name = "comerciado")
    @Temporal(TemporalType.DATE)
    private Date comerciado;
    @JoinColumn(name = "cliente_idcliente", referencedColumnName = "idcliente")
    @ManyToOne(optional = false)
    private Cliente clienteIdcliente;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "refrendoAnterior")
    private List<Contrato> contratoList;
    @JoinColumn(name = "refrendoAnterior", referencedColumnName = "idcontrato")
    @ManyToOne(optional = false)
    private Contrato refrendoAnterior;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "refrendoPosterior")
    private List<Contrato> contratoList1;
    @JoinColumn(name = "refrendoPosterior", referencedColumnName = "idcontrato")
    @ManyToOne(optional = false)
    private Contrato refrendoPosterior;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reempe\u00f1oAnterior")
    private List<Contrato> contratoList2;
    @JoinColumn(name = "reempe\u00f1oAnterior", referencedColumnName = "idcontrato")
    @ManyToOne(optional = false)
    private Contrato reempeñoAnterior;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reempe\u00f1oPosterior")
    private List<Contrato> contratoList3;
    @JoinColumn(name = "reempe\u00f1oPosterior", referencedColumnName = "idcontrato")
    @ManyToOne(optional = false)
    private Contrato reempeñoPosterior;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contratoIdcontrato")
    private List<Fotoprenda> fotoprendaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contratoidContrato")
    private List<Prenda> prendaList;

    public Contrato() {
    }

    public Contrato(Integer idcontrato) {
        this.idcontrato = idcontrato;
    }

    public Contrato(Integer idcontrato, Date fecha, int numBolsa, Date fechaLimite, Date fechaEspera, Date fechaComercializacion, double abono, int numRefrendos, int diasRefrendo, int diasDesempeño, double acumulado) {
        this.idcontrato = idcontrato;
        this.fecha = fecha;
        this.numBolsa = numBolsa;
        this.fechaLimite = fechaLimite;
        this.fechaEspera = fechaEspera;
        this.fechaComercializacion = fechaComercializacion;
        this.abono = abono;
        this.numRefrendos = numRefrendos;
        this.diasRefrendo = diasRefrendo;
        this.diasDesempeño = diasDesempeño;
        this.acumulado = acumulado;
    }

    public Integer getIdcontrato() {
        return idcontrato;
    }

    public void setIdcontrato(Integer idcontrato) {
        this.idcontrato = idcontrato;
    }

    public String getCotitular() {
        return cotitular;
    }

    public void setCotitular(String cotitular) {
        this.cotitular = cotitular;
    }

    public String getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(String beneficiario) {
        this.beneficiario = beneficiario;
    }

    public String getObservacionesEmpeño() {
        return observacionesEmpeño;
    }

    public void setObservacionesEmpeño(String observacionesEmpeño) {
        this.observacionesEmpeño = observacionesEmpeño;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getObservacionesCliente() {
        return observacionesCliente;
    }

    public void setObservacionesCliente(String observacionesCliente) {
        this.observacionesCliente = observacionesCliente;
    }

    public int getNumBolsa() {
        return numBolsa;
    }

    public void setNumBolsa(int numBolsa) {
        this.numBolsa = numBolsa;
    }

    public Date getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(Date fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public Date getFechaEspera() {
        return fechaEspera;
    }

    public void setFechaEspera(Date fechaEspera) {
        this.fechaEspera = fechaEspera;
    }

    public Date getFechaComercializacion() {
        return fechaComercializacion;
    }

    public void setFechaComercializacion(Date fechaComercializacion) {
        this.fechaComercializacion = fechaComercializacion;
    }

    public String getDesempeña() {
        return desempeña;
    }

    public void setDesempeña(String desempeña) {
        this.desempeña = desempeña;
    }

    public double getAbono() {
        return abono;
    }

    public void setAbono(double abono) {
        this.abono = abono;
    }

    public String getIdentificacionCotitular() {
        return identificacionCotitular;
    }

    public void setIdentificacionCotitular(String identificacionCotitular) {
        this.identificacionCotitular = identificacionCotitular;
    }

    public String getNombreDesempeño() {
        return nombreDesempeño;
    }

    public void setNombreDesempeño(String nombreDesempeño) {
        this.nombreDesempeño = nombreDesempeño;
    }

    public int getNumRefrendos() {
        return numRefrendos;
    }

    public void setNumRefrendos(int numRefrendos) {
        this.numRefrendos = numRefrendos;
    }

    public int getDiasRefrendo() {
        return diasRefrendo;
    }

    public void setDiasRefrendo(int diasRefrendo) {
        this.diasRefrendo = diasRefrendo;
    }

    public int getDiasDesempeño() {
        return diasDesempeño;
    }

    public void setDiasDesempeño(int diasDesempeño) {
        this.diasDesempeño = diasDesempeño;
    }

    public double getAcumulado() {
        return acumulado;
    }

    public void setAcumulado(double acumulado) {
        this.acumulado = acumulado;
    }

    public String getIdentificacionDesempeño() {
        return identificacionDesempeño;
    }

    public void setIdentificacionDesempeño(String identificacionDesempeño) {
        this.identificacionDesempeño = identificacionDesempeño;
    }

    public String getNumIdentificacionDesempeño() {
        return numIdentificacionDesempeño;
    }

    public void setNumIdentificacionDesempeño(String numIdentificacionDesempeño) {
        this.numIdentificacionDesempeño = numIdentificacionDesempeño;
    }

    public Date getComerciado() {
        return comerciado;
    }

    public void setComerciado(Date comerciado) {
        this.comerciado = comerciado;
    }

    public Cliente getClienteIdcliente() {
        return clienteIdcliente;
    }

    public void setClienteIdcliente(Cliente clienteIdcliente) {
        this.clienteIdcliente = clienteIdcliente;
    }

    @XmlTransient
    public List<Contrato> getContratoList() {
        return contratoList;
    }

    public void setContratoList(List<Contrato> contratoList) {
        this.contratoList = contratoList;
    }

    public Contrato getRefrendoAnterior() {
        return refrendoAnterior;
    }

    public void setRefrendoAnterior(Contrato refrendoAnterior) {
        this.refrendoAnterior = refrendoAnterior;
    }

    @XmlTransient
    public List<Contrato> getContratoList1() {
        return contratoList1;
    }

    public void setContratoList1(List<Contrato> contratoList1) {
        this.contratoList1 = contratoList1;
    }

    public Contrato getRefrendoPosterior() {
        return refrendoPosterior;
    }

    public void setRefrendoPosterior(Contrato refrendoPosterior) {
        this.refrendoPosterior = refrendoPosterior;
    }

    @XmlTransient
    public List<Contrato> getContratoList2() {
        return contratoList2;
    }

    public void setContratoList2(List<Contrato> contratoList2) {
        this.contratoList2 = contratoList2;
    }

    public Contrato getReempeñoAnterior() {
        return reempeñoAnterior;
    }

    public void setReempeñoAnterior(Contrato reempeñoAnterior) {
        this.reempeñoAnterior = reempeñoAnterior;
    }

    @XmlTransient
    public List<Contrato> getContratoList3() {
        return contratoList3;
    }

    public void setContratoList3(List<Contrato> contratoList3) {
        this.contratoList3 = contratoList3;
    }

    public Contrato getReempeñoPosterior() {
        return reempeñoPosterior;
    }

    public void setReempeñoPosterior(Contrato reempeñoPosterior) {
        this.reempeñoPosterior = reempeñoPosterior;
    }

    @XmlTransient
    public List<Fotoprenda> getFotoprendaList() {
        return fotoprendaList;
    }

    public void setFotoprendaList(List<Fotoprenda> fotoprendaList) {
        this.fotoprendaList = fotoprendaList;
    }

    @XmlTransient
    public List<Prenda> getPrendaList() {
        return prendaList;
    }

    public void setPrendaList(List<Prenda> prendaList) {
        this.prendaList = prendaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcontrato != null ? idcontrato.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contrato)) {
            return false;
        }
        Contrato other = (Contrato) object;
        if ((this.idcontrato == null && other.idcontrato != null) || (this.idcontrato != null && !this.idcontrato.equals(other.idcontrato))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Contrato[ idcontrato=" + idcontrato + " ]";
    }
    
}
