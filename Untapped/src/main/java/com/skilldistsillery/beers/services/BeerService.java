package com.skilldistsillery.beers.services;

import java.util.List;

import com.skilldistillery.untapped.entities.Beer;


public interface BeerService {

	List<Beer> getAllBeers();
	Beer getBeerById(int beerId);
	
}
