package com.lambdaschool.javatodo.Models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer userid;

    @Column(nullable = false)
    private String username;

    public Users() {
    }
}
