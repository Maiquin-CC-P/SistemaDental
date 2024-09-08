/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Dentolife.daos;


import com.Dentolife.connection.ConnectionDB;
import com.Dentolife.dtos.Doctor_Especialidad;
import com.Dentolife.interfaces.OperationDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Doctor_EspecialidadDAO implements OperationDB<Doctor_Especialidad>{
    
    private static ConnectionDB conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet res = null;
    
    private static final String SQL_INSERT = "insert into DOCTOR_ESPECIALIDAD (IdDoctor,IdEspecialidad,Estado) values(?,?,?)";
    private static final String SQL_UPDATE = "";
    private static final String SQL_DELETE = "delete from DOCTOR_ESPECIALIDAD where IdDoctor=? and IdEspecialidad =?";
    private static final String SQL_SELECT_ALL = "select t1.IdDoctor, (Nombre+' '+Apellidos) Nombre, t1.IdEspecialidad, Especialidad, t1.Estado\n" +
                                                 "from DOCTOR_ESPECIALIDAD t1\n" +
                                                 "inner join DOCTOR t2\n" +
                                                 "on t1.IdDoctor = t2.IdDoctor\n" +
                                                 "inner join ESPECIALIDAD t3\n" +
                                                 "on t1.IdEspecialidad = t3.IdEspecialidad order by t1.IdEspecialidad";
    private static final String SQL_SELECT_ID = "select * from DOCTOR_ESPECIALIDAD where IdDoctor=? and IdEspecialidad =?";
    
    public Doctor_EspecialidadDAO() {
        conn = ConnectionDB.newInstance();
    }

    @Override
    public List<Doctor_Especialidad> selectAll() {
        List<Doctor_Especialidad> list = new ArrayList<Doctor_Especialidad>();
        try {
            pstm = conn.getConnection().prepareStatement(SQL_SELECT_ALL);
            res = pstm.executeQuery();
            
            while (res.next()) {
 		Doctor_Especialidad u = new Doctor_Especialidad();                
                u.setIdDoctor(res.getInt(1));  
                u.setDoctor(res.getString(2));
                u.setIdEspecialidad(res.getInt(3));  
                u.setEspecialidad(res.getString(4));
                u.setEstado(res.getInt(5));
                list.add(u);                
            }            
        } catch (Exception e) {
            System.out.println("Error al consultar" + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }
    
    @Override
    public Boolean insert(Doctor_Especialidad t) {
        Boolean result = false;
        try {
            pstm = conn.getConnection().prepareStatement(SQL_INSERT);
            pstm.setInt(1, t.getIdDoctor());
            pstm.setInt(2, t.getIdEspecialidad());
            pstm.setInt(3, t.getEstado());
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
    public Boolean update(Doctor_Especialidad t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean recupera(Doctor_Especialidad t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean delete(Doctor_Especialidad t) {
        Boolean result = false;
         try {
            pstm = conn.getConnection().prepareStatement(SQL_DELETE);
            pstm.setInt(1, t.getIdDoctor()); 
            pstm.setInt(2, t.getIdEspecialidad()); 
            System.out.println("Error al eliminar" + t.getEspecialidad());
            
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
    public Doctor_Especialidad selectById(int id) {
        Doctor_Especialidad u = new Doctor_Especialidad();
        try {           
            pstm = conn.getConnection().prepareStatement(SQL_SELECT_ID);
            pstm.setInt(1, id);
           // pstm.setInt(1, id2);
            res = pstm.executeQuery();
            while (res.next()) {                     
                u.setIdDoctor(res.getInt(1)); 
                u.setIdEspecialidad(res.getInt(2));  
                u.setEstado(res.getInt(3)); 
            }
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeConnection();
        }  
        return u;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Doctor_Especialidad> selectByName() {
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
