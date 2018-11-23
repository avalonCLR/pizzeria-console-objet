package com.pizzeria;

import java.util.Scanner;

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
		menuConsole();

		// choix utilisateur
		int choice = sc.nextInt();

		// CREATE
		
		PizzaMemDao dao = new PizzaMemDao();

		String code;
		String nom;
		double prix;
		int id;

		// boucle while
		while (choice != 99) {
			if (choice == 1) {
				System.out.println("Liste des pizzas");

				// READ
				// affichage des pizzas
				
				dao.findAllPizzas();

				menuConsole();
				choice = sc.nextInt();
			}
			if (choice == 2) {
				System.out.println("Ajout d'une nouvelle pizza");

				// ADD
				//ajouter une pizza
				System.out.println("Saisir le code:");
				sc.nextLine();
				code = sc.nextLine();

				System.out.println("Saisir le nom (sans espace):");
				nom = sc.nextLine();

				System.out.println("Saisir le prix:");
				prix = sc.nextDouble();

				dao.saveNewPizza(new Pizza(code, nom, prix));
				
				System.out.println("Nouvelle pizza ajoutée");

				menuConsole();
				choice = sc.nextInt();
			}
			if (choice == 3) {
				System.out.println("Mise à jour d'une pizza");

				// UPDATE
				//modifier une pizza existante
				System.out.println("Saisir le code de la pizza à modifier");
				sc.nextLine();
				String oldCode = sc.nextLine();

				System.out.println("Saisir le nouveau code:");
				String newCode = sc.nextLine();

				System.out.println("Saisir le nouveau nom (sans espace):");
				String newName = sc.nextLine();

				System.out.println("Saisir le nouveau prix:");
				double newPrice = sc.nextDouble();

				// parcours le tableau sans utiliser l'id

				dao.updatePizza(oldCode, new Pizza(newCode, newName, newPrice));

				System.out.println("Mise à jour effectuée");
				menuConsole();
				choice = sc.nextInt();
			}
			if (choice == 4) {

				// DELETE
				//effacer une pizza existante
				System.out.println("Suppression d'une pizza");

				System.out.println("Saisir le code de la pizza à modifier");
				sc.nextLine();
				String oldCode = sc.nextLine();

				dao.deletePizza(oldCode);

				System.out.println("Pizza supprimée");

				menuConsole();
				choice = sc.nextInt();
			} 
			if(choice == 5) {
				
				//lister les pizzas par prix decroissant
				dao.sortPizzasByPriceReversed();
				
				menuConsole();
				choice = sc.nextInt();
			}
			if(choice == 6) {
			
				//lister les pizzas par code croissant
				dao.sortPizzasByCode();
				
				
				menuConsole();
				choice = sc.nextInt();
			}
			else {
				System.out.println("Please make a choice:");
				menuConsole();
				choice = sc.nextInt();
			}
		}

		System.out.println("Au revoir");

	}

	
	//affichage menu
	public static void menuConsole() {
		System.out.println("***Pizzeria Administration***");
		System.out.println("[1] Lister les pizzas");
		System.out.println("[2] Ajouter une nouvelle pizza");
		System.out.println("[3] Mettre à jour une pizza");
		System.out.println("[4] Supprimer une pizza");
		System.out.println("[5] Lister par prix décroissant");
		System.out.println("[6] Lister par code croissant");
		System.out.println("[99] Sortir");
	}

}
