package controller;

import java.io.IOException;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Dao;
import model.Account;
import model.Order;

// Đường dẫn Servlet (thay đổi nếu cần)
@WebServlet("/SignOrderControl")
public class SignOrderControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Nhận thông tin từ request
		try {
			int orderIdStr = Integer.parseInt(request.getParameter("orderId"));
			String key = request.getParameter("signature");
			if (key == null || key.trim().isEmpty()) {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Thông tin không hợp lệ");
				return;
			}
			HttpSession session = request.getSession();
			Account acc = (Account) session.getAttribute("acc");
			if (acc == null) {
				response.sendRedirect("login");
				return;
			}
			int accountID = acc.getId();
			Dao dao = new Dao();
			List<Order> listOrder = dao.getOrderByOrderId(orderIdStr);
			Order order = listOrder.get(0);
			String name = order.getName(); // Lấy tên
			String sdt = order.getPhoneNumber(); // Lấy số điện thoại
			String address = order.getAddress(); // Lấy địa chỉ
			String content = order.getContent();
			double totalMoney = order.getTotalMoney();
			String status = order.getStatus();
			String publicKey = dao.getPublicKey(accountID);
			
			String dataToSign = name + sdt + address + content + totalMoney;
			String hashDataOrder = hashOrder(dataToSign);
			
			String signedDataOrder = null; // Chữ ký sẽ là null nếu không thành công 
            
            if (key != null && !key.isEmpty() && status.equalsIgnoreCase("unsigned")) { 
                try {
                    PrivateKey pvKey = base64ToPrivateKey(key);
                    PublicKey plKey = base64ToPublicKey(publicKey);
                    signedDataOrder = signOrder(hashDataOrder, pvKey);

                    // Kiểm tra chữ ký
                    if (verifySignature(hashDataOrder, signedDataOrder, plKey)) {
                        status ="signed";
                        dao.setStatus(orderIdStr, status);
                        dao.saveSignature(orderIdStr, signedDataOrder);
                    }
                } catch (Exception e) {
                    System.err.println("Lỗi khi xử lý chữ ký: " + e.getMessage());
                }
            }else {
            	response.sendRedirect("shop");  // Chuyển hướng về trang home nếu key null
                return;
            }

            // Lưu đơn hàng với trạng thái tùy thuộc vào việc ký hay không
            
            
            
            request.getRequestDispatcher("thankyou.jsp").forward(request, response);
		} catch (Exception e) {
			request.setAttribute("error", "Đặt hàng thất bại");
			e.printStackTrace();
			request.getRequestDispatcher("orders").forward(request, response);
		}
//        try {
//            int orderId = Integer.parseInt(orderIdStr); // Chuyển đổi orderId thành số nguyên
//
//            // Gọi DAO để lưu chữ ký vào cơ sở dữ liệu
//            OrderDao orderDao = new OrderDao();
//            boolean isUpdated = orderDao.signOrder(orderId, signature);
//
//            if (isUpdated) {
//                // Thành công: Chuyển hướng lại danh sách đơn hàng hoặc hiển thị thông báo thành công
//                response.sendRedirect("orderList.jsp?status=success");
//            } else {
//                // Không thành công: Trả lỗi
//                response.sendRedirect("orderList.jsp?status=failed");
//            }
//        } catch (NumberFormatException e) {
//            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID không hợp lệ");
//        } catch (Exception e) {
//            e.printStackTrace();
//            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Đã xảy ra lỗi khi xử lý yêu cầu");
//        }
	}

	// Hàm hash dữ liệu đơn hàng
    private String hashOrder(String infoOrder) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(infoOrder.getBytes());
            return bytesToHex(encodedhash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Lỗi khi băm dữ liệu đơn hàng", e);
        }
    }

    // Hàm ký dữ liệu đơn hàng
    public static String signOrder(String hash, PrivateKey privateKey) {
        try {
            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initSign(privateKey);
            signature.update(hash.getBytes());
            byte[] signedData = signature.sign();
            return Base64.getEncoder().encodeToString(signedData);
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi ký đơn hàng", e);
        }
    }

    // Hàm kiểm tra chữ ký
    public static boolean verifySignature(String hash, String signature, PublicKey publicKey) {
        try {
            Signature sig = Signature.getInstance("SHA256withRSA");
            sig.initVerify(publicKey);
            sig.update(hash.getBytes());
            byte[] decodedSignature = Base64.getDecoder().decode(signature);
            return sig.verify(decodedSignature);
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi kiểm tra chữ ký", e);
        }
    }

    // Chuyển byte thành chuỗi hex
    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
    }

    // Chuyển chuỗi Base64 thành PublicKey
    public static PublicKey base64ToPublicKey(String publicKeyBase64) throws Exception {
        byte[] decoded = Base64.getDecoder().decode(publicKeyBase64);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(new X509EncodedKeySpec(decoded));
    }

    // Chuyển chuỗi Base64 thành PrivateKey
    public static PrivateKey base64ToPrivateKey(String privateKeyBase64) throws Exception {
        byte[] decoded = Base64.getDecoder().decode(privateKeyBase64);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(decoded));
    }
}
