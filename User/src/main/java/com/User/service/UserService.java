package com.User.service;



import com.User.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getAll();

    Optional<User> getById(int id );

    Optional<User> getByName(String name);

    User create(User user);


}
