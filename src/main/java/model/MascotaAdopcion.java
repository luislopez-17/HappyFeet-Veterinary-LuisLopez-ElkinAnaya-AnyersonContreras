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
public class MascotaAdopcion {
    private int id;
    private int mascotaId;
    private Date fechaIngreso;
    private String motivoIngreso;
    private String estado;
    private String historia;
    private String temperamento;
    private String necesidadesEspeciales;
    private String fotoAdicionalUrl;

    private String mascota;

    public MascotaAdopcion(int id, int mascotaId, Date fechaIngreso, String motivoIngreso,
                           String estado, String historia, String temperamento,
                           String necesidadesEspeciales, String fotoAdicionalUrl) {
        this.id = id;
        this.mascotaId = mascotaId;
        this.fechaIngreso = fechaIngreso;
        this.motivoIngreso = motivoIngreso;
        this.estado = estado;
        this.historia = historia;
        this.temperamento = temperamento;
        this.necesidadesEspeciales = necesidadesEspeciales;
        this.fotoAdicionalUrl = fotoAdicionalUrl;
    }

    public MascotaAdopcion(int mascotaId, Date fechaIngreso, String motivoIngreso,
                           String estado, String historia, String temperamento,
                           String necesidadesEspeciales, String fotoAdicionalUrl) {
        this.mascotaId = mascotaId;
        this.fechaIngreso = fechaIngreso;
        this.motivoIngreso = motivoIngreso;
        this.estado = estado;
        this.historia = historia;
        this.temperamento = temperamento;
        this.necesidadesEspeciales = necesidadesEspeciales;
        this.fotoAdicionalUrl = fotoAdicionalUrl;
    }

    public int getId() { return id; }
    public int getMascotaId() { return mascotaId; }
    public Date getFechaIngreso() { return fechaIngreso; }
    public String getMotivoIngreso() { return motivoIngreso; }
    public String getEstado() { return estado; }
    public String getHistoria() { return historia; }
    public String getTemperamento() { return temperamento; }
    public String getNecesidadesEspeciales() { return necesidadesEspeciales; }
    public String getFotoAdicionalUrl() { return fotoAdicionalUrl; }

    public void setMascota(String mascota) { this.mascota = mascota; }

    public String toString() {
        String nombreMascota = (this.mascota != null) ? this.mascota : "Mascota#" + mascotaId;

        return "MascotaAdopcion {id = " + id +
                ", mascota = " + nombreMascota +
                ", fechaIngreso = " + fechaIngreso +
                ", motivoIngreso = " + motivoIngreso +
                ", estado = " + estado +
                ", historia = " + historia +
                ", temperamento = " + temperamento +
                ", necesidadesEspeciales = " + necesidadesEspeciales +
                ", fotoAdicionalUrl = " + fotoAdicionalUrl +
                "}";
    }
}
