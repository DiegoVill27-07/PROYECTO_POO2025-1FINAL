/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

/**
 *
 * @author Usuario
 */

import proyecto_poo.Eleccion;
import proyecto_poo.SistemaVotacion;

import javax.swing.*;
import java.awt.*;

public class EleccionFrame extends JFrame {

    private final SistemaVotacion sistema;
    private final JTextField txtTipo, txtFecha;
    private final JLabel lblMensaje;

    public EleccionFrame(SistemaVotacion sistema) {
        this.sistema = sistema;

        setTitle("Gestión de Elecciones");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel form = new JPanel(new GridLayout(3, 2, 10, 10));
        form.setBorder(BorderFactory.createTitledBorder("Datos de la Elección"));

        txtTipo = new JTextField();
        txtFecha = new JTextField();
        lblMensaje = new JLabel();

        form.add(new JLabel("Tipo de elección:")); form.add(txtTipo);
        form.add(new JLabel("Fecha (YYYY-MM-DD):")); form.add(txtFecha);
        form.add(lblMensaje);

        JPanel panelBotones = new JPanel(new FlowLayout());

        JButton btnBuscar = new JButton("Buscar");
        JButton btnRegistrar = new JButton("Registrar");
        JButton btnActualizar = new JButton("Actualizar");
        JButton btnEliminar = new JButton("Eliminar");

        btnBuscar.addActionListener(e -> buscarEleccion());
        btnRegistrar.addActionListener(e -> registrarEleccion());
        btnActualizar.addActionListener(e -> actualizarEleccion());
        btnEliminar.addActionListener(e -> eliminarEleccion());

        panelBotones.add(btnBuscar);
        panelBotones.add(btnRegistrar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);

        add(form, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
        setVisible(true);
    }

    private void buscarEleccion() {
        String tipo = txtTipo.getText().trim();
        String fecha = txtFecha.getText().trim();

        Eleccion eleccion = sistema.buscarEleccionPorTipoYFecha(tipo, fecha);
        if (eleccion != null) {
            lblMensaje.setText("Elección encontrada.");
        } else {
            lblMensaje.setText("Elección no encontrada.");
        }
    }

    private void registrarEleccion() {
        String tipo = txtTipo.getText().trim();
        String fecha = txtFecha.getText().trim();

        if (tipo.isEmpty() || fecha.isEmpty()) {
            lblMensaje.setText("Complete todos los campos.");
            return;
        }

        Eleccion nueva = new Eleccion(tipo, fecha);
        if (sistema.agregarEleccion(nueva)) {
            JOptionPane.showMessageDialog(this, "Elección registrada.");
            limpiarCampos();
        } else {
            lblMensaje.setText("No se pudo registrar (duplicado o límite alcanzado).");
        }
    }

    private void actualizarEleccion() {
        String tipo = txtTipo.getText().trim();
        String fecha = txtFecha.getText().trim();

        Eleccion eleccion = sistema.buscarEleccionPorTipoYFecha(tipo, fecha);
        if (eleccion != null) {
            String nuevoTipo = JOptionPane.showInputDialog(this, "Nuevo tipo:", eleccion.getTipo());
            String nuevaFecha = JOptionPane.showInputDialog(this, "Nueva fecha:", eleccion.getFecha());

            if (nuevoTipo != null && nuevaFecha != null) {
                eleccion.setTipo(nuevoTipo.trim());
                eleccion.setFecha(nuevaFecha.trim());
                JOptionPane.showMessageDialog(this, "Elección actualizada.");
                limpiarCampos();
            }
        } else {
            lblMensaje.setText("Elección no encontrada.");
        }
    }

    private void eliminarEleccion() {
        String tipo = txtTipo.getText().trim();
        String fecha = txtFecha.getText().trim();

        Eleccion eleccion = sistema.buscarEleccionPorTipoYFecha(tipo, fecha);
        if (eleccion != null) {
            if (sistema.eliminarEleccion(eleccion)) {
                JOptionPane.showMessageDialog(this, "Elección eliminada.");
                limpiarCampos();
            } else {
                lblMensaje.setText("No se pudo eliminar.");
            }
        } else {
            lblMensaje.setText("Elección no encontrada.");
        }
    }

    private void limpiarCampos() {
        txtTipo.setText("");
        txtFecha.setText("");
        lblMensaje.setText("");
    }
}
