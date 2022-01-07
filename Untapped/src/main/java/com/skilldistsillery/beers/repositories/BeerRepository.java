package com.skilldistsillery.beers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.untapped.entities.Beer;

public interface BeerRepository extends JpaRepository<Beer, Integer> {



}
