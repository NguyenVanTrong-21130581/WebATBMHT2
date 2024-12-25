package controller;


import java.io.*;
import java.security.*;
import java.util.Base64;
import java.util.Random;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import dao.Dao;
import model.Account;
import model.Email;
import model.EmailUtils;

@WebServlet(name = "VerifyOTPServlet", urlPatterns = { "/verifyOTP" })
public class VerifyOTPServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            String enteredOTP = request.getParameter("otp");  // Mã OTP người dùng nhập vào
            String correctOTP = (String) session.getAttribute("otp");  // Mã OTP đã lưu trong session

            if (correctOTP != null && correctOTP.equals(enteredOTP)) {
                // Mã OTP đúng, tạo cặp khóa
                generateAndSendKeyPair(request, response);
            } else {
                // Mã OTP sai
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("{\"status\":\"error\",\"message\":\"Invalid OTP!\"}");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"status\":\"error\",\"message\":\"Error verifying OTP\"}");
        }
    }

    // Hàm tạo và gửi cặp khóa
    private void generateAndSendKeyPair(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            HttpSession session = request.getSession();
            Account acc = (Account) session.getAttribute("acc");

            // Tạo cặp khóa RSA
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
            keyPairGen.initialize(2048);  // Độ dài khóa 2048 bits
            KeyPair keyPair = keyPairGen.generateKeyPair();
            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();

            String publicKeyBase64 = Base64.getEncoder().encodeToString(publicKey.getEncoded());
            String privateKeyBase64 = Base64.getEncoder().encodeToString(privateKey.getEncoded());

            int accountID = acc.getId();
            Dao dao = new Dao();
            dao.savePublicKeyToDatabase(accountID, publicKeyBase64);

            // Lưu privateKey vào session để gửi tải về sau
            session.setAttribute("privateKeyBase64", privateKeyBase64);

            // Phản hồi thành công
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"status\":\"success\",\"message\":\"Keys generated successfully!\"}");

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"status\":\"error\",\"message\":\"Error generating keys\"}");
        }
    }
}
