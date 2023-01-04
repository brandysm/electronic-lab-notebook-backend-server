package com.github.brandysm.electroniclabnotebook.backendserver.applicationprogramminginterface;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.brandysm.electroniclabnotebook.backendserver.datatransferobject.Account;
import com.github.brandysm.electroniclabnotebook.backendserver.datatransferobject.Todo;
import com.github.brandysm.electroniclabnotebook.backendserver.service.AccountService;
import com.github.brandysm.electroniclabnotebook.backendserver.service.TodoService;

@RestController
public class TodoController {

    @Autowired
    TodoService todoService;

    @Autowired
    AccountService accountService;

    @GetMapping("/todo")
    public List<Todo> getTodos(Principal principal) {
        Account account = accountService.getAccount(principal.getName());
        List<Todo> response = todoService.getTodos(account);
        response.forEach((t) -> t.setAccount(null));
        return response;
    }

    @PostMapping("/todo")
    public ResponseEntity<Todo> createTodo(@RequestBody Todo todo, Principal principal) {
        Account account = accountService.getAccount(principal.getName());
        todo.setAccount(account);
        Todo response = todoService.saveTodo(todo);
        response.setAccount(null);
        return ResponseEntity.created(null).body(response);
    }

    @DeleteMapping("/todo")
    public void deleteTodos(Principal principal) {
        Account account = accountService.getAccount(principal.getName());
        todoService.deleteTodos(account);
    }

    @GetMapping("/todo/{id}")
    public Todo getTodo(@PathVariable Long id, Principal principal) {
        Account account = accountService.getAccount(principal.getName());
        Todo response = todoService.getTodo(id, account);
        response.setAccount(null);
        return response;
    }

    @PatchMapping("/todo/{id}")
    public Todo updateTodo(@PathVariable Long id, @RequestBody Todo todo, Principal principal) {
        Account account = accountService.getAccount(principal.getName());
        Todo todo2 = todoService.getTodo(id, account);
        if (todo2 != null) {
            if (todo.getTitle() != null)
                todo2.setTitle(todo.getTitle());
            if (todo.getCompleted() != null)
                todo2.setCompleted(todo.getCompleted());
            Todo response = todoService.saveTodo(todo2);
            response.setAccount(null);
            return response;
        }
        return null;
    }

    @DeleteMapping("/todo/{id}")
    public void deleteTodo(@PathVariable Long id, Principal principal) {
        Account account = accountService.getAccount(principal.getName());
        todoService.deleteTodo(id, account);
    }
}
