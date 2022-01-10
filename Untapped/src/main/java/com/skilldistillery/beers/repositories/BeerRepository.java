
   
package com.skilldistillery.beers.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.untapped.entities.Beer;

public interface BeerRepository extends JpaRepository<Beer, Integer> {

	List<Beer> findByAlcoholicTrue();

	List<Beer> findByNameContaining(String keyword);

	List<Beer> findByAlcoholBetween(Double min, Double max);

	List<Beer> findByCreatedAtBetween(Date start, Date end);
}