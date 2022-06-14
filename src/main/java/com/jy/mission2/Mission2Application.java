package com.jy.mission2;

import com.jy.mission2.dto.UserDto;
import com.jy.mission2.model.User;
import com.jy.mission2.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class Mission2Application {

    public static void main(String[] args) {
        SpringApplication.run(Mission2Application.class, args);
    }


    @Bean
    public CommandLineRunner demo(UserRepository userRepository) {
        return (args) -> {

//            userRepository.save((new UserDto("email", "nickname", "password")
//                    .getEncodedUser(new BCryptPasswordEncoder())));

            System.out.println("init repository");
        };
    }

}
