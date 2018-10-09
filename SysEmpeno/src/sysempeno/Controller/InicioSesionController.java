/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sysempeno.Controller;

import DAO.UsuarioIDAO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sysempeno.model.Usuario;

/**
 * FXML Controller class
 *
 * @author Enrique Ceballos Mtz
 */
public class InicioSesionController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML TextField usuario;
    @FXML PasswordField contrasenia;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    public void ingresar (){
        String usuarioLogin = usuario.getText();
        String contraseniaLogin = contrasenia.getText();
        Integer probada=Integer .parseInt(usuarioLogin);
        UsuarioIDAO usuarioIDAO = new UsuarioIDAO();
        Usuario usuariotemp = usuarioIDAO.Inicio(probada, contraseniaLogin);
       
        if (usuariotemp != null){
            System.out.println("ya");
        } else {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText("Usuario Incorrecto");
        alert.show();
    }
        
    }
}
