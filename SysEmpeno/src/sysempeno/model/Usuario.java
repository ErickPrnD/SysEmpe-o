/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sysempeno.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Enrique Ceballos Mtz
 */
public class Usuario {
    private Integer idUsuario;
    private StringProperty usuario;
    private StringProperty contrasenia;
    private StringProperty nombre;
    private IntegerProperty estatus;
    private StringProperty celular;
    private StringProperty direccion;
    private StringProperty curp;
    private StringProperty irc;
  
    
    
    public Usuario (){
        this.idUsuario = null;
        this.usuario = new SimpleStringProperty();
        this.contrasenia = new SimpleStringProperty();
        this.nombre = new SimpleStringProperty();
        this.direccion = new SimpleStringProperty();
        this.curp = new SimpleStringProperty();
        this.celular =  new SimpleStringProperty();
        this.estatus = new SimpleIntegerProperty();
        
        
    }
    
    public Usuario (Integer idUsuario, String usuario, String contrasenia, String nombre, String direccion, String curp, String celular,Integer estatus ){
       this.idUsuario = idUsuario; 
       this.usuario = new SimpleStringProperty(usuario);
       this.celular = new SimpleStringProperty(celular);
       this.direccion = new SimpleStringProperty(direccion);
       this.curp = new SimpleStringProperty(nombre);
       this.irc = new SimpleStringProperty();
       
       
       
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public StringProperty getUsuario() {
        return usuario;
    }

    public void setUsuario(StringProperty usuario) {
        this.usuario = usuario;
    }

    public StringProperty getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(StringProperty contrasenia) { 
        this.contrasenia = contrasenia;
    }

    public StringProperty getNombre() {
        return nombre;
    }

    public void setNombre(StringProperty nombre) {
        this.nombre = nombre;
    }

    public IntegerProperty getEstatus() {
        return estatus;
    }

    public void setEstatus(IntegerProperty estatus) {
        this.estatus = estatus;
    }

    public StringProperty getCelular() {
        return celular;
    }

    public void setCelular(StringProperty celular) {
        this.celular = celular;
    }

    public StringProperty getDireccion() {
        return direccion;
    }

    public void setDireccion(StringProperty direccion) {
        this.direccion = direccion;
    }

    public StringProperty getCurp() {
        return curp;
    }

    public void setCurp(StringProperty curp) {
        this.curp = curp;
    }

    public StringProperty getIrc() {
        return irc;
    }

    public void setIrc(StringProperty irc) {
        this.irc = irc;
    }
               
    
    
}
