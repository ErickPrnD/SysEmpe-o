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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "mueve")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mueve.findAll", query = "SELECT m FROM Mueve m")
    , @NamedQuery(name = "Mueve.findByIdmueve", query = "SELECT m FROM Mueve m WHERE m.idmueve = :idmueve")
    , @NamedQuery(name = "Mueve.findByFechaHora", query = "SELECT m FROM Mueve m WHERE m.fechaHora = :fechaHora")})
public class Mueve implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmueve")
    private Integer idmueve;
    @Basic(optional = false)
    @Column(name = "fechaHora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHora;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "captura")
    private List<Movimiento> movimientoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "modificacion")
    private List<Movimiento> movimientoList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cancelacion")
    private List<Movimiento> movimientoList2;
    @JoinColumn(name = "usuario_idusuario", referencedColumnName = "idusuario")
    @ManyToOne(optional = false)
    private Usuario usuarioIdusuario;

    public Mueve() {
    }

    public Mueve(Integer idmueve) {
        this.idmueve = idmueve;
    }

    public Mueve(Integer idmueve, Date fechaHora) {
        this.idmueve = idmueve;
        this.fechaHora = fechaHora;
    }

    public Integer getIdmueve() {
        return idmueve;
    }

    public void setIdmueve(Integer idmueve) {
        this.idmueve = idmueve;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    @XmlTransient
    public List<Movimiento> getMovimientoList() {
        return movimientoList;
    }

    public void setMovimientoList(List<Movimiento> movimientoList) {
        this.movimientoList = movimientoList;
    }

    @XmlTransient
    public List<Movimiento> getMovimientoList1() {
        return movimientoList1;
    }

    public void setMovimientoList1(List<Movimiento> movimientoList1) {
        this.movimientoList1 = movimientoList1;
    }

    @XmlTransient
    public List<Movimiento> getMovimientoList2() {
        return movimientoList2;
    }

    public void setMovimientoList2(List<Movimiento> movimientoList2) {
        this.movimientoList2 = movimientoList2;
    }

    public Usuario getUsuarioIdusuario() {
        return usuarioIdusuario;
    }

    public void setUsuarioIdusuario(Usuario usuarioIdusuario) {
        this.usuarioIdusuario = usuarioIdusuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmueve != null ? idmueve.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mueve)) {
            return false;
        }
        Mueve other = (Mueve) object;
        if ((this.idmueve == null && other.idmueve != null) || (this.idmueve != null && !this.idmueve.equals(other.idmueve))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Mueve[ idmueve=" + idmueve + " ]";
    }
    
}
