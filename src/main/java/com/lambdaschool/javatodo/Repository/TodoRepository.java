package com.lambdaschool.javatodo.Repository;

import com.lambdaschool.javatodo.Models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Integer> {
}
