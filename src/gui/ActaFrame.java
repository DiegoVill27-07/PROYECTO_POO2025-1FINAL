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

public class ActaFrame extends JFrame {

    public ActaFrame(SistemaVotacion sistema) {
        setTitle("Registrar Acta Electoral");
        setSize(500, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(15, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField txtNumero = new JTextField();
        JTextField txtTitulo = new JTextField();
        JTextField txtFecha = new JTextField();
        JTextField txtHora = new JTextField();
        JTextField txtLugar = new JTextField();
        JTextField txtTipoEleccion = new JTextField();
        JTextField txtFechaEleccion = new JTextField();
        JTextField txtCodigoMesa = new JTextField();
        JTextField txtRegistrados = new JTextField();
        JTextField txtBlancos = new JTextField();
        JTextField txtNulos = new JTextField();
        JTextField txtSello = new JTextField();
        JTextArea txtObservaciones = new JTextArea(3, 20);
        JTextField[] firmas = {new JTextField(), new JTextField(), new JTextField()};
        JLabel lblMensaje = new JLabel();

        panel.add(new JLabel("Número de Acta:")); panel.add(txtNumero);
        panel.add(new JLabel("Título:")); panel.add(txtTitulo);
        panel.add(new JLabel("Fecha:")); panel.add(txtFecha);
        panel.add(new JLabel("Hora:")); panel.add(txtHora);
        panel.add(new JLabel("Lugar:")); panel.add(txtLugar);
        panel.add(new JLabel("Tipo Elección:")); panel.add(txtTipoEleccion);
        panel.add(new JLabel("Fecha Elección:")); panel.add(txtFechaEleccion);
        panel.add(new JLabel("Código Mesa:")); panel.add(txtCodigoMesa);
        panel.add(new JLabel("Votantes Registrados:")); panel.add(txtRegistrados);
        panel.add(new JLabel("Votos en Blanco:")); panel.add(txtBlancos);
        panel.add(new JLabel("Votos Nulos:")); panel.add(txtNulos);
        panel.add(new JLabel("Sello Oficial:")); panel.add(txtSello);
        panel.add(new JLabel("Observaciones:")); panel.add(txtObservaciones);

        for (int i = 0; i < 3; i++) {
            panel.add(new JLabel("Firma " + (i + 1) + ":"));
            panel.add(firmas[i]);
        }

        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.addActionListener(e -> {
            try {
                String tipo = txtTipoEleccion.getText().trim();
                String fechaEleccion = txtFechaEleccion.getText().trim();
                Eleccion eleccion = sistema.buscarEleccionPorTipoYFecha(tipo, fechaEleccion);
                MesaElectoral mesa = sistema.buscarMesaPorCodigo(txtCodigoMesa.getText().trim());

                if (eleccion == null || mesa == null) {
                    lblMensaje.setText("Elección o mesa no encontrada.");
                    return;
                }

                ActaElectoral acta = new ActaElectoral(
                        txtNumero.getText().trim(), txtTitulo.getText().trim(), txtFecha.getText().trim(),
                        txtHora.getText().trim(), txtLugar.getText().trim(), mesa, eleccion
                );

                int registrados = Integer.parseInt(txtRegistrados.getText().trim());
                int blancos = Integer.parseInt(txtBlancos.getText().trim());
                int nulos = Integer.parseInt(txtNulos.getText().trim());

                int[] votos = new int[eleccion.getCantidadCandidatos()];
                for (int i = 0; i < votos.length; i++) {
                    String input = JOptionPane.showInputDialog(
                            this, "Votos para " + eleccion.getCandidatos()[i].getNombreCompleto());
                    votos[i] = Integer.parseInt(input.trim());
                }

                acta.registrarVotos(votos, blancos, nulos, registrados);
                acta.setSello(txtSello.getText().trim());
                acta.setObservaciones(txtObservaciones.getText().trim());

                for (JTextField f : firmas) acta.agregarFirma(f.getText().trim());

                if (sistema.agregarActa(acta)) {
                    JOptionPane.showMessageDialog(this, "Acta registrada correctamente.");
                    dispose();
                } else {
                    lblMensaje.setText("No se pudo registrar.");
                }

            } catch (Exception ex) {
                lblMensaje.setText("Error: datos inválidos.");
            }
        });

        panel.add(lblMensaje);
        panel.add(btnRegistrar);

        add(new JScrollPane(panel));
        setVisible(true);
    }
}
