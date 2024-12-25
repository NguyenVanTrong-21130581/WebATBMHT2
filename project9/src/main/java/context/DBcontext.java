package context;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBcontext {
    
	static String jdbcUrl = "jdbc:mysql://localhost:3306/quanlibangiay";
	static String user = "root";
	static String password = "nguyenvantrong";

	public static synchronized Connection createConnection() {
		Connection connection = null;
		try {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection(jdbcUrl, user, password);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Kết nối đã được thiết lập thành công
		} catch (SQLException e) {
			System.out.println("error" + e);
		}
		return connection;
	}
    	public static void main(String[] args) {
			try {
				System.out.println(createConnection());
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
}
