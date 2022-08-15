package com.example.SpringBootweek8project.service;

import com.example.SpringBootweek8project.dto.AuthorDTO;
import com.example.SpringBootweek8project.dto.CreateAuthorDTO;
import com.example.SpringBootweek8project.exception.NotFoundException;
import com.example.SpringBootweek8project.model.Author;
import com.example.SpringBootweek8project.repository.AuthorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private ModelMapper modelMapper;


    public AuthorDTO create(CreateAuthorDTO createAuthorDTO) {
        Author author = modelMapper.map(createAuthorDTO, Author.class);
        return modelMapper.map(authorRepository.save(author), AuthorDTO.class);
    }

    public List<AuthorDTO> getAll() {
        return authorRepository.findAll().stream()
                .map(author -> modelMapper.map(author, AuthorDTO.class)).toList();
    }

    public AuthorDTO getById(Long id) {
        AuthorDTO authorDTO = authorRepository.findById(id)
                .map(author -> modelMapper.map(author, AuthorDTO.class))
                .orElseThrow(() -> new NotFoundException("Author not found"));
        return authorDTO;
    }

    public AuthorDTO getAuthorDTO(Author author){
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(author.getId());
        authorDTO.setName(author.getName());
        return authorDTO;
    }

    public Author getAuthor(AuthorDTO authorDTO) {
        Author author = new Author();
        author.setName(authorDTO.getName());
        author.setId(authorDTO.getId());
        return author;
    }


    public Optional<Author> getAuthorById(Long id) {
        return authorRepository.findById(id);
    }

}
