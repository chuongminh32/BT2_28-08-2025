package example.iotstar.service;

import example.iotstar.models.UserModel;

public interface IUserService {
	UserModel login(String username, String password);

	UserModel findByUsername(String username);
	
	void insert(UserModel user);
	boolean register(String email, String password, String username, String
	fullname, String phone);
	boolean checkExistEmail(String email);
	boolean checkExistUsername(String username);
	boolean checkExistPhone(String phone);
}
