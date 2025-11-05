/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author usuario
 */
public class ItemFactura {
    private int id;
    private int facturaId;
    private String tipoItem;
    private int productoId;
    private int servicioId;
    private String servicioDescripcion;
    private int cantidad;
    private double precioUnitario;
    private double subtotal;

    private String factura;
    private String producto;
    private String servicio;

    public ItemFactura(int id, int facturaId, String tipoItem, int productoId, int servicioId,
                       String servicioDescripcion, int cantidad, double precioUnitario, double subtotal) {
        this.id = id;
        this.facturaId = facturaId;
        this.tipoItem = tipoItem;
        this.productoId = productoId;
        this.servicioId = servicioId;
        this.servicioDescripcion = servicioDescripcion;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
    }

    public ItemFactura(int facturaId, String tipoItem, int productoId, int servicioId,
                       String servicioDescripcion, int cantidad, double precioUnitario, double subtotal) {
        this.facturaId = facturaId;
        this.tipoItem = tipoItem;
        this.productoId = productoId;
        this.servicioId = servicioId;
        this.servicioDescripcion = servicioDescripcion;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
    }

    public int getId() { return id; }
    public int getFacturaId() { return facturaId; }
    public String getTipoItem() { return tipoItem; }
    public int getProductoId() { return productoId; }
    public int getServicioId() { return servicioId; }
    public String getServicioDescripcion() { return servicioDescripcion; }
    public int getCantidad() { return cantidad; }
    public double getPrecioUnitario() { return precioUnitario; }
    public double getSubtotal() { return subtotal; }

    public void setFactura(String factura) { this.factura = factura; }
    public void setProducto(String producto) { this.producto = producto; }
    public void setServicio(String servicio) { this.servicio = servicio; }

    public String toString() {
        String nombreFactura = (this.factura != null) ? this.factura : "Factura#" + facturaId;
        String nombreProducto = (this.producto != null) ? this.producto : "Producto#" + productoId;
        String nombreServicio = (this.servicio != null) ? this.servicio : "Servicio#" + servicioId;

        return "ItemFactura {id = " + id +
                ", factura = " + nombreFactura +
                ", tipoItem = " + tipoItem +
                ", producto = " + nombreProducto +
                ", servicio = " + nombreServicio +
                ", servicioDescripcion = " + servicioDescripcion +
                ", cantidad = " + cantidad +
                ", precioUnitario = " + precioUnitario +
                ", subtotal = " + subtotal +
                "}";
    }
}
