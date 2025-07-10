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

public class InformeFrame extends JFrame {

    private final SistemaVotacion sistema;

    public InformeFrame(SistemaVotacion sistema) {
        this.sistema = sistema;

        setTitle("Generar Informe por Elección");
        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField txtTipo = new JTextField();
        JTextField txtFecha = new JTextField();
        JTextArea areaResultados = new JTextArea();
        areaResultados.setEditable(false);

        JLabel lblMensaje = new JLabel();
        JButton btnGenerar = new JButton("Generar Informe");

        formPanel.add(new JLabel("Tipo de elección:"));
        formPanel.add(txtTipo);
        formPanel.add(new JLabel("Fecha de elección:"));
        formPanel.add(txtFecha);
        formPanel.add(new JLabel(""));
        formPanel.add(btnGenerar);

        btnGenerar.addActionListener(e -> {
            String tipo = txtTipo.getText().trim();
            String fecha = txtFecha.getText().trim();
            Eleccion eleccion = sistema.buscarEleccionPorTipoYFecha(tipo, fecha);

            if (eleccion == null) {
                lblMensaje.setText("Elección no encontrada.");
                return;
            }

            StringBuilder sb = new StringBuilder();
            sb.append("INFORME DE ELECCIÓN: ").append(tipo).append(" - ").append(fecha).append("\n\n");

            int totalBlancos = 0, totalNulos = 0, totalRegistrados = 0, totalEfectivos = 0;

            // Actas por mesa
            sb.append("DETALLE POR MESA:\n");
            for (ActaElectoral acta : sistema.getActas()) {
                if (acta != null && acta.getEleccion().equals(eleccion)) {
                    sb.append("\nActa Nº: ").append(acta.getNumeroActa()).append("\n");
                    sb.append("Mesa: ").append(acta.getMesa().getCodigoMesa()).append("\n");
                    sb.append("Registrados: ").append(acta.getVotantesRegistrados()).append("\n");
                    sb.append("Efectivos: ").append(acta.getVotantesEfectivos()).append("\n");
                    sb.append("Blancos: ").append(acta.getVotosEnBlanco()).append("\n");
                    sb.append("Nulos: ").append(acta.getVotosNulos()).append("\n");

                    totalBlancos += acta.getVotosEnBlanco();
                    totalNulos += acta.getVotosNulos();
                    totalRegistrados += acta.getVotantesRegistrados();
                    totalEfectivos += acta.getVotantesEfectivos();
                }
            }

            // Resultados generales
            sb.append("\n\nRESULTADOS GENERALES:\n");
            for (int i = 0; i < eleccion.getCantidadCandidatos(); i++) {
                Candidato c = eleccion.getCandidatos()[i];
                sb.append("- ").append(c.getNombreCompleto())
                        .append(" (").append(c.getPartidoPolitico()).append("): ")
                        .append(c.getCantidadVotos()).append(" votos\n");
            }

            sb.append("\nTotal Registrados: ").append(totalRegistrados);
            sb.append("\nTotal Votos Efectivos: ").append(totalEfectivos);
            sb.append("\nTotal Votos en Blanco: ").append(totalBlancos);
            sb.append("\nTotal Votos Nulos: ").append(totalNulos);

            areaResultados.setText(sb.toString());
        });

        JPanel resultadoPanel = new JPanel(new BorderLayout());
        resultadoPanel.setBorder(BorderFactory.createTitledBorder("Informe Generado"));
        resultadoPanel.add(new JScrollPane(areaResultados), BorderLayout.CENTER);

        add(formPanel, BorderLayout.NORTH);
        add(resultadoPanel, BorderLayout.CENTER);
        add(lblMensaje, BorderLayout.SOUTH);

        setVisible(true);
    }
}
