package net.icocoa.oa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.icocoa.oa.dao.UserDao;
import net.icocoa.oa.po.User;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public User findById(String Id) {
		return userDao.findById(Id);
	}

	@Override
	public void insert(User u) {
		userDao.insert(u);
	}


}
