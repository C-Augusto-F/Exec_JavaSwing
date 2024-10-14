package com.mycompany.geradordesenhas;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.SecureRandom;

public class GeradorDeSenhas {
    private JFrame frame;
    private JTextField comprimentoField;
    private JCheckBox letrasMaiusculasCheck;
    private JCheckBox letrasMinusculasCheck;
    private JCheckBox numerosCheck;
    private JCheckBox especiaisCheck;
    private JLabel senhaGeradaLabel;
    private JButton copiarButton;
    private SecureRandom random = new SecureRandom();

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                GeradorDeSenhas window = new GeradorDeSenhas();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public GeradorDeSenhas() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Gerador de Senhas");
        frame.setBounds(100, 100, 400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new GridLayout(6, 1));

        JPanel painelComprimento = new JPanel();
        frame.getContentPane().add(painelComprimento);
        painelComprimento.setLayout(new FlowLayout());

        JLabel comprimentoLabel = new JLabel("Comprimento da senha:");
        painelComprimento.add(comprimentoLabel);

        comprimentoField = new JTextField();
        painelComprimento.add(comprimentoField);
        comprimentoField.setColumns(10);

        JPanel painelOpcoes = new JPanel();
        frame.getContentPane().add(painelOpcoes);
        painelOpcoes.setLayout(new FlowLayout());

        letrasMaiusculasCheck = new JCheckBox("Letras maiúsculas");
        painelOpcoes.add(letrasMaiusculasCheck);

        letrasMinusculasCheck = new JCheckBox("Letras minúsculas");
        painelOpcoes.add(letrasMinusculasCheck);

        numerosCheck = new JCheckBox("Números");
        painelOpcoes.add(numerosCheck);

        especiaisCheck = new JCheckBox("Caracteres especiais");
        painelOpcoes.add(especiaisCheck);

        JButton gerarButton = new JButton("Gerar Senha");
        frame.getContentPane().add(gerarButton);

        senhaGeradaLabel = new JLabel("Sua senha gerada aparecerá aqui");
        senhaGeradaLabel.setHorizontalAlignment(SwingConstants.CENTER);
        frame.getContentPane().add(senhaGeradaLabel);

        copiarButton = new JButton("Copiar para área de transferência");
        copiarButton.setEnabled(false);
        frame.getContentPane().add(copiarButton);

        gerarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gerarSenha();
            }
        });

        copiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                copiarSenha();
            }
        });
    }

    private void gerarSenha() {
        StringBuilder poolCaracteres = new StringBuilder();

        if (letrasMaiusculasCheck.isSelected()) {
            poolCaracteres.append("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        }
        if (letrasMinusculasCheck.isSelected()) {
            poolCaracteres.append("abcdefghijklmnopqrstuvwxyz");
        }
        if (numerosCheck.isSelected()) {
            poolCaracteres.append("0123456789");
        }
        if (especiaisCheck.isSelected()) {
            poolCaracteres.append("!@#$%^&*()-_=+[]{};:'\",.<>?/|\\");
        }

        if (poolCaracteres.length() == 0) {
            JOptionPane.showMessageDialog(frame, "Selecione pelo menos um tipo de caractere.");
            return;
        }

        String comprimentoTexto = comprimentoField.getText();
        int comprimento;

        try {
            comprimento = Integer.parseInt(comprimentoTexto);
            if (comprimento <= 0) {
                JOptionPane.showMessageDialog(frame, "O comprimento deve ser um número positivo.");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Por favor, insira um número válido para o comprimento.");
            return;
        }

        StringBuilder senha = new StringBuilder();
        for (int i = 0; i < comprimento; i++) {
            int index = random.nextInt(poolCaracteres.length());
            senha.append(poolCaracteres.charAt(index));
        }

        senhaGeradaLabel.setText(senha.toString());
        copiarButton.setEnabled(true);
    }

    private void copiarSenha() {
        String senha = senhaGeradaLabel.getText();
        StringSelection stringSelection = new StringSelection(senha);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
        JOptionPane.showMessageDialog(frame, "Senha copiada para a área de transferência.");
    }
}
