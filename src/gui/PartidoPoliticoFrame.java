/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

/**
 *
 * @author Usuario
 */

import proyecto_poo.PartidoPolitico;
import proyecto_poo.SistemaVotacion;

import javax.swing.*;
import java.awt.*;

public class PartidoPoliticoFrame extends JFrame {

    private final SistemaVotacion sistema;
    private final JTextArea areaLista;

    public PartidoPoliticoFrame(SistemaVotacion sistema) {
        this.sistema = sistema;

        setTitle("Gestión de Partidos Políticos");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel formulario = new JPanel(new GridLayout(5, 2, 10, 10));
        formulario.setBorder(BorderFactory.createTitledBorder("Registrar Partido"));

        JTextField txtNombre = new JTextField();
        JTextField txtSigla = new JTextField();
        JTextField txtRepresentante = new JTextField();
        JTextField txtSimbolo = new JTextField();
        JLabel lblMensaje = new JLabel();

        formulario.add(new JLabel("Nombre:")); formulario.add(txtNombre);
        formulario.add(new JLabel("Sigla:")); formulario.add(txtSigla);
        formulario.add(new JLabel("Representante Legal:")); formulario.add(txtRepresentante);
        formulario.add(new JLabel("Símbolo/Logo:")); formulario.add(txtSimbolo);
        formulario.add(lblMensaje);

        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.addActionListener(e -> {
            String nombre = txtNombre.getText().trim();
            String sigla = txtSigla.getText().trim();
            String representante = txtRepresentante.getText().trim();
            String simbolo = txtSimbolo.getText().trim();

            if (nombre.isEmpty() || sigla.isEmpty()) {
                lblMensaje.setText("Nombre y sigla son obligatorios.");
                return;
            }

            PartidoPolitico partido = new PartidoPolitico(nombre, sigla, representante, simbolo);
            if (sistema.agregarPartido(partido)) {
                JOptionPane.showMessageDialog(this, "Partido registrado.");
                mostrarPartidos();
                txtNombre.setText(""); txtSigla.setText(""); txtRepresentante.setText(""); txtSimbolo.setText("");
            } else {
                lblMensaje.setText("No se pudo registrar (duplicado o límite alcanzado).");
            }
        });

        formulario.add(new JLabel()); formulario.add(btnRegistrar);
        add(formulario, BorderLayout.NORTH);

        // Lista de partidos
        areaLista = new JTextArea();
        areaLista.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaLista);
        scroll.setBorder(BorderFactory.createTitledBorder("Partidos Registrados"));
        add(scroll, BorderLayout.CENTER);

        // Eliminar
        JPanel panelEliminar = new JPanel(new FlowLayout());
        JTextField txtSiglaEliminar = new JTextField(10);
        JButton btnEliminar = new JButton("Eliminar por sigla");

        btnEliminar.addActionListener(e -> {
            String sigla = txtSiglaEliminar.getText().trim();
            PartidoPolitico partido = sistema.buscarPartidoPorSigla(sigla);
            if (partido != null && sistema.eliminarPartido(partido)) {
                JOptionPane.showMessageDialog(this, "Partido eliminado.");
                mostrarPartidos();
                txtSiglaEliminar.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Partido no encontrado.");
            }
        });

        panelEliminar.add(new JLabel("Sigla:"));
        panelEliminar.add(txtSiglaEliminar);
        panelEliminar.add(btnEliminar);
        add(panelEliminar, BorderLayout.SOUTH);

        mostrarPartidos();
        setVisible(true);
    }

    private void mostrarPartidos() {
        StringBuilder sb = new StringBuilder();
        for (PartidoPolitico p : sistema.getPartidos()) {
            if (p != null) sb.append(p.toString()).append("\n");
        }
        areaLista.setText(sb.toString());
    }
}
