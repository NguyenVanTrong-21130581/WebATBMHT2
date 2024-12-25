package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Dao;
import model.Account;


/**
 * Servlet implementation class DoanhThuTheoThangControl
 */
@WebServlet(name = "DoanhThuTheoThangControl", urlPatterns = {"/doanhThuTheoThang"})
public class DoanhThuTheoThangControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        response.setContentType("text/html;charset=UTF-8");
	        request.setCharacterEncoding("UTF-8");
	        
	        HttpSession session = request.getSession();
			Account acc = (Account) session.getAttribute("acc");
			if(acc == null ) {
				response.sendRedirect("login");
	        	return;
			}else if(acc != null && acc.getIsSell() == 0 && acc.getIsAdmin() == 0) {
				response.sendRedirect("home");
	        	return;
			}
	        
	        Dao dao = new Dao();
	        double totalMoneyMonth1 = dao.totalMoneyMonth(1);
	        double totalMoneyMonth2 = dao.totalMoneyMonth(2);
	        double totalMoneyMonth3 = dao.totalMoneyMonth(3);
	        double totalMoneyMonth4 = dao.totalMoneyMonth(4);
	        double totalMoneyMonth5 = dao.totalMoneyMonth(5);
	        double totalMoneyMonth6 = dao.totalMoneyMonth(6);
	        double totalMoneyMonth7 = dao.totalMoneyMonth(7);
	        double totalMoneyMonth8 = dao.totalMoneyMonth(8);
	        double totalMoneyMonth9 = dao.totalMoneyMonth(9);
	        double totalMoneyMonth10 = dao.totalMoneyMonth(10);
	        double totalMoneyMonth11 = dao.totalMoneyMonth(11);
	        double totalMoneyMonth12 = dao.totalMoneyMonth(12);
	        

	        
	        request.setAttribute("totalMoneyMonth1", totalMoneyMonth1);
	        request.setAttribute("totalMoneyMonth2", totalMoneyMonth2);
	        request.setAttribute("totalMoneyMonth3", totalMoneyMonth3);
	        request.setAttribute("totalMoneyMonth4", totalMoneyMonth4);
	        request.setAttribute("totalMoneyMonth5", totalMoneyMonth5);
	        request.setAttribute("totalMoneyMonth6", totalMoneyMonth6);
	        request.setAttribute("totalMoneyMonth7", totalMoneyMonth7);
	        request.setAttribute("totalMoneyMonth8", totalMoneyMonth8);
	        request.setAttribute("totalMoneyMonth9", totalMoneyMonth9);
	        request.setAttribute("totalMoneyMonth10", totalMoneyMonth10);
	        request.setAttribute("totalMoneyMonth11", totalMoneyMonth11);
	        request.setAttribute("totalMoneyMonth12", totalMoneyMonth12);
	        
	       
	    
	        request.getRequestDispatcher("DoanhThuTheoThang.jsp").forward(request, response);
	       
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
