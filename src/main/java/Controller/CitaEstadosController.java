/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import dao.CitaEstadosDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import model.CitaEstados;
import utils.ConexionDB;

/**
 *
 * @author usuario
 */
public class CitaEstadosController {
    private CitaEstadosDAO citaEstadosDao;
    
    public CitaEstadosController(){
        this.citaEstadosDao = new CitaEstadosDAO();
    }
    
    public void agregarCita(String nombre, String descripcion){
        if(nombre == null || nombre.trim().isEmpty()){
            System.out.println("El nombre no puede estar vacio.");
            return;
        }
        
        CitaEstados ce = new CitaEstados(nombre, descripcion);
        try (Connection con = ConexionDB.conectar()) {
            citaEstadosDao.agregar(ce, con, nombre);
        } catch (SQLException ex) {
            System.out.println("Error al agregar: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public void actualizarCita(int id, String nombre, String descripcion){
        if (id <= 0) {
            System.out.println("ID inválido.");
            return;
        }
        if (nombre == null || nombre.trim().isEmpty()) {
            System.out.println("El nombre no puede estar vacío.");
            return;
        }
        
        CitaEstados et = new CitaEstados(id, nombre, descripcion);
        try (Connection con = ConexionDB.conectar()) {
            citaEstadosDao.actualizar(et, con, nombre);
        } catch (SQLException ex) {
            System.out.println("Error al actualizar: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public void eliminarCita(int id){
        if (id <= 0) {
            System.out.println("ID inválido.");
            return;
        }
        //se crea una especie "temporal" solo con el id
        CitaEstados et = new CitaEstados(id, "", "");
        citaEstadosDao.eliminar(et);
    }
    
    public List<CitaEstados> listar(){
        return citaEstadosDao.listar();
    }
}
