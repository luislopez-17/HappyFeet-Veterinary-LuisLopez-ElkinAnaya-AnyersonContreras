/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import dao.MovimientosInventarioDAO;
import model.MovimientoInventario;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ELKIN
 */
public class MovimientosInventarioController {
     private final MovimientosInventarioDAO movimientosDAO;

    public MovimientosInventarioController() {
        this.movimientosDAO = new MovimientosInventarioDAO();
    }

    // Agregar nuevo movimiento
    public String agregar(int productoId, String tipoMovimiento, int cantidad,
                          int stockAnterior, int stockNuevo, String motivo,
                          Integer refConsultaId, Integer refProcedimientoId, String usuario) {

        if (productoId <= 0 || tipoMovimiento.isBlank() || cantidad <= 0
                || stockAnterior < 0 || stockNuevo < 0 || usuario.isBlank()) {
            return "Error: todos los campos obligatorios deben tener valores válidos.";
        }

        Timestamp fechaMovimiento = new Timestamp(new Date().getTime());

        MovimientoInventario mi = new MovimientoInventario(
                productoId, tipoMovimiento, cantidad, stockAnterior, stockNuevo,
                motivo, refConsultaId, refProcedimientoId, usuario, fechaMovimiento
        );

        try {
            movimientosDAO.agregar(mi);
            return "Movimiento agregado correctamente.";
        } catch (Exception e) {
            return "Error al agregar movimiento: " + e.getMessage();
        }
    }

    // Listar todos los movimientos
    public List<MovimientoInventario> listar() {
        try {
            return movimientosDAO.listar();
        } catch (Exception e) {
            System.out.println("Error al listar movimientos: " + e.getMessage());
            return List.of();
        }
    }

    // Actualizar movimiento existente
    public String actualizar(int id, int productoId, String tipoMovimiento, int cantidad,
                             int stockAnterior, int stockNuevo, String motivo,
                             Integer refConsultaId, Integer refProcedimientoId, String usuario) {

        if (id <= 0) return "Error: ID inválido.";
        if (productoId <= 0 || tipoMovimiento.isBlank() || cantidad <= 0
                || stockAnterior < 0 || stockNuevo < 0 || usuario.isBlank()) {
            return "Error: todos los campos obligatorios deben tener valores válidos.";
        }

        Timestamp fechaMovimiento = new Timestamp(new Date().getTime());

        MovimientoInventario mi = new MovimientoInventario(
                id, productoId, tipoMovimiento, cantidad, stockAnterior, stockNuevo,
                motivo, refConsultaId, refProcedimientoId, usuario, fechaMovimiento
        );

        try {
            boolean actualizado = movimientosDAO.actualizar(mi);
            return actualizado ? "Movimiento actualizado correctamente." : "No se encontró el movimiento con ID " + id;
        } catch (Exception e) {
            return "Error al actualizar movimiento: " + e.getMessage();
        }
    }

    // Eliminar movimiento
    public String eliminar(int id) {
        if (id <= 0) return "Error: ID inválido.";

        try {
            boolean eliminado = movimientosDAO.eliminar(id);
            return eliminado ? "Movimiento eliminado correctamente." : "No se encontró el movimiento con ID " + id;
        } catch (Exception e) {
            return "Error al eliminar movimiento: " + e.getMessage();
        }
    }
}

