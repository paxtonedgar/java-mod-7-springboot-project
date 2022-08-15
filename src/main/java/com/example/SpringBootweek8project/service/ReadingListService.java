package com.example.SpringBootweek8project.service;

import com.example.SpringBootweek8project.dto.CreateReadingList;
import com.example.SpringBootweek8project.dto.ReadingListDTO;
import com.example.SpringBootweek8project.dto.UserDTO;
import com.example.SpringBootweek8project.exception.NotFoundException;
import com.example.SpringBootweek8project.model.Book;
import com.example.SpringBootweek8project.model.ReadingList;
import com.example.SpringBootweek8project.model.User;
import com.example.SpringBootweek8project.repository.BookRepository;
import com.example.SpringBootweek8project.repository.ReadingListRepository;
import com.example.SpringBootweek8project.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReadingListService {
    @Autowired
    private ReadingListRepository readingListRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookService bookService;

    @Autowired
    private ModelMapper modelMapper;

    public User getUser(UserDTO userDTO) {
        if (!userRepository.existsById(userDTO.getId())) {
            throw new NotFoundException("User doesn't exist");
        } else {
            User user = userRepository.findById(userDTO.getId()).get();
            return user;
        }
    }

    public UserDTO getUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        return userDTO;
    }

    public List<Book> getBooks(List<Long> bookIDs) {
        List<Book> bookList = new ArrayList<>();
        bookIDs.stream().forEach(id -> {
            if (bookRepository.findById(id) != null) {
                bookList.add(bookRepository.findById(id).get());
            } else {
                throw new NotFoundException("Book doesnt exist");
            }

        });
        return bookList;
    }

    public ReadingListDTO create(CreateReadingList createReadingListDTO) {
        ReadingList readingList = new ReadingList();
        User user = getUser(createReadingListDTO.getUser());

        readingList.setId(createReadingListDTO.getId());
        readingList.setName(createReadingListDTO.getName());
        readingList.setUser(user);
        readingList.setBookList(getBooks(createReadingListDTO.getBookIDs()));

        user.getReadingList().add(readingList);
        readingListRepository.save(readingList);
        ReadingListDTO readingListDTO = setReadingListDTO(createReadingListDTO);
        return readingListDTO;
    }

    public ReadingListDTO setReadingListDTO(CreateReadingList createReadingListDTO){
        ReadingListDTO readingListDTO = new ReadingListDTO();
        readingListDTO.setId(createReadingListDTO.getId());
        readingListDTO.setUser(createReadingListDTO.getUser());
        readingListDTO.setName(createReadingListDTO.getName());
        readingListDTO.setBooks(bookService.getBookDTOs(
                getBooks(createReadingListDTO.getBookIDs())));
        return readingListDTO;
    }

    public ReadingListDTO setReadingListDTO(ReadingListDTO readingListDTO){
        ReadingListDTO newReadingListDTO = new ReadingListDTO();
        newReadingListDTO.setId(readingListDTO.getId());
        newReadingListDTO.setUser(readingListDTO.getUser());
        newReadingListDTO.setName(readingListDTO.getName());
        newReadingListDTO.setBooks(readingListDTO.getBooks());
        return readingListDTO;
    }

    public ReadingListDTO getReadingListById(Long id, Long list_id) {
        if (userRepository.existsById(id) && readingListRepository.existsById(list_id)) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(id);
            User user = getUser(userDTO);

            ReadingListDTO readingListDTO = new ReadingListDTO();

            int newID = (int)(list_id - 1);
            ReadingList readingList = user.getReadingList().get(newID);

            readingListDTO.setBooks(bookService.getBookDTOs(readingList.getBookList()));
            readingListDTO.setId(readingList.getId());
            readingListDTO.setUser(getUserDTO(user));
            readingListDTO.setName(readingList.getName());
            return readingListDTO;

        } else throw new NotFoundException("Reading list does not exist");
    }

    public List<ReadingListDTO> setReadingListDTO(User user){
        List<ReadingList> readingLists = user.getReadingList();
        ReadingListDTO readingListDTO = new ReadingListDTO();
        List<ReadingListDTO> readingListDTOList = new ArrayList<>();
        readingLists.stream().forEach(list -> {
            readingListDTO.setBooks(bookService.getBookDTOs(list.getBookList()));
            readingListDTO.setId(list.getId());
            readingListDTO.setUser(getUserDTO(user));
            readingListDTO.setName(list.getName());
            readingListDTOList.add(readingListDTO);

        });
        return readingListDTOList;
    }

    public List<ReadingListDTO> getAllReadingLists(Long id) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(id);
        User user = getUser(userDTO);
        List<ReadingListDTO> readingListDTOList = setReadingListDTO(user);
        return readingListDTOList;
    }
}
