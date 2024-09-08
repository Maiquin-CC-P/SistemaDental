/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Dentolife.daos;

import com.Dentolife.connection.ConnectionDB;
import com.Dentolife.dtos.Paciente;
import com.Dentolife.interfaces.OperationDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *

 */
public class PacienteDAO implements OperationDB<Paciente>{
    
    private static ConnectionDB conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet res = null;
    
    private static final String SQL_INSERT = "insert into PACIENTE values(?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String SQL_UPDATE = "update PACIENTE set Nombre=?, Apellidos=?, TipoDocumento=?, NumDocumento=?,Correo=?, Sexo=?, FechaNAcimeinto=?, Direccion=?, TelefonoFijo=?, TelefonoMovil=?, Observaciones=?, estado=?  where IdPaciente=?";
    private static final String SQL_DELETE = "delete from PACIENTE where IdPaciente=?";
    private static final String SQL_SELECT_ALL = "select IdPaciente,Nombre,Apellidos,TipoDocumento,NumDocumento,Correo,TelefonoFijo,TelefonoMovil,Estado from PACIENTE";
    private static final String SQL_SELECT_ID = "select * from PACIENTE where IdPaciente=?";

    public PacienteDAO() {
        conn = ConnectionDB.newInstance();
    }
       
    @Override
    public List<Paciente> selectAll() {
        List<Paciente> list = new ArrayList<Paciente>();
        try {
            pstm = conn.getConnection().prepareStatement(SQL_SELECT_ALL);
            res = pstm.executeQuery();
            
            while (res.next()) {
 		Paciente u = new Paciente();                
                u.setIdPaciente(res.getInt(1));                
                u.setNombre(res.getString(2));                
                u.setApellidos(res.getString(3));                
                u.setTipoDocumento(res.getString(4));                
                u.setNumDocumento(res.getString(5));                
                u.setCorreo(res.getString(6));  
                u.setTelefonoFijo(res.getString(7));
                u.setTelefonoMovil(res.getString(8));
                u.setEstado(res.getInt(9));
                list.add(u);                
            }            
        } catch (Exception e) {
            System.out.println("Error al consultar" + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Boolean insert(Paciente t) {
        Boolean result = false;
        try {
            pstm = conn.getConnection().prepareStatement(SQL_INSERT);
            pstm.setString(1, t.getNombre());
            pstm.setString(2, t.getApellidos());
            pstm.setString(3, t.getTipoDocumento());
            pstm.setString(4, t.getNumDocumento());
            pstm.setString(5, t.getCorreo());
            pstm.setString(6, t.getSexo());            
            pstm.setString(7, t.getFechaNAcimeinto());          
            pstm.setString(8, t.getDireccion());
            pstm.setString(9, t.getTelefonoFijo());
            pstm.setString(10, t.getTelefonoMovil());
            pstm.setString(11, t.getObservaciones());
            pstm.setInt(12, t.getEstado());
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
    public Boolean update(Paciente t) {
        Boolean result = false;
        try {
            pstm = conn.getConnection().prepareStatement(SQL_UPDATE);
            pstm.setString(1, t.getNombre());
            pstm.setString(2, t.getApellidos());
            pstm.setString(3, t.getTipoDocumento());
            pstm.setString(4, t.getNumDocumento());
            pstm.setString(5, t.getCorreo());
            pstm.setString(6, t.getSexo());
            pstm.setString(7, t.getFechaNAcimeinto());       
            pstm.setString(8, t.getDireccion());
            pstm.setString(9, t.getTelefonoFijo());
            pstm.setString(10, t.getTelefonoMovil()); 
            pstm.setString(11, t.getObservaciones());
            pstm.setInt(12, t.getEstado());
            pstm.setInt(13, t.getIdPaciente()); 
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
    public Boolean recupera(Paciente t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean delete(Paciente t) {
        Boolean result = false;
        try {
            pstm = conn.getConnection().prepareStatement(SQL_DELETE);           
            pstm.setInt(1, t.getIdPaciente()); 
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
    public Paciente selectById(int id) {
        Paciente u = new Paciente();
        try {           
            pstm = conn.getConnection().prepareStatement(SQL_SELECT_ID);
            pstm.setInt(1, id);
            res = pstm.executeQuery();
            while (res.next()) {                     
                u.setIdPaciente(res.getInt(1)); 
                u.setNombre(res.getString(2)); 
                u.setApellidos(res.getString(3));   
                u.setTipoDocumento(res.getString(4));                
                u.setNumDocumento(res.getString(5));                
                u.setCorreo(res.getString(6));  
                u.setSexo(res.getString(7)); 
                u.setFechaNAcimeinto(res.getString(8)); 
                u.setDireccion(res.getString(9));                
                u.setTelefonoFijo(res.getString(10));  
                u.setTelefonoMovil(res.getString(11));  
                u.setObservaciones(res.getString(12));  
                u.setEstado(res.getInt(13)); 
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
    public List<Paciente> selectByName() {
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
