package com.example.SpringBootweek8project.controller;


import com.example.SpringBootweek8project.dto.BookDTO;
import com.example.SpringBootweek8project.dto.GenreDTO;
import com.example.SpringBootweek8project.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/genres")
public class GenreController {
    @Autowired
    private GenreService genreService;

    @GetMapping
    public List<GenreDTO> getAllBooks() {
        return genreService.getAll();
    }

    @DeleteMapping("/{id}")
    public void deleteBookById(@PathVariable Long id) {
        genreService.deleteById(id);
    }
}
