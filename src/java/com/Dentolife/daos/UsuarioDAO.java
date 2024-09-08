/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Dentolife.daos;

import com.Dentolife.connection.ConnectionDB;
import com.Dentolife.dtos.Usuario;
import com.Dentolife.interfaces.OperationDB;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO implements OperationDB<Usuario> {
    
    private static ConnectionDB conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet res = null;
    
    private static CallableStatement cstm = null;

    // LOS QUERYS 
    private static final String SQL_LOGIN = "select Nombre, Rol from USUARIO where Correo=? and clave=? and estado=0 and rol='ADMINISTRADOR'";
    private static final String SQL_LOGIN_ID = "update USUARIO set clave=? where Correo=?";
    
    private static final String SQL_INSERT = "insert into USUARIO(nombre,apellidos,Correo,Rol,clave,estado) values(?,?,?,?,?,?)";
    
    private static final String SQL_UPDATE = "update USUARIO set nombre=?, apellidos=?, Correo=?, Rol=?, clave=?, estado=? where IdUsuario=?";
    private static final String SQL_DELETE = "delete from USUARIO where IdUsuario=?";
    private static final String SQL_SELECT_ALL = "select IdUsuario,Nombre,Apellidos,Correo,Rol,clave,estado from USUARIO";
    private static final String SQL_SELECT_ID = "select * from USUARIO where IdUsuario=?";
    private static final String SQL_VALIDATE_EMAIL = "select Correo from USUARIO where Correo=?";
    
    public UsuarioDAO() {
        conn = ConnectionDB.newInstance();
    }
    
    public Usuario login(String correo, String clave) {
        Usuario user = null;
        try {
            pstm = conn.getConnection().prepareStatement(SQL_LOGIN);
            pstm.setString(1, correo);
            pstm.setString(2, clave);
            res = pstm.executeQuery();
            while (res.next()) {
                user = new Usuario();
                user.setNombre(res.getString(1));
                user.setRol(res.getString(2));
            }
            
        } catch (Exception e) {
            System.out.println("Error en el login");
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        
        
        return user;        
    }
    
    @Override
    public Boolean recupera(Usuario t) {
        Boolean result = false;
        try {
            pstm = conn.getConnection().prepareStatement(SQL_LOGIN_ID);
            pstm.setString(1, t.getClave());
            pstm.setString(2, t.getCorreo());
            if (pstm.executeUpdate() > 0) {
                result = true;
            }            
            
        } catch (Exception e) {
            System.out.println("Error en actualizar" + e.getMessage());
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return result;        
    }
    
    public Boolean validarCorreo(Usuario t) {
        Boolean result = false;
        try {
            pstm = conn.getConnection().prepareStatement(SQL_VALIDATE_EMAIL);
            pstm.setString(1, t.getCorreo());
             res = pstm.executeQuery();
            while (res.next()) {                     
               result = true;
            }   
        } catch (Exception e) {
            System.out.println("No existe correo" + e.getMessage());
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return result;        
    }

    
    @Override
    public List<Usuario> selectAll() {
        List<Usuario> list = new ArrayList<Usuario>();
        try {
            pstm = conn.getConnection().prepareStatement(SQL_SELECT_ALL);
            res = pstm.executeQuery();
            
            while (res.next()) {
                Usuario u = new Usuario();                
                u.setId(res.getInt(1));                
                u.setApellidos(res.getString(2));                
                u.setNombre(res.getString(3));                
                u.setCorreo(res.getString(4));                
                u.setRol(res.getString(5));                
                u.setClave(res.getString(6));                
                u.setEstado(res.getInt(7));                
                list.add(u);                
            }            
        } catch (Exception e) {
            System.out.println("Error al consultar" + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }
    
    @Override
    public Boolean insert(Usuario t) {
        Boolean result = false;
        try {
            pstm = conn.getConnection().prepareStatement(SQL_INSERT);
            pstm.setString(1, t.getNombre());
            pstm.setString(2, t.getApellidos());
            pstm.setString(3, t.getCorreo());
            pstm.setString(4, t.getRol());
            pstm.setString(5, t.getClave());
            pstm.setInt(6, t.getEstado());            
            if (pstm.executeUpdate() > 0) {
                result = true;
            }            
        } catch (Exception e) {
            System.out.println("Error al insertar" + e.getMessage());
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return result;
    }
    
       
    @Override
    public Boolean update(Usuario t) {
        Boolean result = false;
        try {
            pstm = conn.getConnection().prepareStatement(SQL_UPDATE);
            pstm.setString(1, t.getNombre());
            pstm.setString(2, t.getApellidos());
            pstm.setString(3, t.getCorreo());
            pstm.setString(4, t.getRol());
            pstm.setString(5, t.getClave());
            pstm.setInt(6, t.getEstado());            
            pstm.setInt(7, t.getId()); 
            if (pstm.executeUpdate() > 0) {
                result = true;
            }            
        } catch (Exception e) {
            System.out.println("Error al actulizar" + e.getMessage());
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return result;
    }
    
    @Override
    public Boolean delete(Usuario t) {
        Boolean result = false;
        try {
             System.out.println("Error al eliminar2" + t);
            pstm = conn.getConnection().prepareStatement(SQL_DELETE);           
            pstm.setInt(1, t.getId()); 
            if (pstm.executeUpdate() > 0) {
                result = true;
            }            
        } catch (Exception e) {
            System.out.println("Error al eliminar" + e.getMessage());
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return result;
    }
    
    @Override
    public Usuario selectById(int id) {   
        Usuario u = new Usuario();
        try {           
            pstm = conn.getConnection().prepareStatement(SQL_SELECT_ID);
            pstm.setInt(1, id);
            res = pstm.executeQuery();
            while (res.next()) {                     
                u.setId(res.getInt(1)); 
                u.setNombre(res.getString(2)); 
                u.setApellidos(res.getString(3));   
                u.setCorreo(res.getString(4));                
                u.setRol(res.getString(5));                
                u.setClave(res.getString(6));  
                u.setEstado(res.getInt(7)); 
            }
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeConnection();
        }  
        return u;
    }
    
    @Override
    public List<Usuario> selectByName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void closeConnection() {
        try {
            if (res != null) {
                res.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (conn != null) {
                conn.closeConnection();
            }
        } catch (Exception e) {
            System.out.println("Error al cerrar conexion");
            e.printStackTrace();
        }        
    }
}
