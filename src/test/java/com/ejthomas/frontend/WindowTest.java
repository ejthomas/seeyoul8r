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
    public void givenEmptyInput_whenEqualsAction_thenAnswerIsError() {
        Window window = new Window("Window");
        window.equalsAction();
        Assertions.assertEquals("Expected non-empty", window.getAnswer());
    }

    @Test
    public void givenValidInput_whenEqualsAction_thenIntegerAnswerIsCorrect() {
        Window window = new Window("Window");
        window.setInput("15*2");
        window.equalsAction();
        Assertions.assertEquals("30", window.getAnswer());
    }

    @Test
    public void givenValidInput_whenEqualsAction_thenDoubleAnswerIsCorrect() {
        Window window = new Window("Window");
        window.setInput("1.8*3");
        window.equalsAction();
        Assertions.assertEquals("5.4", window.getAnswer());
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

    @Test
    public void whenLastAnswerAction_thenAppendsLastAnswer() {
        Window window = new Window("Window");

        // Empty if no previous answer
        window.lastAnswerAction();
        Assertions.assertEquals("", window.getInput());

        // Not updated by Window.setAnswer()
        window.setAnswer("17");
        window.lastAnswerAction();
        Assertions.assertEquals("", window.getInput());

        // Updated on evaluation of valid expression
        window.setInput("1000-80");
        window.equalsAction();
        window.clearAction();
        window.lastAnswerAction();
        Assertions.assertEquals("920", window.getInput());
    }
}
