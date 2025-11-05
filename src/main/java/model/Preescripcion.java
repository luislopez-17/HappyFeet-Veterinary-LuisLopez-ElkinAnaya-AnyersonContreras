/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Timestamp;

/**
 *
 * @author usuario
 */
public class Preescripcion {
    private int id;
    private int consultaId;
    private int procedimientoId;
    private int productoId;
    private int cantidad;
    private String dosis;
    private String frecuencia;
    private int duracionDias;
    private String instrucciones;
    private Timestamp fechaPrescripcion;
    
    // Relaciones descriptivas
    private String consulta;
    private String procedimiento;
    private String producto;
    
    public Preescripcion(int id, int consultaId, int procedimientoId, int productoId, int cantidad, 
                        String dosis, String frecuencia, int duracionDias, String instrucciones, 
                        Timestamp fechaPrescripcion) {
        this.id = id;
        this.consultaId = consultaId;
        this.procedimientoId = procedimientoId;
        this.productoId = productoId;
        this.cantidad = cantidad;
        this.dosis = dosis;
        this.frecuencia = frecuencia;
        this.duracionDias = duracionDias;
        this.instrucciones = instrucciones;
        this.fechaPrescripcion = fechaPrescripcion;
    }
    
    public Preescripcion(int consultaId, int procedimientoId, int productoId, int cantidad, 
                        String dosis, String frecuencia, int duracionDias, String instrucciones, 
                        Timestamp fechaPrescripcion) {
        this.consultaId = consultaId;
        this.procedimientoId = procedimientoId;
        this.productoId = productoId;
        this.cantidad = cantidad;
        this.dosis = dosis;
        this.frecuencia = frecuencia;
        this.duracionDias = duracionDias;
        this.instrucciones = instrucciones;
        this.fechaPrescripcion = fechaPrescripcion;
    }
    
    public int getId(){return id;}
    public int getConsultaId(){return consultaId;}
    public int getProcedimientoId(){return procedimientoId;}
    public int getProductoId(){return productoId;}
    public int getCantidad(){return cantidad;}
    public String getDosis(){return dosis;}
    public String getFrecuencia(){return frecuencia;}
    public int getDuracionDias(){return duracionDias;}
    public String getInstrucciones(){return instrucciones;}
    public Timestamp getFechaPrescripcion(){return fechaPrescripcion;}
    
    public void setConsulta(String consulta){this.consulta = consulta;}
    public void setProcedimiento(String procedimiento){this.procedimiento = procedimiento;}
    public void setProducto(String producto){this.producto = producto;}
    
    public String toString(){
        String nombreConsulta = (this.consulta != null) ? this.consulta : "Consulta#" + consultaId;
        String nombreProcedimiento = (this.procedimiento != null) ? this.procedimiento : "Procedimiento#" + procedimientoId;
        String nombreProducto = (this.producto != null) ? this.producto : "Producto#" + productoId;
        
        return "Prescripción {id = " + id +
                ", consulta = " + nombreConsulta +
                ", procedimiento = " + nombreProcedimiento +
                ", producto = " + nombreProducto +
                ", cantidad = " + cantidad +
                ", dosis = " + dosis +
                ", frecuencia = " + frecuencia +
                ", duración días = " + duracionDias +
                ", instrucciones = " + instrucciones +
                ", fecha prescripción = " + fechaPrescripcion +
                "}";
    }
}
