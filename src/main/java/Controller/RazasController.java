/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import dao.RazaDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import model.Razas;
import utils.ConexionDB;

/**
 *
 * @author usuario
 */
public class RazasController {
    private RazaDAO razaDao;
    
    public RazasController(){
        this.razaDao = new RazaDAO();
    }
    
    public void agregarRaza(Integer especieId, String nombre, String caracteristicas){
        if(especieId == null || especieId <= 0) {
            System.out.println("ESPECIE ID NO PUEDE ESTAR VACIA NI SER MENOR QUE 0");
            return;
        }
        
        if(nombre == null || nombre.isBlank()){
            System.out.println("El nombre es obligatorio, no puede estar vacio");
            return;
        }
        
        Razas r = new Razas(especieId, nombre, caracteristicas);
        try (Connection con = ConexionDB.conectar()) {
            razaDao.agregar(r, con, especieId);
            System.out.println("Raza agregada correctamente");
        } catch (SQLException e) {
            System.out.println("Error al agregar la raza: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void actualizarRaza(int id, String nombre, String caracteristicas, Integer especieId){
        if (id <= 0) {
            System.out.println("ID inválido.");
            return;
        }
        
        if (especieId == null || especieId <= 0) {
            System.out.println("El ID de la especie no puede estar vacío ni ser menor o igual a 0.");
            return;
        }
        
        if(nombre == null || nombre.isBlank()){
            System.out.println("El nombre es obligatorio, no puede estar vacio");
            return;
        }
        
        Razas r = new Razas(id, especieId, nombre, caracteristicas);
        try (Connection con = ConexionDB.conectar()) {
            razaDao.actualizar(r, especieId);
        } catch (SQLException e) {
            System.out.println("Error al actualizar la raza: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void eliminarRaza(int id){
        if(id <= 0){
            System.out.println("El ID tiene que ser mayor que cero");
            return;
        }
        
        Razas r = new Razas(id, 0, "", "");
        razaDao.eliminar(r);
    }
    
    public List<Razas> listarRazas(){
        return razaDao.listar();
    }
}
