/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Dentolife.controllers;

import com.Dentolife.daos.DoctorDAO;
import com.Dentolife.daos.Doctor_EspecialidadDAO;
import com.Dentolife.daos.EspecialidadDAO;
import com.Dentolife.dtos.Doctor;
import com.Dentolife.dtos.Doctor_Especialidad;
import com.Dentolife.dtos.Especialidad;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**

 */
public class Doctor_EspecialidadController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    String validaciones = "";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String proceso = request.getParameter("txtProceso");
        
        switch (proceso) {
            case "mostrar":
                mostrar(request, response);
                break;
            case "nuevo":
                nuevo(request, response);
                break;
            case "registro":
                registrar(request, response);
                break;
            case "borrar":
                eliminar(request, response);
                break;
            case "mostrarID":
                mostrarID(request, response);
                break;
        }
    }

      // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    private void mostrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Doctor_EspecialidadDAO doctor_especialidadDAO = new Doctor_EspecialidadDAO();
        List<Doctor_Especialidad> listaDoctor_especia = doctor_especialidadDAO.selectAll();
        
        request.setAttribute("lista", listaDoctor_especia);
        
        request.getRequestDispatcher("lista_doctor_especia.jsp").forward(request, response);
    }
    
    private void nuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DoctorDAO doctorDAO = new DoctorDAO();
        List<Doctor> listaDoctor = doctorDAO.selectAll();        
        request.setAttribute("listaD", listaDoctor);
        
        EspecialidadDAO especialidadDAO = new EspecialidadDAO();
        List<Especialidad> lista_especialidad = especialidadDAO.selectAll();
        request.setAttribute("listaE", lista_especialidad);  
        

        
        request.getRequestDispatcher("doctor_especia.jsp").forward(request, response);
    }
    
    private void registrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int idDoctor = Integer.parseInt(request.getParameter("txtDoctor"));;
        int idEspecialidad = Integer.parseInt(request.getParameter("txtEspecia"));
        int estado = 0;
        validaciones = "";      
              
        Doctor_Especialidad doctor_especialidad = new Doctor_Especialidad(idDoctor,idEspecialidad,estado);

        Doctor_EspecialidadDAO doctor_especialidadDAO = new Doctor_EspecialidadDAO();

        if (doctor_especialidadDAO.insert(doctor_especialidad)) {
            validaciones += "Se ha realizado el registro exitosamente";
            request.setAttribute("validaciones", validaciones);
            request.getRequestDispatcher("doctor_especia.jsp").forward(request, response);
        } else {
            validaciones = "No se realizado el registro";
            request.setAttribute("validaciones", validaciones);
            request.getRequestDispatcher("doctor_especia.jsp").forward(request, response);
        }
    }
    
    private void mostrarID(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        request.getSession().setAttribute("accion", "2");
        
        Doctor_EspecialidadDAO doctor_especialidadDAO = new Doctor_EspecialidadDAO();      
        Doctor_Especialidad doctor_especialidad = doctor_especialidadDAO.selectById(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("especialidad", doctor_especialidad);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("lista_doctor.jsp");
        dispatcher.forward(request, response);
    }
   
    private void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
      int id = Integer.parseInt(request.getParameter("id"));
      int id2 = Integer.parseInt(request.getParameter("id2"));
      
      Doctor_Especialidad doctor_Especialidad = new Doctor_Especialidad(id, id2);
      Doctor_EspecialidadDAO doctor_EspecialidadDAO = new Doctor_EspecialidadDAO();
        //Especialidad especialidad = new Especialidad(id);
        //EspecialidadDAO especialidadDAO = new EspecialidadDAO();
        
        if (doctor_EspecialidadDAO.delete(doctor_Especialidad)) {
            List<Doctor_Especialidad> listaEspecialidad = doctor_EspecialidadDAO.selectAll();
            request.setAttribute("lista", listaEspecialidad);
            request.getRequestDispatcher("lista_doctor_especia.jsp").forward(request, response);
        } else {
            List<Doctor_Especialidad> listaEspecialidad = doctor_EspecialidadDAO.selectAll();
            request.setAttribute("lista", listaEspecialidad);
            request.getRequestDispatcher("lista_doctor_especia.jsp").forward(request, response);
        }           
            
    }
}
