package com.ejthomas.frontend;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class WindowTest {
    @Test
    public void constructor() {
        Window window = new Window("Test name");
        Assertions.assertInstanceOf(Window.class, window);
        Assertions.assertEquals("Test name", window.getTitle());
    }

    @Test
    public void input() {
        Window window = new Window("Window");
        // Get default value
        Assertions.assertEquals("", window.getInput());
        // Set new value
        window.setInput("2*2");
        // Get updated value
        Assertions.assertEquals("2*2", window.getInput());
    }

    @Test
    public void answer() {
        Window window = new Window("Window");
        // Get default value
        Assertions.assertEquals("", window.getAnswer());
        // Set new value
        window.setAnswer("120");
        // Get updated value
        Assertions.assertEquals("120", window.getAnswer());
    }

    @Test
    public void equalsAction() {
        Window window = new Window("Window");
        window.equalsAction();
        Assertions.assertEquals("Syntax Error", window.getAnswer());
    }

    @Test
    public void clearAction() {
        Window window = new Window("Window");
        window.setInput("9*6");
        window.setAnswer("54");
        Assertions.assertEquals("9*6", window.getInput());
        Assertions.assertEquals("54", window.getAnswer());
        window.clearAction();
        Assertions.assertEquals("", window.getInput());
        Assertions.assertEquals("", window.getAnswer());
    }
}
