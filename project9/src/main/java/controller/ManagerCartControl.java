package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Dao;
import model.Account;
import model.Cart;
import model.Product;

/**
 * Servlet implementation class ManagerCartControl
 */
@WebServlet(name = "ManagerCartControl", urlPatterns = { "/managerCart" })
public class ManagerCartControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		Account acc = (Account) session.getAttribute("acc");
		if (acc == null) {
			response.sendRedirect("login");
			return;

		}
		int accountId = acc.getId();
		Dao dao = new Dao();
		List<Cart> listCart = dao.getCartByAccountID(accountId);
		List<Product> listPro = dao.getAllProduct();

		
		request.setAttribute("listCart", listCart);
		request.setAttribute("listPro", listPro);
		
		
		int totalAmount = dao.SumAllAmountByAccountID(accountId);
		System.out.println(accountId);
		System.out.println(totalAmount);
		double totalMoney = 0;
		for (Cart cart : listCart) {
			for (Product product : listPro) {
				if (cart.getProductID() == product.getId()) {
					totalMoney = totalMoney + (product.getPrice() * cart.getAmount());
					
				}
			}
		}
		

       System.out.println(totalAmount);
        request.setAttribute("totalMoney", totalMoney);
        request.setAttribute("totalAmount", totalAmount);
		request.getRequestDispatcher("cart.jsp").forward(request, response);

//       
//        
//       
//        PrintWriter out = response.getWriter();
//        		out.println(" <li class=\"d-flex justify-content-between py-3 border-bottom\"><strong class=\"text-muted\">Tổng tiền hàng</strong><strong>"+totalMoney+"</strong></li>\r\n"
//        				+ "                                        <li class=\"d-flex justify-content-between py-3 border-bottom\"><strong class=\"text-muted\">Phí vận chuyển</strong><strong>Free ship</strong></li>\r\n"
//        				+ "                                        <li class=\"d-flex justify-content-between py-3 border-bottom\"><strong class=\"text-muted\">VAT</strong><strong>10 %</strong></li>\r\n"
//        				+ "                                        <li class=\"d-flex justify-content-between py-3 border-bottom\"><strong class=\"text-muted\">Tổng thanh toán</strong>\r\n"
//        				+ "                                            <h5 class=\"font-weight-bold\">"+totalMoneyVAT+"</h5>\r\n"
//        				+ "                                        </li>");
//        	
//       
//       
//        		
//        	
//         
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
