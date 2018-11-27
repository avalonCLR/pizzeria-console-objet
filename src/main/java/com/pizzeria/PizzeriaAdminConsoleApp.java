package com.pizzeria;

import java.util.Scanner;

import com.pizzeria.exception.StockageException.DeletePizzaException;
import com.pizzeria.exception.StockageException.SavePizzaException;
import com.pizzeria.exception.StockageException.UpdatePizzaException;
import com.pizzeria.model.CategoriePizzaEnum;

/**
 * Console d'administration comprenant un menu.
 * 
 * @author CLR
 *
 */

public class PizzeriaAdminConsoleApp {

	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		// appel du menu
		Pizza.menuConsole();

		// choix utilisateur
		int choice = sc.nextInt();

		// CREATE
		
		PizzaMemDao dao = new PizzaMemDao();

		// boucle while
		while (choice != 99) {
			if (choice == 1) {
				System.out.println("Liste des pizzas");

				// READ
				// affichage des pizzas
				
				dao.findAllPizzas();

				
				
				Pizza.menuConsole();
				choice = sc.nextInt();
			}
			if (choice == 2) {
				System.out.println("Ajout d'une nouvelle pizza");

				// ADD
				//ajouter une pizza
				
				try {
				System.out.println("Saisir le code:");
				sc.nextLine();
				String code = sc.nextLine();

				System.out.println("Saisir le nom (sans espace):");
				String nom = sc.nextLine();

				System.out.println("Saisir le prix:");
				double prix = sc.nextDouble();
				sc.nextLine(); //fix scanner bug after double input
				System.out.println("Saisir la categorie:");
				System.out.println(CategoriePizzaEnum.toStringAll());
				String cat = sc.nextLine().toUpperCase();
				
				dao.saveNewPizza(new Pizza(code, nom, prix, CategoriePizzaEnum.valueOf(cat)));
				}catch (SavePizzaException e){
					System.out.println(e.getMessage());
				}
				
				Pizza.menuConsole();
				choice = sc.nextInt();
			}
			if (choice == 3) {
				System.out.println("Mise à jour d'une pizza");

				// UPDATE
				//modifier une pizza existante

				try {
				System.out.println("Saisir le code de la pizza à modifier");
				sc.nextLine();
				String oldCode = sc.nextLine();

				System.out.println("Saisir le nouveau code:");
				String newCode = sc.nextLine();

				System.out.println("Saisir le nouveau nom (sans espace):");
				String newName = sc.nextLine();

				System.out.println("Saisir le nouveau prix:");
				double newPrice = sc.nextDouble();
				sc.nextLine(); //fix scanner bug after double input 
				System.out.println("Saisir la categorie:");
				System.out.println(CategoriePizzaEnum.toStringAll());
				String cat = sc.nextLine().toUpperCase();

				
				// parcours le tableau sans utiliser l'id

				dao.updatePizza(oldCode, new Pizza(newCode, newName, newPrice, CategoriePizzaEnum.valueOf(cat)));
				}catch (UpdatePizzaException e){
					System.out.println(e.getMessage());
				}
				
				Pizza.menuConsole();
				choice = sc.nextInt();
			}
			if (choice == 4) {

				// DELETE
				//effacer une pizza existante
				
				try {
				System.out.println("Suppression d'une pizza");

				System.out.println("Saisir le code de la pizza à modifier");
				sc.nextLine();
				String oldCode = sc.nextLine();
		
					dao.deletePizza(oldCode);
				}catch (DeletePizzaException e) {
					System.out.println(e.getMessage());
				}
				
				Pizza.menuConsole();
				choice = sc.nextInt();
			} 
			if(choice == 5) {
				
				//lister les pizzas par prix decroissant
				dao.sortPizzasByPriceReversed();
				
				Pizza.menuConsole();
				choice = sc.nextInt();
			}
			if(choice == 6) {
			
				//lister les pizzas par code croissant
				dao.sortPizzasByCode();
				
				Pizza.menuConsole();
				choice = sc.nextInt();
			}
			else {
				System.out.println("Please make a choice:");
				Pizza.menuConsole();
				choice = sc.nextInt();
			}
		}
		System.out.println("Au revoir");
	}
}
