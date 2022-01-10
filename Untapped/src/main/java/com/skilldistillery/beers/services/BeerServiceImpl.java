package com.skilldistillery.beers.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.beers.repositories.BeerRepository;
import com.skilldistillery.beers.repositories.UserRepository;
import com.skilldistillery.untapped.entities.Beer;
import com.skilldistillery.untapped.entities.User;

@Service
public class BeerServiceImpl implements BeerService {

	@Autowired
	private BeerRepository beerRepo;
	@Autowired
	private UserRepository uRepo;

	@Override
	public List<Beer> findAll() {
		return beerRepo.findAll();
	}

	@Override
	public Beer findById(int id) {

		Optional<Beer> beerOpt = beerRepo.findById(id);
		Beer beer = null;
		if (beerOpt != null) {
			beer = beerOpt.get();
		}

		return beer;
	}

	@Override
	public Beer createBeer(Beer beer) {
		if (!beer.isAlcoholic() && beer.getAlcohol() > 0) {
			return null;
		}
		return beerRepo.saveAndFlush(beer);
	}

	@Override
	public Beer updateBeer(int id, Beer beer) throws Exception {
		if (!beer.isAlcoholic() && beer.getAlcohol() > 0) {
			throw new Exception();
		}

		Optional<Beer> beerOpt = beerRepo.findById(id);
		if (beerOpt.isPresent()) {
			Beer managedbeer = beerOpt.get();
			managedbeer.setId(beer.getId());
			managedbeer.setName(beer.getName());
			managedbeer.setDescription(beer.getDescription());
			managedbeer.setIngredients(beer.getIngredients());
			managedbeer.setAlcohol(beer.getAlcohol());
			managedbeer.setVolume(beer.getVolume());
			managedbeer.setCalories(beer.getCalories());
			managedbeer.setAlcoholic(beer.isAlcoholic());
			managedbeer.setContainsAlcohol(beer.isContainsAlcohol());
			managedbeer.setActive(beer.isActive());

			if (beer.getUser().getId() > 1) {
				managedbeer.setUser(beer.getUser());
			} else {
				User user = uRepo.getOne(1);
				managedbeer.setUser(user);
			}

			return beerRepo.saveAndFlush(managedbeer);
		}

		return null;
	}

	@Override
	public boolean deleteBeer(int id) {
		if (id <= 5) {
			return false;
		}
		
		
		Optional<Beer> beerOpt = beerRepo.findById(id);
		if (beerOpt.isPresent()) {
			Beer beer = beerOpt.get();
			beerRepo.delete(beer);
			return true;
		}

		return false;
	}

	@Override
	public List<Beer> findByAlcoholic() {
		return beerRepo.findByAlcoholicTrue();
	}

	@Override
	public List<Beer> findByKeyword(String keyword) {
		return beerRepo.findByNameContaining(keyword);
	}

	@Override
	public List<Beer> findByAlcoholBetween(Double min, Double max) {
		return beerRepo.findByAlcoholBetween(min, max);
	}

	@Override
	public List<Beer> findByCreatedAt(String dateStr) {

		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}

		return beerRepo.findByCreatedAtBetween(date, date);
	}

	@Override
	public Beer createBeer(int id, Beer beer) throws Exception {
		if (!beer.isAlcoholic() && beer.getAlcohol() > 0) {
			throw new Exception("Beer can not contain alcohol and Alcoholic be false.");
		}

		Optional<User> userOpt = uRepo.findById(id);
		if (userOpt.isPresent()) {
			User user = userOpt.get();
			beer.setUser(user);
			return beerRepo.saveAndFlush(beer);
		}

		return null;
	}

	@Override
	public boolean deleteBeer(int userId, int beerId) {
		Optional<User> userOpt = uRepo.findById(userId);
		if (userOpt.isPresent()) {
			Optional<Beer> beer = beerRepo.findById(beerId);
			if (beer.isPresent()) {
				beerRepo.deleteById(beerId);
				return true;
			}
		}

		return false;
	}

}