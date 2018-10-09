/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IDAO;

import org.apache.ibatis.annotations.Param;
import sysempeno.model.Usuario;

/**
 *
 * @author Enrique Ceballos Mtz
 */
public interface IusuarioIDAO {
    public Usuario Inicio (@Param("idUsuario")Integer idUsuario,@Param("contrasena") String contrasena);
    
}