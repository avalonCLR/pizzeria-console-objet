package com.pizzeria;

import java.util.List;

import com.pizzeria.exception.StockageException.DeletePizzaException;
import com.pizzeria.exception.StockageException.SavePizzaException;
import com.pizzeria.exception.StockageException.UpdatePizzaException;

public interface IPizzaDao {
	
	List<Pizza> findAllPizzas();
	void saveNewPizza(Pizza pizza) throws SavePizzaException;
	void updatePizza(String codePizza, Pizza pizza) throws UpdatePizzaException;
	void deletePizza(String codePizza) throws DeletePizzaException;
	Pizza findPizzaByCode(String codePizza);
	boolean pizzaExists(String codePizza);

}
