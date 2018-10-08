/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sysempeno.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Enrique Ceballos Mtz
 */
public class Cliente {
    private StringProperty nombre;
    private StringProperty apellidoPaterno;
    private StringProperty apellidoMaterno;
    private StringProperty sexo;
    private StringProperty fechaNacimiento;   //checar esta variable porque el tipo no me cuadra
    private StringProperty rfc;
    private StringProperty numeroIdentificacion;
    private StringProperty curp;
    private StringProperty telefono;
    private StringProperty calle;
    private StringProperty numero;
    private StringProperty interior;
    private StringProperty colonia;
    private StringProperty codigoPostal;
    private StringProperty localidad;
    private StringProperty email;
    private StringProperty comentarios;
 //   private StringProperty huella; 
    
    public Cliente () {
    this.nombre = new SimpleStringProperty();
    this.apellidoPaterno = new SimpleStringProperty();
    this.apellidoMaterno = new SimpleStringProperty();
    this.sexo = new SimpleStringProperty();
    this.fechaNacimiento = new SimpleStringProperty();
    this.rfc = new SimpleStringProperty();
    this.telefono = new SimpleStringProperty();
    this.numeroIdentificacion = new SimpleStringProperty();
    this.calle = new SimpleStringProperty();
    this.numero = new SimpleStringProperty();
    this.interior = new SimpleStringProperty();
    this.colonia = new SimpleStringProperty();
    this.curp = new SimpleStringProperty();
    this.codigoPostal = new SimpleStringProperty();
    this.colonia = new SimpleStringProperty();
  //  this.huella = new SimpleStringProperty();
    this.comentarios = new SimpleStringProperty();
    this.localidad = new SimpleStringProperty();
    
   
    
}

 public Cliente (String nombre, String apellidoPaterno, String apellidoMaterno, String sexo, String fechaNacimiento, String rfc, String numeroIdentificacion,String telefono,
         String calle, String numero,String interior, String colonia, String codigoPostal, String localidad, String email, String comentarios){
     
    this.nombre = new SimpleStringProperty(nombre);
    this.apellidoPaterno = new SimpleStringProperty(apellidoPaterno);
    this.apellidoMaterno = new SimpleStringProperty(apellidoMaterno);
    this.sexo = new SimpleStringProperty(sexo);
    this.fechaNacimiento = new SimpleStringProperty(fechaNacimiento);
    this.rfc = new SimpleStringProperty(rfc);
    this.telefono = new SimpleStringProperty(telefono);
    this.numeroIdentificacion = new SimpleStringProperty(numeroIdentificacion);
    this.calle = new SimpleStringProperty(calle);
    this.numero = new SimpleStringProperty(numero);
    this.interior = new SimpleStringProperty(interior);
    this.colonia = new SimpleStringProperty(colonia);
    this.curp = new SimpleStringProperty();
    this.codigoPostal = new SimpleStringProperty(codigoPostal);
    this.colonia = new SimpleStringProperty(colonia);
  //  this.huella = new SimpleStringProperty();
    this.comentarios = new SimpleStringProperty(comentarios);
    this.localidad = new SimpleStringProperty(localidad);
    
   
 }

    public StringProperty getNombre() {
        return nombre;
    }

    public void setNombre(StringProperty nombre) {
        this.nombre = nombre;
    }

    public StringProperty getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(StringProperty apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public StringProperty getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(StringProperty apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public StringProperty getSexo() {
        return sexo;
    }

    public void setSexo(StringProperty sexo) {
        this.sexo = sexo;
    }

    public StringProperty getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(StringProperty fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public StringProperty getRfc() {
        return rfc;
    }

    public void setRfc(StringProperty rfc) {
        this.rfc = rfc;
    }

    public StringProperty getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(StringProperty numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public StringProperty getCurp() {
        return curp;
    }

    public void setCurp(StringProperty curp) {
        this.curp = curp;
    }

    public StringProperty getTelefono() {
        return telefono;
    }

    public void setTelefono(StringProperty telefono) {
        this.telefono = telefono;
    }

    public StringProperty getCalle() {
        return calle;
    }

    public void setCalle(StringProperty calle) {
        this.calle = calle;
    }

    public StringProperty getNumero() {
        return numero;
    }

    public void setNumero(StringProperty numero) {
        this.numero = numero;
    }

    public StringProperty getInterior() {
        return interior;
    }

    public void setInterior(StringProperty interior) {
        this.interior = interior;
    }

    public StringProperty getColonia() {
        return colonia;
    }

    public void setColonia(StringProperty colonia) {
        this.colonia = colonia;
    }

    public StringProperty getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(StringProperty codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public StringProperty getLocalidad() {
        return localidad;
    }

    public void setLocalidad(StringProperty localidad) {
        this.localidad = localidad;
    }

    public StringProperty getEmail() {
        return email;
    }

    public void setEmail(StringProperty email) {
        this.email = email;
    }

    public StringProperty getComentarios() {
        return comentarios;
    }

    public void setComentarios(StringProperty comentarios) {
        this.comentarios = comentarios;
    }
    
}
