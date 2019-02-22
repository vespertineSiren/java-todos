package com.lambdaschool.javatodo.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Todo {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer todoid;

    @Column(nullable = false)
    private String description;

    private String datestarted;

    private Date datetime;

    private Boolean completed;

    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    @JsonIgnore
    private Users user;

    public Todo() {
    }
}
