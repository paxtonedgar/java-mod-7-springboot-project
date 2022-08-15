package com.example.SpringBootweek8project.controller;

import com.example.SpringBootweek8project.dto.CreateReadingList;
import com.example.SpringBootweek8project.dto.ReadingListDTO;
import com.example.SpringBootweek8project.service.ReadingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class ReadingListController {

    @Autowired
    private ReadingListService readingListService;

    @GetMapping("/{id}/reading_lists")
    public List<ReadingListDTO> getAllReadingLists(@PathVariable Long id) {
        return readingListService.getAllReadingLists(id);
    }

    @GetMapping("/{id}/reading_lists/{list_id}")
    public ReadingListDTO getBook(@PathVariable Long id,@PathVariable Long list_id) {
        return readingListService.getReadingListById(id,list_id);
    }


    @PostMapping("/{id}/reading_lists")
    public ReadingListDTO createBook(@Valid @RequestBody CreateReadingList createUserReadingListDTO){
        return readingListService.create(createUserReadingListDTO);
    }




}