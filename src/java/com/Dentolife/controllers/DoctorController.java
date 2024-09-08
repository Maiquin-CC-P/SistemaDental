/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Dentolife.controllers;

import com.Dentolife.daos.DoctorDAO;
import com.Dentolife.dtos.Doctor;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *

 */
public class DoctorController extends HttpServlet {

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
            case "editar":
                actualizar(request, response);
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

        DoctorDAO doctorDAO = new DoctorDAO();

        List<Doctor> listaDoctor = doctorDAO.selectAll();
        request.setAttribute("lista", listaDoctor);
        request.getRequestDispatcher("lista_doctor.jsp").forward(request, response);
    }
    
    private void nuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        request.getSession().setAttribute("accion", "1");
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("doctor.jsp");
        dispatcher.forward(request, response);
    }
    
    private void registrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int idDoctor = 1;
        String nombre = request.getParameter("txtNombre");
        String apellido = request.getParameter("txtApellido");
        String tipoDocumento = request.getParameter("txtTipo");
        String numDocumento = request.getParameter("txtDocu");
        String numColegiatura = request.getParameter("txtColegiatura");
        String direccion = request.getParameter("txtDire");
        String telefonoFijo = request.getParameter("txtTlf1");
        String telefonoMovil = request.getParameter("txtTlf2");        
        int estado = 0;
        validaciones = "";      
        Doctor doctor = new Doctor(idDoctor,nombre,apellido,tipoDocumento,numDocumento,numColegiatura,direccion,telefonoFijo,telefonoMovil,estado);

        DoctorDAO doctorDAO = new DoctorDAO();

        if (doctorDAO.insert(doctor)) {
            validaciones += "Se ha realizado el registro exitosamente";
            request.setAttribute("validaciones", validaciones);
            request.getRequestDispatcher("doctor.jsp").forward(request, response);
        } else {
            validaciones = "No se realizado el registro";
            request.setAttribute("validaciones", validaciones);
            request.getRequestDispatcher("doctor.jsp").forward(request, response);
        }
    }
    
    private void actualizar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String sid = request.getParameter("txtID");
        String nombre = request.getParameter("txtNombre");
        String apellido = request.getParameter("txtApellido");
        String tipoDocumento = request.getParameter("txtTipo");
        String numDocumento = request.getParameter("txtDocu");
        String numColegiatura = request.getParameter("txtColegiatura");
        String direccion = request.getParameter("txtDire");
        String telefonoFijo = request.getParameter("txtTlf1");
        String telefonoMovil = request.getParameter("txtTlf2");
        int estado = Integer.parseInt(request.getParameter("txtEstado"));
        int id = Integer.parseInt(sid);
        validaciones = "";
        
        Doctor doctor = new Doctor(id, nombre, apellido, tipoDocumento, numDocumento, numColegiatura, direccion, telefonoFijo, telefonoMovil, estado);

        DoctorDAO doctorDAO = new DoctorDAO();
        
        if (doctorDAO.update(doctor)) {
            validaciones = "Se ha realizado la actualización exitosamente";
            request.setAttribute("validaciones", validaciones);
            request.getRequestDispatcher("doctor.jsp").forward(request, response);
        } else {
            validaciones = "No se realizado la actualización";
            request.setAttribute("validaciones", validaciones);
            request.getRequestDispatcher("doctor.jsp").forward(request, response);
        }
    }
    
    private void mostrarID(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        request.getSession().setAttribute("accion", "2");
        
        DoctorDAO doctorDAO = new DoctorDAO();      
        Doctor doctor = doctorDAO.selectById(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("doctor", doctor);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("doctor.jsp");
        dispatcher.forward(request, response);
    }
   
    private void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        
        
        Doctor doctor = new Doctor(id);

        DoctorDAO doctorDAO = new DoctorDAO();
        
         if (doctorDAO.delete(doctor)) {
            List<Doctor> listaDoctor = doctorDAO.selectAll();
            request.setAttribute("lista", listaDoctor);
            request.getRequestDispatcher("lista_doctor.jsp").forward(request, response);
        } else {
            List<Doctor> listaDoctor = doctorDAO.selectAll();
            request.setAttribute("lista", listaDoctor);
            request.getRequestDispatcher("lista_doctor.jsp").forward(request, response);
        }     
    }
}
