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
public class HistorialMedico {

    private int id;
    private int mascotaId;
    private Date fechaEvento;
    private int eventoTipoId;
    private String descripcion;
    private String diagnostico;
    private String tratamientoRecomendado;
    private int veterinarioId;
    private int consultaId;
    private int procedimientoId;
    
    private String mascota;
    private String eventoTipo;
    private String veterinario;
    private String consulta;
    private String procedimiento;
    
    public HistorialMedico(int id, int mascotaId, Date fechaEvento, int eventoTipoId, String descripcion, String diagnostico, String tratamiento, int veterinarioId, int consultaId, int procedimientoId){
        this.id = id;
        this.mascotaId = mascotaId;
        this.fechaEvento = fechaEvento;
        this.eventoTipoId = eventoTipoId;
        this.descripcion = descripcion;
        this.diagnostico = diagnostico;
        this.tratamientoRecomendado = tratamiento;
        this.veterinarioId = veterinarioId;
        this.consultaId = consultaId;
        this.procedimientoId = procedimientoId;
    }
    
    public HistorialMedico(int mascotaId, Date fechaEvento, int eventoTipoId, String descripcion, String diagnostico, String tratamiento, int veterinarioId, int consultaId, int procedimientoId){
        this.mascotaId = mascotaId;
        this.fechaEvento = fechaEvento;
        this.eventoTipoId = eventoTipoId;
        this.descripcion = descripcion;
        this.diagnostico = diagnostico;
        this.tratamientoRecomendado = tratamiento;
        this.veterinarioId = veterinarioId;
        this.consultaId = consultaId;
        this.procedimientoId = procedimientoId;
    }
    

    public int getId(){return id;}
    public int getMascotaId(){return mascotaId;}
    public Date getFechaEvento(){return fechaEvento;}
    public int getEventoTipoId(){return eventoTipoId;}
    public String getDescripcion(){return descripcion;}
    public String getDiagnostico(){return diagnostico;}
    public String getTratamientoRecomendado(){return tratamientoRecomendado;}
    public int getVeterinarioId(){return veterinarioId;}
    public int getConsultaId(){return consultaId;}
    public int getProcedimientoId(){return procedimientoId;}
    
    public void setMascota(String mascota){this.mascota = mascota;}
    public void setEventoTipo(String eventoTipo){this.eventoTipo = eventoTipo;}
    public void setVeterinario(String veterinario){this.veterinario = veterinario;}
    public void setConsulta(String consulta){this.consulta = consulta;}
    public void setProcedimiento(String procedimiento){this.procedimiento = procedimiento;}
    
    public String toString(){
        String nombreMascota = (this.mascota != null) ? this.mascota : "Mascota#" + mascotaId;
        String nombreEvento = (this.eventoTipo != null) ? this.eventoTipo : "Evento#" + eventoTipoId;
        String nombreVeterinario = (this.veterinario != null) ? this.veterinario : "Veterinario#" + veterinarioId;
        String nombreConsulta = (this.consulta != null) ? this.consulta : "Consulta#" + consultaId;
        String nombreProcedimiento = (this.procedimiento != null) ? this.procedimiento : "Procedimiento#" + procedimientoId;
        
        return "Historial Médico {id = " + id +
                ", mascota = " + nombreMascota +
                ", fecha evento = " + fechaEvento +
                ", tipo evento = " + nombreEvento +
                ", descripción = " + descripcion +
                ", diagnóstico = " + diagnostico +
                ", tratamiento recomendado = " + tratamientoRecomendado +
                ", veterinario = " + nombreVeterinario +
                ", consulta = " + nombreConsulta +
                ", procedimiento = " + nombreProcedimiento + "}";
    }
}
