package com.mycompany.conversormoedas;

public class ConversorMoedas {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConversorFrame().setVisible(true);
            }
        });
    }
}
