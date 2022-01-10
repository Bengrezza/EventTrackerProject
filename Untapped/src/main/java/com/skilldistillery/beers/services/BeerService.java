package com.skilldistillery.beers.services;

import java.util.List;

import com.skilldistillery.untapped.entities.Beer;

public interface BeerService {
	List<Beer> findAll();

	Beer findById(int id);

	Beer createBeer(Beer beer);

	Beer updateBeer(int id, Beer beer) throws Exception;

	boolean deleteBeer(int id);

	List<Beer> findByAlcoholic();

	List<Beer> findByKeyword(String keyword);

	List<Beer> findByAlcoholBetween(Integer min, Integer max);

	List<Beer> findByCreatedAt(String date);

	Beer createBeer(int id, Beer beer) throws Exception;

	boolean deleteBeer(int userId, int beerId);

}