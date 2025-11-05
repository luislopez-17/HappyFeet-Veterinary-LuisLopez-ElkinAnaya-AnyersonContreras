/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import model.JornadasVacunaciones;
import utils.ConexionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ELKIN
 */
public class JornadaVacunacionDAO {
    
    // Verifica si existe una jornada por id
    public boolean existeJornada(Connection con, int id) throws SQLException {
        String sql = "SELECT COUNT(*) FROM jornadas_vacunacion WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }
    
    // En JornadasVacunacionDAO
    public boolean existePorId(int id) {
        String sql = "SELECT COUNT(*) FROM jornadas_vacunacion WHERE id=?";
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error SQL en existePorId: " + ex.getMessage());
            ex.printStackTrace();
        }
        return false;
    }

    // Agregar jornada
    public void agregar(JornadasVacunaciones j) {
        String sql = "INSERT INTO jornadas_vacunacion(nombre, fecha, hora_inicio, hora_fin, ubicacion, descripcion, capacidad_maxima, estado) VALUES (?,?,?,?,?,?,?,?)";

        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Validaciones NOT NULL
            if (j.getNombre() == null || j.getNombre().isBlank()) {
                System.out.println("Error: El nombre es obligatorio.");
                return;
            }
            if (j.getFecha() == null) {
                System.out.println("Error: La fecha es obligatoria.");
                return;
            }

            ps.setString(1, j.getNombre());
            ps.setDate(2, j.getFecha());
            ps.setTime(3, j.getHoraInicio());
            ps.setTime(4, j.getHoraFin());
            ps.setString(5, (j.getUbicacion() != null && !j.getUbicacion().isBlank()) ? j.getUbicacion() : null);
            ps.setString(6, (j.getDescripcion() != null && !j.getDescripcion().isBlank()) ? j.getDescripcion() : null);
            ps.setInt(7, j.getCapacidadMaxima());
            ps.setString(8, j.getEstado().name().replace("_", " ")); // Para mantener el enum en DB

            int filas = ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    System.out.println("Jornada agregada con id = " + rs.getInt(1));
                }
            }
            System.out.println("Filas afectadas: " + filas);

        } catch (SQLException ex) {
            System.out.println("Error SQL: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    // Actualizar jornada
    public void actualizar(JornadasVacunaciones j) {
        String sql = "UPDATE jornadas_vacunacion SET nombre=?, fecha=?, hora_inicio=?, hora_fin=?, ubicacion=?, descripcion=?, capacidad_maxima=?, estado=? WHERE id=?";

        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            if (!existeJornada(con, j.getId())) {
                System.out.println("Error: La jornada no existe.");
                return;
            }

            // Validaciones NOT NULL
            if (j.getNombre() == null || j.getNombre().isBlank()) {
                System.out.println("Error: El nombre es obligatorio.");
                return;
            }
            if (j.getFecha() == null) {
                System.out.println("Error: La fecha es obligatoria.");
                return;
            }

            ps.setString(1, j.getNombre());
            ps.setDate(2, j.getFecha());
            ps.setTime(3, j.getHoraInicio());
            ps.setTime(4, j.getHoraFin());
            ps.setString(5, (j.getUbicacion() != null && !j.getUbicacion().isBlank()) ? j.getUbicacion() : null);
            ps.setString(6, (j.getDescripcion() != null && !j.getDescripcion().isBlank()) ? j.getDescripcion() : null);
            ps.setInt(7, j.getCapacidadMaxima());
            ps.setString(8, j.getEstado().name().replace("_", " "));
            ps.setInt(9, j.getId());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Jornada actualizada correctamente, filas afectadas: " + filas);
            } else {
                System.out.println("No se encontró la jornada con id = " + j.getId());
            }

        } catch (SQLException ex) {
            System.out.println("Error al actualizar: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    // Eliminar jornada
    public void eliminar(int id) {
        String sql = "DELETE FROM jornadas_vacunacion WHERE id=?";
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Jornada eliminada correctamente, filas afectadas: " + filas);
            } else {
                System.out.println("No se encontró la jornada con id = " + id);
            }

        } catch (SQLException ex) {
            System.out.println("Error al eliminar: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    // Listar jornadas
    public List<JornadasVacunaciones> listar() {
        List<JornadasVacunaciones> lista = new ArrayList<>();
        String sql = "SELECT id, nombre, fecha, hora_inicio, hora_fin, ubicacion, descripcion, capacidad_maxima, estado FROM jornadas_vacunacion";

        try (Connection con = ConexionDB.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                Date fecha = rs.getDate("fecha");
                Time horaInicio = rs.getTime("hora_inicio");
                Time horaFin = rs.getTime("hora_fin");
                String ubicacion = rs.getString("ubicacion");
                String descripcion = rs.getString("descripcion");
                int capacidad = rs.getInt("capacidad_maxima");
                String estadoStr = rs.getString("estado").replace(" ", "_");
                JornadasVacunaciones.Estado estado = JornadasVacunaciones.Estado.valueOf(estadoStr);

                JornadasVacunaciones j = new JornadasVacunaciones(id, nombre, fecha, horaInicio, horaFin, ubicacion, descripcion, capacidad, estado);
                lista.add(j);
            }

        } catch (SQLException ex) {
            System.out.println("Error SQL en listar jornadas: " + ex.getMessage());
            ex.printStackTrace();
        }

        return lista;
    }
}
