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
public class Adopcion {
    private int id;
    private int mascotaAdopcionId;
    private int duenoId;
    private Date fechaAdopcion;
    private String contratoTexto;
    private String condicionesEspeciales;
    private boolean seguimientoRequerido;
    private Date fechaPrimerSeguimiento;

    private String mascotaAdopcion;
    private String dueno;

    public Adopcion(int id, int mascotaAdopcionId, int duenoId, Date fechaAdopcion,
                    String contratoTexto, String condicionesEspeciales,
                    boolean seguimientoRequerido, Date fechaPrimerSeguimiento) {
        this.id = id;
        this.mascotaAdopcionId = mascotaAdopcionId;
        this.duenoId = duenoId;
        this.fechaAdopcion = fechaAdopcion;
        this.contratoTexto = contratoTexto;
        this.condicionesEspeciales = condicionesEspeciales;
        this.seguimientoRequerido = seguimientoRequerido;
        this.fechaPrimerSeguimiento = fechaPrimerSeguimiento;
    }

    public Adopcion(int mascotaAdopcionId, int duenoId, Date fechaAdopcion,
                    String contratoTexto, String condicionesEspeciales,
                    boolean seguimientoRequerido, Date fechaPrimerSeguimiento) {
        this.mascotaAdopcionId = mascotaAdopcionId;
        this.duenoId = duenoId;
        this.fechaAdopcion = fechaAdopcion;
        this.contratoTexto = contratoTexto;
        this.condicionesEspeciales = condicionesEspeciales;
        this.seguimientoRequerido = seguimientoRequerido;
        this.fechaPrimerSeguimiento = fechaPrimerSeguimiento;
    }

    public int getId() { return id; }
    public int getMascotaAdopcionId() { return mascotaAdopcionId; }
    public int getDuenoId() { return duenoId; }
    public Date getFechaAdopcion() { return fechaAdopcion; }
    public String getContratoTexto() { return contratoTexto; }
    public String getCondicionesEspeciales() { return condicionesEspeciales; }
    public boolean isSeguimientoRequerido() { return seguimientoRequerido; }
    public Date getFechaPrimerSeguimiento() { return fechaPrimerSeguimiento; }

    public void setMascotaAdopcion(String mascotaAdopcion) { this.mascotaAdopcion = mascotaAdopcion; }
    public void setDueno(String dueno) { this.dueno = dueno; }

    public String toString() {
        String nombreMascotaAdopcion = (this.mascotaAdopcion != null) ? this.mascotaAdopcion : "MascotaAdopcion#" + mascotaAdopcionId;
        String nombreDueno = (this.dueno != null) ? this.dueno : "Dueño#" + duenoId;

        return "Adopcion {id = " + id +
                ", mascotaAdopcion = " + nombreMascotaAdopcion +
                ", dueño = " + nombreDueno +
                ", fechaAdopcion = " + fechaAdopcion +
                ", contratoTexto = " + contratoTexto +
                ", condicionesEspeciales = " + condicionesEspeciales +
                ", seguimientoRequerido = " + seguimientoRequerido +
                ", fechaPrimerSeguimiento = " + fechaPrimerSeguimiento +
                "}";
    }
}
