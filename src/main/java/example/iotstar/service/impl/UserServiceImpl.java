package example.iotstar.service.impl;

import example.iotstar.dao.IUserDao;
import example.iotstar.dao.impl.UserDaoImpl;
import example.iotstar.models.UserModel;
import example.iotstar.service.IUserService;

public class UserServiceImpl implements IUserService {

	IUserDao userDao = new UserDaoImpl();

	@Override
	public UserModel login(String username, String password) {
		UserModel user = this.findByUsername(username);
		if (user != null && password.equals(user.getPassword())) {
			return user;
		}
		return null;
	}

	@Override
	public UserModel findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	@Override
	public void insert(UserModel user) {
		// TODO Auto-generated method stub
		userDao.Insert(user);
	}

	@Override
	public boolean register(String email, String password, String username, String fullname, String phone) {
		// TODO Auto-generated method stub
		// Kiểm tra email, username, phone đã tồn tại chưa
		if (checkExistEmail(email) || checkExistUsername(username) || checkExistPhone(phone)) {
			return false; // không đăng ký được
		}

		// Tạo đối tượng User mới
		UserModel user = new UserModel();
		user.setEmail(email);
		user.setPassword(password); // có thể hash password trước khi lưu
		user.setUsername(username);
		user.setFullname(fullname);
		user.setPhone(phone);

		// Thêm vào DB
		userDao.Insert(user);
		return true;

	}

	@Override
	public boolean checkExistEmail(String email) {
		// TODO Auto-generated method stub
		UserModel user = userDao.findByEmail(email);
		return user != null;
	}

	@Override
	public boolean checkExistUsername(String username) {
		// TODO Auto-generated method stub
		UserModel user = userDao.findByUsername(username);
		return user != null;
	}

	@Override
	public boolean checkExistPhone(String phone) {
		// TODO Auto-generated method stub
		UserModel user = userDao.findByPhone(phone);
		return user != null;
	}
}
