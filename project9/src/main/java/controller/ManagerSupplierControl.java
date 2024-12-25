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
import model.Supplier;

/**
 * Servlet implementation class ManagerSupplierControl
 */
@WebServlet(name = "ManagerSupplierControl", urlPatterns = {"/managerSupplier"})
public class ManagerSupplierControl extends HttpServlet {
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

        List<Supplier> listAllSupplier = dao.getAllSupplier();
        List<Category> listAllCategory = dao.getAllCategory();


        request.setAttribute("listAllSupplier", listAllSupplier);
        request.setAttribute("listAllCategory", listAllCategory);

        request.getRequestDispatcher("NhaPhanPhoi.jsp").forward(request, response);
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
