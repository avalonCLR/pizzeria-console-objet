package com.pizzeria;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import com.pizzeria.dao.PizzaMemDao;
import com.pizzeria.model.CategorieDao;
import com.pizzeria.utils.Connector;

/**
 * Console d'administration comprenant un menu.
 * 
 * @author CLR
 *
 */

public class PizzeriaAdminConsoleApp {

	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		Connection co = null;
		
		try {
			co = Connector.getConnection();
			
			System.out.println("Connected");
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			
			try {
			co.close();
			System.out.println("Disconnected");
			}catch(SQLException e) {
				System.out.println(e.getErrorCode());
			}
		}
		
		Pizza piz;
		int id_cat;
		Categorie cat;
		CategorieDao myCat = new CategorieDao();
		
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
				System.out.println("Saisir le code:");
				sc.nextLine();
				String code = sc.nextLine();

				System.out.println("Saisir le nom (sans espace):");
				String nom = sc.nextLine();

				System.out.println("Saisir le prix:");
				double prix = sc.nextDouble();
				sc.nextLine(); //fix scanner bug after double input
				
				System.out.println("Veuillez saisir la categorie \n\r"
 						+ "° 1  Fromage \r\n"
 						+ "° 2  Viande \r\n"
 						+ "° 3  Poisson \r\n"
 						+ "° 4  Autre");
 				
 				id_cat = Integer.parseInt(sc.nextLine());
 				cat  = myCat.getOneCategorie(id_cat);
 				piz = new Pizza(code, nom, prix, cat);
 				
 				try {
				dao.createPizza(piz);
				
				}catch (Exception e){
					System.out.println(e.getMessage());
				}
				
				Pizza.menuConsole();
				choice = sc.nextInt();
			}
			if (choice == 3) {
				System.out.println("Mise à jour d'une pizza");

				// UPDATE
				//modifier une pizza existante
				System.out.println("Saisir le code de la pizza à modifier");
				sc.nextLine();
				System.out.println("Veuillez saisir l'id de la pizza à modifier");
 				int id = Integer.parseInt(sc.nextLine()) ;
 				System.out.println("Veuillez saisir le nouveau code");
 				String code = sc.nextLine();
 				System.out.println("Veuillez saisir le nouveau nom :");
 				String name = sc.nextLine();
 				System.out.println("Veuillez saisir le nouveau prix:");
 				Double price = Double.valueOf(sc.nextLine());
 				System.out.println("Veuillez saisir la categorie \n\r"
 						+ "° tapez 1 pour Fromage \r\n"
 						+ "° 2 pour Viande \r\n"
 						+ "° 3 pour Poisson \r\n"
 						+ "° 4 pour Autre \r\n\"");
 				id_cat = Integer.parseInt(sc.nextLine());
 				cat  = myCat.getOneCategorie(id_cat);
 				piz = new Pizza(id, code, name, price, cat);

				try {
					dao.updatePizza(piz);
				}catch (Exception e){
					System.out.println(e.getMessage());
				}
				
				Pizza.menuConsole();
				choice = sc.nextInt();
			}
			if (choice == 4) {

				// DELETE
				//effacer une pizza existante
				System.out.println("Suppression d'une pizza");

				System.out.println("Saisir l'id de la pizza à modifier");
				sc.nextLine();
				int id = Integer.parseInt(sc.nextLine());
				try {
					dao.deletePizza(id);
				}catch (Exception e) {
					System.out.println(e.getMessage());
				}
				
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
