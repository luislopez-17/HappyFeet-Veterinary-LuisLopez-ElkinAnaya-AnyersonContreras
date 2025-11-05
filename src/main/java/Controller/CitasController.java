/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import dao.CitasDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import model.Citas;
import utils.ConexionDB;

/**
 *
 * @author usuario
 */
public class CitasController {
    private CitasDAO citasDao;
    
    public CitasController(){
        this.citasDao = new CitasDAO();
    }
    
    private boolean validarFormatoFechaHora(String fechaHoraStr) {
        if (fechaHoraStr == null || fechaHoraStr.trim().isEmpty()) {
            System.out.println("Error: fecha y hora son obligatorias.");
            return false;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setLenient(false);
        try {
            sdf.parse(fechaHoraStr);
            return true;
        } catch (ParseException e) {
            System.out.println("Error: formato de fecha y hora inválido. Usa el formato yyyy-MM-dd HH:mm:ss");
            return false;
        }
    }
    
    /** validación para que la fecha sea futura */
    private boolean validarFechaFutura(String fechaHoraStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date fechaIngresada = sdf.parse(fechaHoraStr);
            Date ahora = new Date();

            if (fechaIngresada.before(ahora)) {
                System.out.println("Error: la fecha y hora deben ser posteriores a la actual.");
                return false;
            }
            return true;
        } catch (ParseException e) {
            System.out.println("Error al validar la fecha: " + e.getMessage());
            return false;
        }
    }

    public void agregar(Integer mascotaId, Integer veterinarioId, String fechaHoraStr, String motivo, Integer estadoId, String observaciones) {
        if (mascotaId == null || mascotaId <= 0) {
            System.out.println("Mascota ID es obligatorio y debe ser mayor que 0.");
            return;
        }

        if (estadoId == null || estadoId <= 0) {
            System.out.println("Estado ID es obligatorio y debe ser mayor que 0.");
            return;
        }

        // Validar formato y fecha futura
        if (!validarFormatoFechaHora(fechaHoraStr) || !validarFechaFutura(fechaHoraStr)) {
            return;
        }

        // Convertir la fecha a Timestamp
        Timestamp fechaHora = Timestamp.valueOf(fechaHoraStr);
        Citas c = new Citas(mascotaId, veterinarioId, fechaHora, motivo, estadoId, observaciones, null);

        try (Connection con = ConexionDB.conectar()) {
            citasDao.agregar(c, con, mascotaId, veterinarioId, estadoId);
            System.out.println("Cita agregada correctamente");
        } catch (SQLException e) {
            System.out.println("Error al agregar la cita: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void actualizar(int id, Integer mascotaId, int veterinarioId, String fechaHoraStr, String motivo, Integer estadoId, String observaciones) {
        if (id <= 0) {
            System.out.println("ID inválido para actualizar.");
            return;
        }
        if (mascotaId == null || mascotaId <= 0) {
            System.out.println("Mascota ID es obligatorio y debe ser mayor que 0.");
            return;
        }
        if (estadoId == null || estadoId <= 0) {
            System.out.println("Estado ID es obligatorio y debe ser mayor que 0.");
            return;
        }

        // Validar formato y que la fecha sea futura
        if (!validarFormatoFechaHora(fechaHoraStr) || !validarFechaFutura(fechaHoraStr)) {
            return;
        }

        Timestamp fechaHora = Timestamp.valueOf(fechaHoraStr);
        Citas c = new Citas(id, mascotaId, veterinarioId, fechaHora, motivo, estadoId, observaciones, null);

        try {
            citasDao.actualizar(c, mascotaId, veterinarioId, estadoId);
        } catch (SQLException e) {
            System.out.println("Error al actualizar la cita: " + e.getMessage());
        }
    }


    public void eliminar(int id) {
        if (id <= 0) {
            System.out.println("ID inválido.");
            return;
        }
        // se crea una cita "temporal" solo con el id
        Citas c = new Citas(id, 0, 0, null, "", 0, "", null);
        citasDao.eliminar(c);
    }

    public List<Citas> listar() {
        return citasDao.listar();
    }  
}
