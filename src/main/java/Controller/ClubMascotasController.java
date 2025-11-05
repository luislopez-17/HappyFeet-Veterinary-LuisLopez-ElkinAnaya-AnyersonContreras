/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import dao.ClubMascotasDAO;
import model.ClubMascotas;

import java.sql.Date;
import java.sql.SQLException;
/**
 *
 * @author ELKIN
 */
public class ClubMascotasController {
    
    private final ClubMascotasDAO dao = new ClubMascotasDAO();

    public void agregar(int duenoId, int puntosAcum, int puntosCanj, int puntosDisp, String nivel,
                        Date fechaInscripcion, boolean activo) {
        ClubMascotas c = new ClubMascotas(duenoId, puntosAcum, puntosCanj, puntosDisp, nivel, fechaInscripcion, null, activo);
        dao.agregar(c);
    }

    public void actualizar(int id, int duenoId, int puntosAcum, int puntosCanj, int puntosDisp, String nivel,
                           Date fechaInscripcion, boolean activo) {
        ClubMascotas c = new ClubMascotas(id, duenoId, puntosAcum, puntosCanj, puntosDisp, nivel, fechaInscripcion, null, activo);
        dao.actualizar(c);
    }

    public void eliminar(int id) {
        dao.eliminar(id);
    }

    public java.util.List<ClubMascotas> listar() {
        return dao.listar();
    }

    public boolean existePorId(int id) {
        try (var con = utils.ConexionDB.conectar()) {
            return dao.existePorId(con, id);
        } catch (SQLException ex) {
            System.out.println("Error SQL al verificar existencia de club: " + ex.getMessage());
            return false;
        }
    }
}
