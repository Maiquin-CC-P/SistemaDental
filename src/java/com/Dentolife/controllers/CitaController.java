/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Dentolife.controllers;

import com.Dentolife.daos.CitaDAO;
import com.Dentolife.daos.DoctorDAO;
import com.Dentolife.daos.Doctor_EspecialidadDAO;
import com.Dentolife.daos.EspecialidadDAO;
import com.Dentolife.daos.PacienteDAO;
import com.Dentolife.dtos.Agenda;
import com.Dentolife.dtos.Cita;
import com.Dentolife.dtos.Doctor;
import com.Dentolife.dtos.Doctor_Especialidad;
import com.Dentolife.dtos.Especialidad;
import com.Dentolife.dtos.Paciente;
import com.Dentolife.utilitarios.JsonConverter;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import javax.servlet.RequestDispatcher;


public class CitaController extends HttpServlet {

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
    SimpleDateFormat hora = new SimpleDateFormat("HH:mm");

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
            case "mostrarID":
                mostrarID(request, response);
                break;
            case "mostrara":
                mostrara(request, response);
                break;
            case "borrar":
                eliminar(request, response);
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

        CitaDAO citaDAO = new CitaDAO();

        List<Cita> listaCita = citaDAO.selectAll();
        request.setAttribute("lista", listaCita);
        request.getRequestDispatcher("lista_cita.jsp").forward(request, response);
    }

    private void nuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getSession().setAttribute("accion", "1");

        PacienteDAO pacienteDAO = new PacienteDAO();
        List<Paciente> listaPaciente = pacienteDAO.selectAll();
        request.setAttribute("listaP", listaPaciente);
        
        DoctorDAO doctorDAO = new DoctorDAO();
        List<Doctor> listaDoctor = doctorDAO.selectAll();
        request.setAttribute("listaD", listaDoctor);
        
        EspecialidadDAO especialidadDAO = new EspecialidadDAO();
        List<Especialidad> listaEspecialidad = especialidadDAO.selectAll();
        request.setAttribute("listaE", listaEspecialidad);
        
        Doctor_EspecialidadDAO doctor_especialidadDAO = new Doctor_EspecialidadDAO();
        List<Doctor_Especialidad> lista_doctor_especialidad = doctor_especialidadDAO.selectAll();
        request.setAttribute("listaD_E", lista_doctor_especialidad);  
        
        request.getRequestDispatcher("cita.jsp").forward(request, response);
    }

    private void registrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int idCita = 1;
        int idPaciente = Integer.parseInt(request.getParameter("txtPaciente"));
        String doctorEspecialidad = request.getParameter("txtDoctor_Especialidad");
        int idEspecialidad = Integer.parseInt(doctorEspecialidad.substring(0,doctorEspecialidad.indexOf('-')));
        int idDoctor = Integer.parseInt(doctorEspecialidad.substring(doctorEspecialidad.indexOf('-')+1));
        String fechaAtencion = request.getParameter("txtFecha");
        String horaAtencion = request.getParameter("txtHora");
        int estado = 0;
        validaciones = "";

        Cita cita = new Cita(idCita, idPaciente, idDoctor, idEspecialidad, fechaAtencion, horaAtencion, estado);

        CitaDAO citaDAO = new CitaDAO();

        if (citaDAO.insert(cita)) {
            validaciones = "Se ha realizado el registro exitosamente";
            request.setAttribute("validaciones", validaciones);
            request.getRequestDispatcher("cita.jsp").forward(request, response);
        } else {
            validaciones = "Hora o Doctor no disponibles";
            request.setAttribute("validaciones", validaciones);
            request.getRequestDispatcher("cita.jsp").forward(request, response);
        }
    }
    
    private void actualizar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String sid = request.getParameter("txtID");
        int idPaciente = Integer.parseInt(request.getParameter("txtPaciente"));
        int idDoctor = Integer.parseInt(request.getParameter("txtDoctor"));
        int idEspecialidad = Integer.parseInt(request.getParameter("txtEspecialidad"));
        String fechaAtencion = request.getParameter("txtFecha");
        String horaAtencion = request.getParameter("txtHora");
        int idCita = Integer.parseInt(sid);
        int estado = 0;
        validaciones = "";
        
        Cita cita = new Cita(idCita, idPaciente, idDoctor, idEspecialidad, fechaAtencion, horaAtencion, estado);

        CitaDAO citaDAO = new CitaDAO();
        
        if (citaDAO.update(cita)) {
            validaciones = "Se ha realizado la actualización exitosamente";
            request.setAttribute("validaciones", validaciones);
            request.getRequestDispatcher("cita.jsp").forward(request, response);
        } else {
            validaciones = "No se realizado la actualización";
            request.setAttribute("validaciones", validaciones);
            request.getRequestDispatcher("cita.jsp").forward(request, response);
        }
    }
    
    private void mostrarID(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        request.getSession().setAttribute("accion", "2");
        
        CitaDAO cidaDAO = new CitaDAO();      
        Cita cita = cidaDAO.selectById(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("cita", cita);
        
        PacienteDAO pacienteDAO = new PacienteDAO();
        List<Paciente> listaPaciente = pacienteDAO.selectAll();
        request.setAttribute("listaP", listaPaciente);
        
        DoctorDAO doctorDAO = new DoctorDAO();
        List<Doctor> listaDoctor = doctorDAO.selectAll();
        request.setAttribute("listaD", listaDoctor);
        
        EspecialidadDAO especialidadDAO = new EspecialidadDAO();
        List<Especialidad> listaEspecialidad = especialidadDAO.selectAll();
        request.setAttribute("listaE", listaEspecialidad);
        
        
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("cita.jsp");
        dispatcher.forward(request, response);
    }
    
    private void mostrara(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        CitaDAO citaDAO = new CitaDAO();

        //List<Cita> listaCita = citaDAO.selectAll();
       // request.setAttribute("lista", listaCita);
        
        response.setContentType("application/json;charset=UTF-8");

        //ServletOutputStream out = response.getOutputStream();

        List<Agenda> listaCita = citaDAO.selectAgenda();

        JsonConverter converter = new JsonConverter();
        String output = converter.convertToJson(listaCita);

       // out.print(output);
        request.setAttribute("cita", output);
        response.getWriter().print(output);
    }
    
    private void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
      int id = Integer.parseInt(request.getParameter("id"));
        
        Cita cita = new Cita(id);

        CitaDAO citaDAO = new CitaDAO();
        
        if (citaDAO.delete(cita)) {
            List<Cita> listaCita = citaDAO.selectAll();
            request.setAttribute("lista", listaCita);
            request.getRequestDispatcher("lista_cita.jsp").forward(request, response);
        } else {
            List<Cita> listaCita = citaDAO.selectAll();
            request.setAttribute("lista", listaCita);
            request.getRequestDispatcher("lista_cita.jsp").forward(request, response);
        }           
    }
    
}
