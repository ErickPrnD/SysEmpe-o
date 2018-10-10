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
@Table(name = "tipoidentificacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipoidentificacion.findAll", query = "SELECT t FROM Tipoidentificacion t")
    , @NamedQuery(name = "Tipoidentificacion.findByIdtipoidentificacion", query = "SELECT t FROM Tipoidentificacion t WHERE t.idtipoidentificacion = :idtipoidentificacion")
    , @NamedQuery(name = "Tipoidentificacion.findByIdentificacion", query = "SELECT t FROM Tipoidentificacion t WHERE t.identificacion = :identificacion")})
public class Tipoidentificacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtipoidentificacion")
    private Integer idtipoidentificacion;
    @Basic(optional = false)
    @Column(name = "identificacion")
    private String identificacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoidentificacionIdtipoidentificacion")
    private List<Cliente> clienteList;

    public Tipoidentificacion() {
    }

    public Tipoidentificacion(Integer idtipoidentificacion) {
        this.idtipoidentificacion = idtipoidentificacion;
    }

    public Tipoidentificacion(Integer idtipoidentificacion, String identificacion) {
        this.idtipoidentificacion = idtipoidentificacion;
        this.identificacion = identificacion;
    }

    public Integer getIdtipoidentificacion() {
        return idtipoidentificacion;
    }

    public void setIdtipoidentificacion(Integer idtipoidentificacion) {
        this.idtipoidentificacion = idtipoidentificacion;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    @XmlTransient
    public List<Cliente> getClienteList() {
        return clienteList;
    }

    public void setClienteList(List<Cliente> clienteList) {
        this.clienteList = clienteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtipoidentificacion != null ? idtipoidentificacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipoidentificacion)) {
            return false;
        }
        Tipoidentificacion other = (Tipoidentificacion) object;
        if ((this.idtipoidentificacion == null && other.idtipoidentificacion != null) || (this.idtipoidentificacion != null && !this.idtipoidentificacion.equals(other.idtipoidentificacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Tipoidentificacion[ idtipoidentificacion=" + idtipoidentificacion + " ]";
    }
    
}
