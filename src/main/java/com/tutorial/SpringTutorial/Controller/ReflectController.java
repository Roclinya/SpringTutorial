package com.tutorial.SpringTutorial.Controller;

import com.tutorial.SpringTutorial.reflect.Coffee;
import com.tutorial.SpringTutorial.reflect.CoffeeDecorator;
import com.tutorial.SpringTutorial.reflect.SimpleCoffee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

@RequestMapping("/api")
@RestController
public class ReflectController {


    @GetMapping("/getDecoratorInstance")
    public ResponseEntity<String> getDecoratorInstance(@RequestParam String productName) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Coffee coffee = getSimpleCoffee();
        CoffeeDecorator decorator = this.getProductFromReflection(productName, coffee);
        return ResponseEntity.ok("Bill => cost: "+decorator.getCost() + ",Desc: "+decorator.getDescription());
    }

    private Coffee getSimpleCoffee() {
        return new SimpleCoffee();
    }

    private CoffeeDecorator getProductFromReflection(String productName, Coffee coffee) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        productName = productName.trim().replace(" ","");
        Class<?> clazz = Class.forName("com.tutorial.SpringTutorial.reflect.DecoratorFor" + productName.substring(0, 1) + productName.substring(1).toLowerCase());
        Constructor<?> conStructor = clazz.getDeclaredConstructor(Coffee.class);
        //設定私有構造可以訪問
        conStructor.setAccessible(true);
        CoffeeDecorator decorator = (CoffeeDecorator) conStructor.newInstance(coffee);

        return decorator;
    }

    ;
}
