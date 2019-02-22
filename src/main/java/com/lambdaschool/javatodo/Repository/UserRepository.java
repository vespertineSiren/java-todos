package com.lambdaschool.javatodo.Repository;

import com.lambdaschool.javatodo.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Integer> {
}
