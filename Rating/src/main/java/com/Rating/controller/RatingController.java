package com.Rating.controller;

import com.Rating.entity.Rating;
import com.Rating.service.IRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RatingController {

    @Autowired
    private IRatingService service;

    @GetMapping(value = "/ratings")
    public List<Rating> getAllRatings(){
        return service.getAllRatings();
    }

    @GetMapping(value = "/ratingsByUserId/{id}")
    public List<Rating> getAllRatingsByUserId(@PathVariable int id){
        return service.getAllRatingByUserId(id);
    }

    @PostMapping(value = "/ratings")
    public Rating create(@RequestBody Rating rating){
        return service.create(rating);
    }

    @GetMapping("/ratingDemo")
    public String rating(){
        return " rating controller called ";
    }

}
