package com.pizzeria;

public class PizzaMemDao implements IPizzaDao {

	
	static int tailleTab = 8;
	static Pizza[] pizzas = new Pizza[tailleTab];
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
	

	public Pizza[] findAllPizzas() {
		for (Pizza p : pizzas) {
			System.out.println(p.toString());
		}
		return pizzas;
	}

	public void updatePizza(String codePizza, Pizza pizza) {
		
		if(isPizzaExists(codePizza)==true) {
		Pizza p = findPizzaByCode(codePizza);
		
		p.setCode(pizza.getCode());
		p.setDesignation(pizza.getDesignation());
		p.setPrix(pizza.getPrix());
		}else {
			System.out.println("Pizza n'existe pas");
		}		
	}

	public Pizza findPizzaByCode(String codePizza) {
		for (Pizza p : pizzas) {
			if (codePizza.equals(p.getCode())) {
				return p;
			}
		}
		return null;
	}

	public boolean isPizzaExists(String codePizza) {
		for (Pizza p : pizzas) {
			if (codePizza.equals(p.getCode())) {
				return true;
			}
		}
		return false;
	}

	public void addPizza(Pizza pizza) {
		tailleTab++;
		Pizza[] tmp = new Pizza[tailleTab];
		for (int i = 0; i < pizzas.length; i++) {
			tmp[i] = pizzas[i];
		}
		pizzas = tmp;
		pizzas[tailleTab - 1] = pizza;
		
	}

	public void deletePizza(String codePizza) {
		tailleTab--;
		Pizza[] tmp = new Pizza[tailleTab];
		
		int i=0;
			for (Pizza p : pizzas) {

				if (!codePizza.equals(p.getCode())) {
					tmp[i] = p;
					i++;
				}

			}pizzas = tmp;
		
	}
	
	

}
