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
    private StringProperty contrasena;
    private StringProperty nombre;
    private StringProperty estatus;
    private StringProperty celular;
    private StringProperty direccion;
    private StringProperty curp;
    private StringProperty irc;
  
    
    
    public Usuario (){
        this.idUsuario = null;
        this.usuario = new SimpleStringProperty();
        this.contrasena = new SimpleStringProperty();
        this.nombre = new SimpleStringProperty();
        this.direccion = new SimpleStringProperty();
        this.curp = new SimpleStringProperty();
        this.celular =  new SimpleStringProperty();
        this.estatus = new SimpleStringProperty();
        
        
    }
    
    public Usuario (Integer idUsuario, String usuario, String contrasenia, String nombre, String direccion, String curp, String celular,String estatus, String irc ){
       this.idUsuario = idUsuario; 
       this.usuario = new SimpleStringProperty(usuario);
       this.celular = new SimpleStringProperty(celular);
       this.direccion = new SimpleStringProperty(direccion);
       this.curp = new SimpleStringProperty(nombre);
       this.irc = new SimpleStringProperty(irc);
       this.estatus= new SimpleStringProperty(estatus);
       this.nombre = new SimpleStringProperty(nombre);
    }
    public Integer idUsuario(){
        return idUsuario;
    }
    public  Integer getidUsuario(){
        return idUsuario;
    }
        public void setidUsuario (Integer idUsuario){
            this.idUsuario =idUsuario;
        }
        
 
        public  StringProperty usuarioProperty(){
 
            return usuario;
        }
        
        
        public String getUsuario (){
            return usuario.get();
        }
        public void setUsuario (String usuario){
            this.usuario.set(usuario);
        }
        
       public StringProperty contrasenaProperty(){
           return contrasena;
       }
       public String getContrasena(){
           return contrasena.get();
           
       }
       public void setContrasena (String contrasena){
           this.contrasena.set(contrasena);
       }
       public StringProperty celularProperty(){
           return celular;
       }
       public String celular (){
           return celular.get();
       }
       public void setCelular (String celular){
           this.celular.set(celular);
       }
       public StringProperty direccionProperty(){
           return direccion;
       }
       public String getDireccion(){
           return direccion.get();
       }
       public void setDireccion (String direccion){
           this.direccion.set(direccion);
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
       public StringProperty ircProperty(){
           return irc;
       }
       public String getIrc (){
           return irc.get();
       }
       public void setIrc(String irc){
           this.irc.set(irc);
       }
        public StringProperty estatusProperty(){
            return estatus;
        }
        public String getEstatus (){
            return estatus.get();
        }
        public void setEstatus (String estatus){
            this.estatus.set(estatus);
        }
        public StringProperty nombreProperty(){
            return nombre;
        }
        public String getNombre (){
            return nombre.get();
        }
        public void setNombre(String nombre){
            this.nombre.set(nombre);
            
        }
}