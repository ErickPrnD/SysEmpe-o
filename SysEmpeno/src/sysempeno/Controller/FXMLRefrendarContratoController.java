/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sysempeno.Controller;

import entities.Contrato;
import entities.Parametros;
import entities.Prenda;
import java.net.URL;
import java.util.Calendar;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import jpa.ContratoJpaController;
import jpa.ParametrosJpaController;
import sysempeno.model.Alerta;
import sysempeno.model.Context;

/**
 * FXML Controller class
 *
 * @author erick
 */
public class FXMLRefrendarContratoController implements Initializable {

    @FXML
    private Label lblNumero;
    @FXML
    private TextField txfAlmacenaje;
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
    
    private Contrato contrato=Context.getInstance().getContrato();
    private Double  total;
    Alerta alerta=new Alerta();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        final ToggleGroup tipoCalculo = new ToggleGroup();
        rdbPeriodo.setToggleGroup(tipoCalculo);
        rdbDia.setToggleGroup(tipoCalculo);
        rdbPeriodo.setSelected(true);
        llenarDatos();
    }

    private void llenarDatos() {
        lblNumero.setText(Integer.toString(contrato.getIdcontrato()));
        Calendar mesDespues=Calendar.getInstance();
        mesDespues.add(Calendar.MONTH, 1);
        int diasExtemporaneos=(int) ((Calendar.getInstance().getTime().getTime()-contrato.getFechaLimite().getTime())/86400000);
        int diasOrdinarios=(int) ((mesDespues.getTime().getTime()-Calendar.getInstance().getTime().getTime())/86400000);
        diasOrdinarios=diasOrdinarios-(diasOrdinarios/7);
        lblDiasOrdinarios.setText(Integer.toString(diasOrdinarios));
        if(diasExtemporaneos<0){
            diasExtemporaneos=diasExtemporaneos*-1;
            lblDiasFavor.setText(Integer.toString(diasExtemporaneos));
            lblDiasExtemporaneos.setText("0");
        }else{
            
            lblDiasExtemporaneos.setText(Integer.toString(diasExtemporaneos));
            lblDiasFavor.setText("0");
        }
        ParametrosJpaController parametrosjpa=new ParametrosJpaController();
        List<Parametros> listaparametros=parametrosjpa.findParametrosEntities();
        Parametros parametros=listaparametros.get(listaparametros.size()-1);
        lblInteresOrdinario.setText(Double.toString(parametros.getEmpenoInteres())+" %");
        lblInteresExtemporaneo.setText(Double.toString(parametros.getEmpenoInteresExt())+" %");
        
        Double interes=contrato.getTotal()/100*(parametros.getEmpenoInteres());
        txfInteres.setText(Double.toString(interes));
        Double almacenaje=contrato.getTotal()*(parametros.getEmpenoAlmacenaje()/100);
        txfAlmacenaje.setText(Double.toString(almacenaje));
        Double interesExt=contrato.getTotal()/100*(parametros.getEmpenoInteresExt());
        txfInteresExtemporaneo.setText(Double.toString(interesExt));
        txfAlmacenajeExtemporaneo.setText("0");
        Double iva=contrato.getTotal()*(parametros.getIva()/100);
        txfIVA.setText(Double.toString(iva));
        total=interes+almacenaje+interesExt+iva;
        chbReposicion.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(chbReposicion.isSelected()){
                    total=parametros.getCostoReposicionContrato()+total;
                    txfTotal.setText(Double.toString(total));
                    txfReposicion.setText(Double.toString(parametros.getCostoReposicionContrato()));
                }else{
                    txfReposicion.setText("0");
                    total=total-parametros.getCostoReposicionContrato();
                    txfTotal.setText(Double.toString(total));
                }
            }
        });
        ObservableList<Prenda> prendas=FXCollections.observableArrayList(contrato.getPrendaList());
        lsvPrendas.setItems(prendas);
        txfTotal.setText(Double.toString(total));
        txfReposicion.setText("0");
        
        btnGuardar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                try {
                    Contrato nuevo=contrato;
                    nuevo.setFecha(Calendar.getInstance().getTime());
                    Calendar mesDespues=Calendar.getInstance();
                    mesDespues.add(Calendar.MONTH, 1);
                    nuevo.setFechaLimite(mesDespues.getTime());
                    mesDespues.add(Calendar.DAY_OF_YEAR, 5);
                    nuevo.setFechaEspera(mesDespues.getTime());
                    mesDespues.add(Calendar.MONTH, 1);
                    nuevo.setFechaComercializacion(mesDespues.getTime());
                    nuevo.setNumRefrendos(contrato.getNumRefrendos()+1);
                    nuevo.setAcumulado(contrato.getTotal()+total);
                    nuevo.setTotal(total);
                    ContratoJpaController contratojpa=new ContratoJpaController();
                    nuevo.setIdcontrato(null);
                    contratojpa.create(nuevo);
                    
                    alerta.alertaOk("Refrendo exitoso", null, "El refrendo se ha realizado con exito");
                } catch (Exception ex) {
                    Logger.getLogger(FXMLRefrendarContratoController.class.getName()).log(Level.SEVERE, null, ex);
                    alerta.alertaInfo("Error en refrendo", "No se ha realizado el refrendo", "Ha habido un error en el refrendo");
                }
                
                
            }
        });
                }

}
