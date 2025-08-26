package com.tutorial.SpringTutorial.Service.Impl;

import com.tutorial.SpringTutorial.Service.IHello;

public class HelloSpeaker implements IHello {
    public void hello(String name) {
        System.out.println("hello " + name);
    }
}
