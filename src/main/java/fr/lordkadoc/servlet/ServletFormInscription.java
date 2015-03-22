package fr.lordkadoc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

@WebServlet(name="ServetFormInscription", urlPatterns = { "/formInscription" })
public class ServletFormInscription extends WebSocketServlet{

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
		rq.getSession().setAttribute("page", "inscription");
		rq.getRequestDispatcher("WEB-INF/index.jsp").forward(rq, rs);
	}

	@Override
	public void configure(WebSocketServletFactory arg0) {
		//Nothing TODO
	}
	

}
