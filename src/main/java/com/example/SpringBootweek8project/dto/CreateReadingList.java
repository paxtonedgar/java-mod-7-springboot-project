package com.example.SpringBootweek8project.dto;

import lombok.Data;

import java.util.List;

@Data
public class CreateReadingList {
    private Long id;
    private String name;
    private List<Long> bookIDs;
    private UserDTO user;

}
