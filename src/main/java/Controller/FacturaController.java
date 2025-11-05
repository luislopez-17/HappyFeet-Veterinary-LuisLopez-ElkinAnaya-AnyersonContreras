package Controller;

import dao.FacturaDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import model.Factura;
import utils.ConexionDB;

public class FacturaController {

    private FacturaDAO facturaDAO;

    // CONSTRUCTOR
    public FacturaController() {
        this.facturaDAO = new FacturaDAO();
    }

    // AGREGAR NUEVA FACTURA (total se calcula automáticamente)
    public void agregarFactura(int duenoId, String numeroFactura, Date fechaEmision,
                               double subtotal, double impuesto, double descuento,
                               String metodoPago, String estado, String observaciones) {

        // Validaciones básicas
        if (duenoId <= 0) {
            System.out.println("Error: el ID del dueño no puede ser menor o igual a cero.");
            return;
        }
        if (numeroFactura == null || numeroFactura.isBlank()) {
            System.out.println("Error: el número de factura no puede estar vacío.");
            return;
        }
        if (subtotal < 0) {
            System.out.println("Error: el subtotal no puede ser negativo.");
            return;
        }
        if (impuesto < 0) {
            System.out.println("Error: el impuesto no puede ser negativo.");
            return;
        }
        if (descuento < 0) {
            System.out.println("Error: el descuento no puede ser negativo.");
            return;
        }

        // 💡 Cálculo automático del total
        double totalCalculado = subtotal + impuesto - descuento;
        if (totalCalculado < 0) totalCalculado = 0;
        System.out.println("Total calculado automáticamente: " + totalCalculado);

        // Validar que método de pago y estado sean válidos
        if (metodoPago != null && !metodoPago.isBlank()) {
            String[] metodosValidos = {"Efectivo", "Tarjeta", "Transferencia", "Mixto"};
            boolean metodoValido = false;
            for (String m : metodosValidos) {
                if (m.equalsIgnoreCase(metodoPago.trim())) {
                    metodoPago = m; // normalizar texto
                    metodoValido = true;
                    break;
                }
            }
            if (!metodoValido) {
                System.out.println("Error: método de pago inválido. Debe ser Efectivo, Tarjeta, Transferencia o Mixto.");
                return;
            }
        }

        if (estado != null && !estado.isBlank()) {
            String[] estadosValidos = {"Pendiente", "Pagada", "Anulada"};
            boolean estadoValido = false;
            for (String e : estadosValidos) {
                if (e.equalsIgnoreCase(estado.trim())) {
                    estado = e; // normalizar texto
                    estadoValido = true;
                    break;
                }
            }
            if (!estadoValido) {
                System.out.println("Error: estado inválido. Debe ser Pendiente, Pagada o Anulada.");
                return;
            }
        }

        // Crear objeto Factura con total calculado
        Factura f = new Factura(duenoId, numeroFactura, fechaEmision, subtotal, impuesto, descuento,
                                totalCalculado, metodoPago, estado, observaciones);

        try (Connection con = ConexionDB.conectar()) {
            facturaDAO.agregar(f);
            System.out.println("Factura agregada correctamente.");
        } catch (SQLException ex) {
            System.out.println("Error al agregar la factura: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    // ACTUALIZAR FACTURA EXISTENTE (también calcula total automáticamente)
    public void actualizarFactura(int id, int duenoId, String numeroFactura, Date fechaEmision,
                                  double subtotal, double impuesto, double descuento,
                                  String metodoPago, String estado, String observaciones) {

        if (id <= 0) {
            System.out.println("Error: el ID de la factura es inválido.");
            return;
        }
        if (numeroFactura == null || numeroFactura.isBlank()) {
            System.out.println("Error: el número de factura no puede estar vacío.");
            return;
        }

        // 💡 Recalcular total antes de actualizar
        double totalCalculado = subtotal + impuesto - descuento;
        if (totalCalculado < 0) totalCalculado = 0;
        System.out.println("Total recalculado automáticamente: " + totalCalculado);

        Factura f = new Factura(id, duenoId, numeroFactura, fechaEmision, subtotal, impuesto,
                                descuento, totalCalculado, metodoPago, estado, observaciones);

        try {
            facturaDAO.actualizar(f);
            System.out.println("Factura actualizada correctamente.");
        } catch (SQLException ex) {
            System.out.println("Error al actualizar la factura: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    // ELIMINAR FACTURA
    public void eliminarFactura(int id) {
        if (id <= 0) {
            System.out.println("Error: el ID de la factura es inválido.");
            return;
        }

        Factura f = new Factura(id, 0, "", new Date(), 0, 0, 0, 0, "", "", "");

        try {
            facturaDAO.eliminar(f);
            System.out.println("Factura eliminada correctamente.");
        } catch (SQLException ex) {
            System.out.println("Error al eliminar la factura: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    // LISTAR FACTURAS
    public List<Factura> listarFacturas() {
        return facturaDAO.listar();
    }
}
