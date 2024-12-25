package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Dao;
import model.Account;

/**
 * Servlet implementation class LoginControl
 */
@WebServlet(name = "LoginControl", urlPatterns = {"/login"})
public class LoginControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
//	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.setContentType("text/html;charset=UTF-8");
//		String username = request.getParameter("username");
//		String password = request.getParameter("password");
//		
//		Dao dao = new Dao();
//		Account acc = dao.login(username, password);
//		if(acc == null) {
//			//request.setAttribute("mess", "Tai khoan hoac mat khau sai");
//			request.setAttribute("error", "Sai username hoac password!");
//			request.getRequestDispatcher("dangNhap.jsp").forward(request, response);
//		}else {
//			HttpSession session = request.getSession();
//			session.setAttribute("acc", acc);
//			session.setMaxInactiveInterval(60*60*24);
//			response.sendRedirect("ShopControl");
//		}
//	}
	
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null; 
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Cookie arr[] = request.getCookies();
    	if(arr != null) {
    		for(Cookie o : arr) {
        		if(o.getName().equals("userC")) {
        			request.setAttribute("username", o.getValue());
        		}
        		if(o.getName().equals("passC")) {
        			request.setAttribute("password", o.getValue());
        		}
        	}
    	}
    	//b2: set user,pass to login form
        request.getRequestDispatcher("dangNhap.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 response.setContentType("text/html;charset=UTF-8");
         String username = request.getParameter("user");
         String password = request.getParameter("pass");
         String remember =request.getParameter("remember");
         
         Dao dao = new Dao();
         Account a = dao.login(username, password);
         
         if (a == null) {
             request.setAttribute("error", "Sai username hoac password!");
             request.getRequestDispatcher("dangNhap.jsp").forward(request, response);
         } else {
             HttpSession session = request.getSession();
             session.setAttribute("acc", a);
             session.setMaxInactiveInterval(60*60*24);
           //luu account len tren cookie
             Cookie u = new Cookie("userC", username);
             Cookie p = new Cookie("passC", password);
             if(remember != null) {
            	 p.setMaxAge(60*60*24);// 1 ngay
             }else {
            	 p.setMaxAge(0);
             }
             
             u.setMaxAge(60*60*24*365);//1 nam

             response.addCookie(u);//luu u va p len Chrome
             response.addCookie(p);
            
             response.sendRedirect("shop");
	}
	}
}
