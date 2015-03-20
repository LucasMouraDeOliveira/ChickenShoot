package fr.lordkadoc.bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InscriptionBDD {

	public static boolean inscription(String login, String password, String cpassword, String mail) {
		
		Connection conn = null;
		Statement stmt;
		ResultSet rs;
		
		if(login.isEmpty() || password.isEmpty() || !password.equals(cpassword) || mail.isEmpty()){
			return false;
		}
	
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:chickenShoot.db");
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from users where login = '" + login + "'");
			if(rs.next()){
				return false;
			}
			stmt = conn.createStatement();
			stmt.executeUpdate("insert into users values ('"+login+"','"+password+"',1,'"+mail+"',0)");
			
		} catch (ClassNotFoundException | SQLException e) {
			return false;
		}	
		
		return true;
	}

}
