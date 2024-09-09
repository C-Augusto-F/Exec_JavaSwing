/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.calculadoraimc;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Augusto
 */
public class CalculadoraIMC {

    public class IMCCalculator extends JFrame {

        private JTextField weightField;
        private JTextField heightField;
        private JLabel resultLabel;

        public IMCCalculator() {
            setTitle("Calculadora de IMC");
            setSize(300, 200);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);

            // Criando os componentes
            JLabel weightLabel = new JLabel("\n Peso (kg):");
            weightField = new JTextField(10);

            JLabel heightLabel = new JLabel("\n Altura (m):");
            heightField = new JTextField(10);

            JButton calculateButton = new JButton("\n Calcular");
            resultLabel = new JLabel("Seu IMC será exibido aqui.");

            // Configurando o layout
            JPanel panel = new JPanel();
            panel.add(weightLabel);
            panel.add(weightField);
            panel.add(heightLabel);
            panel.add(heightField);
            panel.add(calculateButton);
            panel.add(resultLabel);

            add(panel);

            // Adicionando o listener para o botão
            calculateButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    calculateIMC();
                }
            });
        }

        private void calculateIMC() {
            try {
                double weight = Double.parseDouble(weightField.getText());
                double height = Double.parseDouble(heightField.getText());
                double imc = weight / (height * height);

                String classification = classifyIMC(imc);

                resultLabel.setText(String.format("Seu IMC é %.2f - %s", imc, classification));
            } catch (NumberFormatException ex) {
                resultLabel.setText("Por favor, insira valores válidos.");
            }
        }

        private String classifyIMC(double imc) {
            if (imc < 18.5) {
                return "Abaixo do peso";
            } else if (imc >= 18.5 && imc < 24.9) {
                return "Peso normal";
            } else if (imc >= 25 && imc < 29.9) {
                return "Sobrepeso";
            } else {
                return "Obesidade";
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CalculadoraIMC().new IMCCalculator().setVisible(true);
            }
        });
    }
}
