/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.cronometrotemporizador;

/**
 *
 * @author Augusto
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CronometroTemporizador {
    private JFrame frame;
    private JLabel displayTempo;
    private JButton iniciarButton, pausarButton, pararButton, redefinirButton;
    private JTextField tempoField;
    private Timer timer;
    private boolean cronometroAtivo = true;  // Para alternar entre cronômetro e temporizador
    private int segundos = 0, minutos = 0;   // Para cronômetro
    private int tempoRestante = 0;           // Para temporizador

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                CronometroTemporizador window = new CronometroTemporizador();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public CronometroTemporizador() {
        initialize();
    }

    private void initialize() {
        // Configuração da janela
        frame = new JFrame("Cronômetro e Temporizador");
        frame.setBounds(100, 100, 400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new GridLayout(6, 1));

        // Exibir tempo
        displayTempo = new JLabel("00:00", SwingConstants.CENTER);
        displayTempo.setFont(new Font("Serif", Font.BOLD, 40));
        frame.getContentPane().add(displayTempo);

        // Campo de entrada para temporizador (em minutos)
        JPanel painelEntrada = new JPanel();
        JLabel entradaLabel = new JLabel("Definir Temporizador (min):");
        tempoField = new JTextField(5);
        painelEntrada.add(entradaLabel);
        painelEntrada.add(tempoField);
        frame.getContentPane().add(painelEntrada);

        // Botões
        iniciarButton = new JButton("Iniciar");
        pausarButton = new JButton("Pausar");
        pararButton = new JButton("Parar");
        redefinirButton = new JButton("Redefinir");

        frame.getContentPane().add(iniciarButton);
        frame.getContentPane().add(pausarButton);
        frame.getContentPane().add(pararButton);
        frame.getContentPane().add(redefinirButton);

        // Configurar ações dos botões
        iniciarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciar();
            }
        });

        pausarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pausar();
            }
        });

        pararButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parar();
            }
        });

        redefinirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                redefinir();
            }
        });

        // Timer que será executado a cada 1000ms (1 segundo)
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cronometroAtivo) {
                    atualizarCronometro();
                } else {
                    atualizarTemporizador();
                }
            }
        });
    }

    // Métodos para cronômetro
    private void iniciar() {
        if (tempoField.getText().isEmpty()) {
            cronometroAtivo = true;  // Usar como cronômetro se campo de tempo estiver vazio
        } else {
            try {
                int minutosDefinidos = Integer.parseInt(tempoField.getText());
                tempoRestante = minutosDefinidos * 60; // Converter minutos em segundos
                cronometroAtivo = false;  // Usar como temporizador
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Por favor, insira um número válido.");
                return;
            }
        }
        timer.start();
        iniciarButton.setEnabled(false);
        pausarButton.setEnabled(true);
        pararButton.setEnabled(true);
        redefinirButton.setEnabled(false);
    }

    private void pausar() {
        timer.stop();
        iniciarButton.setEnabled(true);
        redefinirButton.setEnabled(true);
    }

    private void parar() {
        timer.stop();
        iniciarButton.setEnabled(true);
        redefinirButton.setEnabled(true);
        minutos = 0;
        segundos = 0;
        tempoRestante = 0;
        displayTempo.setText("00:00");
    }

    private void redefinir() {
        minutos = 0;
        segundos = 0;
        tempoRestante = 0;
        displayTempo.setText("00:00");
        tempoField.setText("");
        iniciarButton.setEnabled(true);
        redefinirButton.setEnabled(false);
    }

    private void atualizarCronometro() {
        segundos++;
        if (segundos == 60) {
            minutos++;
            segundos = 0;
        }
        displayTempo.setText(String.format("%02d:%02d", minutos, segundos));
    }

    // Métodos para temporizador
    private void atualizarTemporizador() {
        if (tempoRestante > 0) {
            tempoRestante--;
            int min = tempoRestante / 60;
            int sec = tempoRestante % 60;
            displayTempo.setText(String.format("%02d:%02d", min, sec));
        } else {
            timer.stop();
            JOptionPane.showMessageDialog(frame, "O tempo acabou!");
            redefinir();
        }
    }
}
