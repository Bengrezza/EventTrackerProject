
   
package com.skilldistillery.beers.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.untapped.entities.User;
import com.skilldistillery.beers.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository uRepo;

	@Override
	public List<User> findAll() {
		return uRepo.findAll();
	}

	@Override
	public User createUser(User newUser) {
		return uRepo.saveAndFlush(newUser);
	}

	@Override
	public User updateUser(int id, User user) {
		
		Optional<User> userOpt = uRepo.findById(id);
		if (userOpt.isPresent()) {
			User managedUser = userOpt.get();
			managedUser.setFirstName(user.getFirstName());
			managedUser.setLastName(user.getLastName());
			uRepo.saveAndFlush(managedUser);
			return managedUser;
		}
		
		return null;
	}

}