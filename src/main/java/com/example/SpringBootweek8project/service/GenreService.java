package com.example.SpringBootweek8project.service;

import com.example.SpringBootweek8project.dto.GenreDTO;
import com.example.SpringBootweek8project.exception.NotFoundException;
import com.example.SpringBootweek8project.model.Genre;
import com.example.SpringBootweek8project.repository.GenreRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GenreService {
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<Genre> create(List<String> genres) {
        List<Genre> genreList = new ArrayList<>();
        genres.stream().forEach(genre -> {
                if (genreRepository.findByName(genre) == null) {
                    Genre newGenre = new Genre();
                    newGenre.setName(genre);
                    genreRepository.save(newGenre);
                    genreList.add(newGenre);
                } else {
                    genreList.add(modelMapper.map(genreRepository.findByName(genre), Genre.class));
                }
        });
        return genreList;
    }

    public List<GenreDTO> getAll() {
        return genreRepository.findAll()
                .stream()
                .map(genre -> modelMapper.map(genre, GenreDTO.class))
                .toList();
    }

    public void deleteById(Long id) {
        if(genreRepository.existsById(id)) {
            genreRepository.deleteById(id);
        }
        else {
            throw new NotFoundException("Genre not found");
        }}

    public Optional<Genre> getGenreById(Long id) {
        return genreRepository.findById(id);
    }

    public List<GenreDTO> getGenre(List<Genre> genreList){
        List<GenreDTO> genreDTOList = new ArrayList<>();
        genreList.stream().forEach(genre -> {
            GenreDTO genreDTO = new GenreDTO();
            genreDTO.setName(genre.getName());
            genreDTOList.add(genreDTO);
        });
        return genreDTOList;
    }

    public void updateGenre(Genre genre){
        genreRepository.save(genre);
    }

}
