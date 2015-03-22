package fr.lordkadoc.servlet;

import java.io.IOException;

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
		rq.getRequestDispatcher("WEB-INF/index.jsp").forward(rq, rs);
	}
	
	@Override
	public void doPost(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
		String login = rq.getParameter("login");
		String password = rq.getParameter("password");
		String cpassword = rq.getParameter("cpassword");
		String mail = rq.getParameter("mail");
		
		InscriptionBDD bdd = new InscriptionBDD();
		
		if(bdd.inscription(login, password, cpassword, mail)){
			rq.getSession().setAttribute("connecte", "true");
			rq.setAttribute("page", "acceuil");
			rq.getRequestDispatcher("WEB-INF/index.jsp").forward(rq, rs);
		}else{
			rq.setAttribute("login", login);
			rq.setAttribute("password", password);
			rq.setAttribute("cpassword", cpassword);
			rq.setAttribute("mail", mail);
			rq.setAttribute("message_erreur",bdd.getErreur());
			rq.getRequestDispatcher("WEB-INF/index.jsp").forward(rq, rs);
		}
	}

	@Override
	public void configure(WebSocketServletFactory arg0) {
		//Nothing TODO
	}
	

}
