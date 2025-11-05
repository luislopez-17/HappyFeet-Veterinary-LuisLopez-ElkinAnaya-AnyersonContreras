package Controller;

import dao.InsumosProcedimientosDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import model.InsumosProcedimientos;
import utils.ConexionDB;

public class IsumoProcedimientoController {
    private InsumosProcedimientosDAO insumosDao;

    public IsumoProcedimientoController() {
        this.insumosDao = new InsumosProcedimientosDAO();
    }

    public void agregar(Integer procedimientoId, Integer productoId, Integer cantidadUsada, String observaciones) {

        // 🔹 Validaciones
        if (procedimientoId == null || procedimientoId <= 0) {
            System.out.println("Error: el ID del procedimiento es obligatorio y debe ser mayor que 0.");
            return;
        }
        if (productoId == null || productoId <= 0) {
            System.out.println("Error: el ID del producto es obligatorio y debe ser mayor que 0.");
            return;
        }
        if (cantidadUsada == null || cantidadUsada <= 0) {
            System.out.println("Error: la cantidad usada es obligatoria y debe ser mayor que 0.");
            return;
        }

        // 🔹 Crear el objeto modelo
        InsumosProcedimientos insumo = new InsumosProcedimientos(procedimientoId, productoId, cantidadUsada, observaciones);

        try (Connection con = ConexionDB.conectar()) {
            insumosDao.agregar(insumo);
            System.out.println("✅ Insumo del procedimiento agregado correctamente.");
        } catch (SQLException e) {
            System.out.println("❌ Error al agregar el insumo del procedimiento: " + e.getMessage());
        }
    }

    public void actualizar(int id, Integer procedimientoId, Integer productoId, Integer cantidadUsada, String observaciones) {

        // 🔹 Validaciones
        if (id <= 0) {
            System.out.println("Error: ID inválido para actualizar.");
            return;
        }
        if (procedimientoId == null || procedimientoId <= 0) {
            System.out.println("Error: el ID del procedimiento es obligatorio y debe ser mayor que 0.");
            return;
        }
        if (productoId == null || productoId <= 0) {
            System.out.println("Error: el ID del producto es obligatorio y debe ser mayor que 0.");
            return;
        }
        if (cantidadUsada == null || cantidadUsada <= 0) {
            System.out.println("Error: la cantidad usada es obligatoria y debe ser mayor que 0.");
            return;
        }

        // 🔹 Crear objeto modelo
        InsumosProcedimientos insumo = new InsumosProcedimientos(id, procedimientoId, productoId, cantidadUsada, observaciones);

        try {
            insumosDao.actualizar(insumo);
            System.out.println("✅ Insumo del procedimiento actualizado correctamente.");
        } catch (Exception e) {
            System.out.println("❌ Error al actualizar el insumo del procedimiento: " + e.getMessage());
        }
    }

    public void eliminar(int id) {
        if (id <= 0) {
            System.out.println("Error: ID inválido.");
            return;
        }

        InsumosProcedimientos insumo = new InsumosProcedimientos(id, 0, 0, 0, "");
        insumosDao.eliminar(insumo);
    }

    public List<InsumosProcedimientos> listar() {
        return insumosDao.listar();
    }
}


