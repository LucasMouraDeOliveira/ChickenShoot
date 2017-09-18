package fr.lordkadoc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.refactoring.server.ServerManager;


@WebServlet(name="ServletRejoindrePartie", urlPatterns = { "/rejoindreLobby" })
public class ServletRejoindrePartie extends ServletBasique{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2091148739199114751L;
	

	@Override
	public void doPost(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
		String nomPartie = rq.getParameter("gameid");
		ServerManager manager = ServerManager.getInstance();
		rq.setAttribute("nomPartie", nomPartie);
		rq.setAttribute("nbJoueurs", manager.getServerWithName(nomPartie).getMaxPlayers());
		rq.setAttribute("creator", "false");
		rq.setAttribute("page", "lobby");		
		rq.getRequestDispatcher("WEB-INF/index.jsp").forward(rq, rs);
	}
}
