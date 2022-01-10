
   
package com.skilldistillery.beers.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.untapped.entities.Beer;
import com.skilldistillery.beers.services.BeerService;

@RestController
@RequestMapping("api")
public class BeerController {

	@Autowired
	private BeerService beerSvc;

	@GetMapping("ping")
	public String ping() {
		return "pong";
	}

	@GetMapping("beers")
	public List<Beer> showAllBeers(HttpServletRequest req, HttpServletResponse resp) {
		List<Beer> beers = beerSvc.findAll();

		if (beers == null) {
			resp.setStatus(404);
		}
		if (beers.size() == 0) {
			resp.setStatus(204);
		}

		return beers;
	}

	@GetMapping("beers/{id}")
	public Beer showBeerById(@PathVariable int id, HttpServletRequest req, HttpServletResponse resp) {
		Beer beers = beerSvc.findById(id);

		if (beers == null) {
			resp.setStatus(404);
		} else {
			resp.setStatus(200);
		}

		return beers;
	}

	@GetMapping("beers/alcoholic")
	public List<Beer> showAlcoholicBeers(HttpServletRequest req, HttpServletResponse resp) {
		List<Beer> beers = beerSvc.findByAlcoholic();

		if (beers == null) {
			resp.setStatus(404);
		}
		if (beers.size() == 0) {
			resp.setStatus(204);
		}

		return beers;
	}

	@GetMapping("beers/name/{keyword}")
	public List<Beer> searchByKeyword(@PathVariable String keyword, HttpServletRequest req,
			HttpServletResponse resp) {
		List<Beer> beers = beerSvc.findByKeyword(keyword);

		if (beers.size() == 0) {
			resp.setStatus(404);
		}
		if (beers.size() == 0) {
			resp.setStatus(204);
		}

		return beers;
	}

	@GetMapping("beers/{min}/{max}")
	public List<Beer> searchByMinMaxAlcohol(@PathVariable Integer min, @PathVariable Integer max,
			HttpServletRequest req, HttpServletResponse resp) {
		List<Beer> beers = beerSvc.findByAlcoholBetween(min, max);

		if (beers == null) {
			resp.setStatus(404);
		}
		if (beers.size() == 0) {
			resp.setStatus(204);
		}

		return beers;
	}

	@GetMapping("beers/date/{date}")
	public List<Beer> searchByDate(@PathVariable String date, HttpServletRequest req, HttpServletResponse resp) {
		List<Beer> beers = beerSvc.findByCreatedAt(date);

		if (beers == null) {
			resp.setStatus(404);
		}

		if (beers.size() == 0) {
			resp.setStatus(204);
		}

		return beers;
	}

	@GetMapping("beers/date/{year}/{month}/{day}")
	public List<Beer> searchByDateInt(@PathVariable int year, @PathVariable int month, @PathVariable int day,
			HttpServletRequest req, HttpServletResponse resp) {
		String date = year + "-" + month + "-" + day;

		List<Beer> beers = beerSvc.findByCreatedAt(date);

		if (beers == null) {
			resp.setStatus(404);
		}

		if (beers.size() == 0) {
			resp.setStatus(204);
		}

		return beers;
	}

	@PostMapping("beers")
	public Beer create(@RequestBody Beer beer, HttpServletRequest req, HttpServletResponse resp) {
		try {
			beer = beerSvc.createBeer(beer);
			resp.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(beer.getId());
			resp.addHeader("Location", url.toString());
			return beer;
		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatus(400);
		}
		return null;
	}

	@PutMapping("beers/{id}")
	public Beer replaceExistingBeer(@RequestBody Beer beer, @PathVariable int id, HttpServletRequest req,
			HttpServletResponse resp) {
		Beer updatedbeer = null;

		try {
			updatedbeer = beerSvc.updateBeer(id, beer);
			if (updatedbeer == null) {
				resp.setStatus(404);
				return null;
			}
			resp.setStatus(202);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(updatedbeer.getId());
			resp.addHeader("Location", url.toString());
		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatus(400);
			return null;
		}
		return updatedbeer;
	}

	@DeleteMapping("beers/{id}")
	public void deleteBeer(@PathVariable int id, HttpServletRequest req, HttpServletResponse resp) {
		try {
			boolean deleted = beerSvc.deleteBeer(id);
			if (deleted) {
				resp.setStatus(204);
			} else {
				resp.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatus(400);
		}
	}

}

