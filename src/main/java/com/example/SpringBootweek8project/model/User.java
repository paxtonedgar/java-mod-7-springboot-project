package com.example.SpringBootweek8project.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @NotNull
    private String username;

    @NotBlank
    @NotNull
    private String password;

    @OneToMany
    private List<ReadingList> readingList = new ArrayList<>();



}
