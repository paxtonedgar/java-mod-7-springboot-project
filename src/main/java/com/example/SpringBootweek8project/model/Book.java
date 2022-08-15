package com.example.SpringBootweek8project.model;

import com.example.SpringBootweek8project.dto.GenreDTO;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Entity
@Table(name = "books")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @Min(1)
    private int pages;

    @CreatedDate
    private LocalDate publishDate;

    @ManyToOne(cascade = CascadeType.ALL)
    private Author author;

    @ManyToMany(mappedBy = "bookList", cascade = CascadeType.DETACH)
    private List<Genre> genreList = new ArrayList<>();

}
