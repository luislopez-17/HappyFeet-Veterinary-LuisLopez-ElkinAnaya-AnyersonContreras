/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author usuario
 */
public class Razas {
    private int id;
    private int especieID;
    private String nombre;
    private String caracteristicas;
    
    public String especieNombre;
    
    public Razas(int id, int especieID, String nombre, String caracteristicas){
        this.id = id;
        this.especieID = especieID;
        this.nombre = nombre;
        this.caracteristicas = caracteristicas;
    }
    
    public Razas(int especieID, String nombre, String caracteristicas){
        this.especieID = especieID;
        this.nombre = nombre;
        this.caracteristicas = caracteristicas;
    }
    
    public int getId(){return id;}
    public int getEspecieID(){return especieID;}
    public String getNombre(){return nombre;}
    public String getCaracteristicas(){return caracteristicas;}
     
    public void setEspecieNombre(String especieNombre){this.especieNombre = especieNombre;}
    
    public String toString(){
        String especie = (especieNombre != null) ? especieNombre : "Especie#" + especieID;
        return "Raza{id = " + id + ", especie = " + especie + ", nombre: " + nombre +", caracteristicas = " + caracteristicas+"}";
    } 
}
