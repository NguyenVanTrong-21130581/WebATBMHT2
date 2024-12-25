package controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "DownloadPrivateKeyServlet", urlPatterns = { "/downloadPrivateKey" })
public class DownloadPrivateKeyServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy privateKeyBase64 từ session
        String privateKeyBase64 = (String) request.getSession().getAttribute("privateKeyBase64");

        if (privateKeyBase64 == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "No private key available.");
            return;
        }

        // Tạo file để lưu privateKey
        byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyBase64);

        // Đặt header để file có thể được tải về
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=private_key.pem");

        // Sử dụng getOutputStream để gửi dữ liệu nhị phân
        OutputStream out = response.getOutputStream();
        out.write(privateKeyBytes);
        out.flush();
        out.close();  // Đảm bảo đóng luồng xuất sau khi gửi xong dữ liệu
    }
}
