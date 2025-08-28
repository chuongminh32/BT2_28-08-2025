package example.iotstar.config;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectSQLServer {

    // --- Cấu hình kết nối ---
    private final String serverName = "MINH-CHUONG"; // Tên server SQL Server
    private final String dbName = "ltwebct5st7";     // Tên database
    private final String portNumber = "1433";        // Port mặc định
    private final String instance = "";              // Nếu SQL Server là "default instance" thì để rỗng
    private final String userID = "sa";              // Username SQL Server
    private final String password = "2357";          // Password

    /**
     * Tạo kết nối tới SQL Server bằng SQL Authentication
     */
    public Connection getConnection() throws ClassNotFoundException {
        Connection conn = null;
        try {
            // Load driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            // Xây dựng URL
            String url;
            if (instance == null || instance.trim().isEmpty()) {
                url = "jdbc:sqlserver://" + serverName + ":" + portNumber 
                        + ";databaseName=" + dbName
                        + ";encrypt=true;trustServerCertificate=true";
            } else {
                url = "jdbc:sqlserver://" + serverName + "\\" + instance + ":" + portNumber 
                        + ";databaseName=" + dbName
                        + ";encrypt=true;trustServerCertificate=true";
            }

            // Kết nối SQL Authentication
            conn = DriverManager.getConnection(url, userID, password);

            if (conn != null) {
                DatabaseMetaData dm = conn.getMetaData();
                System.out.println("✅ Kết nối SQL Server thành công!");
                System.out.println("Driver name: " + dm.getDriverName());
                System.out.println("Driver version: " + dm.getDriverVersion());
                System.out.println("Product name: " + dm.getDatabaseProductName());
                System.out.println("Product version: " + dm.getDatabaseProductVersion());
            }
        } catch (SQLException e) {
            System.err.println("❌ Lỗi khi kết nối SQL Server:");
            System.err.println("   ➝ SQLState: " + e.getSQLState());
            System.err.println("   ➝ Error Code: " + e.getErrorCode());
            System.err.println("   ➝ Message: " + e.getMessage());
        }
        return conn;
    }

    // Test main
    public static void main(String[] args) {
        try {
            DBConnectSQLServer db = new DBConnectSQLServer();
            Connection conn = db.getConnection();
            if (conn == null) {
                System.out.println("❌ Kết nối thất bại.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
