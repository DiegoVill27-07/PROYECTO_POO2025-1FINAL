/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_poo;

/**
 *
 * @author Usuario
 */

public class MiembroMesa  extends Persona 
{
    private String tipoMiembro; // presidente, secretario, vocal

    public MiembroMesa(String nombre, String apellido, String dni, String tipoMiembro) {
        super(nombre, apellido, dni);
        this.tipoMiembro = tipoMiembro;
    }

    public String getTipoMiembro() {
        return tipoMiembro;
    }

    public void setTipoMiembro(String tipoMiembro) {
        this.tipoMiembro = tipoMiembro;
    }

    @Override
    public String toString() {
        return tipoMiembro.toUpperCase() + ": " + getNombreCompleto() + " (DNI: " + dni + ")";
    }
}
