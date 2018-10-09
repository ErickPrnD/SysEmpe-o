package DAO;

import IDAO.IusuarioIDAO;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import sysempeno.model.Usuario;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


public class UsuarioIDAO implements IusuarioIDAO {

    
     @Override
    public Usuario Inicio (Integer idUsuario, String contrasena) {
        try {
            Usuario usuario = null;
            SqlSession conn = MyBatisUtil.getSession();
            try {
                IusuarioIDAO UsuarioIDAO = conn.getMapper(IusuarioIDAO.class);
                usuario = UsuarioIDAO.Inicio(idUsuario, contrasena);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conn.close();
            }
            return usuario;
        } catch (IOException ex) {
             ex.printStackTrace();
        }
         return null;
    }
}
