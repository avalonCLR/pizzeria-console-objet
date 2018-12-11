package com.pizzeria;

public class Categorie {

	private Integer idCat = null;
	private String nameCat = null;
	
	
	public Categorie(Integer idCat, String nameCat) {
		this.idCat = idCat;
		this.nameCat = nameCat;
	}


	public Categorie(Integer idCat) {
		this.idCat = idCat;
	}


	public Integer getIdCat() {
		return idCat;
	}


	public void setIdCat(Integer idCat) {
		this.idCat = idCat;
	}


	public String getNameCat() {
		return nameCat;
	}


	public void setNameCat(String nameCat) {
		this.nameCat = nameCat;
	}


	@Override
	public String toString() {
		return "categorie = " + idCat + " " + nameCat;
	}
	
	
	
	
}
