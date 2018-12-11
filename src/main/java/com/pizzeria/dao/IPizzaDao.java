package com.pizzeria.dao;

import java.util.List;

import com.pizzeria.Pizza;
import com.pizzeria.exception.StockageException;
import com.pizzeria.exception.StockageException.DeletePizzaException;
import com.pizzeria.exception.StockageException.SavePizzaException;
import com.pizzeria.exception.StockageException.UpdatePizzaException;

public interface IPizzaDao {
	
	List<Pizza> findAllPizzas();
	void createPizza(Pizza pizza) throws Exception;
	void updatePizza(Pizza pizza) throws Exception;
	void deletePizza(int id) throws Exception;
	Pizza findPizzaById(int id);
	
}
