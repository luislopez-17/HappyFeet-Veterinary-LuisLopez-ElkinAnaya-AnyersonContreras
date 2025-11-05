package Controller;

import dao.ProveedoresDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import model.Proveedores;
import utils.ConexionDB;

public class ProveedoresController {

    private final ProveedoresDAO dao = new ProveedoresDAO();

    public void agregar(String nombreEmpresa, String contacto, String telefono, String email, String direccion, String sitioWeb) {
        if (nombreEmpresa == null || nombreEmpresa.trim().isEmpty()) {
            System.out.println("El nombre de la empresa es obligatorio.");
            return;
        }

        Proveedores p = new Proveedores(nombreEmpresa, contacto, telefono, email, direccion, sitioWeb, true, new Timestamp(System.currentTimeMillis()));

        try (Connection con = ConexionDB.conectar()) {
            dao.agregar(p, con);
        } catch (SQLException e) {
            System.out.println("Error al agregar proveedor: " + e.getMessage());
        }
    }

    public void actualizar(int id, String nombreEmpresa, String contacto, String telefono, String email, String direccion, String sitioWeb, boolean activo) {
        if (id <= 0) {
            System.out.println("ID inválido para actualizar.");
            return;
        }

        if (nombreEmpresa == null || nombreEmpresa.trim().isEmpty()) {
            System.out.println("El nombre de la empresa es obligatorio.");
            return;
        }

        Proveedores p = new Proveedores(id, nombreEmpresa, contacto, telefono, email, direccion, sitioWeb, activo, null);

        try {
            dao.actualizar(p);
        } catch (SQLException e) {
            System.out.println("Error al actualizar proveedor: " + e.getMessage());
        }
    }

    public void eliminar(int id) {
        if (id <= 0) {
            System.out.println("ID inválido para eliminar.");
            return;
        }
        dao.eliminar(id);
    }

    public List<Proveedores> listar() {
        return dao.listar();
    }
}

