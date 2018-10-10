/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author erick
 */
@Entity
@Table(name = "movimiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Movimiento.findAll", query = "SELECT m FROM Movimiento m")
    , @NamedQuery(name = "Movimiento.findByIdmovimiento", query = "SELECT m FROM Movimiento m WHERE m.idmovimiento = :idmovimiento")
    , @NamedQuery(name = "Movimiento.findByIngreso", query = "SELECT m FROM Movimiento m WHERE m.ingreso = :ingreso")
    , @NamedQuery(name = "Movimiento.findByEgreso", query = "SELECT m FROM Movimiento m WHERE m.egreso = :egreso")})
public class Movimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmovimiento")
    private Integer idmovimiento;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ingreso")
    private Double ingreso;
    @Column(name = "egreso")
    private Double egreso;
    @JoinColumn(name = "concepto_idconcepto", referencedColumnName = "idconcepto")
    @ManyToOne(optional = false)
    private Concepto conceptoIdconcepto;
    @JoinColumn(name = "captura", referencedColumnName = "idmueve")
    @ManyToOne(optional = false)
    private Mueve captura;
    @JoinColumn(name = "modificacion", referencedColumnName = "idmueve")
    @ManyToOne(optional = false)
    private Mueve modificacion;
    @JoinColumn(name = "cancelacion", referencedColumnName = "idmueve")
    @ManyToOne(optional = false)
    private Mueve cancelacion;

    public Movimiento() {
    }

    public Movimiento(Integer idmovimiento) {
        this.idmovimiento = idmovimiento;
    }

    public Integer getIdmovimiento() {
        return idmovimiento;
    }

    public void setIdmovimiento(Integer idmovimiento) {
        this.idmovimiento = idmovimiento;
    }

    public Double getIngreso() {
        return ingreso;
    }

    public void setIngreso(Double ingreso) {
        this.ingreso = ingreso;
    }

    public Double getEgreso() {
        return egreso;
    }

    public void setEgreso(Double egreso) {
        this.egreso = egreso;
    }

    public Concepto getConceptoIdconcepto() {
        return conceptoIdconcepto;
    }

    public void setConceptoIdconcepto(Concepto conceptoIdconcepto) {
        this.conceptoIdconcepto = conceptoIdconcepto;
    }

    public Mueve getCaptura() {
        return captura;
    }

    public void setCaptura(Mueve captura) {
        this.captura = captura;
    }

    public Mueve getModificacion() {
        return modificacion;
    }

    public void setModificacion(Mueve modificacion) {
        this.modificacion = modificacion;
    }

    public Mueve getCancelacion() {
        return cancelacion;
    }

    public void setCancelacion(Mueve cancelacion) {
        this.cancelacion = cancelacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmovimiento != null ? idmovimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Movimiento)) {
            return false;
        }
        Movimiento other = (Movimiento) object;
        if ((this.idmovimiento == null && other.idmovimiento != null) || (this.idmovimiento != null && !this.idmovimiento.equals(other.idmovimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Movimiento[ idmovimiento=" + idmovimiento + " ]";
    }
    
}
