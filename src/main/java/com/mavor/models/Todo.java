package com.mavor.models;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TODOS")
public class Todo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "title", length = 50, nullable = false, unique = true)
  private String title;
  @Column(name = "description", length = 100, nullable = false, unique = false)
  private String description;
  @Column(name = "target_date", nullable = false, unique = false)
  private Date targetDate;
  @Column(name = "is_done", nullable = false, unique = false)
  private boolean isDone;

  // Required by JPA
  // Default constructor
  public Todo() {
  }

  // Parameterized constructor
  public Todo(String title, String description, Date targetDate, boolean isDone) {
    this.title = title;
    this.description = description;
    this.targetDate = targetDate;
    this.isDone = isDone;
  }

  public Long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Date getTargetDate() {
    return targetDate;
  }

  public void setTargetDate(Date targetDate) {
    this.targetDate = targetDate;
  }

  public boolean isDone() {
    return isDone;
  }

  public void setDone(boolean isDone) {
    this.isDone = isDone;
  }

  @Override
  public String toString() {
    return "Todo [id=" + id + ", title=" + title + ", description=" + description + ", targetDate=" + targetDate
        + ", isDone=" + isDone + "]";
  }

}
