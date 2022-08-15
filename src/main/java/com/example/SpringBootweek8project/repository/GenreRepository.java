package com.example.SpringBootweek8project.repository;

import com.example.SpringBootweek8project.model.Book;
import com.example.SpringBootweek8project.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
    Genre findByName(String name);
}
