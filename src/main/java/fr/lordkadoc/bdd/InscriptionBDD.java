package fr.lordkadoc.bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InscriptionBDD {
	
	private String erreur = "";

	public boolean inscription(String login, String password, String cpassword, String mail) {
		
		Connection conn = null;
		PreparedStatement stmt;
		ResultSet rs;
		
		if(login.isEmpty()){
			this.erreur = "Vous n'avez pas rentré votre login";
			return false;
		}else if(password.isEmpty()){
			this.erreur = "Vous n'avez pas rentré votre mot de passe";
			return false;
		}else if(password.length() <= 5){
			this.erreur = "Le mot de passe est trop court. Rentrez un mot de passe d'au moins 6 caractères.";
			return false;
		}else if(!password.equals(cpassword)){
			this.erreur = "Le mot de passe et le mot de passe de confirmation ne correspondent pas";
			return false;
		}else if(mail.isEmpty()){
			this.erreur = "Vous n'avez pas rentré votre adresse mail";
			return false;
		}
		
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:chickenShoot.db");
			stmt = conn.prepareStatement("select * from users where login = ?");
			stmt.setString(1, login);
			rs = stmt.executeQuery();
			if(rs.next()){
				this.erreur = "Il existe déjà un utilisateur avec le même login. Veuillez en choisir un nouveau.";
				return false;
			}
			stmt = conn.prepareStatement("insert into users values (?,?,1,?,0)");
			stmt.setString(1, login);
			stmt.setString(2, password);
			stmt.setString(3, mail);
			stmt.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			this.erreur = "Une erreur est survenue au niveau de la base de données. C'est chelou parce que normalement j'ai tout bien codé";
			return false;
		}	
		
		return true;
	}

	public String getErreur() {
		return this.erreur;
	}

}
