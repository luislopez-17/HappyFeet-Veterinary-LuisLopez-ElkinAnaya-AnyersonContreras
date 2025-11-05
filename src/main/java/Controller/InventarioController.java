/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.InventarioDAO;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import model.Inventario;

public class InventarioController {

    private InventarioDAO dao = new InventarioDAO();

    // Validación de fecha de vencimiento
    private boolean validarFechaVencimiento(Date fecha) {
        if (fecha == null) {
            return true; // si no hay fecha, se acepta (algunos productos pueden no vencer)
        }
        LocalDate hoy = LocalDate.now();
        return fecha.toLocalDate().isAfter(hoy);
    }

    public void agregarInventario(String nombreProducto, int productoTipoId, String descripcion,
                                  String fabricante, int proveedorId, String lote, int cantidadStock,
                                  int stockMinimo, String unidadMedida, Date fechaVencimiento,
                                  double precioCompra, double precioVenta, boolean requiereReceta) {

        // Validación de campos obligatorios
        if (nombreProducto == null || nombreProducto.isEmpty()) {
            System.out.println("El nombre del producto es obligatorio.");
            return;
        }
        if (productoTipoId <= 0) {
            System.out.println("El ID del tipo de producto es obligatorio y debe ser válido.");
            return;
        }
        if (precioVenta <= 0) {
            System.out.println("El precio de venta debe ser mayor que 0.");
            return;
        }

        // Validar fecha de vencimiento
        if (!validarFechaVencimiento(fechaVencimiento)) {
            System.out.println("La fecha de vencimiento debe ser posterior a la fecha actual.");
            return;
        }

        Timestamp fechaRegistro = Timestamp.valueOf(LocalDateTime.now());
        Inventario inv = new Inventario(nombreProducto, productoTipoId, descripcion, fabricante,
                proveedorId, lote, cantidadStock, stockMinimo, unidadMedida,
                fechaVencimiento, precioCompra, precioVenta, requiereReceta,
                true, fechaRegistro);

        dao.agregar(inv);
    }

    public void actualizarInventario(int id, String nombreProducto, int productoTipoId, String descripcion,
                                     String fabricante, int proveedorId, String lote, int cantidadStock,
                                     int stockMinimo, String unidadMedida, Date fechaVencimiento,
                                     double precioCompra, double precioVenta, boolean requiereReceta,
                                     boolean activo) {

        if (id <= 0) {
            System.out.println("El ID del producto a actualizar no es válido.");
            return;
        }

        if (nombreProducto == null || nombreProducto.isEmpty()) {
            System.out.println("El nombre del producto es obligatorio.");
            return;
        }

        if (!validarFechaVencimiento(fechaVencimiento)) {
            System.out.println("La fecha de vencimiento debe ser posterior a la fecha actual.");
            return;
        }

        Timestamp fechaRegistro = Timestamp.valueOf(LocalDateTime.now());
        Inventario inv = new Inventario(id, nombreProducto, productoTipoId, descripcion, fabricante,
                proveedorId, lote, cantidadStock, stockMinimo, unidadMedida,
                fechaVencimiento, precioCompra, precioVenta, requiereReceta,
                activo, fechaRegistro);

        dao.actualizar(inv);
    }

    public void eliminarInventario(int id) {
        if (id <= 0) {
            System.out.println("El ID del producto no es válido para eliminar.");
            return;
        }
        Inventario inv = new Inventario(id, null, 0, null, null, 0, null, 0, 0, null, null, 0, 0, false, false, null);
        dao.eliminar(inv);
    }

    public void listarInventario() {
        List<Inventario> lista = dao.listar();
        if (lista.isEmpty()) {
            System.out.println("No hay productos registrados en el inventario.");
        } else {
            for (Inventario inv : lista) {
                System.out.println(inv);
            }
        }
    }
}

