/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import dao.DuenosDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import model.Duenos;
import utils.ConexionDB;

/**
 *
 * @author usuario
 */
public class DuenosController {
    private DuenosDAO DuenosDao;
    
    public DuenosController(){
        this.DuenosDao = new DuenosDAO();
    } 
    
    public void agregar(String nombreCompleto, String documento, String direccion, String telefono, String email, String contactoEmergencia){
        if(nombreCompleto == null || nombreCompleto.trim().isBlank()){
            System.out.println("El nombre es obligatorio");
            return;
        }
        
        if(documento == null || documento.trim().isBlank()){
            System.out.println("El documento es obligatorio");
            return;
        }
        
        if(email == null || email.trim().isBlank()){
            System.out.println("El correo es obligatorio");
        }
        
        Duenos d = new Duenos(nombreCompleto, documento, direccion, telefono, email, contactoEmergencia, null, true);
        try (Connection con = ConexionDB.conectar()) {
            DuenosDao.agregar(d, con, email, documento);
        } catch (SQLException ex) {
            System.out.println("Error al agregar dueño: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public void actualizar(int id, String nombreCompleto, String documento, String direccion, String telefono, String email, String contactoEmergencia, boolean activo){
        if (id <= 0) {
            System.out.println("ID inválido.");
            return;
        } 
        if(nombreCompleto == null || nombreCompleto.trim().isBlank()){
            System.out.println("El nombre es obligatorio");
            return;
        }
        
        if(documento == null || documento.trim().isBlank()){
            System.out.println("El documento es obligatorio");
            return;
        }
        
        if(email == null || email.trim().isBlank()){
            System.out.println("El correo es obligatorio");
        }
        
        Duenos d = new Duenos(id, nombreCompleto, documento, direccion, telefono, email, contactoEmergencia, null, activo);
        try (Connection con = ConexionDB.conectar()) {
            DuenosDao.actualizar(d);
        } catch (SQLException ex) {
            System.out.println("Error al actualizar dueño: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public void eliminar(int id){
        if (id <= 0) {
            System.out.println("ID inválido.");
            return;
        }
        //se crea una especie "temporal" solo con el id
        Duenos d = new Duenos(id, "", "", "", "", "", "", null , true);
        DuenosDao.eliminar(d);
    }
    
    public List<Duenos> listar(){
        return DuenosDao.listar();
    }
}

