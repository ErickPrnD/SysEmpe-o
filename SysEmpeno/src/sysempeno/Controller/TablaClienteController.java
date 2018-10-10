/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sysempeno.Controller;

import DAO.ClienteIDAO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import sysempeno.model.Cliente;

/**
 * FXML Controller class
 *
 * @author Enrique Ceballos Mtz
 */
public class TablaClienteController implements Initializable {

    private ClienteIDAO clienteIDAO = new ClienteIDAO();
    private Cliente cliente = new Cliente();
    @FXML
    private TextField Nombre;

    @FXML
    private TextField Paterno;

    @FXML
    private TextField Materno;

    @FXML
    private TextField sexo;

    @FXML
    private TextField fecha;

    @FXML
    private TextField rfc;

    @FXML
    private TextField curp;

    @FXML
    private TextField numero;

    @FXML
    private TextField calle;

    @FXML
    private TextField NumeroCa;

    @FXML
    private TextField Colonia;

    @FXML
    private TextField postal;

    @FXML
    private TextField localidad;

    @FXML
    private TextField telefono;

    @FXML
    private TextField email;
    @FXML
    private TextField interior;

    @FXML
    private TextField comentario;

    @FXML
    private TextField busqueda;

    @FXML
    private TableView<Cliente> tablita;
    @FXML
    private TableColumn<Cliente, String> colNombre;

    @FXML
    private TableColumn<Cliente, String> colPaterno;

    @FXML
    private TableColumn<Cliente, String> colMaterno;

    @FXML
    private TableColumn<Cliente, String> colCalle;

    @FXML
    private TableColumn<Cliente, String> colNumero;

    @FXML
    private TableColumn<Cliente, String> colInterior;

    @FXML
    private TableColumn<Cliente, String> colLocalidad;

    @FXML
    private TableColumn<Cliente, String> colColonia;

    @FXML
    private TableColumn<Cliente, String> colTelefono;

    @FXML
    private TableColumn<Cliente, String> ColEmail;

    @FXML
    private TableColumn<Cliente, String> colComentarios;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tablita.getItems().setAll(clienteIDAO.obtenerCliente(""));
        colNombre.setCellValueFactory(data -> data.getValue().nombreProperty());
        colPaterno.setCellValueFactory(data -> data.getValue().apellidoPaternoProperty());
        colMaterno.setCellValueFactory(data -> data.getValue().apellidoMaternoProperty());
        colCalle.setCellValueFactory(data -> data.getValue().calleProperty());
        colNumero.setCellValueFactory(data -> data.getValue().numeroProperty());
        colInterior.setCellValueFactory(data -> data.getValue().interiorProperty());
        colLocalidad.setCellValueFactory(data -> data.getValue().localidadProperty());
        colColonia.setCellValueFactory(data -> data.getValue().coloniaProperty());
        colTelefono.setCellValueFactory(data -> data.getValue().telefonoProperty());
        ColEmail.setCellValueFactory(data -> data.getValue().emailProperty());
        colComentarios.setCellValueFactory(data -> data.getValue().comentariosProperty());
         tablita.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null) {
                cliente = newValue;
                llenarFormulario();
            }
        });
         
    }
    private void llenarFormulario() {
        Nombre.setText(cliente.getNombre());
        Paterno.setText(cliente.getApellidoPaterno());
        Materno.setText(cliente.getApellidoMaterno());
        sexo.setText(cliente.getSexo());
        fecha.setText(cliente.getFechaNacimiento());
        rfc.setText(cliente.getRfc());
        curp.setText(cliente.getCurp());
        numero.setText(cliente.getNumeroIdentificacion());
        calle.setText(cliente.getCalle());
        NumeroCa.setText(cliente.getNumero());
        Colonia.setText(cliente.getColonia());
        postal.setText(cliente.getCodigoPostal());
        localidad.setText(cliente.getLocalidad());
        interior.setText(cliente.getInterior());
        telefono.setText(cliente.getTelefono());
        email.setText(cliente.getEmail());
        comentario.setText(cliente.getComentarios());
        
                ;
    }
    
    @FXML
    private void guardarCliente() {
     
        if (Nombre.getText().trim().isEmpty()) {
            dialogo(Alert.AlertType.ERROR, "Introduce el/los nombre(s) del cliente");
            return;
        }
        cliente.setNombre(Nombre.getText());
        if (Paterno.getText().trim().isEmpty()) {
            dialogo(Alert.AlertType.ERROR, "Introduce el apellido paterno del cliente");
            return;
        }
        cliente.setApellidoPaterno(Paterno.getText());
        if (Materno.getText().trim().isEmpty()) {
            dialogo(Alert.AlertType.ERROR, "Introduce el apellido materno del cliente");
            return;
        }
        cliente.setApellidoMaterno(Materno.getText());
        if (sexo.getText().trim().isEmpty()) {
            dialogo(Alert.AlertType.ERROR, "Introduce el sexo del cliente");
            return;
        }
        cliente.setSexo(sexo.getText());

        if (fecha.getText().trim().isEmpty() || fecha.getText().length()>9) {
            
            dialogo(Alert.AlertType.ERROR, "Introduce la fecha de nacimiento del cliente");
            return;
        }
        cliente.setFechaNacimiento(fecha.getText());

        if (rfc.getText().trim().isEmpty() || rfc.getText().length()>13) {
            dialogo(Alert.AlertType.ERROR, "Introduce el RFC del cliente");
            return;
        }
        cliente.setRfc(rfc.getText());

        if (curp.getText().trim().isEmpty() || curp.getText().length()>18) {
            dialogo(Alert.AlertType.ERROR, "Introduce el CURP del cliente");
            return;
        }
        cliente.setCurp(curp.getText());

        if (numero.getText().trim().isEmpty()) {
            dialogo(Alert.AlertType.ERROR, "Introduce el numero de identificacion del cliente");
            return;
        }
        cliente.setNumeroIdentificacion(numero.getText());

        if (calle.getText().trim().isEmpty()) {
            dialogo(Alert.AlertType.ERROR, "Introduce el nombre de la calle del cliente");
            return;
        }
        cliente.setCalle(calle.getText());

        if (NumeroCa.getText().trim().isEmpty()) {
            dialogo(Alert.AlertType.ERROR, "Introduce el numero de casa del cliente");
            return;
        }
        cliente.setNumero(NumeroCa.getText());

        if (Colonia.getText().trim().isEmpty()) {
            dialogo(Alert.AlertType.ERROR, "Introduce el nobre de la colonia del cliente");
            return;
        }
        cliente.setColonia(Colonia.getText());

        if (postal.getText().trim().isEmpty()) {
            dialogo(Alert.AlertType.ERROR, "Introduce el codigo postal del cliente");
            return;
        }
        cliente.setCodigoPostal(postal.getText());

        if (localidad.getText().trim().isEmpty()) {
            dialogo(Alert.AlertType.ERROR, "Introduce la localidad del cliente");
            return;
        }
        cliente.setLocalidad(localidad.getText());
        if (interior.getText().trim().isEmpty()){
             dialogo(Alert.AlertType.ERROR, "Introduce el telefono del cliente");
            return;
        }
        cliente.setInterior(interior.getText());

        if (telefono.getText().trim().isEmpty()) {
            dialogo(Alert.AlertType.ERROR, "Introduce el telefono del cliente");
            return;
        }
        cliente.setTelefono(telefono.getText());

        if (email.getText().trim().isEmpty()) {
            dialogo(Alert.AlertType.ERROR, "Introduce el correo del cliente");
            return;
        }
        cliente.setEmail(email.getText());

        if (comentario.getText().trim().isEmpty()) {
            dialogo(Alert.AlertType.ERROR, "Introduce los comentarios del cliente");
            return;
        }
        cliente.setComentarios(comentario.getText());

        if (cliente.getidCliente() == null) {
            if (clienteIDAO.nuevoCliente(cliente) > 0) {
                dialogo(Alert.AlertType.INFORMATION, "Cliente creado correctamente");
                tablita.setItems(FXCollections.observableArrayList(clienteIDAO.obtenerCliente("")));

                limpiarFormulario();
            } else {
                dialogo(Alert.AlertType.ERROR, "Ocurrio un error al crear el cliente");
            }
        } else {
            if (clienteIDAO.editarCliente(cliente) > 0) {
                dialogo(Alert.AlertType.INFORMATION, "Cliente editado correctamente");
                tablita.setItems(FXCollections.observableArrayList(clienteIDAO.obtenerCliente("")));
                limpiarFormulario();
            } else {
                dialogo(Alert.AlertType.ERROR, "Ocurrio un error al editar el cliente");
            }
        }
      cliente = new Cliente();
    }

    @FXML
    private void limpiarFormulario() {
        tablita.getSelectionModel().clearSelection();
        Nombre.setText("");
        Paterno.setText("");
        Materno.setText("");
        sexo.setText("");
        fecha.setText("");
        rfc.setText("");
        curp.setText("");
        numero.setText("");
        calle.setText("");
        NumeroCa.setText("");
        Colonia.setText("");
        postal.setText("");
        interior.setText("");
        localidad.setText("");
        telefono.setText("");
        email.setText("");
        comentario.setText("");
    }

    @FXML
    private void actualizarClientes() {
        tablita.getItems().clear();
        if (busqueda.getText().trim().isEmpty()) {
            tablita.setItems(FXCollections.observableArrayList(clienteIDAO.obtenerCliente("")));
        } else {
            tablita.setItems(FXCollections.observableArrayList(clienteIDAO.obtenerCliente(busqueda.getText())));
        }
    }

    public static void dialogo(Alert.AlertType tipo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.show();
    }

}
