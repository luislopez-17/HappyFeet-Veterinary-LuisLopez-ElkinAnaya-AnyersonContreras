/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author usuario
 */
public class InsumosProcedimientos {
    private int id;
    private int procedimientoId;
    private int productoId;
    private int cantidadUsada;
    private String observaciones;

    private String procedimiento;
    private String producto;

    public InsumosProcedimientos(int id, int procedimientoId, int productoId, int cantidadUsada, String observaciones) {
        this.id = id;
        this.procedimientoId = procedimientoId;
        this.productoId = productoId;
        this.cantidadUsada = cantidadUsada;
        this.observaciones = observaciones;
    }

    public InsumosProcedimientos(int procedimientoId, int productoId, int cantidadUsada, String observaciones) {
        this.procedimientoId = procedimientoId;
        this.productoId = productoId;
        this.cantidadUsada = cantidadUsada;
        this.observaciones = observaciones;
    }

    public int getId() { return id; }
    public int getProcedimientoId() { return procedimientoId; }
    public int getProductoId() { return productoId; }
    public int getCantidadUsada() { return cantidadUsada; }
    public String getObservaciones() { return observaciones; }

    public void setProcedimiento(String procedimiento) { this.procedimiento = procedimiento; }
    public void setProducto(String producto) { this.producto = producto; }

    public String toString() {
        String nombreProcedimiento = (this.procedimiento != null) ? this.procedimiento : "Procedimiento#" + procedimientoId;
        String nombreProducto = (this.producto != null) ? this.producto : "Producto#" + productoId;

        return "InsumoProcedimiento {id = " + id +
                ", procedimiento = " + nombreProcedimiento +
                ", producto = " + nombreProducto +
                ", cantidad usada = " + cantidadUsada +
                ", observaciones = " + observaciones +
                "}";
    }
}
