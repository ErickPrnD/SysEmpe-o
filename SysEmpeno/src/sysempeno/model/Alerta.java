/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sysempeno.model;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 *
 * @author EricK
 */
public class Alerta {

/**
 * Crea alerta para informacion
 * @param titulo el titulo de la alerta
 * @param cabeza cabeza de la alerta
 * @param mensaje mensaje de alerta
 */
public void alertaInfo(String titulo, String cabeza, String mensaje){
  //Se crea la alerta
  Alert alert = new Alert(AlertType.WARNING);
alert.setTitle(titulo); //Se le pone el titulo
alert.setHeaderText(cabeza);  //Se le pone la cabeza
alert.setContentText(mensaje);  //Se le pone el mensaje

alert.showAndWait();  //Se muestra y espera por respuesta
}

/**
 * Alerta para aviso de todo correcto
 * @param titulo el titulo de la alerta
 * @param cabeza cabeza de la alerta
 * @param mensaje mensaje de alerta
 */
public void alertaOk(String titulo, String cabeza, String mensaje){
  //Se crea la alerta
  Alert alert = new Alert(AlertType.INFORMATION);
alert.setTitle(titulo); //Se le pone el titulo
alert.setHeaderText(cabeza);  //Se le pone la cabeza
alert.setContentText(mensaje);  //Se le pone el mensaje

alert.showAndWait();  //Se muestra y espera por respuesta
}
/**
 * Alerta con confirmacion
 * @param titulo el titulo de la alerta
 * @param cabeza cabeza de la alerta
 * @param mensaje mensaje de alerta
 * @return 
 */
public boolean alertaConfirmacion(String titulo, String cabeza, String mensaje){
  Alert alert = new Alert(AlertType.CONFIRMATION);
alert.setTitle(titulo); //Se le pone el titulo
alert.setHeaderText(cabeza);  //Se le pone la cabeza
alert.setContentText(mensaje);  //Se le pone el mensaje

Optional<ButtonType> result = alert.showAndWait(); //Se muestra y espera por confirmacion
  return result.get() == ButtonType.OK; //Se regresa true si es aceptar o false cancelar
}
}