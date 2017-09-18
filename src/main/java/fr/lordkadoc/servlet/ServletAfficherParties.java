package fr.lordkadoc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.refactoring.server.ServerManager;

@WebServlet(name="ServletAfficherParties", urlPatterns = { "/afficherParties" })
public class ServletAfficherParties extends ServletBasique{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7397375722751273842L;
	
	@Override
	public void doPost(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
		
		if(rq.getSession(true).getAttribute("connecte") == "true"){
			ServerManager manager = ServerManager.getInstance();
			rq.setAttribute("parties", manager.getServersInformation(manager.getWaitingServers()));
			rq.setAttribute("page", "listeParties");	
		}else{	
			rq.setAttribute("page","acceuil");	
		}
		rq.getRequestDispatcher("WEB-INF/index.jsp").forward(rq, rs);
		
	}

}
