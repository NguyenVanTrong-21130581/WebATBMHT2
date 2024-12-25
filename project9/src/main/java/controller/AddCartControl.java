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
import model.Cart;

/**
 * Servlet implementation class AddCartControl
 */
@WebServlet(name = "AddCartControl", urlPatterns = { "/addCart" })
public class AddCartControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		int productId = Integer.parseInt(request.getParameter("pid"));
		HttpSession session = request.getSession();
		Account acc = (Account) session.getAttribute("acc");
		if(acc == null) {
			response.sendRedirect("login");
			return;
			
		}
		int accountId = acc.getId();
		int amount = Integer.parseInt(request.getParameter("quantity"));
		String size = request.getParameter("size");
		
		Dao dao = new Dao();
		Cart cartEx = dao.checkCartExist(accountId, productId);
		int amountExisted;
		String sizeExisted;
		if(cartEx != null) {
			amountExisted = cartEx.getAmount();
			dao.editAmountAndSizeCart(accountId, productId, (amountExisted + amount), size);
			request.setAttribute("mess", "Da tang so luong san pham");
			request.getRequestDispatcher("managerCart").forward(request, response);
		}else {
			dao.insertCart(accountId, productId, amount, size);
			request.setAttribute("mess", "da them thanh cong");
			request.getRequestDispatcher("managerCart").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

}
