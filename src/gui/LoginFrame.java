/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

/**
 *
 * @author Usuario
 */


import proyecto_poo.Operador;
import proyecto_poo.SistemaVotacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginFrame extends JFrame {
    private final SistemaVotacion sistema;

    public LoginFrame(SistemaVotacion sistema) {
        this.sistema = sistema;

        setTitle("Login - Sistema de Votación");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 200);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2, 10, 10));

        JLabel lblUsuario = new JLabel("Usuario:");
        JTextField txtUsuario = new JTextField();

        JLabel lblClave = new JLabel("Contraseña:");
        JPasswordField txtClave = new JPasswordField();

        JButton btnLogin = new JButton("Ingresar");
        JLabel lblMensaje = new JLabel();

        btnLogin.addActionListener((ActionEvent e) -> {
            String user = txtUsuario.getText().trim();
            String pass = new String(txtClave.getPassword()).trim();

            Operador op = sistema.autenticarOperador(user, pass);
            if (op != null) {
                JOptionPane.showMessageDialog(this, "Bienvenido " + op.getNombreCompleto());
                new MenuPrincipalFrame(sistema, op);
                dispose();
            } else {
                lblMensaje.setText("Usuario o clave incorrectos");
            }
        });

        add(lblUsuario);
        add(txtUsuario);
        add(lblClave);
        add(txtClave);
        add(new JLabel());
        add(btnLogin);
        add(lblMensaje);

        setVisible(true);
    }
}



