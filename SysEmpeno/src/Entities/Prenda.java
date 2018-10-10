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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "prenda")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Prenda.findAll", query = "SELECT p FROM Prenda p")
    , @NamedQuery(name = "Prenda.findByIdprenda", query = "SELECT p FROM Prenda p WHERE p.idprenda = :idprenda")
    , @NamedQuery(name = "Prenda.findByDescripcion", query = "SELECT p FROM Prenda p WHERE p.descripcion = :descripcion")
    , @NamedQuery(name = "Prenda.findByModelo", query = "SELECT p FROM Prenda p WHERE p.modelo = :modelo")
    , @NamedQuery(name = "Prenda.findByNumSerie", query = "SELECT p FROM Prenda p WHERE p.numSerie = :numSerie")
    , @NamedQuery(name = "Prenda.findByOro", query = "SELECT p FROM Prenda p WHERE p.oro = :oro")
    , @NamedQuery(name = "Prenda.findByGema", query = "SELECT p FROM Prenda p WHERE p.gema = :gema")
    , @NamedQuery(name = "Prenda.findByNumero", query = "SELECT p FROM Prenda p WHERE p.numero = :numero")
    , @NamedQuery(name = "Prenda.findByPeso", query = "SELECT p FROM Prenda p WHERE p.peso = :peso")
    , @NamedQuery(name = "Prenda.findByKilataje", query = "SELECT p FROM Prenda p WHERE p.kilataje = :kilataje")
    , @NamedQuery(name = "Prenda.findByPrestamo", query = "SELECT p FROM Prenda p WHERE p.prestamo = :prestamo")
    , @NamedQuery(name = "Prenda.findByAvaluo", query = "SELECT p FROM Prenda p WHERE p.avaluo = :avaluo")
    , @NamedQuery(name = "Prenda.findByColor", query = "SELECT p FROM Prenda p WHERE p.color = :color")
    , @NamedQuery(name = "Prenda.findByCantidad", query = "SELECT p FROM Prenda p WHERE p.cantidad = :cantidad")
    , @NamedQuery(name = "Prenda.findByComercializada", query = "SELECT p FROM Prenda p WHERE p.comercializada = :comercializada")})
public class Prenda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idprenda")
    private Integer idprenda;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "modelo")
    private String modelo;
    @Column(name = "numSerie")
    private String numSerie;
    @Basic(optional = false)
    @Column(name = "oro")
    private short oro;
    @Basic(optional = false)
    @Column(name = "gema")
    private short gema;
    @Basic(optional = false)
    @Column(name = "numero")
    private int numero;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "peso")
    private Double peso;
    @Column(name = "kilataje")
    private String kilataje;
    @Basic(optional = false)
    @Column(name = "prestamo")
    private double prestamo;
    @Basic(optional = false)
    @Column(name = "avaluo")
    private double avaluo;
    @Column(name = "color")
    private String color;
    @Basic(optional = false)
    @Column(name = "cantidad")
    private int cantidad;
    @Basic(optional = false)
    @Column(name = "comercializada")
    private short comercializada;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "prendaIdprenda")
    private List<Articulo> articuloList;
    @JoinColumn(name = "categoria_idcategoria", referencedColumnName = "idcategoria")
    @ManyToOne(optional = false)
    private Categoria categoriaIdcategoria;
    @JoinColumn(name = "Contrato_idContrato", referencedColumnName = "idcontrato")
    @ManyToOne(optional = false)
    private Contrato contratoidContrato;
    @JoinColumn(name = "revisa", referencedColumnName = "idusuario")
    @ManyToOne
    private Usuario revisa;
    @JoinColumn(name = "autoriza", referencedColumnName = "idusuario")
    @ManyToOne
    private Usuario autoriza;

    public Prenda() {
    }

    public Prenda(Integer idprenda) {
        this.idprenda = idprenda;
    }

    public Prenda(Integer idprenda, short oro, short gema, int numero, double prestamo, double avaluo, int cantidad, short comercializada) {
        this.idprenda = idprenda;
        this.oro = oro;
        this.gema = gema;
        this.numero = numero;
        this.prestamo = prestamo;
        this.avaluo = avaluo;
        this.cantidad = cantidad;
        this.comercializada = comercializada;
    }

    public Integer getIdprenda() {
        return idprenda;
    }

    public void setIdprenda(Integer idprenda) {
        this.idprenda = idprenda;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getNumSerie() {
        return numSerie;
    }

    public void setNumSerie(String numSerie) {
        this.numSerie = numSerie;
    }

    public short getOro() {
        return oro;
    }

    public void setOro(short oro) {
        this.oro = oro;
    }

    public short getGema() {
        return gema;
    }

    public void setGema(short gema) {
        this.gema = gema;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public String getKilataje() {
        return kilataje;
    }

    public void setKilataje(String kilataje) {
        this.kilataje = kilataje;
    }

    public double getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(double prestamo) {
        this.prestamo = prestamo;
    }

    public double getAvaluo() {
        return avaluo;
    }

    public void setAvaluo(double avaluo) {
        this.avaluo = avaluo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public short getComercializada() {
        return comercializada;
    }

    public void setComercializada(short comercializada) {
        this.comercializada = comercializada;
    }

    @XmlTransient
    public List<Articulo> getArticuloList() {
        return articuloList;
    }

    public void setArticuloList(List<Articulo> articuloList) {
        this.articuloList = articuloList;
    }

    public Categoria getCategoriaIdcategoria() {
        return categoriaIdcategoria;
    }

    public void setCategoriaIdcategoria(Categoria categoriaIdcategoria) {
        this.categoriaIdcategoria = categoriaIdcategoria;
    }

    public Contrato getContratoidContrato() {
        return contratoidContrato;
    }

    public void setContratoidContrato(Contrato contratoidContrato) {
        this.contratoidContrato = contratoidContrato;
    }

    public Usuario getRevisa() {
        return revisa;
    }

    public void setRevisa(Usuario revisa) {
        this.revisa = revisa;
    }

    public Usuario getAutoriza() {
        return autoriza;
    }

    public void setAutoriza(Usuario autoriza) {
        this.autoriza = autoriza;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idprenda != null ? idprenda.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prenda)) {
            return false;
        }
        Prenda other = (Prenda) object;
        if ((this.idprenda == null && other.idprenda != null) || (this.idprenda != null && !this.idprenda.equals(other.idprenda))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Prenda[ idprenda=" + idprenda + " ]";
    }
    
}
