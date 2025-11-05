/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import dao.TransaccionesPuntosDAO;
import model.TransaccionesPuntos;
import model.TransaccionesPuntos.Tipo;

import java.sql.Timestamp;
import java.util.List;
/**
 *
 * @author ELKIN
 */
public class TransaccionesPuntosController {
    
    private final TransaccionesPuntosDAO dao = new TransaccionesPuntosDAO();

    // Agregar transacción con validaciones
    public void agregar(int clubMascotasId, int facturaId, int puntos, Tipo tipo, Timestamp fecha,
                        String descripcion, int saldoAnterior, int saldoNuevo) {

        if (clubMascotasId <= 0) {
            System.out.println("Error: El club de mascotas es obligatorio.");
            return;
        }

        if (puntos < 0) {
            System.out.println("Error: Los puntos no pueden ser negativos.");
            return;
        }

        if (tipo == null) {
            System.out.println("Error: Debe seleccionar un tipo de transacción.");
            return;
        }

        if (fecha == null) {
            System.out.println("Error: La fecha de la transacción es obligatoria.");
            return;
        }

        TransaccionesPuntos t = new TransaccionesPuntos(clubMascotasId, facturaId, puntos, tipo, fecha, descripcion, saldoAnterior, saldoNuevo);
        dao.agregar(t);
    }

    // Actualizar transacción con validaciones
    public void actualizar(int id, int clubMascotasId, int facturaId, int puntos, Tipo tipo, Timestamp fecha,
                           String descripcion, int saldoAnterior, int saldoNuevo) {

        if (id <= 0) {
            System.out.println("Error: ID inválido.");
            return;
        }

        if (!dao.existePorId(id)) {
            System.out.println("Error: No se encontró la transacción con ID = " + id);
            return;
        }

        if (clubMascotasId <= 0) {
            System.out.println("Error: El club de mascotas es obligatorio.");
            return;
        }

        if (puntos < 0) {
            System.out.println("Error: Los puntos no pueden ser negativos.");
            return;
        }

        if (tipo == null) {
            System.out.println("Error: Debe seleccionar un tipo de transacción.");
            return;
        }

        if (fecha == null) {
            System.out.println("Error: La fecha de la transacción es obligatoria.");
            return;
        }

        TransaccionesPuntos t = new TransaccionesPuntos(id, clubMascotasId, facturaId, puntos, tipo, fecha, descripcion, saldoAnterior, saldoNuevo);
        dao.actualizar(t);
    }

    // Eliminar transacción
    public void eliminar(int id) {
        if (id <= 0) {
            System.out.println("Error: ID inválido.");
            return;
        }
        dao.eliminar(id);
    }

    // Listar todas las transacciones
    public List<TransaccionesPuntos> listar() {
        return dao.listar();
    }
}
