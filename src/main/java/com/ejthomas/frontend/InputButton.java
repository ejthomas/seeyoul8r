package com.ejthomas.frontend;

import javax.swing.*;
import java.awt.event.*;

public class InputButton extends JButton {
    private Window window;
    public final String value;

    public InputButton(String value, Window window) {
        super(value);
        this.value = value;
        this.window = window;
        registerOutput();
    }

    public InputButton(String value, Window window, String name) {
        super(name);
        this.value = value;
        this.window = window;
        registerOutput();
    }

    public void onClick() {
        window.setInput(window.getInput() + value);
    }

    private void registerOutput() {
        this.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                onClick();
            }
        });
    }
}
