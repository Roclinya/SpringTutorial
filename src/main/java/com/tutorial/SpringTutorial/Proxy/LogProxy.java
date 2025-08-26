package com.tutorial.SpringTutorial.Proxy;

import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.cglib.proxy.Proxy;

import java.lang.reflect.Method;

public class LogProxy implements InvocationHandler {
    Object subject;

    public Object getLogProxy(Object subject) {
        this.subject = subject;
        return Proxy.newProxyInstance(
                subject.getClass().getClassLoader(),
                subject.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Exception {
        System.out.println("dynamic proxy method starts ..." + method);

        Object result = method.invoke(subject, args);

        System.out.println("dynamic proxy method ends." + method);
        return result;
    }
}