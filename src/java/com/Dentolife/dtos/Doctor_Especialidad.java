/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Dentolife.dtos;


public class Doctor_Especialidad {
    
    private int IdDoctor;
    private int IdEspecialidad;
    private int Estado;
    
    private String Doctor;
    private String Especialidad;

    public Doctor_Especialidad(int IdDoctor, int IdEspecialidad, int Estado) {
        this.IdDoctor = IdDoctor;
        this.IdEspecialidad = IdEspecialidad;
        this.Estado = Estado;
    }

    public Doctor_Especialidad(int IdDoctor, int IdEspecialidad) {
        this.IdDoctor = IdDoctor;
        this.IdEspecialidad = IdEspecialidad;
    }
    
    public Doctor_Especialidad() {
    }

    public Doctor_Especialidad(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    public int getEstado() {
        return Estado;
    }

    public void setEstado(int Estado) {
        this.Estado = Estado;
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
