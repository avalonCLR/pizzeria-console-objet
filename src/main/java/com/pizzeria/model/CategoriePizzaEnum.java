package com.pizzeria.model;

public enum CategoriePizzaEnum {
	FROMAGE("Fromage"), VIANDE("Viande"), POISSON("Poisson"), AUTRE("Autre");

	private String nom;

	private CategoriePizzaEnum(String nom) {
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String toString() {
		return this.nom;
	}

	public static String toStringAll() {
		String result = "";
		CategoriePizzaEnum[] tabEnum = CategoriePizzaEnum.values(); //get values of Enum in a tab
		for (CategoriePizzaEnum categoriePizzaEnum : tabEnum) {
			categoriePizzaEnum.toString();
			result += categoriePizzaEnum.getNom() + "\r\n";
		}
		return result;
	}

}
