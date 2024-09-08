/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Dentolife.controllers;

import com.Dentolife.daos.EspecialidadDAO;
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
public class EspecialidadController extends HttpServlet {

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

        EspecialidadDAO especialidadDAO = new EspecialidadDAO();

        List<Especialidad> lista_especialidad = especialidadDAO.selectAll();
        request.setAttribute("lista", lista_especialidad);
        request.getRequestDispatcher("lista_especialidad.jsp").forward(request, response);
    }
    
    private void nuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        request.getSession().setAttribute("accion", "1");
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("especialidad.jsp");
        dispatcher.forward(request, response);
    }
    
    private void registrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int idEspecialidad = 1;
        String descripcion = request.getParameter("txtDesc");
        int estado = 0;
        validaciones = "";     
        
        Especialidad especialidad = new Especialidad(idEspecialidad,descripcion,estado);
        EspecialidadDAO especialidadDAO = new EspecialidadDAO();

        if (especialidadDAO.insert(especialidad)) {
            validaciones += "Se ha realizado el registro exitosamente";
            request.setAttribute("validaciones", validaciones);
            request.getRequestDispatcher("especialidad.jsp").forward(request, response);
        } else {
            validaciones = "No se realizado el registro";
            request.setAttribute("validaciones", validaciones);
            request.getRequestDispatcher("especialidad.jsp").forward(request, response);
        }
    }
    
    private void actualizar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String sid = request.getParameter("txtID");
        String descripcion = request.getParameter("txtDesc");
        int estado = Integer.parseInt(request.getParameter("txtEstado"));
        int id = Integer.parseInt(sid);
        validaciones = "";
        
        Especialidad especia = new Especialidad(id, descripcion, estado);

        EspecialidadDAO especiaDAO = new EspecialidadDAO();
        
        if (especiaDAO.update(especia)) {
            validaciones = "Se ha realizado la actualización exitosamente";
            request.setAttribute("validaciones", validaciones);
            request.getRequestDispatcher("especialidad.jsp").forward(request, response);
        } else {
            validaciones = "No se realizado la actualización";
            request.setAttribute("validaciones", validaciones);
            request.getRequestDispatcher("especialidad.jsp").forward(request, response);
        }
    }
    
    private void mostrarID(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        request.getSession().setAttribute("accion", "2");
        
        EspecialidadDAO especiaDAO = new EspecialidadDAO();      
        Especialidad especialidad = especiaDAO.selectById(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("especialidad", especialidad);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("especialidad.jsp");
        dispatcher.forward(request, response);
    }
   
    private void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        
        Especialidad especialidad = new Especialidad(id);
        EspecialidadDAO especialidadDAO = new EspecialidadDAO();
        
        if (especialidadDAO.delete(especialidad)) {
            List<Especialidad> listaEspecialidad = especialidadDAO.selectAll();
            request.setAttribute("lista", listaEspecialidad);
            request.getRequestDispatcher("lista_especialidad.jsp").forward(request, response);
        } else {
            List<Especialidad> listaEspecialidad = especialidadDAO.selectAll();
            request.setAttribute("lista", listaEspecialidad);
            request.getRequestDispatcher("lista_especialidad.jsp").forward(request, response);
        }           
       
    }
}
