/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import context.DBcontext;
import model.Account;
import model.Cart;
import model.Review;
import model.SoLuongDaBan;
import model.TongChiTieuBanHang;
import model.Supplier;
//import entity.Account;
import model.Category;
import model.Invoice;
import model.Order;
import model.Product;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Dao {

	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	public List<Product> getAllProduct() {
		List<Product> list = new ArrayList<>();
		String query = "select * from Product";
		try {
			conn = new DBcontext().createConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
						rs.getString(6), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12),
						rs.getString(13), rs.getString(14)));
			}
		} catch (Exception e) {
		}
		return list;
	}
// Lay thon tin don hang theo OrderId
	public List<Order> getOrderByOrderId(int orderId) {
		List<Order> list = new ArrayList<>();
		String query = "Select * from `Order` where order_id = ?";
		try {
			conn = new DBcontext().createConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setInt(1, orderId);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Order(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getDouble(8), rs.getString(9), rs.getString(10),
						rs.getTimestamp(11)));
			}
		} catch (Exception e) {
		}
		return list;
	}
	// save trang thai cua don hang
	public void setStatus(int orderId, String status) throws Exception {
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    try {
	        conn = new DBcontext().createConnection(); // Kết nối đến database

	        // Cập nhật trạng thái đơn hàng
	        String updateStatusSQL = "UPDATE `Order` SET status = ? WHERE order_id = ?";
	        stmt = conn.prepareStatement(updateStatusSQL);
	        stmt.setString(1, status); // Cập nhật giá trị trạng thái
	        stmt.setInt(2, orderId);   // Cập nhật theo order_id

	        stmt.executeUpdate(); // Thực thi câu lệnh

	    } finally {
	        if (stmt != null)
	            stmt.close();
	        if (conn != null)
	            conn.close();
	    }
	}
// save chu ki
	public void saveSignature(int orderId, String signature) throws Exception {
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    try {
	        conn = new DBcontext().createConnection(); // Kết nối đến database

	        // Cập nhật chữ ký vào đơn hàng
	        String updateSignatureSQL = "UPDATE `Order` SET signature = ? WHERE order_id = ?";
	        stmt = conn.prepareStatement(updateSignatureSQL);
	        stmt.setString(1, signature); // Cập nhật chữ ký
	        stmt.setInt(2, orderId);       // Cập nhật theo order_id

	        stmt.executeUpdate(); // Thực thi câu lệnh

	    } finally {
	        if (stmt != null)
	            stmt.close();
	        if (conn != null)
	            conn.close();
	    }
	}

// Save Order
	public void saveOrder(int accountId, String name, String phoneNumber, String email, String address, String content,
			double totalMoney, String signature, String status) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = new DBcontext().createConnection(); // Kết nối database
// 2. Thêm đơn hàng mới vào database
			String insertNewOrderSQL = "INSERT INTO `Order` (account_id, name, phone_number, email, address, content, total_money, signature, status) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			stmt = conn.prepareStatement(insertNewOrderSQL);
			stmt.setInt(1, accountId); // ID tài khoản người dùng
			stmt.setString(2, name); // Tên khách hàng
			stmt.setString(3, phoneNumber); // Số điện thoại
			stmt.setString(4, email); // Email
			stmt.setString(5, address); // Địa chỉ
			stmt.setString(6, content); // Nội dung đơn hàng
			stmt.setDouble(7, totalMoney); // Tổng tiền
			stmt.setString(8, signature); // Chữ ký số
			stmt.setString(9, status);
			stmt.executeUpdate();

		} finally {
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		}
	}
// Lay thong tin hóa đơn theo id acc
	// Get Orders by Account ID
	public List<Order> getOrderByAccountId(int accountId) throws Exception {
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    List<Order> orders = new ArrayList<>();

	    try {
	        conn = new DBcontext().createConnection(); // Kết nối database
	        // Truy vấn danh sách đơn hàng theo ID tài khoản
	        String sql = "SELECT * FROM `Order` WHERE account_id = ?";
	        stmt = conn.prepareStatement(sql);
	        stmt.setInt(1, accountId); // Gán giá trị ID tài khoản vào câu lệnh SQL
	        rs = stmt.executeQuery();

	        while (rs.next()) {
	            // Tạo đối tượng Order và gán giá trị từ kết quả truy vấn
	            Order order = new Order();
	            order.setOrderId(rs.getInt("order_id"));
	            order.setAccountId(rs.getInt("account_id"));
	            order.setName(rs.getString("name"));
	            order.setPhoneNumber(rs.getString("phone_number"));
	            order.setEmail(rs.getString("email"));
	            order.setAddress(rs.getString("address"));
	            order.setContent(rs.getString("content"));
	            order.setTotalMoney(rs.getDouble("total_money"));
	            order.setSignature(rs.getString("signature"));
	            order.setStatus(rs.getString("status"));
	            order.setOrderDate(rs.getTimestamp("order_date")); // Nếu có cột thời gian tạo

	            orders.add(order); // Thêm đối tượng vào danh sách
	        }
	    } finally {
	        if (rs != null)
	            rs.close();
	        if (stmt != null)
	            stmt.close();
	        if (conn != null)
	            conn.close();
	    }

	    return orders; // Trả về danh sách đơn hàng
	}

// Them key
//    INSERT INTO UserKey (uID, publicKey)
//    VALUES (@userID, @newPublicKey);
	public void savePublicKeyToDatabase(int uID, String publicKey) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = new DBcontext().createConnection(); // Kết nối database

			// 1. Vô hiệu hóa các key cũ
			String disableOldKeysSQL = "UPDATE UserKey SET endTime = ?, isActive = 0 WHERE uID = ? AND isActive = 1";
			stmt = conn.prepareStatement(disableOldKeysSQL);
			stmt.setTimestamp(1, new Timestamp(System.currentTimeMillis())); // Thời gian hiện tại
			stmt.setInt(2, uID); // ID người dùng
			stmt.executeUpdate();
			stmt.close();

			// 2. Thêm key mới vào database
			String insertNewKeySQL = "INSERT INTO UserKey (uID, publicKey) VALUES (?, ?)";
			stmt = conn.prepareStatement(insertNewKeySQL);
			stmt.setInt(1, uID); // ID người dùng
			stmt.setString(2, publicKey); // Public key mới
			stmt.executeUpdate();

		} finally {
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		}
	}

// Stop key
//    UPDATE UserKey
//    SET isActive = 0, endTime = CURRENT_TIMESTAMP
//    WHERE uID = @userID AND isActive = 1; 

// Lấy email từ Account
	public String getEmail(int uID) {
		String email = null;
		String query = "Select email from account where uID = ?";
		try {
			conn = new DBcontext().createConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setInt(1, uID);
			rs = ps.executeQuery();
			while (rs.next()) {
				email = rs.getString("email");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return email;
	}
	// Lấy publicKey từ userKey thông qua uID
		public String getPublicKey(int uID) {
			String publicKey = null;
			String query = "Select publicKey from userKey where uID = ?";
			try {
				conn = new DBcontext().createConnection();// mo ket noi voi sql
				ps = conn.prepareStatement(query);
				ps.setInt(1, uID);
				rs = ps.executeQuery();
				while (rs.next()) {
					publicKey = rs.getString("publicKey");
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			return publicKey;
		}
	public List<Product> getTop5SanPhamBanChay() {
		List<Product> list = new ArrayList<>();
		String query = "SELECT product.id, product.name, product.image, product.price, product.title, product.description,  product.cateID, product.sell_ID, product.model, product.color, product.delivery, product.image2, product.image3, product.image4\r\n"
				+ " FROM soluongdaban inner join product on soluongdaban.productID = product.id\r\n"
				+ "ORDER BY soluongdaban DESC LIMIT 5";
		try {
			conn = new DBcontext().createConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
						rs.getString(6), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12),
						rs.getString(13), rs.getString(14)));
			}
		} catch (Exception e) {
		}
		return list;
	}

	public List<SoLuongDaBan> getTop10SanPhamBanChay() {
		List<SoLuongDaBan> list = new ArrayList<>();
		String query = "select  * \r\n" + "from SoLuongDaBan\r\n" + "order by soLuongDaBan desc limit 10";
		try {
			conn = new DBcontext().createConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new SoLuongDaBan(rs.getInt(1), rs.getInt(2)));
			}
		} catch (Exception e) {
		}
		return list;
	}

	public List<Invoice> getAllInvoice() {
		List<Invoice> list = new ArrayList<>();
		String query = "select * from Invoice";
		try {
			conn = new DBcontext().createConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Invoice(rs.getInt(1), rs.getInt(2), rs.getDouble(3), rs.getDate(4)));
			}
		} catch (Exception e) {
		}
		return list;
	}

	public int SumAllAmountByAccountID(int accountID) {

		String query = "select sum(amount) from cart where accountID = ?";
		try {
			conn = new DBcontext().createConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, accountID);
			rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {

		}
		return 0;
	}

	public int countAllProductBySellID(int sell_ID) {
		String query = "select count(*) from Product where sell_ID=?";
		try {
			conn = new DBcontext().createConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setInt(1, sell_ID);
			rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
		}
		return 0;
	}

	public int getSellIDByProductID(int productID) {
		String query = "select sell_ID\r\n" + "from Product\r\n" + "where id= ?";
		try {
			conn = new DBcontext().createConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setInt(1, productID);
			rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
		}
		return 0;
	}

	public double totalMoneyDay(int day) {
		String query = "SELECT\r\n" + "    SUM(tongGia) AS totalTongGia\r\n" + "FROM\r\n" + "    Invoice\r\n"
				+ "WHERE\r\n" + "    DAYOFWEEK(ngayXuat) = ?\r\n" + "GROUP BY\r\n" + "    ngayXuat";
		try {
			conn = new DBcontext().createConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setInt(1, day);
			rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getDouble(1);
			}
		} catch (Exception e) {
		}
		return 0;
	}

	public double totalMoneyMonth(int month) {
		String query = "SELECT\r\n" + "    SUM(tongGia) AS totalTongGia\r\n" + "FROM\r\n" + "    Invoice\r\n"
				+ "WHERE\r\n" + "    MONTH(ngayXuat) = ?\r\n" + "GROUP BY\r\n" + "    MONTH(ngayXuat)";
		try {
			conn = new DBcontext().createConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setInt(1, month);
			rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getDouble(1);
			}
		} catch (Exception e) {
		}
		return 0;
	}

	public int countAllProduct() {
		String query = "select count(*) from Product";
		try {
			conn = new DBcontext().createConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
		}
		return 0;
	}

	public double sumAllInvoice() {
		String query = "select SUM(tongGia) from Invoice";
		try {
			conn = new DBcontext().createConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getDouble(1);
			}
		} catch (Exception e) {
		}
		return 0;
	}

	public int countAllReview() {
		String query = "select count(*) from Review";
		try {
			conn = new DBcontext().createConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
		}
		return 0;
	}

	public int getCateIDByProductID(String id) {
		String query = "select cateID from product\r\n" + "where id = ?";
		try {
			conn = new DBcontext().createConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setString(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
		}
		return 0;
	}

	public List<Account> getAllAccount() {
		List<Account> list = new ArrayList<>();
		String query = "select * from Account";
		try {
			conn = new DBcontext().createConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Account(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5),
						rs.getString(6)));
			}
		} catch (Exception e) {
		}
		return list;
	}

	public List<Supplier> getAllSupplier() {
		List<Supplier> list = new ArrayList<>();
		String query = "select * from Supplier";
		try {
			conn = new DBcontext().createConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Supplier(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getInt(6)));
			}
		} catch (Exception e) {
		}
		return list;
	}

	public List<TongChiTieuBanHang> getTop5KhachHang() {
		List<TongChiTieuBanHang> list = new ArrayList<>();
		String query = "select *\r\n" + "from TongChiTieuBanHang\r\n" + "order by TongChiTieu desc limit 5";
		try {
			conn = new DBcontext().createConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new TongChiTieuBanHang(rs.getInt(1), rs.getDouble(2), rs.getDouble(3)));
			}
		} catch (Exception e) {
		}
		return list;
	}

	public List<TongChiTieuBanHang> getTop5NhanVien() {
		List<TongChiTieuBanHang> list = new ArrayList<>();
		String query = "select  *\r\n" + "        		from TongChiTieuBanHang\r\n"
				+ "        	order by TongBanHang desc limit 5";
		try {
			conn = new DBcontext().createConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new TongChiTieuBanHang(rs.getInt(1), rs.getDouble(2), rs.getDouble(3)));
			}
		} catch (Exception e) {
		}
		return list;
	}

	public List<Product> getTop3() {
		List<Product> list = new ArrayList<>();
		String query = "select top 3 * from Product";
		try {
			conn = new DBcontext().createConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
						rs.getString(6), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12),
						rs.getString(13), rs.getString(14)));
			}
		} catch (Exception e) {
		}
		return list;
	}

	public List<Product> getNext3Product(int amount) {
		List<Product> list = new ArrayList<>();
		String query = "SELECT *\n" + "  FROM Product\n" + " ORDER BY id\n" + "OFFSET ? ROWS\n"
				+ " FETCH NEXT 3 ROWS ONLY";
		try {
			conn = new DBcontext().createConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setInt(1, amount);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
						rs.getString(6), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12),
						rs.getString(13), rs.getString(14)));
			}
		} catch (Exception e) {
		}
		return list;
	}

	public List<Product> getNext4NikeProduct(int amount) {
		List<Product> list = new ArrayList<>();
		String query = "select * from Product\r\n" + "where cateID=2\r\n" + "order by id desc\r\n" + "offset ? rows\r\n"
				+ "fetch next 4 rows only";
		try {
			conn = new DBcontext().createConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setInt(1, amount);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
						rs.getString(6), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12),
						rs.getString(13), rs.getString(14)));
			}
		} catch (Exception e) {
		}
		return list;
	}

	public List<Product> getNext4AdidasProduct(int amount) {
		List<Product> list = new ArrayList<>();
		String query = "select * from Product\r\n" + "where cateID=1\r\n" + "order by id desc\r\n" + "offset ? rows\r\n"
				+ "fetch next 4 rows only";
		try {
			conn = new DBcontext().createConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setInt(1, amount);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
						rs.getString(6), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12),
						rs.getString(13), rs.getString(14)));
			}
		} catch (Exception e) {
		}
		return list;
	}

	public List<Product> getProductByCID(String cid) {
		List<Product> list = new ArrayList<>();
		String query = "select * from Product\n" + "where cateID = ?";
		try {
			conn = new DBcontext().createConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setString(1, cid);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
						rs.getString(6), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12),
						rs.getString(13), rs.getString(14)));
			}
		} catch (Exception e) {
		}
		return list;
	}

	public List<Product> getProductBySellIDAndIndex(int id, int indexPage) {
		List<Product> list = new ArrayList<>();
		String query = "SELECT * FROM Product \n" + "WHERE sell_ID = ?\n" + "ORDER BY id\n" + "LIMIT ?, 5";

		try {
			conn = new DBcontext().createConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			ps.setInt(2, (indexPage - 1) * 5);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
						rs.getString(6), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12),
						rs.getString(13), rs.getString(14)));
			}
		} catch (Exception e) {
		}
		return list;
	}

	public List<Product> getProductByIndex(int indexPage) {
		List<Product> list = new ArrayList<>();
		String query = "SELECT * FROM Product \r\n" + "ORDER BY id \r\n" + "LIMIT ?, 9";
		try {
			conn = new DBcontext().createConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setInt(1, (indexPage - 1) * 9);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
						rs.getString(6), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12),
						rs.getString(13), rs.getString(14)));
			}
		} catch (Exception e) {
		}
		return list;
	}

	public List<Product> searchByName(String txtSearch) {
		List<Product> list = new ArrayList<>();
		String query = "select * from Product\n" + "where [name] like ?";
		try {
			conn = new DBcontext().createConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setString(1, "%" + txtSearch + "%");
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
						rs.getString(6), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12),
						rs.getString(13), rs.getString(14)));
			}
		} catch (Exception e) {
		}
		return list;
	}

	public List<Invoice> searchByNgayXuat(String ngayXuat) {
		List<Invoice> list = new ArrayList<>();
		String query = "select * from Invoice\r\n" + "where [ngayXuat] ='" + ngayXuat + "'";
		try {
			conn = new DBcontext().createConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
//            ps.setString(1,ngayXuat);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Invoice(rs.getInt(1), rs.getInt(2), rs.getDouble(3), rs.getDate(4)));
			}
		} catch (Exception e) {
		}
		return list;
	}

	public List<Product> searchPriceUnder100() {
		List<Product> list = new ArrayList<>();
		String query = "select * from Product\r\n" + "where [price] < 100";
		try {
			conn = new DBcontext().createConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
						rs.getString(6), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12),
						rs.getString(13), rs.getString(14)));
			}
		} catch (Exception e) {
		}
		return list;
	}

	public List<Product> searchPrice100To200() {
		List<Product> list = new ArrayList<>();
		String query = "select * from Product\r\n" + "where [price] >= 100 and [price]<=200";
		try {
			conn = new DBcontext().createConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
						rs.getString(6), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12),
						rs.getString(13), rs.getString(14)));
			}
		} catch (Exception e) {
		}
		return list;
	}

	public List<Product> searchColorWhite() {
		List<Product> list = new ArrayList<>();
		String query = "select * from Product\r\n" + "where color = 'White'";
		try {
			conn = new DBcontext().createConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
						rs.getString(6), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12),
						rs.getString(13), rs.getString(14)));
			}
		} catch (Exception e) {
		}
		return list;
	}

	public List<Product> searchColorGray() {
		List<Product> list = new ArrayList<>();
		String query = "select * from Product\r\n" + "where color = 'Gray'";
		try {
			conn = new DBcontext().createConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
						rs.getString(6), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12),
						rs.getString(13), rs.getString(14)));
			}
		} catch (Exception e) {
		}
		return list;
	}

	public List<Product> searchColorBlack() {
		List<Product> list = new ArrayList<>();
		String query = "select * from Product\r\n" + "where color = 'Black'";
		try {
			conn = new DBcontext().createConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
						rs.getString(6), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12),
						rs.getString(13), rs.getString(14)));
			}
		} catch (Exception e) {
		}
		return list;
	}

	public List<Product> searchColorYellow() {
		List<Product> list = new ArrayList<>();
		String query = "select * from Product\r\n" + "where color = 'Yellow'";
		try {
			conn = new DBcontext().createConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
						rs.getString(6), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12),
						rs.getString(13), rs.getString(14)));
			}
		} catch (Exception e) {
		}
		return list;
	}

	public List<Product> searchByPriceMinToMax(String priceMin, String priceMax) {
		List<Product> list = new ArrayList<>();
		String query = "select * from Product\r\n" + "where [price] >= ? and [price]<=?";
		try {
			conn = new DBcontext().createConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setString(1, priceMin);
			ps.setString(2, priceMax);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
						rs.getString(6), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12),
						rs.getString(13), rs.getString(14)));
			}
		} catch (Exception e) {
		}
		return list;
	}

	public List<Product> searchPriceAbove200() {
		List<Product> list = new ArrayList<>();
		String query = "select * from Product\r\n" + "where [price] > 200";
		try {
			conn = new DBcontext().createConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
						rs.getString(6), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12),
						rs.getString(13), rs.getString(14)));
			}
		} catch (Exception e) {
		}
		return list;
	}

	public List<Product> getRelatedProduct(int cateIDProductDetail) {
		List<Product> list = new ArrayList<>();
		String query = "SELECT * FROM product \r\n" + "       WHERE cateID = 2\r\n" + "       ORDER BY id DESC \r\n"
				+ "       LIMIT 4";

		try {
			conn = new DBcontext().createConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setInt(1, cateIDProductDetail);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
						rs.getString(6), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12),
						rs.getString(13), rs.getString(14)));
			}
		} catch (Exception e) {
		}
		return list;
	}

	public List<Review> getAllReviewByProductID(String productId) {
		List<Review> list = new ArrayList<>();
		String query = "select * from Review\r\n" + "        	where productID = ?";
		try {
			conn = new DBcontext().createConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setString(1, productId);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Review(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getDate(4)));
			}
		} catch (Exception e) {
		}
		return list;
	}

	public Product getProductByID(String id) {
		String query = "select * from product\r\n" + "where id = ?";
		try {
			conn = new DBcontext().createConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setString(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				return new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
						rs.getString(6), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12),
						rs.getString(13), rs.getString(14));
			}
		} catch (Exception e) {
		}
		return null;
	}

	public List<Cart> getCartByAccountID(int accountID) {
		List<Cart> list = new ArrayList<>();
		String query = "select * from Cart where accountID = ?";
		try {
			conn = new DBcontext().createConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setInt(1, accountID);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Cart(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getString(5)));
			}
		} catch (Exception e) {
		}
		return list;
	}

	public Cart checkCartExist(int accountID, int productID) {

		String query = "select * from Cart\r\n" + "where accountID = ? and productID = ?";
		try {
			conn = new DBcontext().createConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setInt(1, accountID);
			ps.setInt(2, productID);
			rs = ps.executeQuery();
			while (rs.next()) {
				return new Cart(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getString(5));
			}
		} catch (Exception e) {
		}
		return null;
	}

	public int checkAccountAdmin(int userID) {

		String query = "select isAdmin from Account where [uID]=?";
		try {
			conn = new DBcontext().createConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setInt(1, userID);

			rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {

		}
		return 0;
	}

	public TongChiTieuBanHang checkTongChiTieuBanHangExist(int userID) {

		String query = "select * from TongChiTieuBanHang where userID=?";
		try {
			conn = new DBcontext().createConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setInt(1, userID);

			rs = ps.executeQuery();
			while (rs.next()) {
				return new TongChiTieuBanHang(rs.getInt(1), rs.getDouble(2), rs.getDouble(3));
			}
		} catch (Exception e) {
		}
		return null;
	}

	public SoLuongDaBan checkSoLuongDaBanExist(int productID) {

		String query = "select * from SoLuongDaBan where productID = ?";
		try {
			conn = new DBcontext().createConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setInt(1, productID);

			rs = ps.executeQuery();
			while (rs.next()) {
				return new SoLuongDaBan(rs.getInt(1), rs.getInt(2));
			}
		} catch (Exception e) {
		}
		return null;
	}

	public List<Category> getAllCategory() {
		List<Category> list = new ArrayList<>();
		String query = "select * from Category";
		try {
			conn = new DBcontext().createConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Category(rs.getInt(1), rs.getString(2)));
			}
		} catch (Exception e) {
		}
		return list;
	}

//
	public Product getLast() {
		String query = "SELECT * FROM Product\r\n" + "ORDER BY id DESC\r\n" + "LIMIT 1";
		try {
			conn = new DBcontext().createConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				return new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10),
						rs.getString(11), rs.getString(12));
			}
		} catch (Exception e) {
		}
		return null;
	}

	public List<Product> get8Last() {
		List<Product> list = new ArrayList<>();
		String query = "select top 8 * from Product order by id desc";
		try {
			conn = new DBcontext().createConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
						rs.getString(6), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12),
						rs.getString(13), rs.getString(14)));
			}
		} catch (Exception e) {
		}
		return list;
	}

	public List<Product> get4NikeLast() {
		List<Product> list = new ArrayList<>();
		String query = "select top 4 * from Product\r\n" + "where cateID = 2\r\n" + "order by id desc";
		try {
			conn = new DBcontext().createConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
						rs.getString(6), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12),
						rs.getString(13), rs.getString(14)));
			}
		} catch (Exception e) {
		}
		return list;
	}

	public List<Product> get4AdidasLast() {
		List<Product> list = new ArrayList<>();
		String query = "select top 4 * from Product\r\n" + "where cateID = 1\r\n" + "order by id desc";
		try {
			conn = new DBcontext().createConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
						rs.getString(6), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12),
						rs.getString(13), rs.getString(14)));
			}
		} catch (Exception e) {
		}
		return list;
	}

	public Account login(String user, String pass) {
		String query = "select * from Account\r\n" + "               where user = ?\r\n"
				+ "               and pass = ?";
		try {
			conn = new DBcontext().createConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setString(1, user);
			ps.setString(2, pass);
			rs = ps.executeQuery();
			while (rs.next()) {
				return new Account(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5),
						rs.getString(6));
			}
		} catch (Exception e) {
		}
		return null;
	}

	public Account checkAccountExist(String user) {
		String query = "select * from Account\r\n" + "where user = ?";
		try {
			conn = new DBcontext().createConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setString(1, user);
			rs = ps.executeQuery();
			while (rs.next()) {
				return new Account(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5),
						rs.getString(6));
			}
		} catch (Exception e) {
		}
		return null;
	}

	public Account checkAccountExistByUsernameAndEmail(String username, String email) {
		String query = "select * from Account where user= ? and email =?";
		try {
			conn = new DBcontext().createConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, email);
			rs = ps.executeQuery();
			while (rs.next()) {
				return new Account(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5),
						rs.getString(6));
			}
		} catch (Exception e) {
		}
		return null;
	}

	public Review getNewReview(int accountID, int productID) {
		String query = "select  * from Review\r\n" + "where accountID = ? and productID = ?\r\n"
				+ "order by maReview desc limit 1";
		try {
			conn = new DBcontext().createConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setInt(1, accountID);
			ps.setInt(2, productID);
			rs = ps.executeQuery();
			while (rs.next()) {
				return new Review(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getDate(4));
			}
		} catch (Exception e) {
		}
		return null;
	}

	// lay ra uID moi nhat tu bang Account
	public int getUID() {
		String query = "SELECT uID FROM account ORDER BY uID DESC LIMIT 1";
		try {
			conn = new DBcontext().createConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				return Integer.parseInt(rs.getString(1));

			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return -1;
	}

	public void signup(String user, String pass, String email) {
		String query = "insert into Account\r\n" + "values(?,?, ?, 0, 0, ?)";
		try {
			if (checkAccountExist(user) == null) {
				int uID = getUID() + 1;
				conn = new DBcontext().createConnection();
				ps = conn.prepareStatement(query);
				ps.setInt(1, uID);
				ps.setString(2, user);
				ps.setString(3, pass);
				ps.setString(4, email);
				ps.executeUpdate();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void deleteInvoiceByAccountId(String id) {
		String query = "delete from Invoice\n" + "where accountID = ?";
		try {
			conn = new DBcontext().createConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setString(1, id);
			ps.executeUpdate();
		} catch (Exception e) {
		}
	}

	public void deleteTongChiTieuBanHangByUserID(String id) {
		String query = "delete from TongChiTieuBanHang\n" + "where userID = ?";
		try {
			conn = new DBcontext().createConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setString(1, id);
			ps.executeUpdate();
		} catch (Exception e) {
		}
	}

	public void deleteProduct(String pid) {
		String query = "delete from Product\n" + "where id = ?";
		try {
			conn = new DBcontext().createConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setString(1, pid);
			ps.executeUpdate();
		} catch (Exception e) {
		}
	}

	public void deleteProductBySellID(String id) {
		String query = "delete from Product\n" + "where sell_ID = ?";
		try {
			conn = new DBcontext().createConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setString(1, id);
			ps.executeUpdate();
		} catch (Exception e) {
		}
	}

	public void deleteCartByAccountID(int accountID) {
		String query = "delete from Cart \r\n" + "where accountID = ?";
		try {
			conn = new DBcontext().createConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setInt(1, accountID);
			ps.executeUpdate();
		} catch (Exception e) {
		}
	}

	public void deleteCartByProductID(String productID) {
		String query = "delete from Cart where productID=?";
		try {
			conn = new DBcontext().createConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setString(1, productID);
			ps.executeUpdate();
		} catch (Exception e) {
		}
	}

	public void deleteSoLuongDaBanByProductID(String productID) {
		String query = "delete from SoLuongDaBan where productID=?";
		try {
			conn = new DBcontext().createConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setString(1, productID);
			ps.executeUpdate();
		} catch (Exception e) {
		}
	}

	public void deleteReviewByProductID(String productID) {
		String query = "delete from Review where productID = ?";
		try {
			conn = new DBcontext().createConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setString(1, productID);
			ps.executeUpdate();
		} catch (Exception e) {
		}
	}

	public void deleteReviewByAccountID(String id) {
		String query = "delete from Review where accountID = ?";
		try {
			conn = new DBcontext().createConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setString(1, id);
			ps.executeUpdate();
		} catch (Exception e) {
		}
	}

	public void deleteAccount(String id) {
		String query = "delete from Account where uID= ?";
		try {
			conn = new DBcontext().createConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setString(1, id);
			ps.executeUpdate();
		} catch (Exception e) {
		}
	}

	public void deleteSupplier(String idSupplier) {
		String query = "delete from Supplier\r\n" + "where idSupplier=?";
		try {
			conn = new DBcontext().createConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setString(1, idSupplier);
			ps.executeUpdate();
		} catch (Exception e) {
		}
	}

	public void deleteCart(int productID) {
		String query = "delete from Cart where productID = ?";
		try {
			conn = new DBcontext().createConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setInt(1, productID);
			ps.executeUpdate();
		} catch (Exception e) {
		}
	}

	// lay ra id cao nhat tu bang product
	public int getIdProduct() {
		String query = "SELECT id FROM product ORDER BY id DESC LIMIT 1";
		try {
			conn = new DBcontext().createConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getInt(1);

			}
		} catch (Exception e) {
		}
		return -1;
	}

	public void insertProduct(String name, String image, String price, String title, String description,
			String category, int sid, String model, String color, String delivery, String image2, String image3,
			String image4) {
		String query = "INSERT INTO Product \r\n" + "VALUES \r\n" + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);\r\n";
		try {
			int id = getIdProduct() + 1;
			float pprice = Float.parseFloat(price);
			int pcate_id = Integer.parseInt(category);
			conn = new DBcontext().createConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setString(3, image);
			ps.setFloat(4, pprice);
			ps.setString(5, title);
			ps.setString(6, description);
			ps.setInt(7, pcate_id);
			ps.setInt(8, sid);
			ps.setString(9, model);
			ps.setString(10, color);
			ps.setString(11, delivery);
			ps.setString(12, image2);
			ps.setString(13, image3);
			ps.setString(14, image4);
			ps.executeUpdate();

		} catch (SQLException e) {

		}
	}

	public void insertAccount(String user, String pass, String isSell, String isAdmin, String email) {
		String query = "insert Account(uID, user, pass, isSell, isAdmin, email)\r\n" + "values(?,?,?,?,?,?)";
		try {
			int uID = getUID() + 1;

			int aIsSell = Integer.parseInt(isSell);
			int aIsAdmin = Integer.parseInt(isAdmin);
			conn = new DBcontext().createConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setInt(1, uID);
			ps.setString(2, user);
			ps.setString(3, pass);
			ps.setInt(4, aIsSell);
			ps.setInt(5, aIsAdmin);
			ps.setString(6, email);
			ps.executeUpdate();
		} catch (Exception e) {
		}
	}

	public void insertTongChiTieuBanHang(int userID, double tongChiTieu, double tongBanHang) {
		String query = "insert TongChiTieuBanHang(userID,TongChiTieu,TongBanHang)\r\n" + "values(?,?,?)";
		try {
			conn = new DBcontext().createConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setInt(1, userID);
			ps.setDouble(2, tongChiTieu);
			ps.setDouble(3, tongBanHang);

			ps.executeUpdate();
		} catch (Exception e) {
		}
	}

	public void insertSoLuongDaBan(int productID, int soLuongDaBan) {
		String query = "insert SoLuongDaBan(productID,soLuongDaBan)\r\n" + "values(?,?)";
		try {
			conn = new DBcontext().createConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setInt(1, productID);
			ps.setInt(2, soLuongDaBan);

			ps.executeUpdate();
		} catch (Exception e) {
		}
	}

	public void insertSupplier(String nameSupplier, String phoneSupplier, String emailSupplier, String addressSupplier,
			int cateID) {
		String query = "insert Supplier(nameSupplier, phoneSupplier, emailSupplier, addressSupplier, cateID) \r\n"
				+ "values(?,?,?,?,?)";
		try {
			conn = new DBcontext().createConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setString(1, nameSupplier);
			ps.setString(2, phoneSupplier);
			ps.setString(3, emailSupplier);
			ps.setString(4, addressSupplier);
			ps.setInt(5, cateID);
			ps.executeUpdate();
		} catch (Exception e) {
		}
	}

	// Lay ra ngay hien tai
	private static java.sql.Date getCurrentDate() {
		java.util.Date today = new java.util.Date();
		return new java.sql.Date(today.getTime());
	}

	// lay ra maReview moi nhat tu bang Review
	public int getMaReview() {
		String query = "select maReview from review order by maReview desc limit 1";
		try {
			conn = new DBcontext().createConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				return Integer.parseInt(rs.getString(1));

			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return -1;
	}

	public void insertReview(int accountID, int productID, String contentReview) {
		String query = "insert Review(accountID, productID, contentReview, dateReview, maReview)\r\n"
				+ "values(?,?,?,?,?)";

		try {
			int maReview = getMaReview();
			conn = new DBcontext().createConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setInt(1, accountID);
			ps.setInt(2, productID);
			ps.setString(3, contentReview);
			ps.setDate(4, getCurrentDate());
			ps.setInt(5, maReview);
			ps.executeUpdate();

		} catch (Exception e) {
		}
	}

	public void insertInvoice(int accountID, double tongGia) {
		String query = "insert Invoice(accountID,tongGia,ngayXuat)\r\n" + "values(?,?,?)";

		try {
			conn = new DBcontext().createConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setInt(1, accountID);
			ps.setDouble(2, tongGia);
			ps.setDate(3, getCurrentDate());
			ps.executeUpdate();

		} catch (Exception e) {

		}
	}

	// lay ra maCart moi nhat tu bang Cart
	public int getMaCart() {
		String query = "SELECT maCart FROM cart ORDER BY macart DESC LIMIT 1";
		try {
			conn = new DBcontext().createConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				return Integer.parseInt(rs.getString(1));

			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return -1;
	}

	public void insertCart(int accountID, int productID, int amount, String size) {
		String query = "insert Cart(accountID, productID, amount,maCart, size)\r\n" + "values(?,?,?,?,?)";
		try {
			int maCart = getMaCart() + 1;
			conn = new DBcontext().createConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setInt(1, accountID);
			ps.setInt(2, productID);
			ps.setInt(3, amount);
			ps.setInt(4, maCart);
			ps.setString(5, size);
			ps.executeUpdate();
		} catch (Exception e) {
		}
	}

	public void editProduct(String pname, String pimage, float pprice, String ptitle, String pdescription,
			int pcategory, String pmodel, String pcolor, String pdelivery, String pimage2, String pimage3,
			String pimage4, int pid) {
		String query = "UPDATE Product\r\n" + "SET\r\n" + "    name = ?,\r\n" + "    image = ?,\r\n"
				+ "    price = ?,\r\n" + "    title = ?,\r\n" + "    description = ?,\r\n" + "    cateID = ?,\r\n"
				+ "    model = ?,\r\n" + "    color = ?,\r\n" + "    delivery = ?,\r\n" + "    image2 = ?,\r\n"
				+ "    image3 = ?,\r\n" + "    image4 = ?\r\n" + "WHERE id = ?";
		try {

			conn = new DBcontext().createConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setString(1, pname);
			ps.setString(2, pimage);
			ps.setFloat(3, pprice);
			ps.setString(4, ptitle);
			ps.setString(5, pdescription);
			ps.setInt(6, pcategory);
			ps.setString(7, pmodel);
			ps.setString(8, pcolor);
			ps.setString(9, pdelivery);
			ps.setString(10, pimage2);
			ps.setString(11, pimage3);
			ps.setString(12, pimage4);
			ps.setInt(13, pid);
			ps.executeUpdate();

		} catch (Exception e) {

		}
	}

	public void editAccount(String username, String password, String email, int uID) {
		String query = "update Account set [user]=?,\r\n" + "[pass]=?,\r\n" + "[email]=?\r\n" + "where [uID] = ?";
		try {
			conn = new DBcontext().createConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setString(3, email);
			ps.setInt(4, uID);
			ps.executeUpdate();
		} catch (Exception e) {
		}
	}

	public void editTongChiTieu(int accountID, double totalMoneyVAT) {
		String query = "exec dbo.proc_CapNhatTongChiTieu ?,?";
		try {
			conn = new DBcontext().createConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setInt(1, accountID);
			ps.setDouble(2, totalMoneyVAT);

			ps.executeUpdate();

		} catch (Exception e) {

		}
	}

	public void editSoLuongDaBan(int productID, int soLuongBanThem) {
		String query = "exec dbo.proc_CapNhatSoLuongDaBan ?,?";
		try {
			conn = new DBcontext().createConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setInt(1, productID);
			ps.setInt(2, soLuongBanThem);

			ps.executeUpdate();

		} catch (Exception e) {

		}
	}

	public void editTongBanHang(int sell_ID, double tongTienBanHangThem) {
		String query = "exec dbo.proc_CapNhatTongBanHang ?,?";
		try {
			conn = new DBcontext().createConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setInt(1, sell_ID);
			ps.setDouble(2, tongTienBanHangThem);

			ps.executeUpdate();

		} catch (Exception e) {

		}
	}

	public void editAmountAndSizeCart(int accountID, int productID, int amount, String size) {
		String query = "update Cart set amount= ?,\r\n" + "size=?\r\n" + "where accountID = ? and productID = ?";
		try {
			conn = new DBcontext().createConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setInt(1, amount);
			ps.setString(2, size);
			ps.setInt(3, accountID);
			ps.setInt(4, productID);
			ps.executeUpdate();
		} catch (Exception e) {
		}
	}

	public void editAmountCart(int accountID, int productID, int amount) {
		String query = "update Cart set amount=?\r\n" + "where accountID=? and productID=?";
		try {
			conn = new DBcontext().createConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setInt(1, amount);
			ps.setInt(2, accountID);
			ps.setInt(3, productID);
			ps.executeUpdate();
		} catch (Exception e) {
		}
	}

	public List<Product> searchByNameABC(String name) {
		List<Product> products = new ArrayList<>();
		String query = "select * from product\r\n" + "where name like ?";
		try {
			conn = new DBcontext().createConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, "%" + name + "%");
			rs = ps.executeQuery();
			while (rs.next()) {
				products.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4),
						rs.getString(5), rs.getString(6), rs.getString(9), rs.getString(10), rs.getString(11),
						rs.getString(12), rs.getString(13), rs.getString(14)));
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return products;

	}

	// tim kiem sp nho hon 200$
	public List<Product> searchNhoHon200() {
		List<Product> list = new ArrayList<>();
		String query = "select * from product\r\n" + "where price between 1 and 200 ";
		try {
			conn = new DBcontext().createConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
						rs.getString(6), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12),
						rs.getString(13), rs.getString(14)));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	// lay so luong sp co giá nhỏ hơn 200$
	public int countNhoHon200() {
		String query = "select count(*) from product\r\n" + "where price between 1 and 200 \r\n";
		try {
			conn = new DBcontext().createConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return -1;
	}

	public static void main(String[] args) throws Exception {
		Dao dao = new Dao();
		//dao.savePublicKeyToDatabase(6, "test");
		System.out.println(dao.getOrderByOrderId(5));
	}

}
