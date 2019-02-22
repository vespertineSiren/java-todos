package com.lambdaschool.javatodo;

import com.lambdaschool.javatodo.Models.Todo;
import com.lambdaschool.javatodo.Models.Users;
import com.lambdaschool.javatodo.Repository.TodoRepository;
import com.lambdaschool.javatodo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = {}, produces = MediaType.APPLICATION_JSON_VALUE)
public class TodoController {

    @Autowired
    TodoRepository todoRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    public List<Users> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/todos")
    public List<Todo> getAllTodos(){
        return todoRepository.findAll();
    }

}
