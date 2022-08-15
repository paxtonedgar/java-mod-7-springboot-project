package com.example.SpringBootweek8project.dto;

import com.example.SpringBootweek8project.model.Genre;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class BookDTO {
    private String name;
    private AuthorDTO authorDTO;
    private int pages;
    private List<GenreDTO> genreList;
}
