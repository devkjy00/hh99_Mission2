package com.jy.mission2.dto;

import com.jy.mission2.exception.NameOverlappedPasswordException;
import com.jy.mission2.model.User;
import com.jy.mission2.response.ErrMessage;
import com.jy.mission2.response.Message;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
public class UserDto {

    @Email(message = DtoMessage.WRONG_EMAIL)
    @NotBlank(message = DtoMessage.WRONG_EMAIL)
    private String email;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{3,}", message = DtoMessage.WRONG_NICKNAME)
    @NotBlank(message = DtoMessage.WRONG_NICKNAME)
    private String nickname;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{4,}", message = DtoMessage.WRONG_PASSWORD)
    @NotBlank(message = DtoMessage.WRONG_PASSWORD)
    private String password;
//    private String encodedPassword;

    public User getEncodedUser(BCryptPasswordEncoder encoder){
        return User.builder()
                .email(email)
                .nickname(nickname)
                .password(encoder.encode(password))
                .build();
    }

    public void checkPassword(){
        if(password.contains(nickname)){
            System.out.println("Overlapped");
            throw new NameOverlappedPasswordException(DtoMessage.WRONG_PASSWORD);
        }
    }



}
