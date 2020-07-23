package io.impl;

import io.Printer;

public class ConsolePrinter implements Printer {

    @Override
    public void print(String result) {
        System.out.println(result);
    }
}
