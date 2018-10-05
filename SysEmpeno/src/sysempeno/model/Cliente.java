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
    
}
