package com.jy.mission2.dto;

import com.jy.mission2.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.*;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
public class UserDto {

    @Email
    @NotBlank
    private String email;

    @Max(15)
    @Min(3)
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*/d)$")
    @NotBlank
    private String nickname;

    @Min(4)
    @NotBlank
    private String password;

    private String encodedPassword;

    public User getEncodedUser(BCryptPasswordEncoder encoder){
        return User.builder()
                .email(email)
                .nickname(nickname)
                .password(encoder.encode(password))
                .build();
    }

    public void checkPassword(){
        if(password.contains(nickname)){
            throw new IllegalArgumentException("잘못된 패스워드");
        }
    }



}
