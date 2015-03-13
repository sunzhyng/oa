package net.icocoa.oa.service;

import java.util.ArrayList;
import java.util.List;

import net.icocoa.oa.dao.IUserInfoDao;
import net.icocoa.oa.po.SysUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MyBatisUserDetailService implements UserDetailsService {
	@Autowired
	private IUserInfoDao userInfoDao;

	@Autowired
	private UserCache userCache;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		System.out.println("username is :" + username);

		SysUser user = this.userInfoDao.findByName(username);
		List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
		try {
			List<String> authlist = userInfoDao.loadUserAuthorityByName(username);
			for (String roleName : authlist) {
				org.springframework.security.core.authority.SimpleGrantedAuthority authority = new SimpleGrantedAuthority(roleName);
				auths.add(authority);
			}
		} catch (RuntimeException re) {
			throw re;
		}

		boolean enables = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		User userdetail = new User(username, user.getPassword(), enables, accountNonExpired, credentialsNonExpired, accountNonLocked, auths);
		for (GrantedAuthority s : auths) {
			s.getAuthority();
		}
		System.out.println(auths);
		return userdetail;

	}

	public UserCache getUserCache() {
		return userCache;
	}

	public void setUserCache(UserCache userCache) {
		this.userCache = userCache;
	}

}