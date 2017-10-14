package com.jimscodecorner.demotodo.controllers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.jimscodecorner.demotodo.entities.TodoItem;
import com.jimscodecorner.demotodo.services.TodoItemService;

@RunWith(MockitoJUnitRunner.class)
public class TodoItemControllerTest {

    @InjectMocks
    TodoItemController subject;

    @Mock
    TodoItemService todoItemService;

    @Test
    public void createTodoItemShouldReturnTodoItem() {
	final TodoItem todoItem = this.makeTodoItem(100, "Test item");

	when(todoItemService.save(todoItem)).thenReturn(todoItem);

	assertThat(subject.createTodoItem(todoItem).getId(), equalTo(100L));
	assertThat(subject.createTodoItem(todoItem).getDescription(), equalTo("Test item"));
    }

    @Test
    public void getTodoItemByIdShouldReturnListOfItems() {
	final List<TodoItem> todoList = new ArrayList<TodoItem>();
	todoList.add(this.makeTodoItem(100, "Test item"));

	when(todoItemService.findAll()).thenReturn(todoList);

	assertThat(subject.getTodoList().size(), equalTo(1));
    }

    @Test
    public void deleteTodoItemShouldCallDelete() {
	final long idToDelete = 42L;

	doNothing().when(todoItemService).delete(idToDelete);

	subject.delete(idToDelete);

	verify(todoItemService).delete(idToDelete);
    }

    @Test
    public void updateShouldReturnTodoItem() {
	final long idToUpdate = 42L;
	final TodoItem todoItem = this.makeTodoItem(idToUpdate, "test item");

	when(todoItemService.update(idToUpdate, todoItem)).thenReturn(todoItem);

	final TodoItem result = subject.update(idToUpdate, todoItem);

	verify(todoItemService).update(idToUpdate, todoItem);
	assertThat(result.getId(), equalTo(42L));
	assertThat(result.getDescription(), equalTo("test item"));
    }

    @Test
    public void findOneShouldReturnATodoItem() {
	final long id = 42L;
	final TodoItem todoItem = this.makeTodoItem(id, "test item");

	when(todoItemService.findOne(id)).thenReturn(todoItem);

	final TodoItem result = subject.findOne(id);

	verify(todoItemService).findOne(id);
	assertThat(result.getId(), equalTo(42L));
	assertThat(result.getDescription(), equalTo("test item"));
    }

    private TodoItem makeTodoItem(long id, String description) {
	final TodoItem todoItem = new TodoItem();

	todoItem.setDescription(description);
	todoItem.setId(id);

	return todoItem;
    }
}
