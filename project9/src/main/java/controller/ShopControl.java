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
import model.Category;
import model.Product;

@WebServlet(name = "ShopControl", urlPatterns = {"/shop"})
public class ShopControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		// get data from Dao
	
	        Dao dao = new Dao();
//	        List<Product> list = dao.getAllProduct();
	        List<Category> listC = dao.getAllCategory();
	        int count200 = dao.countNhoHon200();
	       
	        
	        String index = request.getParameter("index");
	        if(index == null) {
	        	index="1";
	        }
	        int indexPage = Integer.parseInt(index);
	        
	      
	        List<Product> list = dao.getProductByIndex(indexPage);
//	        List<Category> listC = dao.getAllCategory();
	        int allProduct = dao.countAllProduct();
	        int endPage = allProduct/9;
	        if(allProduct % 9 != 0) {
	        	endPage++;
	        }		
	        request.setAttribute("tag", indexPage);
	        request.setAttribute("endPage", endPage);
	        request.setAttribute("listCate", listC);
	        request.setAttribute("listPro", list);
	        request.setAttribute("countNhoHon200", count200);
	            // set data to jsp
	        request.getRequestDispatcher("shop.jsp").forward(request, response);    
	        
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}
	@Override
    public String getServletInfo() {
        return "Short description";
    }

}
