package com.ApiGateway.service;

import com.ApiGateway.entity.User;
import com.ApiGateway.feignClient.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class CustomAuthenticationManager implements ReactiveAuthenticationManager {
    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService feignclient;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) throws AuthenticationException {
         String jwt=authentication.getCredentials().toString();
        String username= jwtService.extractUsername(jwt);

        System.out.println(jwt+"    *    "+username);

        if(username != null && SecurityContextHolder.getContext().getAuthentication()==null)
        {
           /* System.out.println("1st Line");
            String url="http://10.0.61.194:8088/loadByName/"+username;
            System.out.println("2st Line");*/
           // Logs logs=restTemplate.getForObject(url,Logs.class);
            Optional<User> _user = feignclient.getByName(username);
            User user = _user.get();
            if(jwtService.validate(jwt)){

                System.out.println("4st Line");
                UsernamePasswordAuthenticationToken authToken= new UsernamePasswordAuthenticationToken(
                        user,
                        user.getPassword(),
                        null
                );

                SecurityContextHolder.getContext().setAuthentication(authToken);
                return Mono.just(authToken);
            }
            else {
                System.out.println("token not matching");
                return Mono.just(authentication);
            }
        }
            else {
                return Mono.just(authentication);
        }
    }
}
