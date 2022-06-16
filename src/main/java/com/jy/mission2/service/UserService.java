package com.jy.mission2.service;

import com.jy.mission2.dto.UserDto;
import com.jy.mission2.model.User;
import com.jy.mission2.repository.UserRepository;
import com.jy.mission2.response.ErrMessage;
import com.jy.mission2.response.Message;
import com.jy.mission2.response.ResponseMessage;
import com.jy.mission2.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public String signup(UserDto requestDto){
        Optional<User> idCheck = userRepository.findByEmail(requestDto.getEmail());
        if(idCheck.isPresent()){
            return ErrMessage.OVERLAPPED_EMAIL.getMessage();
        }

        User user = requestDto.getEncodedUser(passwordEncoder);
        userRepository.save(user);

        return Message.SUCCESS.getMessage();
    }

    @Transactional(readOnly = true)
    public User getUser(UserDetailsImpl userDetails){
        return userRepository.findById(userDetails.getId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저 ID"));
    }


}
