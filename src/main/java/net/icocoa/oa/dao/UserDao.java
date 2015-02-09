package net.icocoa.oa.dao;

import org.springframework.stereotype.Repository;

import net.icocoa.oa.po.User;

@Repository
public interface UserDao {

	public User findById(String Id);

	public void insert(User u);
}