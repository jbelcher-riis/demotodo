package com.jimscodecorner.demotodo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jimscodecorner.demotodo.entities.TodoItem;

public interface TodoItemRepository extends JpaRepository<TodoItem, Long> {

}
