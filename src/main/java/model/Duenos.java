/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Timestamp;

/**
 *
 * @author usuario
 */
public class Duenos {
    private int id;
    private String nombreCompleto;
    private String documento;
    private String direccion;
    private String telefono;
    private String email;
    private String contactoEmergencia;
    private Timestamp fechaRegistro;
    private boolean activo = true;
    
    public Duenos(int id, String nombreCompleto, String documento, String direccion, String telefono, String email, String contactoEmergencia, Timestamp fechaRegistro, boolean activo){
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.documento = documento;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.contactoEmergencia = contactoEmergencia;
        this.fechaRegistro = fechaRegistro;
        this.activo = activo;
    }
    
    public Duenos( String nombreCompleto, String documento, String direccion, String telefono, String email, String contactoEmergencia, Timestamp fechaRegistro, boolean activo){
        this.nombreCompleto = nombreCompleto;
        this.documento = documento;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.contactoEmergencia = contactoEmergencia;
        this.fechaRegistro = fechaRegistro;
        this.activo = activo;
    }
    
    public int getID(){return id;}
    public String getNombreCompleto(){return nombreCompleto;}
    public String getDocumento(){return documento;}
    public String getDireccion(){return direccion;}
    public String getTelefono(){return telefono;}
    public String getEmail(){return email;}
    public String getContactoEmergencia(){return contactoEmergencia;}
    public Timestamp getFechaRegistro(){return fechaRegistro;}
    public boolean getActivo() {return activo;}
    
    public String toString(){
        return "Dueños{id = " + id + ", nombre = " + nombreCompleto + ", documento = " + documento + ", dirección = " + direccion +
                ", telefono = " + telefono + ", email = " + email + ", contacto de emergencia = " + contactoEmergencia + ", fecha de Registro = " +
                fechaRegistro + ", activo = " + activo + "}";
    }
}
