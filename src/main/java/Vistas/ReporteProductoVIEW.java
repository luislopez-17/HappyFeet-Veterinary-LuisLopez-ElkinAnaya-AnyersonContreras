/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vistas;
import Controller.ReporteProductoController;
import java.sql.Date;
import java.util.Scanner;
/**
 *
 * @author usuario
 */
public class ReporteProductoVIEW {
    private Scanner sc = new Scanner(System.in);
    private ReporteProductoController controller = new ReporteProductoController();

    public void mostrarMenu() {
        System.out.println("==== REPORTE DE PRODUCTOS MÁS VENDIDOS ====");
        try {
            System.out.print("Ingrese fecha de inicio (YYYY-MM-DD): ");
            String fi = sc.nextLine();
            System.out.print("Ingrese fecha de fin (YYYY-MM-DD): ");
            String ff = sc.nextLine();

            Date fechaInicio = Date.valueOf(fi);
            Date fechaFin = Date.valueOf(ff);

            controller.generarReporte(fechaInicio, fechaFin);

        } catch (IllegalArgumentException e) {
            System.err.println("Formato de fecha inválido. Use YYYY-MM-DD.");
        }
    }
}
