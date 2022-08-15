package com.example.SpringBootweek8project.service;

import com.example.SpringBootweek8project.dto.CreateUserDTO;
import com.example.SpringBootweek8project.dto.UserDTO;
import com.example.SpringBootweek8project.exception.NotFoundException;
import com.example.SpringBootweek8project.model.User;
import com.example.SpringBootweek8project.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;

    public UserDTO create(CreateUserDTO createUserDTO) {
        User user = modelMapper.map(createUserDTO, User.class);
        return modelMapper.map(userRepository.save(user), UserDTO.class);
    }

    public void deleteById(Long id){
        if(userRepository.existsById(id)) {userRepository.deleteById(id);
        } else {
            throw new NotFoundException("User not found");
        }
    }

    public List<UserDTO> getAll() {
        return userRepository.findAll().stream().map(user -> modelMapper.map(user, UserDTO.class)).toList();
    }

}
