package com.User.service;

import com.User.entity.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {

    @GetMapping("/ratingDemo")
    public String rating();

    @GetMapping("/ratingsByUserId/{id}")
    public List<Rating> getAllRatingsByUserId(@PathVariable int id) ;

}
