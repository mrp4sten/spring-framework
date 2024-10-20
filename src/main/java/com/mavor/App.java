package com.mavor;

import java.sql.Date;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.mavor.config.AppConfig;
import com.mavor.models.Todo;
import com.mavor.service.TodoService;

public class App {
  public static void main(String[] args) {

    try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class)) {
        TodoService todoService = context.getBean(TodoService.class);

        Todo newTodo = new Todo("this is a title", "description for my task", new Date(System.currentTimeMillis()), false);
        todoService.save(newTodo);

        Todo newTodo2 = new Todo("this is a title 2", "description for my task 2", new Date(System.currentTimeMillis()), false);
        todoService.save(newTodo2);

        todoService.findAll()
            .forEach(todo -> System.out.println("Todo: " + todo.getTitle() + ", Completed: " + todo.isDone()));
    }
  }

}
