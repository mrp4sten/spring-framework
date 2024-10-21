package com.mavor.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mavor.models.Todo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class TodoRepository {

  @PersistenceContext
  private EntityManager entityManager;

  @Transactional
  public void save(Todo todo) {
    if (todo.getId() == null) {
      entityManager.persist(todo);
    } else {
      entityManager.merge(todo);
    }
  }

  public List<Todo> findAll() {
    return entityManager.createQuery("SELECT t FROM Todo t", Todo.class).getResultList();
  }

  public Todo findById(Long id) {
    return entityManager.find(Todo.class, id);
  }

  @Transactional
  public void delete(Todo todo) {
    Todo managedTodo = entityManager.merge(todo);
    entityManager.remove(managedTodo);
  }
}
