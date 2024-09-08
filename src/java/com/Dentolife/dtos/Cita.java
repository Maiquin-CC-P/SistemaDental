/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Dentolife.dtos;

import java.sql.Date;
import java.sql.Time;


public class Cita {

    private int IdCita;
    private int IdPaciente;
    private int IdDoctor;
    private int IdEspecialidad;
    private String FechaAtencion;
    private String HoraAtencion;
    private int Estado;

    String Paciente;
    String Doctor;
    String Especialidad;

    public Cita(int IdCita, int IdPaciente, int IdDoctor, int IdEspecialidad, String FechaAtencion, String HoraAtencion, int Estado) {
        this.IdCita = IdCita;
        this.IdPaciente = IdPaciente;
        this.IdDoctor = IdDoctor;
        this.IdEspecialidad = IdEspecialidad;
        this.FechaAtencion = FechaAtencion;
        this.HoraAtencion = HoraAtencion;
        this.Estado = Estado;
    }

    public Cita(int IdCita, String FechaAtencion, String Doctor, String Paciente) {
        this.IdCita = IdCita;
        this.Doctor = Doctor;
        this.Paciente = Paciente;
        this.FechaAtencion = FechaAtencion;
    }

    public Cita() {

    }

    public Cita(int id) {
        this.IdCita = id;
    }

    public int getIdCita() {
        return IdCita;
    }

    public void setIdCita(int IdCita) {
        this.IdCita = IdCita;
    }

    public int getIdPaciente() {
        return IdPaciente;
    }

    public void setIdPaciente(int IdPaciente) {
        this.IdPaciente = IdPaciente;
    }

    public int getIdDoctor() {
        return IdDoctor;
    }

    public void setIdDoctor(int IdDoctor) {
        this.IdDoctor = IdDoctor;
    }

    public int getIdEspecialidad() {
        return IdEspecialidad;
    }

    public void setIdEspecialidad(int IdEspecialidad) {
        this.IdEspecialidad = IdEspecialidad;
    }

    public String getFechaAtencion() {
        return FechaAtencion;
    }

    public void setFechaAtencion(String FechaAtencion) {
        this.FechaAtencion = FechaAtencion;
    }

    public String getHoraAtencion() {
        return HoraAtencion;
    }

    public void setHoraAtencion(String HoraAtencion) {
        this.HoraAtencion = HoraAtencion;
    }

    public int getEstado() {
        return Estado;
    }

    public void setEstado(int Estado) {
        this.Estado = Estado;
    }

    public String getPaciente() {
        return Paciente;
    }

    public void setPaciente(String Paciente) {
        this.Paciente = Paciente;
    }

    public String getDoctor() {
        return Doctor;
    }

    public void setDoctor(String Doctor) {
        this.Doctor = Doctor;
    }

    public String getEspecialidad() {
        return Especialidad;
    }

    public void setEspecialidad(String Especialidad) {
        this.Especialidad = Especialidad;
    }

}
