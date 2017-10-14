package com.jimscodecorner.demotodo.services;

import java.util.List;

import com.jimscodecorner.demotodo.entities.TodoItem;

public interface TodoItemService {
    public TodoItem save(TodoItem todoItem);

    public List<TodoItem> findAll();

    public void delete(long todoItemId);

    public TodoItem update(long todoItemId, TodoItem todoItem);

    public TodoItem findOne(long todoItemId);
}
