package fr.lordkadoc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="ServletCreationPartie", urlPatterns = { "/creationPartie" })
public class ServletCreationPartie extends ServletBasique{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2091148739199114751L;
	

	@Override
	public void doPost(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
		String nomPartie = rq.getParameter("nomPartie");
		
		
		
		rq.setAttribute("page", "game");
		rq.getRequestDispatcher("WEB-INF/index.jsp").forward(rq, rs);
	}
}
