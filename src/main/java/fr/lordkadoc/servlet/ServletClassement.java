package fr.lordkadoc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.lordkadoc.bdd.GetBDD;


@WebServlet(name="servletClassement", urlPatterns = {"classement"})
public class ServletClassement extends ServletBasique {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4005923610293111668L;

	@Override
	public void doPost(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
		rq.setAttribute("classement", GetBDD.getClassement());
		rq.setAttribute("page", "classement");		
		rq.getRequestDispatcher("WEB-INF/index.jsp").forward(rq, rs);
	}

}
