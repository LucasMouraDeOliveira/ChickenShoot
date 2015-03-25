package fr.lordkadoc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.lordkadoc.launcher.ServerInstance;
import fr.lordkadoc.launcher.ServerManager;

@WebServlet(name="ServletRejoindrePartie", urlPatterns = { "/rejoindrePartie" })
public class ServletRejoindrePartie extends ServletBasique{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7397375722751273842L;
	
	@Override
	public void doPost(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
		rq.setAttribute("parties", 
				ServerManager.getSpecificInstances(ServerInstance.WAITING_PLAYERS).keySet());
		rq.setAttribute("page", "listeParties");
		rq.getRequestDispatcher("WEB-INF/index.jsp").forward(rq, rs);
	}

}
