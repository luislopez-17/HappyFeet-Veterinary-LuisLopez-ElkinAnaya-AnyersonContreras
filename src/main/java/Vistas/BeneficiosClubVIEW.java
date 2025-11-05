/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vistas;
import Controller.BeneficiosClubController;
import model.BeneficiosClub;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author ELKIN
 */
public class BeneficiosClubVIEW {
    private static final Scanner sc = new Scanner(System.in);
    private static final BeneficiosClubController controller = new BeneficiosClubController();

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n--- Menú Beneficios Club ---");
            System.out.println("1. Agregar beneficio");
            System.out.println("2. Actualizar beneficio");
            System.out.println("3. Eliminar beneficio");
            System.out.println("4. Listar beneficios");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch(opcion) {
                case 1: agregar(); break;
                case 2: actualizar(); break;
                case 3: eliminar(); break;
                case 4: listar(); break;
                case 0: System.out.println("Saliendo..."); break;
                default: System.out.println("Opción inválida.");
            }

        } while(opcion != 0);
    }

    private void agregar() {
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Descripción: ");
        String descripcion = sc.nextLine();
        System.out.print("Nivel requerido: ");
        String nivel = sc.nextLine();
        System.out.print("Puntos necesarios: ");
        int puntos = sc.nextInt();
        sc.nextLine();
        System.out.println("Tipo de beneficio: 1.Descuento 2.Servicio Gratis 3.Producto Gratis 4.Puntos Extra");
        int tipoOpt = sc.nextInt();
        sc.nextLine();
        BeneficiosClub.TipoBeneficio tipo = switch(tipoOpt) {
            case 1 -> BeneficiosClub.TipoBeneficio.Descuento;
            case 2 -> BeneficiosClub.TipoBeneficio.Servicio_Gratis;
            case 3 -> BeneficiosClub.TipoBeneficio.Producto_Gratis;
            case 4 -> BeneficiosClub.TipoBeneficio.Puntos_Extra;
            default -> BeneficiosClub.TipoBeneficio.Descuento;
        };
        System.out.print("Valor beneficio: ");
        double valor = sc.nextDouble();
        sc.nextLine();
        System.out.print("Activo (true/false): ");
        boolean activo = sc.nextBoolean();
        sc.nextLine();

        controller.agregar(nombre, descripcion, nivel, puntos, tipo, valor, activo);
    }

    private void actualizar() {
        System.out.print("ID del beneficio a actualizar: ");
        int id = sc.nextInt();
        sc.nextLine();

        if (!controller.listar().stream().anyMatch(b -> b.getId() == id)) {
            System.out.println("Error: No se encontró beneficio con ID = " + id);
            return;
        }

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Descripción: ");
        String descripcion = sc.nextLine();
        System.out.print("Nivel requerido: ");
        String nivel = sc.nextLine();
        System.out.print("Puntos necesarios: ");
        int puntos = sc.nextInt();
        sc.nextLine();
        System.out.println("Tipo de beneficio: 1.Descuento 2.Servicio Gratis 3.Producto Gratis 4.Puntos Extra");
        int tipoOpt = sc.nextInt();
        sc.nextLine();
        BeneficiosClub.TipoBeneficio tipo = switch(tipoOpt) {
            case 1 -> BeneficiosClub.TipoBeneficio.Descuento;
            case 2 -> BeneficiosClub.TipoBeneficio.Servicio_Gratis;
            case 3 -> BeneficiosClub.TipoBeneficio.Producto_Gratis;
            case 4 -> BeneficiosClub.TipoBeneficio.Puntos_Extra;
            default -> BeneficiosClub.TipoBeneficio.Descuento;
        };
        System.out.print("Valor beneficio: ");
        double valor = sc.nextDouble();
        sc.nextLine();
        System.out.print("Activo (true/false): ");
        boolean activo = sc.nextBoolean();
        sc.nextLine();

        controller.actualizar(id, nombre, descripcion, nivel, puntos, tipo, valor, activo);
    }

    private void eliminar() {
        System.out.print("ID del beneficio a eliminar: ");
        int id = sc.nextInt();
        sc.nextLine();
        controller.eliminar(id);
    }

    private void listar() {
        List<BeneficiosClub> lista = controller.listar();
        if (lista.isEmpty()) System.out.println("No hay beneficios registrados.");
        else lista.forEach(System.out::println);
    }

}
