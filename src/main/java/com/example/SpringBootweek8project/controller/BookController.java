package com.example.SpringBootweek8project.controller;

import com.example.SpringBootweek8project.dto.BookDTO;
import com.example.SpringBootweek8project.dto.CreateBookDTO;
import com.example.SpringBootweek8project.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public BookDTO createBook(@Valid @RequestBody CreateBookDTO createBookDTO) {
        return bookService.create(createBookDTO);
    }

    @PutMapping("/{id}")
    public BookDTO updateBook(@PathVariable Long id,@Valid @RequestBody CreateBookDTO CreateBookDTO) {
        return bookService.updateBook(id,CreateBookDTO);
    }

    @GetMapping("/{id}")
    public BookDTO getBook(@PathVariable Long id){ return bookService.getBookById(id);}

    @GetMapping
    public List<BookDTO> getAllBooks() {
        return bookService.getAll();
    }

    @DeleteMapping("/{id}")
    public void deleteBookById(@PathVariable Long id) {
        bookService.deleteById(id);
    }
}
