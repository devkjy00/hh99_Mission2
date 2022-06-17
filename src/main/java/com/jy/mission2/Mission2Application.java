package com.jy.mission2;

import com.jy.mission2.model.Board;
import com.jy.mission2.model.User;
import com.jy.mission2.repository.BoardRepository;
import com.jy.mission2.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableJpaAuditing
@SpringBootApplication
public class Mission2Application {

    public static void main(String[] args) {
        SpringApplication.run(Mission2Application.class, args);
    }


    @Bean
    public CommandLineRunner demo(BoardRepository boardRepository, UserRepository userRepository, BCryptPasswordEncoder encoder) {
        return (args) -> {
            System.out.println("ready");
            User user = userRepository.save(
                User.builder()
                        .email("anwjsrlrhwkd@naver.com")
                        .nickname("kkk123")
                        .password(encoder.encode("abcde1234"))
                        .build()
            );

            boardRepository.save(Board.builder()
                    .layoutType(2)
                    .imgUrl("1234")
                    .content("1234")
                    .user(user).build());

        };
    }

}
