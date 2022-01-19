package com.skilldistillery.untapped.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BeerTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Beer beer;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("JPAUntapped");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		beer = em.find(Beer.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		beer = null;
	}

	@Test
	@DisplayName("Test the primary beer fields")
	void test() {
		assertEquals("Night Hawk", beer.getName());
		assertEquals("Stout", beer.getDescription());
		assertEquals("water, barley, chocolate", beer.getIngredients());
		assertFalse(beer.isAlcoholic());
		assertFalse(beer.isContainsAlcohol());
		assertEquals(12.0, beer.getAlcohol());
		assertEquals(200, beer.getCalories());
		assertEquals(8, beer.getVolume());
		assertTrue(beer.isActive());
		assertTrue(beer.getCreatedAt().toString().contains("2022"));
		assertTrue(beer.getUpdatedAt().toString().contains("2022"));
	}

	@Test
	@DisplayName("Test relationship with beer tracker")
	void test1() {
		assertEquals("Bobby", beer.getUser().getFirstName());
	}

}
