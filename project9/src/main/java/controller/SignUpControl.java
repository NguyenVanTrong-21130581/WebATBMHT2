package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;
import model.Account;

/**
 * Servlet implementation class SignUpControl
 */
@WebServlet(name = "SignUpControl", urlPatterns = {"/signup"})
public class SignUpControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String user = request.getParameter("user");
		String pass = request.getParameter("pass");
		String re_pass = request.getParameter("repass");
		String email = request.getParameter("email");
		if(!pass.equals(re_pass)) {
			request.setAttribute("error", "password khác re-pasword");
            request.getRequestDispatcher("dangKi.jsp").forward(request, response);
		}else {
			Dao dao = new Dao();
			Account acc = dao.checkAccountExist(user);
			if(acc == null) {
				// duoc sign up
				dao.signup(user, pass, email);
				response.sendRedirect("login");
			}else {
				response.sendRedirect("dangNhap.jsp");
			}
		}
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

}
