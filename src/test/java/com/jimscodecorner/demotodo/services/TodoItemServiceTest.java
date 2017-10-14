package com.jimscodecorner.demotodo.services;

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
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.jimscodecorner.demotodo.entities.TodoItem;
import com.jimscodecorner.demotodo.repositories.TodoItemRepository;

@RunWith(MockitoJUnitRunner.class)
public class TodoItemServiceTest {

    @InjectMocks
    TodoItemServiceImpl subject;

    @Mock
    TodoItemRepository todoItemRepository;

    @Test
    public void saveShouldReturnTodoItem() {
	final TodoItem todoItem = this.makeTodoItem(100, "Test Item");

	when(todoItemRepository.save(todoItem)).thenReturn(todoItem);

	assertThat(subject.save(todoItem).getId(), equalTo(100L));
	assertThat(subject.save(todoItem).getDescription(), equalTo("Test Item"));
    }

    @Test
    public void findAllShouldReturnAllTodoItems() {
	final List<TodoItem> todoList = new ArrayList<TodoItem>();
	todoList.add(this.makeTodoItem(100, "Test item"));

	when(todoItemRepository.findAll()).thenReturn(todoList);

	final List<TodoItem> result = subject.findAll();

	assertThat(result.size(), equalTo(1));

    }

    @Test
    public void deleteShouldCallDeleteRepository() {
	final long idToDelete = 100L;

	doNothing().when(todoItemRepository).delete(idToDelete);

	subject.delete(idToDelete);

	verify(todoItemRepository).delete(idToDelete);

    }

    @Test
    public void updatShouldCallUpdateRepository() {
	final long idToUpdate = 100L;

	final TodoItem todoItem = this.makeTodoItem(idToUpdate, "description");
	when(todoItemRepository.save(todoItem)).thenReturn(todoItem);
	when(todoItemRepository.findOne(idToUpdate)).thenReturn(todoItem);

	final TodoItem result = subject.update(idToUpdate, todoItem);

	verify(todoItemRepository).findOne(idToUpdate);
	verify(todoItemRepository).save(Mockito.any(TodoItem.class));
	assertThat(result.getDescription(), equalTo("description"));
	assertThat(result.getId(), equalTo(100L));

    }

    @Test
    public void findOneShouldCallFindOneRepository() {
	final long id = 100L;
	final TodoItem todoItem = this.makeTodoItem(id, "description");
	when(todoItemRepository.findOne(id)).thenReturn(todoItem);

	final TodoItem result = subject.findOne(id);

	verify(todoItemRepository).findOne(id);
	assertThat(result.getDescription(), equalTo("description"));
	assertThat(result.getId(), equalTo(100L));

    }

    private TodoItem makeTodoItem(long id, String description) {
	final TodoItem todoItem = new TodoItem();

	todoItem.setDescription(description);
	todoItem.setId(id);

	return todoItem;
    }

}
