/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import dao.AdopcionDAO;
import model.Adopcion;
import java.sql.Date;
import java.util.List;
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

}
