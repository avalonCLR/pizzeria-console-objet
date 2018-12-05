package com.pizzeria;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import com.pizzeria.exception.StockageException;
import com.pizzeria.exception.StockageException.DeletePizzaException;
import com.pizzeria.exception.StockageException.SavePizzaException;
import com.pizzeria.exception.StockageException.UpdatePizzaException;
import com.pizzeria.model.CategoriePizzaEnum;

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
	public void testPizzaList() {
		assertFalse(isPizzaListEmpty());
	}

	public boolean isPizzaListEmpty() {

		for (Pizza p : testList) {
			if (p.getCode().isEmpty()) {
				return true;
			}

			if (p.getDesignation().isEmpty()) {
				return true;
			}

			if (p.getCatEnum() == null) {
				return true;
			}
		}

		return false;
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

	@Test
	public void testSaveNewPizza() throws SavePizzaException {

		Pizza p = new Pizza("TRY", "Test", -20, CategoriePizzaEnum.AUTRE);

		try {
			testList.add(p);
		} catch (Exception e) {
			throw new StockageException(e.getMessage()).new SavePizzaException(e.getMessage());
		}
		System.out.println(testList.toString());
	}

//	@Test
//	public void testUpdatePizza() {
//		try {
//			assertTrue(testUpdatePizzaFail());
//		} catch (UpdatePizzaException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	public boolean testUpdatePizzaFail() throws UpdatePizzaException {
//
//		try {
//			Pizza p = new Pizza("TRY", "Test", -20, CategoriePizzaEnum.AUTRE);
//			dao.updatePizza("TRY", p);		
//			return true;
//		}catch(Exception e){
//			throw new StockageException(e.getMessage()).new UpdatePizzaException(e.getMessage());
//		}
//	}

	@Test
	public void testDeletePizza() throws DeletePizzaException {

		Pizza p = new Pizza("TRY", "Test", -20, CategoriePizzaEnum.AUTRE);

		try {
			testList.remove(p);
		} catch (Exception e) {
			throw new StockageException("").new DeletePizzaException(
					"Impossible de supprimer la pizza, veuillez réessayer.");
		}
		System.out.println(testList.toString());
	}

	@Test(expected = DeletePizzaException.class)
	public void testDeletePizzaException() throws DeletePizzaException {
		dao.deletePizza("???");
	}


}
