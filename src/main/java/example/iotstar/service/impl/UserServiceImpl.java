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
}
