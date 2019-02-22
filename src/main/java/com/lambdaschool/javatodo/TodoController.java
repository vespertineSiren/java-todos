package com.lambdaschool.javatodo;

import com.lambdaschool.javatodo.Models.Todo;
import com.lambdaschool.javatodo.Models.Users;
import com.lambdaschool.javatodo.Repository.TodoRepository;
import com.lambdaschool.javatodo.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

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

   @PostMapping("/users")
    public Users addANewUser(@RequestBody Users users) throws URISyntaxException{
        return userRepository.save(users);
   }

   @PostMapping("/todos")
    public Todo addANewTodo(@RequestBody Todo todo) throws URISyntaxException {
        return todoRepository.save(todo);
   }

    @PutMapping("/users/userid/{userid}")
    public Users updateUser(@RequestBody Users newUser, @PathVariable Integer userid) throws URISyntaxException{
        Optional<Users> userUpdate = userRepository.findById(userid);
        if(userUpdate.isPresent()){
            newUser.setUserid(userid);
            userRepository.save(newUser);
            return newUser;
        }
        return null;
    }

    @PutMapping("/todos/todoid/{todoid}")
    public Todo updateTodo(@RequestBody Todo newTodo, @PathVariable Integer todoID) throws URISyntaxException{
        Optional<Todo> todoUpdate = todoRepository.findById(todoID);
        if(todoUpdate.isPresent()){
            newTodo.setTodoid(todoID);
            todoRepository.save(newTodo);
            return newTodo;
        }
        return null;
    }

    @DeleteMapping("/users/userid/{userid}")
    public Users deleteUserByID(@PathVariable int userid) {
        var foundUser = userRepository.findById(userid);
        if (foundUser.isPresent()) {
            List<Todo> todosFromUser = todoRepository.getUserTodos(userid);
            for (Todo todo : todosFromUser) {
                todoRepository.deleteById(todo.getTodoid());
            }
            userRepository.deleteById(userid);
            return foundUser.get();
        } else {
            return null;
        }
    }

    @DeleteMapping("/todos/todoid/{todoid}")
    public Todo deleteTodoByTodoid(@PathVariable Integer todoid){
        var foundTodo = todoRepository.findById(todoid);
        if(foundTodo.isPresent()){
            todoRepository.deleteById(todoid);
            return foundTodo.get();
        }
        return null;
    }


}
