/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sysempeno.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author erick
 */
public class FXMLRegistrarPrendaController implements Initializable {

    @FXML
    private TextArea txaDescripcion;
    @FXML
    private TextField txfAprobada;
    @FXML
    private TextField txfNumeroSerie;
    @FXML
    private TextField txfClaridad;
    @FXML
    private TextField txfColor;
    @FXML
    private CheckBox chbGema;
    @FXML
    private TextField txfPeso;
    @FXML
    private TextField txfNumeroPiezas;
    @FXML
    private ComboBox cbbSubcategoria;
    @FXML
    private TextField txfKilataje;
    @FXML
    private TextField txfMaxPrestamo;
    @FXML
    private TextField txfRevisada;
    @FXML
    private ComboBox cbbCategoria;
    @FXML
    private TextField txfModelo;
    @FXML
    private Button btnGuardar;
    @FXML
    private CheckBox chbOro;
    @FXML
    private Button btnCancelar;
    @FXML
    private TextField txfPrestamo;
    @FXML
    private TextField txfMaxRefrendos;
    @FXML
    private TextField txfAvaluo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txfClaridad.setVisible(false);
        txfColor.setVisible(false);

        txfNumeroPiezas.textProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue,
                            String newValue) {
                        if (!newValue.matches("\\d*")) {
                            txfNumeroPiezas.setText(newValue.replaceAll("[^\\d]", ""));
                        }
                    }
                });

                txfPeso.textProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue,
                            String newValue) {
                        if (!newValue.matches("\\d*")) {
                            txfPeso.setText(newValue.replaceAll("[^\\d]", ""));
                        }
                    }
                });
                
                txfKilataje.textProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue,
                            String newValue) {
                        if (!newValue.matches("\\d*")) {
                            txfKilataje.setText(newValue.replaceAll("[^\\d]", ""));
                        }
                    }
                });
    }

}
