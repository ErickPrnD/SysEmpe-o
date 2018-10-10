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
@Table(name = "institucionemite")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Institucionemite.findAll", query = "SELECT i FROM Institucionemite i")
    , @NamedQuery(name = "Institucionemite.findByIdinstitucionemite", query = "SELECT i FROM Institucionemite i WHERE i.idinstitucionemite = :idinstitucionemite")
    , @NamedQuery(name = "Institucionemite.findByNombre", query = "SELECT i FROM Institucionemite i WHERE i.nombre = :nombre")})
public class Institucionemite implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idinstitucionemite")
    private Integer idinstitucionemite;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "institucionemiteIdinstitucionemite")
    private List<Cliente> clienteList;

    public Institucionemite() {
    }

    public Institucionemite(Integer idinstitucionemite) {
        this.idinstitucionemite = idinstitucionemite;
    }

    public Institucionemite(Integer idinstitucionemite, String nombre) {
        this.idinstitucionemite = idinstitucionemite;
        this.nombre = nombre;
    }

    public Integer getIdinstitucionemite() {
        return idinstitucionemite;
    }

    public void setIdinstitucionemite(Integer idinstitucionemite) {
        this.idinstitucionemite = idinstitucionemite;
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
        hash += (idinstitucionemite != null ? idinstitucionemite.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Institucionemite)) {
            return false;
        }
        Institucionemite other = (Institucionemite) object;
        if ((this.idinstitucionemite == null && other.idinstitucionemite != null) || (this.idinstitucionemite != null && !this.idinstitucionemite.equals(other.idinstitucionemite))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Institucionemite[ idinstitucionemite=" + idinstitucionemite + " ]";
    }
    
}
