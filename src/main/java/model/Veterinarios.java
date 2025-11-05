/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author usuario
 */
public class Veterinarios {
    private int id;
    private String nombreCompleto;
    private String documento;
    private String licenciaProfesional;
    private String especialidad;
    private String telefono;
    private String email;
    private Date fechaContratacion;
    private boolean activo = true;
    
    public Veterinarios(int id, String nombreCompleto, String documento, String licenciaProfesional, String especialidad, 
                        String telefono, String email,Date fechacontratacion, boolean activo){
        this.id = id;
        this.nombreCompleto =  nombreCompleto;
        this.documento = documento;
        this.licenciaProfesional = licenciaProfesional;
        this.especialidad = especialidad;
        this.telefono = telefono;
        this.email = email;
        this.fechaContratacion = fechacontratacion;
        this.activo = activo;
    }
    
    public Veterinarios(String nombreCompleto, String documento, String licenciaProfesional, String especialidad, 
                        String telefono, String email,Date fechacontratacion, boolean activo){
        this.nombreCompleto =  nombreCompleto;
        this.documento = documento;
        this.licenciaProfesional = licenciaProfesional;
        this.especialidad = especialidad;
        this.telefono = telefono;
        this.email = email;
        this.fechaContratacion = fechacontratacion;
        this.activo = activo;
    }
    
    public int getId(){return id;}
    public String getNombre(){return nombreCompleto;}
    public String getDocumento(){return documento;}
    public String getLicencia(){return licenciaProfesional;}
    public String getEspecialidad(){return especialidad;}
    public String getTelefono(){return telefono;}
    public String getEmail(){return email;}
    public Date getFechaContratacion(){return fechaContratacion;}
    public boolean getActivo(){return activo;}
    
    public String toString(){
        return "Veterianario{id = " + id + ", nombre = " + nombreCompleto + ", documento = " + documento + ", licencia = " + licenciaProfesional +
                ", especialidad = " + especialidad + ", telefono = " + telefono + ", emial = " + email + ", fecha contratacion = " + fechaContratacion + ", activo = " + activo;    
    }
}
