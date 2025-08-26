package com.tutorial.SpringTutorial.Generics;


public class Printer<T> {
    // Printer<WhatEverYouWant>
    T thingToPrint;

    public Printer(T thingToPrint){
        this.thingToPrint=thingToPrint;
    }

    public void print(){
        System.out.println(thingToPrint);
    }

}
