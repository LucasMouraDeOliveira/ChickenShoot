package fr.lordkadoc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.lordkadoc.launcher.ServerManager;


@WebServlet(name="ServletCreationPartie", urlPatterns = { "/create" })
public class ServletCreationPartie extends ServletBasique{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2091148739199114751L;
	

	@Override
	public void doPost(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
		String nomPartie = rq.getParameter("nomPartie");
		ServerManager.ajouterInstance(nomPartie);
		rq.setAttribute("page", "lobby");
		rq.getRequestDispatcher("WEB-INF/index.jsp").forward(rq, rs);
	}
}
