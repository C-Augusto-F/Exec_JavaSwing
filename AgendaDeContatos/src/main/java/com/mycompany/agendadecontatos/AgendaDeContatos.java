package com.mycompany.agendadecontatos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AgendaDeContatos {
    private JFrame frame;
    private JTextField nomeField;
    private JTextField telefoneField;
    private JTextField emailField;
    private JList<String> listaContatos;
    private DefaultListModel<String> modeloLista;
    private ArrayList<Contato> contatos;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                AgendaDeContatos window = new AgendaDeContatos();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public AgendaDeContatos() {
        contatos = new ArrayList<>();
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Agenda de Contatos");
        frame.setBounds(100, 100, 400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        JPanel painelEntrada = new JPanel();
        frame.getContentPane().add(painelEntrada, BorderLayout.NORTH);
        painelEntrada.setLayout(new GridLayout(3, 2));

        JLabel nomeLabel = new JLabel("Nome:");
        painelEntrada.add(nomeLabel);

        nomeField = new JTextField();
        painelEntrada.add(nomeField);
        nomeField.setColumns(10);

        JLabel telefoneLabel = new JLabel("Telefone:");
        painelEntrada.add(telefoneLabel);

        telefoneField = new JTextField();
        painelEntrada.add(telefoneField);
        telefoneField.setColumns(10);

        JLabel emailLabel = new JLabel("Email:");
        painelEntrada.add(emailLabel);

        emailField = new JTextField();
        painelEntrada.add(emailField);
        emailField.setColumns(10);

        JPanel painelBotoes = new JPanel();
        frame.getContentPane().add(painelBotoes, BorderLayout.SOUTH);

        JButton adicionarButton = new JButton("Adicionar");
        painelBotoes.add(adicionarButton);

        JButton editarButton = new JButton("Editar");
        painelBotoes.add(editarButton);

        JButton removerButton = new JButton("Remover");
        painelBotoes.add(removerButton);

        JButton limparButton = new JButton("Limpar");
        painelBotoes.add(limparButton);

        modeloLista = new DefaultListModel<>();
        listaContatos = new JList<>(modeloLista);
        frame.getContentPane().add(new JScrollPane(listaContatos), BorderLayout.CENTER);

        adicionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = nomeField.getText();
                String telefone = telefoneField.getText();
                String email = emailField.getText();

                if (!nome.isEmpty() && !telefone.isEmpty() && !email.isEmpty()) {
                    Contato contato = new Contato(nome, telefone, email);
                    contatos.add(contato);
                    modeloLista.addElement(contato.toString());
                    limparCampos();
                } else {
                    JOptionPane.showMessageDialog(frame, "Todos os campos devem ser preenchidos.");
                }
            }
        });

        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = listaContatos.getSelectedIndex();
                if (index >= 0) {
                    String nome = nomeField.getText();
                    String telefone = telefoneField.getText();
                    String email = emailField.getText();

                    if (!nome.isEmpty() && !telefone.isEmpty() && !email.isEmpty()) {
                        Contato contato = contatos.get(index);
                        contato.setNome(nome);
                        contato.setTelefone(telefone);
                        contato.setEmail(email);
                        modeloLista.set(index, contato.toString());
                        limparCampos();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Todos os campos devem ser preenchidos.");
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Selecione um contato para editar.");
                }
            }
        });

        removerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = listaContatos.getSelectedIndex();
                if (index >= 0) {
                    contatos.remove(index);
                    modeloLista.remove(index);
                } else {
                    JOptionPane.showMessageDialog(frame, "Selecione um contato para remover.");
                }
            }
        });

        limparButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparCampos();
            }
        });
    }

    private void limparCampos() {
        nomeField.setText("");
        telefoneField.setText("");
        emailField.setText("");
        listaContatos.clearSelection();
    }

    class Contato {
        private String nome;
        private String telefone;
        private String email;

        public Contato(String nome, String telefone, String email) {
            this.nome = nome;
            this.telefone = telefone;
            this.email = email;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public void setTelefone(String telefone) {
            this.telefone = telefone;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        @Override
        public String toString() {
            return nome + " - " + telefone + " - " + email;
        }
    }
}
