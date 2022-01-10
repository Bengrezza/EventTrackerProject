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
import com.skilldistillery.untapped.entities.User;
import com.skilldistillery.beers.services.BeerService;
import com.skilldistillery.beers.services.UserService;

@RestController
@RequestMapping("api")
public class UserController {

	@Autowired
	private UserService userSvc;
	@Autowired
	private BeerService beerSvc;

	@GetMapping("users")
	public List<User> findAll(HttpServletRequest req, HttpServletResponse resp) {
		List<User> users = userSvc.findAll();

		if (users == null) {
			resp.setStatus(404);
		}
		if (users.size() == 0) {
			resp.setStatus(204);
		}

		return users;
	}

	@PostMapping("users")
	public User create(@RequestBody User newUser, HttpServletRequest req, HttpServletResponse resp) {
		try {
			newUser = userSvc.createUser(newUser);
			resp.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(newUser.getId());
			resp.addHeader("Location", url.toString());
		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatus(400);
			return null;
		}
		return newUser;
	}
	
	@PutMapping("users/{id}")
	public User replaceExistingUser(@RequestBody User user, @PathVariable int id, HttpServletRequest req,
			HttpServletResponse resp) {
		try {
			user = userSvc.updateUser(id, user);
			if (user == null) {
				resp.setStatus(404);
				return null;
			}
			resp.setStatus(202);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(user.getId());
			resp.addHeader("Location", url.toString());
		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatus(400);
			return null;
		}
		return user;
	}

	@PostMapping("users/{id}/beers")
	public Beer createbeer(@PathVariable int id, @RequestBody Beer beer, HttpServletRequest req,
			HttpServletResponse resp) {
		Beer beerCreated = null;
		try {
			beerCreated = beerSvc.createBeer(id, beer);

			if (beerCreated == null) {
				resp.setStatus(404);
			}

			resp.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(beerCreated.getId());
			resp.addHeader("Location", url.toString());
		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatus(400);
		}

		return beerCreated;
	}

	@DeleteMapping("users/{userId}/beers/{beerId}")
	public void deleteBeer(@PathVariable int userId, @PathVariable int beerId, HttpServletRequest req,
			HttpServletResponse resp) {

		try {
			boolean beerDeleted = beerSvc.deleteBeer(userId, beerId);

			if (!beerDeleted) {
				resp.setStatus(404);
			} else {
				resp.setStatus(204);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatus(400);
		}

	}
}
