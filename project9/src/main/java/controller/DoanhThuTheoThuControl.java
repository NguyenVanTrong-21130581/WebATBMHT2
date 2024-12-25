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
 * Servlet implementation class DoanhThuTheoThuControl
 */
@WebServlet(name = "DoanhThuTheoThuControl", urlPatterns = {"/doanhThuTheoThu"})
public class DoanhThuTheoThuControl extends HttpServlet {
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
	        double totalMoney1 = dao.totalMoneyDay(1);
	        double totalMoney2 = dao.totalMoneyDay(2);
	        double totalMoney3 = dao.totalMoneyDay(3);
	        double totalMoney4 = dao.totalMoneyDay(4);
	        double totalMoney5 = dao.totalMoneyDay(5);
	        double totalMoney6 = dao.totalMoneyDay(6);
	        double totalMoney7 = dao.totalMoneyDay(7);
	        

	        request.setAttribute("totalMoney1", totalMoney1);
	        request.setAttribute("totalMoney2", totalMoney2);
	        request.setAttribute("totalMoney3", totalMoney3);
	        request.setAttribute("totalMoney4", totalMoney4);
	        request.setAttribute("totalMoney5", totalMoney5);
	        request.setAttribute("totalMoney6", totalMoney6);
	        request.setAttribute("totalMoney7", totalMoney7);
	        
	        request.getRequestDispatcher("DoanhThuTheoThu.jsp").forward(request, response);
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
