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

public class Inventario {
    private int id;
    private String nombreProducto;
    private int productoTipoId;
    private String descripcion;
    private String fabricante;
    private int proveedorId;
    private String lote;
    private int cantidadStock;
    private int stockMinimo;
    private String unidadMedida;
    private Date fechaVencimiento;
    private double precioCompra;
    private double precioVenta;
    private boolean requiereReceta;
    private boolean activo;
    private Timestamp fechaRegistro;
    
    private String productoTipo;
    private String proveedor;
    
    public Inventario(int id, String nombreProducto, int productoTipoId, String descripcion, String fabricante, 
                      int proveedorId, String lote, int cantidadStock, int stockMinimo, String unidadMedida, 
                      Date fechaVencimiento, double precioCompra, double precioVenta, boolean requiereReceta, 
                      boolean activo, Timestamp fechaRegistro) {
        this.id = id;
        this.nombreProducto = nombreProducto;
        this.productoTipoId = productoTipoId;
        this.descripcion = descripcion;
        this.fabricante = fabricante;
        this.proveedorId = proveedorId;
        this.lote = lote;
        this.cantidadStock = cantidadStock;
        this.stockMinimo = stockMinimo;
        this.unidadMedida = unidadMedida;
        this.fechaVencimiento = fechaVencimiento;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.requiereReceta = requiereReceta;
        this.activo = activo;
        this.fechaRegistro = fechaRegistro;
    }
    
    public Inventario(String nombreProducto, int productoTipoId, String descripcion, String fabricante, 
                      int proveedorId, String lote, int cantidadStock, int stockMinimo, String unidadMedida, 
                      Date fechaVencimiento, double precioCompra, double precioVenta, boolean requiereReceta, 
                      boolean activo, Timestamp fechaRegistro) {
        this.nombreProducto = nombreProducto;
        this.productoTipoId = productoTipoId;
        this.descripcion = descripcion;
        this.fabricante = fabricante;
        this.proveedorId = proveedorId;
        this.lote = lote;
        this.cantidadStock = cantidadStock;
        this.stockMinimo = stockMinimo;
        this.unidadMedida = unidadMedida;
        this.fechaVencimiento = fechaVencimiento;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.requiereReceta = requiereReceta;
        this.activo = activo;
        this.fechaRegistro = fechaRegistro;
    }
    
    public int getId(){return id;}
    public String getNombreProducto(){return nombreProducto;}
    public int getProductoTipoId(){return productoTipoId;}
    public String getDescripcion(){return descripcion;}
    public String getFabricante(){return fabricante;}
    public int getProveedorId(){return proveedorId;}
    public String getLote(){return lote;}
    public int getCantidadStock(){return cantidadStock;}
    public int getStockMinimo(){return stockMinimo;}
    public String getUnidadMedida(){return unidadMedida;}
    public Date getFechaVencimiento(){return fechaVencimiento;}
    public double getPrecioCompra(){return precioCompra;}
    public double getPrecioVenta(){return precioVenta;}
    public boolean isRequiereReceta(){return requiereReceta;}
    public boolean isActivo(){return activo;}
    public Timestamp getFechaRegistro(){return fechaRegistro;}
    
    public void setProductoTipo(String productoTipo){this.productoTipo = productoTipo;}
    public void setProveedor(String proveedor){this.proveedor = proveedor;}
    public void setCantidadStock(int cantidadStock){this.cantidadStock = cantidadStock;}
    public void setActivo(boolean activo){this.activo = activo;}
    
    public String toString(){
        String nombreTipo = (this.productoTipo != null) ? this.productoTipo : "Tipo#" + productoTipoId;
        String nombreProveedor = (this.proveedor != null) ? this.proveedor : "Proveedor#" + proveedorId;
        
        return "Inventario {id = " + id +
                ", nombre producto = " + nombreProducto +
                ", tipo producto = " + nombreTipo +
                ", descripción = " + descripcion +
                ", fabricante = " + fabricante +
                ", proveedor = " + nombreProveedor +
                ", lote = " + lote +
                ", cantidad stock = " + cantidadStock +
                ", stock mínimo = " + stockMinimo +
                ", unidad medida = " + unidadMedida +
                ", fecha vencimiento = " + fechaVencimiento +
                ", precio compra = " + precioCompra +
                ", precio venta = " + precioVenta +
                ", requiere receta = " + requiereReceta +
                ", activo = " + activo +
                ", fecha registro = " + fechaRegistro +
                "}";
    }
}

