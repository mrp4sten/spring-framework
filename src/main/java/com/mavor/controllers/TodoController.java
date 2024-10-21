package com.mavor.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mavor.models.Todo;
import com.mavor.service.TodoService;



@RestController
@RequestMapping("/todos")
public class TodoController {

  private TodoService todoService;

  private static final Logger logger = LoggerFactory.getLogger(TodoController.class);

  @Autowired
  public TodoController(TodoService todoService) {
    this.todoService = todoService;
  }

  @GetMapping(produces = "application/json")
  public List<Todo> getAllTodos() {
    return todoService.findAll();
  }

  @GetMapping(value = "/{id}",  produces = "application/json")
  public ResponseEntity<Todo> getTodoById(@PathVariable("id") Long id) {
    Todo todo = todoService.findById(id);
    if (todo == null) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(todo);
  }

  @PostMapping(consumes = "application/json", produces = "application/json")
  public Todo addTodo(@RequestBody Todo todo) {
    todoService.save(todo);
    return todo;
  }

  @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
  public ResponseEntity<Todo> updateTodo(@PathVariable("id") Long id, @RequestBody Todo todoDetails) {
    Todo todo = todoService.findById(id);
    if (todo == null) {
      logger.error("Todo with id {} not found", id);
      return ResponseEntity.notFound().build();
    }
    logger.info("Updating todo with id {}", id);
    todo.setTitle(todoDetails.getTitle());
    todo.setDescription(todoDetails.getDescription());
    todo.setTargetDate(todoDetails.getTargetDate());
    todo.setDone(todoDetails.isDone());
    todoService.save(todo);
    return ResponseEntity.ok(todo);
  }

  @DeleteMapping(value = "/{id}", produces = "application/json")
  public ResponseEntity<Void> deleteTodo(@PathVariable("id") Long id) {
    Todo todo = todoService.findById(id);
    if (todo == null) {
      return ResponseEntity.notFound().build();
    }
    todoService.delete(todo);
    return ResponseEntity.noContent().build();
  }
}
