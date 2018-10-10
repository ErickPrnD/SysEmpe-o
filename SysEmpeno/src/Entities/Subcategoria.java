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
@Table(name = "subcategoria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Subcategoria.findAll", query = "SELECT s FROM Subcategoria s")
    , @NamedQuery(name = "Subcategoria.findByIdsubcategoria", query = "SELECT s FROM Subcategoria s WHERE s.idsubcategoria = :idsubcategoria")
    , @NamedQuery(name = "Subcategoria.findByNombre", query = "SELECT s FROM Subcategoria s WHERE s.nombre = :nombre")})
public class Subcategoria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idsubcategoria")
    private Integer idsubcategoria;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @JoinColumn(name = "categoria_idcategoria", referencedColumnName = "idcategoria")
    @ManyToOne(optional = false)
    private Categoria categoriaIdcategoria;

    public Subcategoria() {
    }

    public Subcategoria(Integer idsubcategoria) {
        this.idsubcategoria = idsubcategoria;
    }

    public Subcategoria(Integer idsubcategoria, String nombre) {
        this.idsubcategoria = idsubcategoria;
        this.nombre = nombre;
    }

    public Integer getIdsubcategoria() {
        return idsubcategoria;
    }

    public void setIdsubcategoria(Integer idsubcategoria) {
        this.idsubcategoria = idsubcategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Categoria getCategoriaIdcategoria() {
        return categoriaIdcategoria;
    }

    public void setCategoriaIdcategoria(Categoria categoriaIdcategoria) {
        this.categoriaIdcategoria = categoriaIdcategoria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsubcategoria != null ? idsubcategoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Subcategoria)) {
            return false;
        }
        Subcategoria other = (Subcategoria) object;
        if ((this.idsubcategoria == null && other.idsubcategoria != null) || (this.idsubcategoria != null && !this.idsubcategoria.equals(other.idsubcategoria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Subcategoria[ idsubcategoria=" + idsubcategoria + " ]";
    }
    
}
