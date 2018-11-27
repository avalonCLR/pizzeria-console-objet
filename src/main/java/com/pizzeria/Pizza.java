package com.pizzeria;

import com.pizzeria.exception.StockageException;
import com.pizzeria.model.CategoriePizzaEnum;

/**
 * Creation de la classe pizza comprenant un nom, un code, un prix et un id.
 * 
 * @author CLR
 *
 */
public class Pizza {

	// creation des attributs
	private int id;
	private String code = null;
	private String designation = null;
	private double prix;

	CategoriePizzaEnum catEnum; //donner un nom pour utiliser l'enum
	
	private static int lastId = -1;

	// generation du constructeur
	public Pizza(String code, String designation, double prix, CategoriePizzaEnum catEnum) {
		this.id = ++lastId;
		this.code = code;
		this.designation = designation;
		this.prix = prix;
		this.catEnum = catEnum;
	}

	// generation des accesseurs
	public int getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}
	
	
	public CategoriePizzaEnum getCatEnum() {
		return catEnum;
	}

	public void setCatEnum(CategoriePizzaEnum catEnum) {
		this.catEnum = catEnum;
	}

	@Override
	public String toString() {
		return code + " -> " + designation + " (" + prix + "€)"+" - "+ catEnum;
	}

	// affichage menu
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

	public static final int MAX_PRICE = 99;
	public static final int MIN_PRICE = 0;
	public static final int MIN_CHAR = 1;
	public static final int MAX_CHAR = 4;
	public static final int MAX_NAME = 20;

	public void dataController() throws StockageException {
			
			String message = "";
			
			if(code.trim().length() < MIN_CHAR || code == null || code.trim().length() > MAX_CHAR ) {
				message += "Veuillez saisir un autre code, 4 charactères max. \r\n";
			}
			if(designation.trim().length() < MIN_CHAR || designation == null || designation.trim().length() > MAX_NAME) {
				message += "Veuillez saisir un autre nom, 20 charactères max. \r\n";
			}
			if(prix < MIN_PRICE || prix > MAX_PRICE) {
				message += "Veuillez saisir un autre prix. \r\n";
			}
			 
			if (message.trim().length() > 0) {
				throw new StockageException(message);
			}
		}

}
