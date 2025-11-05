/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import dao.JornadaVacunacionDAO;
import model.JornadasVacunaciones;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
/**
 *
 * @author ELKIN
 */
public class JornadaVacunacionController {
    
    private final JornadaVacunacionDAO dao = new JornadaVacunacionDAO();

    // Agregar  
    public void agregar(String nombre, Date fecha, Time horaInicio, Time horaFin,
                        String ubicacion, String descripcion, int capacidadMaxima,
                        JornadasVacunaciones.Estado estado) {

        if (nombre == null || nombre.isBlank()) {
            System.out.println("Error: El nombre de la jornada es obligatorio.");
            return;
        }

        if (fecha == null) {
            System.out.println("Error: La fecha de la jornada es obligatoria.");
            return;
        }

        if (capacidadMaxima < 0) {
            System.out.println("Error: La capacidad máxima no puede ser negativa.");
            return;
        }

        if (ubicacion != null && ubicacion.isBlank()) ubicacion = null;
        if (descripcion != null && descripcion.isBlank()) descripcion = null;

        JornadasVacunaciones j = new JornadasVacunaciones(nombre, fecha, horaInicio, horaFin,
                ubicacion, descripcion, capacidadMaxima, estado);

        dao.agregar(j);
    }

    // Actualizar  
    public void actualizar(int id, String nombre, Date fecha, Time horaInicio, Time horaFin,
                           String ubicacion, String descripcion, int capacidadMaxima,
                           JornadasVacunaciones.Estado estado) {

        if (id <= 0) {
            System.out.println("Error: ID inválido.");
            return;
        }

        // Validar ID   
        if (!dao.existePorId(id)) {
            System.out.println("Error: No se encontró una jornada con ID = " + id);
            return;
        }

        if (nombre == null || nombre.isBlank()) {
            System.out.println("Error: El nombre de la jornada es obligatorio.");
            return;
        }

        if (fecha == null) {
            System.out.println("Error: La fecha de la jornada es obligatoria.");
            return;
        }

        if (capacidadMaxima < 0) {
            System.out.println("Error: La capacidad máxima no puede ser negativa.");
            return;
        }

        if (ubicacion != null && ubicacion.isBlank()) ubicacion = null;
        if (descripcion != null && descripcion.isBlank()) descripcion = null;

        JornadasVacunaciones j = new JornadasVacunaciones(id, nombre, fecha, horaInicio, horaFin,
                ubicacion, descripcion, capacidadMaxima, estado);

        dao.actualizar(j);
    }

    // Eliminar 
    public void eliminar(int id) {
        if (id <= 0) {
            System.out.println("Error: ID inválido.");
            return;
        }

        if (!dao.existePorId(id)) {
            System.out.println("Error: No se encontró una jornada con ID = " + id);
            return;
        }

        dao.eliminar(id);
    }

    // Listar
    public List<JornadasVacunaciones> listar() {
        return dao.listar();
    }

    // Método para que la View valide existencia del ID
    public boolean existeId(int id) {
        return dao.existePorId(id);
    }
}

