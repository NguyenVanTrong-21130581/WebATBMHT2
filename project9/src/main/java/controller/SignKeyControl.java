package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/signControl")
public class SignKeyControl extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Lấy dữ liệu từ form
		String name = request.getParameter("name");
		String sdt = request.getParameter("sdt");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String content = request.getParameter("content");

		// Lưu dữ liệu vào request để hiển thị lại
		request.setAttribute("name", name);
		request.setAttribute("sdt", sdt);
		request.setAttribute("email", email);
		request.setAttribute("address", address);
		request.setAttribute("content", content);

		// Chuyển đến trang nhập key
		request.getRequestDispatcher("enterKey.jsp").forward(request, response);
	}

}
