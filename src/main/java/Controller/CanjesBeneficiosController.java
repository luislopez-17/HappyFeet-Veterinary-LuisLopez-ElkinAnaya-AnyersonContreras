/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import dao.CanjesBeneficiosDAO;
import model.CanjesBeneficios;
import java.util.Date;
import java.util.List;
/**
 *
 * @author ELKIN
 */
public class CanjesBeneficiosController {
    private final CanjesBeneficiosDAO dao = new CanjesBeneficiosDAO();

    // Agregar un nuevo canje
    public void agregar(int clubMascotasId, int beneficioId, Date fechaCanje, int puntosCanjeados,
                        CanjesBeneficios.Estado estado, Date fechaExpiracion, int facturaId) {

        if (clubMascotasId <= 0) { System.out.println("Error: ID de ClubMascotas inválido."); return; }
        if (beneficioId <= 0) { System.out.println("Error: ID de Beneficio inválido."); return; }
        if (fechaCanje == null) { System.out.println("Error: Fecha de canje obligatoria."); return; }
        if (puntosCanjeados <= 0) { System.out.println("Error: Puntos canjeados deben ser mayores a 0."); return; }

        CanjesBeneficios c = new CanjesBeneficios(clubMascotasId, beneficioId, fechaCanje, puntosCanjeados,
                estado, fechaExpiracion, facturaId);
        dao.agregar(c);
    }

    // Actualizar un canje existente
    public void actualizar(int id, int clubMascotasId, int beneficioId, Date fechaCanje, int puntosCanjeados,
                           CanjesBeneficios.Estado estado, Date fechaExpiracion, int facturaId) {

        if (id <= 0) { System.out.println("Error: ID de canje inválido."); return; }
        if (clubMascotasId <= 0) { System.out.println("Error: ID de ClubMascotas inválido."); return; }
        if (beneficioId <= 0) { System.out.println("Error: ID de Beneficio inválido."); return; }
        if (fechaCanje == null) { System.out.println("Error: Fecha de canje obligatoria."); return; }
        if (puntosCanjeados <= 0) { System.out.println("Error: Puntos canjeados deben ser mayores a 0."); return; }

        CanjesBeneficios c = new CanjesBeneficios(id, clubMascotasId, beneficioId, fechaCanje, puntosCanjeados,
                estado, fechaExpiracion, facturaId);
        dao.actualizar(c);
    }

    // Eliminar un canje por ID
    public void eliminar(int id) {
        if (id <= 0) { System.out.println("Error: ID de canje inválido."); return; }
        dao.eliminar(id);
    }

    // Listar todos los canjes
    public List<CanjesBeneficios> listar() {
        return dao.listar();
    }
}
