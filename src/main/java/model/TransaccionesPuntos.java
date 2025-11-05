/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author usuario
 */
import java.sql.Timestamp;

public class TransaccionesPuntos {

    public enum Tipo {
        Ganados,
        Canjeados,
        Expirados,
        Ajuste
    }

    private int id;
    private int clubMascotasId;
    private int facturaId;
    private int puntos;
    private Tipo tipo;
    private Timestamp fecha;
    private String descripcion;
    private int saldoAnterior;
    private int saldoNuevo;

    private String clubMascotas;
    private String factura;

    public TransaccionesPuntos(int id, int clubMascotasId, int facturaId, int puntos, Tipo tipo, Timestamp fecha,
                               String descripcion, int saldoAnterior, int saldoNuevo) {
        this.id = id;
        this.clubMascotasId = clubMascotasId;
        this.facturaId = facturaId;
        this.puntos = puntos;
        this.tipo = tipo;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.saldoAnterior = saldoAnterior;
        this.saldoNuevo = saldoNuevo;
    }

    public TransaccionesPuntos(int clubMascotasId, int facturaId, int puntos, Tipo tipo, Timestamp fecha,
                               String descripcion, int saldoAnterior, int saldoNuevo) {
        this.clubMascotasId = clubMascotasId;
        this.facturaId = facturaId;
        this.puntos = puntos;
        this.tipo = tipo;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.saldoAnterior = saldoAnterior;
        this.saldoNuevo = saldoNuevo;
    }

    public int getId() { return id; }
    public int getClubMascotasId() { return clubMascotasId; }
    public int getFacturaId() { return facturaId; }
    public int getPuntos() { return puntos; }
    public Tipo getTipo() { return tipo; }
    public Timestamp getFecha() { return fecha; }
    public String getDescripcion() { return descripcion; }
    public int getSaldoAnterior() { return saldoAnterior; }
    public int getSaldoNuevo() { return saldoNuevo; }

    public void setClubMascotas(String clubMascotas) { this.clubMascotas = clubMascotas; }
    public void setFactura(String factura) { this.factura = factura; }

    public String toString() {
        String nombreClub = (this.clubMascotas != null) ? this.clubMascotas : "ClubMascotas#" + clubMascotasId;
        String nombreFactura = (this.factura != null) ? this.factura : "Factura#" + facturaId;

        return "Transacciones Puntos{" +
                "id=" + id +
                ", clubMascotas=" + nombreClub +
                ", factura=" + nombreFactura +
                ", puntos=" + puntos +
                ", tipo=" + tipo +
                ", fecha=" + fecha +
                ", descripcion='" + descripcion + '\'' +
                ", saldo anterior=" + saldoAnterior +
                ", saldo nuevo=" + saldoNuevo +
                '}';
    }
}
