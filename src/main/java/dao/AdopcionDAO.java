/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import model.Adopcion;
import utils.ConexionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ELKIN
 */
public class AdopcionDAO {
    // Verificar si existe la mascota por id
    public boolean existeMascota(Connection con, int id) throws SQLException {
        String sql = "SELECT COUNT(*) FROM mascotas_adopcion WHERE id = ?";
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

    // Verificar si existe el dueño por id
    public boolean existeDueno(Connection con, int id) throws SQLException {
        String sql = "SELECT COUNT(*) FROM duenos WHERE id = ?";
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

    // Agregar adopcion
    public void agregar(Adopcion a) {
        String sql = "INSERT INTO adopciones(mascota_adopcion_id, dueno_id, fecha_adopcion, contrato_texto, condiciones_especiales, seguimiento_requerido, fecha_primer_seguimiento) VALUES (?,?,?,?,?,?,?)";

        try (Connection con = ConexionDB.conectar()) {

            // Verificar que existan los ids
            if (!existeMascota(con, a.getMascotaAdopcionId())) {
                System.out.println("Error: La mascota no existe.");
                return;
            }
            if (!existeDueno(con, a.getDuenoId())) {
                System.out.println("Error: El dueño no existe.");
                return;
            }

            try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, a.getMascotaAdopcionId());
                ps.setInt(2, a.getDuenoId());
                ps.setDate(3, a.getFechaAdopcion());
                ps.setString(4, a.getContratoTexto());
                ps.setString(5, a.getCondicionesEspeciales());
                ps.setBoolean(6, a.isSeguimientoRequerido());
                ps.setDate(7, a.getFechaPrimerSeguimiento());

                int filas = ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        System.out.println("Adopción agregada con id = " + rs.getInt(1));
                    }
                }
                System.out.println("Filas afectadas: " + filas);
            }
        } catch (SQLException ex) {
            System.out.println("Error SQL: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    // Actualizar adopcion
    public void actualizar(Adopcion a) {
        String sql = "UPDATE adopciones SET mascota_adopcion_id=?, dueno_id=?, fecha_adopcion=?, contrato_texto=?, condiciones_especiales=?, seguimiento_requerido=?, fecha_primer_seguimiento=? WHERE id=?";

        try (Connection con = ConexionDB.conectar()) {

            if (!existeMascota(con, a.getMascotaAdopcionId())) {
                System.out.println("Error: La mascota no existe.");
                return;
            }
            if (!existeDueno(con, a.getDuenoId())) {
                System.out.println("Error: El dueño no existe.");
                return;
            }

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, a.getMascotaAdopcionId());
                ps.setInt(2, a.getDuenoId());
                ps.setDate(3, a.getFechaAdopcion());
                ps.setString(4, a.getContratoTexto());
                ps.setString(5, a.getCondicionesEspeciales());
                ps.setBoolean(6, a.isSeguimientoRequerido());
                ps.setDate(7, a.getFechaPrimerSeguimiento());
                ps.setInt(8, a.getId());

                int filas = ps.executeUpdate();
                if (filas > 0) {
                    System.out.println("Adopción actualizada correctamente, filas afectadas: " + filas);
                } else {
                    System.out.println("No se encontró la adopción con id = " + a.getId());
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error al actualizar: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    // Eliminar adopcion
    public void eliminar(int id) {
    String sql = "DELETE FROM adopciones WHERE id=?";
    try (Connection con = ConexionDB.conectar();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, id);
        int filas = ps.executeUpdate();
        if (filas > 0) {
            System.out.println("Adopción eliminada correctamente, filas afectadas: " + filas);
        } else {
            System.out.println("No se encontró la adopción con id = " + id);
        }
    } catch (SQLException ex) {
        System.out.println("Error al eliminar: " + ex.getMessage());
        ex.printStackTrace();
    }
}

    // Listar adopciones
    public List<Adopcion> listar() {
    List<Adopcion> lista = new ArrayList<>();
    String sql = "SELECT * FROM adopciones";

    try (Connection con = ConexionDB.conectar();
         Statement st = con.createStatement();
         ResultSet rs = st.executeQuery(sql)) {

        while (rs.next()) {
            int id = rs.getInt("id");
            int mascotaId = rs.getInt("mascota_adopcion_id");
            int duenoId = rs.getInt("dueno_id");
            Date fechaAdopcion = rs.getDate("fecha_adopcion");
            String contrato = rs.getString("contrato_texto");
            String condiciones = rs.getString("condiciones_especiales");
            boolean seguimiento = rs.getBoolean("seguimiento_requerido");
            Date fechaPrimer = rs.getDate("fecha_primer_seguimiento");

            Adopcion a = new Adopcion(id, mascotaId, duenoId, fechaAdopcion, contrato, condiciones, seguimiento, fechaPrimer);

            // Etiquetas genéricas para mostrar en la vista
            a.setMascotaAdopcion("Mascota #" + mascotaId);
            a.setDueno("Dueño #" + duenoId);

            lista.add(a);
        }

    } catch (SQLException e) {
        System.out.println("Error al listar adopciones: " + e.getMessage());
    }

    return lista;
}
}
    
