/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import dao.ServiciosDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import model.Servicios;
import utils.ConexionDB;
/**
 *
 * @author ELKIN
 */
public class ServiciosController {
    private ServiciosDAO serviciosDAO;

    // CONSTRUCTOR
    public ServiciosController() {
        this.serviciosDAO = new ServiciosDAO();
    }

    // AGREGAR NUEVO SERVICIO
    public void agregarServicio(String nombre, String descripcion, String categoria, double precioBase, int duracionEstimadaMinutos) {
        if (nombre == null || nombre.isBlank()) {
            System.out.println("El nombre no puede estar vacío.");
            return;
        }
        if (precioBase < 0) {
            System.out.println("El precio base no puede ser negativo.");
            return;
        }
        boolean activo = true;
         Servicios s = new Servicios(nombre, descripcion, categoria, precioBase, duracionEstimadaMinutos, activo);

        try (Connection con = ConexionDB.conectar()) {
            serviciosDAO.agregar(s, con);
            System.out.println("Servicio agregado correctamente.");
        } catch (SQLException ex) {
            System.out.println("Error al agregar el servicio: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    // ACTUALIZAR SERVICIO EXISTENTE
    public void actualizarServicio(int id, String nombre, String descripcion, String categoria, double precioBase, int duracionEstimadaMinutos) {
        if (id <= 0) {
            System.out.println("ID inválido.");
            return;
        }
        if (nombre == null || nombre.isBlank()) {
            System.out.println("El nombre no puede estar vacío.");
            return;
        }
        if (precioBase < 0) {
            System.out.println("El precio base no puede ser negativo.");
            return;
        }

        Servicios s = new Servicios(id, nombre, descripcion, categoria, precioBase, duracionEstimadaMinutos, true);
        serviciosDAO.actualizar(s);
        System.out.println("Servicio actualizado correctamente.");
    }

    // ELIMINAR SERVICIO
    public void eliminarServicio(int id) {
        if (id <= 0) {
            System.out.println("ID inválido.");
            return;
        }

        Servicios s = new Servicios(id, "", "", "", 0.0, 0, true);
        serviciosDAO.eliminar(s);
        System.out.println("Servicio eliminado correctamente.");
    }

    // LISTAR SERVICIOS
    public List<Servicios> listarServicios() {
        return serviciosDAO.listar();
    }
}
