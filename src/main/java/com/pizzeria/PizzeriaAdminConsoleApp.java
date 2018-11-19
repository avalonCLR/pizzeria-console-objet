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
		// creation du tableau

		int tailleTab = 8;
		Pizza[] pizzas = new Pizza[tailleTab];
		pizzas[0] = new Pizza("PEP", "Pépéroni", 12.50);
		pizzas[1] = new Pizza("MAR", "Margherita", 14.00);
		pizzas[2] = new Pizza("REIN", "La Reine", 11.50);
		pizzas[3] = new Pizza("FRO", "La 4 fromages", 12.00);
		pizzas[4] = new Pizza("CAN", "La cannibale", 12.50);
		pizzas[5] = new Pizza("SAV", "La savoyarde", 13.00);
		pizzas[6] = new Pizza("ORI", "L'orientale", 13.50);
		pizzas[7] = new Pizza("IND", "L'indienne", 14.00);

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
				for (Pizza p : pizzas) {
					System.out.println(p.toString());
				}

				menuConsole();
				choice = sc.nextInt();
			}
			if (choice == 2) {
				System.out.println("Ajout d'une nouvelle pizza");

				// ADD
				System.out.println("Saisir le code:");
				sc.nextLine();
				code = sc.nextLine();

				System.out.println("Saisir le nom (sans espace):");
				nom = sc.nextLine();

				System.out.println("Saisir le prix:");
				prix = sc.nextDouble();

				tailleTab++;
				Pizza[] tmp = new Pizza[tailleTab];
				for (int i = 0; i < pizzas.length; i++) {
					tmp[i] = pizzas[i];
				}
				pizzas = tmp;

				pizzas[tailleTab - 1] = new Pizza(code, nom, prix);

				System.out.println("Nouvelle pizza ajoutée");

				menuConsole();
				choice = sc.nextInt();
			}
			if (choice == 3) {
				System.out.println("Mise à jour d'une pizza");

				// UPDATE
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

				for (Pizza p : pizzas) {

					if (oldCode.equals(p.getCode())) {
						p.setCode(newCode);
						p.setDesignation(newName);
						p.setPrix(newPrice);
					}

				}

				System.out.println("Mise à jour effectuée");
				menuConsole();
				choice = sc.nextInt();
			}
			if (choice == 4) {

				// DELETE

				System.out.println("Suppression d'une pizza");

				System.out.println("Saisir le code de la pizza à modifier");
				sc.nextLine();
				String oldCode = sc.nextLine();

				tailleTab--;
				Pizza[] tmp = new Pizza[tailleTab];
				
				int i=0;
					for (Pizza p : pizzas) {

						if (!oldCode.equals(p.getCode())) {
							tmp[i] = p;
							i++;
						}

					}pizzas = tmp;
				
				

				System.out.println("Pizza supprimée");

				menuConsole();
				choice = sc.nextInt();
			} else {
				System.out.println("Please make a choice:");
				menuConsole();
				choice = sc.nextInt();
			}
		}

		System.out.println("Au revoir");

	}

	public static void menuConsole() {
		System.out.println("***Pizzeria Administration***");
		System.out.println("[1] Lister les pizzas");
		System.out.println("[2] Ajouter une nouvelle pizza");
		System.out.println("[3] Mettre à jour une pizza");
		System.out.println("[4] Supprimer une pizza");
		System.out.println("[99] Sortir");
	}

}
