/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vistas;

import Controller.EspecieController;
import java.util.List;
import java.util.Scanner;
import model.Especies;

/**
 *
 * @author usuario
 */
public class EspecieVIEW {
    private final Scanner sc = new Scanner(System.in);
    private final EspecieController controller = new EspecieController();
    
    public void mostrarMenu(){
        int opcion;
        
        do{
            System.out.println("\n============================");
            System.out.println("     GESTIÓN DE ESPECIES    ");
            System.out.println("============================");
            System.out.println("1. Listar especies");
            System.out.println("2. Agregar especie");
            System.out.println("3. Actualizar especie");
            System.out.println("4. Eliminar especie");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = leerEntero();
            
            switch (opcion) {
                case 1: lista(); break;
                case 2: agregar(); break;
                case 3: actualizar(); break;
                case 4: eliminar(); break;
                case 0: System.out.println("Volviendo al menú principal..."); break;
                default:
                    System.out.println("Opcion no valida");
            }
        } while (opcion != 0);
    }
    
    private void lista(){
        List<Especies> lista = controller.listarEspecie();
        if(lista.isEmpty()){
            System.out.println("La lista esta vacia");
        }else {
            System.out.println("\n Lista especies ");
            for(Especies e : lista){
                 System.out.println("ID: " + e.getId() + " | Nombre: " + e.getNombre() + " | Descripción: " + e.getDescripcion());
            }
        }
    }
    
    public void agregar(){
        System.out.println("\n AGREGAR ESPECIE");
        System.out.println("Nombre : ");
        String nombre = sc.nextLine();
        System.out.println("Descripción: ");
        String descripcion = sc.nextLine();
        
        controller.agregarEspecie(nombre, descripcion);
    }
    
    public void actualizar(){
        System.out.println("\n ACTUALIZAR ESPECIE");
        System.out.println("ID de la especie: ");
        int id = leerEntero();
        System.out.println("Nuevo nombre: ");
        String nombre = sc.nextLine();
        System.out.println("Nueva descripcion: ");
        String descripcion = sc.nextLine();
        
        controller.actualizarEspecie(id, nombre, descripcion);
        System.out.println("Especie Actualizada correctamente");
    }
    
    public void eliminar(){
        System.out.println("\n ELIMINAR ESPECIE ");
        System.out.println("ID de la especie: ");
        int id = leerEntero();
        
        controller.eliminarEspecie(id);
        System.out.println("Especie eliminada correctamente");
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
