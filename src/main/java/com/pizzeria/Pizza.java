package com.pizzeria;

/**
 * Creation de la classe pizza comprenant un nom, un code, un prix et un id.
 * @author CLR
 *
 */
public class Pizza {

	//creation des attributs
	private int id;
	private String code = null;
	private String designation = null;
	private double prix;
	
	private static int lastId = -1;
	
	
	//generation du constructeur
	public Pizza(String code, String designation, double prix) {
		this.id = ++lastId;
		this.code = code;
		this.designation = designation;
		this.prix = prix;
	}
	
	
	//generation des accesseurs
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


	@Override
	public String toString() {
		return code + " -> " + designation + " (" + prix + "€)";
	}

	
	
}
