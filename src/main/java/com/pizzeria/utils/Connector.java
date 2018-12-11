package com.pizzeria.utils;

import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class Connector {

	public static Connection getConnection() throws Exception {

		Properties prop = new Properties();
		InputStream input = null;
		
		String filename = "parameters.properties";
		
		//load parameters from .properties file
		input = Connector.class.getClassLoader().getResourceAsStream(filename);

		if (input == null) {
			System.out.println("Sorry, unable to find " + filename);
			return null;
		}

		// load a properties file from class path, inside static method
		prop.load(input);
		
		//init database connection
		DriverManager.setLogWriter(new PrintWriter(System.out));
		Class.forName("com.mysql.cj.jdbc.Driver");

		return DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("user"), prop.getProperty("password"));

	}

}
