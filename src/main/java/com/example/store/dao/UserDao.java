package com.example.store.dao;

import com.example.store.data.User;

import java.util.List;


public interface UserDao {

    List<User> getAll(String query);
}
