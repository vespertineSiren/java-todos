package com.lambdaschool.javatodo.Repository;

import com.lambdaschool.javatodo.Models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Integer> {

    public Todo findByTodoid(Integer todoid);

    @Query(value = "SELECT users.username, users.userid, todo.description, todo.datestarted, todo.completed FROM todo JOIN users on todo.userid ORDER By users.username, todo.datestarted ASC", nativeQuery = true)
    List<Object[]> todosWithUsersOrderedByDateStarted();

    @Query(value = "SELECT * FROM todo WHERE todo.completed = 0", nativeQuery = true)
    List<Todo> findByUncompleted();
}
