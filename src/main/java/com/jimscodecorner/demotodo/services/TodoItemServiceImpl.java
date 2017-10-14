package com.jimscodecorner.demotodo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jimscodecorner.demotodo.entities.TodoItem;
import com.jimscodecorner.demotodo.repositories.TodoItemRepository;

@Service
public class TodoItemServiceImpl implements TodoItemService {

    @Autowired
    TodoItemRepository todoItemRepository;

    @Override
    public TodoItem save(TodoItem todoItem) {
	return todoItemRepository.save(todoItem);
    }

    @Override
    public List<TodoItem> findAll() {
	return todoItemRepository.findAll();
    }

    @Override
    public void delete(long todoItemId) {
	todoItemRepository.delete(todoItemId);
    }

    @Override
    public TodoItem update(long todoItemId, TodoItem updatedTodoItem) {

	final TodoItem todoItem = todoItemRepository.findOne(todoItemId);

	todoItem.setDescription(updatedTodoItem.getDescription());

	return todoItemRepository.save(todoItem);

    }

    @Override
    public TodoItem findOne(long todoItemId) {
	return todoItemRepository.findOne(todoItemId);
    }

}
