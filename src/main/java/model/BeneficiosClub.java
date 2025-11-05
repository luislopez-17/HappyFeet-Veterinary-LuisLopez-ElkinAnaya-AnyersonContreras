/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author usuario
 */
public class BeneficiosClub {

    public enum TipoBeneficio {
        Descuento,
        Servicio_Gratis,
        Producto_Gratis,
        Puntos_Extra
    }

    private int id;
    private String nombre;
    private String descripcion;
    private String nivelRequerido;
    private int puntosNecesarios;
    private TipoBeneficio tipoBeneficio;
    private double valorBeneficio;
    private boolean activo;

    public BeneficiosClub(int id, String nombre, String descripcion, String nivelRequerido, int puntosNecesarios,
                          TipoBeneficio tipoBeneficio, double valorBeneficio, boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.nivelRequerido = nivelRequerido;
        this.puntosNecesarios = puntosNecesarios;
        this.tipoBeneficio = tipoBeneficio;
        this.valorBeneficio = valorBeneficio;
        this.activo = activo;
    }

    public BeneficiosClub(String nombre, String descripcion, String nivelRequerido, int puntosNecesarios,
                          TipoBeneficio tipoBeneficio, double valorBeneficio, boolean activo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.nivelRequerido = nivelRequerido;
        this.puntosNecesarios = puntosNecesarios;
        this.tipoBeneficio = tipoBeneficio;
        this.valorBeneficio = valorBeneficio;
        this.activo = activo;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public String getNivelRequerido() { return nivelRequerido; }
    public int getPuntosNecesarios() { return puntosNecesarios; }
    public TipoBeneficio getTipoBeneficio() { return tipoBeneficio; }
    public double getValorBeneficio() { return valorBeneficio; }
    public boolean isActivo() { return activo; }

    public String toString() {
        return "Beneficios Club{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", nivel='" + nivelRequerido + '\'' +
                ", puntos necesarios=" + puntosNecesarios +
                ", tipo beneficio=" + tipoBeneficio +
                ", valor beneficio=" + valorBeneficio +
                ", activo=" + activo +
                '}';
    }
}