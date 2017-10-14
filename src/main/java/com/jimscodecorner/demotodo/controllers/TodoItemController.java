package com.jimscodecorner.demotodo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jimscodecorner.demotodo.entities.TodoItem;
import com.jimscodecorner.demotodo.services.TodoItemService;

@RestController
@RequestMapping(value = "/api/v1/todo-item")
public class TodoItemController {

    @Autowired
    TodoItemService todoItemService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public @ResponseBody TodoItem createTodoItem(@RequestBody TodoItem todoItem) {
	return todoItemService.save(todoItem);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public @ResponseBody List<TodoItem> getTodoList() {
	return todoItemService.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public @ResponseBody void delete(@PathVariable("id") Long idToDelete) {
	todoItemService.delete(idToDelete);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public @ResponseBody TodoItem update(@PathVariable("id") Long idToUpdate, @RequestBody TodoItem todoItem) {
	return todoItemService.update(idToUpdate, todoItem);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody TodoItem findOne(@PathVariable("id") Long todoItemId) {
	return todoItemService.findOne(todoItemId);
    }

}
