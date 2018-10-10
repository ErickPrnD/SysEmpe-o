/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "corte")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Corte.findAll", query = "SELECT c FROM Corte c")
    , @NamedQuery(name = "Corte.findByIdCorte", query = "SELECT c FROM Corte c WHERE c.idCorte = :idCorte")
    , @NamedQuery(name = "Corte.findByFecha", query = "SELECT c FROM Corte c WHERE c.fecha = :fecha")
    , @NamedQuery(name = "Corte.findByBillete500", query = "SELECT c FROM Corte c WHERE c.billete500 = :billete500")
    , @NamedQuery(name = "Corte.findByBillete200", query = "SELECT c FROM Corte c WHERE c.billete200 = :billete200")
    , @NamedQuery(name = "Corte.findByBillete100", query = "SELECT c FROM Corte c WHERE c.billete100 = :billete100")
    , @NamedQuery(name = "Corte.findByBillete50", query = "SELECT c FROM Corte c WHERE c.billete50 = :billete50")
    , @NamedQuery(name = "Corte.findByBillete20", query = "SELECT c FROM Corte c WHERE c.billete20 = :billete20")
    , @NamedQuery(name = "Corte.findByMoneda10", query = "SELECT c FROM Corte c WHERE c.moneda10 = :moneda10")
    , @NamedQuery(name = "Corte.findByMoneda5", query = "SELECT c FROM Corte c WHERE c.moneda5 = :moneda5")
    , @NamedQuery(name = "Corte.findByMoneda2", query = "SELECT c FROM Corte c WHERE c.moneda2 = :moneda2")
    , @NamedQuery(name = "Corte.findByMorralla", query = "SELECT c FROM Corte c WHERE c.morralla = :morralla")
    , @NamedQuery(name = "Corte.findByCheques", query = "SELECT c FROM Corte c WHERE c.cheques = :cheques")
    , @NamedQuery(name = "Corte.findByTransferencias", query = "SELECT c FROM Corte c WHERE c.transferencias = :transferencias")})
public class Corte implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCorte")
    private Integer idCorte;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "billete500")
    private Integer billete500;
    @Column(name = "billete200")
    private Integer billete200;
    @Column(name = "billete100")
    private Integer billete100;
    @Column(name = "billete50")
    private Integer billete50;
    @Column(name = "billete20")
    private Integer billete20;
    @Column(name = "moneda10")
    private Integer moneda10;
    @Column(name = "moneda5")
    private Integer moneda5;
    @Column(name = "moneda2")
    private Integer moneda2;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "morralla")
    private Double morralla;
    @Column(name = "cheques")
    private Double cheques;
    @Column(name = "transferencias")
    private Double transferencias;

    public Corte() {
    }

    public Corte(Integer idCorte) {
        this.idCorte = idCorte;
    }

    public Corte(Integer idCorte, Date fecha) {
        this.idCorte = idCorte;
        this.fecha = fecha;
    }

    public Integer getIdCorte() {
        return idCorte;
    }

    public void setIdCorte(Integer idCorte) {
        this.idCorte = idCorte;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getBillete500() {
        return billete500;
    }

    public void setBillete500(Integer billete500) {
        this.billete500 = billete500;
    }

    public Integer getBillete200() {
        return billete200;
    }

    public void setBillete200(Integer billete200) {
        this.billete200 = billete200;
    }

    public Integer getBillete100() {
        return billete100;
    }

    public void setBillete100(Integer billete100) {
        this.billete100 = billete100;
    }

    public Integer getBillete50() {
        return billete50;
    }

    public void setBillete50(Integer billete50) {
        this.billete50 = billete50;
    }

    public Integer getBillete20() {
        return billete20;
    }

    public void setBillete20(Integer billete20) {
        this.billete20 = billete20;
    }

    public Integer getMoneda10() {
        return moneda10;
    }

    public void setMoneda10(Integer moneda10) {
        this.moneda10 = moneda10;
    }

    public Integer getMoneda5() {
        return moneda5;
    }

    public void setMoneda5(Integer moneda5) {
        this.moneda5 = moneda5;
    }

    public Integer getMoneda2() {
        return moneda2;
    }

    public void setMoneda2(Integer moneda2) {
        this.moneda2 = moneda2;
    }

    public Double getMorralla() {
        return morralla;
    }

    public void setMorralla(Double morralla) {
        this.morralla = morralla;
    }

    public Double getCheques() {
        return cheques;
    }

    public void setCheques(Double cheques) {
        this.cheques = cheques;
    }

    public Double getTransferencias() {
        return transferencias;
    }

    public void setTransferencias(Double transferencias) {
        this.transferencias = transferencias;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCorte != null ? idCorte.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Corte)) {
            return false;
        }
        Corte other = (Corte) object;
        if ((this.idCorte == null && other.idCorte != null) || (this.idCorte != null && !this.idCorte.equals(other.idCorte))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Corte[ idCorte=" + idCorte + " ]";
    }
    
}
