package dao;

import java.sql.*;
import java.util.*;
import model.Preescripcion;
import utils.ConexionDB;

public class PreescripcionDAO {

    // =========================
    // MÉTODO: AGREGAR
    // =========================
    public void agregar(Preescripcion p) {
        // Validación: al menos uno de los dos campos debe tener valor > 0
        if ( (p.getConsultaId() <= 0) && (p.getProcedimientoId() <= 0) ) {
            System.out.println("Debe especificar una consulta o un procedimiento.");
            return;
        }

        try (Connection conn = ConexionDB.conectar()) {
            // Validar existencia de las FK antes de insertar (usar >0 como condición)
            if (p.getConsultaId() > 0 && !existeRegistro(conn, "consultas_medicas", p.getConsultaId())) {
                System.out.println("️ El ID de consulta no existe en la base de datos.");
                return;
            }
            if (p.getProcedimientoId() > 0 && !existeRegistro(conn, "procedimientos_especiales", p.getProcedimientoId())) {
                System.out.println("El ID de procedimiento no existe en la base de datos.");
                return;
            }
            if (!existeRegistro(conn, "inventario", p.getProductoId())) {
                System.out.println("El ID del producto no existe en el inventario.");
                return;
            }

            String sql = """
                INSERT INTO prescripciones 
                (consulta_id, procedimiento_id, producto_id, cantidad, dosis, frecuencia, duracion_dias, instrucciones, fecha_prescripcion)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                // si el id es <= 0, mandamos NULL
                if (p.getConsultaId() > 0) {
                    ps.setInt(1, p.getConsultaId());
                } else {
                    ps.setNull(1, Types.INTEGER);
                }

                if (p.getProcedimientoId() > 0) {
                    ps.setInt(2, p.getProcedimientoId());
                } else {
                    ps.setNull(2, Types.INTEGER);
                }

                ps.setInt(3, p.getProductoId());
                ps.setInt(4, p.getCantidad());
                ps.setString(5, p.getDosis());
                ps.setString(6, p.getFrecuencia());
                ps.setInt(7, p.getDuracionDias());
                ps.setString(8, p.getInstrucciones());
                ps.setTimestamp(9, p.getFechaPrescripcion());

                ps.executeUpdate();
                System.out.println("Prescripción agregada correctamente.");
            }

        } catch (SQLException e) {
            System.out.println("Error al agregar prescripción: " + e.getMessage());
        }
    }

    // =========================
    // MÉTODO: LISTAR
    // =========================
    public List<Preescripcion> listar() {
        List<Preescripcion> lista = new ArrayList<>();
        String sql = """
            SELECT p.*, 
                   c.id AS consulta, 
                   pr.id AS procedimiento, 
                   i.nombre AS producto 
            FROM prescripciones p
            LEFT JOIN consultas_medicas c ON p.consulta_id = c.id
            LEFT JOIN procedimientos_especiales pr ON p.procedimiento_id = pr.id
            LEFT JOIN inventario i ON p.producto_id = i.id
            ORDER BY p.id DESC
        """;

        try (Connection conn = ConexionDB.conectar();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                // si tu modelo usa int, obtenemos int; si columna es NULL, rs.getInt da 0 y rs.wasNull() true
                int consultaId = rs.getInt("consulta_id");
                if (rs.wasNull()) consultaId = 0;

                int procedimientoId = rs.getInt("procedimiento_id");
                if (rs.wasNull()) procedimientoId = 0;

                Preescripcion p = new Preescripcion(
                        rs.getInt("id"),
                        consultaId,
                        procedimientoId,
                        rs.getInt("producto_id"),
                        rs.getInt("cantidad"),
                        rs.getString("dosis"),
                        rs.getString("frecuencia"),
                        rs.getInt("duracion_dias"),
                        rs.getString("instrucciones"),
                        rs.getTimestamp("fecha_prescripcion")
                );
                p.setConsulta(rs.getString("consulta"));
                p.setProcedimiento(rs.getString("procedimiento"));
                p.setProducto(rs.getString("producto"));
                lista.add(p);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar prescripciones: " + e.getMessage());
        }
        return lista;
    }

    // =========================
    // MÉTODO: BUSCAR POR ID
    // =========================
    public Preescripcion buscarPorId(int id) {
        String sql = "SELECT * FROM prescripciones WHERE id = ?";
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int consultaId = rs.getInt("consulta_id");
                    if (rs.wasNull()) consultaId = 0;

                    int procedimientoId = rs.getInt("procedimiento_id");
                    if (rs.wasNull()) procedimientoId = 0;

                    return new Preescripcion(
                            rs.getInt("id"),
                            consultaId,
                            procedimientoId,
                            rs.getInt("producto_id"),
                            rs.getInt("cantidad"),
                            rs.getString("dosis"),
                            rs.getString("frecuencia"),
                            rs.getInt("duracion_dias"),
                            rs.getString("instrucciones"),
                            rs.getTimestamp("fecha_prescripcion")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar prescripción: " + e.getMessage());
        }
        return null;
    }

    // =========================
    // MÉTODO: ACTUALIZAR
    // =========================
    public void actualizar(Preescripcion p) {
        String sql = """
            UPDATE prescripciones SET 
                consulta_id=?, procedimiento_id=?, producto_id=?, cantidad=?, 
                dosis=?, frecuencia=?, duracion_dias=?, instrucciones=?, fecha_prescripcion=? 
            WHERE id=?
        """;

        try (Connection conn = ConexionDB.conectar()) {

            // Verificar existencia antes de actualizar (usar >0)
            if (p.getConsultaId() > 0 && !existeRegistro(conn, "consultas_medicas", p.getConsultaId())) {
                System.out.println("El ID de consulta no existe.");
                return;
            }
            if (p.getProcedimientoId() > 0 && !existeRegistro(conn, "procedimientos_especiales", p.getProcedimientoId())) {
                System.out.println("El ID de procedimiento no existe.");
                return;
            }
            if (!existeRegistro(conn, "inventario", p.getProductoId())) {
                System.out.println("El ID del producto no existe.");
                return;
            }

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                if (p.getConsultaId() > 0) ps.setInt(1, p.getConsultaId()); else ps.setNull(1, Types.INTEGER);
                if (p.getProcedimientoId() > 0) ps.setInt(2, p.getProcedimientoId()); else ps.setNull(2, Types.INTEGER);
                ps.setInt(3, p.getProductoId());
                ps.setInt(4, p.getCantidad());
                ps.setString(5, p.getDosis());
                ps.setString(6, p.getFrecuencia());
                ps.setInt(7, p.getDuracionDias());
                ps.setString(8, p.getInstrucciones());
                ps.setTimestamp(9, p.getFechaPrescripcion());
                ps.setInt(10, p.getId());

                ps.executeUpdate();
                System.out.println("Prescripción actualizada correctamente.");
            }

        } catch (SQLException e) {
            System.out.println("Error al actualizar prescripción: " + e.getMessage());
        }
    }

    // =========================
    // MÉTODO: ELIMINAR
    // =========================
    public boolean eliminar(int id) {
        String sql = "DELETE FROM prescripciones WHERE id = ?";
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar prescripción: " + e.getMessage());
        }
        return false;
    }

    // =========================
    // MÉTODO AUXILIAR: EXISTE REGISTRO
    // =========================
    private boolean existeRegistro(Connection conn, String tabla, int id) throws SQLException {
        String sql = "SELECT COUNT(*) FROM " + tabla + " WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }
}
