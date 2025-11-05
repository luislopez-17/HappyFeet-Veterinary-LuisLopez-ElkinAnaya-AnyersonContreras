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

public class Proveedores {
    private int id;
    private String nombreEmpresa;
    private String contacto;
    private String telefono;
    private String email;
    private String direccion;
    private String sitioWeb;
    private boolean activo;
    private Timestamp fechaRegistro;
    
    public Proveedores(int id, String nombreEmpresa, String contacto, String telefono, String email, 
                     String direccion, String sitioWeb, boolean activo, Timestamp fechaRegistro) {
        this.id = id;
        this.nombreEmpresa = nombreEmpresa;
        this.contacto = contacto;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
        this.sitioWeb = sitioWeb;
        this.activo = activo;
        this.fechaRegistro = fechaRegistro;
    }
    
    public Proveedores(String nombreEmpresa, String contacto, String telefono, String email, 
                     String direccion, String sitioWeb, boolean activo, Timestamp fechaRegistro) {
        this.nombreEmpresa = nombreEmpresa;
        this.contacto = contacto;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
        this.sitioWeb = sitioWeb;
        this.activo = activo;
        this.fechaRegistro = fechaRegistro;
    }
    
    public int getId(){return id;}
    public String getNombreEmpresa(){return nombreEmpresa;}
    public String getContacto(){return contacto;}
    public String getTelefono(){return telefono;}
    public String getEmail(){return email;}
    public String getDireccion(){return direccion;}
    public String getSitioWeb(){return sitioWeb;}
    public boolean isActivo(){return activo;}
    public Timestamp getFechaRegistro(){return fechaRegistro;}
    
    public void setNombreEmpresa(String nombreEmpresa){this.nombreEmpresa = nombreEmpresa;}
    public void setContacto(String contacto){this.contacto = contacto;}
    public void setTelefono(String telefono){this.telefono = telefono;}
    public void setEmail(String email){this.email = email;}
    public void setDireccion(String direccion){this.direccion = direccion;}
    public void setSitioWeb(String sitioWeb){this.sitioWeb = sitioWeb;}
    public void setActivo(boolean activo){this.activo = activo;}
    public void setFechaRegistro(Timestamp fechaRegistro){this.fechaRegistro = fechaRegistro;}
    
    public String toString(){
        return "Proveedor {id = " + id +
                ", nombre empresa = " + nombreEmpresa +
                ", contacto = " + contacto +
                ", teléfono = " + telefono +
                ", email = " + email +
                ", dirección = " + direccion +
                ", sitio web = " + sitioWeb +
                ", activo = " + activo +
                ", fecha registro = " + fechaRegistro +
                "}";
    }
}

