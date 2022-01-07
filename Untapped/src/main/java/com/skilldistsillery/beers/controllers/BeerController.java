package com.skilldistsillery.beers.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.untapped.entities.Beer;
import com.skilldistsillery.beers.services.BeerService;


@RestController
@RequestMapping("api")
public class BeerController {

	@Autowired
	private BeerService beerSvc;
	
	@GetMapping("beers")
	public List<Beer> index() {
		return beerSvc.getAllBeers();
	}
}
