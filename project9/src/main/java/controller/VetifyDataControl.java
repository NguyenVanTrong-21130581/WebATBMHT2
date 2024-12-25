package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/signData")
public class VetifyDataControl extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy dữ liệu từ form
        String name = request.getParameter("name");
        String sdt = request.getParameter("sdt");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String content = request.getParameter("content");
        String key = request.getParameter("key");

        // Xử lý ký dữ liệu (chỉ demo, thực tế cần thêm mã hóa)
        String signedData = "Dữ liệu đã ký với key: " + key;

        // Gửi dữ liệu đã ký về client
        request.setAttribute("signedData", signedData);
        request.getRequestDispatcher("result.jsp").forward(request, response);
    }
}
