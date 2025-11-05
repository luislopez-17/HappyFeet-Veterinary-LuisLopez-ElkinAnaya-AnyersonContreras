/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author usuario
 */
public class ProductoTipos {
    private int id;
    private String nombre;
    private String descripcion;
    
    public ProductoTipos(int id, String nombre, String descripcion){
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
    
    public ProductoTipos(String nombre, String descripcion){
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
    
    public int getId(){return id;}
    public String getNombre(){return nombre;}
    public String getDescripcion(){return descripcion;}
    
    public String toString(){
        return "Tipo de producto{id = " + id + ", nombre = " + nombre + ", descripcion = " + descripcion;
    }
    
}
