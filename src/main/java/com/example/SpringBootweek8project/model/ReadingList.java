package com.example.SpringBootweek8project.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "readingLists")
@Getter
@Setter
public class ReadingList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @ManyToOne
    private User user;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id")
    private List<Book> bookList;

}
