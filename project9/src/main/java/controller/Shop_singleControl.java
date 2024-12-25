package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;
import model.Account;
import model.Review;
import model.Product;

/**
 * Servlet implementation class Shop_singleControl
 */
@WebServlet(name = "Shop_singleControl", urlPatterns = { "/detail" })
public class Shop_singleControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String id = request.getParameter("pid");
		Dao dao = new Dao();
		Product p = dao.getProductByID(id);

		int cateIDProductDetail = dao.getCateIDByProductID(id);
		List<Product> listRelatedProduct = dao.getRelatedProduct(cateIDProductDetail);

		List<Review> listAllReview = dao.getAllReviewByProductID(id);
		int countAllReview = listAllReview.size();

		List<Account> listAllAcount = dao.getAllAccount();

		Product last = dao.getLast();
		
		

		request.setAttribute("detail", p);
		request.setAttribute("listRelatedProduct", listRelatedProduct);
        request.setAttribute("listAllReview", listAllReview);
        request.setAttribute("listAllAcount", listAllAcount);
        request.setAttribute("countAllReview", countAllReview);
        request.setAttribute("p", last);
		request.getRequestDispatcher("shop-single.jsp").forward(request, response);
	}

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
	@Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
