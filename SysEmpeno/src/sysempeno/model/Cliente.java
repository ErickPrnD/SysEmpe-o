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
    private Integer idCliente;
    private StringProperty nombre;
    private StringProperty apellidoPaterno;
    private StringProperty apellidoMaterno;
    private StringProperty sexo;
    private StringProperty fechaNacimiento; 
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
    private Object huella; 
    
    public Cliente () {
    this.idCliente = null;
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
    this.email = new SimpleStringProperty();
    this.huella = new Object();
    this.comentarios = new SimpleStringProperty();
    this.localidad = new SimpleStringProperty();
    
   
    
}

 public Cliente (Integer idCliente,String nombre, String apellidoPaterno, String apellidoMaterno, String sexo, String fechaNacimiento, String rfc, String numeroIdentificacion,String telefono,
         String calle, String numero,String interior, String colonia, String codigoPostal, String localidad, String email, String comentarios, Object huella){
   this.idCliente = idCliente;
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
    this.email = new SimpleStringProperty(email);
    this.huella = huella;
    this.comentarios = new SimpleStringProperty(comentarios);
    this.localidad = new SimpleStringProperty(localidad);
    
    
   
 }

  public Integer getidCliente(){
      return idCliente;
  }
  public void setidCliente(Integer idCliente){
      this.idCliente = idCliente;
  }
  public Object getHuella(){
      return huella;
  }
  public void setHuella(Object huella){
      this.huella=huella;
  }
  public StringProperty nombreProperty(){
      return nombre;
  }
  public String getNombre(){
      return nombre.get();
  }
  public void setNombre (String nombre){
      this.nombre.set(nombre);
  }
  public StringProperty apellidoPaternoProperty(){
      return apellidoPaterno;
  }
  public String getApellidoPaterno(){
      return apellidoPaterno.get();
  }
  public void setApellidoPaterno(String apellidoPaterno){
      this.apellidoPaterno.set(apellidoPaterno);
  }
    public StringProperty apellidoMaternoProperty(){
        return apellidoMaterno;
    }
    public String getApellidoMaterno(){
        return apellidoMaterno.get();
    }
    public void setApellidoMaterno(String apellidoMaterno){
        this.apellidoMaterno.set(apellidoMaterno);
    }
    public StringProperty sexoProperty(){
        return sexo;
    }
    public String getSexo(){
        return sexo.get();
    }
    public void setSexo (String sexo){
        this.sexo.set(sexo);
    }
    public StringProperty fechaNacimientoProperty(){
        return fechaNacimiento;
    }
    public String getFechaNacimiento(){
        return fechaNacimiento.get();
    }
    public void setFechaNacimiento(String fechaNacimiento){
        this.fechaNacimiento.set(fechaNacimiento);
    }
    public StringProperty rfcProperty(){
        return rfc;
    }
    public String getRfc(){
        return rfc.get();
    }
    public void setRfc(String rfc){
        this.rfc.set(rfc);
    }
    public StringProperty telefonoProperty(){
        return telefono;
    }
    public String getTelefono(){
        return telefono.get();
    }
    public void setTelefono(String telefono){
        this.telefono.set(telefono);
    }
    public StringProperty numeroIdentificacion(){
        return numeroIdentificacion;
    }
    public String getNumeroIdentificacion(){
        return numeroIdentificacion.get();
    }
    public void setNumeroIdentificacion(String numeroIdentificacion){
        this.numeroIdentificacion.set(numeroIdentificacion);
    }
    public StringProperty calleProperty(){
        return calle;
    }
    public String getCalle(){
        return calle.get();
    }
    public void setCalle(String calle){
        this.calle.set(calle);
    }
    public StringProperty numeroProperty(){
        return numero;
    }
    public String getNumero(){
        return numero.get();
    }
    public void setNumero(String numero){
        this.numero.set(numero);
    }
    public StringProperty interiorProperty(){
        return interior;
    }
    public String getInterior(){
        return interior.get();
    }
    public void setInterior(String interior){
        this.interior.set(interior);
    }
    public StringProperty coloniaProperty(){
        return colonia;
       
    }
    public String getColonia(){
        return colonia.get();
    }
    public void setColonia(String colonia){
        this.colonia.set(colonia);
    }
 public StringProperty curpProperty(){
     return curp;
 }
 public String getCurp(){
     return curp.get();
 }
 public void setCurp(String curp){
     this.curp.set(curp);
      }
 public StringProperty codigoPostalProperty(){
     return codigoPostal;
 }
 public String getCodigoPostal(){
     return codigoPostal.get();
 }
 public void setCodigoPostal(String codigoPostal){
     this.codigoPostal.set(codigoPostal);
 }
 public StringProperty emailProperty(){
     return email;
 }
 public String getEmail(){
     return email.get();
 }
 public void setEmail (String email){
     this.email.set(email);
 }
 public StringProperty comentariosProperty(){
     return comentarios;
 }
 public String getComentarios(){
     return comentarios.get();
 }
 public void setComentarios(String comentarios){
     this.comentarios.set(comentarios);
 }
 public StringProperty localidadProperty(){
     return localidad;
 }
 public String getLocalidad(){
     return localidad.get();
 }
 public void setLocalidad(String localidad){
     this.localidad.set(localidad);
 }
 
}

