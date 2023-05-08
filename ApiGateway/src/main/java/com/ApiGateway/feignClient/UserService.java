package com.ApiGateway.feignClient;

import com.ApiGateway.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "USER-SERVICE")
@Service
public interface UserService {

    @GetMapping( "/api/users/name/{name}")
    public Optional<User> getByName(@PathVariable String name);

}
