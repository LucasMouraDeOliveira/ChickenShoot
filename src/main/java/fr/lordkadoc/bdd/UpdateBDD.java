package fr.lordkadoc.bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateBDD {

	public static void gainXP(String login, int amount){
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs;
		
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:chickenShoot.db");
			
			stmt = conn.prepareStatement("select experience from users where login = ?");
			stmt.setString(1, login);
			rs = stmt.executeQuery();
			
			rs.next();
			amount+=rs.getInt(1);
			
			stmt = conn.prepareStatement("update users set experience = ? where login = ?");
			stmt.setInt(1, amount);
			stmt.setString(2, login);
			stmt.executeUpdate();
		
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	

}
