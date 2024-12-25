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
import model.Product;
import model.SoLuongDaBan;

/**
 * Servlet implementation class Top10SanPhamBanChayControl
 */
@WebServlet(name = "Top10SanPhamControl", urlPatterns = {"/top10"})
public class Top10SanPhamBanChayControl extends HttpServlet {
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
        List<Product> listAllPro = dao.getAllProduct();
        List<SoLuongDaBan> listTop10Pro = dao.getTop10SanPhamBanChay();
 
        request.setAttribute("listAllProduct", listAllPro);
        request.setAttribute("listTop10Product", listTop10Pro);
        request.getRequestDispatcher("Top10SanPhamBanChay.jsp").forward(request, response);
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
