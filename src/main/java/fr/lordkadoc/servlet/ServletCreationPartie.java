package fr.lordkadoc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name="ServletCreationPartie", urlPatterns = { "/create" })
public class ServletCreationPartie extends ServletBasique{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2091148739199114751L;
	

	@Override
	public void doPost(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
		String nomPartie = rq.getParameter("nomPartie");
		String nbJoueurs = rq.getParameter("nbJoueurs");
		int nbj = 0;
		String erreur = "";
		boolean valide = true;
		
		if(nomPartie == null || nomPartie.isEmpty()){
			erreur = "Vous n'avez pas rentré d'identifiant de partie";
			valide = false;
		}else if(nomPartie.length() > 100){
			erreur = "Le nom de partie est trop long (max 20 caractères)";
			valide = false;
		}else{
			rq.setAttribute("nomPartie", nomPartie);			
			try{
				nbj = Integer.parseInt(nbJoueurs);
				if(nbj < 1){
					erreur = "Il faut au moins 1 joueur";
					valide = false;
				}else if(nbj > 20){
					erreur = "Pas plus de 20 joueurs";
					valide = false;
				}else{				
					rq.setAttribute("nbJoueurs", nbj);
				}
			}catch(Exception e){
				erreur = "Ce n'est pas un nombre !";
				valide = false;
			}
		}
		
		if(valide){
			rq.setAttribute("creator", "true");
			rq.setAttribute("page", "lobby");		
		}else{
			rq.setAttribute("erreur", erreur);
			rq.setAttribute("page", "creation");
		}
		
		rq.getRequestDispatcher("WEB-INF/index.jsp").forward(rq, rs);
	}
}
