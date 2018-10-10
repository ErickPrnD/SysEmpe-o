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
@Table(name = "ocupacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ocupacion.findAll", query = "SELECT o FROM Ocupacion o")
    , @NamedQuery(name = "Ocupacion.findByIdocupacion", query = "SELECT o FROM Ocupacion o WHERE o.idocupacion = :idocupacion")
    , @NamedQuery(name = "Ocupacion.findByNombre", query = "SELECT o FROM Ocupacion o WHERE o.nombre = :nombre")})
public class Ocupacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idocupacion")
    private Integer idocupacion;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ocupacionIdocupacion")
    private List<Cliente> clienteList;

    public Ocupacion() {
    }

    public Ocupacion(Integer idocupacion) {
        this.idocupacion = idocupacion;
    }

    public Ocupacion(Integer idocupacion, String nombre) {
        this.idocupacion = idocupacion;
        this.nombre = nombre;
    }

    public Integer getIdocupacion() {
        return idocupacion;
    }

    public void setIdocupacion(Integer idocupacion) {
        this.idocupacion = idocupacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
        hash += (idocupacion != null ? idocupacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ocupacion)) {
            return false;
        }
        Ocupacion other = (Ocupacion) object;
        if ((this.idocupacion == null && other.idocupacion != null) || (this.idocupacion != null && !this.idocupacion.equals(other.idocupacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Ocupacion[ idocupacion=" + idocupacion + " ]";
    }
    
}
