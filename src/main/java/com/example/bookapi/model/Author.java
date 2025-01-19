package com.example.bookapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table(name = "master_author")
@Entity
@Getter
@Setter
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column(name = "name") // custom column
    private String name;

    @Column(name = "biography") // custom column
    private String biography;

}
