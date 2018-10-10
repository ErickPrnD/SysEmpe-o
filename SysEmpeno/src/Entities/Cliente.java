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
import javax.persistence.Lob;
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
@Table(name = "cliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c")
    , @NamedQuery(name = "Cliente.findByIdcliente", query = "SELECT c FROM Cliente c WHERE c.idcliente = :idcliente")
    , @NamedQuery(name = "Cliente.findByNombre", query = "SELECT c FROM Cliente c WHERE c.nombre = :nombre")
    , @NamedQuery(name = "Cliente.findByApellidoPaterno", query = "SELECT c FROM Cliente c WHERE c.apellidoPaterno = :apellidoPaterno")
    , @NamedQuery(name = "Cliente.findByApellidoMaterno", query = "SELECT c FROM Cliente c WHERE c.apellidoMaterno = :apellidoMaterno")
    , @NamedQuery(name = "Cliente.findBySexo", query = "SELECT c FROM Cliente c WHERE c.sexo = :sexo")
    , @NamedQuery(name = "Cliente.findByFechanacimiento", query = "SELECT c FROM Cliente c WHERE c.fechanacimiento = :fechanacimiento")
    , @NamedQuery(name = "Cliente.findByRfc", query = "SELECT c FROM Cliente c WHERE c.rfc = :rfc")
    , @NamedQuery(name = "Cliente.findByCurp", query = "SELECT c FROM Cliente c WHERE c.curp = :curp")
    , @NamedQuery(name = "Cliente.findByNumeroIdentificacion", query = "SELECT c FROM Cliente c WHERE c.numeroIdentificacion = :numeroIdentificacion")
    , @NamedQuery(name = "Cliente.findByCalle", query = "SELECT c FROM Cliente c WHERE c.calle = :calle")
    , @NamedQuery(name = "Cliente.findByNumero", query = "SELECT c FROM Cliente c WHERE c.numero = :numero")
    , @NamedQuery(name = "Cliente.findByInterior", query = "SELECT c FROM Cliente c WHERE c.interior = :interior")
    , @NamedQuery(name = "Cliente.findByColonia", query = "SELECT c FROM Cliente c WHERE c.colonia = :colonia")
    , @NamedQuery(name = "Cliente.findByCodigoPostal", query = "SELECT c FROM Cliente c WHERE c.codigoPostal = :codigoPostal")
    , @NamedQuery(name = "Cliente.findByLocalidad", query = "SELECT c FROM Cliente c WHERE c.localidad = :localidad")
    , @NamedQuery(name = "Cliente.findByTelefono", query = "SELECT c FROM Cliente c WHERE c.telefono = :telefono")
    , @NamedQuery(name = "Cliente.findByEmail", query = "SELECT c FROM Cliente c WHERE c.email = :email")
    , @NamedQuery(name = "Cliente.findByComentario", query = "SELECT c FROM Cliente c WHERE c.comentario = :comentario")})
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcliente")
    private Integer idcliente;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "apellidoPaterno")
    private String apellidoPaterno;
    @Basic(optional = false)
    @Column(name = "apellidoMaterno")
    private String apellidoMaterno;
    @Basic(optional = false)
    @Column(name = "sexo")
    private String sexo;
    @Basic(optional = false)
    @Column(name = "fechanacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechanacimiento;
    @Basic(optional = false)
    @Column(name = "rfc")
    private String rfc;
    @Basic(optional = false)
    @Column(name = "curp")
    private String curp;
    @Basic(optional = false)
    @Column(name = "numeroIdentificacion")
    private String numeroIdentificacion;
    @Basic(optional = false)
    @Column(name = "calle")
    private String calle;
    @Basic(optional = false)
    @Column(name = "numero")
    private String numero;
    @Basic(optional = false)
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
    @Column(name = "email")
    private String email;
    @Column(name = "comentario")
    private String comentario;
    @Basic(optional = false)
    @Lob
    @Column(name = "huella")
    private byte[] huella;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clienteIdcliente")
    private List<Venta> ventaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clienteIdcliente")
    private List<Contrato> contratoList;
    @JoinColumn(name = "institucionemite_idinstitucionemite", referencedColumnName = "idinstitucionemite")
    @ManyToOne(optional = false)
    private Institucionemite institucionemiteIdinstitucionemite;
    @JoinColumn(name = "municipio_idmunicipio", referencedColumnName = "idmunicipio")
    @ManyToOne(optional = false)
    private Municipio municipioIdmunicipio;
    @JoinColumn(name = "ocupacion_idocupacion", referencedColumnName = "idocupacion")
    @ManyToOne(optional = false)
    private Ocupacion ocupacionIdocupacion;
    @JoinColumn(name = "paisnacimiento", referencedColumnName = "idpais")
    @ManyToOne(optional = false)
    private Pais paisnacimiento;
    @JoinColumn(name = "tipoidentificacion_idtipoidentificacion", referencedColumnName = "idtipoidentificacion")
    @ManyToOne(optional = false)
    private Tipoidentificacion tipoidentificacionIdtipoidentificacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clienteIdcliente")
    private List<Fotocliente> fotoclienteList;

    public Cliente() {
    }

    public Cliente(Integer idcliente) {
        this.idcliente = idcliente;
    }

    public Cliente(Integer idcliente, String nombre, String apellidoPaterno, String apellidoMaterno, String sexo, Date fechanacimiento, String rfc, String curp, String numeroIdentificacion, String calle, String numero, String interior, String colonia, String codigoPostal, String localidad, String telefono, String email, byte[] huella) {
        this.idcliente = idcliente;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.sexo = sexo;
        this.fechanacimiento = fechanacimiento;
        this.rfc = rfc;
        this.curp = curp;
        this.numeroIdentificacion = numeroIdentificacion;
        this.calle = calle;
        this.numero = numero;
        this.interior = interior;
        this.colonia = colonia;
        this.codigoPostal = codigoPostal;
        this.localidad = localidad;
        this.telefono = telefono;
        this.email = email;
        this.huella = huella;
    }

    public Integer getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Integer idcliente) {
        this.idcliente = idcliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Date getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(Date fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public byte[] getHuella() {
        return huella;
    }

    public void setHuella(byte[] huella) {
        this.huella = huella;
    }

    @XmlTransient
    public List<Venta> getVentaList() {
        return ventaList;
    }

    public void setVentaList(List<Venta> ventaList) {
        this.ventaList = ventaList;
    }

    @XmlTransient
    public List<Contrato> getContratoList() {
        return contratoList;
    }

    public void setContratoList(List<Contrato> contratoList) {
        this.contratoList = contratoList;
    }

    public Institucionemite getInstitucionemiteIdinstitucionemite() {
        return institucionemiteIdinstitucionemite;
    }

    public void setInstitucionemiteIdinstitucionemite(Institucionemite institucionemiteIdinstitucionemite) {
        this.institucionemiteIdinstitucionemite = institucionemiteIdinstitucionemite;
    }

    public Municipio getMunicipioIdmunicipio() {
        return municipioIdmunicipio;
    }

    public void setMunicipioIdmunicipio(Municipio municipioIdmunicipio) {
        this.municipioIdmunicipio = municipioIdmunicipio;
    }

    public Ocupacion getOcupacionIdocupacion() {
        return ocupacionIdocupacion;
    }

    public void setOcupacionIdocupacion(Ocupacion ocupacionIdocupacion) {
        this.ocupacionIdocupacion = ocupacionIdocupacion;
    }

    public Pais getPaisnacimiento() {
        return paisnacimiento;
    }

    public void setPaisnacimiento(Pais paisnacimiento) {
        this.paisnacimiento = paisnacimiento;
    }

    public Tipoidentificacion getTipoidentificacionIdtipoidentificacion() {
        return tipoidentificacionIdtipoidentificacion;
    }

    public void setTipoidentificacionIdtipoidentificacion(Tipoidentificacion tipoidentificacionIdtipoidentificacion) {
        this.tipoidentificacionIdtipoidentificacion = tipoidentificacionIdtipoidentificacion;
    }

    @XmlTransient
    public List<Fotocliente> getFotoclienteList() {
        return fotoclienteList;
    }

    public void setFotoclienteList(List<Fotocliente> fotoclienteList) {
        this.fotoclienteList = fotoclienteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcliente != null ? idcliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.idcliente == null && other.idcliente != null) || (this.idcliente != null && !this.idcliente.equals(other.idcliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Cliente[ idcliente=" + idcliente + " ]";
    }
    
}
