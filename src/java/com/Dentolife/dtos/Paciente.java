/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Dentolife.dtos;

import java.sql.Date;


public class Paciente {
    
    private int IdPaciente;
    private String Nombre;
    private String Apellidos;
    private String TipoDocumento;
    private String NumDocumento;
    private String Correo;
    private String Sexo;
    private String FechaNAcimeinto;
    private String Direccion;
    private String TelefonoFijo;
    private String TelefonoMovil;
    private String Observaciones;
    private int Estado;

    public Paciente(int IdPaciente, String Nombre, String Apellidos, String TipoDocumento, String NumDocumento, String Correo, String Sexo, String FechaNAcimeinto, String Direccion, String TelefonoFijo, String TelefonoMovil, String Observaciones, int Estado) {
        this.IdPaciente = IdPaciente;
        this.Nombre = Nombre;
        this.Apellidos = Apellidos;
        this.TipoDocumento = TipoDocumento;
        this.NumDocumento = NumDocumento;
        this.Correo = Correo;
        this.Sexo = Sexo;
        this.FechaNAcimeinto = FechaNAcimeinto;
        this.Direccion = Direccion;
        this.TelefonoFijo = TelefonoFijo;
        this.TelefonoMovil = TelefonoMovil;
        this.Observaciones = Observaciones;
        this.Estado = Estado;
    }

    public Paciente(int IdPaciente, String Nombre, String Apellidos, String TipoDocumento, String NumDocumento, String Correo, String TelefonoFijo, String TelefonoMovil, int Estado) {
        this.IdPaciente = IdPaciente;
        this.Nombre = Nombre;
        this.Apellidos = Apellidos;
        this.TipoDocumento = TipoDocumento;
        this.NumDocumento = NumDocumento;
        this.Correo = Correo;
        this.TelefonoFijo = TelefonoFijo;
        this.TelefonoMovil = TelefonoMovil;
        this.Estado = Estado;
    }
    
    public Paciente() {
    }

    public Paciente(int IdPaciente) {
        this.IdPaciente = IdPaciente;
    }

    public int getIdPaciente() {
        return IdPaciente;
    }

    public void setIdPaciente(int IdPaciente) {
        this.IdPaciente = IdPaciente;
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

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    public String getSexo() {
        return Sexo;
    }

    public void setSexo(String Sexo) {
        this.Sexo = Sexo;
    }

    public String getFechaNAcimeinto() {
        return FechaNAcimeinto;
    }

    public void setFechaNAcimeinto(String FechaNAcimeinto) {
        this.FechaNAcimeinto = FechaNAcimeinto;
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

    public String getObservaciones() {
        return Observaciones;
    }

    public void setObservaciones(String Observaciones) {
        this.Observaciones = Observaciones;
    }

    public int getEstado() {
        return Estado;
    }

    public void setEstado(int Estado) {
        this.Estado = Estado;
    }
    
    
    
}
