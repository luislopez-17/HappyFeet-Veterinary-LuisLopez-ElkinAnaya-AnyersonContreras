/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import dao.AdopcionDAO;
import java.io.IOException;
import java.sql.Connection;
import model.Adopcion;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import utils.ConexionDB;
/**
 *
 * @author ELKIN
 */
public class AdopcionController {
     private final AdopcionDAO dao = new AdopcionDAO();

    // Agregar adopcion
    public void agregar(int mascotaId, int duenoId, Date fechaAdopcion,
                        String contratoTexto, String condicionesEspeciales,
                        boolean seguimientoRequerido, Date fechaPrimerSeguimiento) {

        if (mascotaId <= 0 || duenoId <= 0) {
            System.out.println("Error: El ID de la mascota o del dueño no puede ser menor o igual a 0.");
            return;
        }

        if (fechaAdopcion == null) {
            System.out.println("Error: La fecha de adopción es obligatoria.");
            return;
        }

        if (contratoTexto != null && contratoTexto.isBlank()) contratoTexto = null;
        if (condicionesEspeciales != null && condicionesEspeciales.isBlank()) condicionesEspeciales = null;

        Adopcion a = new Adopcion(mascotaId, duenoId, fechaAdopcion, contratoTexto, condicionesEspeciales, seguimientoRequerido, fechaPrimerSeguimiento);
        dao.agregar(a);
    }

    // Modificar adopcion
    public void modificar(int id, int mascotaId, int duenoId, Date fechaAdopcion,
                          String contratoTexto, String condicionesEspeciales,
                          boolean seguimientoRequerido, Date fechaPrimerSeguimiento) {

        if (id <= 0) {
            System.out.println("Error: ID de adopción inválido.");
            return;
        }

        if (mascotaId <= 0 || duenoId <= 0) {
            System.out.println("Error: ID de mascota o dueño inválido.");
            return;
        }

        if (fechaAdopcion == null) {
            System.out.println("Error: La fecha de adopción es obligatoria.");
            return;
        }

        if (contratoTexto != null && contratoTexto.isBlank()) contratoTexto = null;
        if (condicionesEspeciales != null && condicionesEspeciales.isBlank()) condicionesEspeciales = null;

        Adopcion a = new Adopcion(id, mascotaId, duenoId, fechaAdopcion, contratoTexto, condicionesEspeciales, seguimientoRequerido, fechaPrimerSeguimiento);
        dao.actualizar(a);
    }

    // Eliminar adopcion
    public void eliminar(int id) {
        if (id <= 0) {
            System.out.println("Error: ID inválido.");
            return;
        }
        dao.eliminar(id);
    }

    // Listar todas las adopciones
    public List<Adopcion> listar() {
        return dao.listar();
    }
    
    
    //EXAMEN
    public String generarYGuardarContrato(int adopcionId) {
        try (Connection con = ConexionDB.conectar()) {
            AdopcionDAO dao = new AdopcionDAO();

            // 1️⃣ Validar si existe y no tiene contrato
            if (!dao.existeAdopcionSinContrato(con, adopcionId)) {
                return "Error: La adopción no existe o ya tiene un contrato generado.";
            }

            // 2️⃣ Obtener los datos completos
            Adopcion adopcion = dao.obtenerDatosCompletos(con, adopcionId);
            if (adopcion == null) {
                return "Error: No se encontraron datos para la adopción ID " + adopcionId;
            }

            // 3️⃣ Generar contrato
            String contratoTexto = dao.generarContratoTexto(adopcion);

            // 4️⃣ Guardar archivo + actualizar base de datos
            dao.guardarContrato(con, adopcionId, contratoTexto);

            return "Contrato para la adopción ID " + adopcionId +
                   " generado y guardado exitosamente en 'contrato_adopcion_" +
                   adopcionId + ".txt' y actualizado en la base de datos.";

        } catch (SQLException e) {
            return " Error de base de datos: " + e.getMessage();
        } catch (IOException e) { // Problema de una operación de entrada/salida
            return " Error al crear el archivo: " + e.getMessage();
        }
    }

}
