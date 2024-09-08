/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Dentolife.dtos;


public class Usuario {
    
    private int id;
    private String Nombre;
    private String Apellidos;
    private String Correo;
    private String Rol;
    private String clave;
    private int estado;

    public Usuario( int id, String Nombre, String Apellidos, String Correo, String Rol, String clave, int estado) {
        this.id = id;
        this.Nombre = Nombre;
        this.Apellidos = Apellidos;
        this.Correo = Correo;
        this.Rol = Rol;
        this.clave = clave;
        this.estado = estado;
    }
    
    public Usuario(String clave,String Correo) {
        this.clave = clave;
        this.Correo = Correo;
    }
    
    public Usuario(String Correo){
        this.Correo = Correo;
    }
    
    
    public Usuario(int id) {
        this.id = id;
    }
    
    public Usuario() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String Apellidos) {
        this.Apellidos = Apellidos;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    public String getRol() {
        return Rol;
    }

    public void setRol(String Rol) {
        this.Rol = Rol;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
}
