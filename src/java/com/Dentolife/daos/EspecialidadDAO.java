/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Dentolife.daos;


import com.Dentolife.connection.ConnectionDB;
import com.Dentolife.dtos.Especialidad;
import com.Dentolife.interfaces.OperationDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EspecialidadDAO implements OperationDB<Especialidad>{
    
    private static ConnectionDB conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet res = null;
    
    private static final String SQL_INSERT = "insert into ESPECIALIDAD (Especialidad,Estado) values(?,?)";
    private static final String SQL_UPDATE = "update ESPECIALIDAD set Especialidad=?, Estado=? where IdEspecialidad=?";
    private static final String SQL_DELETE = "delete from ESPECIALIDAD where IdEspecialidad=?";
    private static final String SQL_SELECT_ALL = "select * from ESPECIALIDAD";
    private static final String SQL_SELECT_ID = "select * from ESPECIALIDAD where IdEspecialidad=?";
    
    public EspecialidadDAO() {
        conn = ConnectionDB.newInstance();
    }
    
    @Override
    public List<Especialidad> selectAll() {
        List<Especialidad> list = new ArrayList<Especialidad>();
        try {
            pstm = conn.getConnection().prepareStatement(SQL_SELECT_ALL);
            res = pstm.executeQuery();
            
            while (res.next()) {
 		Especialidad u = new Especialidad();                
                u.setIdEspecialidad(res.getInt(1));                
                u.setEspecialidad(res.getString(2));                
                u.setEstado(res.getInt(3));   
                list.add(u);                
            }            
        } catch (Exception e) {
            System.out.println("Error al consultar" + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }
    
    @Override
    public Boolean insert(Especialidad t) {
        Boolean result = false;
        try {
            pstm = conn.getConnection().prepareStatement(SQL_INSERT);
            pstm.setString(1, t.getEspecialidad());
            pstm.setInt(2, t.getEstado());
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
    public Boolean update(Especialidad t) {
        Boolean result = false;
        try {
            pstm = conn.getConnection().prepareStatement(SQL_UPDATE);
            pstm.setString(1, t.getEspecialidad());
            pstm.setInt(2, t.getEstado());            
            pstm.setInt(3, t.getIdEspecialidad()); 
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
    public Boolean recupera(Especialidad t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean delete(Especialidad t) {
        Boolean result = false;
        try {
            pstm = conn.getConnection().prepareStatement(SQL_DELETE);           
            pstm.setInt(1, t.getIdEspecialidad()); 
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
    public Especialidad selectById(int id) {
        Especialidad u = new Especialidad();
        try {           
            pstm = conn.getConnection().prepareStatement(SQL_SELECT_ID);
            pstm.setInt(1, id);
            res = pstm.executeQuery();
            while (res.next()) {                     
                u.setIdEspecialidad(res.getInt(1)); 
                u.setEspecialidad(res.getString(2));  
                u.setEstado(res.getInt(3)); 
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
    public List<Especialidad> selectByName() {
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
