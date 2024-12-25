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
import model.TongChiTieuBanHang;

/**
 * Servlet implementation class Top5KhachHangControl
 */
@WebServlet(name = "Top5KhachHangControl", urlPatterns = {"/top5khachhang"})
public class Top5KhachHangControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	 protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        response.setContentType("text/html;charset=UTF-8");
	        

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
	        
	        List<Account> listAllAccount = dao.getAllAccount();
	        List<TongChiTieuBanHang> listTop5KhachHang = dao.getTop5KhachHang();


	        request.setAttribute("listAllAccount", listAllAccount);
	        request.setAttribute("listTop5KhachHang", listTop5KhachHang);
	        request.getRequestDispatcher("Top5KhachHang.jsp").forward(request, response);
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
