package com.pizzeria;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.pizzeria.exception.StockageException;
import com.pizzeria.exception.StockageException.DeletePizzaException;
import com.pizzeria.exception.StockageException.SavePizzaException;
import com.pizzeria.exception.StockageException.UpdatePizzaException;

public class PizzaMemDao implements IPizzaDao {

	//init pizzas in an array
	private static int tailleTab = 8;
	private static Pizza[] pizzas = new Pizza[tailleTab];
	{
	pizzas[0] = new Pizza("PEP", "Pépéroni", 12.50);
	pizzas[1] = new Pizza("MAR", "Margherita", 14.00);
	pizzas[2] = new Pizza("REIN", "La Reine", 11.50);
	pizzas[3] = new Pizza("FRO", "La 4 fromages", 12.00);
	pizzas[4] = new Pizza("CAN", "La cannibale", 12.50);
	pizzas[5] = new Pizza("SAV", "La savoyarde", 13.00);
	pizzas[6] = new Pizza("ORI", "L'orientale", 13.50);
	pizzas[7] = new Pizza("IND", "L'indienne", 14.00);
	}
	
	//put array in an arraylist
	List<Pizza> pizzaList = new ArrayList<Pizza>(Arrays.asList(pizzas));
	
	//find all pizza and display them
	public List<Pizza> findAllPizzas() {
		for (Pizza p : pizzaList) {
			System.out.println(p.toString());
		}
		return pizzaList;
	}

	//find pizza by code and display
	public Pizza findPizzaByCode(String codePizza) {
		for (Pizza p : pizzaList) {
			if (codePizza.equals(p.getCode())) {
				return p;
			}
		}
		return null;
	}
	
	//check if the pizza exists
	public boolean pizzaExists(String codePizza) {
		
		for (Pizza p : pizzaList) {
			if (codePizza.equals(p.getCode())) {
				return true;
			}
		}
		return false;
	}
	
	//add a new pizza
	public void saveNewPizza(Pizza pizza) throws SavePizzaException{
		
		boolean ok = false;
		
		try{
			pizza.dataController();
			ok = true;
		}catch(Exception e){
			throw new StockageException(e.getMessage()).new SavePizzaException(e.getMessage());
		}

		if(ok = true) {
			pizzaList.add(pizza);
			System.out.println("Nouvelle pizza ajoutée");
		}
	}

	//modify existing pizza
	public void updatePizza(String codePizza, Pizza pizza) throws UpdatePizzaException{
		
		boolean ok = false;
		
		try{
			pizza.dataController();
			ok = true;
		}catch(Exception e){
			throw new StockageException(e.getMessage()).new UpdatePizzaException(e.getMessage());
		}
		
		if(pizzaExists(codePizza)==true & ok == true) {
		Pizza p = findPizzaByCode(codePizza);
		
		p.setCode(pizza.getCode());
		p.setDesignation(pizza.getDesignation());
		p.setPrix(pizza.getPrix());
		
		System.out.println("Mise à jour effectuée");
		}
		
	}

	//delete pizza
	public void deletePizza(String codePizza) throws DeletePizzaException {
		
		boolean ok = false;
		try{
			findPizzaByCode(codePizza).dataController();
			ok = true;
		}catch(Exception e){
			throw new StockageException("").new DeletePizzaException("Impossible de supprimer la pizza, veuillez réessayer.");
		}
		
		if(pizzaExists(codePizza)==true & ok == true) {
		pizzaList.remove(findPizzaByCode(codePizza));
		System.out.println("Pizza supprimée");
		}

	}
	
	
	//sort pizza by price in reversed order
	public List<Pizza> sortPizzasByPriceReversed() {
	
		pizzaList.sort(Comparator.comparingDouble(Pizza::getPrix).reversed());
		
		return pizzaList;
	}
	
	//sort pizza by code in order
	public List<Pizza> sortPizzasByCode() {
		
		//pizzaList.sort((p1, p2)-> p1.getCode().compareToIgnoreCase(p2.getCode()));
		
		Collections.sort(pizzaList,new Comparator<Pizza>() {
			@Override
			public int compare(Pizza p1, Pizza p2) {
				int result = p1.getCode().compareTo(p2.getCode());
			  	return result; 	
			}
		});
		return pizzaList;
	}
	

}
