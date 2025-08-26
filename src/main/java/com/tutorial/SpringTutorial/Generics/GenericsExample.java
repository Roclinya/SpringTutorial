package com.tutorial.SpringTutorial.Generics;

import java.util.ArrayList;

public class GenericsExample {
    public static void main(String[] args) {
//        IntegerPrinter printer = new IntegerPrinter(23);
        Printer<Integer> printer = new Printer<>(23);
        printer.print();

        Printer<Double> doublePrinter = new Printer<>(33.2);
        doublePrinter.print();


    }
}
