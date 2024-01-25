package com.ejthomas.frontend;

import javax.swing.*;
import javax.swing.border.Border;

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
    private String lastAnswer = "";
    private final JPanel displayPanel;
    private final JPanel numberPanel;
    private final JPanel operationPanel;
    private final JPanel lowerPanel;

    public static void main(String[] args) {
        Window window = new Window("SeeYouL8r Calculator");
        window.setVisible(true);
    }

    public Window(String name) {
        // Window will not be visible until this.setVisible(true) called
        super(name);

        // Display panel
        displayPanel = createDisplayPanel();
        this.getContentPane().add(displayPanel, BorderLayout.PAGE_START);

        // Lower panel
        lowerPanel = new JPanel();
        lowerPanel.setLayout(new BoxLayout(lowerPanel, BoxLayout.LINE_AXIS));
        this.getContentPane().add(lowerPanel, BorderLayout.CENTER);

        // Number panel
        numberPanel = createNumberPanel();
        lowerPanel.add(numberPanel);

        // Separator
        lowerPanel.add(Box.createHorizontalStrut(30));

        // Operation panel
        operationPanel = createOperationPanel();
        lowerPanel.add(operationPanel);


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setSize(600, 400);
        this.setResizable(false);
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
        displayPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        inputField = new JTextField();
        inputField.setPreferredSize(new Dimension(0, 50));
        displayPanel.add(inputField);

        // displayPanel.add(Box.createHorizontalStrut(20));

        answerLabel = new JLabel();
        // answerLabel.setPreferredSize(new Dimension(100, 50));
        // answerLabel.setMinimumSize(new Dimension(200, 50));
        displayPanel.add(answerLabel);
        // displayPanel.add(Box.createHorizontalStrut(20), 1);
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

        // Decimal point button
        numberPanel.add(new InputButton(".", this));

        // Previous answer button
        JButton lastAnswerButton = new JButton("ANS");
        lastAnswerButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                lastAnswerAction();
            }
        });
        numberPanel.add(lastAnswerButton);
        return numberPanel;
    }

    private JPanel createOperationPanel() {
        JPanel operationPanel = new JPanel();
        operationPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JButton clearButton = new JButton("CLEAR");
        clearButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                clearAction();
            }
        });
        InputButton powerButton = new InputButton("^", this, "a^b");
        InputButton leftParenButton = new InputButton("(", this);
        InputButton rightParenButton = new InputButton(")", this);
        InputButton addButton = new InputButton("+", this);
        InputButton subButton = new InputButton("-", this);
        InputButton mulButton = new InputButton("*", this, "x");
        InputButton divButton = new InputButton("/", this);

        // Global constraints
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        // Clear button
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;
        operationPanel.add(clearButton, gbc);

        // Power button
        gbc.gridx += 1;
        operationPanel.add(powerButton, gbc);

        // Left parenthesis
        gbc.gridx = 0;
        gbc.gridy += 1;
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;
        gbc.gridwidth = 1;
        operationPanel.add(leftParenButton, gbc);

        // Right parenthesis
        gbc.gridx += 1;
        operationPanel.add(rightParenButton, gbc);

        // Add button
        gbc.gridx = 0;
        gbc.gridy += 1;
        operationPanel.add(addButton, gbc);

        // Subtract button
        gbc.gridx += 1;
        operationPanel.add(subButton, gbc);

        // Multiply button
        gbc.gridx = 0;
        gbc.gridy += 1;
        operationPanel.add(mulButton, gbc);

        // Divide button
        gbc.gridx += 1;
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
        gbc.gridy += 1;
        gbc.gridwidth = 2;
        operationPanel.add(equalsButton, gbc);
        return operationPanel;
    }

    public void clearAction() {
        setInput("");
        setAnswer("");
        // Remove extra space between input and answer fields
        displayPanel.remove(1);
    }

    public void equalsAction() {
        Parser parser = new Parser(getInput());
        parser.evaluate();
        if (parser.isEvaluated()) {
            double result = parser.getResult();
            if (result == (int)result) {
                String answer = String.valueOf((int)result);
                setAnswer(answer);
                lastAnswer = answer;
            } else {
                String answer = String.valueOf(result);
                setAnswer(answer);
                lastAnswer = answer;
            }
        } else if (parser.isInErrorState()) {
            setAnswer(parser.getLastError());
        } else {
            setAnswer("Unknown Error");
        }
        // Increase space between input and answer fields
        displayPanel.add(Box.createHorizontalStrut(20), 1); 
    }

    public void lastAnswerAction() {
        setInput(getInput() + lastAnswer);
    }
}
