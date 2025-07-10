/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

/**
 *
 * @author Usuario
 */

import proyecto_poo.MesaElectoral;
import proyecto_poo.MiembroMesa;
import proyecto_poo.SistemaVotacion;

import javax.swing.*;
import java.awt.*;

public class MesaFrame extends JFrame {

    public MesaFrame(SistemaVotacion sistema) {
        setTitle("Registrar Mesa Electoral");
        setSize(450, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(10, 2, 10, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField txtCodigoMesa = new JTextField();
        JTextField txtLugar = new JTextField();
        JTextField[] nombres = new JTextField[3];
        JTextField[] apellidos = new JTextField[3];
        JTextField[] dnis = new JTextField[3];
        String[] roles = {"Presidente", "Secretario", "Vocal"};
        JLabel lblMensaje = new JLabel();

        panel.add(new JLabel("Código de Mesa:")); panel.add(txtCodigoMesa);
        panel.add(new JLabel("Lugar:")); panel.add(txtLugar);

        for (int i = 0; i < 3; i++) {
            nombres[i] = new JTextField();
            apellidos[i] = new JTextField();
            dnis[i] = new JTextField();
            panel.add(new JLabel(roles[i] + " - Nombre:")); panel.add(nombres[i]);
            panel.add(new JLabel(roles[i] + " - Apellido:")); panel.add(apellidos[i]);
            panel.add(new JLabel(roles[i] + " - DNI:")); panel.add(dnis[i]);
        }

        JButton btnRegistrar = new JButton("Registrar Mesa");
        btnRegistrar.addActionListener(e -> {
            MesaElectoral mesa = new MesaElectoral(txtCodigoMesa.getText().trim(), txtLugar.getText().trim());
            for (int i = 0; i < 3; i++) {
                mesa.agregarMiembro(new MiembroMesa(
                        nombres[i].getText().trim(),
                        apellidos[i].getText().trim(),
                        dnis[i].getText().trim(),
                        roles[i]
                ));
            }

            if (sistema.agregarMesa(mesa)) {
                JOptionPane.showMessageDialog(this, "Mesa registrada correctamente.");
                dispose();
            } else {
                lblMensaje.setText("No se pudo registrar.");
            }
        });

        panel.add(lblMensaje);
        panel.add(btnRegistrar);

        add(panel);
        setVisible(true);
    }
}



