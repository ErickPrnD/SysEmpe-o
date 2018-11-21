/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sysempeno.Controller;

import entities.Contrato;
import entities.Prenda;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import jpa.ContratoJpaController;
import sysempeno.model.Alerta;
import sysempeno.model.Context;

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
    Alerta alerta = new Alerta();
    boolean contratoLleno=false;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ContratoJpaController contratojpa = new ContratoJpaController();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
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

                Contrato contrato = contratojpa.findContrato(Integer.parseInt(txfBusqueda.getText()));
                if (contrato == null) {
                    contratoLleno=false;
                    alerta.alertaInfo("Contrato inexistente", null, "El numero proporcionado no corresponde a un contrato existente");
                } else {
                    contratoLleno=true;
                    Context.getInstance().setContrato(contrato);
                    desplegarInfoContrato(contrato);
                }
            }

            private void desplegarInfoContrato(Contrato contrato) {
                lblNumeroContrato.setText(contrato.getIdcontrato().toString());
                txfCliente.setText(contrato.getClienteIdcliente().toString());
                txfNumeroIdentificacion.setText(contrato.getClienteIdcliente().getNumeroIdentificacion());
//                txfPrestamo.setText(contrato.getTotal().toString()); 
                txfDireccion.setText(contrato.getClienteIdcliente().getDireccion());
                if (contrato.getFechaLimite().after(Calendar.getInstance().getTime())) {
                    if (contrato.getFechaComercializacion().after(Calendar.getInstance().getTime())) {
                        txfEstatus.setText("Comercializado");
                    } else {
                        txfEstatus.setText("Vencido");
                    }
                } else {
                    txfEstatus.setText("Activo");
                }
                if (contrato.getRefrendoAnterior() != null) {
                    txfContratoP.setText(contrato.getRefrendoAnterior().getIdcontrato().toString());
                }
                if (contrato.getRefrendoPosterior() != null) {
                    txfContratoS.setText(contrato.getRefrendoPosterior().getIdcontrato().toString());
                }
                if (contrato.getReempeñoAnterior() != null) {
                    txfReempeñoO.setText(contrato.getReempeñoAnterior().getIdcontrato().toString());
                }
                if (contrato.getReempeñoPosterior() != null) {
                    txfReempeñoS.setText(contrato.getReempeñoPosterior().getIdcontrato().toString());
                }

                txaObservacionesCliente.setText(contrato.getObservacionesCliente());
                txaObservacionesEmp.setText(contrato.getObservacionesEmpeño());
                ObservableList<Prenda> listaPrendas = FXCollections.observableArrayList(contrato.getPrendaList());
                lsvPrendas.setItems(listaPrendas);
                txfNumeroBolsa.setText(Integer.toString(contrato.getNumBolsa()));
                txfFechaLimite.setText(df.format(contrato.getFechaLimite()));
                txfFechaEspera.setText(df.format(contrato.getFechaEspera()));
                txfFechaComercializacion.setText(df.format(contrato.getFechaComercializacion()));
                txfDesempeño.setText(contrato.getDesempeña());
                txfFotos.setText(Integer.toString(contrato.getFotoprendaList().size()));
                txfPrendas.setText(Integer.toString(contrato.getPrendaList().size()));
                txfAbono.setText(Double.toString(contrato.getAbono()));
                Double total = 0d;
                for (int i = 0; i < contrato.getPrendaList().size(); i++) {
                    total = total + contrato.getPrendaList().get(i).getPrestamo();
                }
                txfPrestamoOriginal.setText(Double.toString(total));
                txfCotitular.setText(contrato.getCotitular());
                txfIdCotitular.setText(contrato.getIdentificacionCotitular());
                txfNombreDesempeño.setText(contrato.getDesempeña());
                txfCaptura.setText(contrato.getPrendaList().get(0).getRevisa().toString());
                txfAutoriza.setText(contrato.getPrendaList().get(0).getAutoriza().toString());
                txfDiasRefrendar.setText(Integer.toString(contrato.getDiasRefrendo()));
                txfDesempeñar.setText(Integer.toString(contrato.getDiasDesempeño()));
                txfAcumulado.setText(Double.toString(contrato.getAcumulado()));
                txfIdDesempeño.setText(contrato.getIdentificacionDesempeño());
                txfNumeroIdDesempeño.setText(contrato.getNumIdentificacionDesempeño());
                txfFechaComercializado.setText(df.format(contrato.getFechaComercializacion()));
                if (contrato.getDesempeña() != null) {
                    txfDesempeño.setText(contrato.getDesempeña());
                }
                if (contrato.getNombreDesempeño() != null) {
                    txfNombreDesempeño.setText(contrato.getNombreDesempeño());
                }
                if (contrato.getNumIdentificacionDesempeño() != null) {
                    txfNumeroIdDesempeño.setText(contrato.getNumIdentificacionDesempeño());
                }
                if (contrato.getIdentificacionDesempeño() != null) {
                    txfIdDesempeño.setText(contrato.getIdentificacionDesempeño());
                }

                tbcPrenda.setCellValueFactory(new PropertyValueFactory<Prenda, String>("categoria_idcategoria"));
                tbcAvaluo.setCellValueFactory(new PropertyValueFactory<Prenda, Double>("avaluo"));
                tbcDescripcion.setCellValueFactory(new PropertyValueFactory<Prenda, String>("descripcion"));
                tbcPrestamo.setCellValueFactory(new PropertyValueFactory<Prenda, Double>("prestamo"));
                ObservableList<Prenda> infoPrendas = FXCollections.observableArrayList(contrato.getPrendaList());
                tbvPrendas.setItems(infoPrendas);
            }
        });

        btnRefrendar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    if(contratoLleno){
                    Stage stage = (Stage) btnRefrendar.getScene().getWindow();
                    stage.close();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sysempeno/view/FXMLRefrendarContrato.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    stage.setScene(new Scene(root1));
                    stage.show();
                    }else{
                        alerta.alertaInfo("Sin contrato", null, "Debes seleccionar un contrato antes de poder refrendar");
                    }
                    
                } catch (IOException ex) {
                    Logger.getLogger(FXMLBuscarContratoController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        btnAumentarEspera.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    if(contratoLleno){
                    Stage stage = (Stage) btnAumentarEspera.getScene().getWindow();
                    stage.close();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sysempeno/view/FXMLExtenderTiempoEspera.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    stage.setScene(new Scene(root1));
                    stage.show();
                    }else{
                        alerta.alertaInfo("Sin contrato", null, "Debes seleccionar un contrato antes de poder aumentar el tiempo de espera");
                    }
                    
                } catch (IOException ex) {
                    Logger.getLogger(FXMLBuscarContratoController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

}
