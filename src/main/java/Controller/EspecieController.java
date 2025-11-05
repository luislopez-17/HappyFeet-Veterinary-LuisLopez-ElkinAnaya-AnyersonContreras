/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import dao.EspecieDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import model.Especies;
import utils.ConexionDB;

/**
 *
 * @author usuario
 */
public class EspecieController {
    private EspecieDAO especieDAO;
    
    //CONSTRUCTOR
    public EspecieController(){
        this.especieDAO =  new EspecieDAO();
    }
    
    //AGREGAR NUEVA ESPECIE
    public void agregarEspecie(String nombre, String descripcion){
        if(nombre == null || nombre.trim().isEmpty()){
            System.out.println("El nombre no puede estar vacio.");
            return;
        }
        
        Especies e = new Especies(nombre, descripcion);
        try (Connection con = ConexionDB.conectar()) {
            especieDAO.agregar(e, con, nombre);
        } catch (SQLException ex) {
            System.out.println("Error al agregar la raza: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public void actualizarEspecie(int id, String nombre, String descripcion){
        if (id <= 0) {
            System.out.println("ID inválido.");
            return;
        }
        if (nombre == null || nombre.trim().isEmpty()) {
            System.out.println("El nombre no puede estar vacío.");
            return;
        }
        
        Especies e = new Especies(id, nombre, descripcion);
        try (Connection con = ConexionDB.conectar()) {
            especieDAO.actualizar(e, con, nombre);
        } catch (SQLException ex) {
            System.out.println("Error al actualizar: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public void eliminarEspecie(int id){
        if (id <= 0) {
            System.out.println("ID inválido.");
            return;
        }
        //se crea una especie "temporal" solo con el id
        Especies e = new Especies(id, "", "");
        especieDAO.eliminar(e);
    }
    
    public List<Especies> listarEspecie(){
        return especieDAO.listar();
    }
}
