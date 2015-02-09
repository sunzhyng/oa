package net.icocoa.oa.service;

import net.icocoa.oa.po.User;

public interface UserService {
	public User findById(String Id);
	public void insert(User u);
}
