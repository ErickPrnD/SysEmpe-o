/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sysempeno.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author erick
 */
public class FXMLRefrendarContratoController implements Initializable {

    @FXML
    private Label lblNumero;
    @FXML
    private TextField txxfAlmacenaje;
    @FXML
    private Label lblDiasExtemporaneos;
    @FXML
    private CheckBox chbEditarPrenda;
    @FXML
    private TextField txfAbono;
    @FXML
    private TextField txfInteres;
    @FXML
    private CheckBox chbEditarImportes;
    @FXML
    private Label lblInteresOrdinario;
    @FXML
    private Label lblDiasOrdinarios;
    @FXML
    private TextField txfIVA;
    @FXML
    private CheckBox chbImpEtiquetas;
    @FXML
    private TextField txfTotal;
    @FXML
    private Button btnGuardar;
    @FXML
    private Label lblDiasFavor;
    @FXML
    private CheckBox chbImpContrato;
    @FXML
    private RadioButton rdbPeriodo;
    @FXML
    private Button btnCancelar;
    @FXML
    private Label lblInteresExtemporaneo;
    @FXML
    private TextField txfReposicion;
    @FXML
    private RadioButton rdbDia;
    @FXML
    private TextField txfAlmacenajeExtemporaneo;
    @FXML
    private ListView lsvPrendas;
    @FXML
    private CheckBox chbReposicion;
    @FXML
    private TextField txfInteresExtemporaneo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        final ToggleGroup tipoCalculo = new ToggleGroup();
        rdbPeriodo.setToggleGroup(tipoCalculo);
        rdbDia.setToggleGroup(tipoCalculo);
        rdbPeriodo.setSelected(true);
        
    }

}
