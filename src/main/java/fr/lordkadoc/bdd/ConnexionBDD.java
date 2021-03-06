package fr.lordkadoc.bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnexionBDD {
	
	public static boolean connect(String login, String password){
			
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:chickenShoot.db");
			stmt = conn.prepareStatement("select * from users where login = ? and mdp = ?");
			stmt.setString(1, login);
			stmt.setString(2, password);
			rs = stmt.executeQuery();
			
			//S'il y a un résultat correspondant dans la BDD, on retourne vrai
			return rs.next();
			
		} catch (ClassNotFoundException | SQLException e) {
			return false;
		}finally{
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
