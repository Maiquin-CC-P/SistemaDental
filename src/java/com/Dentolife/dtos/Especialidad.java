/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Dentolife.dtos;



public class Especialidad {
    
    private int IdEspecialidad;
    private String Especialidad;
    private int Estado;

    public Especialidad(int IdEspecialidad, String Especialidad, int Estado) {
        this.IdEspecialidad = IdEspecialidad;
        this.Especialidad = Especialidad;
        this.Estado = Estado;
    }

    public Especialidad() {
    }

    public Especialidad(int IdEspecialidad) {
        this.IdEspecialidad = IdEspecialidad;
    }
       
    public int getIdEspecialidad() {
        return IdEspecialidad;
    }

    public void setIdEspecialidad(int IdEspecialidad) {
        this.IdEspecialidad = IdEspecialidad;
    }

    public String getEspecialidad() {
        return Especialidad;
    }

    public void setEspecialidad(String Especialidad) {
        this.Especialidad = Especialidad;
    }

    public int getEstado() {
        return Estado;
    }

    public void setEstado(int Estado) {
        this.Estado = Estado;
    }   
    
}
