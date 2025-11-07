/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import dao.ReporteProductoDAO;
import java.sql.Date;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import model.ReporteProducto;
/**
 *
 * @author usuario
 */
public class ReporteProductoController {
    private ReporteProductoDAO dao = new ReporteProductoDAO();

    public void generarReporte(Date fechaInicio, Date fechaFin) {
        List<ReporteProducto> lista = dao.obtenerProductosMasVendidos(fechaInicio, fechaFin);

        if (lista.isEmpty()) {
            System.out.println("No se encontraron ventas en el rango indicado.");
            return;
        }

        NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("REPORTE DE PRODUCTOS MÁS VENDIDOS");
        System.out.println("Período: " + fechaInicio + " - " + fechaFin);
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.printf("%-40s | %-15s | %-20s%n", "Producto", "Cantidad Vendida", "Ingresos Totales");
        System.out.println("---------------------------------------------------------------------");

        for (ReporteProducto rp : lista) {
            System.out.printf("%-40s | %-15d | %-20s%n",
                    rp.getNombreProducto(),
                    rp.getCantidadVendida(),
                    formatoMoneda.format(rp.getIngresosTotales()));
        }
    }
}
