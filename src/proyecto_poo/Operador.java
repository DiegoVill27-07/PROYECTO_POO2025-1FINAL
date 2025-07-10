/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_poo;

/**
 *
 * @author Usuario
 */

public class Operador extends Persona implements Autenticable 
{
    private String usuario;
    private String contrasena;

    public Operador(String nombre, String apellido, String dni, String usuario, String contrasena) {
        super(nombre, apellido, dni);
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    @Override
    public boolean autenticar(String usuario, String contrasena) {
        return this.usuario.equals(usuario) && this.contrasena.equals(contrasena);
    }

    public String getUsuario() {
        return usuario;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

}
