package com.jy.mission2.service;

import com.jy.mission2.dto.UserDto;
import com.jy.mission2.model.User;
import com.jy.mission2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseStatus signup(UserDto requestDto){

        Optional<User> idCheck = userRepository.findByEmail(requestDto.getEmail());
        if(idCheck.isPresent()){
            throw new IllegalArgumentException("중복된 ID 입니다");
        }

        User user = requestDto.getEncodedUser(passwordEncoder);
        userRepository.save(user);

        return ResponseStatus.SUCCESS;
    }



}
