/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_poo;

/**
 *
 * @author Usuario
 */

public class ActaElectoral 
{
    private String numeroActa;
    private String titulo;
    private String fecha;
    private String hora;
    private String lugar;
    private MesaElectoral mesa;
    private Eleccion eleccion;
    private String[] firmas; // máximo 3 firmas
    private String sello;
    private String observaciones;
    private int votantesRegistrados;
    private int votantesEfectivos;
    private int votosEnBlanco;
    private int votosNulos;

    public ActaElectoral(String numeroActa, String titulo, String fecha, String hora, String lugar, MesaElectoral mesa, Eleccion eleccion) {
        this.numeroActa = numeroActa;
        this.titulo = titulo;
        this.fecha = fecha;
        this.hora = hora;
        this.lugar = lugar;
        this.mesa = mesa;
        this.eleccion = eleccion;
        this.firmas = new String[3];
        this.votosEnBlanco = 0;
        this.votosNulos = 0;
        this.votantesRegistrados = 0;
        this.votantesEfectivos = 0;
    }

    public void agregarFirma(String nombre) 
    {
        for (int i = 0; i < firmas.length; i++) 
        {
            if (firmas[i] == null) 
            {
                firmas[i] = nombre;
                return;
            }
        }
        System.out.println("Ya se registraron todas las firmas.");
    }

    public void registrarVotos(int[] votosPorCandidato, int votosEnBlanco, int votosNulos, int votantesRegistrados) 
    {
        if (votosPorCandidato.length != eleccion.getCantidadCandidatos()) 
        {
            System.out.println("Error: cantidad de votos no coincide con los candidatos.");
            return;
        }

        // Asignar votos a los candidatos
        for (int i = 0; i < votosPorCandidato.length; i++) 
        {
            for (int j = 0; j < votosPorCandidato[i]; j++) 
            {
                eleccion.votarPorCandidato(i);
            }
        }

        this.votosEnBlanco = votosEnBlanco;
        this.votosNulos = votosNulos;
        this.votantesRegistrados = votantesRegistrados;
        this.votantesEfectivos = calcularVotantesEfectivos(votosPorCandidato, votosEnBlanco, votosNulos);
    }

    private int calcularVotantesEfectivos(int[] votosPorCandidato, int blancos, int nulos) 
    {
        int total = blancos + nulos;
        for (int v : votosPorCandidato) 
        {
            total += v;
        }
        return total;
    }

    public void setObservaciones(String obs) 
    {
        this.observaciones = obs;
    }

    public void setSello(String sello) 
    {
        this.sello = sello;
    }

    public void mostrarActaResumen() 
    {
        System.out.println("----- ACTA ELECTORAL Nº " + numeroActa + " -----");
        System.out.println("Título: " + titulo);
        System.out.println("Fecha: " + fecha + "  Hora: " + hora);
        System.out.println("Lugar: " + lugar);
        System.out.println(mesa);
        mesa.mostrarMiembros();
        eleccion.mostrarResultados();
        System.out.println("Votos en blanco: " + votosEnBlanco);
        System.out.println("Votos nulos: " + votosNulos);
        System.out.println("Total votantes registrados: " + votantesRegistrados);
        System.out.println("Total votos efectivos: " + votantesEfectivos);
        System.out.println("Observaciones: " + observaciones);
        System.out.println("Sello oficial: " + sello);
        System.out.println("Firmas:");
        for (String firma : firmas) 
        {
            if (firma != null) System.out.println(" - " + firma);
        }
    }

public Eleccion getEleccion() {
    return eleccion;
}

       
  public int getVotosEnBlanco() {
    return votosEnBlanco;
}

public int getVotosNulos() {
    return votosNulos;
}

public int getVotantesRegistrados() {
    return votantesRegistrados;
}

public int getVotantesEfectivos() {
    return votantesEfectivos;
}

public String getNumeroActa() {
    return numeroActa;
}

public MesaElectoral getMesa() {
    return mesa;
}

}
