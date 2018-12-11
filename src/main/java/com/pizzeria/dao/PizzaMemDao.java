package com.pizzeria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.pizzeria.Categorie;
import com.pizzeria.Pizza;
import com.pizzeria.exception.StockageException;
import com.pizzeria.exception.StockageException.SavePizzaException;
import com.pizzeria.exception.StockageException.UpdatePizzaException;
import com.pizzeria.utils.Connector;


public class PizzaMemDao implements IPizzaDao {

	
	final int CODE_LONG = 4;
	final int PRICE_MAX = 20;
	final int PRICE_MIN = 5;
	
	//find all pizza and display them
	public List<Pizza> findAllPizzas() {
		
		Connection co = null;
		Statement st = null;
		ResultSet rs = null;
		
		List<Pizza> pizzaList = new ArrayList<Pizza>();
		
		try {

			co = Connector.getConnection();
			st = co.createStatement();

			rs = st.executeQuery("SELECT * FROM pizza JOIN categorie ON pizza.ID_CATEGORIE = categorie.ID");

			ResultSetMetaData rsmd = rs.getMetaData();

			while (rs.next()) {
		
				pizzaList.add(
						new Pizza(rs.getInt("ID"), 
						rs.getString("CODE"), 
						rs.getString("DESIGNATION"), 
						rs.getDouble("PRIX"), 
						new Categorie(rs.getInt("ID"), 
						rs.getString("NOM"))));
			}

			System.out.println("\r\n====");
	
		} catch (Exception e) {
			e.printStackTrace();

		} finally {

			try {
				rs.close();
				st.close();
				co.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		for (Pizza p : pizzaList) {
			System.out.println(p.toString());
		}
		return pizzaList;
	}

	public void createPizza(Pizza pizza) throws Exception {
		
		Connection co = null;
		PreparedStatement pstatement = null;
		
		try {
			co = Connector.getConnection();
			
			pstatement = co.prepareStatement("INSERT INTO pizza (CODE, DESIGNATION, PRIX, ID_CATEGORIE) values (?,?,?,?) ");
			pstatement.setString(1, pizza.getCode());
			pstatement.setString(2, pizza.getDesignation());
			pstatement.setDouble(3, pizza.getPrix());
			pstatement.setInt(4, pizza.getCategorie().getIdCat());
			
			int result = pstatement.executeUpdate();
			if(result == 0) {
				System.out.println("Cannot register pizza");
			}
			
		}catch(Exception e) {
			throw new Exception("Unable to add pizza");
		}finally {
			
			try {
				pstatement.close();
				co.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
				
		}	
	}

	
	public void updatePizza(Pizza pizza) throws Exception {
		
		Connection co = null;
		PreparedStatement pstatement = null;
		
		try {
			co = Connector.getConnection();
			
			pstatement = co.prepareStatement("UPDATE pizza SET code =?, designation=?, prix=?, id_categorie=? WHERE id = ?");
			pstatement.setString(1, pizza.getCode());
			pstatement.setString(2, pizza.getDesignation());
			pstatement.setDouble(3, pizza.getPrix());
			pstatement.setInt(4, pizza.getCategorie().getIdCat());
			pstatement.setInt(5, pizza.getId());
			int result = pstatement.executeUpdate();
			if(result == 0) {
				System.out.println("Cannot update pizza");
			}
			
		}catch(Exception e) {
			throw new Exception("Unable to update pizza");
		}finally {

			try {
				pstatement.close();
				co.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		

	}

	public void deletePizza(int id) throws Exception {
		Connection co = null;
		PreparedStatement pstatement = null;

		try {
			co = Connector.getConnection();

			pstatement = co.prepareStatement("DELETE FROM pizza WHERE id=?");
			pstatement.setInt(1, id);

			int result = pstatement.executeUpdate();
			if (result == 0) {
				System.out.println("No pizza to delete");
			}

		} catch (Exception e) {
			throw new Exception("Cannot perform delete");
		} finally {
			try {
				pstatement.close();
				co.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
	
	//find pizza by id
	public Pizza findPizzaById(int id) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Pizza myPiz = null;
		try{
			conn = Connector.getConnection();
			ps = conn.prepareStatement("SELECT pizza.id, code, designation, prix , id_categorie, categorie.nom FROM pizza JOIN categorie ON pizza.id_categorie = categorie.id WHERE pizza.id = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while (rs.next()){		
				myPiz = new Pizza(rs.getInt("id"),rs.getString("code"),rs.getString("designation"), rs.getDouble("price"), 
						new Categorie(rs.getInt("id_categorie"),rs.getString("name")));
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
		return myPiz;
	}
	
	public boolean checkMyPizza(Pizza piz) throws StockageException{
		boolean ifOk= true;
		String message = "";
		
		if(piz == null) {
			message += "la pizza n'existe pas !";
		}else {
			if(piz.getCode().trim().length()> CODE_LONG) {
				message += "le code de la pizza est trop long, \n\r";
			}if(piz.getPrix()> PRICE_MAX) {
				message += "le prix de la pizza est trop grand, \n\r";
			}if(piz.getPrix()< PRICE_MIN) {
				message += "le prix de la pizza est trop grand, \n\r";
			}
		}
		if(message.length()>0 && message != null) {
			ifOk = false;
			throw  new StockageException(message);
		}
			return ifOk;
	}

}
