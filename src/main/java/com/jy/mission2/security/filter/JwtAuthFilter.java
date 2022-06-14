package com.jy.mission2.security.filter;

import com.jy.mission2.security.jwt.HeaderTokenExtractor;
import com.jy.mission2.security.jwt.JwtPreProcessingToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class JwtAuthFilter extends AbstractAuthenticationProcessingFilter {

    private final HeaderTokenExtractor extractor;

    public JwtAuthFilter(
            RequestMatcher requestMatcher,
            HeaderTokenExtractor extractor
    ){
        super(requestMatcher);
        this.extractor = extractor;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String tokenPayload = request.getHeader("Authorization");
        if (Objects.isNull(tokenPayload)){
            response.sendRedirect("/user/loginView");
            return null;
        }

        // 헤더에서 JWT토큰을 가져와서 UsernamePasswordAuthenticationToken 상속객체에 페이로드와 길이를 저장
        JwtPreProcessingToken jwtToken = new JwtPreProcessingToken(
                extractor.extract(tokenPayload, request));

        return super
                .getAuthenticationManager()
                .authenticate(jwtToken);
    }
}
