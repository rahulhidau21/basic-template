package com.example.store.service;


import com.example.store.data.User;
import com.example.store.dto.UserRequest;

import java.util.List;

public interface IUserService {
    User getUser(String username);

    void save(UserRequest userRequest);

    User getCurrentUser();

    List<User> getAll(String query);
}
