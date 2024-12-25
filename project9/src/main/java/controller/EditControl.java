package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;



/**
 * Servlet implementation class EditControl
 */
@WebServlet(name = "EditControl", urlPatterns = {"/edit"})
public class EditControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        int pid = Integer.parseInt(request.getParameter("id"));
        String pname = request.getParameter("name");
        String pimage = request.getParameter("image");
        
        String pimage2 = request.getParameter("image2");
        String pimage3 = request.getParameter("image3");
        String pimage4 = request.getParameter("image4");
        String pmodel = request.getParameter("model");
        String pcolor = request.getParameter("color");
        String pdelivery = request.getParameter("delivery");
        
        float pprice = Float.parseFloat(request.getParameter("price"));
        String ptitle = request.getParameter("title");
        String pdescription = request.getParameter("description");
        int pcategory = Integer.parseInt(request.getParameter("category"));
        Dao dao = new Dao();
        dao.editProduct(pname, pimage, pprice, ptitle, pdescription, pcategory, pmodel, pcolor, pdelivery, pimage2, pimage3, pimage4, pid);
       request.setAttribute("mess", "Edited!");
       request.getRequestDispatcher("manager").forward(request, response);
//        response.sendRedirect("manager");
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
