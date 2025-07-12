/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_poo;

import java.util.Scanner;

/**
 *
 * @author dievilla0727
 */
public class MainConsola {
  
    
     public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SistemaVotacion sistema = new SistemaVotacion();

        // Registro inicial de operador
        System.out.println("=== Registro de Operador Inicial ===");
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Apellido: ");
        String apellido = sc.nextLine();
        System.out.print("DNI: ");
        String dni = sc.nextLine();
        System.out.print("Usuario: ");
        String user = sc.nextLine();
        System.out.print("Password: ");
        String pass = sc.nextLine();

        sistema.agregarOperador(new Operador(nombre, apellido, dni, user, pass));

        // Login con reintentos
        Operador operador = null;
        System.out.println("\n=== Iniciar sesion ===");
        while (operador == null) {
            System.out.print("Usuario: ");
            String usuarioLogin = sc.nextLine();
            System.out.print("Password: ");
            String claveLogin = sc.nextLine();

            operador = sistema.autenticarOperador(usuarioLogin, claveLogin);

            if (operador == null) {
                System.out.println("Acceso denegado. Usuario o password incorrectos. Intente nuevamente.\n");
            }
        }

        System.out.println("Bienvenido, " + operador.getNombreCompleto());

        int opcion;
        do {
            System.out.println("\n=== Menu Principal ===");
            System.out.println("1. Registrar eleccion");
            System.out.println("2. Buscar eleccion");
            System.out.println("3. Eliminar eleccion");
            System.out.println("4. Registrar candidato");
            System.out.println("5. Mostrar resultados");
            System.out.println("6. Registrar mesa electoral");
            System.out.println("7. Registrar acta electoral");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1 -> {
                    System.out.print("Tipo: ");
                    String tipo = sc.nextLine();
                    System.out.print("Fecha: ");
                    String fecha = sc.nextLine();
                    if (sistema.agregarEleccion(new Eleccion(tipo, fecha))) {
                        System.out.println("Eleccion registrada.");
                    } else {
                        System.out.println("Error al registrar eleccion.");
                    }
                }
                case 2 -> {
                    System.out.print("Tipo: ");
                    String tipo = sc.nextLine();
                    System.out.print("Fecha: ");
                    String fecha = sc.nextLine();
                    Eleccion e = sistema.buscarEleccionPorTipoYFecha(tipo, fecha);
                    if (e != null) {
                        System.out.println("Eleccion encontrada.");
                        e.mostrarCandidatos();
                    } else {
                        System.out.println("No se encontro la eleccion.");
                    }
                }
                case 3 -> {
                    System.out.print("Tipo: ");
                    String tipo = sc.nextLine();
                    System.out.print("Fecha: ");
                    String fecha = sc.nextLine();
                    Eleccion e = sistema.buscarEleccionPorTipoYFecha(tipo, fecha);
                    if (e != null && sistema.eliminarEleccion(e)) {
                        System.out.println("Eleccion eliminada.");
                    } else {
                        System.out.println("No se pudo eliminar.");
                    }
                }
                case 4 -> {
                    System.out.print("Tipo de eleccion: ");
                    String tipo = sc.nextLine();
                    System.out.print("Fecha: ");
                    String fecha = sc.nextLine();
                    Eleccion e = sistema.buscarEleccionPorTipoYFecha(tipo, fecha);
                    if (e == null) {
                        System.out.println("Eleccion no encontrada.");
                        break;
                    }
                    System.out.print("Nombre: ");
                    String nom = sc.nextLine();
                    System.out.print("Apellido: ");
                    String ape = sc.nextLine();
                    System.out.print("DNI: ");
                    String dniC = sc.nextLine();
                    System.out.print("Partido: ");
                    String partido = sc.nextLine();
                    Candidato c = new Candidato(nom, ape, dniC, partido);
                    if (e.agregarCandidato(c)) {
                        System.out.println("Candidato agregado.");
                    } else {
                        System.out.println("No se pudo agregar.");
                    }
                }
                case 5 -> {
                    System.out.print("Tipo de eleccion: ");
                    String tipo = sc.nextLine();
                    System.out.print("Fecha: ");
                    String fecha = sc.nextLine();
                    Eleccion e = sistema.buscarEleccionPorTipoYFecha(tipo, fecha);
                    if (e != null) {
                        e.mostrarResultados();
                    } else {
                        System.out.println("Eleccion no encontrada.");
                    }
                }
                case 6 -> {
                    System.out.print("Codigo de mesa: ");
                    String codigo = sc.nextLine();
                    System.out.print("Lugar: ");
                    String lugar = sc.nextLine();
                    MesaElectoral m = new MesaElectoral(codigo, lugar);
                    for (int i = 0; i < 3; i++) {
                        System.out.print("Nombre del miembro " + (i + 1) + ": ");
                        String n = sc.nextLine();
                        System.out.print("Apellido: ");
                        String a = sc.nextLine();
                        System.out.print("DNI: ");
                        String d = sc.nextLine();
                        String rol = (i == 0) ? "Presidente" : (i == 1) ? "Secretario" : "Vocal";
                        m.agregarMiembro(new MiembroMesa(n, a, d, rol));
                    }
                    sistema.agregarMesa(m);
                    System.out.println("Mesa registrada.");
                }
                case 7 -> {
                    System.out.print("Tipo de eleccion: ");
                    String tipo = sc.nextLine();
                    System.out.print("Fecha: ");
                    String fecha = sc.nextLine();
                    Eleccion e = sistema.buscarEleccionPorTipoYFecha(tipo, fecha);
                    if (e == null) {
                        System.out.println("Eleccion no encontrada.");
                        break;
                    }

                    System.out.print("Codigo de mesa: ");
                    String cod = sc.nextLine();
                    MesaElectoral m = sistema.buscarMesaPorCodigo(cod);
                    if (m == null) {
                        System.out.println("Mesa no encontrada.");
                        break;
                    }

                    System.out.print("Numero de acta: ");
                    String nro = sc.nextLine();
                    System.out.print("Titulo: ");
                    String titulo = sc.nextLine();
                    System.out.print("Hora: ");
                    String hora = sc.nextLine();
                    ActaElectoral acta = new ActaElectoral(nro, titulo, fecha, hora, m.getLugar(), m, e);

                    int[] votos = new int[e.getCantidadCandidatos()];
                    for (int i = 0; i < e.getCantidadCandidatos(); i++) {
                        System.out.print("Votos para " + e.getCandidatos()[i].getNombreCompleto() + ": ");
                        votos[i] = Integer.parseInt(sc.nextLine());
                    }

                    System.out.print("Votos en blanco: ");
                    int blancos = Integer.parseInt(sc.nextLine());
                    System.out.print("Votos nulos: ");
                    int nulos = Integer.parseInt(sc.nextLine());
                    System.out.print("Votantes registrados: ");
                    int registrados = Integer.parseInt(sc.nextLine());

                    acta.registrarVotos(votos, blancos, nulos, registrados);
                    acta.setSello("Sello Oficial");
                    acta.setObservaciones("Sin novedades");
                    for (int i = 0; i < 3; i++) {
                        System.out.print("Firma " + (i + 1) + ": ");
                        acta.agregarFirma(sc.nextLine());
                    }

                    sistema.agregarActa(acta);
                    System.out.println("Acta registrada.");
                    acta.mostrarActaResumen();
                }
                case 0 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opcion invalida");
            }

        } while (opcion != 0);

        sc.close();
    }
    
}
