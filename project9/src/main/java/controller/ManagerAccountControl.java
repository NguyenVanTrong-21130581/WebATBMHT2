package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Dao;
import model.Account;

/**
 * Servlet implementation class ManagerAccountControl
 */
@WebServlet(name = "ManagerAccountControl", urlPatterns = {"/managerAccount"})
public class ManagerAccountControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	 protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
		 response.setContentType("text/html;charset=UTF-8");
		 HttpSession session = request.getSession();
		 Account a = (Account) session.getAttribute("acc");
		 
		 if(a == null) {
			 response.sendRedirect("login");
			 return;
		 }else if(a != null && a.getIsAdmin() == 0) {
			 response.sendRedirect("home");
			 return;
		 }
		 Dao dao = new Dao();
		 
		 List<Account> listAcc = dao.getAllAccount();
		 
		 request.setAttribute("listAcc", listAcc);
		 request.getRequestDispatcher("QuanLyTaiKhoan.jsp").forward(request, response);
		 
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
	@Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
