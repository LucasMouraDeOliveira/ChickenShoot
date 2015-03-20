package fr.lordkadoc.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

import fr.lordkadoc.bdd.InscriptionBDD;


@WebServlet(name="ServetInscription", urlPatterns = { "/inscription" })
public class ServletInscription extends WebSocketServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2339299883684631581L;
	
	@Override
	public void doGet(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
		rs.sendRedirect("index.html");
	}
	
	@Override
	public void doPost(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
		PrintWriter out = rs.getWriter();
		String login = rq.getParameter("login");
		String password = rq.getParameter("password");
		String cpassword = rq.getParameter("cpassword");
		String mail = rq.getParameter("mail");
		
		if(InscriptionBDD.inscription(login, password, cpassword, mail)){
			rs.sendRedirect("index.html");
		}else{
			out.println("Erreur de remplissage du formulaire");
		}
	}

	@Override
	public void configure(WebSocketServletFactory arg0) {
		//Nothing TODO
	}
	

}
