package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;
import model.Category;
import model.Product;

/**
 * Servlet implementation class CategoryControl
 */
@WebServlet(name="/category", urlPatterns = "/category")
public class CategoryControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String cateId = request.getParameter("cate_id");
		
		Dao dao = new Dao();
		List<Product> list = dao.getProductByCID(cateId);
		List<Category> listC = dao.getAllCategory();
		
		
		
		
		request.setAttribute("listPro", list);
		request.setAttribute("listCate", listC);
		request.getRequestDispatcher("shop.jsp").forward(request, response);
	}
	
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
