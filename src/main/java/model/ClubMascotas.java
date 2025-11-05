/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author usuario
 */
import java.sql.Date;
import java.sql.Timestamp;

public class ClubMascotas {

    private int id;
    private int duenoId;
    private int puntosAcumulados;
    private int puntosCanjeados;
    private int puntosDisponibles;
    private String nivel;
    private Date fechaInscripcion;
    private Timestamp fechaUltimaActualizacion;
    private boolean activo;
    
    private String dueno;

    public ClubMascotas(int id, int duenoId, int puntosAcumulados, int puntosCanjeados, int puntosDisponibles,
                        String nivel, Date fechaInscripcion, Timestamp fechaUltimaActualizacion, boolean activo) {
        this.id = id;
        this.duenoId = duenoId;
        this.puntosAcumulados = puntosAcumulados;
        this.puntosCanjeados = puntosCanjeados;
        this.puntosDisponibles = puntosDisponibles;
        this.nivel = nivel;
        this.fechaInscripcion = fechaInscripcion;
        this.fechaUltimaActualizacion = fechaUltimaActualizacion;
        this.activo = activo;
    }

    public ClubMascotas(int duenoId, int puntosAcumulados, int puntosCanjeados, int puntosDisponibles,
                        String nivel, Date fechaInscripcion, Timestamp fechaUltimaActualizacion, boolean activo) {
        this.duenoId = duenoId;
        this.puntosAcumulados = puntosAcumulados;
        this.puntosCanjeados = puntosCanjeados;
        this.puntosDisponibles = puntosDisponibles;
        this.nivel = nivel;
        this.fechaInscripcion = fechaInscripcion;
        this.fechaUltimaActualizacion = fechaUltimaActualizacion;
        this.activo = activo;
    }

    public int getId() { return id; }
    public int getDuenoId() { return duenoId; }
    public int getPuntosAcumulados() { return puntosAcumulados; }
    public int getPuntosCanjeados() { return puntosCanjeados; }
    public int getPuntosDisponibles() { return puntosDisponibles; }
    public String getNivel() { return nivel; }
    public Date getFechaInscripcion() { return fechaInscripcion; }
    public Timestamp getFechaUltimaActualizacion() { return fechaUltimaActualizacion; }
    public boolean isActivo() { return activo; }

    public void setDueno(String dueno) { this.dueno = dueno; }

    public String toString() {
        String nombreDueno = (this.dueno != null) ? this.dueno : "Dueño#" + duenoId;
        return "Club Mascotas{" +
                "id=" + id +
                ", dueño=" + nombreDueno +
                ", puntos acumulados=" + puntosAcumulados +
                ", puntos canjeados=" + puntosCanjeados +
                ", puntos disponibles=" + puntosDisponibles +
                ", nivel='" + nivel + '\'' +
                ", fecha inscripcion=" + fechaInscripcion +
                ", fecha ultima actualizacion=" + fechaUltimaActualizacion +
                ", activo=" + activo +
                '}';
    }
}
