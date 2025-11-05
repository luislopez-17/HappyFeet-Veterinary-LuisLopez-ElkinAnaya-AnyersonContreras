/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Servicios;
import utils.ConexionDB;

/**
 *
 * @author ELKIN
 */
public class ServiciosDAO {
    // Validación: verificar si ya existe un servicio con el mismo nombre
    public boolean existeNombre(Connection con, String nombre) throws SQLException {
        String sql = "SELECT COUNT(*) FROM servicios WHERE nombre = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0; // true si ya existe
                }
            }
        }
        return false;
    }

    // Agregar nuevo servicio
    public void agregar(Servicios s, Connection con) throws SQLException {
        // Validaciones obligatorias (NOT NULL)
        if (s.getNombre() == null || s.getNombre().isBlank()) {
            System.out.println("Error: el nombre del servicio no puede estar vacío.");
            return;
        }
        if (s.getPrecioBase() < 0) {
            System.out.println("Error: el precio base no puede ser negativo.");
            return;
        }

        if (existeNombre(con, s.getNombre())) {
            System.out.println("Error: ya existe un servicio con ese nombre.");
            return;
        }

        String sql = "INSERT INTO servicios (nombre, descripcion, categoria, precio_base, duracion_estimada_minutos, activo) "
                   + "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, s.getNombre());
            ps.setString(2, s.getDescripcion());
            ps.setString(3, s.getCategoria());
            ps.setDouble(4, s.getPrecioBase());
            ps.setInt(5, s.getDuracionEstimadaMinutos());
            ps.setBoolean(6, s.isActivo());

            int filas = ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int idGenerado = rs.getInt(1);
                    System.out.println("Servicio insertado con id = " + idGenerado);
                }
            }
            System.out.println("Servicio agregado correctamente. Filas afectadas: " + filas);
        } catch (SQLException ex) {
            System.out.println("Error SQL al insertar servicio: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    // Actualizar servicio existente
    public void actualizar(Servicios s) {
        // Validaciones básicas
        if (s.getNombre() == null || s.getNombre().isBlank()) {
            System.out.println("Error: el nombre no puede estar vacío.");
            return;
        }
        if (s.getPrecioBase() < 0) {
            System.out.println("Error: el precio base debe ser positivo.");
            return;
        }

        String sql = "UPDATE servicios SET nombre = ?, descripcion = ?, categoria = ?, precio_base = ?, duracion_estimada_minutos = ?, activo = ? WHERE id = ?";

        try (Connection con = ConexionDB.conectar()) {
            if (existeNombre(con, s.getNombre())) {
                System.out.println("Ya existe otro servicio con ese nombre. No se puede actualizar.");
                return;
            }

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, s.getNombre());
                ps.setString(2, s.getDescripcion());
                ps.setString(3, s.getCategoria());
                ps.setDouble(4, s.getPrecioBase());
                ps.setInt(5, s.getDuracionEstimadaMinutos());
                ps.setBoolean(6, s.isActivo());
                ps.setInt(7, s.getId());

                int filas = ps.executeUpdate();
                if (filas > 0) {
                    System.out.println("Actualización exitosa. Filas afectadas: " + filas);
                } else {
                    System.out.println("No se encontró un servicio con id = " + s.getId());
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error SQL al actualizar servicio: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    // Eliminar servicio
    public void eliminar(Servicios s) {
        String sql = "DELETE FROM servicios WHERE id = ?";

        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, s.getId());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("El servicio se eliminó correctamente. Filas afectadas = " + filas);
            } else {
                System.out.println("No se encontró un servicio con id = " + s.getId());
            }
        } catch (SQLException ex) {
            System.out.println("Error SQL al eliminar servicio: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    // Listar todos los servicios
    public List<Servicios> listar() {
        List<Servicios> lista = new ArrayList<>();

        String sql = "SELECT id, nombre, descripcion, categoria, precio_base, duracion_estimada_minutos, activo FROM servicios";

        try (Connection con = ConexionDB.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                String categoria = rs.getString("categoria");
                double precioBase = rs.getDouble("precio_base");
                int duracion = rs.getInt("duracion_estimada_minutos");
                boolean activo = rs.getBoolean("activo");

                Servicios s = new Servicios(id, nombre, descripcion, categoria, precioBase, duracion, activo);
                lista.add(s);
            }
        } catch (SQLException ex) {
            System.out.println("Error SQL al listar servicios: " + ex.getMessage());
            ex.printStackTrace();
        }
        return lista;
    }
}
