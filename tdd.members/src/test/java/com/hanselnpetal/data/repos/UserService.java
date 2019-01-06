package com.taskboard.application.service;

import com.taskboard.application.dto.UserDTO;
import com.taskboard.application.exception.UserNotFoundException;
import com.taskboard.application.util.DtoAssembler;
import com.taskboard.domain.entity.User;
import com.taskboard.infrastructure.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DtoAssembler assembler;

    @Transactional
    public UserDTO createUser(UserDTO userDto) {

        User result = userRepository.save(assembler().convertFromDto(userDto));

        return assembler().convertToDto(result);
    }


    @Transactional
    public List<UserDTO> findAll() {

        List<User> userEntries = userRepository.findAll();

        return userEntries.stream()
                .map(user -> assembler().convertToDto(user))
                    .collect(Collectors.toList());
    }

    @Transactional
    public UserDTO findById(Long id) {

        User userEntry = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Not found by id: " + id));

        return assembler().convertToDto(userEntry);
    }

    @Transactional
    public UserDTO findByName(String name) {

        User userEntry = userRepository.findByName(name)
                .orElseThrow(() -> new UserNotFoundException("Not found by name: " + name));

        return assembler().convertToDto(userEntry);
    }


    private DtoAssembler assembler() {
        if (this.assembler == null) {
            this.assembler =  new DtoAssembler();
        }
        return this.assembler;
    }
}
