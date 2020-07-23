package io.impl;

import io.InputReader;

import java.util.Scanner;

public class InputConsoleReader implements InputReader {
    private Scanner scanner;

    public InputConsoleReader() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public String readLine() {
       return this.scanner.nextLine();
    }
}
