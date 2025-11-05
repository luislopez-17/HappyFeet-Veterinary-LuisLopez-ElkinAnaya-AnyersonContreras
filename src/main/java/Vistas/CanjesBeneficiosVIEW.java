/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vistas;
import Controller.CanjesBeneficiosController;
import model.CanjesBeneficios;

import java.util.Date;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author ELKIN
 */
public class CanjesBeneficiosVIEW {
    private final CanjesBeneficiosController controller = new CanjesBeneficiosController();
    private static final Scanner sc = new Scanner(System.in);

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("=== Canjes Beneficios ===");
            System.out.println("1. Agregar");
            System.out.println("2. Actualizar");
            System.out.println("3. Eliminar");
            System.out.println("4. Listar");
            System.out.println("0. Salir");
            System.out.print("Ingrese opción: ");
            opcion = sc.nextInt(); sc.nextLine();

            switch(opcion) {
                case 1: agregarCanje(); break;
                case 2: actualizarCanje(); break;
                case 3: eliminarCanje(); break;
                case 4: listarCanjes(); break;
                case 0: System.out.println("Saliendo..."); break;
                default: System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    private void agregarCanje() {
        try {
            System.out.print("ID ClubMascotas: "); int clubId = sc.nextInt(); sc.nextLine();
            System.out.print("ID Beneficio: "); int beneficioId = sc.nextInt(); sc.nextLine();
            System.out.print("Fecha canje (yyyy-mm-dd hh:mm:ss): "); Date fechaCanje = java.sql.Timestamp.valueOf(sc.nextLine());
            System.out.print("Puntos canjeados: "); int puntos = sc.nextInt(); sc.nextLine();
            System.out.print("Estado (Pendiente, Aplicado, Expirado): "); CanjesBeneficios.Estado estado = CanjesBeneficios.Estado.valueOf(sc.nextLine());
            System.out.print("Fecha expiración (yyyy-mm-dd) o ENTER: "); String fExp = sc.nextLine(); Date fechaExp = fExp.isBlank() ? null : java.sql.Date.valueOf(fExp);
            System.out.print("Factura ID (0 si no aplica): "); int facturaId = sc.nextInt(); sc.nextLine();

            controller.agregar(clubId, beneficioId, fechaCanje, puntos, estado, fechaExp, facturaId);
        } catch (Exception e) { System.out.println("Error en entrada, intente de nuevo."); }
    }

    private void actualizarCanje() {
        try {
            System.out.print("ID canje a actualizar: "); int id = sc.nextInt(); sc.nextLine();
            System.out.print("ID ClubMascotas: "); int clubId = sc.nextInt(); sc.nextLine();
            System.out.print("ID Beneficio: "); int beneficioId = sc.nextInt(); sc.nextLine();
            System.out.print("Fecha canje (yyyy-mm-dd hh:mm:ss): "); Date fechaCanje = java.sql.Timestamp.valueOf(sc.nextLine());
            System.out.print("Puntos canjeados: "); int puntos = sc.nextInt(); sc.nextLine();
            System.out.print("Estado (Pendiente, Aplicado, Expirado): "); CanjesBeneficios.Estado estado = CanjesBeneficios.Estado.valueOf(sc.nextLine());
            System.out.print("Fecha expiración (yyyy-mm-dd) o ENTER: "); String fExp = sc.nextLine(); Date fechaExp = fExp.isBlank() ? null : java.sql.Date.valueOf(fExp);
            System.out.print("Factura ID (0 si no aplica): "); int facturaId = sc.nextInt(); sc.nextLine();

            controller.actualizar(id, clubId, beneficioId, fechaCanje, puntos, estado, fechaExp, facturaId);
        } catch (Exception e) { System.out.println("Error en entrada, intente de nuevo."); }
    }

    private void eliminarCanje() {
        try {
            System.out.print("ID canje a eliminar: "); int id = sc.nextInt(); sc.nextLine();
            controller.eliminar(id);
        } catch (Exception e) { System.out.println("Error en entrada."); }
    }

    private void listarCanjes() {
        List<CanjesBeneficios> lista = controller.listar();
        for (CanjesBeneficios c : lista) System.out.println(c);
    }
}
