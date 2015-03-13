package net.icocoa.oa.dao;

import java.util.List;

import net.icocoa.oa.po.SysUser;

import org.springframework.stereotype.Repository;

@Repository
public interface IUserInfoDao {

	public SysUser findByName(String username);

	public List<String> loadUserAuthorityByName(String username);


}
