/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sysempeno.Controller;

import Entities.Contrato;
import Entities.Prenda;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import jpa.ContratoJpaController;
import sysempeno.model.Alerta;

/**
 * FXML Controller class
 *
 * @author erick
 */
public class FXMLBuscarContratoController implements Initializable {

    @FXML
    private Button btnFiniquitar;
    @FXML
    private TextField txfFechaEspera;
    @FXML
    private TextField txfBusqueda;
    @FXML
    private TextField txfNumeroIdDesempeño;
    @FXML
    private TableColumn tbcPrenda;
    @FXML
    private TextField txfReempeñoS;
    @FXML
    private TextField txfFechaComercializacion;
    @FXML
    private TableColumn tbcDescripcion;
    @FXML
    private Button btnNuevoContrato;
    @FXML
    private TextField txfDesempeñar;
    @FXML
    private TextField txfNumeroRefrendos;
    @FXML
    private Button btnRefrendar;
    @FXML
    private TextArea txaObservacionesCliente;
    @FXML
    private Button btnAumentarEspera;
    @FXML
    private TextField txfPrendas;
    @FXML
    private TableColumn tbcAvaluo;
    @FXML
    private TextField txfPrestamo;
    @FXML
    private TextField txfFechaComercializado;
    @FXML
    private TextField txfDiasRefrendar;
    @FXML
    private TextField txfContratoS;
    @FXML
    private TextField txfIdCotitular;
    @FXML
    private TableView tbvPrendas;
    @FXML
    private TextField txfPrestamoOriginal;
    @FXML
    private TextField txfCotitular;
    @FXML
    private TextField txfNumeroIdentificacion;
    @FXML
    private Button btnActualizarObservaciones;
    @FXML
    private TextField txfContratoP;
    @FXML
    private TextField txfFechaLimite;
    @FXML
    private TextField txfIdDesempeño;
    @FXML
    private TextField txfAbono;
    @FXML
    private TableColumn tbcPrestamo;
    @FXML
    private TextField txfDireccion;
    @FXML
    private TextField txfCliente;
    @FXML
    private TextField txfAutoriza;
    @FXML
    private TextField txfReempeñoO;
    @FXML
    private TextArea txaObservacionesEmp;
    @FXML
    private Label lblNumeroContrato;
    @FXML
    private TextField txfNombreDesempeño;
    @FXML
    private TextField txfEstatus;
    @FXML
    private TextField txfCaptura;
    @FXML
    private TextField txfFotos;
    @FXML
    private TextField txfNumeroBolsa;
    @FXML
    private ListView lsvPrendas;
    @FXML
    private Button btnBuscar;
    @FXML
    private TextField txfDesempeño;
    @FXML
    private TextField txfAcumulado;
    Alerta alerta=new Alerta();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txfBusqueda.textProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue,
                            String newValue) {
                        if (!newValue.matches("\\d*")) {
                            txfBusqueda.setText(newValue.replaceAll("[^\\d]", ""));
                        }
                    }
                });
        
        btnBuscar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ContratoJpaController contratojpa=new ContratoJpaController();
                Contrato contrato=contratojpa.findContrato(Integer.parseInt(txfBusqueda.getText()));
                if(contrato==null){
                    
                    alerta.alertaInfo("Contrato inexistente", null, "El numero proporcionado no corresponde a un contrato existente");
                }else{
                    desplegarInfoContrato(contrato);
                }
            }

            private void desplegarInfoContrato(Contrato contrato) {
                lblNumeroContrato.setText(contrato.getIdcontrato().toString());
                txfCliente.setText(contrato.getClienteIdcliente().toString());
                txfNumeroIdentificacion.setText(contrato.getClienteIdcliente().getNumeroIdentificacion());
                //txfPrestamo.setText(contrato.getTotal().toString()); 
                txfDireccion.setText(contrato.getClienteIdcliente().getDireccion());
                txfContratoP.setText(contrato.getRefrendoAnterior().getIdcontrato().toString());
                txfContratoS.setText(contrato.getRefrendoPosterior().getIdcontrato().toString());
                txfReempeñoO.setText(contrato.getReempeñoAnterior().getIdcontrato().toString());
                txfReempeñoS.setText(contrato.getReempeñoPosterior().getIdcontrato().toString());
                txaObservacionesCliente.setText(contrato.getObservacionesCliente());
                txaObservacionesEmp.setText(contrato.getObservacionesEmpeño());
                ObservableList<Prenda> listaPrendas=FXCollections.observableArrayList(contrato.getPrendaList());
                lsvPrendas.setItems(listaPrendas);
                txfNumeroBolsa.setText(Integer.toString(contrato.getNumBolsa()));
                txfFechaLimite.setText(contrato.getFechaLimite().toString());
                txfFechaEspera.setText(contrato.getFechaEspera().toString());
                txfFechaComercializacion.setText(contrato.getFechaComercializacion().toString());
                txfDesempeño.setText(contrato.getDesempeña());
                txfFotos.setText(Integer.toString(contrato.getFotoprendaList().size()));
                txfPrendas.setText(Integer.toString(contrato.getPrendaList().size()));
                txfAbono.setText(Double.toString(contrato.getAbono()));
                //txfPrestamoOriginal.setText(contrato.getTotal().toString());
                txfCotitular.setText(contrato.getCotitular());
                txfIdCotitular.setText(contrato.getIdentificacionCotitular());
                txfNombreDesempeño.setText(contrato.getDesempeña());
                txfCaptura.setText(contrato.getPrendaList().get(0).getRevisa().toString());
                txfAutoriza.setText(contrato.getPrendaList().get(0).getAutoriza().toString());
                //txfNumeroRefrendos.setText(contrato.getNumBolsa());
                txfDiasRefrendar.setText(Integer.toString(contrato.getDiasRefrendo()));
                txfDesempeñar.setText(Integer.toString(contrato.getDiasDesempeño()));
                //txfAcumulado.setText("");
                txfIdDesempeño.setText(contrato.getIdentificacionDesempeño());
                txfNumeroIdDesempeño.setText(contrato.getNumIdentificacionDesempeño());
                txfFechaComercializado.setText(contrato.getFechaComercializacion().toString());
                
            }
        });
    }    
    
    
    
}
