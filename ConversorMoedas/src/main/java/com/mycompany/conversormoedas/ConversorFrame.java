package com.mycompany.conversormoedas;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ConversorFrame extends JFrame {
    private JTextField inputField;
    private JLabel resultLabel;

    public ConversorFrame() {
        setTitle("Conversor de Moedas");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Campo de entrada
        inputField = new JTextField();
        inputField.setBounds(50, 50, 100, 25);
        add(inputField);

        // Botão de conversão

        JButton convertButton = new JButton("Converter");
        convertButton.setBounds(160, 50, 100, 25);
        add(convertButton);

        // Rótulo de resultado
        resultLabel = new JLabel("Resultado:");
        resultLabel.setBounds(50, 100, 300, 25);
        add(resultLabel);

        // Ação do botão
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double amountBRL = Double.parseDouble(inputField.getText());
                    double usdAmount = CurrencyConverter.convertToUSD(amountBRL);
                    double eurAmount = CurrencyConverter.convertToEUR(amountBRL);
                    resultLabel.setText(String.format("USD: %.2f, EUR: %.2f", usdAmount, eurAmount));
                } catch (NumberFormatException | IOException ex) {
                    resultLabel.setText("Erro: " + ex.getMessage());
                }
            }
        });
    }
}
