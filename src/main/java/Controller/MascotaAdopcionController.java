/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;


import dao.MascotaAdopcionDAO;
import model.MascotaAdopcion;

import java.sql.Date;
import java.util.List;


/**
 *
 * @author ELKIN
 */
public class MascotaAdopcionController {
    private final MascotaAdopcionDAO dao;

    public MascotaAdopcionController() {
        this.dao = new MascotaAdopcionDAO();
    }

    // AGREGAR MASCOTA EN ADOPCIÓN
    public void agregar(int mascotaId, Date fechaIngreso, String motivoIngreso,
                        String estado, String historia, String temperamento,
                        String necesidadesEspeciales, String fotoAdicionalUrl) {
        MascotaAdopcion m = new MascotaAdopcion(mascotaId, fechaIngreso, motivoIngreso, estado,
                                                historia, temperamento, necesidadesEspeciales, fotoAdicionalUrl);
        dao.agregar(m);
    }

    // LISTAR MASCOTAS EN ADOPCIÓN
    public List<MascotaAdopcion> listar() {
        return dao.listar();
    }

    // ACTUALIZAR ESTADO DE LA MASCOTA EN ADOPCIÓN
    public void actualizarEstado(int id, String nuevoEstado) {
        MascotaAdopcion m = new MascotaAdopcion(id, 0, null, null, nuevoEstado, null, null, null, null);
        dao.actualizar(m);
    }

    // ELIMINAR MASCOTA EN ADOPCIÓN
    public void eliminar(int id) {
        MascotaAdopcion m = new MascotaAdopcion(id, 0, null, null, null, null, null, null, null);
        dao.eliminar(id);
    }
}
