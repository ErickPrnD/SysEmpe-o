/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import IDAO.IclienteIDAO;
import IDAO.IusuarioIDAO;
import java.io.IOException;
import java.util.List;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import sysempeno.model.Cliente;
import sysempeno.model.Usuario;

public class ClienteIDAO implements IclienteIDAO {

    @Override
    public int nuevoCliente(Cliente cliente) {
        int clienteListo = 0;

        try {
            SqlSession conn = MyBatisUtil.getSession();
            try {
                IclienteIDAO ClienteIDAO = conn.getMapper(IclienteIDAO.class);
                clienteListo = ClienteIDAO.nuevoCliente(cliente);
                conn.commit();
            } catch (Exception e) {
                conn.rollback();
                e.printStackTrace();
            } finally {
                conn.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return clienteListo;

    }

  
  @Override
    public int editarCliente(Cliente cliente) {
        int clienteListo = 0;

        try {
            SqlSession conn = MyBatisUtil.getSession();
            try {
                IclienteIDAO ClienteIDAO = conn.getMapper(IclienteIDAO.class);
                clienteListo = ClienteIDAO.editarCliente(cliente);
                conn.commit();
            } catch (Exception e) {
                conn.rollback();
                e.printStackTrace();
            } finally {
                conn.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return clienteListo;

    }
   
      @Override
    public List<Cliente> obtenerCliente(String nombre) {
        List<Cliente> clienteListo = null;

        try {
            SqlSession conn = MyBatisUtil.getSession();
            try {
                IclienteIDAO ClienteIDAO = conn.getMapper(IclienteIDAO.class);
                clienteListo = ClienteIDAO.obtenerCliente(nombre);
            } catch (Exception e) {
               
                e.printStackTrace();
            } finally {
                conn.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return clienteListo;

    }

}
