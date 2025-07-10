/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_poo;

/**
 *
 * @author Usuario
 */

public class MesaElectoral 
{
    private String codigoMesa;
    private String lugar;
    private MiembroMesa[] miembros;
    private int cantidadMiembros;

    public MesaElectoral(String codigoMesa, String lugar) {
        this.codigoMesa = codigoMesa;
        this.lugar = lugar;
        this.miembros = new MiembroMesa[3]; // Presidente, secretario, vocal
        this.cantidadMiembros = 0;
    }

    public String getCodigoMesa() {
        return codigoMesa;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public boolean agregarMiembro(MiembroMesa miembro) 
    {
        if (cantidadMiembros < miembros.length) 
        {
            miembros[cantidadMiembros] = miembro;
            cantidadMiembros++;
            return true;
        } 
        else 
        {
            System.out.println("La mesa ya tiene 3 miembros.");
            return false;
        }
    }

    public void mostrarMiembros() {
        System.out.println("Miembros de la mesa " + codigoMesa + ":");
        for (int i = 0; i < cantidadMiembros; i++) 
        {
            System.out.println("- " + miembros[i]);
        }
    }

    @Override
    public String toString() {
        return "Mesa " + codigoMesa + " - Lugar: " + lugar;
    }

}
