package com.github.brandysm.electroniclabnotebook.backendserver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.brandysm.electroniclabnotebook.backendserver.dataaccessobject.TodoRepository;
import com.github.brandysm.electroniclabnotebook.backendserver.datatransferobject.Account;
import com.github.brandysm.electroniclabnotebook.backendserver.datatransferobject.Todo;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    public Todo saveTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    public Todo getTodo(Long id, Account account) {
        Todo todo = todoRepository.findById(id).get();
        if (todo.getAccount() == account) {
            return todo;
        } else {
            return null;
        }
    }

    public void deleteTodo(Long id, Account account) {
        Todo todo = todoRepository.findById(id).get();
        if (todo.getAccount() == account) {
            todoRepository.deleteById(id);
        }
    }

    public List<Todo> getTodos(Account account) {
        return todoRepository.findByAccount(account);
    }

    public void deleteTodos(Account account) {
        todoRepository.deleteByAccount(account);
    }
}
