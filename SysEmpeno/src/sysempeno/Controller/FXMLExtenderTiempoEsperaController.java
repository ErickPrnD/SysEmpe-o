/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sysempeno.Controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author erick
 */
public class FXMLExtenderTiempoEsperaController implements Initializable {

    @FXML
    private DatePicker dtpNueva;
    @FXML
    private Label lblEspera;
    @FXML
    private Button btnGuardar;
    @FXML
    private Label lblMaxima;
    @FXML
    private Button btnCancelar;
    @FXML
    private Label lblLimite;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Callback<DatePicker, DateCell> dayCellFactory = dp -> new DateCell() {
    @Override
    public void updateItem(LocalDate item, boolean empty) {

        super.updateItem(item, empty);
        
        this.setDisable(false);
        this.setBackground(null);
        this.setTextFill(Color.BLACK);
        
        // deshabilitar las fechas pasadas
        if (item.isBefore(LocalDate.now())) {
            this.setDisable(true);
        }
        
        // deshabilitar las fechas futuras
        if (item.isAfter(LocalDate.now())) {
            this.setDisable(true);
        }
    }    
    
};
                }
}