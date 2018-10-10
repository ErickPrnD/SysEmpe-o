/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author erick
 */
@Entity
@Table(name = "concepto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Concepto.findAll", query = "SELECT c FROM Concepto c")
    , @NamedQuery(name = "Concepto.findByIdconcepto", query = "SELECT c FROM Concepto c WHERE c.idconcepto = :idconcepto")
    , @NamedQuery(name = "Concepto.findByConcepto", query = "SELECT c FROM Concepto c WHERE c.concepto = :concepto")})
public class Concepto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idconcepto")
    private Integer idconcepto;
    @Basic(optional = false)
    @Column(name = "concepto")
    private String concepto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "conceptoIdconcepto")
    private List<Movimiento> movimientoList;

    public Concepto() {
    }

    public Concepto(Integer idconcepto) {
        this.idconcepto = idconcepto;
    }

    public Concepto(Integer idconcepto, String concepto) {
        this.idconcepto = idconcepto;
        this.concepto = concepto;
    }

    public Integer getIdconcepto() {
        return idconcepto;
    }

    public void setIdconcepto(Integer idconcepto) {
        this.idconcepto = idconcepto;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    @XmlTransient
    public List<Movimiento> getMovimientoList() {
        return movimientoList;
    }

    public void setMovimientoList(List<Movimiento> movimientoList) {
        this.movimientoList = movimientoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idconcepto != null ? idconcepto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Concepto)) {
            return false;
        }
        Concepto other = (Concepto) object;
        if ((this.idconcepto == null && other.idconcepto != null) || (this.idconcepto != null && !this.idconcepto.equals(other.idconcepto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Concepto[ idconcepto=" + idconcepto + " ]";
    }
    
}
