package com.tutorial.SpringTutorial.reflect;

public class DecoratorForMilk extends CoffeeDecorator{

    public DecoratorForMilk(Coffee coffee) {
        super(coffee);
    }

    @Override
    public double getCost() {
        return super.getCost()+10;
    }

    @Override
    public String getDescription() {
        return super.getDescription()+", Milk";
    }
}
