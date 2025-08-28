package example.iotstar.config;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectSQLServer {

	// Thông tin kết nối
	private final String serverName = "MINH-CHUONG"; // Tên server SQL Server (VD: localhost)
	private final String dbName = "ltwebct5st7"; // Tên database
	private final String portNumber = "1433"; // Port mặc định SQL Server
	private final String instance = ""; // Nếu SQL Server là "default instance" thì để rỗng
	private final String userID = "sa"; // Username đăng nhập SQL Server
	private final String password = "2357"; // Password

	public Connection getConnection() {
		Connection conn = null;

		try {
			// URL kết nối SQL Server
			String url;
			if (instance == null || instance.trim().isEmpty()) {
				// Nếu không có instance (SQL Server mặc định)
				url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";databaseName=" + dbName
						+ ";encrypt=true;trustServerCertificate=true";
			} else {
				// Nếu có instance cụ thể: jdbc:sqlserver://MINH-CHUONG\\:1433;databaseName=ltwebct5st7;encrypt=true;trustServerCertificate=true;
				url = "jdbc:sqlserver://" + serverName + "\\" + instance + ":" + portNumber + ";databaseName=" + dbName
						+ ";encrypt=true;trustServerCertificate=true";
			}

			// Kết nối tới SQL Server
			conn = DriverManager.getConnection(url, userID, password);

			if (conn != null) {
				DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
				System.out.println("Kết nối SQL Server thành công!");
				System.out.println("Driver name: " + dm.getDriverName());
				System.out.println("Driver version: " + dm.getDriverVersion());
				System.out.println("Product name: " + dm.getDatabaseProductName());
				System.out.println("Product version: " + dm.getDatabaseProductVersion());
			}
		} catch (SQLException e) {
			System.err.println("Lỗi khi kết nối SQL Server:");
			System.err.println("   ➝ SQLState: " + e.getSQLState());
			System.err.println("   ➝ Error Code: " + e.getErrorCode());
			System.err.println("   ➝ Message: " + e.getMessage());
			e.printStackTrace();
		}

		return conn;
	}

	// Test main
	public static void main(String[] args) {
		try {
			Connection conn = new DBConnectSQLServer().getConnection();
		}
		catch (Exception e) {
			
		}
		DBConnectSQLServer db = new DBConnectSQLServer();
		Connection conn = db.getConnection();
		if (conn == null) {
			System.out.println("Kết nối thất bại.");
		}
	}
}
