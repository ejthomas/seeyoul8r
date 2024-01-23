package com.ejthomas.frontend;

import javax.swing.*;

import com.ejthomas.backend.Parser;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.*;

public class Window extends JFrame {
    protected JTextField inputField;
    protected JLabel answerLabel;

    public static void main(String[] args) {
        Window window = new Window("SeeYouL8r Calculator");
        // System.out.println(window.toString());
        // System.out.println(window.getTitle());
    }

    public Window(String name) {
        super(name);

        // Display panel
        JPanel displayPanel = createDisplayPanel();
        this.getContentPane().add(displayPanel, BorderLayout.PAGE_START);

        // Lower panel
        JPanel lowerPanel = new JPanel();
        lowerPanel.setLayout(new BoxLayout(lowerPanel, BoxLayout.LINE_AXIS));
        this.getContentPane().add(lowerPanel, BorderLayout.CENTER);

        // Number panel
        JPanel numberPanel = createNumberPanel();
        lowerPanel.add(numberPanel);

        // Separator
        lowerPanel.add(Box.createHorizontalStrut(30));

        // Operation panel
        JPanel operationPanel = createOperationPanel();
        lowerPanel.add(operationPanel);


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setSize(400, 400);
        // this.setLayout(new BorderLayout());
        this.setResizable(false);
        this.setVisible(true);
    }

    public String getInput() {
        return inputField.getText();
    }

    public void setInput(String s) {
        inputField.setText(s);
    }

    public String getAnswer() {
        return answerLabel.getText();
    }

    public void setAnswer(String s) {
        answerLabel.setText(s);
    }

    private JPanel createDisplayPanel() {
        JPanel displayPanel = new JPanel();
        displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.LINE_AXIS));
        // this.getContentPane().add(displayPanel, BorderLayout.PAGE_START);

        inputField = new JTextField();
        // tf.setBounds(400, 50, 200, 50);
        inputField.setPreferredSize(new Dimension(100, 50));
        displayPanel.add(inputField);


        answerLabel = new JLabel();
        answerLabel.setPreferredSize(new Dimension(100, 50));
        // label.setBounds(400, 150, 200, 50);
        displayPanel.add(answerLabel);
        return displayPanel;
    }

    private JPanel createNumberPanel() {
        JPanel numberPanel = new JPanel();
        numberPanel.setLayout(new GridLayout(4, 3, 20, 20));

        for (int i = 1; i < 10; i++) {
            InputButton button = new InputButton(String.valueOf(i), this);
            numberPanel.add(button);
        }
        // zero goes last to match typical numpad
        numberPanel.add(new InputButton("0", this));
        return numberPanel;
    }

    private JPanel createOperationPanel() {
        JPanel operationPanel = new JPanel();
        operationPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        InputButton addButton = new InputButton("+", this);
        InputButton subButton = new InputButton("-", this);
        InputButton mulButton = new InputButton("*", this, "x");
        InputButton divButton = new InputButton("/", this);

        JButton clearButton = new JButton("CLEAR");
        clearButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                clearAction();
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 0.5;
        gbc.insets = new Insets(10, 10, 10, 10);
        operationPanel.add(clearButton, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        operationPanel.add(addButton, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        // gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.5;
        operationPanel.add(subButton, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        // gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.5;
        operationPanel.add(mulButton, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        // gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.5;
        operationPanel.add(divButton, gbc);

        // Equals button
        JButton equalsButton = new JButton("=");
        // Adds anonymous class implementing ActionListener
        equalsButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                equalsAction();
            }
        });
        gbc.weightx = 0.0;
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        operationPanel.add(equalsButton, gbc);
        return operationPanel;
    }

    public void clearAction() {
        setInput("");
        setAnswer("");
    }

    public void equalsAction() {
        Parser parser = new Parser(getInput());
        parser.evaluate();
        if (parser.isEvaluated()) {
            setAnswer(String.valueOf(parser.getResult()));
        } else {
            setAnswer("Syntax Error");
        }
    }

    // TODO:extension beyond single operation
}
