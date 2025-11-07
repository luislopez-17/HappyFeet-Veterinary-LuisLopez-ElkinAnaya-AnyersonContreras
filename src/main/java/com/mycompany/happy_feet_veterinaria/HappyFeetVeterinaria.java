/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.happy_feet_veterinaria;

import Vistas.AdopcionVIEW;
import Vistas.BeneficiosClubVIEW;
import Vistas.CanjesBeneficiosVIEW;
import Vistas.CitaEstadosVIEW;
import Vistas.CitasVIEW;
import Vistas.ClubMascotasVIEW;
import Vistas.ConsultasMedicasVIEW;
import Vistas.DuenosVIEW;
import Vistas.EspecieVIEW;
import Vistas.EventoTipoVIEW;
import Vistas.FacturaVIEW;
import Vistas.HistorialVIEW;
import Vistas.InsumoProcedimientoVIEW;
import Vistas.InventarioVIEW;
import Vistas.ItemFacturaVIEW;
import Vistas.JornadaVacunacionVIEW;
import Vistas.MascotaAdopcionVIEW;
import Vistas.MascotasVIEW;
import Vistas.MovimientosInventarioVIEW;
import Vistas.PreescripcionVIEW;
import Vistas.ProcedimientosEspecialesVIEW;
import Vistas.ProductoTiposVIEW;
import Vistas.ProveedoresVIEW;
import Vistas.RazasVIEW;
import Vistas.RegistroJornadaVacunacionVIEW;
import Vistas.ServiciosVIEW;
import Vistas.TransaccionesPuntosVIEW;
import Vistas.VeterinariosVIEW;
import java.util.Scanner;


/**
 *
 * @author usuario
 */
public class HappyFeetVeterinaria {
    private static final Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        int opcion;
        EspecieVIEW vistaEspecie = new EspecieVIEW();
        RazasVIEW vistaRaza = new RazasVIEW();
        ProductoTiposVIEW vistaProductoT = new ProductoTiposVIEW();
        EventoTipoVIEW vistaEventoT = new EventoTipoVIEW();
        CitaEstadosVIEW vistaCitaE = new CitaEstadosVIEW();
        DuenosVIEW vistaDuenos = new DuenosVIEW();
        MascotasVIEW vistaMascotas = new MascotasVIEW();
        VeterinariosVIEW vistaVeterinarios = new VeterinariosVIEW();
        CitasVIEW vistaCita = new CitasVIEW();
        ConsultasMedicasVIEW vistaConsultas = new ConsultasMedicasVIEW();
        ProcedimientosEspecialesVIEW vistaProcedimientos = new ProcedimientosEspecialesVIEW();
        HistorialVIEW vistaHistorial = new HistorialVIEW();
        ProveedoresVIEW vistaProveedores = new ProveedoresVIEW();
        InventarioVIEW vistaInventario = new InventarioVIEW();
        PreescripcionVIEW VistaPreescripcion = new PreescripcionVIEW();
        InsumoProcedimientoVIEW VistaInsumoProcedimientos = new InsumoProcedimientoVIEW();
        MovimientosInventarioVIEW VistaMovimientosInventario = new MovimientosInventarioVIEW();
        ServiciosVIEW VistaServicios = new ServiciosVIEW();
        FacturaVIEW VistaFactura = new FacturaVIEW();
        ItemFacturaVIEW VistaItemFactura = new ItemFacturaVIEW();
        MascotaAdopcionVIEW VistaMascotaAdopcion = new MascotaAdopcionVIEW();
        AdopcionVIEW VistaAdopcion = new AdopcionVIEW();
        JornadaVacunacionVIEW  VistaJornadaVacunacion = new JornadaVacunacionVIEW();
        RegistroJornadaVacunacionVIEW VistaRegistroJornadaVacunacion =new RegistroJornadaVacunacionVIEW();
        ClubMascotasVIEW VistaClubMascotas = new ClubMascotasVIEW();
        TransaccionesPuntosVIEW VistaTransaccionesPuntos = new TransaccionesPuntosVIEW();
        BeneficiosClubVIEW VistaBeneficiosClub = new BeneficiosClubVIEW();
        CanjesBeneficiosVIEW VistaCanjesBeneficios = new CanjesBeneficiosVIEW();
        
        do {
            System.out.println("\n========= MENÚ PRINCIPAL =========");
            System.out.println("1. Gestionar especies");
            System.out.println("2. Gestionar razas");
            System.out.println("3. Gestionar producto tipos");
            System.out.println("4. Gestionar Evento Tipos");
            System.out.println("5. Gestionar Cita Estados");
            System.out.println("6. Gestionar Dueños");
            System.out.println("7. Gestionar mascotas");
            System.out.println("8. Gestionar veterinarios");
            System.out.println("9. Gestionar Citas");
            System.out.println("10. Gestionar consultas citas");
            System.out.println("11. Gestionar Procedimientos especiales");
            System.out.println("12. Gestionar Historial medico");
            System.out.println("13. Gestionar Proveedores");
            System.out.println("14. Gestionar Inventario");
            System.out.println("15. Preescripcion");
            System.out.println("16. Gestionar insumos");
            System.out.println("17. MovimientosInventario");
            System.out.println("18. Servicios");
            System.out.println("19. Factura");
            System.out.println("20. ItemFactura");
            System.out.println("21. Adoptar Mascota");
            System.out.println("22. Adopciones");
            System.out.println("23. Jornada Vacunacion");
            System.out.println("24. Registro jornada Vacunacion");
            System.out.println("25. Club Mascotas");
            System.out.println("26. Transacciones Puntos");
            System.out.println("27. Beneficios club");
            System.out.println("28. Canjeos Beneficios");
            System.out.println("0. Salir");
                    
            System.out.print("Seleccione una opción: ");
            opcion = leerEntero();

            switch (opcion) {
                case 1 -> vistaEspecie.mostrarMenu();
                case 2 -> vistaRaza.mostrarMenu();
                case 3 -> vistaProductoT.mostrarMenu();
                case 4 -> vistaEventoT.mostrarMenu();
                case 5 -> vistaCitaE.mostrarMenu();
                case 6 -> vistaDuenos.mostrarMenu();
                case 7 -> vistaMascotas.mostrarMenu();
                case 8 -> vistaVeterinarios.mostrarMenu();
                case 9 -> vistaCita.mostrarMenu();
                case 10 -> vistaConsultas.mostrarMenu();
                case 11 -> vistaProcedimientos.mostrarMenu();
                case 12 -> vistaHistorial.mostrarMenu();
                case 13 -> vistaProveedores.mostrarMenu();
                case 14 -> vistaInventario.mostrarMenu();
                case 15-> VistaPreescripcion.mostrarMenu();
                case 16 -> VistaInsumoProcedimientos.mostrarMenu();
                case 17 -> VistaMovimientosInventario.mostrarMenu();
                case 18 -> VistaServicios.mostrarMenu();
                case 19 -> VistaFactura.mostrarMenu();
                case 20 -> VistaItemFactura.menu();
                case 21 -> VistaMascotaAdopcion.mostrarMenu();
                case 22 -> VistaAdopcion.mostrarMenu();
                case 23 -> VistaJornadaVacunacion.mostrarMenu();
                case 24 -> VistaRegistroJornadaVacunacion.mostrarMenu();
                case 25 ->  VistaClubMascotas.mostrarMenu();
                case 26 -> VistaTransaccionesPuntos.mostrarMenu();
                case 27 -> VistaBeneficiosClub.mostrarMenu();
                case 28 -> VistaCanjesBeneficios.mostrarMenu(); 
                case 0 -> System.out.println("Cerrando el sistema...");
                default -> System.out.println("Opción inválida.");
            }

        } while (opcion != 0);
    }

    private static int leerEntero() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Ingrese un número válido: ");
            }
        }
    }
}

