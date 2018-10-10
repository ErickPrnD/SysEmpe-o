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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author erick
 */
@Entity
@Table(name = "parametros")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Parametros.findAll", query = "SELECT p FROM Parametros p")
    , @NamedQuery(name = "Parametros.findByIva", query = "SELECT p FROM Parametros p WHERE p.iva = :iva")
    , @NamedQuery(name = "Parametros.findByEmpenoInteres", query = "SELECT p FROM Parametros p WHERE p.empenoInteres = :empenoInteres")
    , @NamedQuery(name = "Parametros.findByEmpenoInteresExt", query = "SELECT p FROM Parametros p WHERE p.empenoInteresExt = :empenoInteresExt")
    , @NamedQuery(name = "Parametros.findByEmpenoPeriodos", query = "SELECT p FROM Parametros p WHERE p.empenoPeriodos = :empenoPeriodos")
    , @NamedQuery(name = "Parametros.findByEmpenoAlmacenaje", query = "SELECT p FROM Parametros p WHERE p.empenoAlmacenaje = :empenoAlmacenaje")
    , @NamedQuery(name = "Parametros.findByEmpenoDiasPeriodo", query = "SELECT p FROM Parametros p WHERE p.empenoDiasPeriodo = :empenoDiasPeriodo")
    , @NamedQuery(name = "Parametros.findByTazaComercializacion", query = "SELECT p FROM Parametros p WHERE p.tazaComercializacion = :tazaComercializacion")
    , @NamedQuery(name = "Parametros.findByCostoReposicionContrato", query = "SELECT p FROM Parametros p WHERE p.costoReposicionContrato = :costoReposicionContrato")
    , @NamedQuery(name = "Parametros.findByDiasApartado", query = "SELECT p FROM Parametros p WHERE p.diasApartado = :diasApartado")
    , @NamedQuery(name = "Parametros.findByPorcentajeApartado", query = "SELECT p FROM Parametros p WHERE p.porcentajeApartado = :porcentajeApartado")
    , @NamedQuery(name = "Parametros.findByPldPlazoMeses", query = "SELECT p FROM Parametros p WHERE p.pldPlazoMeses = :pldPlazoMeses")
    , @NamedQuery(name = "Parametros.findByPldImporteAvisoEmpeno", query = "SELECT p FROM Parametros p WHERE p.pldImporteAvisoEmpeno = :pldImporteAvisoEmpeno")
    , @NamedQuery(name = "Parametros.findByPldImporteIdentificacionVenta", query = "SELECT p FROM Parametros p WHERE p.pldImporteIdentificacionVenta = :pldImporteIdentificacionVenta")
    , @NamedQuery(name = "Parametros.findByPldAvisoVenta", query = "SELECT p FROM Parametros p WHERE p.pldAvisoVenta = :pldAvisoVenta")
    , @NamedQuery(name = "Parametros.findByPldRestriccionUsoEfectivo", query = "SELECT p FROM Parametros p WHERE p.pldRestriccionUsoEfectivo = :pldRestriccionUsoEfectivo")
    , @NamedQuery(name = "Parametros.findByRutaRespaldos", query = "SELECT p FROM Parametros p WHERE p.rutaRespaldos = :rutaRespaldos")
    , @NamedQuery(name = "Parametros.findByPorcentajeAbonoMaximoEmpenos", query = "SELECT p FROM Parametros p WHERE p.porcentajeAbonoMaximoEmpenos = :porcentajeAbonoMaximoEmpenos")
    , @NamedQuery(name = "Parametros.findByNumCopiasTickets", query = "SELECT p FROM Parametros p WHERE p.numCopiasTickets = :numCopiasTickets")
    , @NamedQuery(name = "Parametros.findByAlertaMinimoEfectivoEmpeno", query = "SELECT p FROM Parametros p WHERE p.alertaMinimoEfectivoEmpeno = :alertaMinimoEfectivoEmpeno")
    , @NamedQuery(name = "Parametros.findByMaximoDiasEspera", query = "SELECT p FROM Parametros p WHERE p.maximoDiasEspera = :maximoDiasEspera")
    , @NamedQuery(name = "Parametros.findByDiasEsperaComercializacion", query = "SELECT p FROM Parametros p WHERE p.diasEsperaComercializacion = :diasEsperaComercializacion")
    , @NamedQuery(name = "Parametros.findById", query = "SELECT p FROM Parametros p WHERE p.id = :id")})
public class Parametros implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "iva")
    private double iva;
    @Basic(optional = false)
    @Column(name = "empenoInteres")
    private double empenoInteres;
    @Basic(optional = false)
    @Column(name = "empenoInteresExt")
    private double empenoInteresExt;
    @Basic(optional = false)
    @Column(name = "empenoPeriodos")
    private int empenoPeriodos;
    @Basic(optional = false)
    @Column(name = "empenoAlmacenaje")
    private double empenoAlmacenaje;
    @Basic(optional = false)
    @Column(name = "empenoDiasPeriodo")
    private int empenoDiasPeriodo;
    @Basic(optional = false)
    @Column(name = "tazaComercializacion")
    private double tazaComercializacion;
    @Basic(optional = false)
    @Column(name = "costoReposicionContrato")
    private double costoReposicionContrato;
    @Basic(optional = false)
    @Column(name = "diasApartado")
    private int diasApartado;
    @Basic(optional = false)
    @Column(name = "porcentajeApartado")
    private double porcentajeApartado;
    @Basic(optional = false)
    @Column(name = "pldPlazoMeses")
    private int pldPlazoMeses;
    @Basic(optional = false)
    @Column(name = "pldImporteAvisoEmpeno")
    private double pldImporteAvisoEmpeno;
    @Basic(optional = false)
    @Column(name = "pldImporteIdentificacionVenta")
    private double pldImporteIdentificacionVenta;
    @Basic(optional = false)
    @Column(name = "pldAvisoVenta")
    private double pldAvisoVenta;
    @Basic(optional = false)
    @Column(name = "pldRestriccionUsoEfectivo")
    private double pldRestriccionUsoEfectivo;
    @Basic(optional = false)
    @Column(name = "rutaRespaldos")
    private String rutaRespaldos;
    @Basic(optional = false)
    @Column(name = "porcentajeAbonoMaximoEmpenos")
    private double porcentajeAbonoMaximoEmpenos;
    @Basic(optional = false)
    @Column(name = "numCopiasTickets")
    private int numCopiasTickets;
    @Basic(optional = false)
    @Column(name = "alertaMinimoEfectivoEmpeno")
    private double alertaMinimoEfectivoEmpeno;
    @Basic(optional = false)
    @Column(name = "maximoDiasEspera")
    private int maximoDiasEspera;
    @Basic(optional = false)
    @Column(name = "diasEsperaComercializacion")
    private int diasEsperaComercializacion;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    public Parametros() {
    }

    public Parametros(Integer id) {
        this.id = id;
    }

    public Parametros(Integer id, double iva, double empenoInteres, double empenoInteresExt, int empenoPeriodos, double empenoAlmacenaje, int empenoDiasPeriodo, double tazaComercializacion, double costoReposicionContrato, int diasApartado, double porcentajeApartado, int pldPlazoMeses, double pldImporteAvisoEmpeno, double pldImporteIdentificacionVenta, double pldAvisoVenta, double pldRestriccionUsoEfectivo, String rutaRespaldos, double porcentajeAbonoMaximoEmpenos, int numCopiasTickets, double alertaMinimoEfectivoEmpeno, int maximoDiasEspera, int diasEsperaComercializacion) {
        this.id = id;
        this.iva = iva;
        this.empenoInteres = empenoInteres;
        this.empenoInteresExt = empenoInteresExt;
        this.empenoPeriodos = empenoPeriodos;
        this.empenoAlmacenaje = empenoAlmacenaje;
        this.empenoDiasPeriodo = empenoDiasPeriodo;
        this.tazaComercializacion = tazaComercializacion;
        this.costoReposicionContrato = costoReposicionContrato;
        this.diasApartado = diasApartado;
        this.porcentajeApartado = porcentajeApartado;
        this.pldPlazoMeses = pldPlazoMeses;
        this.pldImporteAvisoEmpeno = pldImporteAvisoEmpeno;
        this.pldImporteIdentificacionVenta = pldImporteIdentificacionVenta;
        this.pldAvisoVenta = pldAvisoVenta;
        this.pldRestriccionUsoEfectivo = pldRestriccionUsoEfectivo;
        this.rutaRespaldos = rutaRespaldos;
        this.porcentajeAbonoMaximoEmpenos = porcentajeAbonoMaximoEmpenos;
        this.numCopiasTickets = numCopiasTickets;
        this.alertaMinimoEfectivoEmpeno = alertaMinimoEfectivoEmpeno;
        this.maximoDiasEspera = maximoDiasEspera;
        this.diasEsperaComercializacion = diasEsperaComercializacion;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public double getEmpenoInteres() {
        return empenoInteres;
    }

    public void setEmpenoInteres(double empenoInteres) {
        this.empenoInteres = empenoInteres;
    }

    public double getEmpenoInteresExt() {
        return empenoInteresExt;
    }

    public void setEmpenoInteresExt(double empenoInteresExt) {
        this.empenoInteresExt = empenoInteresExt;
    }

    public int getEmpenoPeriodos() {
        return empenoPeriodos;
    }

    public void setEmpenoPeriodos(int empenoPeriodos) {
        this.empenoPeriodos = empenoPeriodos;
    }

    public double getEmpenoAlmacenaje() {
        return empenoAlmacenaje;
    }

    public void setEmpenoAlmacenaje(double empenoAlmacenaje) {
        this.empenoAlmacenaje = empenoAlmacenaje;
    }

    public int getEmpenoDiasPeriodo() {
        return empenoDiasPeriodo;
    }

    public void setEmpenoDiasPeriodo(int empenoDiasPeriodo) {
        this.empenoDiasPeriodo = empenoDiasPeriodo;
    }

    public double getTazaComercializacion() {
        return tazaComercializacion;
    }

    public void setTazaComercializacion(double tazaComercializacion) {
        this.tazaComercializacion = tazaComercializacion;
    }

    public double getCostoReposicionContrato() {
        return costoReposicionContrato;
    }

    public void setCostoReposicionContrato(double costoReposicionContrato) {
        this.costoReposicionContrato = costoReposicionContrato;
    }

    public int getDiasApartado() {
        return diasApartado;
    }

    public void setDiasApartado(int diasApartado) {
        this.diasApartado = diasApartado;
    }

    public double getPorcentajeApartado() {
        return porcentajeApartado;
    }

    public void setPorcentajeApartado(double porcentajeApartado) {
        this.porcentajeApartado = porcentajeApartado;
    }

    public int getPldPlazoMeses() {
        return pldPlazoMeses;
    }

    public void setPldPlazoMeses(int pldPlazoMeses) {
        this.pldPlazoMeses = pldPlazoMeses;
    }

    public double getPldImporteAvisoEmpeno() {
        return pldImporteAvisoEmpeno;
    }

    public void setPldImporteAvisoEmpeno(double pldImporteAvisoEmpeno) {
        this.pldImporteAvisoEmpeno = pldImporteAvisoEmpeno;
    }

    public double getPldImporteIdentificacionVenta() {
        return pldImporteIdentificacionVenta;
    }

    public void setPldImporteIdentificacionVenta(double pldImporteIdentificacionVenta) {
        this.pldImporteIdentificacionVenta = pldImporteIdentificacionVenta;
    }

    public double getPldAvisoVenta() {
        return pldAvisoVenta;
    }

    public void setPldAvisoVenta(double pldAvisoVenta) {
        this.pldAvisoVenta = pldAvisoVenta;
    }

    public double getPldRestriccionUsoEfectivo() {
        return pldRestriccionUsoEfectivo;
    }

    public void setPldRestriccionUsoEfectivo(double pldRestriccionUsoEfectivo) {
        this.pldRestriccionUsoEfectivo = pldRestriccionUsoEfectivo;
    }

    public String getRutaRespaldos() {
        return rutaRespaldos;
    }

    public void setRutaRespaldos(String rutaRespaldos) {
        this.rutaRespaldos = rutaRespaldos;
    }

    public double getPorcentajeAbonoMaximoEmpenos() {
        return porcentajeAbonoMaximoEmpenos;
    }

    public void setPorcentajeAbonoMaximoEmpenos(double porcentajeAbonoMaximoEmpenos) {
        this.porcentajeAbonoMaximoEmpenos = porcentajeAbonoMaximoEmpenos;
    }

    public int getNumCopiasTickets() {
        return numCopiasTickets;
    }

    public void setNumCopiasTickets(int numCopiasTickets) {
        this.numCopiasTickets = numCopiasTickets;
    }

    public double getAlertaMinimoEfectivoEmpeno() {
        return alertaMinimoEfectivoEmpeno;
    }

    public void setAlertaMinimoEfectivoEmpeno(double alertaMinimoEfectivoEmpeno) {
        this.alertaMinimoEfectivoEmpeno = alertaMinimoEfectivoEmpeno;
    }

    public int getMaximoDiasEspera() {
        return maximoDiasEspera;
    }

    public void setMaximoDiasEspera(int maximoDiasEspera) {
        this.maximoDiasEspera = maximoDiasEspera;
    }

    public int getDiasEsperaComercializacion() {
        return diasEsperaComercializacion;
    }

    public void setDiasEsperaComercializacion(int diasEsperaComercializacion) {
        this.diasEsperaComercializacion = diasEsperaComercializacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Parametros)) {
            return false;
        }
        Parametros other = (Parametros) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Parametros[ id=" + id + " ]";
    }
    
}
