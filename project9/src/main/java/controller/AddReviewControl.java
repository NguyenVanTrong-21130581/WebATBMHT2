package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Dao;
import model.Account;
import model.Review;

/**
 * Servlet implementation class AddReviewControl
 */
@WebServlet(name = "AddReviewControl", urlPatterns = {"/addReview"})
public class AddReviewControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        response.setContentType("text/html;charset=UTF-8");
	        HttpSession session = request.getSession();
	        Account acc = (Account) session.getAttribute("acc");
	        if(acc == null) {
	        	response.sendRedirect("login");
	        	return;
	        }
	        int accountID = acc.getId();
	        String contentReview = request.getParameter("contentReview");
	        int productID = Integer.parseInt(request.getParameter("pid"));
	        
	        Dao dao = new Dao();
	        dao.insertReview(accountID, productID, contentReview);
	        
	        Review newReview = new Review();
	        newReview = dao.getNewReview(accountID, productID);
	        
//	        PrintWriter out = response.getWriter();
//
//	        
//            out.println(" <div class=\"media mt-3 mb-4\">\r\n"
//            		+ "              <img class=\"d-flex mr-3 z-depth-1\" src=\"https://mdbootstrap.com/img/Photos/Others/placeholder1.jpg\"\r\n"
//            		+ "                width=\"62\" alt=\"Generic placeholder image\">\r\n"
//            		+ "              <div class=\"media-body\">\r\n"
//            		+ "                <div class=\"d-flex justify-content-between\">\r\n"
//            		+ "                  <p class=\"mt-1 mb-2\">\r\n"
//            		+ "                    <strong>"+acc.getUser()+"</strong>\r\n"
//            		+ "                    <span>– </span><span>"+newReview.getDateReview()+"</span>\r\n"
//            		+ "                  </p>\r\n"
//            		+ "                </div>\r\n"
//            		+ "                <p class=\"mb-0\">"+newReview.getContentReview()+"</p>\r\n"
//            		+ "              </div>\r\n"
//            		+ "            </div>\r\n"
//            		+ "            <hr>    ");
//	
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
