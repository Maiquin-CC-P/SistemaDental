/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Dentolife.daos;

import com.Dentolife.connection.ConnectionDB;
import com.Dentolife.dtos.Agenda;
import com.Dentolife.dtos.Cita;
import com.Dentolife.interfaces.OperationDB;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *

 */
public class CitaDAO implements OperationDB<Cita>{
    
    private static ConnectionDB conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet res = null;
    private static CallableStatement cstm = null;
    
    private static final String SQL_INSERT = "insert into CITA values(?,?,?,?,?,?)";
    private static final String SQL_UPDATE = "update CITA set IdPaciente=?, IdDoctor=?, idEspecialidad=?, FechaAtencion=?, HoraAtencion=?, estado=? where IdCita=?";
    private static final String SQL_DELETE = "delete from CITA where IdCita=?";
    private static final String SQL_SELECT_ALL = "select t1.IdCita, (t2.Apellidos+' '+t2.Nombre) Paciente, (t3.Nombre+' '+t3.Apellidos) doctor, Especialidad, t1.FechaAtencion, FORMAT(CONVERT(datetime, t1.HoraAtencion), 'hh:mm tt') + ' ' + RIGHT(CONVERT(VARCHAR(30), t1.HoraAtencion, 9),2) as [HoraAtencion], t1.Estado\n" +
                                                "from CITA t1\n" +
                                                "inner join PACIENTE t2\n" +
                                                "on t1.IdPaciente = t2.IdPaciente\n" +
                                                "inner join DOCTOR t3\n" +
                                                "on t1.IdDoctor = t3.IdDoctor\n" +
                                                "inner join ESPECIALIDAD t4\n" +
                                                "on t1.IdEspecialidad = t4.IdEspecialidad";
    private static final String SQL_SELECT_ID = "select IdCita,IdPaciente,IdDoctor,idEspecialidad,FechaAtencion,CONVERT(VARCHAR(5), HoraAtencion, 108) HoraAtencion,Estado from CITA where IdCita=?";
    private static final String SQL_SELECT_CALENDAR = "select IdCita as id, \n" +
                                                "'Doctor: ' + DOCTOR.Nombre + ' '+ DOCTOR.Apellidos as title,  \n" +
                                                "'Paciente: ' + PACIENTE.Nombre + ' '+ PACIENTE.Apellidos + ', atención en ' + ESPECIALIDAD.Especialidad as description,\n" +
                                                "CAST(CONVERT(Varchar(10),CITA.FechaAtencion, 112)+ ' ' +CONVERT(Varchar(8), CITA.HoraAtencion) AS DateTime)\n" +
                                                "from CITA\n" +
                                                "INNER JOIN PACIENTE\n" +
                                                "ON PACIENTE.IdPaciente=CITA.IdPaciente \n" +
                                                "INNER JOIN DOCTOR\n" +
                                                "ON DOCTOR.IdDoctor=CITA.IdDoctor\n" +
                                                "INNER JOIN ESPECIALIDAD \n" +
                                                "ON ESPECIALIDAD.IdEspecialidad = CITA.IdEspecialidad";
    private static final String SQL_SELECT_REPORT = "{call SP_BUSQUEDA(?)}";
    private static final String SQL_SELECT_REPORT2 = "{call SP_BUSQUEDA2(?)}";
    private static final String SQL_SELECT_REPORT3 = "{call SP_BUSQUEDA3(?)}";
    private static final String SQL_SELECT_VALIDA_PAC= "select count(*) pac from cita where idpaciente=? and FechaAtencion= ?";
    private static final String SQL_SELECT_VALIDA_DOC = "select count(*)doc from cita where iddoctor=? and FechaAtencion= ? and HoraAtencion= ?";

    
    public CitaDAO() {
        conn = ConnectionDB.newInstance();
    }
    
    @Override
    public List<Cita> selectAll() {
        List<Cita> list = new ArrayList<Cita>();
        try {
            pstm = conn.getConnection().prepareStatement(SQL_SELECT_ALL);
            res = pstm.executeQuery();
            
            while (res.next()) {
 		Cita u = new Cita();                
                u.setIdCita(res.getInt(1));                
                u.setPaciente(res.getString(2));                
                u.setDoctor(res.getString(3));                
                u.setEspecialidad(res.getString(4));                
                u.setFechaAtencion(res.getString(5));                
                u.setHoraAtencion(res.getString(6));  
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
    public Boolean insert(Cita t) {
        Boolean result = false;
        int cuenta1=0;
        int cuenta2=0;
        try {
            pstm = conn.getConnection().prepareStatement(SQL_SELECT_VALIDA_PAC);
            pstm.setInt(1, t.getIdPaciente());
            pstm.setString(2, t.getFechaAtencion());
            res = pstm.executeQuery();
            while (res.next()) {
            cuenta1 = res.getInt(1);
            }
            
         if (cuenta1>0){
             closeConnection();
       
          return result;   
            
         }
         
         else if(cuenta1==0){
            
            pstm = conn.getConnection().prepareStatement(SQL_SELECT_VALIDA_DOC);
            pstm.setInt(1, t.getIdDoctor());
            pstm.setString(2, t.getFechaAtencion());
            pstm.setString(3, t.getHoraAtencion());
            res = pstm.executeQuery();
            while (res.next()) {
            cuenta2 = res.getInt(1);
            }
         
         if (cuenta2>0){
             closeConnection();
         return result;   
            
         }    
                
        }
         
            pstm = conn.getConnection().prepareStatement(SQL_INSERT);
            pstm.setInt(1, t.getIdPaciente());
            pstm.setInt(2, t.getIdDoctor());
            pstm.setInt(3, t.getIdEspecialidad());
            pstm.setString(4, t.getFechaAtencion());
            pstm.setString(5, t.getHoraAtencion());
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
    public Boolean update(Cita t) {
        Boolean result = false;
        try {
            pstm = conn.getConnection().prepareStatement(SQL_UPDATE);
            pstm.setInt(1, t.getIdPaciente());
            pstm.setInt(2, t.getIdDoctor());
            pstm.setInt(3, t.getIdEspecialidad());
            pstm.setString(4, t.getFechaAtencion());
            pstm.setString(5, t.getHoraAtencion());
            pstm.setInt(6, t.getEstado());            
            pstm.setInt(7, t.getIdCita()); 
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
    public Boolean recupera(Cita t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean delete(Cita t) {
        Boolean result = false;
        try {
            pstm = conn.getConnection().prepareStatement(SQL_DELETE);           
            pstm.setInt(1, t.getIdCita()); 
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
    public Cita selectById(int id) {
        Cita u = new Cita();
        try {           
            pstm = conn.getConnection().prepareStatement(SQL_SELECT_ID);
            pstm.setInt(1, id);
            res = pstm.executeQuery();
            while (res.next()) {                     
                u.setIdCita(res.getInt(1)); 
                u.setIdPaciente(res.getInt(2)); 
                u.setIdDoctor(res.getInt(3));   
                u.setIdEspecialidad(res.getInt(4));                
                u.setFechaAtencion(res.getString(5));                
                u.setHoraAtencion(res.getString(6));  
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
    public List<Cita> selectByName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<Agenda> selectAgenda() {
        List<Agenda> list = new ArrayList<Agenda>();
        try {
            pstm = conn.getConnection().prepareStatement(SQL_SELECT_CALENDAR);
            res = pstm.executeQuery();
            
            while (res.next()) {
 		Agenda u = new Agenda();                
                u.setId(res.getInt(1)); 
                u.setTitle(res.getString(2));  
                u.setDescription(res.getString(3));                    
                u.setStart(res.getString(4));   
                list.add(u);                
            }            
        } catch (Exception e) {
            System.out.println("Error al consultar" + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }
    
        public ArrayList<Cita> selectBusquedaId(int dato) {
        ArrayList<Cita> list = new ArrayList<>();
        try {
            cstm = conn.getConnection().prepareCall(SQL_SELECT_REPORT);
            cstm.setInt(1, dato);
            res = cstm.executeQuery();           
            while (res.next()) {
 		Cita u = new Cita();                
                u.setIdCita(res.getInt(1));                     
                u.setDoctor(res.getString(2));
                u.setFechaAtencion(res.getString(3));                
                u.setHoraAtencion(res.getString(4));                 
                u.setEspecialidad(res.getString(5));                 
                list.add(u);                
            }            
        } catch (Exception e) {
            System.out.println("Error al consultar" + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }
        public ArrayList<Cita> selectBusquedaId2(int dato) {
        ArrayList<Cita> list = new ArrayList<>();
        try {
            cstm = conn.getConnection().prepareCall(SQL_SELECT_REPORT2);
            cstm.setInt(1, dato);
            res = cstm.executeQuery();           
            while (res.next()) {
 		Cita u = new Cita();                
                u.setIdCita(res.getInt(1));                     
                u.setDoctor(res.getString(2));
                u.setPaciente(res.getString(3)); 
                u.setFechaAtencion(res.getString(4));                
                u.setHoraAtencion(res.getString(5));                 
                               
                list.add(u);                
            }            
        } catch (Exception e) {
            System.out.println("Error al consultar" + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }
        
        public ArrayList<Cita> selectBusquedaId3(int dato) {
        ArrayList<Cita> list = new ArrayList<>();
        try {
            cstm = conn.getConnection().prepareCall(SQL_SELECT_REPORT3);
            cstm.setInt(1, dato);
            res = cstm.executeQuery();           
            while (res.next()) {
 		Cita u = new Cita();                
                u.setIdCita(res.getInt(1));                     
                u.setPaciente(res.getString(2));
                
                u.setFechaAtencion(res.getString(3));                
                u.setHoraAtencion(res.getString(4));
                u.setEspecialidad(res.getString(5)); 
                               
                list.add(u);                
            }            
        } catch (Exception e) {
            System.out.println("Error al consultar" + e.getMessage());
            e.printStackTrace();
        }
        return list;
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
