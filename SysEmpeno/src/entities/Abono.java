/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author erick
 */
@Entity
@Table(name = "abono")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Abono.findAll", query = "SELECT a FROM Abono a")
    , @NamedQuery(name = "Abono.findByIdAbono", query = "SELECT a FROM Abono a WHERE a.idAbono = :idAbono")
    , @NamedQuery(name = "Abono.findByAbono", query = "SELECT a FROM Abono a WHERE a.abono = :abono")
    , @NamedQuery(name = "Abono.findByFecha", query = "SELECT a FROM Abono a WHERE a.fecha = :fecha")})
public class Abono implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAbono")
    private Integer idAbono;
    @Basic(optional = false)
    @Column(name = "abono")
    private double abono;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinColumn(name = "concepto_idconcepto", referencedColumnName = "idconcepto")
    @ManyToOne(optional = false)
    private Concepto conceptoIdconcepto;
    @JoinColumn(name = "contrato_idcontrato", referencedColumnName = "idcontrato")
    @ManyToOne(optional = false)
    private Contrato contratoIdcontrato;

    public Abono() {
    }

    public Abono(Integer idAbono) {
        this.idAbono = idAbono;
    }

    public Abono(Integer idAbono, double abono, Date fecha) {
        this.idAbono = idAbono;
        this.abono = abono;
        this.fecha = fecha;
    }

    public Integer getIdAbono() {
        return idAbono;
    }

    public void setIdAbono(Integer idAbono) {
        this.idAbono = idAbono;
    }

    public double getAbono() {
        return abono;
    }

    public void setAbono(double abono) {
        this.abono = abono;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Concepto getConceptoIdconcepto() {
        return conceptoIdconcepto;
    }

    public void setConceptoIdconcepto(Concepto conceptoIdconcepto) {
        this.conceptoIdconcepto = conceptoIdconcepto;
    }

    public Contrato getContratoIdcontrato() {
        return contratoIdcontrato;
    }

    public void setContratoIdcontrato(Contrato contratoIdcontrato) {
        this.contratoIdcontrato = contratoIdcontrato;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAbono != null ? idAbono.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Abono)) {
            return false;
        }
        Abono other = (Abono) object;
        if ((this.idAbono == null && other.idAbono != null) || (this.idAbono != null && !this.idAbono.equals(other.idAbono))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Abono[ idAbono=" + idAbono + " ]";
    }
    
}
