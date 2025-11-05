/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import dao.BeneficiosClubDAO;
import model.BeneficiosClub;
import java.util.List;
/**
 *
 * @author ELKIN
 */
public class BeneficiosClubController {
     private final BeneficiosClubDAO dao = new BeneficiosClubDAO();

    // Agregar
    public void agregar(String nombre, String descripcion, String nivel, int puntos, BeneficiosClub.TipoBeneficio tipo, double valor, boolean activo) {
        if (nombre == null || nombre.isBlank()) {
            System.out.println("Error: El nombre es obligatorio.");
            return;
        }
        if (nivel == null || nivel.isBlank()) {
            System.out.println("Error: El nivel requerido es obligatorio.");
            return;
        }
        if (puntos <= 0) {
            System.out.println("Error: Los puntos necesarios deben ser mayores a 0.");
            return;
        }

        BeneficiosClub b = new BeneficiosClub(nombre, descripcion, nivel, puntos, tipo, valor, activo);
        dao.agregar(b);
    }

    // Actualizar
    public void actualizar(int id, String nombre, String descripcion, String nivel, int puntos, BeneficiosClub.TipoBeneficio tipo, double valor, boolean activo) {
        if (id <= 0) {
            System.out.println("Error: ID inválido.");
            return;
        }

        if (!dao.existePorId(id)) {
            System.out.println("Error: No se encontró beneficio con ID = " + id);
            return;
        }

        if (nombre == null || nombre.isBlank()) {
            System.out.println("Error: El nombre es obligatorio.");
            return;
        }
        if (nivel == null || nivel.isBlank()) {
            System.out.println("Error: El nivel requerido es obligatorio.");
            return;
        }
        if (puntos <= 0) {
            System.out.println("Error: Los puntos necesarios deben ser mayores a 0.");
            return;
        }

        BeneficiosClub b = new BeneficiosClub(id, nombre, descripcion, nivel, puntos, tipo, valor, activo);
        dao.actualizar(b);
    }

    // Eliminar
    public void eliminar(int id) {
        if (id <= 0) {
            System.out.println("Error: ID inválido.");
            return;
        }
        dao.eliminar(id);
    }

    // Listar
    public List<BeneficiosClub> listar() {
        return dao.listar();
    }
}
