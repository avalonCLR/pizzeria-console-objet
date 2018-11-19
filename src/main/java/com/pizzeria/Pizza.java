package com.pizzeria;

/**
 * Creation de la classe pizza comprenant un nom, un code, un prix et un id.
 * @author CLR
 *
 */
public class Pizza {

	//creation des attributs
	int id;
	String code;
	String designation;
	double prix;
	
	
	//generation du constructeur
	public Pizza(int id, String code, String designation, double prix) {
		super();
		this.id = id;
		this.code = code;
		this.designation = designation;
		this.prix = prix;
	}

	
	//generation des accesseurs
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
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

	
	
}
