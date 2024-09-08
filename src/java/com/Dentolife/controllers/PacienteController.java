/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Dentolife.controllers;

import com.Dentolife.daos.PacienteDAO;
import com.Dentolife.dtos.Paciente;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author Josselyn
 */
public class PacienteController extends HttpServlet {

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
    SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");

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

        PacienteDAO pacienteDAO = new PacienteDAO();

        List<Paciente> listaPaciente = pacienteDAO.selectAll();
        request.setAttribute("lista", listaPaciente);
        request.getRequestDispatcher("lista_paciente.jsp").forward(request, response);
    }

    private void nuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getSession().setAttribute("accion", "1");
        request.getRequestDispatcher("paciente.jsp").forward(request, response);
    }

    private void registrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int idPaciente = 1;
        String nombre = request.getParameter("txtNombre");
        String apellido = request.getParameter("txtApellido");
        String tipoDocumento = request.getParameter("txtTipo");
        String numDocumento = request.getParameter("txtDocu");
        String correo = request.getParameter("txtCorreo");
        String sexo = request.getParameter("txtSexo");
        String fechaNAcimiento = request.getParameter("txtFecha");
        String direccion = request.getParameter("txtDire");
        String telefonoFijo = request.getParameter("txtTlf1");
        String telefonoMovil = request.getParameter("txtTlf2");
        String observaciones = request.getParameter("txtObse");
        int estado = 0;
        validaciones = "";

        Paciente paciente = new Paciente(idPaciente, nombre, apellido, tipoDocumento, numDocumento, correo, sexo, fechaNAcimiento, direccion, telefonoFijo, telefonoMovil, observaciones, estado);

        PacienteDAO pacienteDAO = new PacienteDAO();

        if (pacienteDAO.insert(paciente)) {
            validaciones += "Se ha realizado el registro exitosamente";
            request.setAttribute("validaciones", validaciones);
            request.getRequestDispatcher("paciente.jsp").forward(request, response);
        } else {
            validaciones = "No se realizado el registro";
            request.setAttribute("validaciones", validaciones);
            request.getRequestDispatcher("paciente.jsp").forward(request, response);
        }
    }

    private void actualizar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("txtID"));
        String nombre = request.getParameter("txtNombre");
        String apellido = request.getParameter("txtApellido");
        String tipoDocumento = request.getParameter("txtTipo");
        String numDocumento = request.getParameter("txtDocu");
        String correo = request.getParameter("txtCorreo");
        String sexo = request.getParameter("txtSexo");
        String fechaNAcimiento = request.getParameter("txtFecha");
        String direccion = request.getParameter("txtDire");
        String telefonoFijo = request.getParameter("txtTlf1");
        String telefonoMovil = request.getParameter("txtTlf2");
        String observaciones = request.getParameter("txtObse");
        int estado = 0;
        validaciones = "";

        Paciente paciente = new Paciente(id, nombre, apellido, tipoDocumento, numDocumento, correo, sexo, fechaNAcimiento, direccion, telefonoFijo, telefonoMovil, observaciones, estado);

        PacienteDAO pacienteDAO = new PacienteDAO();

        if (pacienteDAO.update(paciente)) {
            validaciones = "Se ha realizado la actualización exitosamente";
            request.setAttribute("validaciones", validaciones);
            request.getRequestDispatcher("paciente.jsp").forward(request, response);
        } else {
            validaciones = "No se realizado la actualización";
            request.setAttribute("validaciones", validaciones);
            request.getRequestDispatcher("paciente.jsp").forward(request, response);
        }
    }

    private void mostrarID(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getSession().setAttribute("accion", "2");

        PacienteDAO pacienteDAO = new PacienteDAO();
        Paciente paciente = pacienteDAO.selectById(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("paciente", paciente);

        RequestDispatcher dispatcher = request.getRequestDispatcher("paciente.jsp");
        dispatcher.forward(request, response);
    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        
        Paciente paciente = new Paciente(id);
        PacienteDAO pacienteDAO = new PacienteDAO();
        
        if (pacienteDAO.delete(paciente)) {
            List<Paciente> listaPaciente = pacienteDAO.selectAll();
            request.setAttribute("lista", listaPaciente);
            request.getRequestDispatcher("lista_paciente.jsp").forward(request, response);
        } else {
            List<Paciente> listaPaciente = pacienteDAO.selectAll();
            request.setAttribute("lista", listaPaciente);
            request.getRequestDispatcher("lista_paciente.jsp").forward(request, response);
        }           
    }

}
