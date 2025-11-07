/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.io.FileWriter;
import java.io.IOException;
import model.Adopcion;
import utils.ConexionDB;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    //EXAMEN
    
    // ✅ Verificar si existe adopción sin contrato
    public boolean existeAdopcionSinContrato(Connection con, int adopcionId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM adopciones WHERE id = ? AND contrato_texto IS NULL";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, adopcionId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return rs.getInt(1) > 0;
            }
        }
        return false;
    }

    // ✅ Obtener datos completos para el contrato
    public Adopcion obtenerDatosCompletos(Connection con, int adopcionId) throws SQLException {
    String sql = """
        SELECT a.id, a.mascota_adopcion_id, a.dueno_id, a.fecha_adopcion,
               a.contrato_texto, a.condiciones_especiales,
               a.seguimiento_requerido, a.fecha_primer_seguimiento,
               ma.id AS id_mascota_adopcion,
               m.nombre AS nombre_mascota, m.microchip,
               d.nombre_completo AS nombre_dueno, d.documento_identidad AS documento_dueno,
               r.nombre AS nombre_raza, e.nombre AS nombre_especie
        FROM adopciones a
        JOIN duenos d ON a.dueno_id = d.id
        JOIN mascotas_adopcion ma ON a.mascota_adopcion_id = ma.id
        JOIN mascotas m ON ma.mascota_id = m.id
        JOIN razas r ON m.raza_id = r.id
        JOIN especies e ON r.especie_id = e.id
        WHERE a.id = ?
    """;

    try (PreparedStatement stmt = con.prepareStatement(sql)) {
        stmt.setInt(1, adopcionId);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                Adopcion adop = new Adopcion(
                    rs.getInt("id"),
                    rs.getInt("mascota_adopcion_id"),
                    rs.getInt("dueno_id"),
                    rs.getDate("fecha_adopcion"),
                    rs.getString("contrato_texto"),
                    rs.getString("condiciones_especiales"),
                    rs.getBoolean("seguimiento_requerido"),
                    rs.getDate("fecha_primer_seguimiento")
                );
                adop.setMascotaAdopcion(rs.getString("nombre_mascota"));
                adop.setDueno(rs.getString("nombre_dueno"));
                adop.setRazaNombre(rs.getString("nombre_raza"));
                adop.setEspecieNombre(rs.getString("nombre_especie"));
                adop.setDocumentoDueno(rs.getString("documento_dueno"));
                adop.setMicrochip(rs.getString("microchip"));
                return adop;
            }
        }
    }
    return null;
}


    // ✅ Guardar contrato en archivo y base de datos
    public void guardarContrato(Connection con, int adopcionId, String contratoTexto) throws SQLException, IOException {
        // 1️⃣ Crear archivo
        String nombreArchivo = "contrato_adopcion_" + adopcionId + ".txt";
        try (FileWriter writer = new FileWriter(nombreArchivo)) {
            writer.write(contratoTexto);
        }

        // 2️⃣ Actualizar campo en BD
        String sql = "UPDATE adopciones SET contrato_texto = ? WHERE id = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, contratoTexto);
            stmt.setInt(2, adopcionId);
            stmt.executeUpdate();
        }
    }

    // ✅ Generar texto del contrato
    public String generarContratoTexto(Adopcion adopcion) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String fecha = LocalDateTime.now().format(formatter);
        String microchip = (adopcion.getMicrochip() == null || adopcion.getMicrochip().isBlank())
                ? "N/A"
                : adopcion.getMicrochip();

        return """
            --- CONTRATO DE ADOPCIÓN "HAPPY FEET" ---
            FECHA: """ + fecha + """

            DATOS DEL ADOPTANTE:
            Nombre: """ + adopcion.getDuenoId() + """
            Documento: """ + adopcion.getDocumentoDueno() + """

            DATOS DE LA MASCOTA:
            Nombre: """ + adopcion.getMascotaAdopcionId()+ """
            Especie: """ + adopcion.getEspecieNombre() + """
            Raza: """ + adopcion.getRazaNombre() + """
            Identificación: """ + microchip + """

            El adoptante se compromete a brindar al animal una vida digna, alimentación,
            atención médica y afecto, garantizando su bienestar y notificar a "Happy Feet"
            cualquier cambio relevante en su situación o salud.

            Firma del adoptante: ________________________
            """;
    }
}
    
