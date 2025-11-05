/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import dao.MascotasDAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import model.Mascotas;
import utils.ConexionDB;

/**
 *
 * @author usuario
 */
public class MascotasController {
    private MascotasDAO mascotaDao;
    
    public MascotasController(){
        this.mascotaDao = new MascotasDAO();
    }
    
    public void agregar(Integer duenoId, String nombre, Integer razaId, String fechaNacimientoStr, String sexoInput, double pesoActual, String microchip, String tatuaje,
                        String urlFoto, String alergias, String condicionesPreexistentes){
        if (duenoId <= 0 || duenoId == null){
            System.out.println("El Dueño id es obligatorio y no puede ser menor o igual que cero");
            return;
        }
        if(nombre == null || nombre.trim().isBlank()){
            System.out.println("El nombre es obligatorio");
            return;
        }
        if(razaId <= 0 || razaId == null){
            System.out.println("La raza id es obligatoria y no puede ser menor o igual que cero");
            return;
        }
        // Validar que no esté vacío
        if (sexoInput == null || sexoInput.trim().isEmpty()) {
            System.out.println("El campo sexo no puede estar vacío.");
            return;
        }

        // Validar que sea "MACHO" o "HEMBRA" (ignorando mayúsculas/minúsculas)
            Mascotas.Sexo sexo;
        try {
            sexo = Mascotas.Sexo.valueOf(
            sexoInput.trim().substring(0, 1).toUpperCase() + sexoInput.trim().substring(1).toLowerCase()
            );
        } catch (IllegalArgumentException ex) {
            System.out.println("Valor de sexo inválido. Debe ser 'Macho' o 'Hembra'.");
            return;
        }
        
        Date fechaNacimiento = null;
        if (fechaNacimientoStr != null && !fechaNacimientoStr.trim().isEmpty()) {
            try {
                fechaNacimiento = Date.valueOf(fechaNacimientoStr.trim());
            } catch (IllegalArgumentException e) {
                System.out.println("Formato de fecha inválido. Use yyyy-MM-dd o deje vacío.");
                return;
            }
        }
        
        Mascotas m = new Mascotas(duenoId, nombre, razaId, fechaNacimiento, sexo, pesoActual, microchip, tatuaje, urlFoto, alergias, condicionesPreexistentes, null, true);
        try (Connection con = ConexionDB.conectar()) {
            mascotaDao.agregar(m, con, duenoId, razaId, microchip);
        } catch (SQLException ex) {
            System.out.println("Error al agregar dueño: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public void actualizar(int id, Integer duenoId, String nombre, Integer razaId, String fechaNacimientoStr, String sexoInput, double pesoActual, String microchip, String tatuaje,
                           String urlFoto, String alergias, String condicionesPreexistentes, boolean activo){
        if (id <= 0) {
            System.out.println("ID inválido.");
            return;
        }
        
        if (duenoId <= 0 || duenoId == null){
            System.out.println("El Dueño id es obligatorio y no puede ser menor o igual que cero");
            return;
        }
        if(nombre == null || nombre.trim().isBlank()){
            System.out.println("El nombre es obligatorio");
            return;
        }
        if(razaId <= 0 || razaId == null){
            System.out.println("La raza id es obligatoria y no puede ser menor o igual que cero");
            return;
        }
        
        Date fechaNacimiento = null;
        if (fechaNacimientoStr != null && !fechaNacimientoStr.trim().isEmpty()) {
            try {
                fechaNacimiento = Date.valueOf(fechaNacimientoStr.trim());
            } catch (IllegalArgumentException e) {
                System.out.println("⚠️ Formato de fecha inválido. Use yyyy-MM-dd o deje vacío.");
                return;
            }
        }
        // Validar que no esté vacío
        if (sexoInput == null || sexoInput.trim().isEmpty()) {
            System.out.println("El campo sexo no puede estar vacío.");
            return;
        }

        // Validar que sea "MACHO" o "HEMBRA" (ignorando mayúsculas/minúsculas)
        Mascotas.Sexo sexo;
        try {
            if (sexoInput == null || sexoInput.trim().isEmpty()) {
                System.out.println("⚠️ El campo sexo no puede estar vacío.");
                return;
            }
            // Normaliza el texto ingresado: "macho", "MACHO", "   heMBra" → "Macho" o "Hembra"
            String normalizado = sexoInput.trim().substring(0, 1).toUpperCase() +
                             sexoInput.trim().substring(1).toLowerCase();

            sexo = Mascotas.Sexo.valueOf(normalizado);
        } catch (IllegalArgumentException ex) {
            System.out.println("Valor de sexo inválido. Debe ser 'Macho' o 'Hembra'.");
            return;
        }

        
        Mascotas m = new Mascotas(id, duenoId, nombre, razaId, fechaNacimiento, sexo, pesoActual, microchip, tatuaje, urlFoto, alergias, condicionesPreexistentes, null, activo);
        try (Connection con = ConexionDB.conectar()) {
            mascotaDao.actualizar(m, duenoId, razaId, microchip);
        } catch (SQLException ex) {
            System.out.println("Error al actualizar dueño: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public void eliminar(int id){
        if (id <= 0) {
            System.out.println("ID inválido.");
            return;
        }
        //se crea una especie "temporal" solo con el id
        Mascotas m = new Mascotas(id, 0, "", 0, null ,Mascotas.Sexo.Macho, 0.0, "", "", "", "", "",null, true);
        mascotaDao.eliminar(m);
    }
    
    public List<Mascotas> listar(){
        return mascotaDao.listar();
    }
}
