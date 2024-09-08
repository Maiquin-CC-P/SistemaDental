/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Dentolife.controllers;

import com.Dentolife.daos.UsuarioDAO;
import com.Dentolife.dtos.Usuario;
import com.sun.java.swing.plaf.windows.resources.windows;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *

 */
public class UsuarioController extends HttpServlet {

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
            case "login":
                login(request, response);
                break;           
            case "recupera":
                recupera(request, response);
                break;
            case "validarCorreo":
                validarCorreo(request, response);
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

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String correo = request.getParameter("txtCorreo");
        String clave = request.getParameter("txtClave");
       
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.login(correo, clave);
        
        if (usuario != null) {
            request.setAttribute("userLogeo", usuario.getRol());
            request.getSession().setAttribute("accionLogeo", usuario.getRol());
            request.getRequestDispatcher("agenda.jsp").forward(request, response);
            
           // request.getRequestDispatcher("citacontroller.do?txtProceso=mostrar").forward(request, response);
        } else {
            validaciones = "";
            validaciones = "Usuario no Existe o Inactivo / Usuario o password incorrectas";
            request.setAttribute("validaciones", validaciones);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
    
    private void recupera(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String scorreo = request.getParameter("txtCorreo");
        String clave = request.getParameter("txtClave");

        Usuario usuario = new Usuario(clave,scorreo);

        UsuarioDAO usuarioDAO = new UsuarioDAO();

        if (usuarioDAO.recupera(usuario)) {
            String mensaje = "Se ha realizado el registro exitosamente";
            request.getSession().setAttribute("mensaje", mensaje);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else {
            validaciones = "";
            validaciones = "Datos Incorrectos";
            request.setAttribute("validaciones", validaciones);
            request.getRequestDispatcher("login_recupera.jsp").forward(request, response);
        }

    }
    
    //metodo para validar correo
     private void validarCorreo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String scorreo = request.getParameter("txtCorreo");

        Usuario usuario = new Usuario(scorreo);
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
       boolean rspta = usuarioDAO.validarCorreo(usuario);
         ArrayList<String> listRspta = new ArrayList<String>();
       if(!rspta){
            String rsptaCorreo =String.valueOf(rspta);
            String rsptaMsg ="Correo no existe";
            listRspta.add(rsptaCorreo);
            listRspta.add(rsptaMsg);
       }
       out.println(listRspta);
    }

    private void mostrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UsuarioDAO usuarioDAO = new UsuarioDAO();

        List<Usuario> listaUsuario = usuarioDAO.selectAll();
        request.setAttribute("lista", listaUsuario);
        request.getRequestDispatcher("lista_usuario.jsp").forward(request, response);
    }

    private void nuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        request.getSession().setAttribute("accion", "1");
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("usuario.jsp");
        dispatcher.forward(request, response);
    }
        
    private void registrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = 1;
        String nombre = request.getParameter("txtNombre");
        String apellido = request.getParameter("txtApellido");
        String correo = request.getParameter("txtCorreo");
        String rol = request.getParameter("txtRol");
        String clave = request.getParameter("txtClave");
        int estado = 0;
        validaciones = "";
        
        Usuario usuario = new Usuario(id, nombre, apellido, correo, rol, clave, estado);

        
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        
        if (usuarioDAO.insert(usuario)) {
            validaciones = "Se ha realizado el registro exitosamente";
            request.setAttribute("validaciones", validaciones);
            request.getRequestDispatcher("usuario.jsp").forward(request, response);
        } else {
            validaciones = "No se realizado el registro";
            request.setAttribute("validaciones", validaciones);
            request.getRequestDispatcher("usuario.jsp").forward(request, response);
        }
    }
    
    private void actualizar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String sid = request.getParameter("txtID");
        String nombre = request.getParameter("txtNombre");
        String apellido = request.getParameter("txtApellido");
        String correo = request.getParameter("txtCorreo");
        String rol = request.getParameter("txtRol");
        String clave = request.getParameter("txtClave");
        int estado = Integer.parseInt(request.getParameter("txtEstado"));
        int id = Integer.parseInt(sid);
        validaciones = "";
        
        Usuario usuario = new Usuario(id, nombre, apellido, correo, rol, clave, estado);

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        
        if (usuarioDAO.update(usuario)) {
            validaciones = "Se ha realizado la actualización exitosamente";
            request.setAttribute("validaciones", validaciones);
            request.getRequestDispatcher("usuario.jsp").forward(request, response);
        } else {
            validaciones = "No se realizado la actualización";
            request.setAttribute("validaciones", validaciones);
            request.getRequestDispatcher("usuario.jsp").forward(request, response);
        }
    }
    
    private void mostrarID(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        request.getSession().setAttribute("accion", "2");
        
        UsuarioDAO usuarioDAO = new UsuarioDAO();      
        Usuario usuario = usuarioDAO.selectById(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("usuario", usuario);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("usuario.jsp");
        dispatcher.forward(request, response);
    }
    
    private void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
      int id = Integer.parseInt(request.getParameter("id"));
        
        Usuario usuario = new Usuario(id);
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        if (usuarioDAO.delete(usuario)) {
            List<Usuario> listaUsuario = usuarioDAO.selectAll();
            request.setAttribute("lista", listaUsuario);
            request.getRequestDispatcher("lista_usuario.jsp").forward(request, response);
        } else {
            List<Usuario> listaUsuario = usuarioDAO.selectAll();
            request.setAttribute("lista", listaUsuario);
            request.getRequestDispatcher("lista_usuario.jsp").forward(request, response);
        }           
    }
}
