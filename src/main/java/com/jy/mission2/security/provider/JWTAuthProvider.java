package com.jy.mission2.security.provider;

import com.jy.mission2.model.User;
import com.jy.mission2.repository.UserRepository;
import com.jy.mission2.security.UserDetailsImpl;
import com.jy.mission2.security.jwt.JwtDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

@Component
public class JWTAuthProvider implements AuthenticationProvider {

    private final JwtDecoder jwtDecoder;
    private final UserRepository userRepository;

    @Autowired
    public JWTAuthProvider(JwtDecoder jwtDecoder, UserRepository userRepository){
        this.jwtDecoder = jwtDecoder;
        this.userRepository = userRepository;
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = (String) authentication.getPrincipal();
        String username = jwtDecoder.decodeUsername(token);

        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Can't find" + username));
        UserDetailsImpl userDetails = new UserDetailsImpl(user);
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
