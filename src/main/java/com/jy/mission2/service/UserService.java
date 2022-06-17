package com.jy.mission2.service;

import com.jy.mission2.dto.request.UserDto;
import com.jy.mission2.exception.DataNotFoundException;
import com.jy.mission2.model.User;
import com.jy.mission2.repository.UserRepository;
import com.jy.mission2.response.FailureMessage;
import com.jy.mission2.response.SuccessMessage;
import com.jy.mission2.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> signup(UserDto requestDto){
        Optional<User> idCheck = userRepository.findByEmail(requestDto.getEmail());
        if(idCheck.isPresent()){
            return FailureMessage.OVERLAPPED_EMAIL.getResponseEntity();
        }

        User user = requestDto.getEncodedUser(passwordEncoder);
        userRepository.save(user);

        return SuccessMessage.SUCCESS.getResponseEntity();
    }

    @Transactional(readOnly = true)
    public User getUser(UserDetailsImpl userDetails){
        return userRepository.findById(userDetails.getId())
                .orElseThrow(() -> new DataNotFoundException(FailureMessage.NO_DATA_EXIST.getMessage()));
    }


}
