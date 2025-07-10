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

public class ResultadosFrame extends JFrame {

    public ResultadosFrame(SistemaVotacion sistema) {
        setTitle("Resultados de Elección");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        JPanel form = new JPanel(new GridLayout(2, 2, 10, 10));
        form.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField txtTipo = new JTextField();
        JTextField txtFecha = new JTextField();
        JTextArea txtResultados = new JTextArea();
        txtResultados.setEditable(false);

        JButton btnVer = new JButton("Ver Resultados");
        JLabel lblMensaje = new JLabel();

        form.add(new JLabel("Tipo de elección:")); form.add(txtTipo);
        form.add(new JLabel("Fecha de elección:")); form.add(txtFecha);

        btnVer.addActionListener(e -> {
            String tipo = txtTipo.getText().trim();
            String fecha = txtFecha.getText().trim();
            Eleccion eleccion = sistema.buscarEleccionPorTipoYFecha(tipo, fecha);

            if (eleccion == null) {
                lblMensaje.setText("Elección no encontrada.");
                return;
            }

            int blancos = 0, nulos = 0, registrados = 0, efectivos = 0;
            for (ActaElectoral acta : sistema.getActas()) {
                if (acta != null && acta.getEleccion() == eleccion) {
                    blancos += acta.getVotosEnBlanco();
                    nulos += acta.getVotosNulos();
                    registrados += acta.getVotantesRegistrados();
                    efectivos += acta.getVotantesEfectivos();
                }
            }

            StringBuilder sb = new StringBuilder("Resultados:\n\n");
            for (int i = 0; i < eleccion.getCantidadCandidatos(); i++) {
                Candidato c = eleccion.getCandidatos()[i];
                sb.append("- ").append(c.getNombreCompleto())
                  .append(" (").append(c.getPartidoPolitico()).append("): ")
                  .append(c.getCantidadVotos()).append(" votos\n");
            }
            sb.append("\nBlancos: ").append(blancos);
            sb.append("\nNulos: ").append(nulos);
            sb.append("\nRegistrados: ").append(registrados);
            sb.append("\nEfectivos: ").append(efectivos);

            txtResultados.setText(sb.toString());
        });

        panel.add(form, BorderLayout.NORTH);
        panel.add(btnVer, BorderLayout.CENTER);
        panel.add(new JScrollPane(txtResultados), BorderLayout.SOUTH);
        panel.add(lblMensaje, BorderLayout.PAGE_END);

        add(panel);
        setVisible(true);
    }
}



