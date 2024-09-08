/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Dentolife.controllers;

import com.Dentolife.daos.CitaDAO;
import com.Dentolife.daos.DoctorDAO;
import com.Dentolife.daos.EspecialidadDAO;
import com.Dentolife.daos.PacienteDAO;
import com.Dentolife.daos.UsuarioDAO;
import com.Dentolife.dtos.Cita;
import com.Dentolife.dtos.Doctor;
import com.Dentolife.dtos.Especialidad;
import com.Dentolife.dtos.Paciente;
import com.Dentolife.dtos.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*

 */
public class ReportesController extends HttpServlet {

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
    // String accion = ""; //1 registrar //2 modificar //3 anular

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String proceso = request.getParameter("txtProceso");

        switch (proceso) {
            case "reportexpaciente":
                reportexpaciente(request, response);
                break;
            case "reportexdoctor":
                reportexdoctor(request, response);
                break;
            case "reportexespecialidad":
                reportexespecialidad(request, response);
                break;
            case "busqueda":
                busqueda(request, response);
                break;
             case "busqueda2":
                busqueda2(request, response);
             break;
             case "busqueda3":
                busqueda3(request, response);
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

     private void reportexespecialidad(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //    request.getSession().setAttribute("accion", "1");

        EspecialidadDAO especialidadDAO = new EspecialidadDAO();
        List<Especialidad> listaEspecialidad = especialidadDAO.selectAll();
        request.setAttribute("listaE", listaEspecialidad);

        RequestDispatcher dispatcher = request.getRequestDispatcher("reporte_especialidad.jsp");
        dispatcher.forward(request, response);
    }

    private void reportexdoctor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //    request.getSession().setAttribute("accion", "1");

         DoctorDAO doctorDAO = new DoctorDAO();
        List<Doctor> listaDoctor = doctorDAO.selectAll();
        request.setAttribute("listaD", listaDoctor);

        RequestDispatcher dispatcher = request.getRequestDispatcher("reporte_doctor.jsp");
        dispatcher.forward(request, response);
    }

    private void reportexpaciente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //    request.getSession().setAttribute("accion", "1");

        PacienteDAO pacienteDAO = new PacienteDAO();
        List<Paciente> listaPaciente = pacienteDAO.selectAll();
        request.setAttribute("listaP", listaPaciente);

        RequestDispatcher dispatcher = request.getRequestDispatcher("reporte_paciente.jsp");
        dispatcher.forward(request, response);
    }

    protected void busqueda(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {

            String dato = request.getParameter("txtDato");
            CitaDAO citaDAO = new CitaDAO();
            ArrayList<Cita> citas = new ArrayList<>();
            citas = citaDAO.selectBusquedaId( Integer.parseInt(dato));

            if (!citas.isEmpty()) {
                for (Cita cita : citas) {
                     String id = "<tr><td>" + cita.getIdCita() + "</td><td>";
                    String doctor = cita.getDoctor() + "</td><td>";
                    String fecha = cita.getFechaAtencion() + "</td><td>";
                    String hora = cita.getHoraAtencion() + "</td><td>";
                    String especialidad = cita.getEspecialidad() + "</td></tr>";
                    out.print("" + id + doctor + fecha + hora + especialidad);

                }
            } else {
                out.print("<tr><td>No existen registros con ese dato</tr><td>");
            }
        }
    }
    
    protected void busqueda2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {

            String dato = request.getParameter("txtDato");
            CitaDAO citaDAO = new CitaDAO();
            ArrayList<Cita> citas = new ArrayList<>();
            citas = citaDAO.selectBusquedaId2( Integer.parseInt(dato));

            if (!citas.isEmpty()) {
                for (Cita cita : citas) {
                     String id = "<tr><td>" + cita.getIdCita() + "</td><td>";
                    String doctor = cita.getDoctor() + "</td><td>";
                    String paciente = cita.getPaciente() + "</td><td>";
                    String fecha = cita.getFechaAtencion() + "</td><td>";
                    String hora = cita.getHoraAtencion() + "</td></tr>";
                    
                    out.print("" + id + doctor + paciente + fecha + hora);

                }
            } else {
                out.print("<tr><td>No existen registros con ese dato</tr><td>");
            }
        }
    }
    
    protected void busqueda3(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {

            String dato = request.getParameter("txtDato");
            CitaDAO citaDAO = new CitaDAO();
            ArrayList<Cita> citas = new ArrayList<>();
            citas = citaDAO.selectBusquedaId3( Integer.parseInt(dato));

            if (!citas.isEmpty()) {
                for (Cita cita : citas) {
                    String id = "<tr><td>" + cita.getIdCita() + "</td><td>";
                    String paciente = cita.getPaciente() + "</td><td>";
                    String fecha = cita.getFechaAtencion() + "</td><td>";
                    String hora = cita.getHoraAtencion() + "</td><td>";
                    String especialidad = cita.getEspecialidad() + "</td></tr>";
                    
                    out.print("" + id + paciente + fecha + hora + especialidad);

                }
            } else {
                out.print("<tr><td>No existen registros con ese dato</tr><td>");
            }
        }
    }
}
