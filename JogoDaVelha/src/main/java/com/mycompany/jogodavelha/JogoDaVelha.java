/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.jogodavelha;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JogoDaVelha extends JFrame {
    private JButton[][] botoes = new JButton[3][3];
    private JLabel labelStatus;
    private boolean jogadorX = true;  // true para "X", false para "O"
    private int jogadas = 0;

    public JogoDaVelha() {
        // Configurações da janela principal
        super("Jogo da Velha");
        setLayout(new BorderLayout());
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Painel do tabuleiro 3x3
        JPanel panelTabuleiro = new JPanel();
        panelTabuleiro.setLayout(new GridLayout(3, 3));
        
        // Inicializa os botões do tabuleiro
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                botoes[i][j] = new JButton("");
                botoes[i][j].setFont(new Font("Arial", Font.PLAIN, 60));
                panelTabuleiro.add(botoes[i][j]);
                botoes[i][j].addActionListener(new BotaoClickListener(i, j));
            }
        }

        // Painel de status e reiniciar
        JPanel panelStatus = new JPanel(new BorderLayout());
        labelStatus = new JLabel("Jogador X começa", SwingConstants.CENTER);
        labelStatus.setFont(new Font("Arial", Font.PLAIN, 20));
        panelStatus.add(labelStatus, BorderLayout.NORTH);

        JButton btnReiniciar = new JButton("Reiniciar Jogo");
        btnReiniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reiniciarJogo();
            }
        });
        panelStatus.add(btnReiniciar, BorderLayout.SOUTH);

        // Adiciona os painéis à janela
        add(panelTabuleiro, BorderLayout.CENTER);
        add(panelStatus, BorderLayout.SOUTH);
    }

    // Listener para tratar os cliques nos botões
    private class BotaoClickListener implements ActionListener {
        private int linha, coluna;

        public BotaoClickListener(int linha, int coluna) {
            this.linha = linha;
            this.coluna = coluna;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (botoes[linha][coluna].getText().equals("") && !verificaVencedor()) {
                botoes[linha][coluna].setText(jogadorX ? "X" : "O");
                jogadas++;
                if (verificaVencedor()) {
                    labelStatus.setText("Jogador " + (jogadorX ? "X" : "O") + " venceu!");
                    desabilitarBotoes();
                } else if (jogadas == 9) {
                    labelStatus.setText("Empate!");
                } else {
                    jogadorX = !jogadorX;
                    labelStatus.setText("Vez do jogador " + (jogadorX ? "X" : "O"));
                }
            }
        }
    }

    // Verifica se houve um vencedor
    private boolean verificaVencedor() {
        String simboloAtual = jogadorX ? "X" : "O";

        // Verifica linhas e colunas
        for (int i = 0; i < 3; i++) {
            if (botoes[i][0].getText().equals(simboloAtual) && botoes[i][1].getText().equals(simboloAtual) && botoes[i][2].getText().equals(simboloAtual)) {
                return true;
            }
            if (botoes[0][i].getText().equals(simboloAtual) && botoes[1][i].getText().equals(simboloAtual) && botoes[2][i].getText().equals(simboloAtual)) {
                return true;
            }
        }

        // Verifica diagonais
        if (botoes[0][0].getText().equals(simboloAtual) && botoes[1][1].getText().equals(simboloAtual) && botoes[2][2].getText().equals(simboloAtual)) {
            return true;
        }
        if (botoes[0][2].getText().equals(simboloAtual) && botoes[1][1].getText().equals(simboloAtual) && botoes[2][0].getText().equals(simboloAtual)) {
            return true;
        }

        return false;
    }

    // Desabilita os botões do tabuleiro
    private void desabilitarBotoes() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                botoes[i][j].setEnabled(false);
            }
        }
    }

    // Reinicia o jogo
    private void reiniciarJogo() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                botoes[i][j].setText("");
                botoes[i][j].setEnabled(true);
            }
        }
        jogadorX = true;
        jogadas = 0;
        labelStatus.setText("Jogador X começa");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JogoDaVelha app = new JogoDaVelha();
            app.setVisible(true);
        });
    }
}
