package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;

/**
 * Servlet implementation class DeleteAccountControl
 */
@WebServlet(name = "DeleteAccountControl", urlPatterns = {"/deleteAccount"})
public class DeleteAccountControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String id = request.getParameter("id");
        System.out.println("id: " + id);
        Dao dao = new Dao();
        
        dao.deleteCartByAccountID(Integer.parseInt(id));
        dao.deleteProductBySellID(id);
        dao.deleteReviewByAccountID(id);
        dao.deleteInvoiceByAccountId(id);
        dao.deleteTongChiTieuBanHangByUserID(id);
        dao.deleteAccount(id);
        
        request.setAttribute("mess", "Account da duoc xoa");
        request.getRequestDispatcher("managerAccount").forward(request, response);
	
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
