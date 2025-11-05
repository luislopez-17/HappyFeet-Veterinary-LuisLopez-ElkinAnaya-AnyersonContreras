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
public class ConsultaMedica {
    private int id;
    private int idMascotas;
    private int idVeterinario;
    private int idCita;
    private Timestamp fechaHora;
    private String motivo;
    private String sintomas;
    private String diagnostico;
    private String recomendaciones;
    private String observaciones;
    private double pesoRegistrado;
    private double temperatura;
    
    private String mascota;
    private String veterinario;
    private String cita;
    
    public ConsultaMedica(int id, int idMascotas, int idVeterinario, int idCita, Timestamp fechaHora, String motivo, String sintomas, String diagnostico, String recomedaciones,
            String observaciones, double pesoRegistrado, double temperatura){
        this.id = id;
        this.idMascotas = idMascotas;
        this.idVeterinario = idVeterinario;
        this.idCita = idCita;
        this.fechaHora = fechaHora;
        this.motivo = motivo;
        this.sintomas = sintomas;
        this.diagnostico = diagnostico;
        this.recomendaciones = recomedaciones;
        this.observaciones = observaciones;
        this.pesoRegistrado = pesoRegistrado;
        this.temperatura = temperatura;
    }
    
    public ConsultaMedica(int idMascotas, int idVeterinario, int idCita, Timestamp fechaHora, String motivo, String sintomas, String diagnostico, String recomedaciones,
            String observaciones, double pesoRegistrado, double temperatura){
        this.idMascotas = idMascotas;
        this.idVeterinario = idVeterinario;
        this.idCita = idCita;
        this.fechaHora = fechaHora;
        this.motivo = motivo;
        this.sintomas = sintomas;
        this.diagnostico = diagnostico;
        this.recomendaciones = recomedaciones;
        this.observaciones = observaciones;
        this.pesoRegistrado = pesoRegistrado;
        this.temperatura = temperatura;
    }
    
    public int getId(){return id;}
    public int getIdMascotas(){return idMascotas;}
    public int getIdVeterinario(){return idVeterinario;}
    public int getIdCita(){return idCita;}
    public Timestamp getFechaHora(){return fechaHora;}
    public String getMotivo(){return motivo;}
    public String getSintomas(){return sintomas;}
    public String getDiagnostico(){return diagnostico;}
    public String getRecomendaciones(){return recomendaciones;}
    public String getObservaciones(){return observaciones;}
    public double getPesoRegistrado(){return pesoRegistrado;}
    public double getTemperatura(){return temperatura;}
    
    public void setMascota(String mascota){this.mascota = mascota;}
    private void setVeterinario(String veterinario){this.veterinario = veterinario;}
    public void setCita(String cita){this.cita = cita;}
    
    public String toString(){
        String nombreMascota = (this.mascota != null) ? this.mascota : "Mascota#" + idMascotas;
        String nombreVeterinario = (this.veterinario != null) ? this.veterinario : "Veterinario#" + idVeterinario;
        String nomCita = (this.cita != null) ? this.cita : "Cita#" +idCita;
        return "Consulta Medica{id = " + id + ", mascota = " + nombreMascota + ", veterinario = " + nombreVeterinario + ", cita = " + nomCita +
                ", fecha y hora = " + fechaHora + ", motivo = " + motivo + ", sintomas = " + sintomas + ", diagnostico = " + diagnostico +
                ", recomendaciones = " + recomendaciones + ", observaciones = " + observaciones + ", peso = " + pesoRegistrado + ", temperatura = " + temperatura + "}";             
    }
}
