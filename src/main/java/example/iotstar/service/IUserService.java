package example.iotstar.service;

import example.iotstar.models.UserModel;

public interface IUserService {
	UserModel login(String username, String password);

	UserModel findByUsername(String username);	
}
