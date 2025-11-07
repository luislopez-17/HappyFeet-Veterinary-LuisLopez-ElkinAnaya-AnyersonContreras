/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author usuario
 */
public class ReporteProducto {
    private String nombreProducto;
    private int cantidadVendida;
    private double ingresosTotales;

    public ReporteProducto(String nombreProducto, int cantidadVendida, double ingresosTotales) {
        this.nombreProducto = nombreProducto;
        this.cantidadVendida = cantidadVendida;
        this.ingresosTotales = ingresosTotales;
    }

    public String getNombreProducto() { return nombreProducto; }
    public int getCantidadVendida() { return cantidadVendida; }
    public double getIngresosTotales() { return ingresosTotales; }
}

