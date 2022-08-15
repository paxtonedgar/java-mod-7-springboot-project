package com.example.SpringBootweek8project.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

@Data
public class CreateBookDTO {
    @NotBlank
    private String name;
    @Min(1)
    private int pages;
    private AuthorDTO author;
    private List<String> genres;
}
