package com.pizzeria.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.pizzeria.Categorie;
import com.pizzeria.utils.Connector;

public class CategorieDao {
	public ArrayList<Categorie> getAllCategorie() {
		Connection conn = null;
		Statement statement = null;
		ResultSet rs = null;
		ArrayList<Categorie> myAllCategorie = new ArrayList<Categorie>();
		try{
			conn = Connector.getConnection();
			statement = conn.createStatement();
			rs = statement.executeQuery("SELECT  id, nom FROM categorie");

			while (rs.next()){
				myAllCategorie.add(new Categorie(rs.getInt("id"),rs.getString("nom")));
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				rs.close();
				statement.close();
				conn.close();
			}
			catch (SQLException e){

				e.printStackTrace();
			}
			
		}
		return myAllCategorie;
	}
	public Categorie getOneCategorie(int id) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Categorie myCat = null;
		try{
			conn = Connector.getConnection();
			ps = conn.prepareStatement("SELECT  id, nom FROM categorie WHERE id = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while (rs.next()){		
				myCat = new Categorie(rs.getInt("id"),rs.getString("nom"));
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				rs.close();
				ps.close();
				conn.close();
			}
			catch (SQLException e){

				e.printStackTrace();
			}
			
		}
		
		return myCat;
	}
}
