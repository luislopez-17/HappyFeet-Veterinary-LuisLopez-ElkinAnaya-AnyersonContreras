/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import model.BeneficiosClub;
import utils.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ELKIN
 */
public class BeneficiosClubDAO {
     // Verificar existencia por ID
    public boolean existePorId(int id) {
        String sql = "SELECT COUNT(*) FROM beneficios_club WHERE id=?";
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt(1) > 0;
            }
        } catch (SQLException ex) {
            System.out.println("Error SQL existePorId: " + ex.getMessage());
        }
        return false;
    }

    // Agregar beneficio
    public void agregar(BeneficiosClub b) {
        String sql = "INSERT INTO beneficios_club(nombre, descripcion, nivel_requerido, puntos_necesarios, tipo_beneficio, valor_beneficio, activo) VALUES (?,?,?,?,?,?,?)";
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, b.getNombre());
            ps.setString(2, b.getDescripcion());
            ps.setString(3, b.getNivelRequerido());
            ps.setInt(4, b.getPuntosNecesarios());
            ps.setString(5, b.getTipoBeneficio().name());
            ps.setDouble(6, b.getValorBeneficio());
            ps.setBoolean(7, b.isActivo());

            int filas = ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) System.out.println("Beneficio agregado con id = " + rs.getInt(1));
            }
            System.out.println("Filas afectadas: " + filas);

        } catch (SQLException ex) {
            System.out.println("Error SQL agregar: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    // Actualizar beneficio
    public void actualizar(BeneficiosClub b) {
        String sql = "UPDATE beneficios_club SET nombre=?, descripcion=?, nivel_requerido=?, puntos_necesarios=?, tipo_beneficio=?, valor_beneficio=?, activo=? WHERE id=?";
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, b.getNombre());
            ps.setString(2, b.getDescripcion());
            ps.setString(3, b.getNivelRequerido());
            ps.setInt(4, b.getPuntosNecesarios());
            ps.setString(5, b.getTipoBeneficio().name());
            ps.setDouble(6, b.getValorBeneficio());
            ps.setBoolean(7, b.isActivo());
            ps.setInt(8, b.getId());

            int filas = ps.executeUpdate();
            if (filas > 0) System.out.println("Beneficio actualizado correctamente.");
            else System.out.println("No se encontró beneficio con id = " + b.getId());

        } catch (SQLException ex) {
            System.out.println("Error SQL actualizar: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    // Eliminar beneficio
    public void eliminar(int id) {
        String sql = "DELETE FROM beneficios_club WHERE id=?";
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            if (filas > 0) System.out.println("Beneficio eliminado correctamente.");
            else System.out.println("No se encontró beneficio con id = " + id);
        } catch (SQLException ex) {
            System.out.println("Error SQL eliminar: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    // Listar todos los beneficios
    public List<BeneficiosClub> listar() {
        List<BeneficiosClub> lista = new ArrayList<>();
        String sql = "SELECT id, nombre, descripcion, nivel_requerido, puntos_necesarios, tipo_beneficio, valor_beneficio, activo FROM beneficios_club";

        try (Connection con = ConexionDB.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                String nivel = rs.getString("nivel_requerido");
                int puntos = rs.getInt("puntos_necesarios");
                BeneficiosClub.TipoBeneficio tipo = BeneficiosClub.TipoBeneficio.valueOf(rs.getString("tipo_beneficio"));
                double valor = rs.getDouble("valor_beneficio");
                boolean activo = rs.getBoolean("activo");

                lista.add(new BeneficiosClub(id, nombre, descripcion, nivel, puntos, tipo, valor, activo));
            }

        } catch (SQLException ex) {
            System.out.println("Error SQL listar: " + ex.getMessage());
            ex.printStackTrace();
        }

        return lista;
    }
}
