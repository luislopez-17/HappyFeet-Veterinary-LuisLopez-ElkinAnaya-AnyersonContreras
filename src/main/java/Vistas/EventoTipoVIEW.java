/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vistas;

import Controller.EventoTipoController;
import java.util.List;
import java.util.Scanner;
import model.EventosTipos;

/**
 *
 * @author usuario
 */
public class EventoTipoVIEW {
    private final Scanner sc = new Scanner(System.in);
    private final EventoTipoController controller = new EventoTipoController();
    
    public void mostrarMenu(){
        int opcion;
        
        do{
            System.out.println("\n============================");
            System.out.println("     GESTIÓN DE EVENTO TIPO   ");
            System.out.println("============================");
            System.out.println("1. Listar Producto tipos");
            System.out.println("2. Agregar Producto tipos");
            System.out.println("3. Actualizar Producto tipos");
            System.out.println("4. Eliminar Producto ");
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
        }while (opcion != 0);    
    }
    
    private void lista(){
        List<EventosTipos> lista = controller.listar();
        if(lista.isEmpty()){
            System.out.println("La lista esta vacia");
        }else{
            System.out.println("\n Lista de evento tipos");
            for(EventosTipos et : lista){
                System.out.println("ID: " + et.getId() + " | Nombre: " + et.getNombre() + " | Descripción: " + et.getDescripcion());
            }
        }
    }
    
    public void agregar(){
        System.out.println("\n AGREGAR EVENTO TIPO");
        System.out.println("Nombre : ");
        String nombre = sc.nextLine();
        System.out.println("Descripción: ");
        String descripcion = sc.nextLine();
        
        controller.agregarEvento(nombre, descripcion);
    }
    
    public void actualizar(){
        System.out.println("\n ACTUALIZAR EVENTO TIPO");
        System.out.println("ID de la especie: ");
        int id = leerEntero();
        System.out.println("Nuevo nombre: ");
        String nombre = sc.nextLine();
        System.out.println("Nueva descripcion: ");
        String descripcion = sc.nextLine();
        
        controller.actualizarEvento(id, nombre, descripcion);
    }
    
     public void eliminar(){
        System.out.println("\n ELIMINAR ESPECIE ");
        System.out.println("ID de la especie: ");
        int id = leerEntero();
        
        controller.eliminarEvento(id);
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
