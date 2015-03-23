package fr.lordkadoc.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.lordkadoc.launcher.ServerManager;

@WebServlet(name="ServletRejoindrePartie", urlPatterns = { "/rejoindrePartie" })
public class ServletRejoindrePartie extends ServletBasique{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7397375722751273842L;
	
	@Override
	public void doPost(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
		List<String> nomParties = new ArrayList<String>();
		for(String s : ServerManager.getParties().keySet()){
			nomParties.add(s);
		}
		rq.setAttribute("parties", nomParties);
		rq.setAttribute("page", "listeParties");
		rq.getRequestDispatcher("WEB-INF/index.jsp").forward(rq, rs);
	}

}
