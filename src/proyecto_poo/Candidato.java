/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_poo;

/**
 *
 * @author Usuario
 */
public class Candidato extends Persona implements Votable
{
    private String partidoPolitico;
    private int cantidadVotos;

    public Candidato(String nombre, String apellido, String dni, String partidoPolitico) {
        super(nombre, apellido, dni);
        this.partidoPolitico = partidoPolitico;
        this.cantidadVotos = 0;
    }

    public String getPartidoPolitico() {
        return partidoPolitico;
    }

    public void setPartidoPolitico(String partidoPolitico) {
        this.partidoPolitico = partidoPolitico;
    }

    @Override
    public void recibirVoto() {
        cantidadVotos++;
    }

    @Override
    public int getCantidadVotos() {
        return cantidadVotos;
    }

    public void reiniciarVotos() {
        this.cantidadVotos = 0;
    }

    @Override
    public String toString() {
        return getNombreCompleto() + " - " + partidoPolitico + " | Votos: " + cantidadVotos;
    }
    
}
