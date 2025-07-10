/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

/**
 *
 * @author Usuario
 */


import proyecto_poo.MiembroMesa;
import proyecto_poo.SistemaVotacion;

import javax.swing.*;
import java.awt.*;

public class MiembroMesaFrame extends JFrame {

    private final SistemaVotacion sistema;
    private final JTextArea areaLista;

    public MiembroMesaFrame(SistemaVotacion sistema) {
        this.sistema = sistema;

        setTitle("Gestión de Miembros de Mesa");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel formulario = new JPanel(new GridLayout(5, 2, 10, 10));
        formulario.setBorder(BorderFactory.createTitledBorder("Registrar Miembro"));

        JTextField txtNombre = new JTextField();
        JTextField txtApellido = new JTextField();
        JTextField txtDNI = new JTextField();
        String[] tipos = {"Presidente", "Secretario", "Vocal"};
        JComboBox<String> comboTipo = new JComboBox<>(tipos);
        JLabel lblMensaje = new JLabel();

        formulario.add(new JLabel("Nombre:")); formulario.add(txtNombre);
        formulario.add(new JLabel("Apellido:")); formulario.add(txtApellido);
        formulario.add(new JLabel("DNI:")); formulario.add(txtDNI);
        formulario.add(new JLabel("Tipo de miembro:")); formulario.add(comboTipo);
        formulario.add(lblMensaje);

        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.addActionListener(e -> {
            String nombre = txtNombre.getText().trim();
            String apellido = txtApellido.getText().trim();
            String dni = txtDNI.getText().trim();
            String tipo = comboTipo.getSelectedItem().toString();

            if (nombre.isEmpty() || apellido.isEmpty() || dni.isEmpty()) {
                lblMensaje.setText("Complete todos los campos.");
                return;
            }

            MiembroMesa miembro = new MiembroMesa(nombre, apellido, dni, tipo);
            if (sistema.agregarMiembro(miembro)) {
                JOptionPane.showMessageDialog(this, "Miembro registrado.");
                mostrarMiembros();
                txtNombre.setText(""); txtApellido.setText(""); txtDNI.setText("");
            } else {
                lblMensaje.setText("No se pudo registrar (duplicado o límite alcanzado).");
            }
        });

        formulario.add(new JLabel()); formulario.add(btnRegistrar);
        add(formulario, BorderLayout.NORTH);

        areaLista = new JTextArea();
        areaLista.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaLista);
        scroll.setBorder(BorderFactory.createTitledBorder("Miembros Registrados"));
        add(scroll, BorderLayout.CENTER);

        // Eliminar
        JPanel panelEliminar = new JPanel(new FlowLayout());
        JTextField txtDniEliminar = new JTextField(10);
        JButton btnEliminar = new JButton("Eliminar por DNI");

        btnEliminar.addActionListener(e -> {
            String dni = txtDniEliminar.getText().trim();
            if (sistema.eliminarMiembroPorDNI(dni)) {
                JOptionPane.showMessageDialog(this, "Miembro eliminado.");
                mostrarMiembros();
                txtDniEliminar.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Miembro no encontrado.");
            }
        });

        panelEliminar.add(new JLabel("DNI:"));
        panelEliminar.add(txtDniEliminar);
        panelEliminar.add(btnEliminar);
        add(panelEliminar, BorderLayout.SOUTH);

        mostrarMiembros();
        setVisible(true);
    }

    private void mostrarMiembros() {
        StringBuilder sb = new StringBuilder();
        for (MiembroMesa m : sistema.getMiembros()) {
            if (m != null) sb.append(m.toString()).append("\n");
        }
        areaLista.setText(sb.toString());
    }
}
