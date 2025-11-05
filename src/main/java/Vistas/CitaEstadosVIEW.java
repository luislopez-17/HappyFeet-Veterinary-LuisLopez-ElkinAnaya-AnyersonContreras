/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vistas;

import Controller.CitaEstadosController;
import java.util.List;
import java.util.Scanner;
import model.CitaEstados;

/**
 *
 * @author usuario
 */
public class CitaEstadosVIEW {
    private final Scanner sc = new Scanner(System.in);
    private final CitaEstadosController controller = new CitaEstadosController();
    
    public void mostrarMenu(){
        int opcion;
        
        do{
            System.out.println("\n============================");
            System.out.println("     GESTIÓN DE CITA ESTADOS   ");
            System.out.println("============================");
            System.out.println("1. Listar citas esatados");
            System.out.println("2. Agregar citas esatados");
            System.out.println("3. Actualizar citas esatados");
            System.out.println("4. Eliminar citas esatados");
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
        List<CitaEstados> lista = controller.listar();
        if(lista.isEmpty()){
            System.out.println("La lista esta vacia");
        }else{
            System.out.println("\n Lista de evento tipos");
            for(CitaEstados et : lista){
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
        
        controller.agregarCita(nombre, descripcion);
    }
    
    public void actualizar(){
        System.out.println("\n ACTUALIZAR EVENTO TIPO");
        System.out.println("ID de la especie: ");
        int id = leerEntero();
        System.out.println("Nuevo nombre: ");
        String nombre = sc.nextLine();
        System.out.println("Nueva descripcion: ");
        String descripcion = sc.nextLine();
        
        controller.actualizarCita(id, nombre, descripcion);
    }
    
     public void eliminar(){
        System.out.println("\n ELIMINAR ESPECIE ");
        System.out.println("ID de la especie: ");
        int id = leerEntero();
        
        controller.eliminarCita(id);
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
