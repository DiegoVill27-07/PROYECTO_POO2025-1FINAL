/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_poo;

/**
 *
 * @author Usuario
 */

import proyecto_poo.Operador;
import proyecto_poo.SistemaVotacion;
import gui.LoginFrame;

public class MainSwing {
    public static void main(String[] args) {
        SistemaVotacion sistema = new SistemaVotacion();

        // Agregar operador por defecto
        Operador admin = new Operador("Juan", "Pérez", "12345678", "juanp", "1234");
        sistema.agregarOperador(admin);

        new LoginFrame(sistema);
    }
}
