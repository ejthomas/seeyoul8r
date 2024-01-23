package com.ejthomas.frontend;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InputButtonTest {
    @Test
    public void constructorNoName() {
        Window window = new Window("dummy");
        InputButton button = new InputButton("3", window);
        Assertions.assertInstanceOf(InputButton.class, button);
        Assertions.assertEquals("3", button.value);
        Assertions.assertEquals("3", button.getText());
    }

    @Test
    public void constructorWithName() {
        Window window = new Window("dummy");
        InputButton button = new InputButton("*", window, "x");
        Assertions.assertInstanceOf(InputButton.class, button);
        Assertions.assertEquals("*", button.value);
        Assertions.assertEquals("x", button.getText());
    }

    @Test
    public void onClick() {
        Window window = new Window("dummy");
        InputButton button = new InputButton("*", window, "x");
        window.setInput("14");
        button.onClick();
        Assertions.assertEquals("14*", window.getInput());
    }
}
