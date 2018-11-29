package com.pizzeria;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

public class PizzaMemDaoTest {

	/**
	 * Test d'accès au DAO et test d'accès à la liste
	 * 
	 */
	@BeforeClass
	public static void setUpClass() {
		PizzaMemDao dao = new PizzaMemDao();
		List<Pizza> testList = dao.findAllPizzas();
	}

	PizzaMemDao dao = new PizzaMemDao();
	List<Pizza> testList = dao.findAllPizzas();

	@Test
	public void findAllPizzaTest() {
		assertNotNull(dao.findAllPizzas());
	}

	@Test
	public void testPizzaPrice() {
		assertFalse(isPizzaPriceNotRight());
	}

	public boolean isPizzaPriceNotRight() {

		for (Pizza pizza : testList) {
			if (pizza.getPrix() < 0 | pizza.getPrix() > 99) {
				return true;
			}
		}
		return false;
	}

	@Test
	public void testPizzaCode() {
		assertFalse(isPizzaCodeNotUppercaseLetters());
	}

	public boolean isPizzaCodeNotUppercaseLetters() {

		for (Pizza p : testList) {
			if (!p.getCode().matches("^+[A-Z]+[A-Z]+[A-Z]?[A-Z]$")) {
				return true;
			}
		}
		return false;
	}

	@Test
	public void testDuplicateDesignation() {
		assertFalse(isDuplicateDesignation());
	}

	public boolean isDuplicateDesignation() {

		Set<String> setTest = new HashSet<String>();

		for (Pizza p : testList) {
			if (!setTest.add(p.getDesignation())) {
				return true;
			}
		}
		return false;
	}

}
