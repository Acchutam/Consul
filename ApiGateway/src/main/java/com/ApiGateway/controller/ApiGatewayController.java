package com.ApiGateway.controller;

import com.ApiGateway.entity.User;
import com.ApiGateway.service.CustomAuthManager;
import com.ApiGateway.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class ApiGatewayController {

    private AuthenticationManager manager = new CustomAuthManager();

    @Autowired
    private JwtService service;
    @PostMapping("/login")
    public HashMap<String,String> login(@RequestBody User user) {
        HashMap<String, String > map = new HashMap<>();
        Authentication authenticate=manager.authenticate(new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword()));
        if(authenticate!=null)
        {
           String token =  service.generateToken(user.getName());
           map.put("Token",token);
           return map;
        }
        else {
            map.put("Token", "empty token something went wrong ");
            return map;
        }
    }
}
