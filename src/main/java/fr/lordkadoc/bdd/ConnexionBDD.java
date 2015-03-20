package fr.lordkadoc.bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnexionBDD {
	
	public static boolean connect(String login, String password){
		
		
		Connection conn = null;
		Statement stmt;
		ResultSet rs;
		
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:chickenShoot.db");
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from users where login = '" + login + "' and mdp = '" + password + "'");
			if(rs.next()){
				return true;
			}else{
				return false;
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			return false;
		}
	}

}
