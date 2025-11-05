/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author usuario
 */
public class MovimientoInventario {
    private int id;
    private int productoId;
    private String tipoMovimiento;
    private int cantidad;
    private int stockAnterior;
    private int stockNuevo;
    private String motivo;
    private Integer referenciaConsultaId;
    private Integer referenciaProcedimientoId;
    private String usuario;
    private Timestamp fechaMovimiento;

    private String producto;
    private String consulta;
    private String procedimiento;

    public MovimientoInventario(int id, int productoId, String tipoMovimiento, int cantidad, int stockAnterior, int stockNuevo,
                                String motivo, Integer referenciaConsultaId, Integer referenciaProcedimientoId,
                                String usuario, Timestamp fechaMovimiento) {
        this.id = id;
        this.productoId = productoId;
        this.tipoMovimiento = tipoMovimiento;
        this.cantidad = cantidad;
        this.stockAnterior = stockAnterior;
        this.stockNuevo = stockNuevo;
        this.motivo = motivo;
        this.referenciaConsultaId = referenciaConsultaId;
        this.referenciaProcedimientoId = referenciaProcedimientoId;
        this.usuario = usuario;
        this.fechaMovimiento = fechaMovimiento;
    }

    public MovimientoInventario(int productoId, String tipoMovimiento, int cantidad, int stockAnterior, int stockNuevo,
                                String motivo, Integer referenciaConsultaId, Integer referenciaProcedimientoId,
                                String usuario, Timestamp fechaMovimiento) {
        this.productoId = productoId;
        this.tipoMovimiento = tipoMovimiento;
        this.cantidad = cantidad;
        this.stockAnterior = stockAnterior;
        this.stockNuevo = stockNuevo;
        this.motivo = motivo;
        this.referenciaConsultaId = referenciaConsultaId;
        this.referenciaProcedimientoId = referenciaProcedimientoId;
        this.usuario = usuario;
        this.fechaMovimiento = fechaMovimiento;
    }

    public int getId() { return id; }
    public int getProductoId() { return productoId; }
    public String getTipoMovimiento() { return tipoMovimiento; }
    public int getCantidad() { return cantidad; }
    public int getStockAnterior() { return stockAnterior; }
    public int getStockNuevo() { return stockNuevo; }
    public String getMotivo() { return motivo; }
    public Integer getReferenciaConsultaId() { return referenciaConsultaId; }
    public Integer getReferenciaProcedimientoId() { return referenciaProcedimientoId; }
    public String getUsuario() { return usuario; }
    public Timestamp getFechaMovimiento() { return fechaMovimiento; }

    public void setFechaMovimiento(Timestamp fechaMovimiento) {
    this.fechaMovimiento = fechaMovimiento;
}

    public void setProducto(String producto) { this.producto = producto; }
    public void setConsulta(String consulta) { this.consulta = consulta; }
    public void setProcedimiento(String procedimiento) { this.procedimiento = procedimiento; }

    public String toString() {
        String nombreProducto = (this.producto != null) ? this.producto : "Producto#" + productoId;
        String nombreConsulta = (this.consulta != null) ? this.consulta : "Consulta#" + referenciaConsultaId;
        String nombreProcedimiento = (this.procedimiento != null) ? this.procedimiento : "Procedimiento#" + referenciaProcedimientoId;

        return "MovimientoInventario {id = " + id +
                ", producto = " + nombreProducto +
                ", tipoMovimiento = " + tipoMovimiento +
                ", cantidad = " + cantidad +
                ", stockAnterior = " + stockAnterior +
                ", stockNuevo = " + stockNuevo +
                ", motivo = " + motivo +
                ", referenciaConsulta = " + nombreConsulta +
                ", referenciaProcedimiento = " + nombreProcedimiento +
                ", usuario = " + usuario +
                ", fechaMovimiento = " + fechaMovimiento +
                "}";
    }
}
