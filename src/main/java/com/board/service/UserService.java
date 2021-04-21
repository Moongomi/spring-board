package com.board.service;

import com.board.domain.dto.UserDto;
import com.board.domain.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public String saveUser(UserDto userDto){
        return userRepository.save(userDto.toEntity()).getUsername();
    }
}
