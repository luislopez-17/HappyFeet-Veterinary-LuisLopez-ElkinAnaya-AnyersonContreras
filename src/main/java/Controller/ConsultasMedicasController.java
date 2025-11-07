/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import dao.ConsultaMedicasDAO;
import dao.MascotasDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import model.ConsultaMedica;
import model.Mascotas;
import utils.ConexionDB;

public class ConsultasMedicasController {
    private final ConsultaMedicasDAO consultasDao;

    public ConsultasMedicasController() {
        this.consultasDao = new ConsultaMedicasDAO();
    }

    // Validar formato de fecha
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

    // Validar que la fecha sea futura
    private boolean validarFechaFutura(String fechaHoraStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date fechaIngresada = sdf.parse(fechaHoraStr);
            Date ahora = new Date();

            if (fechaIngresada.before(ahora)) {
                System.out.println("⚠️ Error: la fecha y hora deben ser posteriores a la actual.");
                return false;
            }
            return true;
        } catch (ParseException e) {
            System.out.println("Error al validar la fecha: " + e.getMessage());
            return false;
        }
    }

    // ==========================
    // AGREGAR
    // ==========================
    public void agregar(Integer idMascota, Integer idVeterinario, Integer idCita,
                        String fechaHoraStr, String motivo, String sintomas,
                        String diagnostico, String recomendaciones, String observaciones,
                        double peso, double temperatura) {
        if (idMascota == null || idMascota <= 0) {
            System.out.println("Mascota ID es obligatorio y debe ser mayor que 0.");
            return;
        }
        if (idVeterinario == null || idVeterinario <= 0) {
            System.out.println("Veterinario ID es obligatorio y debe ser mayor que 0.");
            return;
        }
        if (fechaHoraStr == null || fechaHoraStr.trim().isEmpty()) {
            System.out.println("Fecha y hora son obligatorias.");
            return;
        }
        if (motivo == null || motivo.trim().isEmpty()) {
            System.out.println("Motivo es obligatorio.");
            return;
        }

        // Validar formato y fecha futura
        if (!validarFormatoFechaHora(fechaHoraStr) || !validarFechaFutura(fechaHoraStr)) {
            return;
        }

        Timestamp fechaHora = Timestamp.valueOf(fechaHoraStr);

        ConsultaMedica c = new ConsultaMedica(
                idMascota,
                idVeterinario,
                (idCita != null) ? idCita : 0,
                fechaHora,
                motivo,
                sintomas,
                diagnostico,
                recomendaciones,
                observaciones,
                peso,
                temperatura
        );

        try (Connection con = ConexionDB.conectar()) {
            consultasDao.agregar(c, con);
            System.out.println("Consulta médica agregada correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al agregar la consulta: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // ==========================
    // ACTUALIZAR
    // ==========================
    public void actualizar(int id, Integer idMascota, Integer idVeterinario, Integer idCita,
                           String fechaHoraStr, String motivo, String sintomas,
                           String diagnostico, String recomendaciones, String observaciones,
                           double peso, double temperatura) {
        if (id <= 0) {
            System.out.println("ID inválido para actualizar.");
            return;
        }
        if (idMascota == null || idMascota <= 0) {
            System.out.println("Mascota ID es obligatorio y debe ser mayor que 0.");
            return;
        }
        if (idVeterinario == null || idVeterinario <= 0) {
            System.out.println("Veterinario ID es obligatorio y debe ser mayor que 0.");
            return;
        }
        if (fechaHoraStr == null || fechaHoraStr.trim().isEmpty()) {
            System.out.println("Fecha y hora son obligatorias.");
            return;
        }
        if (motivo == null || motivo.trim().isEmpty()) {
            System.out.println("Motivo es obligatorio.");
            return;
        }

        // Validar formato y fecha futura
        if (!validarFormatoFechaHora(fechaHoraStr) || !validarFechaFutura(fechaHoraStr)) {
            return;
        }

        Timestamp fechaHora = Timestamp.valueOf(fechaHoraStr);

        ConsultaMedica c = new ConsultaMedica(id,idMascota,idVeterinario,(idCita != null) ? idCita : 0,fechaHora,motivo,sintomas,diagnostico,recomendaciones,observaciones,peso,temperatura);

        consultasDao.actualizar(c);
    }

    public void eliminar(int id) {
        if (id <= 0) {
            System.out.println("ID inválido.");
            return;
        }

        ConsultaMedica c = new ConsultaMedica(
                id,
                0, 0, 0, null, "", "", "", "", "", 0.0, 0.0
        );

        consultasDao.eliminar(c);
    }

    public List<ConsultaMedica> listar() {
        return consultasDao.listar();
    }
    
    public String generarReportePeso(int mascotaId) {
        try (Connection con = ConexionDB.conectar()) {

            // 1️⃣ Verificar si la mascota existe
            MascotasDAO mascotaDAO = new MascotasDAO();
            Mascotas mascota = mascotaDAO.buscarPorId(con, mascotaId);

            if (mascota == null) {
                return "Error: No se encontró ninguna mascota con el ID proporcionado.";
            }

            // 2️⃣ Consultar historial de pesos
            ConsultaMedicasDAO dao = new ConsultaMedicasDAO();
            List<ConsultaMedica> lista = dao.obtenerHistorialPesoPorMascota(con, mascotaId);

            if (lista.isEmpty()) {
                return "No hay registros de peso para la mascota '" + mascota.getNombre() + "'.";
            }

            // 3️⃣ Construir el reporte
            StringBuilder sb = new StringBuilder();
            sb.append("+++++++++++++++++++++++++++++++++++++++++++++\n");
            sb.append("HISTORIAL DE PESO DEL PACIENTE\n");
            sb.append("+++++++++++++++++++++++++++++++++++++++++++++\n\n");
            sb.append("DATOS DEL PACIENTE\n");
            sb.append("Nombre:   ").append(mascota.getNombre()).append("\n");
            sb.append("Especie:  ").append(mascota.getNombre()).append("\n");
            sb.append("Raza:     ").append(mascota.getRazaid()).append("\n\n");
            sb.append("REGISTRO DE PESOS\n");
            sb.append("Fecha Consulta\t\t| Peso Registrado\n");
            sb.append("-----------------------------------------\n");

            double ultimo = 0;
            for (ConsultaMedica c : lista) {
                sb.append(String.format("%s | %.2f kg%n", c.getFechaHora(), c.getPesoRegistrado()));
                ultimo = c.getPesoRegistrado();
            }

            sb.append(String.format("%n>> Último peso registrado: %.2f kg%n", ultimo));
            return sb.toString();

        } catch (SQLException e) {
            return "Error al conectar o consultar la base de datos: " + e.getMessage();
        }
    }
}
