/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author usuario
 */
public class Servicios {
     private int id;
    private String nombre;
    private String descripcion;
    private String categoria;
    private double precioBase;
    private int duracionEstimadaMinutos;
    private boolean activo;

    public Servicios(int id, String nombre, String descripcion, String categoria, double precioBase, int duracionEstimadaMinutos, boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.precioBase = precioBase;
        this.duracionEstimadaMinutos = duracionEstimadaMinutos;
        this.activo = activo;
    }

    public Servicios(String nombre, String descripcion, String categoria, double precioBase, int duracionEstimadaMinutos, boolean activo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.precioBase = precioBase;
        this.duracionEstimadaMinutos = duracionEstimadaMinutos;
        this.activo = activo;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public String getCategoria() { return categoria; }
    public double getPrecioBase() { return precioBase; }
    public int getDuracionEstimadaMinutos() { return duracionEstimadaMinutos; }
    public boolean isActivo() { return activo; }

    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public void setActivo(boolean activo) { this.activo = activo; }

    public String toString() {
        return "Servicio {id = " + id +
                ", nombre = " + nombre +
                ", descripcion = " + descripcion +
                ", categoria = " + categoria +
                ", precioBase = " + precioBase +
                ", duracionEstimadaMinutos = " + duracionEstimadaMinutos +
                ", activo=" + activo +
                "}";
    }
}
