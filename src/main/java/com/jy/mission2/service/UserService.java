package com.jy.mission2.service;

import com.jy.mission2.dto.UserDto;
import com.jy.mission2.model.User;
import com.jy.mission2.repository.UserRepository;
import com.jy.mission2.response.ErrMessage;
import com.jy.mission2.response.Message;
import com.jy.mission2.response.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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

    public String signup(UserDto requestDto){
        Optional<User> idCheck = userRepository.findByEmail(requestDto.getEmail());
        if(idCheck.isPresent()){
            return ErrMessage.OVERLAPPED_EMAIL.getMessage();
        }

        User user = requestDto.getEncodedUser(passwordEncoder);
        userRepository.save(user);

        return Message.SUCCESS.getMessage();
    }



}
