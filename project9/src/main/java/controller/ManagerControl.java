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
import model.Category;
import model.Product;

/**
 * Servlet implementation class ManagerControl
 */
@WebServlet(name = "ManagerControl", urlPatterns = {"/manager"})
public class ManagerControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		HttpSession session = request.getSession();
		Account acc = (Account) session.getAttribute("acc");
		if(acc == null ) {
			response.sendRedirect("login");
        	return;
		}else if(acc != null && acc.getIsSell() == 0) {
			response.sendRedirect("home");
        	return;
		}
		int id = acc.getId();
        String index = request.getParameter("index"); 
        if(index == null) {
        	index="1";
        }
        int indexPage = Integer.parseInt(index);
        
		Dao dao = new Dao();
		List<Product> listPro = dao.getProductBySellIDAndIndex(id, indexPage);
		List<Category> listCate = dao.getAllCategory();
		int allProductBySellID = dao.countAllProductBySellID(id);
        int endPage = allProductBySellID/5;
        if(allProductBySellID % 5 != 0) {
        	endPage++;
        }
		
        request.setAttribute("tag", indexPage);
        request.setAttribute("endPage", endPage);
		request.setAttribute("listPro", listPro);
		request.setAttribute("listCate", listCate);
		request.getRequestDispatcher("QuanLySanPham.jsp").forward(request, response);
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
	@Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
