package com.lambdaschool.javatodo;

import com.lambdaschool.javatodo.Models.Todo;
import com.lambdaschool.javatodo.Models.Users;
import com.lambdaschool.javatodo.Repository.TodoRepository;
import com.lambdaschool.javatodo.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/users/userid/{userid}")
    public Users getUserBasedOffUserID(Integer userid){
        return userRepository.findById(userid).orElseThrow();
    }

    @GetMapping("/users/username/{username}")
    public Users getUserBasedOffUserName(@PathVariable String username){
        return userRepository.findByUsername(username);
    }

   @GetMapping("/todos/todoid/{todoid}")
    public Todo getTodoBasedOffTodoId(@PathVariable Integer todoid){
        return todoRepository.findByTodoid(todoid);
   }

   @GetMapping("/todos/users")
    public List<Object[]> getTodoWithAssignedUser() {
        return todoRepository.todosWithUsersOrderedByDateStarted();
   }

   @GetMapping("/todos/active")
    public List<Todo> getUncompleteTodo(){
        return todoRepository.findByUncompleted();
   }



}
