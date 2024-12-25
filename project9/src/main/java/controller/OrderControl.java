package controller;

import java.io.IOException;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import dao.Dao;
import model.*;

@WebServlet(name = "OrderControl", urlPatterns = {"/order"})
public class OrderControl extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public OrderControl() {
        super();
    }

    // doGet: Xử lý yêu cầu GET cho trang đặt hàng
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("acc");

        if (acc == null) {
            response.sendRedirect("login");
            return;
        }

        int accountID = acc.getId();
        Dao dao = new Dao();
        List<Cart> listCart = dao.getCartByAccountID(accountID);
        List<Product> listPro = dao.getAllProduct();
        String email = dao.getEmail(accountID);

        double totalMoney = calculateTotalMoney(listCart, listPro);
        dao.insertInvoice(accountID, totalMoney);

        request.setAttribute("email", email);
        request.getRequestDispatcher("datHang.jsp").forward(request, response);
    }

 // doPost: Xử lý yêu cầu POST để đặt hàng
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String name = request.getParameter("name");
            String sdt = request.getParameter("sdt");
            String address = request.getParameter("address");
            String content = request.getParameter("content");
            String key = request.getParameter("key");
            
            HttpSession session = request.getSession();
            Account acc = (Account) session.getAttribute("acc");
            if (acc == null) {
                response.sendRedirect("login");
                return;
            }
            
            int accountID = acc.getId();
            Dao dao = new Dao();
            List<Cart> list = dao.getCartByAccountID(accountID);
            List<Product> list2 = dao.getAllProduct();
            String email = dao.getEmail(accountID);
            String publicKey = dao.getPublicKey(accountID);

            double totalMoney = calculateTotalMoney(list, list2);
            String dataToSign = name + sdt + address + content + totalMoney;
            String hashDataOrder = hashOrder(dataToSign);

            String signedDataOrder = null; // Chữ ký sẽ là null nếu không thành công
            boolean isSigned = false;     // Biến xác định trạng thái ký

            if (key != null && !key.isEmpty()) { 
                try {
                    PrivateKey pvKey = base64ToPrivateKey(key);
                    PublicKey plKey = base64ToPublicKey(publicKey);
                    signedDataOrder = signOrder(hashDataOrder, pvKey);

                    // Kiểm tra chữ ký
                    if (verifySignature(hashDataOrder, signedDataOrder, plKey)) {
                        isSigned = true;
                    }
                } catch (Exception e) {
                    System.err.println("Lỗi khi xử lý chữ ký: " + e.getMessage());
                }
            }

            // Lưu đơn hàng với trạng thái tùy thuộc vào việc ký hay không
            String status = isSigned ? "signed" : "unsigned";
            dao.saveOrder(accountID, name, sdt, email, address, content, totalMoney, signedDataOrder, status);

            // Gửi email xác nhận đơn hàng
            sendOrderEmail(email, name, address, sdt, content, list, list2, totalMoney);

            // Xóa giỏ hàng sau khi đặt hàng
            dao.deleteCartByAccountID(accountID);

            if (isSigned) {
                request.setAttribute("mess", "Đặt hàng thành công và chữ ký đã được xác thực!");
            } else {
                request.setAttribute("mess", "Đặt hàng thành công! (Chưa ký)");
            }

            request.setAttribute("email", email);
            request.getRequestDispatcher("thankyou.jsp").forward(request, response);

        } catch (Exception e) {
            request.setAttribute("error", "Đặt hàng thất bại");
            e.printStackTrace();
            request.getRequestDispatcher("datHang.jsp").forward(request, response);
        }
    }


    // Tính tổng tiền từ giỏ hàng
    private double calculateTotalMoney(List<Cart> cart, List<Product> products) {
        double totalMoney = 0;
        for (Cart c : cart) {
            for (Product p : products) {
                if (c.getProductID() == p.getId()) {
                    totalMoney += p.getPrice() * c.getAmount();
                }
            }
        }
        return totalMoney;
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

    // Gửi email thông báo đơn hàng thành công
    private void sendOrderEmail(String email, String name, String address, String sdt, String content, List<Cart> list, List<Product> list2, double totalMoney) throws Exception {
        Email e = new Email();
        e.setFrom("trong221119@gmail.com");
        e.setFromPassword("ucmn odtw ewpb exki");
        e.setTo(email);
        e.setSubject("Đặt hàng thành công");

        StringBuilder sb = new StringBuilder();
        sb.append("Dear ").append(name).append("<br>");
        sb.append("Bạn vừa đặt hàng. <br>");
        sb.append("Địa chỉ nhận hàng: <b>").append(address).append("</b><br>");
        sb.append("Số điện thoại: <b>").append(sdt).append("</b><br>");
        sb.append("Yêu cầu của bạn: ").append(content).append("<br>");
        sb.append("Các sản phẩm bạn đã đặt: <br>");
        for (Cart c : list) {
            for (Product p : list2) {
                if (c.getProductID() == p.getId()) {
                    sb.append(p.getName()).append(" | Giá: ").append(p.getPrice()).append("$ | Số lượng: ").append(c.getAmount()).append(" | Size: ").append(c.getSize()).append("<br>");
                }
            }
        }
        sb.append("Tổng tiền: ").append(String.format("%.02f", totalMoney)).append("$").append("<br>");
        sb.append("Cảm ơn bạn đã đặt hàng!<br>");
        sb.append("Chủ cửa hàng");

        e.setContent(sb.toString());
        EmailUtils.send(e);
    }
}
