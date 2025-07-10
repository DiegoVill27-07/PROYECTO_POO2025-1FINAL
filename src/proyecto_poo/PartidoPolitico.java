/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_poo;

/**
 *
 * @author Usuario
 */

public class PartidoPolitico 
{
    private String nombre;
    private String sigla;
    private String representanteLegal;
    private String simbolo; 

    public PartidoPolitico(String nombre, String sigla, String representanteLegal, String simbolo) {
        this.nombre = nombre;
        this.sigla = sigla;
        this.representanteLegal = representanteLegal;
        this.simbolo = simbolo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getSigla() {
        return sigla;
    }

    public String getRepresentanteLegal() {
        return representanteLegal;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public void setRepresentanteLegal(String representanteLegal) {
        this.representanteLegal = representanteLegal;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    @Override
    public String toString() {
        return sigla + " - " + nombre + " (" + simbolo + "), Representante: " + representanteLegal;
    }
}
