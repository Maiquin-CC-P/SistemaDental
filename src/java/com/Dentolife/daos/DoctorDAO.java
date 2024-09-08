/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Dentolife.daos;

import com.Dentolife.connection.ConnectionDB;
import com.Dentolife.dtos.Doctor;
import com.Dentolife.interfaces.OperationDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class DoctorDAO implements OperationDB<Doctor>{
    
    private static ConnectionDB conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet res = null;
    
    private static final String SQL_INSERT = "insert into DOCTOR (Nombre,Apellidos,TipoDocumento,NumDocumento,NumColegiatura,Direccion,TelefonoFijo,TelefonoMovil,Estado) values(?,?,?,?,?,?,?,?,?)";
    private static final String SQL_UPDATE = "update DOCTOR set Nombre=?, Apellidos=?, TipoDocumento=?, NumDocumento=?, NumColegiatura=?, Direccion=?, TelefonoFijo=?,TelefonoMovil=?,Estado=? where IdDoctor=?";
    private static final String SQL_DELETE = "delete from DOCTOR where IdDoctor=?";
    private static final String SQL_SELECT_ALL = "select * from DOCTOR";
    private static final String SQL_SELECT_ID = "select * from DOCTOR where IdDoctor=?";
    
    public DoctorDAO() {
        conn = ConnectionDB.newInstance();
    }
    
    @Override
    public List<Doctor> selectAll() {
        List<Doctor> list = new ArrayList<Doctor>();
        try {
            pstm = conn.getConnection().prepareStatement(SQL_SELECT_ALL);
            res = pstm.executeQuery();
            
            while (res.next()) {
 		Doctor u = new Doctor();                
                u.setIdDoctor(res.getInt(1));                
                u.setNombre(res.getString(2));                
                u.setApellidos(res.getString(3));                
                u.setTipoDocumento(res.getString(4));                
                u.setNumDocumento(res.getString(5));                
                u.setNumColegiatura(res.getString(6));  
                u.setDireccion(res.getString(7));
                u.setTelefonoFijo(res.getString(8));
                u.setTelefonoMovil(res.getString(9));
                u.setEstado(res.getInt(10));
                list.add(u);                
            }            
        } catch (Exception e) {
            System.out.println("Error al consultar" + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }
    
    @Override
    public Boolean insert(Doctor t) {
        Boolean result = false;
        try {
            pstm = conn.getConnection().prepareStatement(SQL_INSERT);
            pstm.setString(1, t.getNombre());
            pstm.setString(2, t.getApellidos());
            pstm.setString(3, t.getTipoDocumento());
            pstm.setString(4, t.getNumDocumento());
            pstm.setString(5, t.getNumColegiatura());
            pstm.setString(6, t.getDireccion());  
            pstm.setString(7, t.getTelefonoFijo());
            pstm.setString(8, t.getTelefonoMovil());
            pstm.setInt(9, 1);
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
    public Boolean update(Doctor t) {
        Boolean result = false;
        try {
            pstm = conn.getConnection().prepareStatement(SQL_UPDATE);
            pstm.setString(1, t.getNombre());
            pstm.setString(2, t.getApellidos());
            pstm.setString(3, t.getTipoDocumento());
            pstm.setString(4, t.getNumDocumento());
            pstm.setString(5, t.getNumColegiatura());
            pstm.setString(6, t.getDireccion());
            pstm.setString(7, t.getTelefonoFijo());
            pstm.setString(8, t.getTelefonoMovil());
            pstm.setInt(9, t.getEstado());            
            pstm.setInt(10, t.getIdDoctor()); 
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
    public Boolean recupera(Doctor t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean delete(Doctor t) {
        Boolean result = false;
        try {
            pstm = conn.getConnection().prepareStatement(SQL_DELETE);           
            pstm.setInt(1, t.getIdDoctor()); 
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
    public Doctor selectById(int id) {
        Doctor u = new Doctor();
        try {           
            pstm = conn.getConnection().prepareStatement(SQL_SELECT_ID);
            pstm.setInt(1, id);
            res = pstm.executeQuery();
            while (res.next()) {                     
                u.setIdDoctor(res.getInt(1)); 
                u.setNombre(res.getString(2)); 
                u.setApellidos(res.getString(3));   
                u.setTipoDocumento(res.getString(4));                
                u.setNumDocumento(res.getString(5));                
                u.setNumColegiatura(res.getString(6));  
                u.setDireccion(res.getString(7));                
                u.setTelefonoFijo(res.getString(8));  
                u.setTelefonoMovil(res.getString(9));  
                u.setEstado(res.getInt(10)); 
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
    public List<Doctor> selectByName() {
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
