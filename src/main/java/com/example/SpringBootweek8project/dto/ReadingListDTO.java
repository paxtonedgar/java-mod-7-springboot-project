package com.example.SpringBootweek8project.dto;

import com.example.SpringBootweek8project.model.Book;
import lombok.Data;

import java.util.List;

@Data
public class ReadingListDTO {
    private Long id;
    private String name;
    private List<BookDTO> books;
    private UserDTO user;

}
