package fr.lordkadoc.bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import fr.lordkadoc.launcher.PlayerClassement;

public class GetBDD {

	public static List<PlayerClassement> getClassement() {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		List<PlayerClassement> classement = new ArrayList<PlayerClassement>();
		PlayerClassement player;
		
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:chickenShoot.db");
			
			stmt = conn.prepareStatement("select login, experience from users order by experience desc");
			rs = stmt.executeQuery();
			
			while(rs.next()){
				player = new PlayerClassement(rs.getString(1), rs.getInt(2));
				classement.add(player);
			}
		
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return classement;
	}

}
