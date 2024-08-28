package com.mycompany.principal.controle;


import com.mycompany.principal.enums.EnumOperacao;

public class CalculadoraController {
    private double total;
    private double operando;
    private EnumOperacao ultimaOperacao;

    public void realizaOperacao(EnumOperacao operacao, double valor) {
        if (ultimaOperacao == null) {
            total = valor;
        } else {
            switch (ultimaOperacao) {
                case SOMA:
                    total += valor;
                    break;
                case SUBTRACAO:
                    total -= valor;
                    break;
                case MULTIPLICACAO:
                    total *= valor;
                    break;
                case DIVISAO:
                    if (valor != 0) {
                        total /= valor;
                    } else {
                        // Tratar divisão por zero
                        total = 0;
                    }
                    break;
            }
        }
        ultimaOperacao = operacao;
    }

    public double getTotal() {
        return total;
    }

    public void zerar() {
        total = 0;
        ultimaOperacao = null;
    }
}