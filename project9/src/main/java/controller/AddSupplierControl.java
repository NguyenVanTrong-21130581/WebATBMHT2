package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;



/**
 * Servlet implementation class AddSupplierControl
 */
@WebServlet(name = "AddSupplierControl", urlPatterns = {"/addSupplier"})
public class AddSupplierControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        response.setContentType("text/html;charset=UTF-8");
	        request.setCharacterEncoding("UTF-8");

	        String nameSupplier = request.getParameter("nameSupplier");
	        String phoneSupplier = request.getParameter("phoneSupplier");
	        String emailSupplier = request.getParameter("emailSupplier");
	        String addressSupplier = request.getParameter("addressSupplier");
	        int cateID = Integer.parseInt(request.getParameter("cateID")) ;
	        System.out.println(nameSupplier);
	        Dao dao = new Dao();
	        dao.insertSupplier(nameSupplier, phoneSupplier, emailSupplier, addressSupplier, cateID);
	        request.setAttribute("mess", "Supplier Added!");
	        request.getRequestDispatcher("managerSupplier").forward(request, response);
	   
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
