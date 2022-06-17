package com.jy.mission2.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jy.mission2.response.FailureMessage;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FormLoginFailureHandler implements AuthenticationFailureHandler {

    private final ObjectMapper mapper;

    public FormLoginFailureHandler() {
        this.mapper = new ObjectMapper();
    }


    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
       response.setContentType("application/json");
        ResponseEntity<String> responseEntity =
                FailureMessage.NOT_VALID_ID_PASSWORD.getResponseEntity();

        String message = mapper.writeValueAsString(responseEntity);

        response.getWriter().write(message);
    }
}
