package com.mavor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
  public static void main(String[] args) {
    // Load Spring context from the XML file
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

    // Get the HelloWorld bean and call the sayHello method
    HelloWorld helloWorld = (HelloWorld) context.getBean("helloWorld");
    helloWorld.sayHello();
  }

}
