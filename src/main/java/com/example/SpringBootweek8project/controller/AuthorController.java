package com.example.SpringBootweek8project.controller;

import com.example.SpringBootweek8project.dto.AuthorDTO;
import com.example.SpringBootweek8project.dto.CreateAuthorDTO;
import com.example.SpringBootweek8project.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping
    public AuthorDTO createAuthor(@Valid @RequestBody CreateAuthorDTO createAuthorDTO) {
        return authorService.create(createAuthorDTO);
    }

    @GetMapping
    public List<AuthorDTO> getAllAuthors() {
        return authorService.getAll();
    }

    @GetMapping("/{id}")
    public AuthorDTO getAuthor(@PathVariable Long id) {
        return authorService.getById(id);
    }
}
