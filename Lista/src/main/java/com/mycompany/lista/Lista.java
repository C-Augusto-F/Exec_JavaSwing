package com.mycompany.lista;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Lista extends JFrame {
    
    private DefaultListModel<String> todoModel;
    private JList<String> todoList;
    private JTextField inputField;
    private JButton addButton;
    private JButton removeButton;
    private JButton markAsDoneButton;
    
    public Lista() {
        setTitle("To-Do List");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Model to hold the list items
        todoModel = new DefaultListModel<>();
        todoList = new JList<>(todoModel);
        todoList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // Input field and buttons
        inputField = new JTextField(20);
        addButton = new JButton("Add Task");
        removeButton = new JButton("Remove Task");
        markAsDoneButton = new JButton("Mark as Done");
        
        // Panel for input and buttons
        JPanel inputPanel = new JPanel();
        inputPanel.add(inputField);
        inputPanel.add(addButton);
        inputPanel.add(removeButton);
        inputPanel.add(markAsDoneButton);
        
        // Add components to the frame
        add(new JScrollPane(todoList), BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);
        
        // Action listeners for buttons
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String task = inputField.getText();
                if (!task.isEmpty()) {
                    todoModel.addElement(task);
                    inputField.setText("");
                }
            }
        });
        
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = todoList.getSelectedIndex();
                if (selectedIndex != -1) {
                    todoModel.remove(selectedIndex);
                }
            }
        });
        
        markAsDoneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = todoList.getSelectedIndex();
                if (selectedIndex != -1) {
                    String task = todoModel.getElementAt(selectedIndex);
                    todoModel.setElementAt("[DONE] " + task, selectedIndex);
                }
            }
        });
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Lista().setVisible(true);
            }
        });
    }
}
