/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import dao.EventoTipoDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import model.EventosTipos;
import utils.ConexionDB;

/**
 *
 * @author usuario
 */
public class EventoTipoController {
    private EventoTipoDAO eventoTipoDao;
    
    public EventoTipoController(){
        this.eventoTipoDao = new EventoTipoDAO();
    }
    
    public void agregarEvento(String nombre, String descripcion){
        if(nombre == null || nombre.trim().isEmpty()){
            System.out.println("El nombre no puede estar vacio.");
            return;
        }
        
        EventosTipos et = new EventosTipos(nombre, descripcion);
        try (Connection con = ConexionDB.conectar()) {
            eventoTipoDao.agregar(et, con, nombre);
            System.out.println("Evento tipo agregado correctamente.");
        } catch (SQLException ex) {
            System.out.println("Error al agregar: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public void actualizarEvento(int id, String nombre, String descripcion){
        if (id <= 0) {
            System.out.println("ID inválido.");
            return;
        }
        if (nombre == null || nombre.trim().isEmpty()) {
            System.out.println("El nombre no puede estar vacío.");
            return;
        }
        
        EventosTipos et = new EventosTipos(id, nombre, descripcion);
        try (Connection con = ConexionDB.conectar()) {
            eventoTipoDao.actualizar(et, con, nombre);
        } catch (SQLException ex) {
            System.out.println("Error al actualizar: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public void eliminarEvento(int id){
        if (id <= 0) {
            System.out.println("ID inválido.");
            return;
        }
        //se crea una especie "temporal" solo con el id
        EventosTipos et = new EventosTipos(id, "", "");
        eventoTipoDao.eliminar(et);
    }
    
    public List<EventosTipos> listar(){
        return eventoTipoDao.listar();
    }
}
