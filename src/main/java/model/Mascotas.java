/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author usuario
 */
public class Mascotas {
    public enum Sexo{
        Macho,
        Hembra
    }
    
    private int id;
    private int duenoId;
    private String nombre;
    private int razaId;
    private Date fechaNacimiento;
    private Sexo sexo;
    private double pesoActual;
    private String microchip;
    private String tatuaje;
    private String urlFoto;
    private String alergias;
    private String  condicionesPreexistentes;
    private Timestamp fechaRegistro;
    private boolean activo = true;
    
    private String duenoNombre;
    private String razaNombre;
    
    public Mascotas(int id, int duenoId, String nombre, int razaId, Date fechaNacimiento, Sexo sexo, double pesoActual, String microchip, String tatuaje,
                    String urlFoto, String alergias, String condicionesPreexistentes, Timestamp fechaRegistro, boolean activo){
        this.id = id;
        this.duenoId = duenoId;
        this.nombre = nombre;
        this.razaId = razaId;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
        this.pesoActual = pesoActual;
        this.microchip = microchip;
        this.tatuaje = tatuaje;
        this.urlFoto = urlFoto;
        this.alergias = alergias;
        this.condicionesPreexistentes = condicionesPreexistentes;
        this.fechaRegistro = fechaRegistro;
        this.activo = activo;
    }
    
    public Mascotas(int duenoId, String nombre, int razaId, Date fechaNacimiento, Sexo sexo, double pesoActual, String microchip, String tatuaje,
                    String urlFoto, String alergias, String condicionesPreexistentes, Timestamp fechaRegistro, boolean activo){
        this.duenoId = duenoId;
        this.nombre = nombre;
        this.razaId = razaId;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
        this.pesoActual = pesoActual;
        this.microchip = microchip;
        this.tatuaje = tatuaje;
        this.urlFoto = urlFoto;
        this.alergias = alergias;
        this.condicionesPreexistentes = condicionesPreexistentes;
        this.fechaRegistro = fechaRegistro;
        this.activo = activo;
    }
    
    public int getId(){return id;}
    public int getDuenoId(){return duenoId;}
    public String getNombre(){return nombre;}
    public int getRazaid(){return razaId;}
    public Date getFechaNacimiento(){return fechaNacimiento;}
    public Sexo getSexo(){return sexo;}
    public double getPesoActual(){return pesoActual;}
    public String getMicrochip(){return microchip;}
    public String getTatuaje(){return tatuaje;}
    public String getUrlFoto(){return urlFoto;}
    public String getAlergias(){return alergias;}
    public String getCondiciones(){return condicionesPreexistentes;}
    public Timestamp getFechaRegistro(){return fechaRegistro;}
    public boolean getActivo(){return activo;}
    
    public void setDuenoNombre(String duenoNombre){this.duenoNombre = duenoNombre;}
    public void setRazaNombre(String razaNombre){this.razaNombre = razaNombre;}
    
    public String toString(){
        String nombreDueno = (this.duenoNombre != null) ? this.duenoNombre : "Especie#" + duenoId;
        String nombreRaza = (this.razaNombre != null) ? this.razaNombre : "Especie#" + razaId;
        return "Mascota{id = " + id + ", dueño = " + nombreDueno + ", nombre: " + nombre +", raza = " + nombreRaza +
                ", fecha nacimiento = " + fechaNacimiento + ", sexo = " + sexo + ", peso actual = " + pesoActual +
                ", microchip = " + microchip + ", tatuaje = " + tatuaje + ", url foto = " + urlFoto + ", alergias = " +
                alergias + ", condiciones preexistentes = " + condicionesPreexistentes + ", fecha registro = " + fechaRegistro + ", Activo = " + activo;
    } 
}
