package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;

/**
 * Servlet implementation class AddAccountControl
 */
@WebServlet(name = "AddAccountControl", urlPatterns = {"/addAccount"})
public class AddAccountControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        response.setContentType("text/html;charset=UTF-8");
	        request.setCharacterEncoding("UTF-8");
	        String user = request.getParameter("user");
	        String pass = request.getParameter("pass");
	        String isSell = request.getParameter("isSell");
	        String isAdmin = request.getParameter("isAdmin");
	        String email = request.getParameter("email");
	        // Kiem tra isSell va isAdmin
	        if(isSell != null && isAdmin != null) {
	        	isSell = "1";isAdmin = "1";
	        }else if(isSell == null && isAdmin != null) {
	        	isSell = "0";isAdmin = "1";
	        }else if(isSell != null && isAdmin == null) {
	        	isSell = "1";isAdmin = "0";
	        }else {
	        	isSell = "0"; isAdmin = "0";
	        }
	        // Viet ham kiem tra Account 
	        Dao dao = new Dao();
	        dao.insertAccount(user, pass, isSell, isAdmin, email);
	        System.out.println(isAdmin);
	        System.out.println(isSell);
	        request.setAttribute("mess", "Tai khoan them thanh cong");
	        request.getRequestDispatcher("managerAccount").forward(request, response);
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
