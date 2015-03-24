package fr.lordkadoc.launcher;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;


@WebServlet(name="ChickenShootServlet", urlPatterns = { "/websocket"})
public class ChickenShootServlet extends WebSocketServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	public void doGet(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
		rs.sendRedirect("index.html");
	}
	
	@Override
	public void configure(WebSocketServletFactory factory) {
		//factory.getPolicy().setIdleTimeout(15000);
		factory.register(EndPoint.class);
	}
}