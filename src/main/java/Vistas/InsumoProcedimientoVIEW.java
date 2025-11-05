/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vistas;

import Controller.IsumoProcedimientoController;
import java.util.List;
import java.util.Scanner;
import model.InsumosProcedimientos;

/**
 *
 * @author usuario
 */
public class InsumoProcedimientoVIEW {
    private Scanner sc;
    private IsumoProcedimientoController controller;

    public InsumoProcedimientoVIEW() {
        this.sc = new Scanner(System.in);
        this.controller = new IsumoProcedimientoController();
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n===== MENÚ INSUMOS DE PROCEDIMIENTOS =====");
            System.out.println("1. Agregar insumo");
            System.out.println("2. Actualizar insumo");
            System.out.println("3. Eliminar insumo");
            System.out.println("4. Listar insumos");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = leerEntero();

            switch (opcion) {
                case 1 -> agregar();
                case 2 -> actualizar();
                case 3 -> eliminar();
                case 4 -> listar();
                case 0 -> System.out.println("Saliendo del módulo...");
                default -> System.out.println("Opción inválida, intente nuevamente.");
            }
        } while (opcion != 0);
    }

    private void agregar() {
        System.out.println("\n--- AGREGAR INSUMO DE PROCEDIMIENTO ---");

        System.out.print("ID del procedimiento: ");
        int procedimientoId = leerEntero();

        System.out.print("ID del producto: ");
        int productoId = leerEntero();

        System.out.print("Cantidad usada: ");
        int cantidadUsada = leerEntero();

        System.out.print("Observaciones (opcional): ");
        String observaciones = sc.nextLine();

        controller.agregar(procedimientoId, productoId, cantidadUsada, observaciones);
    }

    private void actualizar() {
        System.out.println("\n--- ACTUALIZAR INSUMO DE PROCEDIMIENTO ---");

        System.out.print("ID del insumo a actualizar: ");
        int id = leerEntero();

        System.out.print("Nuevo ID del procedimiento: ");
        int procedimientoId = leerEntero();

        System.out.print("Nuevo ID del producto: ");
        int productoId = leerEntero();

        System.out.print("Nueva cantidad usada: ");
        int cantidadUsada = leerEntero();

        System.out.print("Nuevas observaciones (opcional): ");
        String observaciones = sc.nextLine();

        controller.actualizar(id, procedimientoId, productoId, cantidadUsada, observaciones);
    }

    private void eliminar() {
        System.out.println("\n--- ELIMINAR INSUMO DE PROCEDIMIENTO ---");

        System.out.print("ID del insumo a eliminar: ");
        int id = leerEntero();

        controller.eliminar(id);
        System.out.println("Eliminación completada (si el registro existía).");
    }

    private void listar() {
        System.out.println("\n--- LISTADO DE INSUMOS DE PROCEDIMIENTOS ---");

        List<InsumosProcedimientos> lista = controller.listar();

        if (lista.isEmpty()) {
            System.out.println("No hay registros de insumos de procedimientos.");
        } else {
            lista.forEach(System.out::println);
        }
    }

    // Método auxiliar seguro para leer enteros
    private int leerEntero() {
        while (true) {
            try {
                int valor = Integer.parseInt(sc.nextLine());
                return valor;
            } catch (NumberFormatException e) {
                System.out.print("Valor inválido. Intente nuevamente: ");
            }
        }
    }
}

