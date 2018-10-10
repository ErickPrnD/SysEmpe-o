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
import javax.persistence.Lob;
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
@Table(name = "fotoprenda")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fotoprenda.findAll", query = "SELECT f FROM Fotoprenda f")
    , @NamedQuery(name = "Fotoprenda.findByIdfoto", query = "SELECT f FROM Fotoprenda f WHERE f.idfoto = :idfoto")})
public class Fotoprenda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idfoto")
    private Integer idfoto;
    @Basic(optional = false)
    @Lob
    @Column(name = "foto")
    private byte[] foto;
    @JoinColumn(name = "contrato_idcontrato", referencedColumnName = "idcontrato")
    @ManyToOne(optional = false)
    private Contrato contratoIdcontrato;

    public Fotoprenda() {
    }

    public Fotoprenda(Integer idfoto) {
        this.idfoto = idfoto;
    }

    public Fotoprenda(Integer idfoto, byte[] foto) {
        this.idfoto = idfoto;
        this.foto = foto;
    }

    public Integer getIdfoto() {
        return idfoto;
    }

    public void setIdfoto(Integer idfoto) {
        this.idfoto = idfoto;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public Contrato getContratoIdcontrato() {
        return contratoIdcontrato;
    }

    public void setContratoIdcontrato(Contrato contratoIdcontrato) {
        this.contratoIdcontrato = contratoIdcontrato;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idfoto != null ? idfoto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fotoprenda)) {
            return false;
        }
        Fotoprenda other = (Fotoprenda) object;
        if ((this.idfoto == null && other.idfoto != null) || (this.idfoto != null && !this.idfoto.equals(other.idfoto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Fotoprenda[ idfoto=" + idfoto + " ]";
    }
    
}
