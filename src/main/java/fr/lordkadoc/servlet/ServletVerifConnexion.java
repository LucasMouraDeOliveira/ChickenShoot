package fr.lordkadoc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="ServletVerifConnexion", urlPatterns = { "/game" })
public class ServletVerifConnexion extends ServletBasique{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public void doPost(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
		if(rq.getSession(true).getAttribute("connecte") == "true"){
			rq.setAttribute("page",rq.getParameter("lien"));
		}else{
			rq.setAttribute("page","acceuil");
		}
		rq.getRequestDispatcher("WEB-INF/index.jsp").forward(rq, rs);		
	}
	
}