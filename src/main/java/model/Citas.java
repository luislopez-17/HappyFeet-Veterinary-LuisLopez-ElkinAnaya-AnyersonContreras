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
public class Citas {
    private int id;
    private int idMascotas;
    private int idVeterinario;
    private Timestamp fechaHora;
    private String motivo;
    private int idEstado;
    private String observaciones;
    private Timestamp fechaCreacion;
    
    private String mascota;
    private String veterinario;
    private String estado;
    
    public Citas(int id, int idMascotas, int idVeterinario, Timestamp fechaHora, String motivo, int idEstado, String observaciones, Timestamp fechaCreacion){
        this.id = id;
        this.idMascotas = idMascotas;
        this.idVeterinario = idVeterinario;
        this.fechaHora = fechaHora;
        this.motivo = motivo;
        this.idEstado = idEstado;
        this.observaciones = observaciones;
        this.fechaCreacion = fechaCreacion;
    }
    
    public Citas(int idMascotas, int idVeterinario, Timestamp fechaHora, String motivo, int idEstado, String observaciones, Timestamp fechaCreacion){
        this.idMascotas = idMascotas;
        this.idVeterinario = idVeterinario;
        this.fechaHora = fechaHora;
        this.motivo = motivo;
        this.idEstado = idEstado;
        this.observaciones = observaciones;
        this.fechaCreacion = fechaCreacion;
    }
    
    public int getId(){return id;}
    public int getIdMascotas(){return idMascotas;}
    public int getIdVeterinario(){return idVeterinario;}
    public Timestamp getFechaHora(){return fechaHora;}
    public String getMotivo(){return motivo;}
    public int getIdEstado(){return idEstado;}
    public String getObservaciones(){return observaciones;}
    public Timestamp getFechaCreacion(){return fechaCreacion;}
    
    public void setMascota(String mascota){this.mascota = mascota;}
    public void setVeterinario(String veterinario){this.veterinario = veterinario;}
    public void setEstado(String estado){this.estado = estado;}
    
    public String toString(){
        String nombreMascota = (this.mascota != null) ? this.mascota : "Mascota#" + idMascotas;
        String nombreVeterinario = (this.veterinario != null) ? this.veterinario : "Veterinario#" + idVeterinario;
        String nombreEstado = (this.estado != null) ? this.estado : "Estado#" + idEstado;
        return "Cita{id =" + id + ", mascota = " + nombreMascota + ", veterinario = " + nombreVeterinario + ", fecha y hora = " + fechaHora + 
                ", motivo = " + motivo + ", estado = " + nombreEstado + ", observaciones = " + observaciones + ", fecha creación = " + fechaCreacion ; 
    }
}
