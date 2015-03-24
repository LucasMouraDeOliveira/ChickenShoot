package fr.lordkadoc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.lordkadoc.bdd.ConnexionBDD;

@WebServlet(name="ServletConnexion", urlPatterns = { "/connect" })
public class ServletConnexion extends ServletBasique{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doPost(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
		String login = rq.getParameter("login");
		String password = rq.getParameter("password");
		if(ConnexionBDD.connect(login, password)){
			rq.getSession(true).setAttribute("connecte", "true");
			rq.getSession(true).setAttribute("login", login);
			rq.getRequestDispatcher("WEB-INF/index.jsp").forward(rq, rs);
		}else{
			rq.getRequestDispatcher("WEB-INF/index.jsp").forward(rq, rs);
		}
	}

}
