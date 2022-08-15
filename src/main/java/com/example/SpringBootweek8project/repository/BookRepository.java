package com.example.SpringBootweek8project.repository;

import com.example.SpringBootweek8project.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByName(String title);
}
