/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_poo;

import java.util.Scanner;

/**
 *
 * @author Usuario
 */

public class SistemaVotacion 
{
     private Operador[] operadores;
    private int cantidadOperadores;

    private Eleccion[] elecciones;
    private int cantidadElecciones;

    private MesaElectoral[] mesas;
    private int cantidadMesas;

    private PartidoPolitico[] partidos;
    private int cantidadPartidos;

    private ActaElectoral[] actas;
    private int cantidadActas;
    
    private MiembroMesa[] miembrosMesa = new MiembroMesa[30];
    private int cantidadMiembros = 0;


    public SistemaVotacion() 
    {
        operadores = new Operador[10];
        cantidadOperadores = 0;

        elecciones = new Eleccion[10];
        cantidadElecciones = 0;

        mesas = new MesaElectoral[10];
        cantidadMesas = 0;

        partidos = new PartidoPolitico[10];
        cantidadPartidos = 0;

        actas = new ActaElectoral[20];
        cantidadActas = 0;
    }

    // Operadores
    public boolean agregarOperador(Operador op) 
    {
        if (cantidadOperadores < operadores.length) 
        {
            operadores[cantidadOperadores++] = op;
            return true;
        }
        return false;
    }

    public Operador autenticarOperador(String usuario, String contrasena) 
    {
        for (int i = 0; i < cantidadOperadores; i++) 
        {
            if (operadores[i].autenticar(usuario, contrasena)) 
            {
                return operadores[i];
            }
        }
        return null;
    }

    // Elecciones
    public boolean agregarEleccion(Eleccion e) 
    {
        if (cantidadElecciones < elecciones.length) 
        {
            elecciones[cantidadElecciones++] = e;
            return true;
        }
        return false;
    }

   public Eleccion buscarEleccionPorTipoYFecha(String tipo, String fecha) {
    tipo = tipo.trim();
    fecha = fecha.trim();

    for (int i = 0; i < cantidadElecciones; i++) {
        if (elecciones[i].getTipo().trim().equalsIgnoreCase(tipo) &&
            elecciones[i].getFecha().trim().equals(fecha)) {
            return elecciones[i];
        }
    }
    return null;
    }


    public boolean eliminarEleccion(Eleccion e) 
    {
        for (int i = 0; i < cantidadElecciones; i++) 
        {
            if (elecciones[i] == e) {
                for (int j = i; j < cantidadElecciones - 1; j++) 
                {
                    elecciones[j] = elecciones[j + 1];
                }
                cantidadElecciones--;
                return true;
            }
        }
        return false;
    }

    // Mesas Electorales
    public boolean agregarMesa(MesaElectoral m) 
    {
        if (cantidadMesas < mesas.length) 
        {
            mesas[cantidadMesas++] = m;
            return true;
        }
        return false;
    }

    public MesaElectoral buscarMesaPorCodigo(String codigo) 
    {
        for (int i = 0; i < cantidadMesas; i++) 
        {
            if (mesas[i].getCodigoMesa().equalsIgnoreCase(codigo)) 
            {
                return mesas[i];
            }
        }
        return null;
    }

    public boolean eliminarMesa(MesaElectoral m) 
    {
        for (int i = 0; i < cantidadMesas; i++) 
        {
            if (mesas[i] == m) 
            {
                for (int j = i; j < cantidadMesas - 1; j++) 
                {
                    mesas[j] = mesas[j + 1];
                }
                cantidadMesas--;
                return true;
            }
        }
        return false;
    }

    // Partidos Políticos
    public boolean agregarPartido(PartidoPolitico p) {
        if (cantidadPartidos < partidos.length) {
            partidos[cantidadPartidos++] = p;
            return true;
        }
        return false;
    }

    public PartidoPolitico buscarPartidoPorSigla(String sigla) {
        for (int i = 0; i < cantidadPartidos; i++) {
            if (partidos[i].getSigla().equalsIgnoreCase(sigla)) {
                return partidos[i];
            }
        }
        return null;
    }

    public boolean eliminarPartido(PartidoPolitico p) {
        for (int i = 0; i < cantidadPartidos; i++) {
            if (partidos[i] == p) {
                for (int j = i; j < cantidadPartidos - 1; j++) {
                    partidos[j] = partidos[j + 1];
                }
                cantidadPartidos--;
                return true;
            }
        }
        return false;
    }

    // Actas Electorales
    public boolean agregarActa(ActaElectoral a) {
        if (cantidadActas < actas.length) {
            actas[cantidadActas++] = a;
            return true;
        }
        return false;
    }
    
    //Miembros de mesa
    public boolean agregarMiembro(MiembroMesa m) {
    if (cantidadMiembros < miembrosMesa.length) {
        miembrosMesa[cantidadMiembros++] = m;
        return true;
    }
    return false;
    }

    public MiembroMesa[] getMiembros() {
    return miembrosMesa;
    }   

    public boolean eliminarMiembroPorDNI(String dni) {
        for (int i = 0; i < cantidadMiembros; i++) {
            if (miembrosMesa[i].getDni().equalsIgnoreCase(dni)) {
                for (int j = i; j < cantidadMiembros - 1; j++) {
                    miembrosMesa[j] = miembrosMesa[j + 1];
                }
                cantidadMiembros--;
                return true;
        }
    }
    return false;
}

    
    public static void main(String[] args) {
        SistemaVotacion sistema = new SistemaVotacion();

        // Crear operador
        Operador op1 = new Operador("Juan", "Perez", "12345678", "juanp", "1234");
        sistema.agregarOperador(op1);

        // Autenticación
        Scanner sc = new Scanner(System.in);
        System.out.print("Usuario: ");
        String user = sc.nextLine();
        System.out.print("Contraseña: ");
        String pass = sc.nextLine();

        Operador usuarioLogueado = sistema.autenticarOperador(user, pass);
        if (usuarioLogueado != null) 
        {
            System.out.println("¡Bienvenido " + usuarioLogueado.getNombreCompleto() + "!");
            
        } 
        else 
        {
            System.out.println("Usuario o contraseña incorrectos.");
        }
        
        sc.close();
    }    

    public ActaElectoral[] getActas() {
    return actas;
}
    public PartidoPolitico[] getPartidos() {
    return partidos;
}

}