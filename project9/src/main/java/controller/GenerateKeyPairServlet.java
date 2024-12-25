package controller;

import java.io.*;
import java.security.*;
import java.util.Base64;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import dao.Dao;
import model.Account;

@WebServlet(name = "GenerateKeyPairServlet", urlPatterns = { "/generateKeyPair" })
public class GenerateKeyPairServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			Account acc = (Account) session.getAttribute("acc");
			if (acc == null) {
				response.sendRedirect("login");
				return;
			}

			// Tạo cặp khóa RSA với độ dài khóa 2048 bits
			KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
			keyPairGen.initialize(2048); // Độ dài khóa: 2048 bits

			KeyPair keyPair = keyPairGen.generateKeyPair();
			PublicKey publicKey = keyPair.getPublic();
			PrivateKey privateKey = keyPair.getPrivate();

			// Chuyển publicKey và privateKey sang Base64 để dễ lưu trữ và gửi cho người
			// dùng
			String publicKeyBase64 = Base64.getEncoder().encodeToString(publicKey.getEncoded());
			String privateKeyBase64 = Base64.getEncoder().encodeToString(privateKey.getEncoded());
			System.out.println(privateKeyBase64);
			int accountID = acc.getId();
			Dao dao = new Dao();
			// Lưu publicKey vào database
			dao.savePublicKeyToDatabase(accountID, publicKeyBase64);

			// Lưu privateKey vào session để sử dụng ở servlet tải file
			session.setAttribute("privateKeyBase64", privateKeyBase64);

			// Phản hồi JSON thông báo thành công
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter()
					.write("{\"status\":\"success\",\"message\":\"Keys generated and saved successfully!\"}");
			response.getWriter().flush(); // Đảm bảo gửi dữ liệu JSON ngay lập tức

		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.getWriter().write("{\"status\":\"error\",\"message\":\"Error generating keys\"}");
		}
	}
}
