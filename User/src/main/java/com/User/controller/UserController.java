package com.User.controller;


import com.User.entity.User;
import com.User.service.RatingService;
import com.User.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    private RatingService ratingService;

    private RestTemplate restTemplate = new RestTemplate();

    @GetMapping(value = "/users")
    public List<User> getAll(){
        return userService.getAll();
    }


    @GetMapping(value = "/users/id/{id}")
    public Optional<User> getById(@PathVariable int id){
        Optional<User> user = userService.getById(id);
        user.get().setRating(ratingService.getAllRatingsByUserId(user.get().getId()));
       // user.get().setRating(restTemplate.getForObject("http://RATING-SERVICE/"));
        return user;

    }

    @GetMapping(value = "/users/name/{name}")
    public Optional<User> getByName(@PathVariable String name){
        return userService.getByName(name);
    }

    @PostMapping(value = "/users")
    public User create(@RequestBody User user){
        return userService.create(user);
    }

    @GetMapping(value = "/checkFeign")
    public String check(){

        //return ratingService.rating();
         return restTemplate.getForObject("http://RATING-SERVICE/ratingDemo",String.class);
    }


}
