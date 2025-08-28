package example.iotstar.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectMySQL {

	// Thông tin kết nối
	private static final String USERNAME = "root";
	private static final String PASSWORD = "2357";
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/ltweb?useSSL=false&serverTimezone=UTC";
	// URL kết nối MySQL:
	// - localhost:3306 = chạy trên máy local, cổng mặc định 3306
	// - database_name = tên database của bạn
	// - useSSL=false để tắt SSL warning
	// - serverTimezone=UTC để tránh lỗi timezone

	// Hàm kết nối database
	public static Connection getDatabaseConnection() {
		Connection conn = null;
		try {
			// Load driver
			Class.forName(DRIVER);
			System.out.println("Driver đã được load thành công!");

			// Tạo kết nối
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			System.out.println("Kết nối MySQL thành công!");
		} catch (ClassNotFoundException e) {
			System.err.println("Lỗi: Không tìm thấy JDBC Driver.");
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Lỗi SQL khi kết nối MySQL:");
			System.err.println("   ➝ SQLState: " + e.getSQLState());
			System.err.println("   ➝ Error Code: " + e.getErrorCode());
			System.err.println("   ➝ Message: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("Lỗi không xác định:");
			e.printStackTrace();
		}
		return conn;
	}

	// Test main
	public static void main(String[] args) {
		// khong co static
		// DBConnectMySQL db = new DBConnectMySQL();
		// Connection conn = db.getDatabaseConnection();
		
		// co static 
		Connection conn = getDatabaseConnection();
		if (conn == null) {
			System.out.println("Kết nối thất bại.");
		} else {
			System.out.println("Có thể sử dụng kết nối để query database: " + conn);
		}
	}
}
