/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author usuario
 */
import java.util.Date;

public class CanjesBeneficios {

    public enum Estado {
        Pendiente,
        Aplicado,
        Expirado
    }

    private int id;
    private int clubMascotasId;
    private int beneficioId;
    private Date fechaCanje;
    private int puntosCanjeados;
    private Estado estado;
    private Date fechaExpiracion;
    private int facturaId;

    public CanjesBeneficios(int id, int clubMascotasId, int beneficioId, Date fechaCanje,
                            int puntosCanjeados, Estado estado, Date fechaExpiracion, int facturaId) {
        this.id = id;
        this.clubMascotasId = clubMascotasId;
        this.beneficioId = beneficioId;
        this.fechaCanje = fechaCanje;
        this.puntosCanjeados = puntosCanjeados;
        this.estado = estado;
        this.fechaExpiracion = fechaExpiracion;
        this.facturaId = facturaId;
    }

    public CanjesBeneficios(int clubMascotasId, int beneficioId, Date fechaCanje,
                            int puntosCanjeados, Estado estado, Date fechaExpiracion, int facturaId) {
        this.clubMascotasId = clubMascotasId;
        this.beneficioId = beneficioId;
        this.fechaCanje = fechaCanje;
        this.puntosCanjeados = puntosCanjeados;
        this.estado = estado;
        this.fechaExpiracion = fechaExpiracion;
        this.facturaId = facturaId;
    }

    public int getId() { return id; }
    public int getClubMascotasId() { return clubMascotasId; }
    public int getBeneficioId() { return beneficioId; }
    public Date getFechaCanje() { return fechaCanje; }
    public int getPuntosCanjeados() { return puntosCanjeados; }
    public Estado getEstado() { return estado; }
    public Date getFechaExpiracion() { return fechaExpiracion; }
    public int getFacturaId() { return facturaId; }

    public String toString() {
        return "Canje Beneficio{" +
                "id=" + id +
                ", club_mascotas_id=" + clubMascotasId +
                ", beneficio_id=" + beneficioId +
                ", fecha_canje=" + fechaCanje +
                ", puntos_canjeados=" + puntosCanjeados +
                ", estado=" + estado +
                ", fecha_expiracion=" + fechaExpiracion +
                ", factura_id=" + facturaId +
                '}';
    }
}