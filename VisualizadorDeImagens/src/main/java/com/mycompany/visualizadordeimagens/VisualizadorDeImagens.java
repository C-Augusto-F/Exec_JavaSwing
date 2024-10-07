/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.visualizadordeimagens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class VisualizadorDeImagens extends JFrame {
    private JLabel labelImagem;
    private JButton btnCarregar, btnLimpar;
    
    public VisualizadorDeImagens() {
        // Configurações da janela principal
        super("Visualizador de Imagens");
        setLayout(new BorderLayout());
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Painel para os botões
        JPanel panelBotoes = new JPanel();
        btnCarregar = new JButton("Carregar Imagem");
        btnLimpar = new JButton("Limpar Imagem");
        panelBotoes.add(btnCarregar);
        panelBotoes.add(btnLimpar);
        
        // Label para exibir a imagem
        labelImagem = new JLabel();
        labelImagem.setHorizontalAlignment(JLabel.CENTER);
        labelImagem.setVerticalAlignment(JLabel.CENTER);
        labelImagem.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        // Adiciona os componentes à janela
        add(panelBotoes, BorderLayout.SOUTH);
        add(labelImagem, BorderLayout.CENTER);
        
        // Ação para o botão Carregar
        btnCarregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int resultado = fileChooser.showOpenDialog(null);
                
                if (resultado == JFileChooser.APPROVE_OPTION) {
                    File arquivoSelecionado = fileChooser.getSelectedFile();
                    String caminhoImagem = arquivoSelecionado.getAbsolutePath();
                    ImageIcon imagem = new ImageIcon(caminhoImagem);
                    Image img = imagem.getImage().getScaledInstance(labelImagem.getWidth(), labelImagem.getHeight(), Image.SCALE_SMOOTH);
                    labelImagem.setIcon(new ImageIcon(img));
                }
            }
        });
        
        // Ação para o botão Limpar
        btnLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labelImagem.setIcon(null);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VisualizadorDeImagens app = new VisualizadorDeImagens();
            app.setVisible(true);
        });
    }
}
