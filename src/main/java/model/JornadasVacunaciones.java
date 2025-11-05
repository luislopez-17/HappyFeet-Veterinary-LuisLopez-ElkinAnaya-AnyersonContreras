/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.sql.Date;
import java.sql.Time;
/**
 *
 * @author usuario
 */
public class JornadasVacunaciones {

    public enum Estado {
        Planificada,
        En_Curso,
        Finalizada,
        Cancelada
    }
    
    private int id;
    private String nombre;
    private Date fecha;
    private Time horaInicio;
    private Time horaFin;
    private String ubicacion;
    private String descripcion;
    private int capacidadMaxima;
    private Estado estado;
    
    public JornadasVacunaciones(int id, String nombre, Date fecha, Time horaInicio, Time horaFin, String ubicacion, String descripcion, int capacidadMaxima, Estado estado) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.ubicacion = ubicacion;
        this.descripcion = descripcion;
        this.capacidadMaxima = capacidadMaxima;
        this.estado = estado;
    }
    
    public JornadasVacunaciones(String nombre, Date fecha, Time horaInicio, Time horaFin, String ubicacion, String descripcion, int capacidadMaxima, Estado estado) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.ubicacion = ubicacion;
        this.descripcion = descripcion;
        this.capacidadMaxima = capacidadMaxima;
        this.estado = estado;
    }
    
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public Date getFecha() { return fecha; }
    public Time getHoraInicio() { return horaInicio; }
    public Time getHoraFin() { return horaFin; }
    public String getUbicacion() { return ubicacion; }
    public String getDescripcion() { return descripcion; }
    public int getCapacidadMaxima() { return capacidadMaxima; }
    public Estado getEstado() { return estado; }
    
    public String toString() {
        return "Jornadas Vacunacion{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", fecha=" + fecha +
                ", hora inicio=" + horaInicio +
                ", hora fin=" + horaFin +
                ", ubicacion='" + ubicacion + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", capacidad maxima=" + capacidadMaxima +
                ", estado=" + estado +
                '}';
    }
}

