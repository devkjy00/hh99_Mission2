package com.jy.mission2.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jy.mission2.exception.DataNotFoundException;
import com.jy.mission2.response.FailureMessage;
import com.jy.mission2.security.jwt.HeaderTokenExtractor;
import com.jy.mission2.security.jwt.JwtPreProcessingToken;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebServlet
public class JwtAuthFilter extends AbstractAuthenticationProcessingFilter {

    private final HeaderTokenExtractor extractor;
    private final ObjectMapper mapper;

    public JwtAuthFilter(
            RequestMatcher requestMatcher,
            HeaderTokenExtractor extractor){
        super(requestMatcher);
        this.extractor = extractor;
        this.mapper = new ObjectMapper();
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String tokenPayload = request.getHeader("Authorization");
        if (Objects.isNull(tokenPayload)){
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            ResponseEntity<String> responseEntity =
                    FailureMessage.NOT_ATHOURIZED.getResponseEntity();

            String message = mapper.writeValueAsString(responseEntity);

            response.getWriter().write(message);

            throw new DataNotFoundException(FailureMessage.NO_DATA_EXIST.getMessage());
        }

        // 헤더에서 JWT토큰을 가져와서 UsernamePasswordAuthenticationToken 상속객체에 페이로드와 길이를 저장
        JwtPreProcessingToken jwtToken = new JwtPreProcessingToken(
                extractor.extract(tokenPayload, request));

        return super
                .getAuthenticationManager()
                .authenticate(jwtToken);
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain,
            Authentication authResult
    ) throws IOException, ServletException {
        /*
         *  SecurityContext 사용자 Token 저장소를 생성합니다.
         *  SecurityContext 에 사용자의 인증된 Token 값을 저장합니다.
         */
        SecurityContext context = SecurityContextHolder.createEmptyContext();

        context.setAuthentication(authResult);
        SecurityContextHolder.setContext(context);

        // FilterChain chain 해당 필터가 실행 후 다른 필터도 실행할 수 있도록 연결실켜주는 메서드
        chain.doFilter(
                request,
                response
        );
    }


    @Override
    protected void unsuccessfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException failed
    ) throws IOException, ServletException {
        /*
         *	로그인을 한 상태에서 Token값을 주고받는 상황에서 잘못된 Token값이라면
         *	인증이 성공하지 못한 단계 이기 때문에 잘못된 Token값을 제거합니다.
         *	모든 인증받은 Context 값이 삭제 됩니다.
         */
        SecurityContextHolder.clearContext();

        super.unsuccessfulAuthentication(
                request,
                response,
                failed
        );
    }
}
