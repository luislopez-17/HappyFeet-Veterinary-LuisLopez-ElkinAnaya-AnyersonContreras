    /*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
     */
    package model;

    import java.sql.Date;
    import java.sql.Timestamp;

    /**
     *
     * @author usuario
     */
    public class ProcedimientosEspeciales {
        public enum Estado{
            Programado,
            EnProceso,
            Finalizado,
            Cancelado
        }

        private int id;
        private int idMascotas;
        private int idVeterinario;
        private String tipoProcedimiento;
        private String nombreProcedimiento;
        private  Timestamp fechaHora;
        private int duracionMinutos;
        private String informacionPreoperatoria;
        private String detalleProcedimiento;
        private String complicaciones;
        private String seguimientoPostoperatorio;
        private Date proximoControl;
        private Estado estado;
        private double costo;

        private String mascota;
        private String veterinario;

        public ProcedimientosEspeciales(int id, int idMascotas, int idVeterinario, String tipoProcedimiento, String nombreProcedimiento, Timestamp fechaHora, int duracionMinutos,
               String informacionPreoperatoria, String detalleProcedimientos, String complicaciones, String seguimientoPostoperatorio, Date proximoControl, Estado estado, double costo){
            this.id = id;
            this.idMascotas = idMascotas;
            this.idVeterinario = idVeterinario;
            this.tipoProcedimiento = tipoProcedimiento;
            this.nombreProcedimiento = nombreProcedimiento;
            this.fechaHora = fechaHora;
            this.duracionMinutos = duracionMinutos;
            this.detalleProcedimiento = detalleProcedimientos;
            this.informacionPreoperatoria = informacionPreoperatoria;
            this.complicaciones = complicaciones;
            this.seguimientoPostoperatorio = seguimientoPostoperatorio;
            this.proximoControl = proximoControl;
            this.estado = estado;
            this.costo = costo;
        }

        public ProcedimientosEspeciales(int idMascotas, int idVeterinario, String tipoProcedimiento, String nombreProcedimiento, Timestamp fechaHora, int duracionMinutos,
               String informacionPreoperatoria, String detalleProcedimientos, String complicaciones, String seguimientoPostoperatorio, Date proximoControl, Estado estado, double costo){
            this.idMascotas = idMascotas;
            this.idVeterinario = idVeterinario;
            this.tipoProcedimiento = tipoProcedimiento;
            this.nombreProcedimiento = nombreProcedimiento;
            this.fechaHora = fechaHora;
            this.duracionMinutos = duracionMinutos;
            this.detalleProcedimiento = detalleProcedimientos;
            this.informacionPreoperatoria = informacionPreoperatoria;
            this.complicaciones = complicaciones;
            this.seguimientoPostoperatorio = seguimientoPostoperatorio;
            this.proximoControl = proximoControl;
            this.estado = estado;
            this.costo = costo;
        }

        public int getId(){return id;}
        public int getIdMascotas(){return idMascotas;}
        public int getIdVeterinario(){return idVeterinario;}
        public String getTipoProcedimiento(){return tipoProcedimiento;}
        public String getNombreProcedimiento(){return nombreProcedimiento;}
        public Timestamp getFechaHora(){return fechaHora;}
        public int getDuracionMinutos(){return duracionMinutos;}
        public String getInformacionPreoperatoria(){return informacionPreoperatoria;}
        public String getDetalleProcedimientos(){return detalleProcedimiento;}
        public String getComplicaciones(){return complicaciones;}
        public String getSeguimiento(){return seguimientoPostoperatorio;}
        public Date getProximoControl(){return proximoControl;}
        public Estado getEstado(){return estado;}
        public double getCosto(){return costo;}

        public void setMascota(String mascota){this.mascota = mascota;}
        private void setVeterinario(String veterinario){this.veterinario = veterinario;}


        public String toString(){
            String nombreMascota = (this.mascota != null) ? this.mascota : "Mascota#" + idMascotas;
            String nombreVeterinario = (this.veterinario != null) ? this.veterinario : "Veterinario#" + idVeterinario;
            return "Procedimientos especiales{id = " + id + ", mascota = " + nombreMascota + ", veterinario = " + nombreVeterinario + ", tipo procedimienti = " + tipoProcedimiento +
                    ", nombre porcedimiento = " + nombreProcedimiento + "fecha y hora = " + fechaHora + ", duracion minutos = " + duracionMinutos + ", informacion preoperatoria = " 
                    + informacionPreoperatoria + ", complicaciones = " + complicaciones + ", seguimiento = " + seguimientoPostoperatorio + ", proximo control = " + proximoControl
                    + ", estado = " + estado + ", costo = " + costo + "}";             
        }
    }
