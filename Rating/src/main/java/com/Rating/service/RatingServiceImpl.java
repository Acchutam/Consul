package com.Rating.service;

import com.Rating.entity.Rating;
import com.Rating.repository.Ratingrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingServiceImpl implements IRatingService{

   @Autowired
   private Ratingrepository ratingrepository;

    @Override
    public List<Rating> getAllRatings() {
        return ratingrepository.findAll();
    }

    @Override
    public List<Rating> getAllRatingByUserId(int id) {
        return ratingrepository .findAllByUserId(id);
    }

    @Override
    public Optional<Rating> getById(int id) {
        return ratingrepository.findById(id);
    }

    @Override
    public void deleteRating(int id) {
        ratingrepository.deleteById(id);
    }

    @Override
    public Rating create(Rating rating){
        return ratingrepository.save(rating);
    }
}
