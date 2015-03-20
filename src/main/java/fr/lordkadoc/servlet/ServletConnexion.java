package fr.lordkadoc.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

import fr.lordkadoc.bdd.ConnexionBDD;

@WebServlet(name="ServletConnexion", urlPatterns = { "/connect" })
public class ServletConnexion extends WebSocketServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
		rs.sendRedirect("index.html");
	}
	
	@Override
	public void doPost(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
		PrintWriter out = rs.getWriter();
		String login = rq.getParameter("login");
		String password = rq.getParameter("password");
		if(ConnexionBDD.connect(login, password)){
			rs.sendRedirect("index.html");
		}else{
			out.println("Mauvais couple login/mot de passe");
		}
	}

	@Override
	public void configure(WebSocketServletFactory arg0) {
		//Nothing TODO
	}
}
