/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_poo;

/**
 *
 * @author Usuario
 */

public class Eleccion 
{
    private String tipo; // municipal, nacional, referéndum
    private String fecha;
    private Candidato[] candidatos;
    private int cantidadCandidatos;

    public Eleccion(String tipo, String fecha) {
        this.tipo = tipo;
        this.fecha = fecha;
        this.candidatos = new Candidato[10]; // máximo 10 candidatos por elección
        this.cantidadCandidatos = 0;
    }

    public String getTipo() {
        return tipo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public boolean agregarCandidato(Candidato c) 
    {
        if (cantidadCandidatos < candidatos.length) 
        {
            candidatos[cantidadCandidatos] = c;
            cantidadCandidatos++;
            return true;
        } 
        else 
        {
            System.out.println("No se pueden agregar más candidatos.");
            return false;
        }
    }

    public boolean eliminarCandidatoPorDNI(String dni) 
    {
        for (int i = 0; i < cantidadCandidatos; i++) 
        {
            if (candidatos[i].getDni().equals(dni)) 
            {
                for (int j = i; j < cantidadCandidatos - 1; j++) 
                {
                    candidatos[j] = candidatos[j + 1];
                }
                cantidadCandidatos--;
                return true;
            }
        }
        return false;
    }

    public void mostrarCandidatos() 
    {
        for (int i = 0; i < cantidadCandidatos; i++) 
        {
            System.out.println((i + 1) + ". " + candidatos[i]);
        }
    }

    public void votarPorCandidato(int indice) 
    {
        if (indice >= 0 && indice < cantidadCandidatos) 
        {
            candidatos[indice].recibirVoto();
        } 
        else 
        {
            System.out.println("Índice inválido.");
        }
    }

    public void mostrarResultados() 
    {
        System.out.println("Resultados de la elección (" + tipo + " - " + fecha + "):");
        for (int i = 0; i < cantidadCandidatos; i++) 
        {
            System.out.println(candidatos[i]);
        }
    }

    public Candidato[] getCandidatos() {
        return candidatos;
    }

    public int getCantidadCandidatos() {
        return cantidadCandidatos;
    }

}
