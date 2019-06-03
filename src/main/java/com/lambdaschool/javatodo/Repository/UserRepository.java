package com.lambdaschool.javatodo.Repository;

import com.lambdaschool.javatodo.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Integer> {

   public Users findByUsername(String name);
}
