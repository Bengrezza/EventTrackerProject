package com.skilldistillery.beers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.untapped.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
