/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sysempeno.Controller;

import DAO.UsuarioIDAO;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sysempeno.SysEmpeno;
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
         clientes();
        } else {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText("Usuario Incorrecto");
        alert.show();
    }
        
        
    }
     public static void clientes() {
        try {
            FXMLLoader loader = new FXMLLoader(SysEmpeno.class.getResource("view/TablaCliente.fxml"));
            Parent root = (Parent) loader.load();
            Scene scene = new Scene(root);
            SysEmpeno.getStage().hide();
           SysEmpeno.getStage().setScene(scene);
            SysEmpeno.getStage().setTitle("Empeños- Administración de clientes");
           SysEmpeno.getStage().setResizable(true);
          SysEmpeno.getStage().sizeToScene();
           SysEmpeno.getStage().show();
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }
    }
}
