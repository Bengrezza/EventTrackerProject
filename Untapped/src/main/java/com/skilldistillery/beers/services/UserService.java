package com.skilldistillery.beers.services;

import java.util.List;

import com.skilldistillery.untapped.entities.User;

public interface UserService {

	List<User> findAll();

	User createUser(User newUser);

	User updateUser(int id, User user);

}