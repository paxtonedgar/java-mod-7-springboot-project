package com.example.SpringBootweek8project.dto;

import lombok.Data;

import java.util.Set;

@Data
public class ErrorsDTO {
    private Set<String> errors;
}

