package com.Rating.service;

import com.Rating.entity.Rating;

import java.util.List;
import java.util.Optional;
import java.util.RandomAccess;

public interface IRatingService {

    List<Rating> getAllRatings();

    List<Rating> getAllRatingByUserId(int id);

    Optional<Rating> getById(int id);

    void deleteRating(int id );

    Rating create(Rating rating);
}
