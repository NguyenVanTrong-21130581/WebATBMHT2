package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;
import model.Product;

/**
 * Servlet implementation class SearchNhoHon200Control
 */
@WebServlet(name = "SearchTren200Control", urlPatterns = {"/searchByTren200"})
public class SearchByTren200 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        response.setContentType("text/html;charset=UTF-8");
	        request.setCharacterEncoding("UTF-8");
	        Dao dao = new Dao();
	        
	        List<Product> listPro = dao.searchNhoHon200();
	        PrintWriter out = response.getWriter();
	        for (Product product : listPro) {
				out.println("<div class=\"row mb-5\">\r\n"
												
						+ "								<div class=\"col-sm-6 col-lg-4 mb-4\" data-aos=\"fade-up\">\r\n"
						+ "									<div class=\"block-4 text-center border\">\r\n"
						+ "										<figure class=\"block-4-image\">\r\n"
						+ "											<a href=\"shop-single?pid=${product.getId() }\"><img\r\n"
						+ "												src=\""+product.getImage() +"\" alt=\"Image placeholder\"\r\n"
						+ "												class=\"img-fluid\"></a>\r\n"
						+ "										</figure>\r\n"
						+ "										<div class=\"block-4-text p-4\">\r\n"
						+ "											<h3>\r\n"
						+ "												<a href=\"detail?pid="+product.getId()+"\">"+product.getName() +"</a>\r\n"
						+ "											</h3>\r\n"
						+ "											<p class=\"mb-0\">"+ product.getTitle() +"</p>\r\n"
						+ "											<p class=\"text-primary font-weight-bold\">" +product.getPrice() +"$</p>\r\n"
						+ "										</div>\r\n"
						+ "									</div>\r\n"
						+ "								</div>\r\n"
											
						+ "\r\n"
						+ "\r\n"
						+ "\r\n"
						+ "\r\n"
						+ "\r\n"
						+ "\r\n"
						+ "						</div>");
			}
	        
	        
	       
	       
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
