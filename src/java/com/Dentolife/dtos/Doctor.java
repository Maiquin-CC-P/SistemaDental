/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Dentolife.dtos;


public class Doctor {
    
    private int IdDoctor;
    private String Nombre;
    private String Apellidos;
    private String TipoDocumento;
    private String NumDocumento;
    private String NumColegiatura;
    private String Direccion;
    private String TelefonoFijo;
    private String TelefonoMovil;
    private int Estado;

    public Doctor(int IdDoctor, String Nombre, String Apellidos, String TipoDocumento, String NumDocumento, String NumColegiatura, String Direccion, String TelefonoFijo, String TelefonoMovil, int Estado) {
        this.IdDoctor = IdDoctor;
        this.Nombre = Nombre;
        this.Apellidos = Apellidos;
        this.TipoDocumento = TipoDocumento;
        this.NumDocumento = NumDocumento;
        this.NumColegiatura = NumColegiatura;
        this.Direccion = Direccion;
        this.TelefonoFijo = TelefonoFijo;
        this.TelefonoMovil = TelefonoMovil;
        this.Estado = Estado;
    }

    public Doctor() {
    }
    
    public Doctor(int IdDoctor) {
        this.IdDoctor = IdDoctor;
    }
    
    public int getIdDoctor() {
        return IdDoctor;
    }

    public void setIdDoctor(int IdDoctor) {
        this.IdDoctor = IdDoctor;
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

    public String getTipoDocumento() {
        return TipoDocumento;
    }

    public void setTipoDocumento(String TipoDocumento) {
        this.TipoDocumento = TipoDocumento;
    }

    public String getNumDocumento() {
        return NumDocumento;
    }

    public void setNumDocumento(String NumDocumento) {
        this.NumDocumento = NumDocumento;
    }

    public String getNumColegiatura() {
        return NumColegiatura;
    }

    public void setNumColegiatura(String NumColegiatura) {
        this.NumColegiatura = NumColegiatura;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getTelefonoFijo() {
        return TelefonoFijo;
    }

    public void setTelefonoFijo(String TelefonoFijo) {
        this.TelefonoFijo = TelefonoFijo;
    }

    public String getTelefonoMovil() {
        return TelefonoMovil;
    }

    public void setTelefonoMovil(String TelefonoMovil) {
        this.TelefonoMovil = TelefonoMovil;
    }
    
    public int getEstado() {
        return Estado;
    }

    public void setEstado(int Estado) {
        this.Estado = Estado;
    }
}
