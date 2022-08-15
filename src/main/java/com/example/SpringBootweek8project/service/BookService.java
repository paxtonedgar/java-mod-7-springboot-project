package com.example.SpringBootweek8project.service;

import com.example.SpringBootweek8project.dto.BookDTO;
import com.example.SpringBootweek8project.dto.CreateBookDTO;
import com.example.SpringBootweek8project.dto.GenreDTO;
import com.example.SpringBootweek8project.exception.NotFoundException;
import com.example.SpringBootweek8project.exception.ValidationException;
import com.example.SpringBootweek8project.model.Author;
import com.example.SpringBootweek8project.model.Book;
import com.example.SpringBootweek8project.model.Genre;
import com.example.SpringBootweek8project.repository.BookRepository;
import com.example.SpringBootweek8project.repository.GenreRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private GenreService genreService;

    public List<BookDTO> getAll() {
        return bookRepository.findAll()
                .stream()
                .map(book -> modelMapper.map(book, BookDTO.class))
                .toList();
    }

    public void deleteById(Long id) {
        if(bookRepository.existsById(id)) {
            Book book = bookRepository.findById(id).get();
            List<Genre> genreList = genreRepository.findAll();
            genreList.stream()
                    .filter(genre -> genre.getBookList().contains(book))
                    .map(genre -> genre.getBookList().remove(book));
            bookRepository.deleteById(id);
        }
        else {
            throw new NotFoundException("Book not found");
        }}

    public BookDTO getBookById(Long id) {
        BookDTO bookDTO = bookRepository.findById(id)
                .map(book -> modelMapper.map(book, BookDTO.class))
                .orElseThrow(() -> new NotFoundException("Book not found"));
        return bookDTO;
    }

    public BookDTO create(CreateBookDTO createBookDTO) {
        Book book = modelMapper.map(createBookDTO, Book.class);
        book.setName(createBookDTO.getName());
        book.setPages(createBookDTO.getPages());
        //author
        Author author = authorService.getAuthor(createBookDTO.getAuthor());
        book.setAuthor(author);
        author.getBookList().add(book);
        //genre
        List<Genre> genre = genreService.create(createBookDTO.getGenres());
        book.setGenreList(genre);
        for(Genre item: genre){
            item.getBookList().add(book);
        }
        BookDTO bookDTO = setBookDTO(book);
        bookRepository.save(book);
        return bookDTO;
    }

    public List<BookDTO> getBookDTOs(List<Book> books){
        List<BookDTO> bookList = new ArrayList<>();
        books.stream().forEach(book -> {
            BookDTO bookDTO = new BookDTO();
            bookDTO.setName(book.getName());
            bookDTO.setPages(bookDTO.getPages());
            bookDTO.setAuthorDTO(authorService.getAuthorDTO(book.getAuthor()));
            bookDTO.setGenreList(genreService.getGenre(book.getGenreList()));
            bookList.add(bookDTO);
        });
        return bookList;
    }

    public BookDTO updateBook(Long id, CreateBookDTO createBookDTO){
        if (bookRepository.existsById(id)) {
            Book book = bookRepository.findById(id).get();
            List<Genre> genre = genreService.create(createBookDTO.getGenres());
            Author author = authorService.getAuthor(createBookDTO.getAuthor());
            book.setAuthor(author);
            if(!createBookDTO.getName().isEmpty()){
                book.setName(createBookDTO.getName());
            }

            if(!genre.isEmpty()){
                for(Genre item: genre){
                    item.getBookList().add(book);
                }
            }
            book.setPages(createBookDTO.getPages());
            BookDTO bookDTO = setBookDTO(book);
            bookRepository.save(book);
            return bookDTO;
        } else throw new ValidationException();

    }

    public BookDTO setBookDTO(Book book){
        BookDTO bookDTO = new BookDTO();
        bookDTO.setName(book.getName());
        bookDTO.setPages(bookDTO.getPages());
        bookDTO.setAuthorDTO(authorService.getAuthorDTO(book.getAuthor()));
        bookDTO.setGenreList(genreService.getGenre(book.getGenreList()));
        return bookDTO;
    }

}
