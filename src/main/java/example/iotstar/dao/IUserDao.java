package example.iotstar.dao;

import java.util.List;
import example.iotstar.models.UserModel;

public interface IUserDao {
	List<UserModel> findAll();
	
	UserModel findById(int id);
	
	void Insert(UserModel user); 
	
	void Update(UserModel user);
	
	void Delete(int id);
	
	UserModel checkLogin(String username, String password);
}
