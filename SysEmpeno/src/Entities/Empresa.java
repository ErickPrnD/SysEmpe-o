/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author erick
 */
@Entity
@Table(name = "empresa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empresa.findAll", query = "SELECT e FROM Empresa e")
    , @NamedQuery(name = "Empresa.findByNombre", query = "SELECT e FROM Empresa e WHERE e.nombre = :nombre")
    , @NamedQuery(name = "Empresa.findByNumAutorizacion", query = "SELECT e FROM Empresa e WHERE e.numAutorizacion = :numAutorizacion")
    , @NamedQuery(name = "Empresa.findByLicenciaFuncionamiento", query = "SELECT e FROM Empresa e WHERE e.licenciaFuncionamiento = :licenciaFuncionamiento")
    , @NamedQuery(name = "Empresa.findByCodigoAdhesion", query = "SELECT e FROM Empresa e WHERE e.codigoAdhesion = :codigoAdhesion")
    , @NamedQuery(name = "Empresa.findByFechaAdhesion", query = "SELECT e FROM Empresa e WHERE e.fechaAdhesion = :fechaAdhesion")
    , @NamedQuery(name = "Empresa.findByHoraEntradaSemana", query = "SELECT e FROM Empresa e WHERE e.horaEntradaSemana = :horaEntradaSemana")
    , @NamedQuery(name = "Empresa.findByHoraSalidaSemana", query = "SELECT e FROM Empresa e WHERE e.horaSalidaSemana = :horaSalidaSemana")
    , @NamedQuery(name = "Empresa.findByHoraEntradafin", query = "SELECT e FROM Empresa e WHERE e.horaEntradafin = :horaEntradafin")
    , @NamedQuery(name = "Empresa.findByHoraSalidaFin", query = "SELECT e FROM Empresa e WHERE e.horaSalidaFin = :horaSalidaFin")
    , @NamedQuery(name = "Empresa.findByCalle", query = "SELECT e FROM Empresa e WHERE e.calle = :calle")
    , @NamedQuery(name = "Empresa.findByNumero", query = "SELECT e FROM Empresa e WHERE e.numero = :numero")
    , @NamedQuery(name = "Empresa.findByInterior", query = "SELECT e FROM Empresa e WHERE e.interior = :interior")
    , @NamedQuery(name = "Empresa.findByColonia", query = "SELECT e FROM Empresa e WHERE e.colonia = :colonia")
    , @NamedQuery(name = "Empresa.findByCodigoPostal", query = "SELECT e FROM Empresa e WHERE e.codigoPostal = :codigoPostal")
    , @NamedQuery(name = "Empresa.findByLocalidad", query = "SELECT e FROM Empresa e WHERE e.localidad = :localidad")
    , @NamedQuery(name = "Empresa.findByTelefono", query = "SELECT e FROM Empresa e WHERE e.telefono = :telefono")
    , @NamedQuery(name = "Empresa.findByCorreo", query = "SELECT e FROM Empresa e WHERE e.correo = :correo")
    , @NamedQuery(name = "Empresa.findByIdempresa", query = "SELECT e FROM Empresa e WHERE e.idempresa = :idempresa")})
public class Empresa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "numAutorizacion")
    private String numAutorizacion;
    @Basic(optional = false)
    @Column(name = "licenciaFuncionamiento")
    private String licenciaFuncionamiento;
    @Basic(optional = false)
    @Column(name = "codigoAdhesion")
    private String codigoAdhesion;
    @Basic(optional = false)
    @Column(name = "fechaAdhesion")
    @Temporal(TemporalType.DATE)
    private Date fechaAdhesion;
    @Basic(optional = false)
    @Column(name = "horaEntradaSemana")
    @Temporal(TemporalType.TIME)
    private Date horaEntradaSemana;
    @Basic(optional = false)
    @Column(name = "horaSalidaSemana")
    @Temporal(TemporalType.TIME)
    private Date horaSalidaSemana;
    @Basic(optional = false)
    @Column(name = "horaEntradafin")
    @Temporal(TemporalType.TIME)
    private Date horaEntradafin;
    @Basic(optional = false)
    @Column(name = "horaSalidaFin")
    @Temporal(TemporalType.TIME)
    private Date horaSalidaFin;
    @Basic(optional = false)
    @Column(name = "calle")
    private String calle;
    @Basic(optional = false)
    @Column(name = "numero")
    private String numero;
    @Column(name = "interior")
    private String interior;
    @Basic(optional = false)
    @Column(name = "colonia")
    private String colonia;
    @Basic(optional = false)
    @Column(name = "codigoPostal")
    private String codigoPostal;
    @Basic(optional = false)
    @Column(name = "localidad")
    private String localidad;
    @Basic(optional = false)
    @Column(name = "telefono")
    private String telefono;
    @Basic(optional = false)
    @Column(name = "correo")
    private String correo;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idempresa")
    private Integer idempresa;
    @JoinColumn(name = "municipio_idMunicipio", referencedColumnName = "idmunicipio")
    @ManyToOne(optional = false)
    private Municipio municipioidMunicipio;

    public Empresa() {
    }

    public Empresa(Integer idempresa) {
        this.idempresa = idempresa;
    }

    public Empresa(Integer idempresa, String nombre, String numAutorizacion, String licenciaFuncionamiento, String codigoAdhesion, Date fechaAdhesion, Date horaEntradaSemana, Date horaSalidaSemana, Date horaEntradafin, Date horaSalidaFin, String calle, String numero, String colonia, String codigoPostal, String localidad, String telefono, String correo) {
        this.idempresa = idempresa;
        this.nombre = nombre;
        this.numAutorizacion = numAutorizacion;
        this.licenciaFuncionamiento = licenciaFuncionamiento;
        this.codigoAdhesion = codigoAdhesion;
        this.fechaAdhesion = fechaAdhesion;
        this.horaEntradaSemana = horaEntradaSemana;
        this.horaSalidaSemana = horaSalidaSemana;
        this.horaEntradafin = horaEntradafin;
        this.horaSalidaFin = horaSalidaFin;
        this.calle = calle;
        this.numero = numero;
        this.colonia = colonia;
        this.codigoPostal = codigoPostal;
        this.localidad = localidad;
        this.telefono = telefono;
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumAutorizacion() {
        return numAutorizacion;
    }

    public void setNumAutorizacion(String numAutorizacion) {
        this.numAutorizacion = numAutorizacion;
    }

    public String getLicenciaFuncionamiento() {
        return licenciaFuncionamiento;
    }

    public void setLicenciaFuncionamiento(String licenciaFuncionamiento) {
        this.licenciaFuncionamiento = licenciaFuncionamiento;
    }

    public String getCodigoAdhesion() {
        return codigoAdhesion;
    }

    public void setCodigoAdhesion(String codigoAdhesion) {
        this.codigoAdhesion = codigoAdhesion;
    }

    public Date getFechaAdhesion() {
        return fechaAdhesion;
    }

    public void setFechaAdhesion(Date fechaAdhesion) {
        this.fechaAdhesion = fechaAdhesion;
    }

    public Date getHoraEntradaSemana() {
        return horaEntradaSemana;
    }

    public void setHoraEntradaSemana(Date horaEntradaSemana) {
        this.horaEntradaSemana = horaEntradaSemana;
    }

    public Date getHoraSalidaSemana() {
        return horaSalidaSemana;
    }

    public void setHoraSalidaSemana(Date horaSalidaSemana) {
        this.horaSalidaSemana = horaSalidaSemana;
    }

    public Date getHoraEntradafin() {
        return horaEntradafin;
    }

    public void setHoraEntradafin(Date horaEntradafin) {
        this.horaEntradafin = horaEntradafin;
    }

    public Date getHoraSalidaFin() {
        return horaSalidaFin;
    }

    public void setHoraSalidaFin(Date horaSalidaFin) {
        this.horaSalidaFin = horaSalidaFin;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getInterior() {
        return interior;
    }

    public void setInterior(String interior) {
        this.interior = interior;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Integer getIdempresa() {
        return idempresa;
    }

    public void setIdempresa(Integer idempresa) {
        this.idempresa = idempresa;
    }

    public Municipio getMunicipioidMunicipio() {
        return municipioidMunicipio;
    }

    public void setMunicipioidMunicipio(Municipio municipioidMunicipio) {
        this.municipioidMunicipio = municipioidMunicipio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idempresa != null ? idempresa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empresa)) {
            return false;
        }
        Empresa other = (Empresa) object;
        if ((this.idempresa == null && other.idempresa != null) || (this.idempresa != null && !this.idempresa.equals(other.idempresa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Empresa[ idempresa=" + idempresa + " ]";
    }
    
}
