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

public class RegistroJornadaVacunacion {

    private int id;
    private int jornadaId;
    private int mascotaId;
    private int duenoId;
    private int vacunaId;
    private int veterinarioId;
    private Timestamp fechaHora;
    private String loteVacuna;
    private Date proximaDosis;
    private String observaciones;

    private String jornada;
    private String mascota;
    private String dueno;
    private String vacuna;
    private String veterinario;

    public RegistroJornadaVacunacion(int id, int jornadaId, int mascotaId, int duenoId, int vacunaId, int veterinarioId,
                                     Timestamp fechaHora, String loteVacuna, Date proximaDosis, String observaciones) {
        this.id = id;
        this.jornadaId = jornadaId;
        this.mascotaId = mascotaId;
        this.duenoId = duenoId;
        this.vacunaId = vacunaId;
        this.veterinarioId = veterinarioId;
        this.fechaHora = fechaHora;
        this.loteVacuna = loteVacuna;
        this.proximaDosis = proximaDosis;
        this.observaciones = observaciones;
    }

    public RegistroJornadaVacunacion(int jornadaId, int mascotaId, int duenoId, int vacunaId, int veterinarioId,
                                     Timestamp fechaHora, String loteVacuna, Date proximaDosis, String observaciones) {
        this.jornadaId = jornadaId;
        this.mascotaId = mascotaId;
        this.duenoId = duenoId;
        this.vacunaId = vacunaId;
        this.veterinarioId = veterinarioId;
        this.fechaHora = fechaHora;
        this.loteVacuna = loteVacuna;
        this.proximaDosis = proximaDosis;
        this.observaciones = observaciones;
    }

    public int getId() { return id; }
    public int getJornadaId() { return jornadaId; }
    public int getMascotaId() { return mascotaId; }
    public int getDuenoId() { return duenoId; }
    public int getVacunaId() { return vacunaId; }
    public int getVeterinarioId() { return veterinarioId; }
    public Timestamp getFechaHora() { return fechaHora; }
    public String getLoteVacuna() { return loteVacuna; }
    public Date getProximaDosis() { return proximaDosis; }
    public String getObservaciones() { return observaciones; }

    public void setJornada(String jornada) { this.jornada = jornada; }
    public void setMascota(String mascota) { this.mascota = mascota; }
    public void setDueno(String dueno) { this.dueno = dueno; }
    public void setVacuna(String vacuna) { this.vacuna = vacuna; }
    public void setVeterinario(String veterinario) { this.veterinario = veterinario; }

    public String toString() {
        String nombreJornada = (this.jornada != null) ? this.jornada : "Jornada#" + jornadaId;
        String nombreMascota = (this.mascota != null) ? this.mascota : "Mascota#" + mascotaId;
        String nombreDueno = (this.dueno != null) ? this.dueno : "Dueño#" + duenoId;
        String nombreVacuna = (this.vacuna != null) ? this.vacuna : "Vacuna#" + vacunaId;
        String nombreVeterinario = (this.veterinario != null) ? this.veterinario : "Veterinario#" + veterinarioId;

        return "Registro Jornada Vacunacion{" +
                "id=" + id +
                ", jornada=" + nombreJornada +
                ", mascota=" + nombreMascota +
                ", dueño=" + nombreDueno +
                ", vacuna=" + nombreVacuna +
                ", veterinario=" + nombreVeterinario +
                ", fechaHora=" + fechaHora +
                ", loteVacuna='" + loteVacuna + '\'' +
                ", proximaDosis=" + proximaDosis +
                ", observaciones='" + observaciones + '\'' +
                '}';
    }
}