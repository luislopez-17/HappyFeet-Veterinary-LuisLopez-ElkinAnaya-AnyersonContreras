/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author usuario
 */

import java.util.Date;

public class Factura {
    private int id;
    private int duenoId;
    private String numeroFactura;
    private Date fechaEmision;
    private double subtotal;
    private double impuesto;
    private double descuento;
    private double total;
    private String metodoPago;
    private String estado;
    private String observaciones;

    private String dueno;

    public Factura(int id, int duenoId, String numeroFactura, Date fechaEmision, double subtotal,
                   double impuesto, double descuento, double total, String metodoPago,
                   String estado, String observaciones) {
        this.id = id;
        this.duenoId = duenoId;
        this.numeroFactura = numeroFactura;
        this.fechaEmision = fechaEmision;
        this.subtotal = subtotal;
        this.impuesto = impuesto;
        this.descuento = descuento;
        this.total = total;
        this.metodoPago = metodoPago;
        this.estado = estado;
        this.observaciones = observaciones;
    }

    public Factura(int duenoId, String numeroFactura, Date fechaEmision, double subtotal,
                   double impuesto, double descuento, double total, String metodoPago,
                   String estado, String observaciones) {
        this.duenoId = duenoId;
        this.numeroFactura = numeroFactura;
        this.fechaEmision = fechaEmision;
        this.subtotal = subtotal;
        this.impuesto = impuesto;
        this.descuento = descuento;
        this.total = total;
        this.metodoPago = metodoPago;
        this.estado = estado;
        this.observaciones = observaciones;
    }

    public int getId() { return id; }
    public int getDuenoId() { return duenoId; }
    public String getNumeroFactura() { return numeroFactura; }
    public Date getFechaEmision() { return fechaEmision; }
    public double getSubtotal() { return subtotal; }
    public double getImpuesto() { return impuesto; }
    public double getDescuento() { return descuento; }
    public double getTotal() { return total; }
    public String getMetodoPago() { return metodoPago; }
    public String getEstado() { return estado; }
    public String getObservaciones() { return observaciones; }

    public void setDueno(String dueno) { this.dueno = dueno; }

    public String toString() {
        String nombreDueno = (this.dueno != null) ? this.dueno : "Dueño#" + duenoId;

        return "Factura {id = " + id +
                ", dueño = " + nombreDueno +
                ", númeroFactura = " + numeroFactura +
                ", fechaEmisión = " + fechaEmision +
                ", subtotal = " + subtotal +
                ", impuesto = " + impuesto +
                ", descuento = " + descuento +
                ", total = " + total +
                ", métodoPago = " + metodoPago +
                ", estado = " + estado +
                ", observaciones = " + observaciones +
                "}";
    }
}