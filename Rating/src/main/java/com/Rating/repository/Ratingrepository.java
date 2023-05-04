package com.Rating.repository;

import com.Rating.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Ratingrepository  extends JpaRepository<Rating,Integer> {

    List<Rating> findAllByUserId(int id);
}
