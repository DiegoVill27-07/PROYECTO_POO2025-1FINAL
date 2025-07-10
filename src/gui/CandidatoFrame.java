/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

/**
 *
 * @author Usuario
 */

import proyecto_poo.*;

import javax.swing.*;
import java.awt.*;

public class CandidatoFrame extends JFrame {

    private final SistemaVotacion sistema;
    private final JTextField txtNombre, txtApellido, txtDNI, txtPartido, txtTipoEleccion, txtFechaEleccion;
    private final JLabel lblMensaje;
    private Eleccion eleccionActual;

    public CandidatoFrame(SistemaVotacion sistema) {
        this.sistema = sistema;

        setTitle("Gestión de Candidatos");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel form = new JPanel(new GridLayout(7, 2, 10, 5));
        form.setBorder(BorderFactory.createTitledBorder("Datos del Candidato"));

        txtNombre = new JTextField();
        txtApellido = new JTextField();
        txtDNI = new JTextField();
        txtPartido = new JTextField();
        txtTipoEleccion = new JTextField();
        txtFechaEleccion = new JTextField();
        lblMensaje = new JLabel();

        form.add(new JLabel("Nombre:")); form.add(txtNombre);
        form.add(new JLabel("Apellido:")); form.add(txtApellido);
        form.add(new JLabel("DNI:")); form.add(txtDNI);
        form.add(new JLabel("Partido Político:")); form.add(txtPartido);
        form.add(new JLabel("Tipo de Elección:")); form.add(txtTipoEleccion);
        form.add(new JLabel("Fecha de Elección:")); form.add(txtFechaEleccion);
        form.add(lblMensaje);

        // Botones
        JPanel panelBotones = new JPanel(new FlowLayout());

        JButton btnBuscar = new JButton("Buscar");
        JButton btnRegistrar = new JButton("Registrar");
        JButton btnActualizar = new JButton("Actualizar");
        JButton btnEliminar = new JButton("Eliminar");

        btnBuscar.addActionListener(e -> buscarCandidato());
        btnRegistrar.addActionListener(e -> registrarCandidato());
        btnActualizar.addActionListener(e -> actualizarCandidato());
        btnEliminar.addActionListener(e -> eliminarCandidato());

        panelBotones.add(btnBuscar);
        panelBotones.add(btnRegistrar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);

        add(form, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void buscarCandidato() {
        eleccionActual = buscarEleccion();
        if (eleccionActual == null) return;

        String dni = txtDNI.getText().trim();
        for (int i = 0; i < eleccionActual.getCantidadCandidatos(); i++) {
            Candidato c = eleccionActual.getCandidatos()[i];
            if (c.getDni().equalsIgnoreCase(dni)) {
                txtNombre.setText(c.getNombre());
                txtApellido.setText(c.getApellido());
                txtPartido.setText(c.getPartidoPolitico());
                lblMensaje.setText("Candidato encontrado.");
                return;
            }
        }
        lblMensaje.setText("Candidato no encontrado.");
    }

    private void registrarCandidato() {
        eleccionActual = buscarEleccion();
        if (eleccionActual == null) return;

        String nombre = txtNombre.getText().trim();
        String apellido = txtApellido.getText().trim();
        String dni = txtDNI.getText().trim();
        String partido = txtPartido.getText().trim();

        if (nombre.isEmpty() || apellido.isEmpty() || dni.isEmpty() || partido.isEmpty()) {
            lblMensaje.setText("Complete todos los campos.");
            return;
        }

        Candidato nuevo = new Candidato(nombre, apellido, dni, partido);
        if (eleccionActual.agregarCandidato(nuevo)) {
            JOptionPane.showMessageDialog(this, "Candidato registrado.");
            limpiarCampos();
        } else {
            lblMensaje.setText("No se pudo registrar (máximo alcanzado).");
        }
    }

    private void actualizarCandidato() {
        eleccionActual = buscarEleccion();
        if (eleccionActual == null) return;

        String dni = txtDNI.getText().trim();
        for (int i = 0; i < eleccionActual.getCantidadCandidatos(); i++) {
            Candidato c = eleccionActual.getCandidatos()[i];
            if (c.getDni().equalsIgnoreCase(dni)) {
                c.setNombre(txtNombre.getText().trim());
                c.setApellido(txtApellido.getText().trim());
                c.setPartidoPolitico(txtPartido.getText().trim());
                JOptionPane.showMessageDialog(this, "Candidato actualizado.");
                limpiarCampos();
                return;
            }
        }
        lblMensaje.setText("Candidato no encontrado.");
    }

    private void eliminarCandidato() {
        eleccionActual = buscarEleccion();
        if (eleccionActual == null) return;

        String dni = txtDNI.getText().trim();
        if (eleccionActual.eliminarCandidatoPorDNI(dni)) {
            JOptionPane.showMessageDialog(this, "Candidato eliminado.");
            limpiarCampos();
        } else {
            lblMensaje.setText("No se pudo eliminar (no encontrado).");
        }
    }

    private Eleccion buscarEleccion() {
        String tipo = txtTipoEleccion.getText().trim();
        String fecha = txtFechaEleccion.getText().trim();

        if (tipo.isEmpty() || fecha.isEmpty()) {
            lblMensaje.setText("Complete tipo y fecha de elección.");
            return null;
        }

        Eleccion e = sistema.buscarEleccionPorTipoYFecha(tipo, fecha);
        if (e == null) {
            lblMensaje.setText("Elección no encontrada.");
        }
        return e;
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtApellido.setText("");
        txtDNI.setText("");
        txtPartido.setText("");
        txtTipoEleccion.setText("");
        txtFechaEleccion.setText("");
        lblMensaje.setText("");
    }
}
