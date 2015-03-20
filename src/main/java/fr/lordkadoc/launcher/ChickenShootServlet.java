package fr.lordkadoc.launcher;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

import fr.lordkadoc.bdd.ConnexionBDD;

@WebServlet(name="ChickenShootServlet", urlPatterns = { "/chickenShoot/websocket"})
public class ChickenShootServlet extends WebSocketServlet {
	
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
		out.println("<p>Login : " + login + "</p>");
		out.println("<p>Mot de passe : " + password + "</p>");
		if(ConnexionBDD.connect(login, password)){
			rs.sendRedirect("../indexHome.html");
		}else{
			out.println("Mauvais couple login/mot de passe");
		}
	}
	
	@Override
	public void configure(WebSocketServletFactory factory) {
		factory.getPolicy().setIdleTimeout(15000);
		factory.register(EndPoint.class);
	}
}