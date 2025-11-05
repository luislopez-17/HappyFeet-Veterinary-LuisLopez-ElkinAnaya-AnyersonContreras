/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author usuario
 */
public class EventosTipos {
    private int id;
    private String nombre;
    private String descripcion;
    
    public EventosTipos(int id, String nombre, String descripcion){
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
    
    public EventosTipos(String nombre, String descripcion){
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
    
    public int getId(){return id;}
    public String getNombre(){return nombre;}
    public String getDescripcion(){return descripcion;}
    
    public String toString(){
        return "Tipo de eventos{id = " + id + ", nombre = " + nombre + ", descripcion = " + descripcion;
    }
}
