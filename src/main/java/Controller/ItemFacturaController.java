/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import dao.ItemFacturaDAO;
import java.util.List;
import model.ItemFactura;

/**
 *
 * @author ELKIN
 */
public class ItemFacturaController {

    private ItemFacturaDAO itemFacturaDAO;

    // Constructor
    public ItemFacturaController() {
        this.itemFacturaDAO = new ItemFacturaDAO();
    }

    // =======================================================
    // 🔹 AGREGAR ITEM FACTURA
    // =======================================================
    public void agregarItemFactura(int facturaId, String tipoItem, int productoId, int servicioId,
                                   String servicioDescripcion, int cantidad, double precioUnitario) {

        if (facturaId <= 0) {
            System.out.println("El ID de la factura no puede ser menor o igual a 0.");
            return;
        }

        if (tipoItem == null || tipoItem.isBlank()) {
            System.out.println("El tipo de ítem no puede estar vacío.");
            return;
        }

        if (cantidad <= 0) {
            System.out.println("La cantidad debe ser mayor a 0.");
            return;
        }

        if (precioUnitario < 0) {
            System.out.println("El precio unitario no puede ser negativo.");
            return;
        }

        double subtotal = cantidad * precioUnitario;

        ItemFactura i = new ItemFactura(
                facturaId, tipoItem, productoId, servicioId,
                servicioDescripcion, cantidad, precioUnitario, subtotal
        );

        itemFacturaDAO.agregar(i);
    }

    // =======================================================
    // 🔹 ACTUALIZAR ITEM FACTURA
    // =======================================================
    public void actualizarItemFactura(int id, int facturaId, String tipoItem, int productoId, int servicioId,
                                      String servicioDescripcion, int cantidad, double precioUnitario) {

        if (id <= 0) {
            System.out.println("El ID del ítem no puede ser menor o igual a 0.");
            return;
        }

        if (facturaId <= 0) {
            System.out.println("El ID de la factura no puede ser menor o igual a 0.");
            return;
        }

        if (cantidad <= 0) {
            System.out.println("La cantidad debe ser mayor a 0.");
            return;
        }

        if (precioUnitario < 0) {
            System.out.println("El precio unitario no puede ser negativo.");
            return;
        }

        double subtotal = cantidad * precioUnitario;

        ItemFactura i = new ItemFactura(
                id, facturaId, tipoItem, productoId, servicioId,
                servicioDescripcion, cantidad, precioUnitario, subtotal
        );

        itemFacturaDAO.actualizar(i);
    }

    // =======================================================
    // 🔹 ELIMINAR ITEM FACTURA
    // =======================================================
    public void eliminarItemFactura(int id) {
        if (id <= 0) {
            System.out.println("El ID del ítem no puede ser menor o igual a 0.");
            return;
        }

        ItemFactura i = new ItemFactura(id, 0, "", 0, 0, "", 0, 0, 0);
        itemFacturaDAO.eliminar(i);
    }

    // =======================================================
    // 🔹 LISTAR TODOS LOS ÍTEMS
    // =======================================================
    public List<ItemFactura> listarItemsFactura() {
        return itemFacturaDAO.listar();
    }
}