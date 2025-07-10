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
import gui.PartidoPoliticoFrame;
import gui.MiembroMesaFrame;
import gui.InformeFrame;

import javax.swing.*;
import java.awt.*;

public class MenuPrincipalFrame extends JFrame {

    public MenuPrincipalFrame(SistemaVotacion sistema, Operador operador) {
        setTitle("Menú Principal - Sistema de Votación");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(6, 1, 10, 10));

        JLabel lblBienvenida = new JLabel("Bienvenido: " + operador.getNombreCompleto(), SwingConstants.CENTER);
        panel.add(lblBienvenida);

        JButton btnElecciones = new JButton("Gestionar Elecciones");
        JButton btnCandidatos = new JButton("Gestionar Candidatos");
        JButton btnMesas = new JButton("Gestionar Mesas");
        JButton btnActas = new JButton("Registrar Actas");
        JButton btnMiembros = new JButton("Gestionar Miembros de Mesa");
        JButton btnPartidos = new JButton("Gestionar Partidos Políticos");
        JButton btnInforme = new JButton("Generar Informe");

        JButton btnResultados = new JButton("Ver Resultados");

        
        
        
        // Listeners de navegación (serán implementados luego)
        btnElecciones.addActionListener(e -> new EleccionFrame(sistema));
        btnCandidatos.addActionListener(e -> new CandidatoFrame(sistema));
        btnMesas.addActionListener(e -> new MesaFrame(sistema));
        btnActas.addActionListener(e -> new ActaFrame(sistema));
        btnPartidos.addActionListener(e -> new PartidoPoliticoFrame(sistema));
        btnMiembros.addActionListener(e -> new MiembroMesaFrame(sistema));
        btnInforme.addActionListener(e -> new InformeFrame(sistema));
        btnResultados.addActionListener(e -> new ResultadosFrame(sistema));

        panel.add(btnElecciones);
        panel.add(btnCandidatos);
        panel.add(btnMesas);
        panel.add(btnActas);
        panel.add(btnPartidos); 
        panel.add(btnMiembros);
        panel.add(btnInforme);
        panel.add(btnResultados);

        add(panel);
        setVisible(true);
    }
}
