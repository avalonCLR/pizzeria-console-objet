package com.pizzeria;

import java.util.Scanner;

/**
 * Console d'administration comprenant un menu.
 * @author CLR
 *
 */

public class PizzeriaAdminConsoleApp {

	public static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		//appel du menu
		menuConsole();

		//choix utilisateur
		int choice = sc.nextInt();
		
		//boucle while
		while(choice!=99) {
			if(choice==1) {
				System.out.println("Liste des pizzas");
				menuConsole();
				choice = sc.nextInt();
			}
			if(choice==2) {
				System.out.println("Ajout d'une nouvelle pizza");
				menuConsole();
				choice = sc.nextInt();
			}
			if(choice==3) {
				System.out.println("Mise à jour d'une pizza");
				menuConsole();
				choice = sc.nextInt();
			}
			if(choice==4) {
				System.out.println("Suppression d'une pizza");
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
