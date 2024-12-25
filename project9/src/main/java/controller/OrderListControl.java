package controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import dao.Dao;
import model.Account;
import model.Order;

@WebServlet(name = "OrderListControl", urlPatterns = {"/orders"})
public class OrderListControl extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public OrderListControl() {
        super();
    }

    // Xử lý yêu cầu GET để hiển thị danh sách đơn hàng
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("acc");

        if (acc == null) {
            response.sendRedirect("login");
            return;
        }

        int accountId = acc.getId();
        Dao dao = new Dao();
        List<Order> orders;
		try {
			orders = dao.getOrderByAccountId(accountId);
			 request.setAttribute("orders", orders);
		        request.getRequestDispatcher("OrderByID.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

       
    }

    // Xử lý yêu cầu POST để ký xác nhận đơn hàng
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }
}
