/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import dao.PreescripcionDAO;
import model.Preescripcion;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author ELKIN
 */
public class PreescripcionController {

    private final PreescripcionDAO dao = new PreescripcionDAO();

    // ======================================================
    // MÉTODO: AGREGAR
    // ======================================================
    public void agregar(int consultaId, int procedimientoId, int productoId, int cantidad,
                        String dosis, String frecuencia, int duracionDias, String instrucciones) {

        // Validaciones de campos obligatorios
        if (productoId <= 0) {
            System.out.println("Error: el ID del producto es obligatorio.");
            return;
        }

        if (cantidad <= 0) {
            System.out.println("Error: la cantidad debe ser mayor que cero.");
            return;
        }

        // La tabla exige al menos una referencia: consulta_id o procedimiento_id
        if (consultaId <= 0 && procedimientoId <= 0) {
            System.out.println("Error: debe asignar una consulta o un procedimiento a la prescripción.");
            return;
        }

        // Crear objeto modelo
        Preescripcion p = new Preescripcion(
                0,
                (consultaId > 0 ? consultaId : null),
                (procedimientoId > 0 ? procedimientoId : null),
                productoId,
                cantidad,
                dosis,
                frecuencia,
                duracionDias,
                instrucciones,
                new Timestamp(System.currentTimeMillis())
        );

        dao.agregar(p);
        System.out.println("Prescripción agregada correctamente.");
    }
    
    public void agregar(Preescripcion p){
        dao.agregar(p);
    }
    

    // ======================================================
    // MÉTODO: ACTUALIZAR
    // ======================================================
    public void actualizar(int id, int consultaId, int procedimientoId, int productoId, int cantidad,
                           String dosis, String frecuencia, int duracionDias, String instrucciones) {

        if (id <= 0) {
            System.out.println("Error: ID inválido para actualizar.");
            return;
        }

        Preescripcion existente = dao.buscarPorId(id);
        if (existente == null) {
            System.out.println("No se encontró una prescripción con ese ID.");
            return;
        }

        if (productoId <= 0) {
            System.out.println("Error: el ID del producto es obligatorio.");
            return;
        }

        if (cantidad <= 0) {
            System.out.println("Error: la cantidad debe ser mayor que cero.");
            return;
        }

        if (consultaId <= 0 && procedimientoId <= 0) {
            System.out.println("Error: debe asignar una consulta o un procedimiento a la prescripción.");
            return;
        }

        Preescripcion p = new Preescripcion(
                id,
                (consultaId > 0 ? consultaId : null),
                (procedimientoId > 0 ? procedimientoId : null),
                productoId,
                cantidad,
                dosis,
                frecuencia,
                duracionDias,
                instrucciones,
                new Timestamp(System.currentTimeMillis())
        );

        dao.actualizar(p);
        System.out.println("Prescripción actualizada correctamente.");
    }

    // ======================================================
    // MÉTODO: ELIMINAR
    // ======================================================
    public void eliminar(int id) {
        if (id <= 0) {
            System.out.println("Error: ID inválido.");
            return;
        }

        Preescripcion p = dao.buscarPorId(id);
        if (p == null) {
            System.out.println("️ No existe una prescripción con ese ID.");
            return;
        }

        boolean eliminado = dao.eliminar(id);
        if (eliminado) {
            System.out.println("Prescripción eliminada correctamente.");
        } else {
            System.out.println("No se pudo eliminar la prescripción.");
        }
    }

    // ======================================================
    // MÉTODO: LISTAR
    // ======================================================
    public void listar() {
        List<Preescripcion> lista = dao.listar();
        if (lista == null || lista.isEmpty()) {
            System.out.println("No hay prescripciones registradas.");
            return;
        }

        System.out.println("\n=== LISTADO DE PRESCRIPCIONES ===");
        for (Preescripcion p : lista) {
            System.out.println(p);
        }
    }

    // ======================================================
    // MÉTODO: BUSCAR POR ID
    // ======================================================
    public Preescripcion buscarPorId(int id) {
        if (id <= 0) {
            System.out.println("Error: ID inválido.");
            return null;
        }

        Preescripcion p = dao.buscarPorId(id);
        if (p == null) {
            System.out.println("No existe una prescripción con ese ID.");
        }
        return p;
    }
}

