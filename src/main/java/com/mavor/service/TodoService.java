package com.mavor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mavor.models.Todo;
import com.mavor.repository.TodoRepository;

@Service
public class TodoService {
  private final TodoRepository todoRepository;

  @Autowired
  public TodoService(TodoRepository todoRepository) {
    this.todoRepository = todoRepository;
  }

  public void save(Todo todo) {
    todoRepository.save(todo);
  }

  public List<Todo> findAll() {
    return todoRepository.findAll();
  }

  public Todo findById(Long id) {
    return todoRepository.findById(id);
  }

  public void delete(Todo todo) {
    todoRepository.delete(todo);
  }

}
