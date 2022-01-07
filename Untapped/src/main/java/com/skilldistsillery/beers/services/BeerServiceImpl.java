package com.skilldistsillery.beers.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.untapped.entities.Beer;
import com.skilldistsillery.beers.repositories.BeerRepository;

@Service
public class BeerServiceImpl implements BeerService {
	
	@Autowired
	private BeerRepository beerRepo;
	
	@Override
	public List<Beer> getAllBeers() {
		return beerRepo.findAll();
	}

	@Override
	public Beer getBeerById(int beerId) {
		// TODO Auto-generated method stub
		return null;
	}

}
