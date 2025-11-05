package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Proveedores;
import utils.ConexionDB;

public class ProveedoresDAO {

    private boolean existeProveedor(Connection con, int id) throws SQLException {
        String sql = "SELECT COUNT(*) FROM proveedores WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }

    public void agregar(Proveedores p, Connection con) throws SQLException {
        String sql = "INSERT INTO proveedores (nombre_empresa, contacto, telefono, email, direccion, sitio_web) VALUES (?,?,?,?,?,?)";
        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, p.getNombreEmpresa());
            ps.setString(2, p.getContacto());
            ps.setString(3, p.getTelefono());
            ps.setString(4, p.getEmail());
            ps.setString(5, p.getDireccion());
            ps.setString(6, p.getSitioWeb());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        System.out.println("Proveedor agregado correctamente con ID = " + rs.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al agregar proveedor: " + e.getMessage());
            throw e;
        }
    }

    public void actualizar(Proveedores p) throws SQLException {
        String sql = "UPDATE proveedores SET nombre_empresa=?, contacto=?, telefono=?, email=?, direccion=?, sitio_web=?, activo=? WHERE id=?";
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            if (!existeProveedor(con, p.getId())) {
                System.out.println("No existe un proveedor con el ID especificado.");
                return;
            }

            ps.setString(1, p.getNombreEmpresa());
            ps.setString(2, p.getContacto());
            ps.setString(3, p.getTelefono());
            ps.setString(4, p.getEmail());
            ps.setString(5, p.getDireccion());
            ps.setString(6, p.getSitioWeb());
            ps.setBoolean(7, p.isActivo());
            ps.setInt(8, p.getId());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Proveedor actualizado correctamente.");
            } else {
                System.out.println("No se pudo actualizar el proveedor.");
            }

        } catch (SQLException e) {
            System.out.println("Error al actualizar proveedor: " + e.getMessage());
            throw e;
        }
    }

    public void eliminar(int id) {
        String sql = "DELETE FROM proveedores WHERE id=?";
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            if (!existeProveedor(con, id)) {
                System.out.println("El proveedor con ID " + id + " no existe.");
                return;
            }

            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Proveedor eliminado correctamente.");
            } else {
                System.out.println("No se pudo eliminar el proveedor.");
            }

        } catch (SQLException e) {
            System.out.println("Error SQL al eliminar proveedor: " + e.getMessage());
        }
    }

    public List<Proveedores> listar() {
        List<Proveedores> lista = new ArrayList<>();
        String sql = "SELECT * FROM proveedores";

        try (Connection con = ConexionDB.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Proveedores p = new Proveedores(
                        rs.getInt("id"),
                        rs.getString("nombre_empresa"),
                        rs.getString("contacto"),
                        rs.getString("telefono"),
                        rs.getString("email"),
                        rs.getString("direccion"),
                        rs.getString("sitio_web"),
                        rs.getBoolean("activo"),
                        rs.getTimestamp("fecha_registro")
                );
                lista.add(p);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar proveedores: " + e.getMessage());
        }

        return lista;
    }
}
