package com.lambdaschool.javatodo;

import com.lambdaschool.javatodo.Models.Todo;
import com.lambdaschool.javatodo.Models.Users;
import com.lambdaschool.javatodo.Repository.TodoRepository;
import com.lambdaschool.javatodo.Repository.UserRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Api(value = "Java Todo App", description = "Backed for the JX Sprint 3")
@RestController
@RequestMapping(path = {}, produces = MediaType.APPLICATION_JSON_VALUE)
public class TodoController {

    @Autowired
    TodoRepository todoRepository;

    @Autowired
    UserRepository userRepository;

    @ApiOperation(value = "Gets all the users", response = List.class)
    @ApiResponses(value = {
                    @ApiResponse(code = 200, message = "Successfully retrieves list"),
                    @ApiResponse(code = 401, message = "You are not authorized to the view the resource"),
                    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
                    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("/users")
    public List<Users> getAllUsers(){
        return userRepository.findAll();
    }

    @ApiOperation(value = "Gets all the todos", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieves list"),
            @ApiResponse(code = 401, message = "You are not authorized to the view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("/todos")
    public List<Todo> getAllTodos(){
        return todoRepository.findAll();
    }

    @ApiOperation(value = "Gets a user based on userid provided", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved user"),
            @ApiResponse(code = 401, message = "You are not authorized to the view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("/users/userid/{userid}")
    public Users getUserBasedOffUserID(@PathVariable Integer userid){
        return userRepository.findById(userid).orElseThrow();
    }


    @ApiOperation(value = "Gets a user based on username provided", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved user"),
            @ApiResponse(code = 401, message = "You are not authorized to the view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("/users/username/{username}")
    public Users getUserBasedOffUserName(@PathVariable String username){
        return userRepository.findByUsername(username);
    }

    @ApiOperation(value = "Gets a todo task based on todoid provided", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved todo task"),
            @ApiResponse(code = 401, message = "You are not authorized to the view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
   @GetMapping("/todos/todoid/{todoid}")
    public Todo getTodoBasedOffTodoId(@PathVariable Integer todoid){
        return todoRepository.findByTodoid(todoid);
   }

    @ApiOperation(value = "Gets a list of todo tasks assigned to each user", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to the view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
   @GetMapping("/todos/users")
    public List<Object[]> getTodoWithAssignedUser() {
        return todoRepository.todosWithUsersOrderedByDateStarted();
   }

    @ApiOperation(value = "Gets a list of incomplete todo tasks", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved uncompleted todo list"),
            @ApiResponse(code = 401, message = "You are not authorized to the view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
   @GetMapping("/todos/active")
    public List<Todo> getIncompleteTodo(){
        return todoRepository.findByUncompleted();
   }

    @ApiOperation(value = "Adds a user", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully added"),
            @ApiResponse(code = 401, message = "You are not authorized to the view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
   @PostMapping("/users")
    public Users addANewUser(@RequestBody Users users) throws URISyntaxException{
        return userRepository.save(users);
   }

    @ApiOperation(value = "Adds a todo task", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully added"),
            @ApiResponse(code = 401, message = "You are not authorized to the view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
   @PostMapping("/todos")
    public Todo addANewTodo(@RequestBody Todo todo) throws URISyntaxException {
        return todoRepository.save(todo);
   }

    @ApiOperation(value = "Updates user's id", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated"),
            @ApiResponse(code = 401, message = "You are not authorized to the view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
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

    @ApiOperation(value = "Updates todo's id", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated"),
            @ApiResponse(code = 401, message = "You are not authorized to the view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @PutMapping("/todos/todoid/{todoid}")
    public Todo updateTodo(@RequestBody Todo newTodo, @PathVariable Integer todoid) throws URISyntaxException{
        Optional<Todo> todoUpdate = todoRepository.findById(todoid);
        if(todoUpdate.isPresent()){
            newTodo.setTodoid(todoid);
            todoRepository.save(newTodo);
            return newTodo;
        }
        return null;
    }

    @ApiOperation(value = "Deletes a user with given userid", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted"),
            @ApiResponse(code = 401, message = "You are not authorized to the view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
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

    @ApiOperation(value = "Deletes a todo with given todo id", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted"),
            @ApiResponse(code = 401, message = "You are not authorized to the view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
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
