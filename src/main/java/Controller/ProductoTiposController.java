/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import dao.ProductoTiposDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import model.ProductoTipos;
import utils.ConexionDB;

/**
 *
 * @author usuario
 */
public class ProductoTiposController {
    private ProductoTiposDAO productoTiposDao;
    
    public ProductoTiposController(){
        this.productoTiposDao = new ProductoTiposDAO();
    }
    
    public void agregarProducto(String nombre, String descripcion){
        if(nombre == null || nombre.trim().isEmpty()){
            System.out.println("El nombre no puede estar vacio.");
            return;
        }
        
        ProductoTipos pt = new ProductoTipos(nombre, descripcion);
        try (Connection con = ConexionDB.conectar()) {
            productoTiposDao.agregar(pt, con, nombre);
        } catch (SQLException ex) {
            System.out.println("Error al agregar: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public void actualizarProducto(int id, String nombre, String descripcion){
        if (id <= 0) {
            System.out.println("ID inválido.");
            return;
        }
        if (nombre == null || nombre.trim().isEmpty()) {
            System.out.println("El nombre no puede estar vacío.");
            return;
        }
        
        ProductoTipos pt = new ProductoTipos(id, nombre, descripcion);
        try (Connection con = ConexionDB.conectar()) {
            productoTiposDao.actualizar(pt, con, nombre);
        } catch (SQLException ex) {
            System.out.println("Error al actualizar: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public void eliminarProducto(int id){
        if (id <= 0) {
            System.out.println("ID inválido.");
            return;
        }
        //se crea una especie "temporal" solo con el id
        ProductoTipos pt = new ProductoTipos(id, "", "");
        productoTiposDao.eliminar(pt);
    }
    
    public List<ProductoTipos> listar(){
        return productoTiposDao.listar();
    }
}
