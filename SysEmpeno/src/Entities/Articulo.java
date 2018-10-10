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
@Table(name = "articulo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Articulo.findAll", query = "SELECT a FROM Articulo a")
    , @NamedQuery(name = "Articulo.findByIdarticulo", query = "SELECT a FROM Articulo a WHERE a.idarticulo = :idarticulo")
    , @NamedQuery(name = "Articulo.findByDescripcion", query = "SELECT a FROM Articulo a WHERE a.descripcion = :descripcion")
    , @NamedQuery(name = "Articulo.findByPrecio", query = "SELECT a FROM Articulo a WHERE a.precio = :precio")})
public class Articulo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idarticulo")
    private Integer idarticulo;
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "precio")
    private double precio;
    @JoinColumn(name = "prenda_idprenda", referencedColumnName = "idprenda")
    @ManyToOne(optional = false)
    private Prenda prendaIdprenda;
    @JoinColumn(name = "venta_idventa", referencedColumnName = "idventa")
    @ManyToOne
    private Venta ventaIdventa;

    public Articulo() {
    }

    public Articulo(Integer idarticulo) {
        this.idarticulo = idarticulo;
    }

    public Articulo(Integer idarticulo, double precio) {
        this.idarticulo = idarticulo;
        this.precio = precio;
    }

    public Integer getIdarticulo() {
        return idarticulo;
    }

    public void setIdarticulo(Integer idarticulo) {
        this.idarticulo = idarticulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Prenda getPrendaIdprenda() {
        return prendaIdprenda;
    }

    public void setPrendaIdprenda(Prenda prendaIdprenda) {
        this.prendaIdprenda = prendaIdprenda;
    }

    public Venta getVentaIdventa() {
        return ventaIdventa;
    }

    public void setVentaIdventa(Venta ventaIdventa) {
        this.ventaIdventa = ventaIdventa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idarticulo != null ? idarticulo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Articulo)) {
            return false;
        }
        Articulo other = (Articulo) object;
        if ((this.idarticulo == null && other.idarticulo != null) || (this.idarticulo != null && !this.idarticulo.equals(other.idarticulo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Articulo[ idarticulo=" + idarticulo + " ]";
    }
    
}
