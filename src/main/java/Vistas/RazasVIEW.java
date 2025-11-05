/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vistas;

import Controller.RazasController;
import java.util.List;
import java.util.Scanner;
import model.Razas;

/**
 *
 * @author usuario
 */
public class RazasVIEW {
    private final Scanner sc = new Scanner(System.in);
    private final RazasController controller = new RazasController();
    
    public void mostrarMenu(){
        int opcion;
        do{
            System.out.println("\n ===================");
            System.out.println("    GESTION DE RAZAS   ");
            System.out.println("===================");
            System.out.println("1. Listar razas");
            System.out.println("2. Agregar razas");
            System.out.println("3. Actualizar raza");
            System.out.println("4. Eliminar raza");
            System.out.println("0. Volver al menú principal");
            System.out.println("Elige una opcion");
                    
            opcion = leerEntero();
            
            switch (opcion) {
                case 1: lista(); break;    
                case 2: agregar(); break;
                case 3: actualizar(); break;
                case 4: eliminar(); break;
                default:
                    System.out.println("Opcion no valida");
            }
        }while (opcion != 0);
    }
    
    private void lista(){
        List<Razas> lista = controller.listarRazas();
        if(lista.isEmpty()){
            System.out.println("La lista esta vacia");
        }else {
            System.out.println("\n Lista especies ");
            for(Razas r : lista){
                 System.out.println("ID: " + r.getId() + " | ID Especie: " + r.getEspecieID() + " | Nombre: " + r.getNombre() + " | Descripción: " + r.getCaracteristicas());
            }
        }
    }
    
    private void agregar(){
        System.out.println("\n AGREGAR RAZA");
        System.out.println("ID especie: ");
        int EspecieId = leerEntero();
        System.out.println("Nombre: ");
        String nombre = sc.nextLine();
        System.out.println("Caracteristicas : ");
        String caracteristicas = sc.nextLine();
        
        controller.agregarRaza(EspecieId, nombre, caracteristicas);
        System.out.println("Raza agregada correctamente");
    }
    
    private void actualizar() {
        System.out.println("\n===== ACTUALIZAR RAZA =====");
        System.out.print("ID de la raza a actualizar: ");
        int id = leerEntero();

        System.out.print("Nuevo ID de especie: ");
        int especieId = leerEntero();

        System.out.print("Nuevo nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Nuevas características: ");
        String caracteristicas = sc.nextLine();

        controller.actualizarRaza(id, nombre, caracteristicas, especieId);
    }

    private void eliminar() {
        System.out.println("\n===== ELIMINAR RAZA =====");
        System.out.print("Ingrese el ID de la raza a eliminar: ");
        int id = leerEntero();
        controller.eliminarRaza(id);
    }

    private int leerEntero() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Ingrese un número válido: ");
            }
        }
    }
}
