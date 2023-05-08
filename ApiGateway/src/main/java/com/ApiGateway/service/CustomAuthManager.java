package com.ApiGateway.service;

import com.ApiGateway.entity.User;
import com.ApiGateway.feignClient.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@Slf4j
public class CustomAuthManager implements AuthenticationManager {

    @Autowired
    private PasswordEncoder encoder;


    private RestTemplate restTemplate= new RestTemplate();

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username= authentication.getName();
        String password=(String)authentication.getCredentials();
        System.out.println(password);

       // Optional<User> user =  feignservice.getByName(username);
        log.info("******************************************************"+restTemplate+"********************************8");

        Optional<User> user = restTemplate.getForObject("http://USER-SERVICE/api/users/"+username, Optional.class);
        if(user.isEmpty()) {

            throw new BadCredentialsException("USER WITH NO SUCH CREDENTIALS");
        }

        else {

            if(encoder.matches(password, user.get().getPassword()))
            {
                System.out.println("inside if provider");
                return  new UsernamePasswordAuthenticationToken(username,password,null);
            }
            else
                throw new BadCredentialsException("USER FOUND BUT INVALID CREDENTIALS ");
        }
    }
}

