package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import model.queries.CardQueries;
import model.queries.KdvQueries;

public class Database {
	
	private final CardQueries queries = new CardQueries();
	private final KdvQueries kdvQueries = new KdvQueries();
	
	private String url = "jdbc:mysql://localhost:3306/workshop";
	private String userName = "root";
	private String password = "password";
	
	
	public Connection connect() {
		try {
			Connection conn = DriverManager.getConnection(url, userName, password);
			return conn;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void createCardDatabase() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Statement cardSt = connect().createStatement();
			cardSt.executeUpdate(queries.getCreateCardQuery());
			
			System.out.println("Success (Card)!");
			connect().close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void createKdvDatabase() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Statement kdvSt = connect().createStatement();
			kdvSt.executeUpdate(kdvQueries.getCreateKdvQuery());
			
			System.out.println("Success (KDV)!");
			connect().close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}