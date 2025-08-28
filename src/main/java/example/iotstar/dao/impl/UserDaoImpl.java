package example.iotstar.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import example.iotstar.config.DBConnectSQLServer;
import example.iotstar.dao.IUserDao;
import example.iotstar.models.UserModel;

public class UserDaoImpl extends DBConnectSQLServer implements IUserDao {

	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;

	@Override
	public UserModel checkLogin(String username, String password) {
		String sql = "SELECT * FROM users WHERE username=? AND password=?";
		try (Connection conn = super.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, username);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				UserModel user = new UserModel();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null; // không tìm thấy
	}

	@Override
	public List<UserModel> findAll() {
		String query = "SELECT * FROM users";
		List<UserModel> list = new ArrayList<>();
		try {
			conn = super.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				UserModel newUser = new UserModel(rs.getInt("id"), rs.getString("username"), rs.getString("email"),
						rs.getString("password"), rs.getString("fullname"), rs.getString("images"),
						rs.getString("phone"), rs.getInt("roleid"), rs.getDate("createDate") // giữ nguyên thời gian
				);
				list.add(newUser);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return list; // luôn return list, không null
	}

	@Override
	public UserModel findById(int id) {
		String query = "SELECT * FROM users WHERE id = ?";
		try {
			conn = super.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			if (rs.next()) {
				return new UserModel(rs.getInt("id"), rs.getString("username"), rs.getString("email"),
						rs.getString("password"), rs.getString("fullname"), rs.getString("images"),
						rs.getString("phone"), rs.getInt("roleid"), rs.getDate("createDate"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return null;
	}

	@Override
	public void Insert(UserModel user) {
		String queryCheck = "SELECT * FROM users WHERE username = ? OR email = ?";
		String insertQuery = "INSERT INTO users(id, username, email, password, fullname, images, phone, roleid, createDate) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			conn = super.getConnection();

			// check tồn tại
			ps = conn.prepareStatement(queryCheck);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getEmail());
			rs = ps.executeQuery();

			if (rs.next()) {
				System.out.println("Đã tồn tại username hoặc email!");
				return;
			}
			rs.close();
			ps.close();

			// insert mới
			ps = conn.prepareStatement(insertQuery);
			ps.setInt(1, user.getId());
			ps.setString(2, user.getUsername());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getPassword());
			ps.setString(5, user.getFullname());
			ps.setString(6, user.getImages());
			ps.setString(7, user.getPhone());
			ps.setInt(8, user.getRoleid());
			ps.setDate(9, user.getCreateDate());

			ps.executeUpdate();
			System.out.println("Thêm user thành công!");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
	}

	@Override
	public void Update(UserModel user) {
		String query = "UPDATE users SET username=?, email=?, password=?, fullname=?, images=?, phone=?, roleid=?, createDate=? WHERE id=?";
		try {
			conn = super.getConnection();
			ps = conn.prepareStatement(query);

			ps.setString(1, user.getUsername());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPassword());
			ps.setString(4, user.getFullname());
			ps.setString(5, user.getImages());
			ps.setInt(6, user.getId());
			ps.setString(7, user.getPhone());
			ps.setInt(8, user.getRoleid());
			ps.setDate(9, user.getCreateDate());

			int rows = ps.executeUpdate();
			if (rows > 0) {
				System.out.println("Update thành công!");
			} else {
				System.out.println("Không tìm thấy user với id: " + user.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
	}

	@Override
	public void Delete(int id) {
		String query = "DELETE FROM users WHERE id = ?";
		try {
			conn = super.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);

			int rows = ps.executeUpdate();
			if (rows > 0) {
				System.out.println("Xóa user thành công! (id=" + id + ")");
			} else {
				System.out.println("Không tìm thấy user với id = " + id);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
	}

	@Override
	public UserModel findByUsername(String username) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM users WHERE username = ? ";
		try {
			conn = super.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			rs = ps.executeQuery();
			while (rs.next()) {
				UserModel user = new UserModel();
				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("email"));
				user.setUsername(rs.getString("username"));
				user.setFullname(rs.getString("fullname"));
				user.setPassword(rs.getString("password"));
				user.setRoleid(rs.getInt("roleid"));
				user.setPhone(rs.getString("phone"));
				user.setCreateDate(rs.getDate("createDate"));
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Hàm đóng kết nối an toàn
	private void closeAll() {
		try {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		UserDaoImpl dm = new UserDaoImpl();

//		UserModel newUserModel = new UserModel(4, "chuongminh", "chuong@gmail.com", "123", "phamhanminhchuong",
//				"https://images3.com");
//		dm.Insert(newUserModel);

//		UserModel modelUpdate = new UserModel(
//			    1,
//			    "admin",
//			    "admin@gmail.com",
//			    "2357",
//			    "phamhanminhchuong",
//			    "https://images3.com",
//			    "0977692690",
//			    2,
//			    new Date(0) // thời điểm hiện tại
//			);

		// Thử xóa user id=3
//		dm.Delete(3);
//		UserModel u = dm.findById(2);
//		System.out.println("Find user id=2: " + u);

		List<UserModel> listUserModel = dm.findAll();
		for (UserModel user : listUserModel) {
			System.out.println(user);
		}
	}

}
