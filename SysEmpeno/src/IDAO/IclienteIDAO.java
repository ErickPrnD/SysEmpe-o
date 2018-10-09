/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IDAO;

import java.util.List;
import sysempeno.model.Cliente;

/**
 *
 * @author Enrique Ceballos Mtz
 */
public interface IclienteIDAO {
    public int nuevoCliente(Cliente cliente);
    public int  editarCliente(Cliente cliente);
    public List<Cliente> obtenerCliente (String nombre);
   
}
